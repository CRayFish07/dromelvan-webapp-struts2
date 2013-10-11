package org.dromelvan.struts2.interceptor;

import org.apache.log4j.Logger;
import org.dromelvan.modell.persistence.hibernate.HibernateSessionManager;
import org.dromelvan.struts2.DromelvaActionSupport;
import org.hibernate.*;

import com.opensymphony.xwork2.*;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


/**
 * Interceptor som stänger Hibernatesessionen efter att jspsidan genererats. På det
 * sättet får jspsidorna tillgång till sessionen, vilket är nödvändigt då Hibernate
 * använder lazy instantiation. Detta implementerar Open Session in View mönstret.
 * @see http://www.hibernate.org/43.html
 * @author macke
 */
public class HibernateInterceptor extends AbstractInterceptor {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -8070555692921715815L;

	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
        } catch(ObjectNotFoundException e) {            
        	// Här hamnar vi om nån försöker göra findById på ett persistent objekt och
        	// ger in ett id som inte motsvarar något objekt i databasen. Detta bör inte
        	// hända om folk följer länkar i applikationen men kan t.ex hända om någon
        	// börjar skriva roliga saker i addressfältet
        	rollbackTransaction();
        	addActionError((Action)invocation.getAction(),"Kunde inte hitta objekt av klass " + 
        			                     e.getEntityName() + " med id " + e.getIdentifier() + ".");
            return DromelvaActionSupport.EXCEPTION;
		} catch(Exception e) {
			// Här borde vi bara hamna pga programmeringsfel
			Logger.getLogger(getClass()).error("Oväntat undantag",e);
			rollbackTransaction();
            addActionError((Action)invocation.getAction(),e.getClass().getName() + ". Transaktionen har gjort rollback.");
			return DromelvaActionSupport.EXCEPTION;
		} finally {            
			HibernateSessionManager.getSessionFactory().getCurrentSession().close();            
		}
	}
	
	private void rollbackTransaction() {
		Transaction transaction = HibernateSessionManager.getSessionFactory().getCurrentSession().getTransaction();
		if(transaction != null && transaction.isActive()) {
			Logger.getLogger(getClass()).info("Gör rollback på transaktionen.");                
			transaction.rollback();
		}		
	}
    
    private void addActionError(Action action,String actionError) {
        ((ActionSupport)action).addActionError(actionError);        
    }
}