package org.dromelvan.struts2;

import org.dromelvan.modell.Anvandare;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

public class EditPassword extends DromelvaAdminAction {

	/**
	 *
	 */
	private static final long serialVersionUID = -1879342118209329395L;
	private String password;
	private String password2;

	@Override
	public boolean isRequiresAdministrator() {
	    return false;
	}

	public String doExecute() {
		if(!getPassword().equals(getPassword2())) {
            addFieldError("password","Lösenorden matchar inte");
            return ERROR;
        }
		getSessionManager().beginTransaction();
		Anvandare anvandare = getDromelvaSession().getAnvandare();
		anvandare.setPassword(new StrongPasswordEncryptor().encryptPassword(getPassword()));
		anvandare = getDAOFactory().getAnvandareDAO().save(anvandare);
		getSessionManager().commitTransaction();

		getDromelvaSession().setAnvandare(anvandare);
		return SUCCESS;
	}

	@RequiredStringValidator(message = "Lösenord saknas")
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}

	@RequiredStringValidator(message = "Lösenord saknas")
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getPassword2() {
		return password2;
	}
}
