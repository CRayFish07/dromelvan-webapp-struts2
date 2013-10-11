package org.dromelvan.struts2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dromelvan.modell.DeByte;
import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.SpelareSasong;
import org.dromelvan.modell.SpelareSasongStatistik;
import org.dromelvan.struts2.util.DummySpelareSasongStatistik;
import org.dromelvan.struts2.util.SpelareTransfer;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;


public class SpelareTransfers extends DromelvaActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = -2797369708560703016L;
	private int spelareId;
	private SpelareSasongStatistik spelareSasongStatistik;
	private List<SpelareTransfer> spelareTransfers;
	private List<Sasong> sasonger;

	public String doExecute() {
        getSessionManager().beginTransaction();
        Spelare spelare = getDAOFactory().getSpelareDAO().findById(getSpelareId());
        Sasong sasong = getDAOFactory().getSasongDAO().findById(getDefaultSasongId());

        spelareSasongStatistik = sasong.getSpelareSasongStatistik(spelare);
        if(spelareSasongStatistik == null) {
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

        Map<DeByteOmgang,SpelareTransfer> spelareTransferMap = new HashMap<DeByteOmgang,SpelareTransfer>();
        Deltagare ingen = getDAOFactory().getDeltagareDAO().findById(1);

        for(DeByte deByte : getDAOFactory().getDeByteDAO().findBySpelare(spelare)) {
        	SpelareTransfer spelareTransfer = spelareTransferMap.get(deByte.getDeByteOmgang());
        	if(spelareTransfer == null) {
        		spelareTransfer = new SpelareTransfer(spelare,deByte.getDeByteOmgang());
        		spelareTransfer.setDeltagareKopt(ingen);
        		spelareTransfer.setDeltagareSald(ingen);
        		spelareTransferMap.put(deByte.getDeByteOmgang(),spelareTransfer);
        	}

        	if(deByte.getKoptSpelare().equals(spelare)) {
        		spelareTransfer.setDeltagareKopt(deByte.getDeltagare());
        		spelareTransfer.setPris(deByte.getPris());
        	} else {
        		spelareTransfer.setDeltagareSald(deByte.getDeltagare());
        	}
        }

        spelareTransfers = new ArrayList<SpelareTransfer>(spelareTransferMap.values());
        Collections.sort(spelareTransfers);

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

	public List<Sasong> getSasonger() {
		return sasonger;
	}

	public SpelareSasongStatistik getSpelareSasongStatistik() {
		return spelareSasongStatistik;
	}

	public List<SpelareTransfer> getSpelareTransfers() {
		return spelareTransfers;
	}


}