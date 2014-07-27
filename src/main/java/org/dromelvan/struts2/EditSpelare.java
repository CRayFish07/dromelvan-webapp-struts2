package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.DeByte;
import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.Lag;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.Spelare.Position;
import org.dromelvan.modell.SpelareSasong;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

/**
 *
 * @author macke
 */
public class EditSpelare extends SpelareInputAction {

    /**
	 *
	 */
	private static final long serialVersionUID = 3425506427319368475L;
	private int spelareId;
    private double pris;

    public String input() {
        clearWorkFlowObject();
        setSpelare(getDAOFactory().getSpelareDAO().findById(getSpelareId()));

        setFornamn(getSpelare().getFornamn());
        setEfternamn(getSpelare().getEfternamn());
        setLagId(getSpelare().getLag().getId());
        setDeltagareId(getSpelare().getDeltagare().getId());
        setPosition(getSpelare().getPosition().ordinal());
        setWhoScoredId(getSpelare().getWhoScoredId());

        Sasong sasong = getDAOFactory().getSasongDAO().findById(getDefaultSasongId());
        SpelareSasong spelareSasong = getDAOFactory().getSpelareSasongDAO().findBySpelareOchSasong(getSpelare(),sasong);
        if(spelareSasong != null) {
        	setPris(spelareSasong.getPris());
        } else {
        	setPris(0.0);
        }

        setWorkFlowObject(getSpelare());
        return INPUT;
    }

	public String doExecute() {
		Lag lag = getDAOFactory().getLagDAO().findById(getLagId());
		Deltagare deltagare = getDAOFactory().getDeltagareDAO().findById(getDeltagareId());

		Spelare spelare = getDAOFactory().getSpelareDAO().findById(getSpelare().getId());
		spelare.setFornamn(getFornamn().trim());
		spelare.setEfternamn(getEfternamn().trim());
        spelare.setLag(lag);
		spelare.setDeltagare(deltagare);
		spelare.setPosition(Position.values()[getPosition()]);
		spelare.setWhoScoredId(getWhoScoredId());
        spelare = getDAOFactory().getSpelareDAO().save(spelare);
        setSpelare(spelare);

		Sasong sasong = getDAOFactory().getSasongDAO().findById(getDefaultSasongId());
        SpelareSasong spelareSasong = getDAOFactory().getSpelareSasongDAO().findBySpelareOchSasong(spelare,sasong);

        if(getPris() > 50.0)  {
        	setPris(0.0);
        }

        double oldPris = 0.0;

		if(spelareSasong == null) {
			// Om vi håller på med en spelare som inte vart med än den här säsongen
			// så måste vi lägga in ett ny objekt för spelaren för den aktuella säsongen.
			// (Om spelaren flyttas till ett lag som är med i Premier League, annars
            // är det onödigt göra det)
            if(sasong.getLag().contains(spelare.getLag())) {
    			spelareSasong = new SpelareSasong();
    			spelareSasong.setSpelare(spelare);
    			spelareSasong.setSasong(sasong);
    			spelareSasong.setLag(spelare.getLag());
    			spelareSasong.setDeltagare(spelare.getDeltagare());
    			spelareSasong.setPosition(spelare.getPosition());
    			// Det här behövs bara vid budgivningen
    			spelareSasong.setPris(getPris());
    			getDAOFactory().getSpelareSasongDAO().save(spelareSasong);
            }
		} else {
		    oldPris = spelareSasong.getPris();
			spelareSasong.setLag(spelare.getLag());
			spelareSasong.setDeltagare(spelare.getDeltagare());
			spelareSasong.setPosition(spelare.getPosition());
			// Behövs bara vid budgivningen
			spelareSasong.setPris(getPris());
            getDAOFactory().getSpelareSasongDAO().save(spelareSasong);
		}

		if(spelareSasong.getPris() != oldPris
		   && isBudgivning()) {
	        List<DeByteOmgang> deByteOmgangList = getDAOFactory().getDeByteOmgangDAO().findBySasong(sasong);
	        Collections.sort(deByteOmgangList);
	        DeByteOmgang deByteOmgang = deByteOmgangList.get(0);

	        DeByte deByte = new DeByte();
	        for(DeByte deByteTemp : deByteOmgang.getDeByten()) {
	            if(deByteTemp.getKoptSpelare().equals(spelare)) {
	                deByte = deByteTemp;
	                break;
	            }
	        }

	        deByte.setDeByteOmgang(deByteOmgang);
            deByte.setDeltagare(spelare.getDeltagare());
            deByte.setSaldSpelare(getDAOFactory().getSpelareDAO().findById(1));
            deByte.setKoptSpelare(spelare);
            deByte.setPris(spelareSasong.getPris());
            getDAOFactory().getDeByteDAO().save(deByte);
		}
		getSessionManager().commitTransaction();
		getSessionManager().evictCache();
        clearWorkFlowObject();

		return SUCCESS;
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

    @ConversionErrorFieldValidator(message = "Felaktigt format på pris.")
	public double getPris() {
		return pris;
	}
	public void setPris(double pris) {
		this.pris = pris;
	}
}
