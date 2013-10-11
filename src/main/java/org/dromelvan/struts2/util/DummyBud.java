package org.dromelvan.struts2.util;

import org.dromelvan.modell.Bud;


/**
 * Vi använder dummybud för att representera bud på 'ingen' i sidorna som visar
 * alla bud för en bytesomgång. De buden finns i databasen men det blir lite
 * lättare att rita upp dem så som vi vill ha dem så här.
 * @author macke
 */
public class DummyBud extends Bud implements Dummy {

    /**
	 *
	 */
	private static final long serialVersionUID = 4443902637255329803L;

	public DummyBud() {}

    public boolean isLyckat() {
        return true;
    }

	public boolean isDummy() {
		return true;
	}
}
