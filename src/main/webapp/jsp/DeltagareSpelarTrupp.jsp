<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Premier League > <struts:property value="deltagareSpelarTrupp.deltagare.namn"/> - Spelartrupp <struts:property value="deltagareSpelarTrupp.sasong.namn"/></title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <%@ include file="include/context/deltagare.jsp" %>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Drömelvan</h2>
            <h3>Spelartrupp <struts:property value="deltagareSpelarTrupp.sasong.namn"/></h3>

            <div class="deltagare_header">
                <img class="deltagare_klubbmarke" src="bilder/deltagare/deltagare_<struts:property value="deltagareSpelarTrupp.deltagare.id"/>.gif" alt="<struts:property value="deltagareSpelarTrupp.deltagare.namn"/>"/>
                <table>
                    <colgroup>
                        <col class="vanster_column"/>
                        <col class="hoger_column"/>
                    </colgroup>
                    <tr>
                        <th class="namn" colspan="2">
                            <struts:property value="deltagareSpelarTrupp.deltagare.namn"/>
                        </th>
                    </tr>
                    <tr>
                        <td>Spelare: <struts:property value="deltagareSpelarTrupp.antalSpelare"/></td>
                        <!-- <td>Pris: <struts:property value="deltagareSpelarTrupp.pris"/></td> -->
                        <!-- Nedanstående rad bara under budgivningen -->
                        <td>Pris: <struts:property value="deltagareSpelarTrupp.pris"/> (<struts:property value="kvarstaendeTransferBudget"/> kvar, max <struts:property value="storstaBud"/>)</td> 
                    </tr>
                    <tr>
                        <td>Mål/Assist: <struts:property value="deltagareSpelarTrupp.antalMal"/>/<struts:property value="deltagareSpelarTrupp.antalAssist"/></td>
                        <td>Kort (G/R): <struts:property value="deltagareSpelarTrupp.antalGultKort"/>/<struts:property value="deltagareSpelarTrupp.antalRottKort"/></td>
                    </tr>
                    <tr>
                        <td>Poäng*: <struts:property value="deltagareSpelarTrupp.poang"/></td>
                        <td>Medelpoäng*: <struts:property value="deltagareSpelarTrupp.medelPoang"/></td>
                    </tr>
                    <tr>
                        <td>MoM: <struts:property value="deltagareSpelarTrupp.antalMom"/>/<struts:property value="deltagareSpelarTrupp.antalDeladMom"/></td>                                            
                        <td>Medelrating*: <struts:property value="deltagareSpelarTrupp.medelRating"/></td>                        
                    </tr>
                </table>
            </div>

            <table id="spelare">
                <colgroup>
                    <col class="spelare_column"/>
                    <col class="position_column"/>
                    <col class="pris_column"/>                    
                    <col class="placering_column"/>
                    <col class="matcher_column"/>
                    <col class="mal_column"/>
                    <col class="assist_column"/>
                    <col class="mom_column"/>
                    <col class="gult_kort_column"/>
                    <col class="rott_kort_column"/>
                    <col class="medel_rating_column"/>
                    <col class="poang_column"/>
                    <col class="lag_column"/>
                </colgroup>
                <tr>
                    <th class="spelare">Namn</th>                    
                    <struts:url var="url" action="%{actionNamn}">
                        <struts:param name="deltagareId" value="deltagareId"/>
                        <struts:param name="sasongId" value="sasongId"/>                        
                        <struts:param name="kolumn" value="3"/>                        
                    </struts:url>            
                    <th><struts:a href="%{url}">Pos</struts:a></th>
                    <th>Pr.</th>                                        
                    <struts:url var="url" action="%{actionNamn}">
                        <struts:param name="deltagareId" value="deltagareId"/>
                        <struts:param name="sasongId" value="sasongId"/>                                                
                        <struts:param name="kolumn" value="0"/>                        
                    </struts:url>                    
                    <th><struts:a href="%{url}">#</struts:a></th>
                    <struts:url var="url" action="%{actionNamn}">
                        <struts:param name="deltagareId" value="deltagareId"/>
                        <struts:param name="sasongId" value="sasongId"/>                                                
                        <struts:param name="kolumn" value="4"/>                        
                    </struts:url>                    
                    <th><struts:a href="%{url}">M.</struts:a></th>
                    <struts:url var="url" action="%{actionNamn}">
                        <struts:param name="deltagareId" value="deltagareId"/>
                        <struts:param name="sasongId" value="sasongId"/>                                                
                        <struts:param name="kolumn" value="5"/>                        
                    </struts:url>            
                    <th><struts:a href="%{url}">Mål</struts:a></th>
                    <struts:url var="url" action="%{actionNamn}">
                        <struts:param name="deltagareId" value="deltagareId"/>
                        <struts:param name="sasongId" value="sasongId"/>                                                
                        <struts:param name="kolumn" value="10"/>                        
                    </struts:url>            
                    <th><struts:a href="%{url}">A.</struts:a></th>                    
                    <struts:url var="url" action="%{actionNamn}">
                        <struts:param name="deltagareId" value="deltagareId"/>
                        <struts:param name="sasongId" value="sasongId"/>                                                
                        <struts:param name="kolumn" value="6"/>                        
                    </struts:url>            
                    <th><struts:a href="%{url}">MoM</struts:a></th>
                    <struts:url var="url" action="%{actionNamn}">
                        <struts:param name="deltagareId" value="deltagareId"/>
                        <struts:param name="sasongId" value="sasongId"/>                                                
                        <struts:param name="kolumn" value="7"/>                        
                    </struts:url>            
                    <th><struts:a href="%{url}">GK</struts:a></th>
                    <struts:url var="url" action="%{actionNamn}">
                        <struts:param name="deltagareId" value="deltagareId"/>
                        <struts:param name="sasongId" value="sasongId"/>                                                
                        <struts:param name="kolumn" value="8"/>                        
                    </struts:url>            
                    <th><struts:a href="%{url}">RK</struts:a></th>
                    <struts:url var="url" action="%{actionNamn}">
                        <struts:param name="deltagareId" value="deltagareId"/>
                        <struts:param name="sasongId" value="sasongId"/>                                                
                        <struts:param name="kolumn" value="9"/>                        
                    </struts:url>            
                    <th><struts:a href="%{url}">R.</struts:a></th>
                    <struts:url var="url" action="%{actionNamn}">
                        <struts:param name="deltagareId" value="deltagareId"/>
                        <struts:param name="sasongId" value="sasongId"/>                                                
                        <struts:param name="kolumn" value="0"/>                        
                    </struts:url>            
                    <th><struts:a href="%{url}">P.</struts:a></th>
                    <th class="lag">Lag</th>
                </tr>
                <struts:iterator value="deltagareSpelarTrupp.spelareSasongStatistik">
                <tr>
                    <struts:push value="spelare">
                    <td class="spelare"><%@ include file="include/spelare_lank.jsp" %></td>
                    </struts:push>
                    <td><struts:property value="position.kod"/></td>
                    <td><struts:property value="pris"/></td>
                    <td><struts:property value="placering"/></td>
                    <%@ include file="include/spelare_sasong_statistik.jsp" %>
                    <td class="lag">
                    <struts:push value="lag">
                        <%@ include file="include/lag_lank.jsp" %>
                    </struts:push>
                    </td>
                </tr>
                </struts:iterator>
                <tr class="tabell_forklaring">
                   <td colspan="13">
                       Pos = Position, # = Placering i statistiken, M = Matcher, A = Assist, GK = gula kort, RK = röda kort,
                       R = Medelrating, P = Drömelvapoäng, Pr. = Pris
                   </td>
                </tr>                
            </table>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
