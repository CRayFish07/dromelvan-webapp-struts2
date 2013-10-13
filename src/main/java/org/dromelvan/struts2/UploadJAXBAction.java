package org.dromelvan.struts2;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.dromelvan.jaxb.util.JAXBUtil;
import org.dromelvan.struts2.util.OkandSpelare;
import org.xml.sax.SAXException;


public abstract class UploadJAXBAction<T extends Object> extends DromelvaAdminAction {

    /**
     *
     */
    private static final long serialVersionUID = -3162678098379045193L;
    private File uploadFile;
    private String uploadFileContentType;
    private String uploadFileFileName;
    private T uploadObject;
    private Set<OkandSpelare> okandSpelareSet = new HashSet<OkandSpelare>();

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

    public T getUploadObject() {
        return uploadObject;
    }
    public void setUploadObject(T uploadObject) {
        this.uploadObject = uploadObject;
    }

    public Set<OkandSpelare> getOkandSpelareSet() {
        return okandSpelareSet;
    }
    protected void addOkandSpelare(OkandSpelare okandSpelare) {
        getOkandSpelareSet().add(okandSpelare);
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
                T jaxbObject = getJAXBUtil().unmarshall(getUploadFile());
                validateJAXBObject(jaxbObject);
                setUploadObject(jaxbObject);
            } catch(SAXException e) {
                addActionError("SAXException. Filen kunde inte parsas.");
                Logger.getLogger(UploadJAXBAction.class).debug("Fel uppstod vid parse()",e);
            } catch(JAXBException e) {
                addActionError("JAXBException. Filen kunde inte parsas.");
                Logger.getLogger(UploadJAXBAction.class).debug("Fel uppstod vid parse()",e);
            }
        }
    }

    protected abstract JAXBUtil<T> getJAXBUtil() throws SAXException, JAXBException ;

    protected abstract void validateJAXBObject(T jaxbObject);

}
