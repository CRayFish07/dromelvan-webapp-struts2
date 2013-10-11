package org.dromelvan.struts2.interceptor;

import org.dromelvan.struts2.*;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.*;


/**
 * Interceptor som kallar validateWorkFlowObject() i den action som körs. Implementera den
 * metoden för att kontrollera att allt actionen förväntar sig att användaren gjort i tidigare
 * sidor i aktuellt workflow verkligen gjorts och att alla objekt den behöver finns i användarens
 * session.
 * @author macke
 */
public class WorkFlowValidatorInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1525194228342066574L;

	public String doIntercept(ActionInvocation invocation) throws Exception {
		DromelvaActionSupport dromelvaActionSupport = (DromelvaActionSupport)invocation.getAction();

		if(dromelvaActionSupport instanceof DromelvaAdminAction) {
            if(!((DromelvaAdminAction)dromelvaActionSupport).validateWorkFlowObject()) {
                return DromelvaActionSupport.WORKFLOW;
            }
		}
        return invocation.invoke();
	}

}