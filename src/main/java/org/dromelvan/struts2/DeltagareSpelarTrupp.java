package org.dromelvan.struts2;

import java.util.List;

import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.SpelareSasong;
import org.dromelvan.modell.util.collections.SpelareSasongStatistikSorter;

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

}