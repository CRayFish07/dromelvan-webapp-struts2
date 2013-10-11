/*
 * Copyright (c) 2002-2007 by OpenSymphony
 * All rights reserved.
 */ 
package org.dromelvan.struts2.interceptor;


import java.lang.reflect.Method;
import java.util.List;

import org.dromelvan.struts2.DromelvaActionSupport;

import com.opensymphony.xwork2.*;
import com.opensymphony.xwork2.interceptor.*;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.util.logging.*;


/**
 * Implementation av workflowinterceptor som returnerar DromelvaActionSupport.EXCEPTION om
 * ett conversion fel uppstått. Dvs om någon skrivit en sträng där det skall vara en int.
 * Det går inte att bara plugga in sådan hantering i den interceptor som kommer med
 * XWork så jag har tagit deras kod från DefaultWorkflowInterceptor och hackat in det här. 
 * Inte snyggt men det gör vad vi vill. Det här skulle inte vara nödvändigt om Struts 
 * hade lite mer flexibel hantering av detta men i väntan på det får det här duga.
 *
 * @author Jason Carreira
 * @author Rainer Hermanns
 * @author Alexandru Popescu
 * @author Philip Luppens
 * @author tm_jee
 * @see com.opensymphony.xwork2.interceptor.DefaultWorkflowInterceptor
 */
public class DromelvaWorkflowInterceptor extends MethodFilterInterceptor {

    private static final long serialVersionUID = 7563014655616490865L;

    private static final Logger LOG = LoggerFactory.getLogger(DromelvaWorkflowInterceptor.class);

    private String inputResultName = Action.INPUT;

    /**
     * Set the <code>inputResultName</code> (result name to be returned when
     * a action / field error is found registered). Default to {@link Action#INPUT}
     *
     * @param inputResultName what result name to use when there was validation error(s).
     */
    public void setInputResultName(String inputResultName) {
        this.inputResultName = inputResultName;
    }

    /**
     * Intercept {@link ActionInvocation} and returns a <code>inputResultName</code>
     * when action / field errors is found registered.
     *
     * @return String result name
     */
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
    	
        Object action = invocation.getAction();

        if (action instanceof ValidationAware) {
            ValidationAware validationAwareAction = (ValidationAware) action;

            if (validationAwareAction.hasErrors()) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Errors on action " + validationAwareAction + ", returning result name 'input'");
                }

                String resultName = inputResultName;

                if (action instanceof ValidationWorkflowAware) {
                    resultName = ((ValidationWorkflowAware) action).getInputResultName();
                }

                InputConfig annotation = action.getClass().getMethod(invocation.getProxy().getMethod(), new Class[0]).getAnnotation(InputConfig.class);
                if (annotation != null) {
                    if (!annotation.methodName().equals("")) {
                        Method method = action.getClass().getMethod(annotation.methodName());
                        resultName = (String) method.invoke(action);
                    } else {
                        resultName = annotation.resultName();
                    }
                }
                // Det här är Mackes fula hack --------------------------------------------
                for(String key : validationAwareAction.getFieldErrors().keySet()) {
                	List<String> errors = validationAwareAction.getFieldErrors().get(key);
                	for(String error : errors) {                		
                		// Finns det ett fel med detta meddelande så har vi fått det fel
                		// vi letar efter. Ta bort det och lämna bara felmeddelanden 
                		// vi satt i våra actions
                		if(error.startsWith("Invalid field value for field")) {
                			if(errors.size() > 1) {
                				errors.remove(error);
                			}
                	        resultName = DromelvaActionSupport.EXCEPTION;
                		}
                	}
                }
                // ------------------------------------------------------------------------
                return resultName;
            }
        }
        return invocation.invoke();
    }

}
