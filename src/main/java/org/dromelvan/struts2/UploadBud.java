package org.dromelvan.struts2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.xmlbeans.XmlException;
import org.dromelvan.modell.Bud;
import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.TillgangligSpelare;
import org.dromelvan.struts2.util.DeltagareMap;
import org.dromelvan.struts2.util.OkandSpelare;
import org.dromelvan.struts2.util.SpelareMap;

import dromelvan.tools.xml.BudOmgangDocument;
import dromelvan.tools.xml.BudXml;


/**
 * @author macke
 */
public class UploadBud extends UploadAction<BudOmgangDocument> {

	/**
	 *
	 */
	private static final long serialVersionUID = 2886621131127262015L;
	private DeByteOmgang deByteOmgang;
    private Set<OkandSpelare> okandSpelareSet = new HashSet<OkandSpelare>();

    protected void validateDocumentContent() {
        getSessionManager().beginTransaction();
        int deByteOmgangId = getDocument().getBudOmgang().getDeByteOmgangId().intValue();
        setDeByteOmgang(getDAOFactory().getDeByteOmgangDAO().findById(deByteOmgangId));

        DeltagareMap deltagareMap = new DeltagareMap(getDAOFactory().getDeltagareDAO().findAll());
        SpelareMap tillgangligaSpelareMap = new SpelareMap();
        for(TillgangligSpelare tillgangligSpelare : getDeByteOmgang().getTillgangligaSpelare()) {
        	tillgangligaSpelareMap.add(tillgangligSpelare.getSpelare());
        }
        // 'Ingen' måste också alltid vara tillgänglig
        tillgangligaSpelareMap.add(getDAOFactory().getSpelareDAO().findById(1));

        for(BudXml budXml : getDocument().getBudOmgang().getBudArray()) {
            if(tillgangligaSpelareMap.get(budXml.getSpelare()) == null) {
		        OkandSpelare okandSpelare = new OkandSpelare();
		        okandSpelare.setNamn(budXml.getSpelare());
		        addOkandSpelare(okandSpelare);
		    }
            if(tillgangligaSpelareMap.get(budXml.getListadSpelare()) == null) {
		        OkandSpelare okandSpelare = new OkandSpelare();
		        okandSpelare.setNamn(budXml.getListadSpelare());
		        addOkandSpelare(okandSpelare);
            }
            if(deltagareMap.get(budXml.getDeltagare()) == null) {
            	addActionError("Okänd deltagare: " + budXml.getDeltagare());
            }
        }
        if(!getOkandSpelareSet().isEmpty()) {
            addActionError("Okända spelare.");
        }
    }

	public String doExecute() {

        SpelareMap spelareMap = new SpelareMap(getDAOFactory().getSpelareDAO().findAll());
        DeltagareMap deltagareMap = new DeltagareMap(getDAOFactory().getDeltagareDAO().findAll());

		List<Bud> budList = new ArrayList<Bud>();
		for(BudXml budXml : getDocument().getBudOmgang().getBudArray()) {
			Bud bud = new Bud();
			bud.setDeByteOmgang(getDeByteOmgang());
			bud.setDeltagare(deltagareMap.get(budXml.getDeltagare()));
			bud.setSpelare(spelareMap.get(budXml.getSpelare()));
			bud.setListadSpelare(spelareMap.get(budXml.getListadSpelare()));
			bud.setPrioritet(budXml.getPrioritet());
			bud.setListadSpelarePrioritet(budXml.getListadSpelarePrioritet());
			bud.setPris(budXml.getPris());
			bud.setLyckat(budXml.getLyckat());
			budList.add(bud);
		}

		getDAOFactory().getBudDAO().deleteBatch(getDeByteOmgang().getBud());
		getDAOFactory().getBudDAO().saveBatch(budList);

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

    protected BudOmgangDocument parse(File file) throws IOException, XmlException {
	    return BudOmgangDocument.Factory.parse(file);
	}

}
