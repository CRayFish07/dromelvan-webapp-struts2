package org.dromelvan.struts2;

import java.util.List;

import org.dromelvan.struts2.webapplikation.*;



public class WebappStatistik extends DromelvaAdminAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4089805065064837742L;

	public String doExecute() {
		return SUCCESS;
	}

	public List<ActionExecutionLogElement> getActionExecutionHistory() {
		return D11WebApplikation.getActionExecutionHistory();
	}
	
	public List<UserAgent> getUserAgents() {
		return D11WebApplikation.getUserAgents();
	}
}
