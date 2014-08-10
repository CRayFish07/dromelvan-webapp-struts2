package org.dromelvan.struts2.util.budhanterare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dromelvan.modell.Bud;
import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.Deltagare;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.Spelare.Position;
import org.dromelvan.modell.SpelareSasong;
import org.dromelvan.modell.persistence.DAOFactory;
import org.dromelvan.modell.persistence.SpelareSasongDAO;
import org.dromelvan.modell.statistik.spelartrupp.DeltagareSpelarTrupp;

public class BudHanterare {

    private DeByteOmgang deByteOmgang;
    private List<Spelare> spelareList = new ArrayList<Spelare>();
    private Map<Deltagare, DeltagareInfo> deltagareInfoMap = new HashMap<Deltagare, DeltagareInfo>();
    private Map<Spelare, List<Bud>> spelareBudMap = new HashMap<Spelare, List<Bud>>();

    public BudHanterare(DeByteOmgang deByteOmgang) {
        this.deByteOmgang = deByteOmgang;
        Sasong sasong = deByteOmgang.getDeOmgang().getTavling().getSasong();
        SpelareSasongDAO spelareSasongDAO = DAOFactory.getInstance().getSpelareSasongDAO();

        for(Bud bud : deByteOmgang.getBud()) {
            Deltagare deltagare = bud.getDeltagare();
            DeltagareInfo deltagareInfo = deltagareInfoMap.get(deltagare);
            if(deltagareInfo == null) {
                deltagareInfo = new DeltagareInfo(deltagare);

                List<SpelareSasong> spelareSasonger = spelareSasongDAO.findByDeltagareOchSasong(deltagare, sasong);
                DeltagareSpelarTrupp deltagareSpelarTrupp = new DeltagareSpelarTrupp(deltagare,sasong,spelareSasonger);

                deltagareInfo.setMaxBud(deltagareSpelarTrupp.getStorstaBud());
                for(Position position : Position.values()) {
                    deltagareInfo.getLedigaPositioner().put(position, deltagareSpelarTrupp.getAntalSaknadeSpelare(position));
                }

                deltagareInfoMap.put(deltagare, deltagareInfo);
            }
            deltagareInfo.getDeltagareBud().add(bud);

            Spelare spelare = bud.getSpelare();
            if(!spelareList.contains(spelare)) {
                spelareList.add(spelare);
            }
            List<Bud> spelareBudList = spelareBudMap.get(spelare);
            if(spelareBudList == null) {
                spelareBudList = new ArrayList<Bud>();
                spelareBudMap.put(spelare, spelareBudList);
            }
            spelareBudList.add(bud);
        }

        for(DeltagareInfo deltagareInfo : deltagareInfoMap.values()) {
            System.out.println(deltagareInfo);
            for(Bud bud : deltagareInfo.getDeltagareBud()) {
                System.out.println("  Bud på " + bud.getSpelare().getNamn() + ": " + bud.getAktivtPris());
            }
        }
    }

    public void hantera() {
        for(Spelare spelare : spelareList) {
            List<Bud> budList = spelareBudMap.get(spelare);
            if(!budList.isEmpty()) {
                System.out.println("Hanterar bud för: " + spelare.getNamn() + " (Plats " + budList.iterator().next().getPrioritet() + " i poängtabellen).");

                Collections.sort(budList);

                for(Bud bud : budList) {
                    System.out.println("   " + bud.getDeltagare().getNamn() + " Bud: " + bud.getPris() + " Aktivt bud: " + bud.getAktivtPris());
                }

                Bud vinnandeBud = budList.get(0);
                double nastHogstaBud = 0.0;
                if(budList.size() > 1) {
                    nastHogstaBud = budList.get(1).getAktivtPris();
                }
                if(vinnandeBud.getAktivtPris() > nastHogstaBud) {
                    vinnandeBud.setAktivtPris(nastHogstaBud + 0.5);
                }

                System.out.println(" Vinnande bud: " + vinnandeBud.getDeltagare().getNamn() + " Aktivt bud: " + vinnandeBud.getAktivtPris());
                for(Bud bud : budList) {
                    DeltagareInfo deltagareInfo = deltagareInfoMap.get(bud.getDeltagare());
                    deltagareInfo.getDeltagareBud().remove(bud);
                }

                DeltagareInfo deltagareInfo = deltagareInfoMap.get(vinnandeBud.getDeltagare());
                deltagareInfo.hanteraVinnandeBud(vinnandeBud);
                vinnandeBud.setLyckat(true);

                for(Bud bud : deltagareInfo.getDeltagareBud()) {
                    if(bud.getAktivtPris() == 0.0) {
                        spelareBudMap.get(bud.getSpelare()).remove(bud);
                    }
                }
            }
        }

        System.out.println("Lyckade bud:");
        for(Bud bud : deByteOmgang.getBud()) {
            if(bud.isLyckat()) {
                System.out.println(bud.getDeltagare().getNamn() + " Spelare: " + bud.getSpelare().getNamn() + " Bud: " + bud.getAktivtPris());
            }
        }

    }

}
