package org.dromelvan.struts2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.dromelvan.jaxb.trades.Trade;
import org.dromelvan.jaxb.trades.Trades;
import org.dromelvan.jaxb.util.JAXBUtil;
import org.dromelvan.jaxb.util.TradesJAXBUtil;
import org.dromelvan.modell.DeByte;
import org.dromelvan.modell.DeByteOmgang;
import org.dromelvan.modell.Sasong;
import org.dromelvan.modell.Spelare;
import org.dromelvan.modell.SpelareSasong;
import org.dromelvan.modell.TillgangligSpelare;
import org.dromelvan.modell.persistence.SpelareDAO;
import org.dromelvan.modell.persistence.SpelareSasongDAO;
import org.dromelvan.struts2.util.DeltagareMap;
import org.dromelvan.struts2.util.OkandSpelare;
import org.dromelvan.struts2.util.SpelareMap;
import org.xml.sax.SAXException;

public class UploadJAXBDeByten extends UploadJAXBAction<Trades> {

    /**
     *
     */
    private static final long serialVersionUID = -998389271446277026L;
    private DeByteOmgang deByteOmgang;
    private final Set<OkandSpelare> okandSpelareSet = new HashSet<OkandSpelare>();

    public String doExecute() {
        // 2012-13 och framåt vill vi kunna göra detta
        //if(!getDeByteOmgang().getDeByten().isEmpty()) {
        //  addActionError("Byten för omgången har redan lagts in.");
        //  return ERROR;
        //}

        Sasong sasong = getDeByteOmgang().getDeOmgang().getTavling().getSasong();
        SpelareMap spelareMap = new SpelareMap(getDAOFactory().getSpelareDAO().findAll());
        DeltagareMap deltagareMap = new DeltagareMap(getDAOFactory().getDeltagareDAO().findAll());

        Trades trades = getUploadObject();
        List<DeByte> deByteList = new ArrayList<DeByte>();
        for(Trade trade : trades.getTrade()) {
            DeByte deByte = new DeByte();
            deByte.setDeByteOmgang(getDeByteOmgang());
            deByte.setDeltagare(deltagareMap.get(trade.getD11Team()));
            deByte.setSaldSpelare(spelareMap.get(trade.getPlayerOut()));
            deByte.setKoptSpelare(spelareMap.get(trade.getPlayerIn()));
            deByte.setPris((trade.getFee()) / 10.0);
            deByteList.add(deByte);
        }

        SpelareDAO spelareDAO = getDAOFactory().getSpelareDAO();
        SpelareSasongDAO spelareSasongDAO = getDAOFactory().getSpelareSasongDAO();

        //Deltagare ingen = getDAOFactory().getDeltagareDAO().findById(1);
        //for(DeByte deByte : deByteList) {
            //Spelare spelare = deByte.getSaldSpelare();
            // Fr.o.m 2012-13 vill vi inte göra detta eftersom a) detta görs vid transferlistning
            // och b) vi vill kunna ladda upp byten i fler omgångar.
            //if(spelare.getId() > 1) {
            //  SpelareSasong spelareSasong = spelareSasongDAO.findBySpelareOchSasong(spelare,sasong);
            //  spelare.setDeltagare(ingen);
            //  spelareSasong.setDeltagare(ingen);
            //  spelareSasong.setPris(0.0);
            //  spelareDAO.save(spelare);
            //  spelareSasongDAO.save(spelareSasong);
            //}
        //}

        for(DeByte deByte : deByteList) {
            Spelare spelare = deByte.getKoptSpelare();
            if(spelare.getId() > 1) {
                SpelareSasong spelareSasong = spelareSasongDAO.findBySpelareOchSasong(spelare,sasong);
                spelare.setDeltagare(deByte.getDeltagare());
                spelareSasong.setDeltagare(deByte.getDeltagare());
                spelareSasong.setPris(deByte.getPris());
                spelareDAO.save(spelare);
                spelareSasongDAO.save(spelareSasong);
            }
        }

        getDAOFactory().getDeByteDAO().saveBatch(deByteList);

        getSessionManager().commitTransaction();
        getSessionManager().evictCache();

        return SUCCESS;
    }

    @Override
    protected JAXBUtil<Trades> getJAXBUtil() throws SAXException, JAXBException {
        return new TradesJAXBUtil();
    }

    @Override
    protected void validateJAXBObject(Trades trades) {
        getSessionManager().beginTransaction();
        int deByteOmgangId = trades.getTradeDay().intValue();
        setDeByteOmgang(getDAOFactory().getDeByteOmgangDAO().findById(deByteOmgangId));

        DeltagareMap deltagareMap = new DeltagareMap(getDAOFactory().getDeltagareDAO().findAll());
        SpelareMap tillgangligaSpelareMap = new SpelareMap();
        for(TillgangligSpelare tillgangligSpelare : getDeByteOmgang().getTillgangligaSpelare()) {
            tillgangligaSpelareMap.add(tillgangligSpelare.getSpelare());
        }
        tillgangligaSpelareMap.add(getDAOFactory().getSpelareDAO().findById(1));

        for(Trade trade : trades.getTrade()) {
            if(tillgangligaSpelareMap.get(trade.getPlayerIn()) == null) {
                OkandSpelare okandSpelare = new OkandSpelare();
                okandSpelare.setNamn(trade.getPlayerIn());
                addOkandSpelare(okandSpelare);
            }
            if(tillgangligaSpelareMap.get(trade.getPlayerOut()) == null) {
                OkandSpelare okandSpelare = new OkandSpelare();
                okandSpelare.setNamn(trade.getPlayerOut());
                addOkandSpelare(okandSpelare);
            }
            if(deltagareMap.get(trade.getD11Team()) == null) {
                addActionError("Okänd deltagare: " + trade.getD11Team());
            }
        }
        if(!getOkandSpelareSet().isEmpty()) {
            addActionError("Okända spelare.");
        }
    }

    public DeByteOmgang getDeByteOmgang() {
        return deByteOmgang;
    }
    public void setDeByteOmgang(DeByteOmgang deByteOmgang) {
        this.deByteOmgang = deByteOmgang;
    }

    public Set<OkandSpelare> getOkandSpelareSet() {
        return okandSpelareSet;
    }
    protected void addOkandSpelare(OkandSpelare okandSpelare) {
        getOkandSpelareSet().add(okandSpelare);
    }

}
