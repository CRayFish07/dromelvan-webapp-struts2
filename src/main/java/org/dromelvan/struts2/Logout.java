package org.dromelvan.struts2;

public class Logout extends DromelvaActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5980523461012319417L;

	public String doExecute() {
        getDromelvaSession().setAnvandare(null);
		return SUCCESS;
	}
}
