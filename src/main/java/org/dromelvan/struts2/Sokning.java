package org.dromelvan.struts2;

import java.util.ArrayList;
import java.util.List;

import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.Lag;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.persistence.DeltagareDAO;
import org.dromelvan.modell.persistence.LagDAO;
import org.dromelvan.modell.persistence.SpelareDAO;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;


public class Sokning extends DromelvaActionSupport {

    /**
	 *
	 */
	private static final long serialVersionUID = -2839620665911444428L;
	private String sokning;
    private List<Spelare> spelare = new ArrayList<Spelare>();
    private List<Lag> lag = new ArrayList<Lag>();
    private List<Deltagare> deltagare = new ArrayList<Deltagare>();

	public String doExecute() {
        getSessionManager().beginTransaction();
        SpelareDAO spelareDAO = getDAOFactory().getSpelareDAO();
        spelare = spelareDAO.findByNamnLike(sokning.trim());
//        for(String namn : sokning.split(" ")) {
//            spelare.addAll(spelareDAO.findByNamnLike("%" + namn.trim() + "%"));
//        }

        LagDAO lagDAO = getDAOFactory().getLagDAO();
        for(String namn : sokning.split(" ")) {
            lag.addAll(lagDAO.findByNamnLike("%" + namn.trim() + "%"));
        }

        DeltagareDAO deltagareDAO = getDAOFactory().getDeltagareDAO();
        for(String namn : sokning.split(" ")) {
            deltagare.addAll(deltagareDAO.findByNamnLike("%" + namn.trim() + "%"));
        }

		return SUCCESS;
	}

    @RequiredStringValidator(message = "Sökning saknas", shortCircuit = true, trim = true)
    public String getSokning() {
        return sokning;
    }
    public void setSokning(String sokning) {
        this.sokning = sokning;
    }

    public List<Spelare> getSpelare() {
        return spelare;
    }

    public List<Lag> getLag() {
        return lag;
    }

    public List<Deltagare> getDeltagare() {
        return deltagare;
    }
}
