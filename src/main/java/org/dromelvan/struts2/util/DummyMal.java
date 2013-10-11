package org.dromelvan.struts2.util;

import org.dromelvan.modell.Mal;


/**
 * Genom att fylla upp mållistorna med sådana här objekt blir det enkelt att fylla
 * på det ena lagets målkolumn med tomma celler. Det vill vi göra då vi har ett
 * resultat som inte är oavgjort och tabellen därför blir obalanserad.
 * @author macke
 */
public class DummyMal extends Mal implements Dummy {

	/**
	 *
	 */
	private static final long serialVersionUID = 6760074973638731648L;

	public boolean isDummy() {
		return true;
	}
}
