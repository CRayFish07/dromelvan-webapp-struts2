package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.Bud;
import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.SpelareSasong;
import org.dromelvan.modell.SpelareSasongStatistik;
import org.dromelvan.modell.Tavling;
import org.dromelvan.modell.TillgangligSpelare;
import org.dromelvan.modell.statistik.tabell.DromelvaTavlingTabell;
import org.dromelvan.modell.statistik.tabell.TabellObjekt;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

/**
 *
 * @author macke
 */
public class InsertBud extends DromelvaAdminAction {

    /**
	 *
	 */
	private static final long serialVersionUID = 3425506427319368475L;
	private int spelareId;
	private Spelare spelare;
	private TillgangligSpelare tillgangligSpelare;
	private DeByteOmgang deByteOmgang;
    private double pris;

    @Override
    public boolean isRequiresAdministrator() {
        return false;
    }

    public String input() {
        getSessionManager().beginTransaction();
        clearWorkFlowObject();
        setSpelare(getDAOFactory().getSpelareDAO().findById(getSpelareId()));

        Sasong sasong = getDAOFactory().getSasongDAO().findById(getDefaultSasongId());
        List<DeByteOmgang> deByteOmgangList = getDAOFactory().getDeByteOmgangDAO().findBySasong(sasong);
        Collections.sort(deByteOmgangList);
        if(isLoggedIn()
           && !deByteOmgangList.isEmpty()) {
            setDeByteOmgang(deByteOmgangList.get(0));
        }

        if(getDeByteOmgang().getStatus() != 1) {
            addActionError("Bytesomgången är inte öppen för bud. Bättre lycka nästa gång.");
            return ERROR;
        } else if(!sasong.getDeltagare().contains(getAnvandare().getDeltagare())) {
            addActionError("Bud kan bara läggas av användare som deltar denna säsong.");
            return ERROR;
        }

        for(TillgangligSpelare tillgangligSpelare : getDAOFactory().getTillgangligSpelareDAO().findByDeByteOmgang(getDeByteOmgang())) {
            if(tillgangligSpelare.getSpelare().equals(getSpelare())) {
                setTillgangligSpelare(tillgangligSpelare);
            }
        }
        if(getTillgangligSpelare() == null) {
            addActionError(getSpelare().getNamn() + " är inte tillgänglig för byte.");
            return ERROR;
        }

        List<SpelareSasong> spelareSasonger = getDAOFactory().getSpelareSasongDAO().findByDeltagareOchSasong(getAnvandare().getDeltagare(), sasong);
        org.dromelvan.modell.statistik.spelartrupp.DeltagareSpelarTrupp deltagareSpelarTrupp = new org.dromelvan.modell.statistik.spelartrupp.DeltagareSpelarTrupp(getAnvandare().getDeltagare(),sasong,spelareSasonger);
        if(deltagareSpelarTrupp.getAntalSaknadeSpelare(getSpelare().getPosition()) <= 0) {
            addActionError("Din spelartrupp har redan fullt antal spelare på position " + getSpelare().getPosition() + ".");
            return ERROR;
        }

        setPris(deltagareSpelarTrupp.getStorstaBud());

        setWorkFlowObject(getSpelare());
        return INPUT;
    }

	public String doExecute() {
        Sasong sasong = getDAOFactory().getSasongDAO().findById(getDefaultSasongId());
        List<DeByteOmgang> deByteOmgangList = getDAOFactory().getDeByteOmgangDAO().findBySasong(sasong);
        Collections.sort(deByteOmgangList);
        if(isLoggedIn()
           && !deByteOmgangList.isEmpty()) {
            setDeByteOmgang(deByteOmgangList.get(0));
        }

        Deltagare deltagare = getAnvandare().getDeltagare();
        Tavling tavling = getDAOFactory().getTavlingDAO().findByIdOchTyp(getDefaultDeTavlingId(),Tavling.DROMELVAN);
        DromelvaTavlingTabell tabell = new DromelvaTavlingTabell(tavling, false);
        org.dromelvan.modell.statistik.tabell.DeltagareSasongStatistik deltagareSasongStatistik = null;
        for(TabellObjekt tabellObjekt : tabell.getTabellObjekt()) {
            if(tabellObjekt instanceof org.dromelvan.modell.statistik.tabell.DeltagareSasongStatistik) {
                org.dromelvan.modell.statistik.tabell.DeltagareSasongStatistik deltagareSasongStatistikTemp = (org.dromelvan.modell.statistik.tabell.DeltagareSasongStatistik)tabellObjekt;
                if(deltagareSasongStatistikTemp.getDeltagare().equals(deltagare)) {
                    deltagareSasongStatistik = deltagareSasongStatistikTemp;
                    break;
                }
            }
        }

        SpelareSasongStatistik spelareSasongStatistik = null;
        for(SpelareSasongStatistik spelareSasongStatistikTemp : sasong.getSpelareSasongStatistik()) {
            if(spelareSasongStatistikTemp.getSpelare().equals(spelare)) {
                spelareSasongStatistik = spelareSasongStatistikTemp;
                break;
            }
        }

        // Kolla om det redan finns bud från deltagaren på spelaren
        Bud bud = null;
        for(Bud budTemp : getDeByteOmgang().getBud()) {
            if(budTemp.getSpelare().equals(spelare) && budTemp.getDeltagare().equals(deltagare)) {
                bud = budTemp;
                break;
            }
        }

        if(bud == null) {
            bud = new Bud();
        }

        bud.setDeByteOmgang(getDeByteOmgang());
        bud.setDeltagare(deltagare);
        bud.setSpelare(spelare);
        bud.setListadSpelare(getDAOFactory().getSpelareDAO().findById(1));
        bud.setPrioritet(spelareSasongStatistik.getPoang());
        bud.setListadSpelarePrioritet(deltagareSasongStatistik.getPlacering());
        bud.setPris(getPris());
        bud.setAktivtPris(bud.getPris());
        bud.setLyckat(false);

        System.out.print("Lyckat: " + bud.isLyckat());

        getDAOFactory().getBudDAO().save(bud);

		getSessionManager().commitTransaction();
		getSessionManager().evictCache();
        clearWorkFlowObject();

		return SUCCESS;
	}

	public void validate() {
        getSessionManager().beginTransaction();

        double mod = (getPris() * 10) % 5;
        if(mod != 0.0) {
            addFieldError("pris","Budet måste sluta med .0 eller .5.");
        }

        Sasong sasong = getDAOFactory().getSasongDAO().findById(getDefaultSasongId());
        List<SpelareSasong> spelareSasonger = getDAOFactory().getSpelareSasongDAO().findByDeltagareOchSasong(getAnvandare().getDeltagare(), sasong);
        org.dromelvan.modell.statistik.spelartrupp.DeltagareSpelarTrupp deltagareSpelarTrupp = new org.dromelvan.modell.statistik.spelartrupp.DeltagareSpelarTrupp(getAnvandare().getDeltagare(),sasong,spelareSasonger);
        if(pris > deltagareSpelarTrupp.getStorstaBud()) {
            setPris(deltagareSpelarTrupp.getStorstaBud());
            addFieldError("pris","Ditt maxbud för denna bytesomgång är " + deltagareSpelarTrupp.getStorstaBud() + ".");
        }
	}

    public boolean validateWorkFlowObject() {
        try {
            setSpelare((Spelare)getWorkFlowObject());
        } catch(ClassCastException e) {}
        return getSpelare() != null;
    }

    @ConversionErrorFieldValidator(message = "Felaktigt format på spelareId.")
    public int getSpelareId() {
        return spelareId;
    }
    public void setSpelareId(int spelareId) {
        this.spelareId = spelareId;
    }

    public Spelare getSpelare() {
        return spelare;
    }

    public void setSpelare(Spelare spelare) {
        this.spelare = spelare;
    }

    public TillgangligSpelare getTillgangligSpelare() {
        return tillgangligSpelare;
    }

    public void setTillgangligSpelare(TillgangligSpelare tillgangligSpelare) {
        this.tillgangligSpelare = tillgangligSpelare;
    }

    public DeByteOmgang getDeByteOmgang() {
        return deByteOmgang;
    }

    public void setDeByteOmgang(DeByteOmgang deByteOmgang) {
        this.deByteOmgang = deByteOmgang;
    }

    @ConversionErrorFieldValidator(message = "Felaktigt format på pris.")
	public double getPris() {
		return pris;
	}
	public void setPris(double pris) {
		this.pris = pris;
	}
}
