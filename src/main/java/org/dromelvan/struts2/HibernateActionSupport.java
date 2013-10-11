package org.dromelvan.struts2;

import org.dromelvan.modell.persistence.*;

import com.opensymphony.xwork2.ActionSupport;


public class HibernateActionSupport extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4551442313768309082L;

	protected DAOFactory getDAOFactory() {
		return DAOFactory.getInstance();
	}
		
	protected SessionManager getSessionManager() {
		return SessionManager.getInstance();
	}
	
}
