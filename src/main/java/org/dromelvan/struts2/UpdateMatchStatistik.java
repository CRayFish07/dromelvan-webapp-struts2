package org.dromelvan.struts2;

import java.util.Set;

import org.dromelvan.modell.Mal;
import org.dromelvan.modell.SpelareMatchStatistik;
import org.dromelvan.modell.persistence.MalDAO;




/**
 * @author macke
 */
public class UpdateMatchStatistik extends UploadMatchStatistik {

	/**
	 *
	 */
	private static final long serialVersionUID = 7745727374587561336L;

	public String doExecute() {
		if(!getMatch().getIsInlagd()) {
            addActionError("Statistik för matchen är inte inlagd.");
            return ERROR;
        }

		MalDAO malDAO = getDAOFactory().getMalDAO();
		for(Mal mal : getMatch().getMal()) {
			malDAO.delete(mal);
		}

		Set<SpelareMatchStatistik> spelareMatchStatistikSet = getMatch().getSpelareMatchStatistik();
		for(SpelareMatchStatistik spelareMatchStatistik : spelareMatchStatistikSet) {
			spelareMatchStatistik.setDeltog(-1);
			spelareMatchStatistik.setMal(0);
			spelareMatchStatistik.setSjalvmal(0);
			spelareMatchStatistik.setGultKortTid(0);
			spelareMatchStatistik.setRottKortTid(0);
			spelareMatchStatistik.setInbyttTid(0);
			spelareMatchStatistik.setUtbyttTid(0);
			spelareMatchStatistik.setMom(false);
			spelareMatchStatistik.setDeladMom(false);
			spelareMatchStatistik.setRating(0);
			spelareMatchStatistik.setPoang(0);
		}
		getDAOFactory().getSpelareMatchStatistikDAO().saveBatch(spelareMatchStatistikSet);

		// Måste sätta matchen på nytt för att fortsätta eftersom saveBatch
		// gör flush på sessionen
		setMatch(getDAOFactory().getMatchDAO().findById(getMatch().getId()));
		getMatch().setIsInlagd(false);
		getMatch().setAntalMalHemmaLag(0);
		getMatch().setAntalMalBortaLag(0);
		return super.doExecute();
	}

}
