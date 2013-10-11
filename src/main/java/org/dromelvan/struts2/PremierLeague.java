package org.dromelvan.struts2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.Omgang;
import org.dromelvan.modell.Tavling;
import org.dromelvan.modell.persistence.TavlingDAO;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;


public class PremierLeague extends DromelvaActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 3200149550293748093L;
	private int tavlingId;
	private int startOmgang = 1;
	private Tavling tavling;
	private List<Tavling> tavlingar;

	public String doExecute() {
        getSessionManager().beginTransaction();
        TavlingDAO tavlingDAO = getDAOFactory().getTavlingDAO();
        tavling = tavlingDAO.findByIdOchTyp(getTavlingId(),Tavling.PREMIER_LEAGUE);
        tavlingar = tavlingDAO.findByTyp(Tavling.PREMIER_LEAGUE);
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

	public List<Omgang> getOmgangarSubList() {
		List<Omgang> omgangarSublist = new ArrayList<Omgang>();
		for(Omgang omgang : tavling.getOmgangar()) {
			if(startOmgang <= omgang.getNummer()) {
				omgangarSublist.add(omgang);
				if(omgang.getNummer() >= startOmgang + 9) {
					break;
				}
			}
		}
		return omgangarSublist;
	}

	@Override
	public Omgang getSenastSpeladOmgang() {
		Omgang senastSpeladOmgang = getTavling().getOmgangar().first();
		for(Omgang omgang : getTavling().getOmgangar()) {
			if(omgang.getIsSpelad()) {
				senastSpeladOmgang = omgang;
			}
		}
		return senastSpeladOmgang;
	}
}
