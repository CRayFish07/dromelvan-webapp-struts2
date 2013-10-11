package org.dromelvan.struts2.util;

import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.DromelvaObjekt;
import org.dromelvan.modell.Spelare;


/**
 * @author macke
 */
public class SpelareTransfer extends DromelvaObjekt implements Comparable<SpelareTransfer> {

	/**
	 *
	 */
	private static final long serialVersionUID = -3291936581513871591L;
	private DeByteOmgang deByteOmgang;
	private Spelare spelare;
	private Deltagare deltagareSald;
	private Deltagare deltagareKopt;
	private double pris;

	public SpelareTransfer(Spelare spelare,DeByteOmgang deByteOmgang) {
		setDeByteOmgang(deByteOmgang);
		setSpelare(spelare);
	}

	public DeByteOmgang getDeByteOmgang() {
		return deByteOmgang;
	}
	public void setDeByteOmgang(DeByteOmgang deByteOmgang) {
		this.deByteOmgang = deByteOmgang;
	}

	public Spelare getSpelare() {
		return spelare;
	}
	public void setSpelare(Spelare spelare) {
		this.spelare = spelare;
	}

	public Deltagare getDeltagareSald() {
		return deltagareSald;
	}
	public void setDeltagareSald(Deltagare deltagareSald) {
		this.deltagareSald = deltagareSald;
	}

	public Deltagare getDeltagareKopt() {
		return deltagareKopt;
	}
	public void setDeltagareKopt(Deltagare deltagareKopt) {
		this.deltagareKopt = deltagareKopt;
	}

	public double getPris() {
		return pris;
	}
	public void setPris(double pris) {
		this.pris = pris;
	}

	public int compareTo(SpelareTransfer spelareTransfer) {
		return getDeByteOmgang().compareTo(spelareTransfer.getDeByteOmgang());
	}
}
