package org.dromelvan.struts2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.Lag;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.SpelareSasongStatistik;
import org.dromelvan.modell.Tavling;
import org.dromelvan.modell.TillgangligSpelare;
import org.dromelvan.struts2.util.LagTillgangligaSpelare;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;


public class DromelvaByteOmgangTillgangligaPrint extends DromelvaActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 6658927643900452241L;
	private int deByteOmgangId;
	private DeByteOmgang deByteOmgang;
    private List<List<LagTillgangligaSpelare>> rader;
	private List<Tavling> tavlingar;
	private List<DeByteOmgang> deByteOmgangar = new ArrayList<DeByteOmgang>();

	public String doExecute() {
        getSessionManager().beginTransaction();
      	deByteOmgang = getDAOFactory().getDeByteOmgangDAO().findById(getDeByteOmgangId());
        Sasong sasong = deByteOmgang.getDeOmgang().getTavling().getSasong();
        Map<Spelare,SpelareSasongStatistik> spelareSasongStatistikMap = new HashMap<Spelare,SpelareSasongStatistik>();

        for(SpelareSasongStatistik spelareSasongStatistik : sasong.getSpelareSasongStatistik()) {
            spelareSasongStatistikMap.put(spelareSasongStatistik.getSpelare(), spelareSasongStatistik);
        }

        Map<Lag,LagTillgangligaSpelare> lagMap = new HashMap<Lag,LagTillgangligaSpelare>();

        for(Lag lag : sasong.getLag()) {
            lagMap.put(lag,new LagTillgangligaSpelare(lag));
        }

        if(!deByteOmgang.getTillgangligaSpelare().isEmpty()) {
            for(TillgangligSpelare tillgangligSpelare : deByteOmgang.getTillgangligaSpelare()) {
                SpelareSasongStatistik spelareSasongStatistik = spelareSasongStatistikMap.get(tillgangligSpelare.getSpelare());
                if(spelareSasongStatistik != null && lagMap.get(spelareSasongStatistik.getLag()) != null) {
                    lagMap.get(spelareSasongStatistik.getLag()).getTillgangligaSpelare().add(tillgangligSpelare);
                }
            }
        } else {
            // Preliminär lista som inte spikats än
            for(Lag lag : sasong.getLag()) {
                for(Spelare spelare : getDAOFactory().getSpelareDAO().findByLag(lag)) {
                    if(spelare.getDeltagare().getId() == 1) {
                        TillgangligSpelare tillgangligSpelare = new TillgangligSpelare();
                        tillgangligSpelare.setSpelare(spelare);

                        SpelareSasongStatistik dummy = new SpelareSasongStatistik();
                        dummy.setId(spelare.getId());
                        dummy.setSpelare(spelare);
                        dummy.setSasong(sasong);
                        dummy.setLag(spelare.getLag());
                        dummy.setPosition(spelare.getPosition());
                        dummy.setDeltagare(spelare.getDeltagare());
                        dummy.setPris(0.0);
                        lagMap.get(lag).getTillgangligaSpelare().add(tillgangligSpelare);
                    }
                }
            }
        }

        List<LagTillgangligaSpelare> lagTillgangligaSpelare = new ArrayList<LagTillgangligaSpelare>(lagMap.values());
        Collections.sort(lagTillgangligaSpelare);
        for(LagTillgangligaSpelare lagTillgangligSpelare : lagTillgangligaSpelare) {
        	lagTillgangligSpelare.sortTillgangligaSpelare();
        }

        rader = new ArrayList<List<LagTillgangligaSpelare>>();
        for(int i = 0; i < 4; ++i) {
            List<LagTillgangligaSpelare> rad = new ArrayList<LagTillgangligaSpelare>();
            for(int j = 0; j < 5; ++j) {
                rad.add(lagTillgangligaSpelare.get((i * 5) + j));
            }
            rader.add(rad);
        }

    	deByteOmgangar = getDAOFactory().getDeByteOmgangDAO().findBySasong(sasong);
        Collections.sort(deByteOmgangar);
    	if(deByteOmgang == null && deByteOmgangar.size() > 0) {
    		deByteOmgang = deByteOmgangar.get(0);
    	}

        tavlingar = getDAOFactory().getTavlingDAO().findByTyp(Tavling.DROMELVAN);
        Collections.sort(tavlingar);
		return SUCCESS;
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

    public List<List<LagTillgangligaSpelare>> getRader() {
        return rader;
    }

	public List<DeByteOmgang> getDeByteOmgangar() {
		return deByteOmgangar;
	}

    public int getSasongId() {
        return getDeByteOmgang().getDeOmgang().getTavling().getSasong().getId();
    }

	public List<Tavling>getTavlingar() {
		return tavlingar;
	}

}
