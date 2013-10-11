package org.dromelvan.struts2;

import java.util.Date;

import org.dromelvan.modell.Nyhet;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;


public class InsertNyhet extends DromelvaAdminAction {

	/**
	 *
	 */
	private static final long serialVersionUID = -3410022289632573356L;
	private String rubrik;
	private String meddelande;

	public String doExecute() {
        getSessionManager().beginTransaction();
        Nyhet nyhet = new Nyhet();
        nyhet.setAnvandare(getDromelvaSession().getAnvandare());
        nyhet.setRubrik(getRubrik());
        nyhet.setMeddelande(getMeddelande());
        nyhet.setTidpunkt(new Date());
        nyhet = getDAOFactory().getNyhetDAO().save(nyhet);
        getSessionManager().commitTransaction();
		return SUCCESS;
	}

	@RequiredStringValidator(message = "Rubrik saknas")
	public void setRubrik(String rubrik) {
		this.rubrik = rubrik;
	}
	public String getRubrik() {
		return rubrik;
	}

	@RequiredStringValidator(message = "Meddelande saknas")
	public void setMeddelande(String meddelande) {
		this.meddelande = meddelande;
	}
	public String getMeddelande() {
		return meddelande;
	}
}
