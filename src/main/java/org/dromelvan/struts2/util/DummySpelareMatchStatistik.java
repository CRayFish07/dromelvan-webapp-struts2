package org.dromelvan.struts2.util;

import org.dromelvan.modell.SpelareMatchStatistik;


/**
 * Ibland kommer något lag till start med färre avbytare. Då kan vi enkelt fylla på
 * det lagets uppställningskolumn med tomma celler genom att fylla på dess uppställning
 * med sådana här objekt.
 * @author macke
 */
public class DummySpelareMatchStatistik extends SpelareMatchStatistik implements Dummy {

	/**
	 *
	 */
	private static final long serialVersionUID = -6516239980016047993L;

	public boolean isDummy() {
		return true;
	}
}
