package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.TillgangligSpelare;
import org.dromelvan.modell.persistence.SpelareDAO;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

/**
 * @author macke
 */
public class TransferlistaSpelare extends DromelvaAdminAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 4938581500308025299L;
	private int spelareId;
	private Spelare spelare;

	@Override
	public boolean isRequiresAdministrator() {
	    return false;
	}

    public String input() {
        clearWorkFlowObject();
        getSessionManager().beginTransaction();
        SpelareDAO spelareDAO = getDAOFactory().getSpelareDAO();
        setSpelare(spelareDAO.findById(getSpelareId()));
        if(!getDromelvaSession().getAnvandare().isAdministrator()) {
            Sasong sasong = getDAOFactory().getSasongDAO().findById(getDefaultSasongId());
            if(!spelareDAO.findTransferlistningsbaraSpelareByAnvandareOchSasong(getDromelvaSession().getAnvandare(), sasong).contains(spelare)) {
                addActionError("Du kan bara transferlista spelare som tillhör ditt eget lag.");
                return ERROR;
            }
        }
        setWorkFlowObject(getSpelare());
        return INPUT;
    }

	public String doExecute() {
	    Spelare spelare = getDAOFactory().getSpelareDAO().findById(getSpelare().getId());
	    TillgangligSpelare tillgangligSpelare = new TillgangligSpelare();
	    tillgangligSpelare.setSpelare(spelare);
	    tillgangligSpelare.setDeltagare(spelare.getDeltagare());
	    tillgangligSpelare.setDeByteOmgang(getDeByteOmgang());
	    tillgangligSpelare.setNy(false);
	    getDAOFactory().getTillgangligSpelareDAO().save(tillgangligSpelare);

// Gör detta då bytesomgången stängs istället
//	    Sasong sasong = getDAOFactory().getSasongDAO().findById(getDefaultSasongId());
//	    SpelareSasong spelareSasong = getDAOFactory().getSpelareSasongDAO().findBySpelareOchSasong(spelare, sasong);
//	    Deltagare deltagare = getDAOFactory().getDeltagareDAO().findById(1);
//
//	    spelare.setDeltagare(deltagare);
//	    spelareSasong.setPris(0.0);
//	    spelareSasong.setDeltagare(deltagare);
//
//	    getDAOFactory().getSpelareDAO().save(spelare);
//	    getDAOFactory().getSpelareSasongDAO().save(spelareSasong);

        getSessionManager().commitTransaction();
        getSessionManager().evictCache();
	    clearWorkFlowObject();
		return SUCCESS;
	}

	public void validate() {
	    getSessionManager().beginTransaction();
        if(getSpelare().getDeltagare().getId() <= 1) {
            addActionError("Endast spelare som hör till någon deltagare kan transferlistas.");
        }
        DeByteOmgang deByteOmgang = getDeByteOmgang();
//        if(deByteOmgang == null || deByteOmgang.getTillgangligaSpelare().isEmpty()) {
//            addActionError("Tillgängliga spelare för bytesomgången måste spikas före spelare kan transferlistas.");
//        }
        if(deByteOmgang == null || deByteOmgang.getStatus() != 0) {
            addActionError("Bytesomgången är stängd för transferlistningar. Bättre lycka nästa gång.");
        }

        for(TillgangligSpelare tillgangligSpelare : deByteOmgang.getTillgangligaSpelare()) {
            if(tillgangligSpelare.getSpelare().equals(getSpelare())) {
                addActionError("Spelaren är redan transferlistad.");
            }
        }
	}

	public boolean validateWorkFlowObject() {
	    try {
	        setSpelare((Spelare)getWorkFlowObject());
	    } catch(ClassCastException e) {}
	    return getSpelare() != null;
	}

    @ConversionErrorFieldValidator(message = "Felaktigt format på spelareId.")
	public int getSpelareId() {
		return spelareId;
	}
	public void setSpelareId(int spelareId) {
		this.spelareId = spelareId;
	}

	public Spelare getSpelare() {
		return spelare;
	}
	protected void setSpelare(Spelare spelare) {
		this.spelare = spelare;
	}

	public DeByteOmgang getDeByteOmgang() {
        Sasong sasong = getDAOFactory().getSasongDAO().findById(getDefaultSasongId());
        List<DeByteOmgang> deByteOmgangList = getDAOFactory().getDeByteOmgangDAO().findBySasong(sasong);
        Collections.sort(deByteOmgangList);
        if(deByteOmgangList.isEmpty()) {
            return null;
        }
        return deByteOmgangList.get(0);
	}
}
