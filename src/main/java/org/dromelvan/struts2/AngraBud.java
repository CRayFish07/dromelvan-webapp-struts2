package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.Bud;
import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.persistence.BudDAO;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

/**
 * @author macke
 */
public class AngraBud extends DromelvaAdminAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 4938581500308025299L;
	private int budId;
	private Bud bud;
	private DeByteOmgang deByteOmgang;

	@Override
	public boolean isRequiresAdministrator() {
	    return false;
	}

	public String doExecute() {
	    getDAOFactory().getBudDAO().delete(getBud());
        getSessionManager().commitTransaction();
        getSessionManager().evictCache();
	    clearWorkFlowObject();
		return SUCCESS;
	}

	public void validate() {
	    getSessionManager().beginTransaction();
	    BudDAO budDAO = getDAOFactory().getBudDAO();
	    setBud(budDAO.findById(getBudId()));

	    Deltagare deltagare = getAnvandare().getDeltagare();

        Sasong sasong = getDAOFactory().getSasongDAO().findById(getDefaultSasongId());
        List<DeByteOmgang> deByteOmgangList = getDAOFactory().getDeByteOmgangDAO().findBySasong(sasong);
        Collections.sort(deByteOmgangList);
        if(!deByteOmgangList.isEmpty()) {
            setDeByteOmgang(deByteOmgangList.get(0));
        }

        if(getDeByteOmgang() == null || getDeByteOmgang().getStatus() != 1) {
            addActionError("Bytesomgången är stängd för bud. Bättre lycka nästa gång.");
        } else if(!getBud().getDeltagare().equals(deltagare)) {
            addActionError("Du kan bara ångra transferlistningar du själv gjort.");
        }
	}

	@ConversionErrorFieldValidator(message = "Felaktigt format på budId.")
	public int getBudId() {
        return budId;
    }

    public void setBudId(int budId) {
        this.budId = budId;
    }

    public Bud getBud() {
        return bud;
    }

    public void setBud(Bud bud) {
        this.bud = bud;
    }

    public void setDeByteOmgang(DeByteOmgang deByteOmgang) {
        this.deByteOmgang = deByteOmgang;
    }

    public DeByteOmgang getDeByteOmgang() {
        return deByteOmgang;
	}
}
