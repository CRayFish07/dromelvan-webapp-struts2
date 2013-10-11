package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.Nyhet;

public class Index extends DromelvaActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 2980727999273474910L;
	private List<Nyhet> nyheter;
	private boolean visaAlla;

    protected String doExecute() {
        getSessionManager().beginTransaction();
	    nyheter = getDAOFactory().getNyhetDAO().findAll();

	    Collections.sort(nyheter);
	    if(!visaAlla && nyheter.size() > 10) {
	    	nyheter = nyheter.subList(0,10);
	    }
        return SUCCESS;
    }

    public void setVisaAlla(boolean visaAlla) {
    	this.visaAlla = visaAlla;
    }

    public List<Nyhet> getNyheter() {
    	return nyheter;
    }
}
