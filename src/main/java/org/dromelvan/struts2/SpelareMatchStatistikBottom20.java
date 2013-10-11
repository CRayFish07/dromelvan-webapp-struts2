package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.SpelareMatchStatistik;
import org.dromelvan.modell.util.collections.SpelareMatchStatistikSorter;

public class SpelareMatchStatistikBottom20 extends DromelvaActionSupport {

    /**
	 *
	 */
	private static final long serialVersionUID = 7425113603981926074L;
	private List<SpelareMatchStatistik> spelareMatchStatistik;

    public String doExecute() {
        getSessionManager().beginTransaction();
        // -12 blir ett lagom värde här för tillfället
        spelareMatchStatistik = getDAOFactory().getSpelareMatchStatistikDAO().findByMaximumPoang(-12);
        Collections.sort(spelareMatchStatistik,new SpelareMatchStatistikSorter(SpelareMatchStatistikSorter.POANG));
        Collections.reverse(spelareMatchStatistik);
        return SUCCESS;
    }

    public List<SpelareMatchStatistik> getSpelareMatchStatistik() {
        return spelareMatchStatistik;
    }
}
