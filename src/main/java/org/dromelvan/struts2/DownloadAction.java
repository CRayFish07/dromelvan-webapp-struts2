package org.dromelvan.struts2;

import java.io.InputStream;

public abstract class DownloadAction extends DromelvaActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9170638497094255513L;
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
}
