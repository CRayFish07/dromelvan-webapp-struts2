package org.dromelvan.struts2;

import java.util.Date;

import org.dromelvan.modell.Nyhet;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;



public class EditNyhet extends InsertNyhet {

    /**
	 *
	 */
	private static final long serialVersionUID = 2637483419833554997L;
	private Nyhet nyhet;
    private int nyhetId;

    public String input() {
        clearWorkFlowObject();

        getSessionManager().beginTransaction();
        setNyhet(getDAOFactory().getNyhetDAO().findById(getNyhetId()));
        setRubrik(getNyhet().getRubrik());
        setMeddelande(getNyhet().getMeddelande());

        setWorkFlowObject(getNyhet());

        return INPUT;
    }

	public String doExecute() {
        getSessionManager().beginTransaction();

        Nyhet nyhet = getDAOFactory().getNyhetDAO().findById(getNyhet().getId());
        nyhet.setAnvandare(getDromelvaSession().getAnvandare());
        nyhet.setRubrik(getRubrik());
        nyhet.setMeddelande(getMeddelande());
        nyhet.setTidpunkt(new Date());
        nyhet = getDAOFactory().getNyhetDAO().save(nyhet);
        setNyhet(nyhet);

        getSessionManager().commitTransaction();
        clearWorkFlowObject();
		return SUCCESS;
	}

    public boolean validateWorkFlowObject() {
        try {
            setNyhet((Nyhet)getWorkFlowObject());
        } catch(ClassCastException e) {}
        return getNyhet() != null;
    }

    public Nyhet getNyhet() {
        return nyhet;
    }
    private void setNyhet(Nyhet nyhet) {
        this.nyhet = nyhet;
    }

    @ConversionErrorFieldValidator(message = "Felaktigt format på nyhetId.")
    public void setNyhetId(int nyhetId) {
        this.nyhetId = nyhetId;
    }
    public int getNyhetId() {
        return nyhetId;
    }

}
