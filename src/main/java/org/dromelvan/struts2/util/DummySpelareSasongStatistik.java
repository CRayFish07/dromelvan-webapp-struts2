package org.dromelvan.struts2.util;

import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.SpelareSasong;
import org.dromelvan.modell.SpelareSasongStatistik;


/**
 * @author macke
 */
public class DummySpelareSasongStatistik extends SpelareSasongStatistik implements Dummy {

    /**
	 *
	 */
	private static final long serialVersionUID = -7433968958197607551L;

	public DummySpelareSasongStatistik(Spelare spelare) {
        setSpelare(spelare);
        setLag(spelare.getLag());
        setDeltagare(spelare.getDeltagare());
        setPosition(spelare.getPosition());
    }

	public DummySpelareSasongStatistik(SpelareSasong spelareSasong) {
    	this(spelareSasong.getSpelare());
    	setSasong(spelareSasong.getSasong());
    	setPris(spelareSasong.getPris());
    }

	public boolean isDummy() {
		return true;
	}
}
