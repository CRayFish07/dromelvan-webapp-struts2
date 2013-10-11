package org.dromelvan.struts2;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sourceforge.wurfl.core.Device;
import net.sourceforge.wurfl.core.WURFLHolder;
import net.sourceforge.wurfl.core.WURFLManager;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.dromelvan.modell.DeOmgang;
import org.dromelvan.modell.Omgang;
import org.dromelvan.modell.Tavling;
import org.dromelvan.modell.persistence.TavlingDAO;
import org.dromelvan.struts2.webapplikation.D11WebApplikation;
import org.dromelvan.struts2.webapplikation.DromelvaSession;
import org.dromelvan.struts2.webapplikation.UserAgent;


/**
 * Basklass för Actions i Drömelvan.
 * @author macke
 */
public abstract class DromelvaActionSupport extends HibernateActionSupport implements ApplicationAware, SessionAware, ParameterAware, ServletContextAware {

	/**
	 *
	 */
	private static final long serialVersionUID = 5383601465676295609L;
	private long startTid;
    private long stoppTid;

	private Map<String,Object> applicationMap;
	private Map<String,Object> sessionMap;
    private Map<String,String[]> parameterMap;
    private ServletContext servletContext;

	public final static String EXCEPTION = "exception";
    public final static String CANCEL = "cancel";
    public final static String WORKFLOW = "workflow";

    public String execute() throws Exception {
        try {
        	preExecute();
        	return doExecute();
        } finally {
        	postExecute();
        }
    }

    public String cancel() {
        return CANCEL;
    }

    protected void preExecute() {
        startTid = System.currentTimeMillis();
    }

    protected abstract String doExecute();

    protected void postExecute() {
        stoppTid = System.currentTimeMillis();
        D11WebApplikation.logAction(this);
        Logger.getLogger(getClass()).info(getActionNamn() + ".action utford. Tid: " + getActionTimer() + " ms.");
    }

	public boolean getHasFieldError(String field) {
	    return getFieldErrorMessage(field) != null;
	}

	public boolean getHasFieldErrors() {
		return getFieldErrors() != null && !getFieldErrors().isEmpty();
	}

	public String getFieldErrorMessage(String field) {
		List<String> errors = getFieldErrors().get(field);
		if(errors != null && errors.size() > 0) {
			return errors.get(0);
		}
		return null;
	}

	public List<String>getFieldErrorMessages() {
		List<String> fieldErrors = new ArrayList<String>();
		for(String key : getFieldErrors().keySet()) {
			fieldErrors.addAll(getFieldErrors().get(key));
		}
		return fieldErrors;
	}

	public String getStyle() {
		if(!isMobile()) {
			return "default";
		} else {
			return "mobile";
		}
	}

	public boolean isMobile() {
		return getUserAgent().isMobile();
	}

    public String getActionNamn() {
        String namn = getClass().getName();
        return namn.substring(namn.lastIndexOf(".") + 1);
    }

    public long getActionTimer() {
    	return stoppTid - startTid;
    }

    public long getJspTimer() {
        return System.currentTimeMillis() - stoppTid;
    }

    public String getVersion() {
    	return getApplicationParameter("applikation.version");
    }

    public String getApplikationDatum() {
    	return getApplicationParameter("applikation.datum");
    }

    public int getDefaultSasongId() {
    	return Integer.parseInt(getApplicationParameter("applikation.default.sasong_id"));
    }

    public int getDefaultTavlingId() {
    	return Integer.parseInt(getApplicationParameter("applikation.default.tavling_id"));
    }

	public Omgang getSenastSpeladOmgang() {
        TavlingDAO tavlingDAO = getDAOFactory().getTavlingDAO();
        Tavling tavling = tavlingDAO.findByIdOchTyp(getDefaultTavlingId(),Tavling.PREMIER_LEAGUE);

		Omgang senastSpeladOmgang = tavling.getOmgangar().first();
		for(Omgang omgang : tavling.getOmgangar()) {
			if(omgang.getIsSpelad()) {
				senastSpeladOmgang = omgang;
			}
		}
		return senastSpeladOmgang;
	}

    public int getDefaultDeTavlingId() {
    	return Integer.parseInt(getApplicationParameter("applikation.default.de_tavling_id"));
    }

	public DeOmgang getSenastSpeladDeOmgang() {
        TavlingDAO tavlingDAO = getDAOFactory().getTavlingDAO();
        Tavling tavling = tavlingDAO.findByIdOchTyp(getDefaultDeTavlingId(),Tavling.DROMELVAN);

		DeOmgang senastSpeladDeOmgang = tavling.getDeOmgangar().first();
		for(DeOmgang deOmgang : tavling.getDeOmgangar()) {
			if(deOmgang.getOmgang().getIsSpelad()) {
				senastSpeladDeOmgang = deOmgang;
			}
		}
		return senastSpeladDeOmgang;
	}

    public int getTransferBudget() {
        return Integer.parseInt(getApplicationParameter("applikation.transfer_budget"));
    }

    public int getMinstaBud() {
        return Integer.parseInt(getApplicationParameter("applikation.minsta_bud"));
    }

    public void setApplication(Map<String,Object> applicationMap) {
    	this.applicationMap = applicationMap;
    }
    protected String getApplicationParameter(String parameter) {
    	return (String)applicationMap.get(parameter);
    }

    public void setSession(Map<String,Object> sessionMap) {
    	this.sessionMap = sessionMap;
    }
    protected void setSessionParameter(String parameter,Object value) {
    	sessionMap.put(parameter,value);
    }
    protected Object getSessionParameter(String parameter) {
    	return sessionMap.get(parameter);
    }

    public void setParameters(Map<String,String[]> parameterMap) {
        this.parameterMap = parameterMap;
    }
    public String getParameter(String key) {
        String[] values = parameterMap.get(key);
        if(values != null && values.length > 0) {
            return values[0];
        }
        return null;
    }

	public ServletContext getServletContext() {
		return servletContext;
	}
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	protected void setDromelvaSession(DromelvaSession dromelvaSession) {
		setSessionParameter(DromelvaSession.class.getName(),dromelvaSession);
	}
	protected DromelvaSession getDromelvaSession() {
		DromelvaSession dromelvaSession = (DromelvaSession)getSessionParameter(DromelvaSession.class.getName());
		if(dromelvaSession == null) {
			dromelvaSession = new DromelvaSession();
			setDromelvaSession(dromelvaSession);
		}
		return dromelvaSession;
	}

    public String getTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Timestamp(System.currentTimeMillis()));
    }

    public String getRemoteHost() {
    	return ServletActionContext.getRequest().getRemoteHost();
    }

    public String getRemoteAddr() {
    	return ServletActionContext.getRequest().getRemoteAddr();
    }

    public HttpServletRequest getRequest() {
    	return ServletActionContext.getRequest();
    }

    public UserAgent getUserAgent() {
    	UserAgent userAgent = (UserAgent)getRequest().getAttribute("DROMELVA_ACTION_SUPPORT_USER_AGENT");
    	if(userAgent == null) {
	    	WURFLHolder wurfl = (WURFLHolder)getServletContext().getAttribute(WURFLHolder.class.getName());
	    	WURFLManager manager = wurfl.getWURFLManager();
	    	Device device = manager.getDeviceForRequest(getRequest());
	    	userAgent = new UserAgent(getRequest(), device);
	    	getRequest().setAttribute("DROMELVA_ACTION_SUPPORT_USER_AGENT", userAgent);
    	}
    	return userAgent;
    }
}
