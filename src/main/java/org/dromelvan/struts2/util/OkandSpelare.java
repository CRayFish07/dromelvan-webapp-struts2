package org.dromelvan.struts2.util;

import java.util.List;

import org.dromelvan.modell.DromelvaObjekt;
import org.dromelvan.modell.Spelare;


/**
 * @author macke
 */
public class OkandSpelare extends DromelvaObjekt {

    /**
	 *
	 */
	private static final long serialVersionUID = 4842794752668877967L;
	private String namn;
    private String lag;
    private List<Spelare> alternativ;

    public String getNamn() {
        return namn;
    }
    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getFornamn() {
    	int i = namn.lastIndexOf(' ');
    	if(i > 0) {
    		return namn.substring(0,i);
    	}
    	return namn;
    }

    public String getEfternamn() {
    	int i = namn.lastIndexOf(' ');
    	if(i > 0) {
    		return namn.substring(i);
    	}
    	return namn;
    }

    public String getLag() {
		return lag;
	}
	public void setLag(String lag) {
		this.lag = lag;
	}

	public List<Spelare> getAlternativ() {
        return alternativ;
    }
    public void setAlternativ(List<Spelare> alternativ) {
        this.alternativ = alternativ;
    }

}
