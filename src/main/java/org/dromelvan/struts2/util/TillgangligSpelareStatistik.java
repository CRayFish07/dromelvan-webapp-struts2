package org.dromelvan.struts2.util;

import org.dromelvan.modell.SpelareSasongStatistik;
import org.dromelvan.modell.TillgangligSpelare;


/**
 * På sidan med tillgängliga spelare vill vi visa spelarstatistik. Den här klassen
 * gör det lite enklare.
 * @author macke
 */
public class TillgangligSpelareStatistik implements Comparable<TillgangligSpelareStatistik> {

	private TillgangligSpelare tillgangligSpelare;
	private SpelareSasongStatistik spelareSasongStatistik;

	public TillgangligSpelareStatistik() {}

	public TillgangligSpelare getTillgangligSpelare() {
		return tillgangligSpelare;
	}
	public void setTillgangligSpelare(TillgangligSpelare tillgangligSpelare) {
		this.tillgangligSpelare = tillgangligSpelare;
	}

	public SpelareSasongStatistik getSpelareSasongStatistik() {
		return spelareSasongStatistik;
	}
	public void setSpelareSasongStatistik(SpelareSasongStatistik spelareSasongStatistik) {
		this.spelareSasongStatistik = spelareSasongStatistik;
	}

	public int compareTo(TillgangligSpelareStatistik tillgangligSpelareStatistik) {
		int compare = 0;
		compare = getTillgangligSpelare().getDeltagare().compareTo(tillgangligSpelareStatistik.getTillgangligSpelare().getDeltagare());
		if(compare == 0) {
			compare = getSpelareSasongStatistik().compareTo(tillgangligSpelareStatistik.getSpelareSasongStatistik());
		}
		return compare;
	}
}
