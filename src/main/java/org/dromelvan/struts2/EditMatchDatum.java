package org.dromelvan.struts2;

import java.text.ParseException;

import org.dromelvan.modell.Match;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;



public class EditMatchDatum extends DromelvaAdminAction {

    /**
	 *
	 */
	private static final long serialVersionUID = 4120444489593832742L;

	private Match match;

    private int matchId;
    private String datum;

    public String input() {
        clearWorkFlowObject();

        getSessionManager().beginTransaction();
        setMatch(getDAOFactory().getMatchDAO().findById(getMatchId()));
        setDatum(getMatch().getDatumStr());

        setWorkFlowObject(getMatch());
        return INPUT;
    }

	public String doExecute() {
        getSessionManager().beginTransaction();

        Match match = getDAOFactory().getMatchDAO().findById(getMatch().getId());
        try {
        	match.setDatum(Match.datumFormat.parse(datum));
        } catch(ParseException e) {}
        match = getDAOFactory().getMatchDAO().save(match);
        setMatch(match);

        getSessionManager().commitTransaction();
        clearWorkFlowObject();
		return SUCCESS;
	}

    public boolean validateWorkFlowObject() {
    	try {
    		setMatch((Match)getWorkFlowObject());
    	} catch(ClassCastException e) {}
        return getMatch() != null;
    }

    public Match getMatch() {
        return match;
    }
    private void setMatch(Match match) {
        this.match = match;
    }

    @ConversionErrorFieldValidator(message = "Felaktigt format på matchId.")
	public int getMatchId() {
        return matchId;
    }
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    @RequiredFieldValidator(message = "Datum saknas", shortCircuit = true)
    public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}

}
