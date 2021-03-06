package org.dromelvan.struts2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dromelvan.modell.DeByte;
import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.Lag;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.SpelareSasong;
import org.dromelvan.modell.SpelareSasongStatistik;
import org.dromelvan.modell.TillgangligSpelare;
import org.dromelvan.struts2.util.TillgangligSpelareStatistik;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;


/**
 *
 * @author macke
 */
public class InsertTillgangligaSpelare extends DromelvaAdminAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1829608030457242395L;
	private int deByteOmgangId;
	private DeByteOmgang deByteOmgang;

    public String input() {
        clearWorkFlowObject();
        getSessionManager().beginTransaction();
        setDeByteOmgang(getDAOFactory().getDeByteOmgangDAO().findById(getDeByteOmgangId()));

        setWorkFlowObject(getDeByteOmgang());
        return INPUT;
    }

	public String doExecute() {
	    getSessionManager().beginTransaction();
	    DeByteOmgang deByteOmgang = getDAOFactory().getDeByteOmgangDAO().findById(getDeByteOmgang().getId());
        if(deByteOmgang.getStatus() != 0) {
            addActionError("Tillgängliga spelare för bytesomgången har redan spikats.");
            return ERROR;
        }

        // Hämta alla lediga spelare
        List<TillgangligSpelareStatistik> tillgangligSpelareStatistikList = getTillgangligSpelareStatistik();

        // Gör de transferlistade spelarna lediga.
        Deltagare deltagare = getDAOFactory().getDeltagareDAO().findById(1);
        for(TillgangligSpelare tillgangligSpelare : getDAOFactory().getTillgangligSpelareDAO().findByDeByteOmgang(deByteOmgang)) {
            Sasong sasong = deByteOmgang.getDeOmgang().getTavling().getSasong();
            Spelare spelare = tillgangligSpelare.getSpelare();
            SpelareSasong spelareSasong = getDAOFactory().getSpelareSasongDAO().findBySpelareOchSasong(spelare, sasong);

            DeByte deByte = new DeByte();
            deByte.setDeByteOmgang(deByteOmgang);
            deByte.setDeltagare(spelare.getDeltagare());
            deByte.setSaldSpelare(spelare);
            deByte.setKoptSpelare(getDAOFactory().getSpelareDAO().findById(1));
            deByte.setPris(0.0);
            getDAOFactory().getDeByteDAO().save(deByte);

            spelare.setDeltagare(deltagare);
            spelareSasong.setPris(0.0);
            spelareSasong.setDeltagare(deltagare);

            getDAOFactory().getSpelareDAO().save(spelare);
            getDAOFactory().getSpelareSasongDAO().save(spelareSasong);
        }

        // Transferlista lediga spelare
        List<TillgangligSpelare> tillgangligaSpelare = new ArrayList<TillgangligSpelare>();
        for(TillgangligSpelareStatistik tillgangligSpelareStatistik : tillgangligSpelareStatistikList) {
            tillgangligaSpelare.add(tillgangligSpelareStatistik.getTillgangligSpelare());
        }
        getDAOFactory().getTillgangligSpelareDAO().saveBatch(tillgangligaSpelare);

        deByteOmgang.setStatus(1);
        getDAOFactory().getDeByteOmgangDAO().save(deByteOmgang);

        getSessionManager().commitTransaction();
        getSessionManager().evictCache();
	    clearWorkFlowObject();
		return SUCCESS;
	}

    public boolean validateWorkFlowObject() {
        try {
            setDeByteOmgang((DeByteOmgang)getWorkFlowObject());
        } catch(ClassCastException e) {}
        return getDeByteOmgang() != null;
    }

	@ConversionErrorFieldValidator(message = "Felaktigt format på deByteOmgangId.")
	public int getDeByteOmgangId() {
		return deByteOmgangId;
	}
	public void setDeByteOmgangId(int deByteOmgangId) {
		this.deByteOmgangId = deByteOmgangId;
	}

    public DeByteOmgang getDeByteOmgang() {
        return deByteOmgang;
    }
    public void setDeByteOmgang(DeByteOmgang deByteOmgang) {
        this.deByteOmgang = deByteOmgang;
    }

    public List<TillgangligSpelareStatistik> getTillgangligSpelareStatistik() {
        Map<Integer,TillgangligSpelare> ejNyaMap = new HashMap<Integer,TillgangligSpelare>();
        for(DeByteOmgang deByteOmgang : getDAOFactory().getDeByteOmgangDAO().findBySasong(getDeByteOmgang().getDeOmgang().getTavling().getSasong())) {
            for(TillgangligSpelare tillgangligSpelare : deByteOmgang.getTillgangligaSpelare()) {
                ejNyaMap.put(tillgangligSpelare.getSpelare().getId(),tillgangligSpelare);
            }
        }

        List<TillgangligSpelareStatistik> tillgangligSpelareStatistik = new ArrayList<TillgangligSpelareStatistik>();

        Sasong sasong = getDeByteOmgang().getDeOmgang().getTavling().getSasong();
        Collection<SpelareSasongStatistik> spelareSasongStatistikCollection = new ArrayList<SpelareSasongStatistik>(sasong.getSpelareSasongStatistik());
    	for(Lag lag : sasong.getLag()) {
    		for(Spelare spelare : getDAOFactory().getSpelareDAO().findByLag(lag)) {
    			if(sasong.getSpelareSasongStatistik(spelare) == null) {
        			SpelareSasongStatistik dummy = new SpelareSasongStatistik();
        			dummy.setId(spelare.getId());
        			dummy.setSpelare(spelare);
        			dummy.setSasong(sasong);
        			dummy.setLag(spelare.getLag());
        			dummy.setPosition(spelare.getPosition());
        			dummy.setDeltagare(spelare.getDeltagare());
        			dummy.setPris(0.0);
        			spelareSasongStatistikCollection.add(dummy);
    			}
    		}
    	}

        // Om vi inte spikat tillgängliga spelare än så skapar vi dummyobjekt för alla spelare som är lediga och visar dem
        for(SpelareSasongStatistik spelareSasongStatistik : spelareSasongStatistikCollection) {
            if(spelareSasongStatistik.getDeltagare().getId() <= 1 && spelareSasongStatistik.getLag().getId() > 1) {
                TillgangligSpelare tillgangligSpelare = new TillgangligSpelare();
                tillgangligSpelare.setDeByteOmgang(getDeByteOmgang());
                tillgangligSpelare.setSpelare(spelareSasongStatistik.getSpelare());
                tillgangligSpelare.setDeltagare(spelareSasongStatistik.getDeltagare());
                if(ejNyaMap.get(spelareSasongStatistik.getSpelare().getId()) == null) {
                    tillgangligSpelare.setNy(true);
                }

                TillgangligSpelareStatistik statistik = new TillgangligSpelareStatistik();
                statistik.setTillgangligSpelare(tillgangligSpelare);
                statistik.setSpelareSasongStatistik(spelareSasongStatistik);
                tillgangligSpelareStatistik.add(statistik);
            }
        }

        return tillgangligSpelareStatistik;
    }

}
