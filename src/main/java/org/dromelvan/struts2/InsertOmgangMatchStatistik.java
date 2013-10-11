package org.dromelvan.struts2;

import java.util.ArrayList;
import java.util.List;

import org.dromelvan.modell.Match;
import org.dromelvan.modell.Omgang;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.SpelareMatchStatistik;
import org.dromelvan.modell.SpelareSasong;
import org.dromelvan.modell.persistence.SpelareSasongDAO;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;


/**
 * @author macke
 */
public class InsertOmgangMatchStatistik extends DromelvaAdminAction {

    /**
	 *
	 */
	private static final long serialVersionUID = 2084769638606548792L;
	private int omgangId;
    private Omgang omgang;

    public String input() {
        clearWorkFlowObject();

        getSessionManager().beginTransaction();
        setOmgang(getDAOFactory().getOmgangDAO().findById(getOmgangId()));
        if(isMatchStatistikInlagd(omgang)) {
            addActionError("Matchstatistik för " + getOmgang().getNamn().toLowerCase() + " är redan inlagd.");
            return ERROR;
        }

        setWorkFlowObject(getOmgang());
        return INPUT;
    }

	public String doExecute() {
        getSessionManager().beginTransaction();
        Omgang omgang = getDAOFactory().getOmgangDAO().findById(getOmgang().getId());
        Sasong sasong = omgang.getTavling().getSasong();
        SpelareSasongDAO spelareSasongDAO = getDAOFactory().getSpelareSasongDAO();
        List<SpelareMatchStatistik> spelareMatchStatistikList = new ArrayList<SpelareMatchStatistik>();

        for(Match match : getOmgang().getMatcher()) {
            List<SpelareSasong> spelareSasongList = spelareSasongDAO.findByLagOchSasong(match.getHemmaLag(),sasong);
            spelareSasongList.addAll(spelareSasongDAO.findByLagOchSasong(match.getBortaLag(),sasong));
            for(SpelareSasong spelareSasong : spelareSasongList) {
                SpelareMatchStatistik spelareMatchStatistik = new SpelareMatchStatistik();
                spelareMatchStatistik.setSpelare(spelareSasong.getSpelare());
                spelareMatchStatistik.setMatch(match);
                spelareMatchStatistik.setLag(spelareSasong.getLag());
                spelareMatchStatistik.setDeltagare(spelareSasong.getDeltagare());
                spelareMatchStatistik.setPosition(spelareSasong.getPosition());
                spelareMatchStatistik.setDeltog(-1);
                spelareMatchStatistikList.add(spelareMatchStatistik);
            }
        }
        getDAOFactory().getSpelareMatchStatistikDAO().saveBatch(spelareMatchStatistikList);

		getSessionManager().commitTransaction();
		getSessionManager().evictCache();
        clearWorkFlowObject();

		return SUCCESS;
	}

    public boolean validateWorkFlowObject() {
        try {
            setOmgang((Omgang)getWorkFlowObject());
        } catch(ClassCastException e) {}
        return getOmgang() != null && !isMatchStatistikInlagd(getOmgang());
    }

	public void setOmgang(Omgang omgang) {
		this.omgang = omgang;
	}
	public void setOmgangId(int omgangId) {
        this.omgangId = omgangId;
    }

    @ConversionErrorFieldValidator(message = "Felaktigt format på omgangId.")
    public int getOmgangId() {
        return omgangId;
    }
    public Omgang getOmgang() {
		return omgang;
	}

    private boolean isMatchStatistikInlagd(Omgang omgang) {
        for(Match match : omgang.getMatcher()) {
            if(match.getSpelareMatchStatistik().size() > 0) {
                return true;
            }
        }
        return false;
    }
}
