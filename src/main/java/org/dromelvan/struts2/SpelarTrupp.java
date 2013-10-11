package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.Sasong;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

public abstract class SpelarTrupp<T extends org.dromelvan.modell.statistik.spelartrupp.SpelarTrupp<?>> extends DromelvaActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 6642748267110534159L;
	private int sasongId;
	private Sasong sasong;
	private T spelarTrupp;
	private List<Sasong> sasonger;

	public String doExecute() {
        getSessionManager().beginTransaction();
        sasong = getDAOFactory().getSasongDAO().findById(getSasongId() != 0 ? getSasongId() : getDefaultSasongId());
        spelarTrupp = skapaSpelarTrupp(sasong);

        sasonger = getDAOFactory().getSasongDAO().findAll();
        Collections.sort(sasonger);
        return SUCCESS;
	}

	@ConversionErrorFieldValidator(message = "Felaktigt format på sasongId.")
	public int getSasongId() {
		return sasongId;
	}
	public void setSasongId(int sasongId) {
		this.sasongId = sasongId;
	}

	public Sasong getSasong() {
		return sasong;
	}

	public T getSpelarTrupp() {
		return spelarTrupp;
	}

	public List<Sasong> getSasonger() {
		return sasonger;
	}

    protected abstract T skapaSpelarTrupp(Sasong sasong);
}