package org.dromelvan.struts2.util;

import java.util.Collection;
import java.util.HashMap;

import org.dromelvan.modell.Lag;

/**
 * @author macke
 */
public class LagMap extends HashMap<String,Lag> {

    /**
	 *
	 */
	private static final long serialVersionUID = 1416512529486285344L;

	public LagMap() {}

    public LagMap(Collection<Lag> lagCollection) {
        putAll(lagCollection);
    }

    public void putAll(Collection<Lag> lagCollection) {
        for(Lag lag : lagCollection) {
            add(lag);
        }
    }

    public void add(Lag lag) {
        put(lag.getNamn(),lag);
    }
}
