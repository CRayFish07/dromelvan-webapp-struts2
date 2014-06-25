package org.dromelvan.struts2.test;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.Match;
import org.dromelvan.modell.persistence.DAOFactory;
import org.dromelvan.modell.persistence.MatchDAO;
import org.dromelvan.modell.persistence.SessionManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Dump {

    @Test
    public void test() {
        SessionManager.getInstance().beginTransaction();

        MatchDAO matchDAO = DAOFactory.getInstance().getMatchDAO();
        List<Match> matches = matchDAO.findAll();

        Collections.sort(matches);

        for(Match match : matches) {
            System.out.println(String.format(" [%d, %d, %d, %d, %d, 2, \"%s 17:00\"]",
                    match.getHemmaLag().getId(),
                    match.getBortaLag().getId(),
                    match.getAntalMalHemmaLag(),
                    match.getAntalMalBortaLag(),
                    match.getOmgang().getId(),
                    match.getDatum()));
        }
    }
}
