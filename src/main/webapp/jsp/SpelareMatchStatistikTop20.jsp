<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Statistik > Top 20 Matchinsatser</title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <%@ include file="include/context/hall_of_fame.jsp" %>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Statistik</h2>

            <h3>Top 20 Matchinsatser</h3>
            <p>
                De 20 bästa insatserna av en spelare i en och samma match. Om
                flera spelare delar 20:de platsen visas alla de spelarna.
            </p>
            <table class="max">
                <colgroup>
                    <col class="spelare_column"/>
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
                    <col class="deltagare_column"/>
                </colgroup>
                <tr>
                    <th class="spelare">Spelare</th>
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
                    <th class="deltagare">Deltagare</th>
                </tr>
                <struts:iterator value="spelareMatchStatistik">
                <tr>
                    <td class="spelare">
                        <struts:push value="spelare">
                        <%@ include file="include/spelare_lank.jsp" %>
                        </struts:push>
                    </td>
                    <td>
                        <struts:url var="url" action="LagSpelarTrupp">
                            <struts:param name="lagId" value="match.hemmaLag.id"/>
                            <struts:param name="sasongId" value="sasongId"/>                            
                        </struts:url>
                        <struts:if test="mobile">
                        <struts:a href="%{url}"><struts:property value="match.hemmaLag.kortKod"/></struts:a>
                        </struts:if>
                        <struts:else>
                        <struts:a href="%{url}"><struts:if test="match.hemmaLag.id == lag.id"><struts:property value="%{getMatch().getHemmaLag().getKod().toUpperCase()}"/></struts:if><struts:else><struts:property value="match.hemmaLag.kod"/></struts:else></struts:a>
                        </struts:else>
                        -
                        <struts:url var="url" action="LagSpelarTrupp">
                            <struts:param name="lagId" value="match.bortaLag.id"/>
                            <struts:param name="sasongId" value="sasongId"/>                            
                        </struts:url>
                        <struts:if test="mobile">
                        <struts:a href="%{url}"><struts:property value="match.bortaLag.kortKod"/></struts:a>
                        </struts:if>
                        <struts:else>                        
                        <struts:a href="%{url}"><struts:if test="match.bortaLag.id == lag.id"><struts:property value="%{getMatch().getBortaLag().getKod().toUpperCase()}"/></struts:if><struts:else><struts:property value="match.bortaLag.kod"/></struts:else></struts:a>
                        </struts:else>
                    </td>
                    <td>
                        <struts:push value="match">
                        <%@ include file="include/match_lank.jsp" %>                            
                        </struts:push>
                    </td>
                    <%@ include file="include/spelare_match_statistik.jsp" %>                                                    
                    <td class="deltagare">                        
                        <struts:push value="deltagare">
                        <%@ include file="include/deltagare_lank.jsp" %>
                        </struts:push>
                    </td>
                </tr>
                </struts:iterator>
                <tr class="tabell_forklaring">
                    <td colspan="13">
                        Res. = Resultat M = Mål SM = Självmål A = Assist GK = Gula kort RK = Röda kort R = Rating P = Poäng                        
                    </td>
                </tr>
            </table>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
