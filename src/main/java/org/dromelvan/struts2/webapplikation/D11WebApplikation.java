package org.dromelvan.struts2.webapplikation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dromelvan.struts2.DromelvaActionSupport;



/**
 * Håller i lite statistik om webapplikationen.
 * @author macke
 */
public class D11WebApplikation {
	
	private static List<ActionExecutionLogElement> actionExecutionHistory = new ArrayList<ActionExecutionLogElement>();
	private static Map<String,UserAgent> userAgentMap = new HashMap<String,UserAgent>();
	
    public static synchronized void logAction(DromelvaActionSupport dromelvaActionSupport) {
    	if(actionExecutionHistory.size() == 20) {
    		actionExecutionHistory.remove(19);
    	}
    	actionExecutionHistory.add(new ActionExecutionLogElement(dromelvaActionSupport));
    	Collections.sort(actionExecutionHistory);
    	
    	UserAgent userAgent = userAgentMap.get(dromelvaActionSupport.getUserAgent().getNamn());
    	if(userAgent == null) {
    		userAgent = dromelvaActionSupport.getUserAgent();
    		userAgentMap.put(userAgent.getNamn(),userAgent);
    	}   	
    	userAgent.inkrementeraAntal();
    }

    public static List<ActionExecutionLogElement> getActionExecutionHistory() {
    	return actionExecutionHistory;
    }
    
    public static List<UserAgent> getUserAgents() {
    	List<UserAgent> userAgents = new ArrayList<UserAgent>();
    	userAgents.addAll(userAgentMap.values());
    	Collections.sort(userAgents);
    	return userAgents;
    }
}
