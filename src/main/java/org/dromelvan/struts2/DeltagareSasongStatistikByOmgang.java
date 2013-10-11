package org.dromelvan.struts2;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.dromelvan.modell.DeOmgang;
import org.dromelvan.modell.statistik.SpelareDeltagareSasongStatistik;


public class DeltagareSasongStatistikByOmgang extends DeltagareSasongStatistik {

	/**
	 *
	 */
	private static final long serialVersionUID = 323593909024529894L;
	public int totalt = 0;

	public SortedSet<DeOmgang> getDeOmgangar() {
		return getTavling().getDeOmgangar();
	}

    public List<Integer> getPoangPerOmgangSumma() {
    	List<Integer> poangList = new ArrayList<Integer>();

    	for(int i = 0; i < 38; ++i) {
    		int poang = 0;
    		for(SpelareDeltagareSasongStatistik statistik : getSpelareDeltagareSasongStatistik()) {
    			if(statistik.getPoangPerOmgangNummer(i) > -1000) {
    				poang += statistik.getPoangPerOmgangNummer(i);
    			}
    		}
    		poangList.add(poang);
    		totalt += poang;
    	}
    	return poangList;
    }

    public int getTotalt() {
    	return totalt;
    }

}