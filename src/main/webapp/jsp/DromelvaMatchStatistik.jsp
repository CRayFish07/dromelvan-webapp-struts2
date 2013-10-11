<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Drömelvan > <struts:property value="deMatch.hemmaDeltagare.namn"/> - <struts:property value="deMatch.bortaDeltagare.namn"/> (<struts:property value="deMatch.deOmgang.tavling.sasong.namn"/>)</title>

    <%@ include file="include/head.jsp" %>
    
</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <%@ include file="include/context/dromelva_match.jsp" %>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Drömelvamatch</h2>

            <h3 class="match_rubrik">Matchstatistik: <struts:property value="deMatch.hemmaDeltagare.namn"/> - <struts:property value="deMatch.bortaDeltagare.namn"/> (<struts:property value="deMatch.deOmgang.tavling.sasong.namn"/>)</h3>

            <div class="match_header">
                <img class="hemma_klubbmarke" src="bilder/deltagare/deltagare_<struts:property value="deMatch.hemmaDeltagare.id"/>.gif" alt="<struts:property value="deMatch.hemmaDeltagare.namn"/>"/>
                <img class="borta_klubbmarke" src="bilder/deltagare/deltagare_<struts:property value="deMatch.bortaDeltagare.id"/>.gif" alt="<struts:property value="deMatch.bortaDeltagare.namn"/>"/>
                <div class="match_info">
                    <p>
                        <struts:property value="deMatch.deOmgang.tavling.namn"/>
                    </p>
                    <p>
                        <struts:property value="deMatch.deOmgang.namn"/>
                    </p>
                    <p>
                        Säsong: <struts:property value="deMatch.deOmgang.tavling.sasong.namn"/>
                    </p>
                    <p>
                        Datum: <struts:property value="deMatch.deOmgang.omgang.datumStr"/>
                    </p>
                </div>
            </div>
            
            <table class="resultat_table">
                <colgroup>
                    <col class="deltagare_column"/>
                    <col class="mal_column"/>
                    <col class="separator_column"/>
                    <col class="mal_column"/>
                    <col class="deltagare_column"/>
                </colgroup>
                <tr>
                    <th>
                        <struts:push value="deMatch.hemmaDeltagare">
                        <%@ include file="include/deltagare_lank.jsp" %>
                        </struts:push>
                    </th>
                    <th>
                        <struts:property value="deMatch.antalMalHemmaDeltagare"/>
                    </th>
                    <th>
                        -
                    </th>
                    <th>
                        <struts:property value="deMatch.antalMalBortaDeltagare"/>
                    </th>
                    <th>
                        <struts:push value="deMatch.bortaDeltagare">
                        <%@ include file="include/deltagare_lank.jsp" %>
                        </struts:push>
                    </th>
                </tr>
            </table>

            <div id="deltagare_dromelvastatistik">
                <h4><struts:property value="deMatch.hemmaDeltagare.namn"/></h4>

                <table class="top" id="dromelvamatch">
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
                    </tr>
                        
                    <struts:iterator value="deMatch.hemmaDeltagareSpelareMatchStatistik">
                    <tr>
                        <td class="spelare">
                            <struts:push value="spelare">
                            <%@ include file="include/spelare_lank.jsp" %>
                            </struts:push>
                        </td>
                        <td><struts:property value="position.kod"/>
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
                    </tr>
                    </struts:iterator>
                    
                    <tr>
                        <td class="totalt_label" colspan="13">Totalt:</td>
                        <td class="totalt"><struts:property value="deMatch.antalPoangHemmaDeltagare"/></td>
                    </tr>
                </table>
            </div>

            <div id="deltagare_dromelvastatistik">
                <h4><struts:property value="deMatch.bortaDeltagare.namn"/></h4>
                <table class="top" id="dromelvamatch">
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
                    </tr>
                        
                    <struts:iterator value="deMatch.bortaDeltagareSpelareMatchStatistik">
                    <tr>
                        <td class="spelare">
                            <struts:push value="spelare">
                            <%@ include file="include/spelare_lank.jsp" %>
                            </struts:push>
                        </td>
                        <td><struts:property value="position.kod"/>
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
                    </tr>
                    </struts:iterator>
                    
                    <tr>
                        <td class="totalt_label" colspan="13">Totalt:</td>
                        <td class="totalt"><struts:property value="deMatch.antalPoangBortaDeltagare"/></td>
                    </tr>
                </table>
            </div>

        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
