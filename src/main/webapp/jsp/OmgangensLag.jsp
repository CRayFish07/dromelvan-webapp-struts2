<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Premier League > Omgångens lag - <struts:property value="omgang.namn"/> <struts:property value="omgang.tavling.sasong.namn"/></title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <h3>Omgångens lag</h3>
            <h4 class="top">Omgångens lag</h4>
            <ul>
                <struts:url var="url" action="OmgangensLag">
                    <struts:param name="omgangId" value="omgang.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Omgångens lag</struts:a></li>
                <struts:url var="url" action="OmgangensSkamLag">
                    <struts:param name="omgangId" value="omgang.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Omgångens skamlag</struts:a></li>
                
                <struts:if test="foregaendeOmgang.isInlagd">
                <struts:url var="url" action="OmgangensLag">
                    <struts:param name="omgangId" value="foregaendeOmgang.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Föregående omgang</struts:a></li>
                </struts:if>

                <struts:if test="nastaOmgang.isInlagd">
                <struts:url var="url" action="OmgangensLag">
                    <struts:param name="omgangId" value="nastaOmgang.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Nästa omgang</struts:a></li>
                </struts:if>
               
            </ul>
            
            <h4 class="sasong_menu">Säsong <struts:property value="omgang.tavling.sasong.namn"/></h4>           
            <ul class="sasong_menu">
                <struts:url var="url" action="PremierLeague" anchor="%{omgang.id}">
                    <struts:param name="tavlingId" value="omgang.tavling.id"/>
                    <struts:param name="startOmgang" value="omgang.startOmgangNummer"/>
                </struts:url>            
                <li><struts:a href="%{url}"><struts:property value="omgang.namn"/></struts:a></li>                
                <struts:url var="url" action="PremierLeague">
                    <struts:param name="tavlingId" value="omgang.tavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Matcher</struts:a></li>
                <struts:url var="url" action="PremierLeagueTabell">
                    <struts:param name="tavlingId" value="omgang.tavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Tabell</struts:a></li>                                
            </ul>
            
            <h4 class="big_menu">Säsonger</h4>            
            <ul class="big_menu">
                <struts:iterator value="tavlingar">
                <struts:url var="url" action="PremierLeague">
                    <struts:param name="tavlingId" value="id"/>
                </struts:url>            
                <li><struts:a href="%{url}"><struts:property value="sasong.namn"/></struts:a></li>                
                </struts:iterator>
            </ul>            
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Premier League</h2>

            <h3>Omgångens lag - <struts:property value="omgang.namn"/> <struts:property value="omgang.tavling.sasong.namn"/></h3>

            <div class="lag_header">
                <img class="lag_klubbmarke" src="bilder/omgangens_lag.gif" alt="Omgångens lag"/>
                <table>
                    <colgroup>
                        <col class="vanster_column"/>
                        <col class="hoger_column"/>
                    </colgroup>
                    <tr>
                        <th class="namn" colspan="2">
                            Omgångens lag
                        </th>
                    </tr>
                    <tr>
                        <td>Poäng: <struts:property value="omgangensLagStatistik.poang"/></td>
                        <td>Mål: <struts:property value="omgangensLagStatistik.antalMal"/></td>
                    </tr>
                    <tr>
                        <td>Gula kort: <struts:property value="omgangensLagStatistik.antalGultKort"/></td>
                        <td>Röda kort: <struts:property value="omgangensLagStatistik.antalRottKort"/></td>
                    </tr>
                    <tr>
                        <td>MoM: <struts:property value="omgangensLagStatistik.antalMom"/></td>
                        <td>Delad MoM: <struts:property value="omgangensLagStatistik.antalDeladMom"/></td>
                    </tr>
                    <tr>
                        <td>Medelrating: <struts:property value="omgangensLagStatistik.medelRating"/></td>
                        <td>Medelpoäng: <struts:property value="omgangensLagStatistik.medelPoang"/></td>
                    </tr>
                </table>
            </div>

            <table id="omgangens_lag">
                <colgroup>
                    <col class="spelare_column"/>
                    <col class="position_column"/>
                    <col class="match_column"/>
                    <col class="resultat_column"/>
                    <col class="mal_column"/>
                    <col class="sjalvmal_column"/>
                    <col class="assist_column"/>
                    <col class="gult_kort_column"/>
                    <col class="rott_kort_column"/>
                    <col class="inbytt_column"/>
                    <col class="utbytt_column"/>
                    <col class="mom_column"/>
                    <col class="rating_column"/>
                    <col class="poang_column"/>
                    <struts:if test="mobile == false">
                    <col class="deltagare_column"/>
                    </struts:if>
                </colgroup>
                <tr>
                    <th class="spelare">Spelare</th>
                    <th>Pos</th>
                    <th>Match</th>
                    <th>Res.</th>
                    <th>M</th>
                    <th>SM</th>
                    <th>A</th>
                    <th>GK</th>
                    <th>RK</th>
                    <th>In</th>
                    <th>Ut</th>
                    <th>MoM</th>
                    <th>R</th>
                    <th>P</th>
                    <struts:if test="mobile == false">
                    <th class="deltagare">Deltagare</th>
                    </struts:if>
                </tr>
                    
                <struts:iterator value="omgangensLagStatistik.spelareMatchStatistik">
                <tr>
                    <td class="spelare">
                        <struts:push value="spelare">
                        <%@ include file="include/spelare_lank.jsp" %>
                        </struts:push>
                    </td>
                    <td><struts:property value="position.kod"/></td>
                    <td>
                        <struts:url var="url" action="LagSpelarTrupp">
                            <struts:param name="lagId" value="match.hemmaLag.id"/>
                            <struts:param name="sasongId" value="sasongId"/>                            
                        </struts:url>
                        <struts:a href="%{url}"><struts:if test="match.hemmaLag.id == lag.id"><struts:property value="%{getMatch().getHemmaLag().getKod().toUpperCase()}"/></struts:if><struts:else><struts:property value="match.hemmaLag.kod"/></struts:else></struts:a>                            
                        -
                        <struts:url var="url" action="LagSpelarTrupp">
                            <struts:param name="lagId" value="match.bortaLag.id"/>
                            <struts:param name="sasongId" value="sasongId"/>                            
                        </struts:url>            
                        <struts:a href="%{url}"><struts:if test="match.bortaLag.id == lag.id"><struts:property value="%{getMatch().getBortaLag().getKod().toUpperCase()}"/></struts:if><struts:else><struts:property value="match.bortaLag.kod"/></struts:else></struts:a>
                    </td>
                    <td>
                        <struts:push value="match">
                        <%@ include file="include/match_lank.jsp" %>                            
                        </struts:push>
                    </td>
                    <%@ include file="include/spelare_match_statistik.jsp" %>
                    <struts:if test="mobile == false">
                    <td class="deltagare">                        
                        <struts:push value="deltagare">
                        <%@ include file="include/deltagare_lank.jsp" %>
                        </struts:push>
                    </td>
                    </struts:if>
                </tr>
                </struts:iterator>
                <tr class="tabell_forklaring">
                    <td colspan="14">
                        Res. = Resultat M = Mål SM = Självmål A = Assist GK = Gula kort RK = Röda kort R = Rating P = Poäng
                    </td>
                </tr>
            </table>

        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
