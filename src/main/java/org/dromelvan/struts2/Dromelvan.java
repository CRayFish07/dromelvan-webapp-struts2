package org.dromelvan.struts2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.DeOmgang;
import org.dromelvan.modell.Tavling;
import org.dromelvan.modell.persistence.TavlingDAO;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;


public class Dromelvan extends DromelvaActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = -4669902433660240153L;
	private int tavlingId;
	private int startOmgang = 1;
	private Tavling tavling;
	private List<Tavling> tavlingar;

	public String doExecute() {
        getSessionManager().beginTransaction();
        TavlingDAO tavlingDAO = getDAOFactory().getTavlingDAO();
        tavling = tavlingDAO.findByIdOchTyp(getTavlingId(),Tavling.DROMELVAN);
        tavlingar = tavlingDAO.findByTyp(Tavling.DROMELVAN);
        Collections.sort(tavlingar);
		return SUCCESS;
	}

	@ConversionErrorFieldValidator(message = "Felaktigt format på tavlingId.")
	public void setTavlingId(int tavlingId) {
		this.tavlingId = tavlingId;
	}
	public int getTavlingId() {
		return tavlingId;
	}

	@ConversionErrorFieldValidator(message = "Felaktigt format på startOmgang.")
	public void setStartOmgang(int startOmgang) {
		this.startOmgang = startOmgang;
	}
	public int getStartOmgang() {
		return startOmgang;
	}

	public Tavling getTavling() {
		return tavling;
	}

    public int getSasongId() {
        return getTavling().getSasong().getId();
    }

	public List<Tavling>getTavlingar() {
		return tavlingar;
	}

	public List<DeOmgang> getOmgangarSubList() {
		List<DeOmgang> deOmgangarSublist = new ArrayList<DeOmgang>();
		for(DeOmgang deOmgang : tavling.getDeOmgangar()) {
			if(startOmgang <= deOmgang.getNummer()) {
				deOmgangarSublist.add(deOmgang);
				if(deOmgang.getNummer() >= startOmgang + 9) {
					break;
				}
			}
		}
		return deOmgangarSublist;
	}

	@Override
	public DeOmgang getSenastSpeladDeOmgang() {
		DeOmgang senastSpeladDeOmgang = getTavling().getDeOmgangar().first();
		for(DeOmgang deOmgang : getTavling().getDeOmgangar()) {
			if(deOmgang.getOmgang().getIsSpelad()) {
				senastSpeladDeOmgang = deOmgang;
			}
		}
		return senastSpeladDeOmgang;
	}

}
