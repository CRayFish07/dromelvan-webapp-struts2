package org.dromelvan.struts2.util;

import java.util.ArrayList;

import org.dromelvan.modell.Lag;
import org.dromelvan.modell.Match;


/**
 * Klass som gör det lite lättare att rita upp matchstatistiksidan. I den sidan
 * vill vi ha objekt från två lag i skilda kolumner i en tabell.
 * @author macke
 */
@SuppressWarnings("all")
public class MatchStatistikCollection extends ArrayList<MatchStatistikObjekt> {

    private Match match;

	public MatchStatistikCollection(Match match) {
	    this.match = match;
	}

    protected Match getMatch() {
        return match;
    }

    protected MatchStatistikObjekt getLedigtMatchStatistikObjekt(Lag lag) {
        for(MatchStatistikObjekt objekt : this) {
            if(lag.equals(getMatch().getHemmaLag()) && objekt.getHemmaLagObjekt() instanceof Dummy) {
                return objekt;
            } else if(lag.equals(getMatch().getBortaLag()) && objekt.getBortaLagObjekt() instanceof Dummy) {
                return objekt;
            }
        }
        return null;
    }


}
