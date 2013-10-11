package org.dromelvan.struts2;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;


public abstract class UploadJAXBAction<T extends Object> extends DromelvaAdminAction {

    /**
     * 
     */
    private static final long serialVersionUID = -3162678098379045193L;    
    private File uploadFile;
    private String uploadFileContentType;
    private String uploadFileFileName;

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
    
    public void validate() {
        Logger.getLogger(UploadAction.class).debug("Validerar " + getUploadFileFileName() + 
                                                   " (" + getUploadFileContentType() + ")");
        if(getUploadFile() == null || getUploadFileContentType() == null || getUploadFileFileName() == null) {
            addActionError("Fil saknas.");
        } else if(!getUploadFileContentType().equals("text/xml")) {
            addActionError("Felaktigt filformat " + getUploadFileContentType() + ".");
        } else {
            try {
                T jaxbObject = parse(getUploadFile());
                validateJAXBObject(jaxbObject);
            } catch(JAXBException e) {
                addActionError("JAXBException. Filen kunde inte parsas.");
                Logger.getLogger(UploadJAXBAction.class).debug("Fel uppstod vid parse()",e);
            }               
        }
    }
    
    protected T parse(File file) throws JAXBException {
        JAXBContext jaxbContext = getJAXBContext();
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        T parsedObject = (T)unmarshaller.unmarshal(getUploadFile());
        return parsedObject;
    }
    
    protected abstract JAXBContext getJAXBContext() throws JAXBException;
        
    protected abstract void validateJAXBObject(T jaxbObject);
    
}
