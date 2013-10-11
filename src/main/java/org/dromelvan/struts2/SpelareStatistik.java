package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.SpelareMatchStatistik;
import org.dromelvan.modell.SpelareSasong;
import org.dromelvan.modell.SpelareSasongStatistik;
import org.dromelvan.struts2.util.DummySpelareSasongStatistik;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;


public class SpelareStatistik extends DromelvaActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 651065535486525241L;
	private int spelareId;
	private int sasongId;
	private Sasong sasong;
	private SpelareSasongStatistik spelareSasongStatistik;
	private List<SpelareMatchStatistik> spelareMatchStatistik;
	private List<Sasong> sasonger;

	public String doExecute() {
        getSessionManager().beginTransaction();
        Spelare spelare = getDAOFactory().getSpelareDAO().findById(getSpelareId());
        sasong = getDAOFactory().getSasongDAO().findById(getSasongId());

        spelareSasongStatistik = sasong.getSpelareSasongStatistik(spelare);

        if(spelareSasongStatistik != null) {
        	spelareMatchStatistik = getDAOFactory().getSpelareMatchStatistikDAO().findBySpelareOchSasong(spelare,sasong);
            Collections.sort(spelareMatchStatistik);
        } else {
            // Om det inte finns någon statistik för spelaren den här säsongen så pusslar vi ihop
            // ett dummyobjekt så vi kan visa headern och länkar till andra säsonger
        	SpelareSasong spelareSasong = getDAOFactory().getSpelareSasongDAO().findBySpelareOchSasong(spelare, sasong);
        	if(spelareSasong != null) {
        		spelareSasongStatistik = new DummySpelareSasongStatistik(spelareSasong);
        	} else {
                spelareSasongStatistik = new DummySpelareSasongStatistik(spelare);
        	}
            spelareSasongStatistik.setSasong(sasong);
        }

        sasonger = getDAOFactory().getSasongDAO().findAll();
        Collections.sort(sasonger);
        return SUCCESS;
	}

	@ConversionErrorFieldValidator(message = "Felaktigt format på spelareId.")
	public int getSpelareId() {
		return spelareId;
	}
	public void setSpelareId(int spelareId) {
		this.spelareId = spelareId;
	}

	@ConversionErrorFieldValidator(message = "Felaktigt format på sasongId.")
	public int getSasongId() {
		return sasongId != 0 ? sasongId : getDefaultSasongId();
	}
	public void setSasongId(int sasongId) {
		this.sasongId = sasongId;
	}

	public Sasong getSasong() {
		return sasong;
	}

	public List<Sasong> getSasonger() {
		return sasonger;
	}

	public SpelareSasongStatistik getSpelareSasongStatistik() {
		return spelareSasongStatistik;
	}

	public List<SpelareMatchStatistik> getSpelareMatchStatistik() {
		return spelareMatchStatistik;
	}
}