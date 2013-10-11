package org.dromelvan.struts2;

import org.dromelvan.modell.Nyhet;
import org.dromelvan.modell.persistence.NyhetDAO;




public class DeleteNyhet extends DromelvaAdminAction {

    /**
	 *
	 */
	private static final long serialVersionUID = -5598190429168604454L;
	private int nyhetId;

	public String doExecute() {
        getSessionManager().beginTransaction();

        NyhetDAO nyhetDAO = getDAOFactory().getNyhetDAO();
        Nyhet nyhet = nyhetDAO.findById(getNyhetId());
        nyhetDAO.delete(nyhet);

        getSessionManager().commitTransaction();
		return SUCCESS;
	}

    public void setNyhetId(int nyhetId) {
        this.nyhetId = nyhetId;
    }
    public int getNyhetId() {
        return nyhetId;
    }
}
