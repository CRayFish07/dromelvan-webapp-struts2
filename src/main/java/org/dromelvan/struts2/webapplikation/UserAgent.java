package org.dromelvan.struts2.webapplikation;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.wurfl.core.Device;


public class UserAgent implements Comparable<UserAgent> {

	private String namn;
	private String deviceId;
	private int antal;
	private boolean mobile;
	
	public UserAgent(HttpServletRequest httpServletRequest, Device device) {
		String namn = httpServletRequest.getHeader("User-Agent");
		setNamn((namn != null ? namn : "null"));
		setDeviceId(device.getId());
		setAntal(0);
		
    	boolean wireLessDevice = Boolean.valueOf(device.getCapability("is_wireless_device"));
    	boolean tablet = Boolean.valueOf(device.getCapability("is_tablet"));
    	setMobile(wireLessDevice && !tablet);
	}
	
	public String getNamn() {
		return namn;
	}
	public void setNamn(String namn) {
		this.namn = namn;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public int getAntal() {
		return antal;
	}
	public synchronized void setAntal(int antal) {
		this.antal = antal;
	}	
	public synchronized void inkrementeraAntal() {
		this.antal++;
	}
	
	public boolean isMobile() {
		return mobile;
	}
	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

	public int compareTo(UserAgent userAgent) {
		return userAgent.getAntal() - getAntal();
	}
	
	public String toString() {
		return getDeviceId() + " (" + getNamn() + ") Antal: " + getAntal() + " Mobile: " + isMobile();
	}
	
}
