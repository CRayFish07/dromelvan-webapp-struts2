package org.dromelvan.struts2;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.dromelvan.modell.Anvandare;



public abstract class DromelvaAdminAction extends DromelvaActionSupport implements ServletRequestAware {

	/**
	 *
	 */
	private static final long serialVersionUID = -908770141876012699L;
	private HttpServletRequest httpServletRequest;
	private boolean requiresAdministrator = true;
    protected final static String WORKFLOW_OBJECT = DromelvaAdminAction.class.getName() + "workFlowObject";

	public boolean isLoggedInAdministrator() {
		Anvandare anvandare = getDromelvaSession().getAnvandare();
		if(anvandare != null && anvandare.isAdministrator()) {
			return true;
		}
		return false;
	}

    public String cancel() {
        clearWorkFlowObject();
        return super.cancel();
    }

    public boolean validateWorkFlowObject() {
        return true;
    }

    protected void setWorkFlowObject(Object workFlowObject) {
        setSessionParameter(WORKFLOW_OBJECT,workFlowObject);
    }
    protected Object getWorkFlowObject() {
        return getSessionParameter(WORKFLOW_OBJECT);
    }
    protected void clearWorkFlowObject() {
        setWorkFlowObject(null);
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
    	this.httpServletRequest = httpServletRequest;
    }

    public String getRequestUrl() {
    	return httpServletRequest.getRequestURL() +
    	      (httpServletRequest.getQueryString() != null ? "?" + httpServletRequest.getQueryString() : "");
    }

    public boolean isRequiresAdministrator() {
        return requiresAdministrator;
    }

    public void setRequiresAdministrator(boolean requiresAdministrator) {
        this.requiresAdministrator = requiresAdministrator;
    }

}
