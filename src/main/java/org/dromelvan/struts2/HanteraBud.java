package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.Bud;
import org.dromelvan.modell.DeByte;
import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.SpelareSasong;
import org.dromelvan.struts2.util.budhanterare.BudHanterare;

/**
 * @author macke
 */
public class HanteraBud extends DromelvaAdminAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 4938581500308025299L;
	private DeByteOmgang deByteOmgang;

	public String doExecute() {
        BudHanterare budHanterare = new BudHanterare(getDeByteOmgang());
        budHanterare.hantera();

        Sasong sasong = getDeByteOmgang().getDeOmgang().getTavling().getSasong();
        for(Bud bud : getDeByteOmgang().getBud()) {
            getDAOFactory().getBudDAO().save(bud);
            if(bud.isLyckat()) {
                DeByte deByte = new DeByte();
                deByte.setDeByteOmgang(getDeByteOmgang());
                deByte.setDeltagare(bud.getDeltagare());
                deByte.setSaldSpelare(bud.getListadSpelare());
                deByte.setKoptSpelare(bud.getSpelare());
                deByte.setPris(bud.getAktivtPris());
                getDAOFactory().getDeByteDAO().save(deByte);

                if(bud.getSpelare().getId() > 1) {
                    Spelare spelare = bud.getSpelare();
                    SpelareSasong spelareSasong = getDAOFactory().getSpelareSasongDAO().findBySpelareOchSasong(spelare,sasong);
                    spelare.setDeltagare(deByte.getDeltagare());
                    spelareSasong.setDeltagare(deByte.getDeltagare());
                    spelareSasong.setPris(deByte.getPris());
                    getDAOFactory().getSpelareDAO().save(spelare);
                    getDAOFactory().getSpelareSasongDAO().save(spelareSasong);
                }
            }
        }

        getDeByteOmgang().setStatus(2);
        getDAOFactory().getDeByteOmgangDAO().save(getDeByteOmgang());

        getSessionManager().commitTransaction();
        getSessionManager().evictCache();
	    clearWorkFlowObject();
		return SUCCESS;
	}

	public void validate() {
	    getSessionManager().beginTransaction();

        Sasong sasong = getDAOFactory().getSasongDAO().findById(getDefaultSasongId());
        List<DeByteOmgang> deByteOmgangList = getDAOFactory().getDeByteOmgangDAO().findBySasong(sasong);
        Collections.sort(deByteOmgangList);
        if(!deByteOmgangList.isEmpty()) {
            setDeByteOmgang(deByteOmgangList.get(0));
        }

        if(getDeByteOmgang() == null || getDeByteOmgang().getStatus() != 1) {
            addActionError("Bud för bytesomgången kan inte hanteras i omgångens nuvarande status.");
        }
	}

    public DeByteOmgang getDeByteOmgang() {
        return deByteOmgang;
    }
    public void setDeByteOmgang(DeByteOmgang deByteOmgang) {
        this.deByteOmgang = deByteOmgang;
    }

}
