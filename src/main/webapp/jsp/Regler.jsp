<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Information > Regler 2014-2015</title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <h3>Regler</h3>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Information</h2>

            <h3>Regler 2014-15</h3>
            <p>
                Regel #1: Då en situation uppstår som inte uttryckligen behandlas
                i de övriga reglerna men där vi har en praxis eller ett prejudikat från hur vi gjort
                tidigare, så gäller det som en regel.
            </p>
            <p>
                De matcher som ingår i tävlingen är matcherna i engelska Premier League.
            </p>
            <p>
                Flyttade matcher kommer att räknas in i den omgång de ursprungligen hörde till.
                Exempel: Omgång 5 spelas 10 november. Matchen Arsenal-Aston Villa flyttas till 5 mars.
                Resultatet räknas in i omgång 5 i efterhand.
            </p>

            <h4>Poängsystemet</h4>
            <p>
                Deltagarna möter varandras lag precis som lagen i Premier League.
                Den som gör flest mål i matchen får 3 poäng, förloraren får 0 poäng
                och vid oavgjort blir det 1 poäng var.
            </p>
            <p>
                Mål ges enligt antal *virtuella poäng:
            </p>

            <table id="poang_system">
                <colgroup>
                    <col id="virtuella_poang_column"/>
                    <col id="mal_column"/>
                </colgroup>
                <tr>
                    <th>Virtuella poäng</th>
                    <th>Mål</th>
                </tr>
                <tr>
                    <td>Under 1</td>
                    <td>0</td>
                </tr>
                <tr>
                    <td>1-4</td>
                    <td>1</td>
                </tr>
                <tr>
                    <td>5-9</td>
                    <td>2</td>
                </tr>
                <tr>
                    <td>10-14</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>15-19</td>
                    <td>4</td>
                </tr>
                <tr>
                    <td>20-24</td>
                    <td>5</td>
                </tr>
                <tr>
                    <td>25-29</td>
                    <td>6</td>
                </tr>
                <tr>
                    <td>30-34</td>
                    <td>7</td>
                </tr>
                <tr>
                    <td>O.s.v</td>
                    <td>O.s.v</td>
                </tr>
            </table>

            <p>
                Den som får flest poäng vinner tävlingen. Om deltagarna står på lika
                poäng vinner den som har flest gjorda mål. Är det fortfarande oavgjort
                vinner den som har flest insläppta mål. Sedan följer straffsparksläggning
                på Nabbens IP för att avgöra vinnaren.
            </p>
            <p>
                Matcherna lottas efter budgivningen och "matchprogrammet" spikas upp på
                tävlingens internetsida.
            </p>
            <p>
                OBS! Nyhet: All matchstatistik inklusive ratings tas från www.whoscored.com. Whoscored.coms rating
                avrundas till närmsta jämna tal. .5 avrundas uppåt. Om whoscored.com
                misslyckas med att få upp statistik tas matchstatistik från www.premierleague.com
                och ratings från www.skysports.com.
            </p>
            <p>
                Stjärnratingen tilldelas på följande sätt: Om en
                spelare ensam har högsta ratingen i sitt lag så blir han Man of the Match (MoM).
                Om två spelare har högsta ratingen i sitt lag blir de delad MoM. Om tre eller
                flera spelare har högsta ratingen i sitt lag så blir det ingen MoM utmärkelse
                för någon i laget.
            </p>

            <h4>Virtuella poäng</h4>

            <p>
                Varje spelare får poäng för sin insats i en match enligt följande tabell.
            </p>

            <table id="virtuella_poang">
                <colgroup>
                    <col id="poanggrund_alla_column"/>
                    <col id="poang_alla_column"/>
                    <col id="poanggrund_forsvarare_column"/>
                    <col id="poang_forsvarare_column"/>
                </colgroup>
                <tr>
                    <th class="poanggrund">Poänggrund</th>
                    <th>Alla</th>
                    <th class="poanggrund">Poänggrund</th>
                    <th>Mv/back</th>
                </tr>
                <tr>
                    <td class="poanggrund">Rating 10</td>
                    <td>5</td>
                    <td class="poanggrund">1 insläppt mål</td>
                    <td>OBS! Nyhet: 0</td>
                </tr>
                <tr>
                    <td class="poanggrund">Rating 9</td>
                    <td>3</td>
                    <td class="poanggrund">2 insläppta mål</td>
                    <td>OBS! Nyhet: -2</td>
                </tr>
                <tr>
                    <td class="poanggrund">Rating 8</td>
                    <td>2</td>
                    <td class="poanggrund">3 insläppta mål</td>
                    <td>OBS! Nyhet: -3</td>
                </tr>
                <tr>
                    <td class="poanggrund">Rating 7</td>
                    <td>1</td>
                    <td class="poanggrund">4 insläppta mål</td>
                    <td>OBS! Nyhet: -4</td>
                </tr>
                <tr>
                    <td class="poanggrund">Ingen rating</td>
                    <td>0</td>
                    <td class="poanggrund">O.s.v</td>
                    <td>O.s.v</td>
                </tr>
                <tr>
                    <td class="poanggrund">Rating 6</td>
                    <td>0</td>
                    <td class="poanggrund">Håller nollan</td>
                    <td>4</td>
                </tr>
                <tr>
                    <td class="poanggrund">Rating 5</td>
                    <td>-1</td>
                    <td class="poanggrund">Ej spelat</td>
                    <td>-1</td>
                </tr>
                <tr>
                    <td class="poanggrund">Rating 4</td>
                    <td>-2</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td class="poanggrund">Rating 3</td>
                    <td>-3</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td class="poanggrund">Rating 2</td>
                    <td>-4</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>                    
                </tr>
                <tr>
                    <td class="poanggrund">Rating 1</td>
                    <td>-5</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td class="poanggrund">Rating 0</td>
                    <td>-6</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>                
                <tr>
                    <td class="poanggrund">MoM</td>
                    <td>4</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td class="poanggrund">Delad MoM</td>
                    <td>2</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td class="poanggrund">Mål</td>
                    <td>4</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td class="poanggrund">Assist</td>
                    <td>2</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>                    
                <tr>
                    <td class="poanggrund">Självmål</td>
                    <td>-4</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td class="poanggrund">Ej spelat - mf/anf</td>
                    <td>0</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td class="poanggrund">Gult kort</td>
                    <td>-1</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td class="poanggrund">Rött kort</td>
                    <td>-4</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
 
            <p>
                OBS! Nyhet: Eftersom Whoscored.com startar sina ratings från 6.00 så får spelare poäng för
                ratings oberoende av hur många minute de spelat.
            </p>

            <h4>Anmälningsavgift</h4>
            
            <ul>
                <li>Anmälningsavgiften är 40 euro. Denna summa går oavkortat till prispengar
                    för de deltagare som kommer på första, andra och tredje plats. De exakta
                    summorna som betalas ut till var och en beror på antalet deltagare. Om vi
                    har 20 deltagare blir prissummorna 400, 250 och 150 euro.
                </li>
            </ul>
            
            <h4>Budgivningen</h4>

            <ul>
                <li>Preliminärt datum för budgivningen är någon gång just före säsongen börjar. Plats och
                    tidpunkt meddelas senare.</li>
                <li>Kom i god tid! Laget man då skrapat ihop är i aktion första
                    gången lördagen den 16 augusti. Preliminär spelarlista finns
                    att tillgå på denna website.</li>
                <li>Ordinarie lista ges vid budgivningen.</li>
                <li>Antalet deltagare i tävlingen kan vara högst 20 eftersom det
                    inte finns fler lag i Premier League. Ifall fler än 20 önskar
                    deltaga så kan så många som det behövs bilda lag.</li>
                <li>Budgivning hålls i princip som en auktion. Förra säsongens segrare
                    får först välja en spelare att bjuda på. Han kan välja
                    vilken spelare som helst från listan. Sedan är det fri auktion
                    (vem som helst får höja budet utan turordning) på spelaren och
                    högsta budet får spelaren i sitt lag. Man kan naturligtvis inte
                    ångra sig när man en gång fått en spelare.</li>
                <li>Ingen kommer att ha samma spelare i sitt lag som någon annan.</li>
                <li>Alla deltagare har £50 miljoner att spendera på sitt lag.</li>
                <li>Det finns inga begränsningar på hur många spelare från varje lag man har.</li>
                <li>Det finns inga givna priser på spelarna, förutom att minimipriset på
                    alla spelare är 0,5. Man måste även bjuda minst 0,5 mer än nuvarande
                    högsta bud ifall man vill bjuda över.</li>
                <li>Laget skall bestå av målvakt, 4 försvarare, 4 mittfältare och 2 anfallare (4-4-2).</li>
                <li>Det är viktigt att hålla koll på att man inte bjuder mer än man
                    har råd med. Se alltså till att du åtminstone har kvar så mycket
                    pengar att du får ihop ett lag! Säkert händer det att någon ändå
                    bjuder över sina tillgångar. Det kontrolleras varje gång någon
                    fått hem en spelare. Har så skett så blir det ombjudning på just
                    den aktuella spelaren.</li>
            </ul>

            <h4>OBS! Nyhet: Bytessystemet</h4>
            <ul>
                <li>
                    Transferfönster öppnar i början av månaderna oktober, december, februari och april.
                    Exakta datum meddelas närmare bytestillfället.
                </li>
                <li>
                    OBS! Nyhet: När som helst före transferfönstret öppnar kan du logga in på siten och transferlista spelare.
                    Spelarna kommer tillsvidare att vara kvar i ditt lag och före transferfönstret stänger kan du kan även ångra 
                    alla transferlistningar du gjort. Endast du själv kan se vilka spelare som finns på din transferlista före
                    transferfönstret öppnar.
                </li>
                <li>
                    OBS! Nyhet: Då transferfönstret öppnar kommer alla spelare du transferlistat att försvinna från ditt lag.
                    Då detta är gjort är det får sent att ångra sig. Se till att inga spelare du vill ha kvar i ditt lag är 
                    transferlistade då deadline för transferlistning passerar!
                </li>                
                <li>
                    Alla spelare du transferlistat är fr.o.m transferlistningen borta ur ditt lag.
                    För att få behålla en listad spelare måste du alltså vinna budgivningen på honom igen.
                </li>                
                <li>
                    OBS! Nyhet: Då transferfönstret öppnar kommer du att kunna logga in och ge bud på tillgängliga spelare. Tillgängliga
                    spelare är de spelare som inte tillhörde någon deltagare före transferfönstret öppnade samt alla transferlistade spelare.
                    Du kan alltså även bjuda på de spelare du själv transferlistat.
                </li>                
                <li>Precis som vid budgivningen kan man bjuda och "höja" minst 0.5. Alla bud skall således
                    sluta på .0 eller .5. Observera att det högsta bud du kan ge på en spelare måste ta i
                    beaktande att man måste betala minst 0.5 på varje position. Om man har listat tre
                    spelare och har 10.0 att spendera är alltså det högsta budet på en enskild spelare
                    som kan lyckas 9.0. OBS! Nyhet: Maxbudet kommer att visas på sidan där du skickar in dina bud.</li>                                
                <li>
                    OBS! Nyhet: Före deadline för bud passerat kan du ändra och/eller ta bort inskickade bud. Efter att deadline för bud
                    passerat är det för sent att ändra eller lägga till. Se till att dina bud ser ut som du har tänkt före deadline för
                    bud passeras.
                </li>                                
                <li>
                    OBS! Nyhet: Alla bud läggs i en hög och om man t.ex behöver två mittfältare så får du de två första mittfältarna
                    du först lyckas få lyckade bud på.
                </li>                
                <li>
                    OBS! Nyhet: Du får bara lägga ett bud på varje spelare. Detta bud är ett "upp till" bud, dvs maxbudet du vill ge för spelaren. 
                    Om du har det högsta budet blir summan du till slut får betala 0.5 högre än det näst högsta budet. Ex: Deltagare A bjuder 15.0 för 
                    spelare S. Det näst högsta budet är deltagare B som bjuder 10.0. Deltagare A får spelare S för 10.5.                    
                </li>
                <li>
                    OBS! Nyhet: Spelarna "bjuds ut" i poängordning. Den tillgängliga spelare som har mest poäng på buddagen går som första spelare 
                    till den deltagare som hade högsta budet på honom. Sedan går den spelaren som hade näst mest poäng till den deltagare som hade 
                    högsta budet på honom, osv, osv. Om två spelare har lika många poäng bjuds de ut i bokstavsordning. Listan på tillgängliga spelare
                    för varje transferfönster är sorterad enligt den ordning spelarna kommer att bjudas ut.
                </li>
                <li>Vem får spelaren? Den som har högsta budet på en spelare får honom.
                    Om budet är lika får den deltagare som ligger sämre till i
                    tävlingen spelaren.</li>                
                <li>
                    OBS! Nyhet: Pengar som spenderats på lyckade bud tas bort från bud som inte hanteras än. Ex: Deltagare A har 15.0 som maxbud och 
                    bjuder 15.0 på spelare S och 15.0 på spelare P. Spelare S har mer poäng än spelare P och går först och deltagare A får honom för 10.5. 
                    Efter det är deltagare As maxbud 4.5, så budet på spelare P justeras ner till 4.5.
                </li>
                <li>Om man inte lyckats få ett enda av de alternativ man bjudit på så
                    får man försöka igen nästa dag. Då får alla som inte fått ersättare skicka
                    in nya bud på spelare för de positioner de fortfarande behöver spelare på. Om
                    man fortfarande inte fått ersättare upprepas produren en gång till nästa dag,
                    osv, osv tills dess alla fått fullt lag. Deadline för att hinna få med sina byten
                    varje bytesdag är kl 22.00 den dagen.</li>
                <li>Om en omgång i Premier League har hunnit börja innan alla har hunnit få fullt
                    lag så spelar de olyckliga deltagarna med försvagat manskap i den omgången. Vi kommer att
                    försöka välja listningsdatum och budgivningsdatum så att det finns tillräckligt många
                    dagar för att de som vill ha fullt lag skall kunna få det.</li>
                <li>Om du köpt en spelare till t ex omgång 30 och hans lag har spelat
                    matchen i omgång 30 tidigare, så kommer den nya spelarens
                    poäng att räknas.</li>
            </ul>
            <p>
                Fråga gärna om det är något du funderar över!
            </p>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
