package org.dromelvan.struts2.test;

import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.persistence.DAOFactory;
import org.dromelvan.modell.persistence.SessionManager;
import org.dromelvan.struts2.util.budhanterare.BudHanterare;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JukitoRunner.class)
public class BudHanterareTest {

    public static class Module extends JukitoModule {
        @Override
        protected void configureTest() {

        }
    }

    @BeforeClass
    public static void beginTransaction() {
        SessionManager.getInstance().beginTransaction();
    }

    @Test
    public void test() {
        DeByteOmgang deByteOmgang = DAOFactory.getInstance().getDeByteOmgangDAO().findById(50);
        System.out.println("Hanterar byteomgång " + deByteOmgang.getNamn() + " " + deByteOmgang.getDeOmgang().getTavling().getSasong().getNamn() + ".");

        BudHanterare budHanterare = new BudHanterare(deByteOmgang);
        budHanterare.hantera();
    }

    @AfterClass
    public static void commitTransaction() {
        SessionManager.getInstance().rollbackTransaction();
    }
}
