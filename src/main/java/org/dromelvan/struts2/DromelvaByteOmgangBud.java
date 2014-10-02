package org.dromelvan.struts2;

import java.util.SortedSet;
import java.util.TreeSet;

import org.dromelvan.modell.Bud;
import org.dromelvan.modell.Deltagare;
import org.dromelvan.struts2.util.ListadSpelareBudCollection;


public class DromelvaByteOmgangBud extends DromelvaByten {

    /**
	 *
	 */
	private static final long serialVersionUID = 2570519775953242109L;
	private ListadSpelareBudCollection listadeSpelareBud;

    public ListadSpelareBudCollection getListadeSpelareBud() {
        if(listadeSpelareBud == null) {
            listadeSpelareBud = new ListadSpelareBudCollection(getAnvandare(), getDeByteOmgang());
        }
        return listadeSpelareBud;
    }

    public SortedSet<Bud> getBud() {
        SortedSet<Bud> budSet = new TreeSet<Bud>();
        Deltagare deltagare = (getAnvandare() != null ? getAnvandare().getDeltagare() : null);
        for(Bud bud : getDeByteOmgang().getBud()) {
            if(getDeByteOmgang().getStatus() != 2 && !getAnvandare().isAdministrator()) {
                if(deltagare == null || !bud.getDeltagare().equals(deltagare)) {
                    continue;
                }
            }
            budSet.add(bud);
        }
        return budSet;
    }
}
