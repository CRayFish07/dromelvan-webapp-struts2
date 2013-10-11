package org.dromelvan.struts2;

import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.SpelareMatchStatistik;
import org.dromelvan.modell.util.collections.SpelareMatchStatistikSorter;

public class SpelareMatchStatistikTop20 extends DromelvaActionSupport {

    /**
	 *
	 */
	private static final long serialVersionUID = 1870423772897950664L;
	private List<SpelareMatchStatistik> spelareMatchStatistik;

    public String doExecute() {
        getSessionManager().beginTransaction();
        // Vi behöver få över 20 nya matchinsatser med mer än 19 poäng för att detta
        // inte skall bli rätt kriterie längre. Hittils under 4+ säsonger har vi totalt
        // 7 st sådana matcher så detta borde inte behöva ändras på ett tag
        spelareMatchStatistik = getDAOFactory().getSpelareMatchStatistikDAO().findByMinimumPoang(19);
        Collections.sort(spelareMatchStatistik,new SpelareMatchStatistikSorter(SpelareMatchStatistikSorter.POANG));
        return SUCCESS;
    }

    public List<SpelareMatchStatistik> getSpelareMatchStatistik() {
        return spelareMatchStatistik;
    }
}
