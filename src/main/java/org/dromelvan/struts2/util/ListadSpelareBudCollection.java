package org.dromelvan.struts2.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.dromelvan.modell.Anvandare;
import org.dromelvan.modell.Bud;
import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.Deltagare;


/**
 * @author macke
 */
public class ListadSpelareBudCollection extends ArrayList<ListadSpelareBud> {

    /**
	 *
	 */
	private static final long serialVersionUID = 7694427999734755692L;
	private int maxAntalBud;

	public ListadSpelareBudCollection(Anvandare anvandare, DeByteOmgang deByteOmgang) {
        Map<String,ListadSpelareBud> listadSpelareBudMap = new HashMap<String,ListadSpelareBud>();

        Deltagare deltagare = anvandare.getDeltagare();
        for(Bud bud : deByteOmgang.getBud()) {
            if(deByteOmgang.getStatus() != 2) {
                if(deltagare == null || !bud.getDeltagare().equals(deltagare)) {
                    continue;
                }
            }
            ListadSpelareBud listadSpelareBud = listadSpelareBudMap.get(bud.getDeltagare().getId() + ";" + bud.getListadSpelarePrioritet());
            if(listadSpelareBud == null) {
                listadSpelareBud = new ListadSpelareBud(bud);
                listadSpelareBudMap.put(bud.getDeltagare().getId() + ";" + bud.getListadSpelarePrioritet(),listadSpelareBud);
            }
            listadSpelareBud.addBud(bud);
        }
        addAll(listadSpelareBudMap.values());
        Collections.sort(this);

        for(ListadSpelareBud listadSpelareBud : this) {
            if(listadSpelareBud.getAntalBud() > maxAntalBud) {
                maxAntalBud = listadSpelareBud.getAntalBud();
            }
            listadSpelareBud.sortBud();
        }
	}

    public int getMaxAntalBud() {
        return maxAntalBud;
    }
}
