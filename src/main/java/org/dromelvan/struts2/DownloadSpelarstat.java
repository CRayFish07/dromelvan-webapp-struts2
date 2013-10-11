package org.dromelvan.struts2;

import java.io.ByteArrayInputStream;

import org.dromelvan.modell.Sasong;
import org.dromelvan.workbook.SpelarstatWorkbook;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;


public class DownloadSpelarstat extends DownloadAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 8833476044065477138L;
	private int sasongId;
	private Sasong sasong;

    protected String doExecute() {
        getSessionManager().beginTransaction();
        setSasong(getDAOFactory().getSasongDAO().findById(getSasongId()));
        SpelarstatWorkbook workbook = new SpelarstatWorkbook(getSasong().getSpelareSasongStatistik());

        setInputStream(new ByteArrayInputStream(workbook.getBytes()));
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
	public void setSasong(Sasong sasong) {
		this.sasong = sasong;
	}

}
