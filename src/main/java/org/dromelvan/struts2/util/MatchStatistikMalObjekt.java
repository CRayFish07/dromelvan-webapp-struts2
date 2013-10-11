package org.dromelvan.struts2.util;

import org.dromelvan.modell.Mal;
import org.dromelvan.modell.Match;


/**
 * Klass som gör det lite lättare att rita upp målen i matchstatistiksidan.
 * @author macke
 */
public class MatchStatistikMalObjekt extends MatchStatistikObjekt<Mal> {

	public MatchStatistikMalObjekt(Match match) {
        super(match);
		setHemmaLagObjekt(new DummyMal());
		setBortaLagObjekt(new DummyMal());
	}

	public Mal getHemmaLagMal() {
		return getHemmaLagObjekt();
	}

	public Mal getBortaLagMal() {
		return getBortaLagObjekt();
	}
}
