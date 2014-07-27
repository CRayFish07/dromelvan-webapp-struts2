package org.dromelvan.struts2;

import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.Lag;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.Spelare.Position;
import org.dromelvan.modell.SpelareSasong;


public class InsertSpelare extends SpelareInputAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 3429872746725182480L;

	public String doExecute() {
		Lag lag = getDAOFactory().getLagDAO().findById(getLagId());
		Deltagare deltagare = getDAOFactory().getDeltagareDAO().findById(1);

		Spelare spelare = new Spelare();
		spelare.setFornamn(getFornamn().trim());
		spelare.setEfternamn(getEfternamn().trim());
        spelare.setLag(lag);
		spelare.setDeltagare(deltagare);
		spelare.setPosition(Position.values()[getPosition()]);
		spelare.setWhoScoredId(getWhoScoredId());
        spelare = getDAOFactory().getSpelareDAO().save(spelare);
        setSpelare(spelare);

		Sasong sasong = getDAOFactory().getSasongDAO().findById(getDefaultSasongId());
        if(sasong.getLag().contains(spelare.getLag())) {
			SpelareSasong spelareSasong = new SpelareSasong();
			spelareSasong.setSpelare(spelare);
			spelareSasong.setSasong(sasong);
			spelareSasong.setLag(spelare.getLag());
			spelareSasong.setDeltagare(spelare.getDeltagare());
			spelareSasong.setPosition(spelare.getPosition());
			getDAOFactory().getSpelareSasongDAO().save(spelareSasong);
        }

		getSessionManager().commitTransaction();
		getSessionManager().evictCache();
		return SUCCESS;
	}
}
