package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.Tavling;
import org.dromelvan.modell.persistence.TavlingDAO;
import org.dromelvan.modell.statistik.tabell.PremierLeagueTavlingTabell;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;


public class PremierLeagueTabell extends DromelvaActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = -1561732745480525776L;
	private int tavlingId;
	private Tavling tavling;
	private List<Tavling> tavlingar;
	private PremierLeagueTavlingTabell tabell;

	public String doExecute() {
        getSessionManager().beginTransaction();
        TavlingDAO tavlingDAO = getDAOFactory().getTavlingDAO();
        tavling = tavlingDAO.findByIdOchTyp(getTavlingId(),Tavling.PREMIER_LEAGUE);
        tavlingar = tavlingDAO.findByTyp(Tavling.PREMIER_LEAGUE);
        Collections.sort(tavlingar);
        tabell = new PremierLeagueTavlingTabell(tavling);
		return SUCCESS;
	}

	@ConversionErrorFieldValidator(message = "Felaktigt format på tavlingId.")
	public void setTavlingId(int tavlingId) {
		this.tavlingId = tavlingId;
	}
	public int getTavlingId() {
		return tavlingId;
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

	public PremierLeagueTavlingTabell getTabell() {
		return tabell;
	}
}
