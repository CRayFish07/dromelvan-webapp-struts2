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
import org.dromelvan.modell.TillgangligSpelare;
import org.dromelvan.struts2.util.TillgangligSpelareStatistik;



/**
 * @author macke
 */
public class DromelvaByteOmgangTillgangliga extends DromelvaByten {

	/**
	 *
	 */
	private static final long serialVersionUID = -5498705810540056970L;

	public List<TillgangligSpelareStatistik> getTillgangligSpelareStatistik() {
        if(getTillgangligaInlagda()) {
            return getTillgangligSpelareStatistikInlagd();
        } else {
            return getTillgangligSpelareStatistikPreliminar();
        }
	}

    public boolean getTillgangligaInlagda() {
        return !getDeByteOmgang().getTillgangligaSpelare().isEmpty();
    }

    public List<TillgangligSpelareStatistik> getTillgangligSpelareStatistikInlagd() {
        Map<Spelare,SpelareSasongStatistik> spelareSasongStatistikMap = new HashMap<Spelare,SpelareSasongStatistik>();
        List<TillgangligSpelareStatistik> tillgangligSpelareStatistik = new ArrayList<TillgangligSpelareStatistik>();

        for(SpelareSasongStatistik spelareSasongStatistik : getDeByteOmgang().getDeOmgang().getTavling().getSasong().getSpelareSasongStatistik()) {
            spelareSasongStatistikMap.put(spelareSasongStatistik.getSpelare(), spelareSasongStatistik);
        }

        Sasong sasong = getDeByteOmgang().getDeOmgang().getTavling().getSasong();
    	for(Lag lag : sasong.getLag()) {
    		for(Spelare spelare : getDAOFactory().getSpelareDAO().findByLag(lag)) {
    			if(spelareSasongStatistikMap.get(spelare) == null) {
        			SpelareSasongStatistik dummy = new SpelareSasongStatistik();
        			dummy.setId(spelare.getId());
        			dummy.setSpelare(spelare);
        			dummy.setSasong(sasong);
        			dummy.setLag(spelare.getLag());
        			dummy.setPosition(spelare.getPosition());
        			dummy.setDeltagare(spelare.getDeltagare());
        			dummy.setPris(0.0);
        			spelareSasongStatistikMap.put(spelare,dummy);
    			}
    		}
    	}

        for(TillgangligSpelare tillgangligSpelare : getDeByteOmgang().getTillgangligaSpelare()) {
            TillgangligSpelareStatistik statistik = new TillgangligSpelareStatistik();
            statistik.setTillgangligSpelare(tillgangligSpelare);
            statistik.setSpelareSasongStatistik(spelareSasongStatistikMap.get(tillgangligSpelare.getSpelare()));
            // Tidigare säsonger kan det ha slunkit med någon spelare som inte spelat alls och inte har någon
            // registrerad säsonginfo
            if(statistik.getSpelareSasongStatistik() != null) {
                tillgangligSpelareStatistik.add(statistik);
            }
        }
        Collections.sort(tillgangligSpelareStatistik);
        return tillgangligSpelareStatistik;
    }

    public List<TillgangligSpelareStatistik> getTillgangligSpelareStatistikPreliminar() {
        Map<Spelare,TillgangligSpelare> ejNyaMap = new HashMap<Spelare,TillgangligSpelare>();
        for(DeByteOmgang deByteOmgang : getDeByteOmgangar()) {
            for(TillgangligSpelare tillgangligSpelare : deByteOmgang.getTillgangligaSpelare()) {
                ejNyaMap.put(tillgangligSpelare.getSpelare(),tillgangligSpelare);
            }
        }

        List<TillgangligSpelareStatistik> tillgangligSpelareStatistik = new ArrayList<TillgangligSpelareStatistik>();

        // Om vi inte spikat tillgängliga spelare än så skapar vi dummyobjekt för alla spelare som är lediga och visar dem
        Sasong sasong = getDeByteOmgang().getDeOmgang().getTavling().getSasong();
        List<SpelareSasongStatistik> spelareSasongStatistikCollection = new ArrayList<SpelareSasongStatistik>(sasong.getSpelareSasongStatistik());
        if(spelareSasongStatistikCollection.isEmpty()) {
        	// Behöver detta för budgivningen då ingen spelat nån match än
        	for(Lag lag : sasong.getLag()) {
        		for(Spelare spelare : getDAOFactory().getSpelareDAO().findByLag(lag)) {
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
        	ejNyaMap = null;
        } else {
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
        }
        Collections.sort(spelareSasongStatistikCollection);

        for(SpelareSasongStatistik spelareSasongStatistik : spelareSasongStatistikCollection) {
            if(spelareSasongStatistik.getDeltagare().getId() <= 1 && spelareSasongStatistik.getLag().getId() > 1) {
                TillgangligSpelare tillgangligSpelare = new TillgangligSpelare();
                tillgangligSpelare.setDeByteOmgang(getDeByteOmgang());
                tillgangligSpelare.setSpelare(spelareSasongStatistik.getSpelare());
                tillgangligSpelare.setDeltagare(spelareSasongStatistik.getDeltagare());
                if(ejNyaMap != null && ejNyaMap.get(spelareSasongStatistik.getSpelare()) == null) {
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
