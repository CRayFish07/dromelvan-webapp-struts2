package org.dromelvan.struts2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.SpelareSasong;
import org.dromelvan.modell.Tavling;
import org.dromelvan.modell.statistik.DeltagareLag;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;



public class AllasLag extends DromelvaActionSupport {

    /**
	 *
	 */
	private static final long serialVersionUID = -8188552276966666132L;
	private int tavlingId;
    private Tavling tavling;
    private List<List<DeltagareLag>> deltagareLag = new ArrayList<List<DeltagareLag>>();
    private List<Tavling> tavlingar;

    public String doExecute() {
        getSessionManager().beginTransaction();
        tavling = getDAOFactory().getTavlingDAO().findByIdOchTyp(getTavlingId() != 0 ? getTavlingId() : getDefaultDeTavlingId(),Tavling.DROMELVAN);

        List<DeltagareLag> rad = new ArrayList<DeltagareLag>();
        for(Deltagare deltagare : tavling.getSasong().getDeltagare()) {
            List<SpelareSasong> spelareSasonger = getDAOFactory().getSpelareSasongDAO().findByDeltagareOchSasong(deltagare, tavling.getSasong());
            rad.add(new DeltagareLag(deltagare, tavling.getSasong(), spelareSasonger));
            int radStorlek = 4;
            if(isMobile()) {
            	radStorlek = 2;
            }
            if(rad.size() == radStorlek) {
                deltagareLag.add(rad);
                rad = new ArrayList<DeltagareLag>();
            }
        }
        if(!rad.isEmpty() && !deltagareLag.contains(rad)) {
            deltagareLag.add(rad);
        }

        tavlingar = getDAOFactory().getTavlingDAO().findByTyp(Tavling.DROMELVAN);
        Collections.sort(tavlingar);
        return SUCCESS;
    }

    @ConversionErrorFieldValidator(message = "Felaktigt format på tavlingId.")
    public int getTavlingId() {
        return tavlingId;
    }
    public void setTavlingId(int tavlingId) {
        this.tavlingId = tavlingId;
    }

    public List<List<DeltagareLag>> getDeltagareLag() {
    	return deltagareLag;
    }

    public Tavling getTavling() {
        return tavling;
    }

    public List<Tavling> getTavlingar() {
        return tavlingar;
    }

    public int getSasongId() {
        return getTavling().getSasong().getId();
    }

    private Set<Spelare> getSpelareSet() {
    	Set<Spelare> spelareSet = new HashSet<Spelare>();
    	for(List<DeltagareLag> list : deltagareLag) {
    		for(DeltagareLag deltagareLag : list) {
    			for(SpelareSasong spelareSasong : deltagareLag.getSpelareSasonger()) {
    				spelareSet.add(spelareSasong.getSpelare());
    			}
    		}
    	}
    	return spelareSet;
    }

    private int getAntalSpelare(Spelare.Position position) {
    	int antal = 0;
    	for(Spelare spelare : getSpelareSet()) {
    		if(spelare.getPosition().equals(position)) {
    			++antal;
    		}
    	}
    	return antal;
    }

    public int getAntalMalvakter() {
    	return getAntalSpelare(Spelare.Position.MALVAKT);
    }

    public int getAntalYtterbackar() {
    	return getAntalSpelare(Spelare.Position.YTTERBACK);
    }

    public int getAntalInnerbackar() {
    	return getAntalSpelare(Spelare.Position.INNERBACK);
    }

    public int getAntalMittfaltare() {
    	return getAntalSpelare(Spelare.Position.MITTFALTARE);
    }

    public int getAntalAnfallare() {
    	return getAntalSpelare(Spelare.Position.ANFALLARE);
    }

}