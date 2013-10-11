package org.dromelvan.struts2;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.dromelvan.jaxb.PLMatch;
import org.dromelvan.modell.Match;
import org.dromelvan.struts2.util.OkandSpelare;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

public class UploadJAXBMatchStatistik extends UploadJAXBAction<PLMatch> {

    /**
     * 
     */
    private static final long serialVersionUID = 8540927196122972506L;
    private int matchId;
    private Match match;
    private Set<OkandSpelare> okandSpelareSet = new HashSet<OkandSpelare>();    

    @ConversionErrorFieldValidator(message = "Felaktigt format på matchId.")
    public int getMatchId() {
        return matchId;
    }
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public Match getMatch() {
        return match;
    }
    protected void setMatch(Match match) {
        this.match = match;
    }

    public Set<OkandSpelare> getOkandSpelareSet() {
        return okandSpelareSet;
    }
    protected void addOkandSpelare(OkandSpelare okandSpelare) {
        getOkandSpelareSet().add(okandSpelare);
    }
    
    public String input() {
        clearWorkFlowObject();
        getSessionManager().beginTransaction();
        setMatch(getDAOFactory().getMatchDAO().findById(getMatchId()));

        setWorkFlowObject(getMatch());
        return INPUT;
    }
    
    public boolean validateWorkFlowObject() {
        try {
            setMatch((Match)getWorkFlowObject());
        } catch(ClassCastException e) {}
        return getMatch() != null;
    }
    
    public String doExecute() {
        return SUCCESS;
    }

    @Override
    protected JAXBContext getJAXBContext() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(PLMatch.class);
        return jaxbContext;
    }
    
    @Override
    protected void validateJAXBObject(PLMatch jaxbObject) {
        
    }
}
