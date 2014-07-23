package org.dromelvan.struts2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.dromelvan.jaxb.PLMatch;
import org.dromelvan.jaxb.PLTeam;
import org.dromelvan.jaxb.PlayerMatchStatistics;
import org.dromelvan.modell.DeMatch;
import org.dromelvan.modell.DeOmgang;
import org.dromelvan.modell.SpelareMatchStatistik;
import org.dromelvan.modell.persistence.SpelareDAO;
import org.dromelvan.struts2.util.OkandSpelare;
import org.dromelvan.struts2.util.SpelareMap;
import org.dromelvan.struts2.util.SpelareMatchStatistikMap;

import com.ibm.icu.math.BigDecimal;

public class UploadJAXBRatings extends UploadJAXBMatchStatistik {

    /**
     *
     */
    private static final long serialVersionUID = -1180470359004356206L;

    public String doExecute() {
        if(getMatch().getSpelareMatchStatistik().isEmpty()) {
            addActionError("Statistik för omgången har inte lagts in. Detta måste " +
                           "göras före matchstatistik kan laddas upp.");
            return ERROR;
        } else if(!getMatch().getIsInlagd()) {
            addActionError("Statistik för matchen måste läggas in före ratings kan laddas up.");
            return ERROR;
        }

        SpelareMap spelareMap = new SpelareMap(getDAOFactory().getSpelareDAO().findByLag(getMatch().getHemmaLag()));
        spelareMap.putAll(getDAOFactory().getSpelareDAO().findByLag(getMatch().getBortaLag()));

        SpelareMatchStatistikMap spelareMatchStatistikMap = new SpelareMatchStatistikMap();
        for(SpelareMatchStatistik spelareMatchStatistik : getDAOFactory().getSpelareMatchStatistikDAO().findByMatch(getMatch())) {
            spelareMatchStatistikMap.add(spelareMatchStatistik);
        }

        PLMatch plMatch = getUploadObject();

        updatePLTeam(plMatch.getHomeTeam(), spelareMatchStatistikMap);
        updatePLTeam(plMatch.getAwayTeam(), spelareMatchStatistikMap);

        DeOmgang deOmgang = getDAOFactory().getDeOmgangDAO().findByOmgang(getMatch().getOmgang());
        Collection<DeMatch> deMatcher = new ArrayList<DeMatch>(deOmgang.getDeMatcher());
        for(DeMatch deMatch : deMatcher) {
            deMatch.raknaPoang();
        }

        getDAOFactory().getSpelareMatchStatistikDAO().saveBatch(spelareMatchStatistikMap.values());
        getDAOFactory().getDeMatchDAO().saveBatch(deMatcher);

        getSessionManager().commitTransaction();
        getSessionManager().evictCache();
        clearWorkFlowObject();

        return SUCCESS;
    }

    private void updatePLTeam(PLTeam team, SpelareMatchStatistikMap spelareMatchStatistikMap) {
        Set<SpelareMatchStatistik> moms = new HashSet<SpelareMatchStatistik>();
        int maxRating = 0;

        for(PlayerMatchStatistics playerMatchStatistics : team.getPlayers().getPlayerMatchStatistics()) {
            SpelareMatchStatistik spelareMatchStatistik = spelareMatchStatistikMap.get(playerMatchStatistics.getPlayer());
            BigDecimal bigDecimal = BigDecimal.valueOf(playerMatchStatistics.getRating());
            bigDecimal = bigDecimal.movePointLeft(2);
            bigDecimal = bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP);
            spelareMatchStatistik.setRating(bigDecimal.intValue());
            spelareMatchStatistik.setWhoScoredRating(playerMatchStatistics.getRating());
            spelareMatchStatistik.setMom(false);
            spelareMatchStatistik.setDeladMom(false);

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

        for(SpelareMatchStatistik spelareMatchStatistik : spelareMatchStatistikMap.values()) {
            // Kan få lazy instantiation exception i raknaPoang av någon anledning jag inte förstår nu
            // för vissa spelare om vi inte sätter matchen så här
            spelareMatchStatistik.setMatch(getMatch());
            spelareMatchStatistik.setPoang(spelareMatchStatistik.raknaPoang());
        }
    }

    @Override
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
    }
}
