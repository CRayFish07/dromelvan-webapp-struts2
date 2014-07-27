package org.dromelvan.struts2.util;

import java.util.Collection;
import java.util.HashMap;

import org.dromelvan.modell.Spelare;

/**
 * Map<namn,spelare> för att enklare hantera hämtningen av spelare från deras
 * namn då statistik laddas upp.
 * @author macke
 */
public class SpelareMap extends HashMap<String,Spelare> {

	/**
	 *
	 */
	private static final long serialVersionUID = -7304949026697291161L;

	public SpelareMap() {}

    public SpelareMap(Collection<Spelare> spelareCollection) {
        putAll(spelareCollection);
    }

    public void putAll(Collection<Spelare> spelareCollection) {
        for(Spelare spelare : spelareCollection) {
            add(spelare);
        }
    }

    public void add(Spelare spelare) {
        put(spelare.getNamn(),spelare);
        put(String.valueOf(spelare.getWhoScoredId()),spelare);
    }
}
