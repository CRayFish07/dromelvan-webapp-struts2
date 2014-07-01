package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.TillgangligSpelare;
import org.dromelvan.modell.persistence.SpelareDAO;
import org.dromelvan.modell.persistence.TillgangligSpelareDAO;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

/**
 * @author macke
 */
public class AngraTransferListning extends DromelvaAdminAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 4938581500308025299L;
	private int spelareId;
	private Spelare spelare;
	private TillgangligSpelare tillgangligSpelare;

	@Override
	public boolean isRequiresAdministrator() {
	    return false;
	}

	public String doExecute() {
        TillgangligSpelareDAO tillgangligSpelareDAO = getDAOFactory().getTillgangligSpelareDAO();
        tillgangligSpelareDAO.delete(getTillgangligSpelare());

        getSessionManager().commitTransaction();
        getSessionManager().evictCache();
	    clearWorkFlowObject();
		return SUCCESS;
	}

	public void validate() {
	    getSessionManager().beginTransaction();
	    SpelareDAO spelareDAO = getDAOFactory().getSpelareDAO();
	    setSpelare(spelareDAO.findById(getSpelareId()));

	    Deltagare deltagare = getAnvandare().getDeltagare();

        DeByteOmgang deByteOmgang = getDeByteOmgang();
        if(deByteOmgang == null || deByteOmgang.getStatus() != 0) {
            addActionError("Bytesomgången är stängd för transferlistningar. Bättre lycka nästa gång.");
        } else {
            for(TillgangligSpelare tillgangligSpelare : deByteOmgang.getTillgangligaSpelare()) {
                if(tillgangligSpelare.getSpelare().equals(getSpelare())) {
                    if(getAnvandare().isAdministrator()
                       || tillgangligSpelare.getDeltagare().equals(deltagare)) {
                        setTillgangligSpelare(tillgangligSpelare);
                        break;
                    }
                }
            }

            if(getTillgangligSpelare() == null) {
                addActionError("Du kan bara ångra transferlistningar du själv gjort.");
            }
        }
	}

    @ConversionErrorFieldValidator(message = "Felaktigt format på spelareId.")
	public int getSpelareId() {
		return spelareId;
	}
	public void setSpelareId(int spelareId) {
		this.spelareId = spelareId;
	}

	public Spelare getSpelare() {
		return spelare;
	}
	protected void setSpelare(Spelare spelare) {
		this.spelare = spelare;
	}

	public TillgangligSpelare getTillgangligSpelare() {
        return tillgangligSpelare;
    }
    protected void setTillgangligSpelare(TillgangligSpelare tillgangligSpelare) {
        this.tillgangligSpelare = tillgangligSpelare;
    }

    public DeByteOmgang getDeByteOmgang() {
        Sasong sasong = getDAOFactory().getSasongDAO().findById(getDefaultSasongId());
        List<DeByteOmgang> deByteOmgangList = getDAOFactory().getDeByteOmgangDAO().findBySasong(sasong);
        Collections.sort(deByteOmgangList);
        if(deByteOmgangList.isEmpty()) {
            return null;
        }
        return deByteOmgangList.get(0);
	}
}
