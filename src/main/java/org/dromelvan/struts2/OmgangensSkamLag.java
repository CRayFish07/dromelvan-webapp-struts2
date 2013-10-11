package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.Omgang;
import org.dromelvan.modell.Tavling;
import org.dromelvan.modell.statistik.OmgangensSkamLagStatistik;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

public class OmgangensSkamLag extends DromelvaActionSupport {

    /**
	 *
	 */
	private static final long serialVersionUID = 2118410867841584329L;
	private int omgangId;
    private Omgang omgang;
    private Omgang foregaendeOmgang;
    private Omgang nastaOmgang;
    private OmgangensSkamLagStatistik omgangensSkamLagStatistik;
    private List<Tavling> tavlingar;

    public String doExecute() {
        getSessionManager().beginTransaction();
        omgang = getDAOFactory().getOmgangDAO().findById(omgangId);
        if(!omgang.getIsInlagd()) {
            addActionError("Omgången är inte inlagd.");
            return ERROR;
        }
        omgangensSkamLagStatistik = new OmgangensSkamLagStatistik(omgang);

        if(omgang.getNummer() > 1) {
            foregaendeOmgang = getDAOFactory().getOmgangDAO().findById(omgang.getId() - 1);
        }
        if(omgang.getNummer() < 38) {
            nastaOmgang = getDAOFactory().getOmgangDAO().findById(omgang.getId() + 1);
        }

        tavlingar = getDAOFactory().getTavlingDAO().findByTyp(Tavling.PREMIER_LEAGUE);
        Collections.sort(tavlingar);
        return SUCCESS;
    }

    @ConversionErrorFieldValidator(message = "Felaktigt format på omgangId.")
    public void setOmgangId(int omgangId) {
        this.omgangId = omgangId;
    }
    public int getOmgangId() {
        return omgangId;
    }

    public OmgangensSkamLagStatistik getOmgangensSkamLagStatistik() {
        return omgangensSkamLagStatistik;
    }

    public Omgang getOmgang() {
        return omgang;
    }

    public Omgang getForegaendeOmgang() {
        return foregaendeOmgang;
    }

    public Omgang getNastaOmgang() {
        return nastaOmgang;
    }

    public List<Tavling> getTavlingar() {
        return tavlingar;
    }

    public int getSasongId() {
        return getOmgang().getTavling().getSasong().getId();
    }

}
