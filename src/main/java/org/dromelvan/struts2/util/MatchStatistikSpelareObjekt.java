package org.dromelvan.struts2.util;

import org.dromelvan.modell.Match;
import org.dromelvan.modell.SpelareMatchStatistik;


/**
 * Klass som gör det lite lättare att rita upp laguppställningarna i matchstatistiksidan.
 * @author macke
 */
public class MatchStatistikSpelareObjekt extends MatchStatistikObjekt<SpelareMatchStatistik> {

	public MatchStatistikSpelareObjekt(Match match) {
        super(match);
		setHemmaLagObjekt(new DummySpelareMatchStatistik());
		setBortaLagObjekt(new DummySpelareMatchStatistik());
	}

	public SpelareMatchStatistik getHemmaLagSpelare() {
		return getHemmaLagObjekt();
	}

	public SpelareMatchStatistik getBortaLagSpelare() {
		return getBortaLagObjekt();
	}
}
