package org.dromelvan.struts2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.xmlbeans.XmlException;
import org.dromelvan.modell.DeMatch;
import org.dromelvan.modell.DeOmgang;
import org.dromelvan.modell.Mal;
import org.dromelvan.modell.Match;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.SpelareMatchStatistik;
import org.dromelvan.modell.SpelareSasong;
import org.dromelvan.modell.persistence.SpelareDAO;
import org.dromelvan.struts2.util.LagMap;
import org.dromelvan.struts2.util.OkandSpelare;
import org.dromelvan.struts2.util.SpelareMap;
import org.dromelvan.struts2.util.SpelareMatchStatistikMap;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

import dromelvan.tools.xml.ByteXml;
import dromelvan.tools.xml.KortTypEnumeration;
import dromelvan.tools.xml.KortXml;
import dromelvan.tools.xml.MalXml;
import dromelvan.tools.xml.MatchDocument;
import dromelvan.tools.xml.MatchXml;
import dromelvan.tools.xml.SpelareXml;


/**
 * @author macke
 */
public class UploadMatchStatistik extends UploadAction<MatchDocument> {

    /**
	 *
	 */
	private static final long serialVersionUID = -5991677375821486359L;
	private int matchId;
    private Match match;
    private Set<OkandSpelare> okandSpelareSet = new HashSet<OkandSpelare>();

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

    protected void validateDocumentContent() {
        getSessionManager().beginTransaction();
        setMatch(getDAOFactory().getMatchDAO().findById(getMatch().getId()));
        MatchXml matchXml = getDocument().getMatch();

        if(!getMatch().getHemmaLag().getNamn().equals(matchXml.getHemmaLag()) ||
           !getMatch().getBortaLag().getNamn().equals(matchXml.getBortaLag())) {
            addActionError("Filen innehåller statistik för en annan match (" +
                           matchXml.getHemmaLag() + " - " + matchXml.getBortaLag() + ").");
        } else {
            SpelareDAO spelareDAO = getDAOFactory().getSpelareDAO();
            SpelareMap kandaSpelareMap = new SpelareMap(spelareDAO.findByLag(getMatch().getHemmaLag()));
            kandaSpelareMap.putAll(spelareDAO.findByLag(getMatch().getBortaLag()));

            for(SpelareXml spelareXml : matchXml.getSpelareArray()) {
                if(kandaSpelareMap.get(spelareXml.getNamn()) == null) {
                    OkandSpelare okandSpelare = new OkandSpelare();
                    okandSpelare.setNamn(spelareXml.getNamn());
                    okandSpelare.setLag(spelareXml.getLag());
                    okandSpelare.setAlternativ(spelareDAO.findByNamnLike(spelareXml.getNamn()));
                    addOkandSpelare(okandSpelare);
                }
            }
            if(!getOkandSpelareSet().isEmpty()) {
                addActionError("Okända spelare.");
            } else {
            	for(MalXml malXml : matchXml.getMalArray()) {
            		if(kandaSpelareMap.get(malXml.getSpelare()) == null) {
            			addActionError("Okänd målgörare: " + malXml.getSpelare());
            		}
            	}
            	for(ByteXml byteXml : matchXml.getByteArray()) {
            		if(kandaSpelareMap.get(byteXml.getUtbyttSpelare()) == null) {
            			addActionError("Okänd utbytt spelare: " + byteXml.getUtbyttSpelare());
            		}
            		if(kandaSpelareMap.get(byteXml.getInbyttSpelare()) == null) {
            			addActionError("Okänd inbytt spelare: " + byteXml.getInbyttSpelare());
            		}
            	}
            	for(KortXml kortXml : matchXml.getKortArray()) {
            		if(kandaSpelareMap.get(kortXml.getSpelare()) == null) {
            			addActionError("Okänd varnad/utvisad spelare: " + kortXml.getSpelare());
            		}
            	}
            }
        }
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

	    LagMap lagMap = new LagMap();
	    lagMap.add(getMatch().getHemmaLag());
	    lagMap.add(getMatch().getBortaLag());
        SpelareMap spelareMap = new SpelareMap(getDAOFactory().getSpelareDAO().findByLag(getMatch().getHemmaLag()));
        spelareMap.putAll(getDAOFactory().getSpelareDAO().findByLag(getMatch().getBortaLag()));

		SpelareMatchStatistikMap spelareMatchStatistikMap = new SpelareMatchStatistikMap();
		for(SpelareMatchStatistik spelareMatchStatistik : getDAOFactory().getSpelareMatchStatistikDAO().findByMatch(getMatch())) {
		    // Sätt deltog ej som default på alla spelare först så räcker det med att hantera de
		    // som verkligen finns i statistikfilen för att få rätt status på allt
		    spelareMatchStatistik.setDeltog(0);
		    spelareMatchStatistikMap.add(spelareMatchStatistik);
		}

		MatchXml matchXml = getDocument().getMatch();
		Sasong sasong = getMatch().getOmgang().getTavling().getSasong();

		for(SpelareXml spelareXml : matchXml.getSpelareArray()) {
		    SpelareMatchStatistik spelareMatchStatistik = spelareMatchStatistikMap.get(spelareXml.getNamn());
		    if(spelareMatchStatistik == null) {
		        Spelare spelare = spelareMap.get(spelareXml.getNamn());
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
		    spelareMatchStatistik.setDeltog(spelareXml.getDeltog());
		    spelareMatchStatistik.setAssist(spelareXml.getAssist());
		    // Det är inte troligt men dock möjligt att en spelare hörde till ett lag då matchens
		    // omgång spelades, och sedan hör till det andra laget då en framflyttad match spelas.
		    // Därför sätter vi lag från xmlfilen här också
		    spelareMatchStatistik.setLag(lagMap.get(spelareXml.getLag()));
		}

        for(KortXml kortXml : matchXml.getKortArray()) {
            SpelareMatchStatistik spelareMatchStatistik = spelareMatchStatistikMap.get(kortXml.getSpelare());
            if(kortXml.getTyp() == KortTypEnumeration.GULT) {
                spelareMatchStatistik.setGultKortTid(kortXml.getTid());
            } else {
                spelareMatchStatistik.setRottKortTid(kortXml.getTid());
            }
        }

        for(ByteXml byteXml : matchXml.getByteArray()) {
            spelareMatchStatistikMap.get(byteXml.getUtbyttSpelare()).setUtbyttTid(byteXml.getTid());
            spelareMatchStatistikMap.get(byteXml.getInbyttSpelare()).setInbyttTid(byteXml.getTid());
        }

		List<Mal> malList = new ArrayList<Mal>();
		for(MalXml malXml : matchXml.getMalArray()) {
		    Mal mal = new Mal();
		    SpelareMatchStatistik spelareMatchStatistik = spelareMatchStatistikMap.get(malXml.getSpelare());
		    mal.setSpelare(spelareMatchStatistik.getSpelare());
		    mal.setMatch(getMatch());
		    mal.setStraffspark(malXml.getStraffspark());
		    mal.setSjalvmal(malXml.getSjalvmal());
		    mal.setTid(malXml.getTid());

		    if(!mal.isSjalvmal()) {
		        mal.setLag(spelareMatchStatistik.getLag());
		        spelareMatchStatistik.setMal(spelareMatchStatistik.getMal() + 1);
		    } else {
		        spelareMatchStatistik.setSjalvmal(spelareMatchStatistik.getSjalvmal() + 1);
		        if(getMatch().getHemmaLag().equals(spelareMatchStatistik.getLag())) {
		            mal.setLag(getMatch().getBortaLag());
		        } else {
		            mal.setLag(getMatch().getHemmaLag());
		        }
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

    protected MatchDocument parse(File file) throws IOException, XmlException {
	    return MatchDocument.Factory.parse(file);
	}
}
