package org.dromelvan.struts2;

import java.io.*;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.*;


/**
 * Basklass för actions som vill hantera uppladdade xmlfiler.
 * @author macke
 */
public abstract class UploadAction<T extends XmlObject> extends DromelvaAdminAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2751272413268341241L;

	private T document;
    
    private File uploadFile;
    private String uploadFileContentType;
    private String uploadFileFileName;
	
    public void validate() {
        Logger.getLogger(UploadAction.class).debug("Validerar " + getUploadFileFileName() + 
                                                   " (" + getUploadFileContentType() + ")");
        if(getUploadFile() == null || getUploadFileContentType() == null || getUploadFileFileName() == null) {
        	addActionError("Fil saknas.");
        } else if(!getUploadFileContentType().equals("text/xml")) {
            addActionError("Felaktigt filformat " + getUploadFileContentType() + ".");
        } else {
            try {
                setDocument(parse(getUploadFile()));
                
                XmlOptions xmlOptions = new XmlOptions();
                List<XmlValidationError> xmlValidationErrorList = new ArrayList<XmlValidationError>();
                xmlOptions.setErrorListener(xmlValidationErrorList);

                if(!getDocument().validate(xmlOptions)) {
                    for(XmlValidationError xmlValidationError : xmlValidationErrorList) {
                        addActionError(xmlValidationError.getMessage());
                        Logger.getLogger(UploadAction.class).debug(xmlValidationError.getMessage());
                    }
                } else {
                    validateDocumentContent();
                }
            } catch(XmlException e) {
                addActionError(getUploadFileFileName() + ":" + 
                               (e.getError() != null ? e.getError().getLine() + ":" : "") +
                               (e.getError() != null ? e.getError().getColumn() + ": " : "") + 
                               (e.getError() != null ? e.getError().getMessage() : e.getMessage()));
            } catch(IOException e) {
                addActionError("IOException. Filen kunde inte parsas.");
                Logger.getLogger(UploadAction.class).debug("Fel uppstod vid parse()",e);
            }               
        }
    }
    
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	
    public String getUploadFileContentType() {
        return uploadFileContentType;
    }
    public void setUploadFileContentType(String uploadFileContentType) {
        this.uploadFileContentType = uploadFileContentType;
    }
    
    public String getUploadFileFileName() {
        return uploadFileFileName;
    }
    public void setUploadFileFileName(String uploadFileFileName) {
        this.uploadFileFileName = uploadFileFileName;
    }

    protected T getDocument() {
        return document;
    }
    protected void setDocument(T document) {
        this.document = document;
    }
    /**
     * Behöver typ bara implementeras som T.Factory.parse(file);
     */
    protected abstract T parse(File file) throws IOException, XmlException;
    
    protected abstract void validateDocumentContent();
    
}
