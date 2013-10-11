<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Premier League > <struts:property value="match.hemmaLag.namn"/> - <struts:property value="match.bortaLag.namn"/> (<struts:property value="match.omgang.tavling.sasong.namn"/>)</title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <%@ include file="include/context/match.jsp" %>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Premier League</h2>

            <h3 class="match_rubrik">Matchstatistik: <struts:property value="match.hemmaLag.namn"/> - <struts:property value="match.bortaLag.namn"/> (<struts:property value="match.omgang.tavling.sasong.namn"/>)</h3>
            <%@ include file="include/match_header.jsp" %>
            <%@ include file="include/match_statistik_resultat_table.jsp" %>

            <struts:if test="match.isInlagd">            
            <div id="lag_dromelvastatistik">
                <h4 id="match_lag_rubrik"><struts:property value="match.hemmaLag.namn"/></h4>

                <table class="top">
                    <colgroup>
                        <col class="spelare_column"/>
                        <col class="position_column"/>
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
                        <th class="spelare">Startuppställning</th>
                        <th>Pos</th>
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
                    <struts:iterator value="startuppstallning">
                    <struts:if test="!dummy">
                    <tr>
                        <struts:push value="hemmaLagSpelare">                        
                        <%@ include file="include/spelare_dromelva_match_statistik.jsp" %>
                        </struts:push>
                    </tr>
                    </struts:if>
                    </struts:iterator>
                    <tr>
                        <th class="spelare">Reserver</th>
                        <th>Pos</th>
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
                    <struts:iterator value="reserver">
                    <struts:if test="!dummy">
                    <tr>
                        <struts:push value="hemmaLagSpelare">                        
                        <%@ include file="include/spelare_dromelva_match_statistik.jsp" %>
                        </struts:push>
                    </tr>
                    </struts:if>
                    </struts:iterator>                    
                </table>
            </div>

            <div id="lag_dromelvastatistik">
                <h4><struts:property value="match.bortaLag.namn"/></h4>

                <table class="top">
                    <colgroup>
                        <col class="spelare_column"/>
                        <col class="position_column"/>
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
                        <th class="spelare">Startuppställning</th>
                        <th>Pos</th>
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
                    <struts:iterator value="startuppstallning">
                    <struts:if test="!dummy">
                    <tr>
                        <struts:push value="bortaLagSpelare">                        
                        <%@ include file="include/spelare_dromelva_match_statistik.jsp" %>
                        </struts:push>
                    </tr>
                    </struts:if>
                    </struts:iterator>
                    <tr>
                        <th class="spelare">Reserver</th>
                        <th>Pos</th>
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
                    <struts:iterator value="reserver">
                    <struts:if test="!dummy">
                    <tr>
                        <struts:push value="bortaLagSpelare">                        
                        <%@ include file="include/spelare_dromelva_match_statistik.jsp" %>
                        </struts:push>
                    </tr>
                    </struts:if>
                    </struts:iterator>                    
                </table>
            </div>
            </struts:if>
            <struts:else>
            <p>
                Matchstatistik saknas för den här matchen. Antingen har matchen inte spelats eller så har informationen inte lagts in i databasen än. 
            </p>            
            </struts:else>            

        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
