package org.dromelvan.struts2;

import org.dromelvan.modell.DeMatch;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

public class DromelvaMatchStatistik extends DromelvaActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 3025514314935578180L;
	private int matchId;
	private DeMatch deMatch;

	public String doExecute() {
        getSessionManager().beginTransaction();
        deMatch = getDAOFactory().getDeMatchDAO().findById(matchId);
		return SUCCESS;
	}

    @ConversionErrorFieldValidator(message = "Felaktigt format på matchId.")
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
    public int getMatchId() {
        return matchId;
    }

    public DeMatch getDeMatch() {
        return deMatch;
    }

    public int getSasongId() {
        return getDeMatch().getDeOmgang().getTavling().getSasong().getId();
    }

}
