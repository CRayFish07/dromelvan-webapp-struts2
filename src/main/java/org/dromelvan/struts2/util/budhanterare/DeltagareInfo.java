package org.dromelvan.struts2.util.budhanterare;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.dromelvan.modell.Bud;
import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.Spelare.Position;

public class DeltagareInfo {

    private Deltagare deltagare;
    private double maxBud;
    private Map<Position, Integer> ledigaPositioner = new HashMap<Position, Integer>();
    private Set<Bud> deltagareBud = new HashSet<Bud>();

    public DeltagareInfo(Deltagare deltagare) {
        this.deltagare = deltagare;
    }

    public Deltagare getDeltagare() {
        return deltagare;
    }
    public void setDeltagare(Deltagare deltagare) {
        this.deltagare = deltagare;
    }

    public double getMaxBud() {
        return maxBud;
    }
    public void setMaxBud(double maxBud) {
        this.maxBud = maxBud;
    }

    public Map<Position, Integer> getLedigaPositioner() {
        return ledigaPositioner;
    }
    public void setLedigaPositioner(Map<Position, Integer> ledigaPositioner) {
        this.ledigaPositioner = ledigaPositioner;
    }

    public Set<Bud> getDeltagareBud() {
        return deltagareBud;
    }
    public void setDeltagareBud(Set<Bud> deltagareBud) {
        this.deltagareBud = deltagareBud;
    }

    public double getReserveratBelopp() {
        int antal = 0;
        for(int positionAntal : ledigaPositioner.values()) {
            antal += positionAntal;
        }
        return 0.5 * antal;
    }

    public void hanteraVinnandeBud(Bud vinnandeBud) {
        setMaxBud(getMaxBud() - vinnandeBud.getAktivtPris() + 0.5);
        System.out.println(" Maxbud för " + getDeltagare().getNamn() + " är nu " + getMaxBud() + ".");

        int lediga = getLedigaPositioner().get(vinnandeBud.getSpelare().getPosition()) - 1;
        getLedigaPositioner().put(vinnandeBud.getSpelare().getPosition(), lediga);

        for(Bud bud : getDeltagareBud()) {
            if(lediga == 0 && bud.getSpelare().getPosition().equals(vinnandeBud.getSpelare().getPosition())) {
                bud.setAktivtPris(0.0);
                System.out.println(" Bud på " + bud.getSpelare().getNamn() + " nollat då position " + bud.getSpelare().getPosition() + " är full.");
            } else if(bud.getAktivtPris() > getMaxBud()) {
                double oldAktivtPris = bud.getAktivtPris();
                bud.setAktivtPris(getMaxBud());
                System.out.println(" Bud på " + bud.getSpelare().getNamn() + " sänktes från " + oldAktivtPris + " till nytt maxbud " + bud.getAktivtPris() + ".");
            }
        }
    }

    @Override
    public String toString() {
        return "Deltagare: " + getDeltagare().getNamn() + " Max: " + getMaxBud() + " Lediga: " + getLedigaPositioner() + " Reserverat: " + getReserveratBelopp();
    }

}
