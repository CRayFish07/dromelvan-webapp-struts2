package org.dromelvan.struts2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.SpelareMatchStatistik;
import org.dromelvan.modell.SpelareSasong;
import org.dromelvan.modell.Tavling;
import org.dromelvan.modell.statistik.SpelareDeltagareSasongStatistik;
import org.dromelvan.modell.statistik.tabell.DromelvaTavlingTabell;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

public class DeltagareSasongStatistik extends DromelvaActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 8214282112462829093L;
	private int sasongId;
    private int deltagareId;
    private Deltagare deltagare;
    private Tavling tavling;
    private List<SpelareDeltagareSasongStatistik> spelareDeltagareSasongStatistik;
	private org.dromelvan.modell.statistik.tabell.DeltagareSasongStatistik deltagareSasongStatistik;
    private List<Sasong> sasonger;

    public String doExecute() {
    	getSessionManager().beginTransaction();
    	Sasong sasong = getDAOFactory().getSasongDAO().findById(sasongId);
    	deltagare = getDAOFactory().getDeltagareDAO().findById(deltagareId);
    	tavling = getDAOFactory().getTavlingDAO().findBySasongIdOchTyp(sasong.getId(),Tavling.DROMELVAN);

        Map<Spelare,SpelareDeltagareSasongStatistik> map = new HashMap<Spelare,SpelareDeltagareSasongStatistik>();
    	for(SpelareMatchStatistik stats : getDAOFactory().getSpelareMatchStatistikDAO().findByDeltagareOchSasong(deltagare,sasong)) {
			SpelareDeltagareSasongStatistik spelare = map.get(stats.getSpelare());
			if(spelare == null) {
			    SpelareSasong spelareSasong = getDAOFactory().getSpelareSasongDAO().findBySpelareOchSasong(stats.getSpelare(),sasong);
			    if(spelareSasong == null) {
			        spelareSasong = new SpelareSasong();
			        spelareSasong.setSpelare(stats.getSpelare());
			        spelareSasong.setDeltagare(deltagare);
			        spelareSasong.setSasong(sasong);
			        spelareSasong.setPosition(stats.getSpelare().getPosition());
			        spelareSasong.setLag(stats.getSpelare().getLag());
			    }
				spelare = new SpelareDeltagareSasongStatistik(spelareSasong,deltagare,sasong);
				map.put(stats.getSpelare(),spelare);
			}
			spelare.addSpelareMatchStatistik(stats);
    	}
        spelareDeltagareSasongStatistik = new ArrayList<SpelareDeltagareSasongStatistik>(map.values());
        for(SpelareDeltagareSasongStatistik statistik : spelareDeltagareSasongStatistik) {
        	statistik.doRaknaStatistik();
        }
    	Collections.sort(spelareDeltagareSasongStatistik);

        DromelvaTavlingTabell tabell = new DromelvaTavlingTabell(tavling);
        deltagareSasongStatistik = tabell.getTabellObjekt(deltagare);
        if(deltagareSasongStatistik == null) {
        	deltagareSasongStatistik = new org.dromelvan.modell.statistik.tabell.DeltagareSasongStatistik(deltagare,sasong);
        }

        sasonger = getDAOFactory().getSasongDAO().findAll();
        Collections.sort(sasonger);
    	return SUCCESS;
    }

	@ConversionErrorFieldValidator(message = "Felaktigt format på sasongId.")
    public int getSasongId() {
		return sasongId;
	}
	public void setSasongId(int sasongId) {
		this.sasongId = sasongId;
	}

	@ConversionErrorFieldValidator(message = "Felaktigt format på deltagareId.")
    public int getDeltagareId() {
        return deltagareId;
    }
    public void setDeltagareId(int deltagareId) {
        this.deltagareId = deltagareId;
    }

    public Deltagare getDeltagare() {
    	return deltagare;
    }

	public org.dromelvan.modell.statistik.tabell.DeltagareSasongStatistik getDeltagareSasongStatistik() {
		return deltagareSasongStatistik;
	}

    public Tavling getTavling() {
    	return tavling;
    }

    public List<Sasong> getSasonger() {
        return sasonger;
    }

    public List<SpelareDeltagareSasongStatistik> getSpelareDeltagareSasongStatistik() {
    	return spelareDeltagareSasongStatistik;
    }

}