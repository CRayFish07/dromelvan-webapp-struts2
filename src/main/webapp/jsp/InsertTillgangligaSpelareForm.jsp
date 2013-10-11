<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Administration > Lägg in tillgängliga spelare för <struts:property value="deByteOmgang.namn"/></title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <h3>Adminfunktioner</h3>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Administration</h2>

            <h3>Lägg in tillgängliga spelare för <struts:property value="deByteOmgang.namn"/></h3>

            <p>
                Följande spelare tillhör ingen deltagare och har vart inne på plan i minst en match
                eller var tillgängliga vid budgivningen. Klicka på 'Lägg in' för att spika denna lista
                som tillgängliga för byte <struts:property value="deByteOmgang.datumStr"/>. Listan
                kan sedan kompletteras med transferlistade spelare.
            </p>
        
            <form id="knappform" method="post" action="<struts:url action="InsertTillgangligaSpelare"/>">
                <p>
                    <struts:submit value="Lägg in"/>
                    <struts:submit method="cancel" value="Avbryt"/>
                </p>
            </form>

            <table>
                <colgroup>
                    <col class="spelare_column"/>
                    <col class="lag_kod_column"/>
                    <col class="position_column"/>
                    <col class="matcher_column"/>
                    <col class="mal_column"/>
                    <col class="assist_column"/>
                    <col class="mom_column"/>
                    <col class="gult_kort_column"/>
                    <col class="rott_kort_column"/>
                    <col class="medel_rating_column"/>
                    <col class="poang_column"/>
                    <col class="deltagare_column"/>
                </colgroup>
                <tr>
                    <th class="spelare">Spelare</th>
                    <th>Lag</th>
                    <th>Pos</th>
                    <th>M.</th>
                    <th>Mål</th>
                    <th>A.</th>
                    <th>MoM</th>
                    <th>GK</th>
                    <th>RK</th>
                    <th>R.</th>
                    <th>P.</th>
                    <th class="deltagare">Listad av</th>
                </tr>
                
                <struts:iterator value="tillgangligSpelareStatistik">
                <tr>
                    <td class="spelare">
                        <struts:push value="tillgangligSpelare.spelare">
                        <%@ include file="include/spelare_lank.jsp" %> <struts:if test="tillgangligSpelare.ny"><img src="bilder/ny.gif"></struts:if>
                        </struts:push>
                    </td>
                    <struts:push value="spelareSasongStatistik">
                    <td>
                        <struts:url var="url" action="LagSpelarTrupp">
                            <struts:param name="lagId" value="lag.id"/>
                            <struts:param name="sasongId" value="sasong.id"/>
                        </struts:url>            
                        <struts:a href="%{url}"><struts:property value="lag.kod"/></struts:a>              
                    </td>
                    <td><struts:property value="position.kod"/></td>
                    <%@ include file="include/spelare_sasong_statistik.jsp" %>
                    </struts:push>
                    <td class="deltagare">
                        <struts:push value="tillgangligSpelare.deltagare">
                        <%@ include file="include/deltagare_lank.jsp" %>
                        </struts:push>
                    </td>
                </tr>
                </struts:iterator>
                <tr class="tabell_forklaring">
                    <td colspan="12">
                        Pos = Position, M = Matcher, GK = gula kort, RK = röda kort, R = Medelrating,
                        P = Drömelvapoäng
                    </td>
                </tr>                                
            </table>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
