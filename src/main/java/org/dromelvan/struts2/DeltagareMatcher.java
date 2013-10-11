package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Tavling;
import org.dromelvan.modell.statistik.tabell.DromelvaTavlingTabell;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

public class DeltagareMatcher extends DromelvaActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 8138932668948644531L;
	private int deltagareId;
	private int sasongId;
	private org.dromelvan.modell.statistik.tabell.DeltagareSasongStatistik deltagareSasongStatistik;
	private List<Sasong> sasonger;

	public String doExecute() {
        getSessionManager().beginTransaction();
        Deltagare deltagare = getDAOFactory().getDeltagareDAO().findById(getDeltagareId());
        Sasong sasong = getDAOFactory().getSasongDAO().findById(getSasongId() != 0 ? getSasongId() : getDefaultSasongId());
        setSasongId(sasong.getId());
        Tavling tavling = getDAOFactory().getTavlingDAO().findBySasongIdOchTyp(sasong.getId(),Tavling.DROMELVAN);

        DromelvaTavlingTabell tabell = new DromelvaTavlingTabell(tavling);
        deltagareSasongStatistik = tabell.getTabellObjekt(deltagare);
        if(deltagareSasongStatistik == null) {
        	deltagareSasongStatistik = new org.dromelvan.modell.statistik.tabell.DeltagareSasongStatistik(deltagare,sasong);
        }

        sasonger = getDAOFactory().getSasongDAO().findAll();
        Collections.sort(sasonger);
        return SUCCESS;
	}

    @ConversionErrorFieldValidator(message = "Felaktigt format på deltagareId.")
    public int getDeltagareId() {
        return deltagareId;
    }
    public void setDeltagareId(int deltagareId) {
        this.deltagareId = deltagareId;
    }

	@ConversionErrorFieldValidator(message = "Felaktigt format på sasongId.")
	public int getSasongId() {
		return sasongId;
	}
	public void setSasongId(int sasongId) {
		this.sasongId = sasongId;
	}

	public Deltagare getDeltagare() {
	    return getDeltagareSasongStatistik().getDeltagare();
	}

	public org.dromelvan.modell.statistik.tabell.DeltagareSasongStatistik getDeltagareSasongStatistik() {
		return deltagareSasongStatistik;
	}

	public Sasong getSasong() {
		return getDAOFactory().getSasongDAO().findById(getSasongId());
	}

	public List<Sasong> getSasonger() {
		return sasonger;
	}

}