package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.Lag;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Tavling;
import org.dromelvan.modell.statistik.tabell.LagSasongStatistik;
import org.dromelvan.modell.statistik.tabell.PremierLeagueTavlingTabell;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

public class LagMatcher extends DromelvaActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = -6415779482044181728L;
	private int lagId;
	private int sasongId;
	private LagSasongStatistik lagSasongStatistik;
	private List<Sasong> sasonger;

	public String doExecute() {
        getSessionManager().beginTransaction();
        Lag lag = getDAOFactory().getLagDAO().findById(getLagId());
        Sasong sasong = getDAOFactory().getSasongDAO().findById(getSasongId() != 0 ? getSasongId() : getDefaultSasongId());
        Tavling tavling = getDAOFactory().getTavlingDAO().findBySasongIdOchTyp(sasong.getId(),Tavling.PREMIER_LEAGUE);

        PremierLeagueTavlingTabell tabell = new PremierLeagueTavlingTabell(tavling);
        lagSasongStatistik = tabell.getTabellObjekt(lag);
        if(lagSasongStatistik == null) {
        	lagSasongStatistik = new LagSasongStatistik(lag,sasong);
        }

        sasonger = getDAOFactory().getSasongDAO().findAll();
        Collections.sort(sasonger);
        return SUCCESS;
	}

    @ConversionErrorFieldValidator(message = "Felaktigt format på lagId.")
    public int getLagId() {
        return lagId;
    }
    public void setLagId(int lagId) {
        this.lagId = lagId;
    }

	@ConversionErrorFieldValidator(message = "Felaktigt format på sasongId.")
	public int getSasongId() {
		return sasongId;
	}
	public void setSasongId(int sasongId) {
		this.sasongId = sasongId;
	}

	public LagSasongStatistik getLagSasongStatistik() {
		return lagSasongStatistik;
	}

	public Sasong getSasong() {
		return getDAOFactory().getSasongDAO().findById(getSasongId());
	}

	public List<Sasong> getSasonger() {
		return sasonger;
	}

}