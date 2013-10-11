package org.dromelvan.struts2;

import java.util.List;

import org.dromelvan.modell.Lag;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.SpelareSasong;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;

public class LagSpelarTrupp extends SpelarTrupp<org.dromelvan.modell.statistik.spelartrupp.LagSpelarTrupp> {

    /**
	 *
	 */
	private static final long serialVersionUID = 7565257968438212591L;
	private int lagId;

    @ConversionErrorFieldValidator(message = "Felaktigt format på lagId.")
    public int getLagId() {
        return lagId;
    }
    public void setLagId(int lagId) {
        this.lagId = lagId;
    }

    public org.dromelvan.modell.statistik.spelartrupp.LagSpelarTrupp getLagSpelarTrupp() {
        return getSpelarTrupp();
    }

    protected org.dromelvan.modell.statistik.spelartrupp.LagSpelarTrupp skapaSpelarTrupp(Sasong sasong) {
        Lag lag = getDAOFactory().getLagDAO().findById(getLagId());
        List<SpelareSasong> spelareSasonger = getDAOFactory().getSpelareSasongDAO().findByLagOchSasong(lag, sasong);
        return new org.dromelvan.modell.statistik.spelartrupp.LagSpelarTrupp(lag,sasong,spelareSasonger);
    }

}