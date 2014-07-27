package org.dromelvan.struts2.util;

import java.util.Collection;
import java.util.HashMap;

import org.dromelvan.modell.SpelareMatchStatistik;

/**
 * Map<namn,SpelareMatchStatistik> för att enklare hantera hämtningen av spelare från deras
 * namn då statistik laddas upp.
 * @author macke
 */
public class SpelareMatchStatistikMap extends HashMap<String,SpelareMatchStatistik> {

    /**
	 *
	 */
	private static final long serialVersionUID = -2080315523412335728L;

	public SpelareMatchStatistikMap() {}

    public SpelareMatchStatistikMap(Collection<SpelareMatchStatistik> spelareMatchStatistikCollection) {
        putAll(spelareMatchStatistikCollection);
    }

    public void putAll(Collection<SpelareMatchStatistik> spelareMatchStatistikCollection) {
        for(SpelareMatchStatistik spelareMatchStatistik : spelareMatchStatistikCollection) {
            add(spelareMatchStatistik);
        }
    }

    public void add(SpelareMatchStatistik spelareMatchStatistik) {
        put(spelareMatchStatistik.getSpelare().getNamn(),spelareMatchStatistik);
        put(String.valueOf(spelareMatchStatistik.getSpelare().getWhoScoredId()),spelareMatchStatistik);
    }
}
