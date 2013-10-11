package org.dromelvan.struts2.util;

import org.dromelvan.modell.Match;
import org.dromelvan.modell.SpelareMatchStatistik;


/**
 * Klass som gör det lite lättare att visa laguppställningar i matchstatistiksidan.
 * @author macke
 */
public class MatchStatistikSpelareCollection extends MatchStatistikCollection {

	/**
	 *
	 */
	private static final long serialVersionUID = 5468906060408285059L;
	public final static int STARTUPPSTALLNING = 2;
	public final static int RESERVER = 1;

	@SuppressWarnings("all")
	public MatchStatistikSpelareCollection(Match match,int deltog) {
        super(match);

        for(SpelareMatchStatistik spelareMatchStatistik : getMatch().getSpelareMatchStatistik()) {
            if(spelareMatchStatistik.getDeltog() == deltog) {
                MatchStatistikObjekt matchStatistikObjekt = getLedigtMatchStatistikObjekt(spelareMatchStatistik.getLag());
                if(matchStatistikObjekt == null) {
                    matchStatistikObjekt = new MatchStatistikSpelareObjekt(getMatch());
                    add(matchStatistikObjekt);
                }
                matchStatistikObjekt.setObjektForLag(spelareMatchStatistik,spelareMatchStatistik.getLag());
            }
        }
	}
}
