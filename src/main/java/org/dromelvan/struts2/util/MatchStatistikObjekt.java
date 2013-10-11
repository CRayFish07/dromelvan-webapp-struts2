package org.dromelvan.struts2.util;

import org.dromelvan.modell.Lag;
import org.dromelvan.modell.Match;
import org.dromelvan.modell.PersistentObjekt;


/**
 * Klass som gör det lite lättare att rita upp matchstatistiksidan där vi
 * vill ha en tabell med de två olika lagens data i två skilda kolumner.
 * @author macke
 */
public class MatchStatistikObjekt<T extends PersistentObjekt> {

    private Match match;
	private T hemmaLagObjekt;
	private T bortaLagObjekt;

	public MatchStatistikObjekt(Match match) {
	    setMatch(match);
    }

	public T getHemmaLagObjekt() {
		return hemmaLagObjekt;
	}
	public void setHemmaLagObjekt(T hemmaLagObjekt) {
		this.hemmaLagObjekt = hemmaLagObjekt;
	}

	public T getBortaLagObjekt() {
		return bortaLagObjekt;
	}
	public void setBortaLagObjekt(T bortaLagObjekt) {
		this.bortaLagObjekt = bortaLagObjekt;
	}

	public Match getMatch() {
        return match;
    }
    public void setMatch(Match match) {
        this.match = match;
    }

    public void setObjektForLag(T persistentObjekt,Lag lag) {
        if(lag.equals(getMatch().getHemmaLag())) {
            setHemmaLagObjekt(persistentObjekt);
        } else {
            setBortaLagObjekt(persistentObjekt);
        }
    }
}
