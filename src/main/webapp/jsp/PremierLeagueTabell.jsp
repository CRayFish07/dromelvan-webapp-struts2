<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Premier League > Tabell <struts:property value="tavling.sasong.namn"/></title>

    <%@ include file="include/head.jsp" %>
    
</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <h3>Tabell</h3>
            <h4 class="top sasong_menu">Säsong <struts:property value="tavling.sasong.namn"/></h4>
            <ul class="sasong_menu">
                <struts:url var="url" action="PremierLeague">
                    <struts:param name="tavlingId" value="tavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Matcher</struts:a></li>                
            </ul>
            <h4 class="big_menu">Säsonger</h4>
            <ul class="big_menu">
                <struts:iterator value="tavlingar">
                <struts:url var="url" action="PremierLeagueTabell">
                    <struts:param name="tavlingId" value="id"/>
                </struts:url>            
                <li><struts:a href="%{url}"><struts:property value="sasong.namn"/></struts:a></li>                
                </struts:iterator>              
            </ul>
            
            <h4 class="small_menu">Säsonger</h4>
            <ul class="small_menu">            
                <struts:url var="url" action="PremierLeagueTabell">
                    <struts:param name="tavlingId" value="tavling.nastaSasongTavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Nästa säsong</struts:a></li>
                <struts:url var="url" action="PremierLeagueTabell">
                    <struts:param name="tavlingId" value="tavling.foregaendeSasongTavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Föregående säsong</struts:a></li>                                                
            </ul>            
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Premier League</h2>

            <h3><struts:property value="tavling.namn"/> <struts:property value="tavling.sasong.namn"/></h3>

            <table class="tabell" id="premier_league_tabell">
                <colgroup>
                    <col class="placering_column"/>
                    <col class="lag_column"/>
                    <col class="matcher_spelade_column"/>
                    <col class="matcher_vunna_column"/>
                    <col class="matcher_oavgjorda_column"/>
                    <col class="matcher_forlorade_column"/>
                    <col class="mal_gjorda_column"/>
                    <col class="separator_column"/>
                    <col class="mal_inslappta_column"/>
                    <col class="poang_column"/>
                </colgroup>
                <tr>
                    <th>#</th>
                    <th class="lag">Lag</th>
                    <th>M.</th>
                    <th>V.</th>
                    <th>O.</th>
                    <th>F.</th>
                    <th colspan="3">Målskillnad</th>
                    <th>Poäng</th>
                </tr>
                <struts:iterator value="tabell.tabellObjekt">
                <struts:if test="isSeparator">
                <tr class="separator">
                    <td class="separator" colspan="10"></td>
                </tr>                
                </struts:if>
                <struts:else>
                <tr>
                    <td><struts:property value="placering"/></td>
                    <td class="lag">
                        <struts:url var="url" action="LagMatcher">
                            <struts:param name="lagId" value="lag.id"/>
                            <struts:param name="sasongId" value="sasongId"/>
                        </struts:url>            
                        <struts:a href="%{url}"><struts:property value="lag.namn"/></struts:a>              
                    </td>
                    <td><struts:property value="antalMatcherSpelade"/></td>
                    <td><struts:property value="antalMatcherVunna"/></td>
                    <td><struts:property value="antalMatcherOavgjorda"/></td>
                    <td><struts:property value="antalMatcherForlorade"/></td>
                    <td><struts:property value="antalMalGjorda"/></td>
                    <td> - </td>
                    <td><struts:property value="antalMalInslappta"/></td>
                    <td><struts:property value="poang"/></td>                
                </tr>
                </struts:else>
                </struts:iterator>
                <tr class="tabell_forklaring">
                    <td colspan="10"># = Placering, M = Matcher, V = Vunna, O = Oavgjorda, F = Förlorade</td>
                </tr>              
            </table>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
