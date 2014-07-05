package org.dromelvan.struts2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.Tavling;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

public class DromelvaByten extends DromelvaActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 4298435326899776467L;
	private int tavlingId;
	private int deByteOmgangId;
	private Tavling tavling;
	private DeByteOmgang deByteOmgang;
	private List<Tavling> tavlingar;
	private List<DeByteOmgang> deByteOmgangar = new ArrayList<DeByteOmgang>();

	public String doExecute() {

        getSessionManager().beginTransaction();
        if(tavlingId > 0) {
        	tavling = getDAOFactory().getTavlingDAO().findByIdOchTyp(getTavlingId(),Tavling.DROMELVAN);
        } else {
        	deByteOmgang = getDAOFactory().getDeByteOmgangDAO().findById(getDeByteOmgangId());
        	tavling = deByteOmgang.getDeOmgang().getTavling();
        }

    	deByteOmgangar = getDAOFactory().getDeByteOmgangDAO().findBySasong(tavling.getSasong());
        Collections.sort(deByteOmgangar);
    	if(deByteOmgang == null && deByteOmgangar.size() > 0) {
    		deByteOmgang = deByteOmgangar.get(0);
    	}

        tavlingar = getDAOFactory().getTavlingDAO().findByTyp(Tavling.DROMELVAN);
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

	@ConversionErrorFieldValidator(message = "Felaktigt format på deByteOmgangId.")
	public int getDeByteOmgangId() {
		return deByteOmgangId;
	}
	public void setDeByteOmgangId(int deByteOmgangId) {
		this.deByteOmgangId = deByteOmgangId;
	}

	public DeByteOmgang getDeByteOmgang() {
		return deByteOmgang;
	}

	public List<DeByteOmgang> getDeByteOmgangar() {
		return deByteOmgangar;
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

}
