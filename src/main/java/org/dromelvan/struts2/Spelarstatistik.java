package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.SpelareSasongStatistik;
import org.dromelvan.modell.Tavling;
import org.dromelvan.modell.util.collections.SpelareSasongStatistikSorter;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

public class Spelarstatistik extends DromelvaActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = -6324360058975609788L;
	private int tavlingId;
	private int kolumn;
	private Tavling tavling;
	private List<Tavling> tavlingar;
	private List<SpelareSasongStatistik> spelareSasongStatistik;

	public String doExecute() {
        getSessionManager().beginTransaction();
        tavling = getDAOFactory().getTavlingDAO().findByIdOchTyp(getTavlingId() != 0 ? getTavlingId() : getDefaultTavlingId(),Tavling.PREMIER_LEAGUE);
        spelareSasongStatistik = new SpelareSasongStatistikSorter(getKolumn()).sort(tavling.getSasong().getSpelareSasongStatistik());
        tavlingar = getDAOFactory().getTavlingDAO().findByTyp(Tavling.PREMIER_LEAGUE);
        Collections.sort(tavlingar);
        return SUCCESS;
	}

	@ConversionErrorFieldValidator(message = "Felaktigt format på tavlingId.")
	public int getTavlingId() {
		return tavlingId;
	}
	public void setTavlingId(int tavlingId) {
		this.tavlingId = tavlingId;
	}

	@ConversionErrorFieldValidator(message = "Felaktigt format på kolumn.")
	public int getKolumn() {
		return kolumn;
	}
	public void setKolumn(int kolumn) {
		this.kolumn = kolumn;
	}

	public Tavling getTavling() {
		return tavling;
	}

	public List<Tavling> getTavlingar() {
		return tavlingar;
	}

	public List<SpelareSasongStatistik> getSpelareSasongStatistik() {
		return spelareSasongStatistik;
	}
}