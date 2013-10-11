package org.dromelvan.struts2;

import org.dromelvan.modell.Match;
import org.dromelvan.struts2.util.MatchStatistikMalCollection;
import org.dromelvan.struts2.util.MatchStatistikSpelareCollection;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;


public class MatchStatistik extends DromelvaActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = -6439318363272481997L;
	private int matchId;
	private Match match;

	private MatchStatistikMalCollection matchStatistikMalCollection;
	private MatchStatistikSpelareCollection startuppstallning;
	private MatchStatistikSpelareCollection reserver;

	public String doExecute() {
        getSessionManager().beginTransaction();
        match = getDAOFactory().getMatchDAO().findById(matchId);
        matchStatistikMalCollection = new MatchStatistikMalCollection(match);
        startuppstallning = new MatchStatistikSpelareCollection(match, MatchStatistikSpelareCollection.STARTUPPSTALLNING);
        reserver = new MatchStatistikSpelareCollection(match, MatchStatistikSpelareCollection.RESERVER);
		return SUCCESS;
	}

    @ConversionErrorFieldValidator(message = "Felaktigt format på matchId.")
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
    public int getMatchId() {
        return matchId;
    }

    public Match getMatch() {
        return match;
    }

    public int getSasongId() {
        return getMatch().getOmgang().getTavling().getSasong().getId();
    }

    public MatchStatistikMalCollection getMal() {
    	return matchStatistikMalCollection;
    }

    public MatchStatistikSpelareCollection getStartuppstallning() {
    	return startuppstallning;
    }

    public MatchStatistikSpelareCollection getReserver() {
    	return reserver;
    }
}
