package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.Tavling;
import org.dromelvan.modell.persistence.TavlingDAO;
import org.dromelvan.modell.statistik.tabell.DromelvaTavlingOturTabell;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;


public class DromelvanOturTabell extends DromelvaActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 693592796450060991L;
	private int tavlingId;
	private boolean haltande;
	private Tavling tavling;
	private List<Tavling> tavlingar;
	private DromelvaTavlingOturTabell tabell;

	public String doExecute() {
        getSessionManager().beginTransaction();
        TavlingDAO tavlingDAO = getDAOFactory().getTavlingDAO();
        tavling = tavlingDAO.findByIdOchTyp(getTavlingId(),Tavling.DROMELVAN);
        tavlingar = tavlingDAO.findByTyp(Tavling.DROMELVAN);
        Collections.sort(tavlingar);
        tabell = new DromelvaTavlingOturTabell(tavling);
		return SUCCESS;
	}

	@ConversionErrorFieldValidator(message = "Felaktigt format på tavlingId.")
	public void setTavlingId(int tavlingId) {
		this.tavlingId = tavlingId;
	}
	public int getTavlingId() {
		return tavlingId;
	}

	public boolean isHaltande() {
		return haltande;
	}
	public void setHaltande(boolean haltande) {
		this.haltande = haltande;
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

	public DromelvaTavlingOturTabell getTabell() {
		return tabell;
	}
}
