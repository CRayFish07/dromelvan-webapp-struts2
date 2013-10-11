package org.dromelvan.struts2.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.Bud;
import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.DromelvaObjekt;
import org.dromelvan.modell.Spelare;


/**
 * Klass som gör det lite lättare att rita upp tabellen med alla bud för en bytesomgång.
 * @author macke
 */
public class ListadSpelareBud extends DromelvaObjekt implements Comparable<ListadSpelareBud> {

    /**
	 *
	 */
	private static final long serialVersionUID = -6580118047429664578L;
	private Bud bud;
    private List<Bud> budList = new ArrayList<Bud>();

	public ListadSpelareBud(Bud bud) {
        this.bud = bud;
	}

    public Deltagare getDeltagare() {
        return bud.getDeltagare();
    }

    public Spelare getSpelare() {
        return bud.getListadSpelare();
    }

    public int getListadSpelarePrioritet() {
        return bud.getListadSpelarePrioritet();
    }

    public List<Bud> getBud() {
        return budList;
    }
    public void addBud(Bud bud) {
        if(bud.getSpelare().getId() > 1) {
            budList.add(bud);
        } else {
            // Dummybud för 'ingen' gör det lite lättare att visa ingen i tabellen
            budList.add(new DummyBud());
        }
    }

    public int getAntalBud() {
        return budList.size();
    }

    public void sortBud() {
        Collections.sort(budList);
    }

    public int compareTo(ListadSpelareBud listadSpelareBud) {
        int compare = getDeltagare().compareTo(listadSpelareBud.getDeltagare());
        if(compare == 0) {
            compare = getListadSpelarePrioritet() - listadSpelareBud.getListadSpelarePrioritet();
        }
        return compare;
    }
}
