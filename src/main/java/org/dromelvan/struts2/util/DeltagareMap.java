package org.dromelvan.struts2.util;

import java.util.Collection;
import java.util.HashMap;

import org.dromelvan.modell.Deltagare;

/**
 * Map<namn,deltagare> för att enklare hantera hämtningen av deltagare från deras
 * namn då bud/byten laddas upp.
 * @author macke
 */
public class DeltagareMap extends HashMap<String,Deltagare> {

	/**
	 *
	 */
	private static final long serialVersionUID = -8171566844514240990L;

	public DeltagareMap() {}

    public DeltagareMap(Collection<Deltagare> deltagareCollection) {
        putAll(deltagareCollection);
    }

    public void putAll(Collection<Deltagare> deltagareCollection) {
        for(Deltagare deltagare : deltagareCollection) {
            add(deltagare);
        }
    }

    public void add(Deltagare deltagare) {
        put(deltagare.getNamn(),deltagare);
    }
}
