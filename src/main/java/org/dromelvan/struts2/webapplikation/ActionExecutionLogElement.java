package org.dromelvan.struts2.webapplikation;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.dromelvan.struts2.DromelvaActionSupport;



/**
 * @author macke
 */
public class ActionExecutionLogElement implements Comparable<ActionExecutionLogElement> {
	
	private String actionNamn;
	private Date tidpunkt;
	private String remoteHost;
	private String remoteAddr;
	private long tid;
	
    private static final SimpleDateFormat datumFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public ActionExecutionLogElement(DromelvaActionSupport dromelvaActionSupport) {
    	setActionNamn(dromelvaActionSupport.getActionNamn());
    	setTidpunkt(new Date());
    	setRemoteHost(dromelvaActionSupport.getRemoteHost());
    	setRemoteAddr(dromelvaActionSupport.getRemoteAddr());
    	setTid(dromelvaActionSupport.getActionTimer());
    }
    
	public String getActionNamn() {
		return actionNamn;
	}
	public void setActionNamn(String actionNamn) {
		this.actionNamn = actionNamn;
	}

	public Date getTidpunkt() {
		return tidpunkt;
	}
	public void setTidpunkt(Date tidpunkt) {
		this.tidpunkt = tidpunkt;
	}
	public String getTidpunktStr() {
		return datumFormat.format(getTidpunkt());
	}
    	
	public String getRemoteHost() {
		return remoteHost;
	}
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public long getTid() {
		return tid;
	}
	public void setTid(long tid) {
		this.tid = tid;
	}

	public int compareTo(ActionExecutionLogElement e) {
		return e.getTidpunkt().compareTo(getTidpunkt());
	}
}
