package org.dromelvan.struts2.interceptor;

import java.util.Map;

import org.dromelvan.struts2.DromelvaActionSupport;
import org.dromelvan.struts2.DromelvaAdminAction;
import org.dromelvan.struts2.Login;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


public class AuthenticationInterceptor extends AbstractInterceptor {

    /**
	 *
	 */
	private static final long serialVersionUID = -3091270832080111355L;
	private final static String LOGIN_REDIRECT_URL = "loginRedirectUrl";

	public String intercept(ActionInvocation invocation) throws Exception {
		DromelvaActionSupport dromelvaActionSupport = (DromelvaActionSupport)invocation.getAction();

		if(dromelvaActionSupport instanceof DromelvaAdminAction) {
			DromelvaAdminAction dromelvaAdminAction = (DromelvaAdminAction)dromelvaActionSupport;
			if(!dromelvaAdminAction.isLoggedIn()) {
				invocation.getInvocationContext().getSession().put(LOGIN_REDIRECT_URL,dromelvaAdminAction.getRequestUrl());
				return Action.LOGIN;
			}
            if(dromelvaAdminAction.isRequiresAdministrator() &&
               !dromelvaAdminAction.isLoggedInAdministrator()) {
                invocation.getInvocationContext().getSession().put(LOGIN_REDIRECT_URL,dromelvaAdminAction.getRequestUrl());
                return Action.LOGIN;
            }
		}

		// Lite småful hack för att skicka användare vidare till den sidan de försökte komma till
		// efter att de lyckats logga in
		Map<String,Object> sessionMap = invocation.getInvocationContext().getSession();
		String loginRedirectUrl = (String)sessionMap.get(LOGIN_REDIRECT_URL);
		if(loginRedirectUrl != null) {
            // Försöker användaren logga in sätter vi redirectUrl så vi kan skicka användaren vidare
            // till rätt ställe om inloggningen lyckas. Annars glömmer vi vad användaren försökte
            // göra och nullar propertyn för då har användaren tydligen bestämt sig för att göra nåt annat
			if(dromelvaActionSupport instanceof Login) {
				((Login)dromelvaActionSupport).setLoginRedirectUrl(loginRedirectUrl);
			} else {
				sessionMap.put(LOGIN_REDIRECT_URL,null);
			}
		}

		return invocation.invoke();
	}

}