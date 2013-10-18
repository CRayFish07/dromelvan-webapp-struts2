package org.dromelvan.struts2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.xmlbeans.XmlException;
import org.dromelvan.modell.DeMatch;
import org.dromelvan.modell.DeOmgang;
import org.dromelvan.modell.Lag;
import org.dromelvan.modell.Match;
import org.dromelvan.modell.SpelareMatchStatistik;
import org.dromelvan.struts2.util.OkandSpelare;
import org.dromelvan.struts2.util.SpelareMap;
import org.dromelvan.struts2.util.SpelareMatchStatistikMap;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

import dromelvan.tools.xml.RatingXml;
import dromelvan.tools.xml.RatingsDocument;
import dromelvan.tools.xml.RatingsXml;


/**
 * @author macke
 */
@Deprecated
public class UploadRatings extends UploadAction<RatingsDocument> {

    /**
	 *
	 */
	private static final long serialVersionUID = -514102625795117268L;
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
        RatingsXml ratingsXml = getDocument().getRatings();

        if(!getMatch().getHemmaLag().getNamn().equals(ratingsXml.getHemmaLag()) ||
           !getMatch().getBortaLag().getNamn().equals(ratingsXml.getBortaLag())) {
            addActionError("Filen innehåller statistik för en annan match (" +
                           ratingsXml.getHemmaLag() + " - " + ratingsXml.getBortaLag() + ").");
        } else {
            SpelareMap kandaSpelareMap = new SpelareMap();
    		for(SpelareMatchStatistik spelareMatchStatistik : getDAOFactory().getSpelareMatchStatistikDAO().findByMatch(getMatch())) {
    			kandaSpelareMap.add(spelareMatchStatistik.getSpelare());
    		}
            for(RatingXml ratingXml : ratingsXml.getRatingArray()) {
                if(kandaSpelareMap.get(ratingXml.getSpelare()) == null) {
                    OkandSpelare okandSpelare = new OkandSpelare();
                    okandSpelare.setNamn(ratingXml.getSpelare());
                    okandSpelare.setAlternativ(getDAOFactory().getSpelareDAO().findByNamnLike(ratingXml.getSpelare()));
                    addOkandSpelare(okandSpelare);
                }
            }
            if(!getOkandSpelareSet().isEmpty()) {
                addActionError("Okända spelare.");
            }
        }
    }

	public String doExecute() {
        if(!getMatch().getIsInlagd()) {
            addActionError("Matchstatistik för matchen är inte inlagd.");
            return ERROR;
        }

		SpelareMatchStatistikMap spelareMatchStatistikMap = new SpelareMatchStatistikMap();
		for(SpelareMatchStatistik spelareMatchStatistik : getDAOFactory().getSpelareMatchStatistikDAO().findByMatch(getMatch())) {
		    spelareMatchStatistik.setRating(0);
		    spelareMatchStatistik.setMom(false);
		    spelareMatchStatistik.setDeladMom(false);
		    spelareMatchStatistikMap.add(spelareMatchStatistik);
		}

		Map<Lag,Set<SpelareMatchStatistik>> moms = new HashMap<Lag,Set<SpelareMatchStatistik>>();
		moms.put(getMatch().getHemmaLag(),new HashSet<SpelareMatchStatistik>());
		moms.put(getMatch().getBortaLag(),new HashSet<SpelareMatchStatistik>());
		Map<Lag,Integer> maxRating = new HashMap<Lag,Integer>();
		maxRating.put(getMatch().getHemmaLag(),0);
		maxRating.put(getMatch().getBortaLag(),0);

		for(RatingXml ratingXml : getDocument().getRatings().getRatingArray()) {
			SpelareMatchStatistik spelareMatchStatistik = spelareMatchStatistikMap.get(ratingXml.getSpelare());
    		spelareMatchStatistik.setRating(ratingXml.getRating());

			if(spelareMatchStatistik.getRating() == maxRating.get(spelareMatchStatistik.getLag())) {
				moms.get(spelareMatchStatistik.getLag()).add(spelareMatchStatistik);
			} else if(spelareMatchStatistik.getRating() > maxRating.get(spelareMatchStatistik.getLag())) {
				maxRating.put(spelareMatchStatistik.getLag(),spelareMatchStatistik.getRating());
				moms.get(spelareMatchStatistik.getLag()).clear();
				moms.get(spelareMatchStatistik.getLag()).add(spelareMatchStatistik);
			}
		}

		setMoms(moms.get(getMatch().getHemmaLag()));
		setMoms(moms.get(getMatch().getBortaLag()));

		for(SpelareMatchStatistik spelareMatchStatistik : spelareMatchStatistikMap.values()) {
			spelareMatchStatistik.setPoang(spelareMatchStatistik.raknaPoang());
		}
		getDAOFactory().getSpelareMatchStatistikDAO().saveBatch(spelareMatchStatistikMap.values());

		DeOmgang deOmgang = getDAOFactory().getDeOmgangDAO().findByOmgang(getMatch().getOmgang());
		Collection<DeMatch> deMatcher = new ArrayList<DeMatch>(deOmgang.getDeMatcher());
		for(DeMatch deMatch : deMatcher) {
			deMatch.raknaPoang();
		}
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

    protected RatingsDocument parse(File file) throws IOException, XmlException {
	    return RatingsDocument.Factory.parse(file);
	}

    private void setMoms(Set<SpelareMatchStatistik> moms) {
    	for(SpelareMatchStatistik spelareMatchStatistik : moms) {
    		if(moms.size() == 1) {
    			spelareMatchStatistik.setMom(true);
    		} else if(moms.size() == 2) {
    			spelareMatchStatistik.setDeladMom(true);
    		}
    	}
    }
}
