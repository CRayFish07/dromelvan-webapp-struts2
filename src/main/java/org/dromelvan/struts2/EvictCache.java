package org.dromelvan.struts2;


public class EvictCache extends DromelvaAdminAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2147788787306134097L;

	protected String doExecute() {
    	getSessionManager().evictCache();
        return SUCCESS;
    }

}
