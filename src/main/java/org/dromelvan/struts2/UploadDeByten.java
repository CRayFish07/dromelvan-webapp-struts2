package org.dromelvan.struts2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.xmlbeans.XmlException;
import org.dromelvan.modell.DeByte;
import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.SpelareSasong;
import org.dromelvan.modell.TillgangligSpelare;
import org.dromelvan.modell.persistence.SpelareDAO;
import org.dromelvan.modell.persistence.SpelareSasongDAO;
import org.dromelvan.struts2.util.DeltagareMap;
import org.dromelvan.struts2.util.OkandSpelare;
import org.dromelvan.struts2.util.SpelareMap;

import dromelvan.tools.xml.BytenDocument;
import dromelvan.tools.xml.DeByteXml;


/**
 * @author macke
 */
public class UploadDeByten extends UploadAction<BytenDocument> {

	/**
	 *
	 */
	private static final long serialVersionUID = -1024425383846792869L;
	private DeByteOmgang deByteOmgang;
    private final Set<OkandSpelare> okandSpelareSet = new HashSet<OkandSpelare>();

    @Override
	protected void validateDocumentContent() {
        getSessionManager().beginTransaction();
        int deByteOmgangId = getDocument().getByten().getDeByteOmgangId().intValue();
        setDeByteOmgang(getDAOFactory().getDeByteOmgangDAO().findById(deByteOmgangId));

        DeltagareMap deltagareMap = new DeltagareMap(getDAOFactory().getDeltagareDAO().findAll());
        SpelareMap tillgangligaSpelareMap = new SpelareMap();
        for(TillgangligSpelare tillgangligSpelare : getDeByteOmgang().getTillgangligaSpelare()) {
        	tillgangligaSpelareMap.add(tillgangligSpelare.getSpelare());
        }
        tillgangligaSpelareMap.add(getDAOFactory().getSpelareDAO().findById(1));

        for(DeByteXml deByteXml : getDocument().getByten().getByteArray()) {
            if(tillgangligaSpelareMap.get(deByteXml.getKoptSpelare()) == null) {
		        OkandSpelare okandSpelare = new OkandSpelare();
		        okandSpelare.setNamn(deByteXml.getKoptSpelare());
		        addOkandSpelare(okandSpelare);
		    }
            if(tillgangligaSpelareMap.get(deByteXml.getSaldSpelare()) == null) {
		        OkandSpelare okandSpelare = new OkandSpelare();
		        okandSpelare.setNamn(deByteXml.getSaldSpelare());
		        addOkandSpelare(okandSpelare);
            }
            if(deltagareMap.get(deByteXml.getDeltagare()) == null) {
            	addActionError("Okänd deltagare: " + deByteXml.getDeltagare());
            }
        }
        if(!getOkandSpelareSet().isEmpty()) {
            addActionError("Okända spelare.");
        }
    }

	@Override
	public String doExecute() {
		// 2012-13 och framåt vill vi kunna göra detta
		//if(!getDeByteOmgang().getDeByten().isEmpty()) {
		//	addActionError("Byten för omgången har redan lagts in.");
		//	return ERROR;
		//}

		Sasong sasong = getDeByteOmgang().getDeOmgang().getTavling().getSasong();
        SpelareMap spelareMap = new SpelareMap(getDAOFactory().getSpelareDAO().findAll());
        DeltagareMap deltagareMap = new DeltagareMap(getDAOFactory().getDeltagareDAO().findAll());

        List<DeByte> deByteList = new ArrayList<DeByte>();
        for(DeByteXml deByteXml : getDocument().getByten().getByteArray()) {
        	DeByte deByte = new DeByte();
        	deByte.setDeByteOmgang(getDeByteOmgang());
        	deByte.setDeltagare(deltagareMap.get(deByteXml.getDeltagare()));
        	deByte.setSaldSpelare(spelareMap.get(deByteXml.getSaldSpelare()));
        	deByte.setKoptSpelare(spelareMap.get(deByteXml.getKoptSpelare()));
        	deByte.setPris(deByteXml.getPris());
        	deByteList.add(deByte);
        }

        SpelareDAO spelareDAO = getDAOFactory().getSpelareDAO();
        SpelareSasongDAO spelareSasongDAO = getDAOFactory().getSpelareSasongDAO();

        //Deltagare ingen = getDAOFactory().getDeltagareDAO().findById(1);
        //for(DeByte deByte : deByteList) {
        	//Spelare spelare = deByte.getSaldSpelare();
        	// Fr.o.m 2012-13 vill vi inte göra detta eftersom a) detta görs vid transferlistning
        	// och b) vi vill kunna ladda upp byten i fler omgångar.
        	//if(spelare.getId() > 1) {
	        //	SpelareSasong spelareSasong = spelareSasongDAO.findBySpelareOchSasong(spelare,sasong);
	        //	spelare.setDeltagare(ingen);
	        //	spelareSasong.setDeltagare(ingen);
	        //	spelareSasong.setPris(0.0);
	        //	spelareDAO.save(spelare);
	        //	spelareSasongDAO.save(spelareSasong);
        	//}
        //}

        for(DeByte deByte : deByteList) {
        	Spelare spelare = deByte.getKoptSpelare();
        	if(spelare.getId() > 1) {
	        	SpelareSasong spelareSasong = spelareSasongDAO.findBySpelareOchSasong(spelare,sasong);
	        	spelare.setDeltagare(deByte.getDeltagare());
	        	spelareSasong.setDeltagare(deByte.getDeltagare());
	        	spelareSasong.setPris(deByte.getPris());
	        	spelareDAO.save(spelare);
	        	spelareSasongDAO.save(spelareSasong);
        	}
        }

		getDAOFactory().getDeByteDAO().saveBatch(deByteList);

		getSessionManager().commitTransaction();
		getSessionManager().evictCache();

		return SUCCESS;
	}

	public DeByteOmgang getDeByteOmgang() {
		return deByteOmgang;
	}
	public void setDeByteOmgang(DeByteOmgang deByteOmgang) {
		this.deByteOmgang = deByteOmgang;
	}

	public Set<OkandSpelare> getOkandSpelareSet() {
        return okandSpelareSet;
    }
    protected void addOkandSpelare(OkandSpelare okandSpelare) {
        getOkandSpelareSet().add(okandSpelare);
    }

    @Override
	protected BytenDocument parse(File file) throws IOException, XmlException {
	    return BytenDocument.Factory.parse(file);
	}

}
