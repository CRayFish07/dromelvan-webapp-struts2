package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.Sasong;

public class Nerladdningar extends DromelvaActionSupport {

    /**
	 *
	 */
	private static final long serialVersionUID = 2136263377693072138L;
	private List<Sasong> sasonger;

    public String doExecute() {
        getSessionManager().beginTransaction();
        sasonger = getDAOFactory().getSasongDAO().findAll();
        Collections.reverse(sasonger);
        return SUCCESS;
    }

    public List<Sasong> getSasonger() {
        return sasonger;
    }
}
