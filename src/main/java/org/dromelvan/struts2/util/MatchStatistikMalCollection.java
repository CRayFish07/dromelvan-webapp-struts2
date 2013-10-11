package org.dromelvan.struts2.util;

import org.dromelvan.modell.Mal;
import org.dromelvan.modell.Match;


/**
 * Klass som gör det lite lättare att visa mål i matchstatistiksidan.
 * @author macke
 */
public class MatchStatistikMalCollection extends MatchStatistikCollection {

	/**
	 *
	 */
	private static final long serialVersionUID = -8237241425920707322L;

	@SuppressWarnings("all")
	public MatchStatistikMalCollection(Match match) {
	    super(match);

		for(Mal mal : match.getMal()) {
            MatchStatistikObjekt matchStatistikObjekt = getLedigtMatchStatistikObjekt(mal.getLag());
            if(matchStatistikObjekt == null) {
                matchStatistikObjekt = new MatchStatistikMalObjekt(getMatch());
                add(matchStatistikObjekt);
            }

            matchStatistikObjekt.setObjektForLag(mal,mal.getLag());
		}
	}
}
