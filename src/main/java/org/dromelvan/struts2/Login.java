package org.dromelvan.struts2;

import org.dromelvan.modell.Anvandare;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;


public class Login extends DromelvaActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = -5604268983511526371L;
	private String login;
	private String password;
    private String loginRedirectUrl = "Administration.action";

	public String doExecute() {
		getSessionManager().beginTransaction();
		Anvandare anvandare = getDAOFactory().getAnvandareDAO().findByLogin(login);
		if(anvandare != null && anvandare.isAdministrator()) {
			if(anvandare.getPassword().equals("")) {
			    getDromelvaSession().setAnvandare(anvandare);
				// Nyskapad användare har tomt password, bör ändra det direkt då de loggat in
				return SUCCESS;
			}
            StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
            if(passwordEncryptor.checkPassword(password,anvandare.getPassword())) {
                getDromelvaSession().setAnvandare(anvandare);
                return SUCCESS;
            }
		}
		addFieldError("login","Felaktig användare/lösenord");
		return ERROR;
	}

	@RequiredStringValidator(message = "Användare saknas")
	public void setLogin(String login) {
		this.login = login;
	}
	public String getLogin() {
		return login;
	}

	@RequiredStringValidator(message = "Lösenord saknas")
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}

    public void setLoginRedirectUrl(String loginRedirectUrl) {
    	this.loginRedirectUrl = loginRedirectUrl;
    }
    public String getLoginRedirectUrl() {
    	return loginRedirectUrl;
    }
}
