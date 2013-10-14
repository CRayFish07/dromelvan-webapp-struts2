package org.dromelvan.struts2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.dromelvan.jaxb.Card;
import org.dromelvan.jaxb.CardType;
import org.dromelvan.jaxb.Goal;
import org.dromelvan.jaxb.PLMatch;
import org.dromelvan.jaxb.PLTeam;
import org.dromelvan.jaxb.PlayerMatchStatistics;
import org.dromelvan.jaxb.Substitution;
import org.dromelvan.jaxb.util.JAXBUtil;
import org.dromelvan.jaxb.util.PLMatchJAXBUtil;
import org.dromelvan.modell.DeMatch;
import org.dromelvan.modell.DeOmgang;
import org.dromelvan.modell.Lag;
import org.dromelvan.modell.Mal;
import org.dromelvan.modell.Match;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.SpelareMatchStatistik;
import org.dromelvan.modell.SpelareSasong;
import org.dromelvan.modell.persistence.SpelareDAO;
import org.dromelvan.struts2.util.OkandSpelare;
import org.dromelvan.struts2.util.SpelareMap;
import org.dromelvan.struts2.util.SpelareMatchStatistikMap;
import org.xml.sax.SAXException;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

public class UploadJAXBMatchStatistik extends UploadJAXBAction<PLMatch> {

    /**
     *
     */
    private static final long serialVersionUID = 8540927196122972506L;
    private int matchId;
    private Match match;

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
        if(getMatch().getSpelareMatchStatistik().isEmpty()) {
            addActionError("Statistik för omgången har inte lagts in. Detta måste " +
                           "göras före matchstatistik kan laddas upp.");
            return ERROR;
        } else if(getMatch().getIsInlagd()) {
            addActionError("Statistik för matchen är redan inlagd.");
            return ERROR;
        }

        SpelareMap spelareMap = new SpelareMap(getDAOFactory().getSpelareDAO().findByLag(getMatch().getHemmaLag()));
        spelareMap.putAll(getDAOFactory().getSpelareDAO().findByLag(getMatch().getBortaLag()));

        SpelareMatchStatistikMap spelareMatchStatistikMap = new SpelareMatchStatistikMap();
        for(SpelareMatchStatistik spelareMatchStatistik : getDAOFactory().getSpelareMatchStatistikDAO().findByMatch(getMatch())) {
            // Sätt deltog ej som default på alla spelare först så räcker det med att hantera de
            // som verkligen finns i statistikfilen för att få rätt status på allt
            spelareMatchStatistik.setDeltog(0);
            spelareMatchStatistikMap.add(spelareMatchStatistik);
        }

        Sasong sasong = getMatch().getOmgang().getTavling().getSasong();
        PLMatch plMatch = getUploadObject();

        List<Mal> malList = updatePLTeam(plMatch.getHomeTeam(), sasong, getMatch().getHemmaLag(), spelareMap, spelareMatchStatistikMap);
        malList.addAll(updatePLTeam(plMatch.getAwayTeam(), sasong, getMatch().getBortaLag(), spelareMap, spelareMatchStatistikMap));

        DeOmgang deOmgang = getDAOFactory().getDeOmgangDAO().findByOmgang(getMatch().getOmgang());
        Collection<DeMatch> deMatcher = new ArrayList<DeMatch>(deOmgang.getDeMatcher());
        for(DeMatch deMatch : deMatcher) {
            deMatch.raknaPoang();
        }

        getDAOFactory().getMalDAO().saveBatch(malList);
        getDAOFactory().getSpelareMatchStatistikDAO().saveBatch(spelareMatchStatistikMap.values());
        getDAOFactory().getDeMatchDAO().saveBatch(deMatcher);

        getSessionManager().commitTransaction();
        getSessionManager().evictCache();
        clearWorkFlowObject();

        return SUCCESS;
    }

    private List<Mal> updatePLTeam(PLTeam team, Sasong sasong, Lag lag, SpelareMap spelareMap, SpelareMatchStatistikMap spelareMatchStatistikMap) {
        Set<SpelareMatchStatistik> moms = new HashSet<SpelareMatchStatistik>();
        int maxRating = 0;

        for(PlayerMatchStatistics playerMatchStatistics : team.getPlayers().getPlayerMatchStatistics()) {
            SpelareMatchStatistik spelareMatchStatistik = spelareMatchStatistikMap.get(playerMatchStatistics.getPlayer());
            if(spelareMatchStatistik == null) {
                Spelare spelare = spelareMap.get(playerMatchStatistics.getPlayer());
                SpelareSasong spelareSasong = getDAOFactory().getSpelareSasongDAO().findBySpelareOchSasong(spelare,sasong);

                spelareMatchStatistik = new SpelareMatchStatistik();
                spelareMatchStatistik.setSpelare(spelare);
                spelareMatchStatistik.setMatch(getMatch());
                // Om en match flyttats framåt skall en spelare som bytt lag inte kunna spela två
                // matcher för en deltagare en omgång. Det är alltid matchen som spelaren läggs in
                // för då omgången spikas som skall räknas. Därför sätter vi deltagare till ingen
                spelareMatchStatistik.setDeltagare(getDAOFactory().getDeltagareDAO().findById(1));
                spelareMatchStatistik.setPosition(spelareSasong.getPosition());
                spelareMatchStatistikMap.add(spelareMatchStatistik);
            }
            spelareMatchStatistik.setDeltog(playerMatchStatistics.getParticipated());
            spelareMatchStatistik.setAssist(playerMatchStatistics.getAssists());
            spelareMatchStatistik.setRating(playerMatchStatistics.getRating());
            // Det är inte troligt men dock möjligt att en spelare hörde till ett lag då matchens
            // omgång spelades, och sedan hör till det andra laget då en framflyttad match spelas.
            // Därför sätter vi lag från xmlfilen här också
            spelareMatchStatistik.setLag(lag);

            if(spelareMatchStatistik.getRating() == maxRating) {
                moms.add(spelareMatchStatistik);
            } else if(spelareMatchStatistik.getRating() > maxRating) {
                maxRating = spelareMatchStatistik.getRating();
                moms.clear();
                moms.add(spelareMatchStatistik);
            }
        }

        for(SpelareMatchStatistik spelareMatchStatistik : moms) {
            if(moms.size() == 1) {
                spelareMatchStatistik.setMom(true);
            } else if(moms.size() == 2) {
                spelareMatchStatistik.setDeladMom(true);
            }
        }

        for(Card card : team.getCards().getCard()) {
            SpelareMatchStatistik spelareMatchStatistik = spelareMatchStatistikMap.get(card.getPlayer());
            if(card.getType() == CardType.YELLOW) {
                spelareMatchStatistik.setGultKortTid(card.getTime());
            } else {
                spelareMatchStatistik.setRottKortTid(card.getTime());
            }
        }

        for(Substitution substitution : team.getSubstitutions().getSubstitution()) {
            spelareMatchStatistikMap.get(substitution.getPlayerOut()).setUtbyttTid(substitution.getTime());
            spelareMatchStatistikMap.get(substitution.getPlayerIn()).setInbyttTid(substitution.getTime());
        }

        List<Mal> malList = new ArrayList<Mal>();
        for(Goal goal : team.getGoals().getGoal()) {
            Mal mal = new Mal();
            SpelareMatchStatistik spelareMatchStatistik = spelareMatchStatistikMap.get(goal.getPlayer());
            mal.setSpelare(spelareMatchStatistik.getSpelare());
            mal.setMatch(getMatch());
            mal.setStraffspark(goal.isPenalty());
            mal.setSjalvmal(goal.isOwnGoal());
            mal.setTid(goal.getTime());
            mal.setLag(lag);

            if(!mal.isSjalvmal()) {
                spelareMatchStatistik.setMal(spelareMatchStatistik.getMal() + 1);
            } else {
                spelareMatchStatistik.setSjalvmal(spelareMatchStatistik.getSjalvmal() + 1);
            }
            malList.add(mal);
            // Måste göra detta för att räkna poäng rätt för försvarare
            if(mal.getLag().equals(getMatch().getHemmaLag())) {
                getMatch().setAntalMalHemmaLag(getMatch().getAntalMalHemmaLag() + 1);
            } else {
                getMatch().setAntalMalBortaLag(getMatch().getAntalMalBortaLag() + 1);
            }
        }

        for(SpelareMatchStatistik spelareMatchStatistik : spelareMatchStatistikMap.values()) {
            // Kan få lazy instantiation exception i raknaPoang av någon anledning jag inte förstår nu
            // för vissa spelare om vi inte sätter matchen så här
            spelareMatchStatistik.setMatch(getMatch());
            spelareMatchStatistik.setPoang(spelareMatchStatistik.raknaPoang());
        }

        return malList;
    }

    @Override
    protected JAXBUtil<PLMatch> getJAXBUtil() throws SAXException, JAXBException {
        return new PLMatchJAXBUtil();
    }

    @Override
    protected void validateJAXBObject(PLMatch plMatch) {
        getSessionManager().beginTransaction();
        setMatch(getDAOFactory().getMatchDAO().findById(getMatch().getId()));

        if(!getMatch().getHemmaLag().getNamn().equalsIgnoreCase(plMatch.getHomeTeam().getName()) ||
           !getMatch().getBortaLag().getNamn().equalsIgnoreCase(plMatch.getAwayTeam().getName())) {
            addActionError("Filen innehåller statistik för en annan match (" +
                           plMatch.getHomeTeam().getName() + " - " + plMatch.getAwayTeam().getName() + ").");
        } else {
            validatePLTeam(plMatch.getHomeTeam());
            validatePLTeam(plMatch.getAwayTeam());

            if(!getOkandSpelareSet().isEmpty()) {
                addActionError("Okända spelare.");
            }
        }
    }

    protected void validatePLTeam(PLTeam plTeam) {
        SpelareDAO spelareDAO = getDAOFactory().getSpelareDAO();
        SpelareMap kandaSpelareMap = new SpelareMap(spelareDAO.findByLag(getMatch().getHemmaLag()));
        kandaSpelareMap.putAll(spelareDAO.findByLag(getMatch().getBortaLag()));

        for(PlayerMatchStatistics playerMatchStatistics : plTeam.getPlayers().getPlayerMatchStatistics()) {
            if(kandaSpelareMap.get(playerMatchStatistics.getPlayer()) == null) {
                OkandSpelare okandSpelare = new OkandSpelare();
                okandSpelare.setNamn(playerMatchStatistics.getPlayer());
                okandSpelare.setLag(plTeam.getName());
                okandSpelare.setAlternativ(spelareDAO.findByNamnLike(playerMatchStatistics.getPlayer()));
                addOkandSpelare(okandSpelare);
            }
        }

        for(Goal goal : plTeam.getGoals().getGoal()) {
            if(kandaSpelareMap.get(goal.getPlayer()) == null) {
                addActionError("Okänd målgörare: " + goal.getPlayer());
            }
        }

        for(Card card : plTeam.getCards().getCard()) {
            if(kandaSpelareMap.get(card.getPlayer()) == null) {
                addActionError("Okänd varnad/utvisad spelare: " + card.getPlayer());
            }
        }

        for(Substitution substitution : plTeam.getSubstitutions().getSubstitution()) {
            if(kandaSpelareMap.get(substitution.getPlayerOut()) == null) {
                addActionError("Okänd utbytt spelare: " + substitution.getPlayerOut());
            }
            if(kandaSpelareMap.get(substitution.getPlayerIn()) == null) {
                addActionError("Okänd inbytt spelare: " + substitution.getPlayerIn());
            }
        }
    }
}
