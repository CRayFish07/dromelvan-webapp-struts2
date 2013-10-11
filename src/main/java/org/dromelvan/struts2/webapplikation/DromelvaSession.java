package org.dromelvan.struts2.webapplikation;

import java.util.HashMap;

import org.dromelvan.modell.Anvandare;

/**
 * Klass som håller i smått och gott vi vill spara i sessionen för de som
 * loggar in och administrerar det hela,
 * @author macke
 */
public class DromelvaSession extends HashMap<String,Object> {

	/**
	 *
	 */
	private static final long serialVersionUID = -1715225843924294683L;
	public void setAnvandare(Anvandare anvandare) {
		put("anvandare",anvandare);
	}
	public Anvandare getAnvandare() {
		return (Anvandare)get("anvandare");
	}
}
