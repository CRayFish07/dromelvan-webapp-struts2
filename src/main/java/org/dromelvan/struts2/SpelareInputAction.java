package org.dromelvan.struts2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.Lag;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.Spelare.Position;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

/**
 * Basklass för actions där man vill lägga in uppgifter om spelare (insert, edit).
 * @author macke
 */
public abstract class SpelareInputAction extends DromelvaAdminAction implements Preparable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3884544477842186013L;

	private Spelare spelare;

	private String fornamn;
	private String efternamn;
	private int lagId;
	private int deltagareId;
	private int position;

	private List<Lag> lagList;
	private List<Deltagare> deltagareList;
	private List<Position> positionList;

	public void prepare() {
		getSessionManager().beginTransaction();

        lagList = getDAOFactory().getLagDAO().findAll();
        Collections.sort(lagList);

        deltagareList = getDAOFactory().getDeltagareDAO().findAll();
        Collections.sort(deltagareList);

		positionList = new ArrayList<Position>();
		for(Position position : Position.values()) {
			if(position != Position.OKAND) {
				positionList.add(position);
			}
		}
	}

    public void validate() {
        String namn = ((getFornamn() != null ? getFornamn().trim() : "") + " " +
                       (getEfternamn() != null ? getEfternamn().trim() : "")).trim();
        if(namn.length() <= 0) {
            addFieldError("namn","Namn saknas");
        } else {
            Spelare spelare = getDAOFactory().getSpelareDAO().findByNamn(namn);
            if(spelare != null) {
                if(getSpelare() == null || spelare.getId() != getSpelare().getId()) {
                    addFieldError("namn","Spelare med givet namn finns redan");
                }
            }
        }
    }

	public Spelare getSpelare() {
		return spelare;
	}
	protected void setSpelare(Spelare spelare) {
		this.spelare = spelare;
	}

    @RequiredFieldValidator(message = "Förnamn saknas", shortCircuit = true)
    public String getFornamn() {
		return fornamn;
	}
	public void setFornamn(String fornamn) {
		this.fornamn = fornamn;
	}

    @RequiredFieldValidator(message = "Efternamn saknas", shortCircuit = true)
	public String getEfternamn() {
		return efternamn;
	}
	public void setEfternamn(String efternamn) {
		this.efternamn = efternamn;
	}

    @ConversionErrorFieldValidator(message = "Felaktigt format på lagId.")
	public int getLagId() {
		return lagId;
	}
	public void setLagId(int lagId) {
		this.lagId = lagId;
	}

    @ConversionErrorFieldValidator(message = "Felaktigt format på deltagareId.")
	public int getDeltagareId() {
		return deltagareId;
	}
	public void setDeltagareId(int deltagareId) {
		this.deltagareId = deltagareId;
	}

    @IntRangeFieldValidator(message = "Felaktig position", shortCircuit = true, min = "1", max = "5")
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}

	public List<Lag> getLagList() {
        return lagList;
	}
	public List<Deltagare> getDeltagareList() {
        return deltagareList;
	}
	public List<Position> getPositionList() {
		return positionList;
	}

}
