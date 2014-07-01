package org.dromelvan.struts2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dromelvan.modell.Anvandare;
import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.SpelareSasong;
import org.dromelvan.modell.SpelareSasongStatistik;
import org.dromelvan.modell.TillgangligSpelare;
import org.dromelvan.modell.persistence.TillgangligSpelareDAO;
import org.dromelvan.modell.util.collections.SpelareSasongStatistikSorter;
import org.dromelvan.struts2.util.TillgangligSpelareStatistik;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

public class DeltagareSpelarTrupp extends SpelarTrupp<org.dromelvan.modell.statistik.spelartrupp.DeltagareSpelarTrupp> {

    /**
	 *
	 */
	private static final long serialVersionUID = 1387007215920144133L;
	private int deltagareId;
    private int kolumn = SpelareSasongStatistikSorter.POSITION;

    @ConversionErrorFieldValidator(message = "Felaktigt format på deltagareId.")
    public int getDeltagareId() {
        return deltagareId;
    }
    public void setDeltagareId(int deltagareId) {
        this.deltagareId = deltagareId;
    }

    @ConversionErrorFieldValidator(message = "Felaktigt format på kolumn.")
    public int getKolumn() {
        return kolumn;
    }
    public void setKolumn(int kolumn) {
        this.kolumn = kolumn;
    }

    public Deltagare getDeltagare() {
        return getDeltagareSpelarTrupp().getDeltagare();
    }

    public org.dromelvan.modell.statistik.spelartrupp.DeltagareSpelarTrupp getDeltagareSpelarTrupp() {
        return getSpelarTrupp();
    }

    public double getKvarstaendeTransferBudget() {
        int summa = getTransferBudget() - (int)(getSpelarTrupp().getPris() * 10.0);
        return summa / 10.0;
    }

    public double getStorstaBud() {
        if(getSpelarTrupp().getAntalSpelare() == 11) {
            return 0.0;
        }

        int summa = getTransferBudget() - (int)(getSpelarTrupp().getPris() * 10.0);
        if(summa > 0) {
            summa -= (10 - getSpelarTrupp().getAntalSpelare()) * getMinstaBud();
        }
        return summa / 10.0;
    }

    protected org.dromelvan.modell.statistik.spelartrupp.DeltagareSpelarTrupp skapaSpelarTrupp(Sasong sasong) {
        Deltagare deltagare = getDAOFactory().getDeltagareDAO().findById(getDeltagareId());
        List<SpelareSasong> spelareSasonger = getDAOFactory().getSpelareSasongDAO().findByDeltagareOchSasong(deltagare, sasong);
        org.dromelvan.modell.statistik.spelartrupp.DeltagareSpelarTrupp deltagareSpelarTrupp = new org.dromelvan.modell.statistik.spelartrupp.DeltagareSpelarTrupp(deltagare,sasong,spelareSasonger);
        deltagareSpelarTrupp.sortSpelareSasongStatistik(getKolumn());
        return deltagareSpelarTrupp;
    }

    public boolean getHarTransferListadeSpelare() {
        return !getTransferListadeSpelare().isEmpty();
    }

    public List<TillgangligSpelareStatistik> getTransferListadeSpelare() {
        Anvandare anvandare = getAnvandare();
        List<TillgangligSpelare> tillgangligaSpelare = new ArrayList<TillgangligSpelare>();
        List<TillgangligSpelareStatistik> tillgangligSpelareStatistik = new ArrayList<TillgangligSpelareStatistik>();

        if(anvandare.isAdministrator() || getDeltagare().equals(anvandare.getDeltagare())) {
            DeByteOmgang deByteOmgang = getDeByteOmgang();
            if(deByteOmgang != null && deByteOmgang.getStatus() == 0) {
                TillgangligSpelareDAO tillgangligSpelareDAO = getDAOFactory().getTillgangligSpelareDAO();
                tillgangligaSpelare = tillgangligSpelareDAO.findByDeByteOmgangOchDeltagare(deByteOmgang, getDeltagare());

                Map<Spelare,SpelareSasongStatistik> spelareSasongStatistikMap = new HashMap<Spelare,SpelareSasongStatistik>();
                for(SpelareSasongStatistik spelareSasongStatistik : getDeByteOmgang().getDeOmgang().getTavling().getSasong().getSpelareSasongStatistik()) {
                    spelareSasongStatistikMap.put(spelareSasongStatistik.getSpelare(), spelareSasongStatistik);
                }

                for(TillgangligSpelare tillgangligSpelare : tillgangligaSpelare) {
                    TillgangligSpelareStatistik statistik = new TillgangligSpelareStatistik();
                    statistik.setTillgangligSpelare(tillgangligSpelare);
                    statistik.setSpelareSasongStatistik(spelareSasongStatistikMap.get(tillgangligSpelare.getSpelare()));
                    // Tidigare säsonger kan det ha slunkit med någon spelare som inte spelat alls och inte har någon
                    // registrerad säsonginfo
                    if(statistik.getSpelareSasongStatistik() != null) {
                        tillgangligSpelareStatistik.add(statistik);
                    }
                }
            }
        }
        Collections.sort(tillgangligSpelareStatistik);
        return tillgangligSpelareStatistik;
    }

    public DeByteOmgang getDeByteOmgang() {
        Sasong sasong = getDAOFactory().getSasongDAO().findById(getDefaultSasongId());
        List<DeByteOmgang> deByteOmgangList = getDAOFactory().getDeByteOmgangDAO().findBySasong(sasong);
        Collections.sort(deByteOmgangList);
        if(deByteOmgangList.isEmpty()) {
            return null;
        }
        return deByteOmgangList.get(0);
    }

}