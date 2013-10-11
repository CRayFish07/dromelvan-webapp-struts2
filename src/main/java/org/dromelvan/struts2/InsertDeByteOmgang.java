package org.dromelvan.struts2;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.DeOmgang;
import org.dromelvan.modell.Tavling;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

/**
 *
 * @author macke
 */
public class InsertDeByteOmgang extends DromelvaAdminAction implements Preparable {

	/**
	 *
	 */
	private static final long serialVersionUID = 55230423474756182L;
	private int deOmgangId;
	private String namn;
	private String listDatumStr;
	private String datumStr;

	private Date listDatum;
	private Date datum;

	private DeByteOmgang deByteOmgang;
	private List<DeOmgang> deOmgangList;

	public void prepare() {
		getSessionManager().beginTransaction();

		Tavling tavling = getDAOFactory().getTavlingDAO().findById(getDefaultDeTavlingId());
		deOmgangList = new ArrayList<DeOmgang>(tavling.getDeOmgangar());
        Collections.sort(deOmgangList);
	}

	public void validate() {
        try {
            listDatum = DeByteOmgang.datumFormat.parse(getListDatumStr());
        } catch(ParseException e) {
            addFieldError("listDatumStr","Felaktigt format på listdatum");
        }

	    try {
	        datum = DeByteOmgang.datumFormat.parse(getDatumStr());
	    } catch(ParseException e) {
	        addFieldError("datumStr","Felaktigt format på datum");
	    }
	}

	public String doExecute() {
	    DeOmgang deOmgang = getDAOFactory().getDeOmgangDAO().findById(getDeOmgangId());

	    DeByteOmgang deByteOmgang = new DeByteOmgang();
	    deByteOmgang.setDeOmgang(deOmgang);
	    deByteOmgang.setNamn(namn);
	    deByteOmgang.setListDatum(listDatum);
	    deByteOmgang.setDatum(datum);
	    deByteOmgang = getDAOFactory().getDeByteOmgangDAO().save(deByteOmgang);
	    setDeByteOmgang(deByteOmgang);

		getSessionManager().commitTransaction();
		return SUCCESS;
	}

	@ConversionErrorFieldValidator(message = "Felaktigt format på deOmgangId.")
	public int getDeOmgangId() {
		return deOmgangId;
	}
	public void setDeOmgangId(int deOmgangId) {
		this.deOmgangId = deOmgangId;
	}

	@RequiredStringValidator(message = "Namn saknas", shortCircuit = true)
	public String getNamn() {
		return namn;
	}
	public void setNamn(String namn) {
		this.namn = namn;
	}

    @RequiredStringValidator(message = "Listdatum saknas", shortCircuit = true)
	public String getListDatumStr() {
        return listDatumStr;
    }
    public void setListDatumStr(String listDatumStr) {
        this.listDatumStr = listDatumStr;
    }

    @RequiredStringValidator(message = "Datum saknas", shortCircuit = true)
    public String getDatumStr() {
        return datumStr;
    }
    public void setDatumStr(String datumStr) {
        this.datumStr = datumStr;
    }

    public DeByteOmgang getDeByteOmgang() {
        return deByteOmgang;
    }
    public void setDeByteOmgang(DeByteOmgang deByteOmgang) {
        this.deByteOmgang = deByteOmgang;
    }

    public List<DeOmgang> getDeOmgangList() {
		return deOmgangList;
	}

}
