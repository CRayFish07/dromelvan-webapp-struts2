<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Drömelvan > Tillgängliga spelare - <struts:property value="deByteOmgang.namn"/> <struts:property value="tavling.sasong.namn"/></title>

    <%@ include file="include/head.jsp" %>
    
</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <%@ include file="include/context/byten.jsp" %>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Tillgängliga spelare</h2>

            <h3>Tillgängliga spelare - <struts:property value="deByteOmgang.namn"/> <struts:property value="tavling.sasong.namn"/></h3>

            <div>
                <p>
                    Spelare tillgängliga för byte: <struts:property value="deByteOmgang.datumStr"/>
                </p>
                <struts:if test="!tillgangligaInlagda">
                <struts:url var="url" action="InsertTillgangligaSpelareForm">
                    <struts:param name="deByteOmgangId" value="deByteOmgang.id"/>
                </struts:url>                                                
                <p>
                    OBS: Preliminär lista. Klicka
                    <struts:a href="%{url}">här</struts:a>
                    för att spika tillgängliga spelare denna omgång.
                </p>
                </struts:if>
            </div>

            <table id="tillgangliga_spelare">
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
                    <th class="spelare_lag">Lag</th>
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
                        <%@ include file="include/spelare_lank.jsp" %> <struts:if test="tillgangligSpelare.ny"><img class="ny_tillganglig_spelare" src="bilder/ny.gif"></struts:if>
                        </struts:push>
                    </td>
                    <struts:push value="spelareSasongStatistik">
                    <td class="spelare_lag">
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
                      Pos = Position, M = Matcher, A = Assist, GK = gula kort, RK = röda kort, R = Medelrating,
                      P = Drömelvapoäng
                   </td>
                </tr>                
            </table>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
