package org.dromelvan.struts2;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dromelvan.modell.Spelare;

public class DownloadSpelareNamnLista extends DownloadAction {

    /**
	 *
	 */
	private static final long serialVersionUID = -5144669908874685902L;

	protected String doExecute() {
        getSessionManager().beginTransaction();
        List<Spelare> spelareList = getDAOFactory().getSpelareDAO().findAll();
        Collections.sort(spelareList, new Comparator<Spelare>() {
            public int compare(Spelare spelare1,Spelare spelare2) {
                return spelare1.getNamn().compareTo(spelare2.getNamn());
            }
        });

        StringBuffer stringBuffer = new StringBuffer();
        for(Spelare spelare : spelareList) {
            stringBuffer.append(spelare.getNamn().replace(' ','_'));
            stringBuffer.append("\n");
        }
        try {
        	// Properties antar att konfigurationsfiler har iso-8859-1 encoding
        	// så vi sätter det här
        	setInputStream(new ByteArrayInputStream(stringBuffer.toString().getBytes("ISO8859_1")));
        } catch(UnsupportedEncodingException e) {
        	// Detta borde aldrig hända
        	Logger.getLogger(getClass()).error("Kunde inte skapa inputstream", e);
        }
        return SUCCESS;
    }
}
