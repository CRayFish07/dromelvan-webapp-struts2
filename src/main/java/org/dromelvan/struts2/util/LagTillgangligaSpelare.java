package org.dromelvan.struts2.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.DromelvaObjekt;
import org.dromelvan.modell.Lag;
import org.dromelvan.modell.TillgangligSpelare;


/**
 * Håller i tillgängliga spelare per lag för att enklare visa den printervänliga
 * sidan med lediga spelare för en bytesomgång.
 * @author macke
 */
public class LagTillgangligaSpelare extends DromelvaObjekt implements Comparable<LagTillgangligaSpelare> {

    /**
	 *
	 */
	private static final long serialVersionUID = 182363035375798285L;
	private Lag lag;
	private List<TillgangligSpelare> tillgangligaSpelare = new ArrayList<TillgangligSpelare>();

	public LagTillgangligaSpelare(Lag lag) {
	    setLag(lag);
    }

    public Lag getLag() {
        return lag;
    }
    public void setLag(Lag lag) {
        this.lag = lag;
    }

    public List<TillgangligSpelare> getTillgangligaSpelare() {
        return tillgangligaSpelare;
    }
    public void setTillgangligaSpelare(List<TillgangligSpelare> tillgangligaSpelare) {
        this.tillgangligaSpelare = tillgangligaSpelare;
    }

    public int compareTo(LagTillgangligaSpelare lagTillgangligaSpelare) {
        return getLag().compareTo(lagTillgangligaSpelare.getLag());
    }

    public void sortTillgangligaSpelare() {
    	Collections.sort(getTillgangligaSpelare());
    }
}
