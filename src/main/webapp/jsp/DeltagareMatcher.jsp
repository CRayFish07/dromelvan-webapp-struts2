<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > <struts:property value="deltagareSasongStatistik.deltagare.namn"/> - Säsongstatistik <struts:property value="deltagareSasongStatistik.sasong.namn"/></title>

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
            <h3>Matcher <struts:property value="deltagareSasongStatistik.sasong.namn"/></h3>

            <div class="deltagare_header">
                <img class="deltagare_klubbmarke" src="bilder/deltagare/deltagare_<struts:property value="deltagareSasongStatistik.deltagare.id"/>.gif" alt="<struts:property value="deltagareSasongStatistik.deltagare.namn"/>"/>
                <table>
                    <colgroup>
                        <col class="vanster_column"/>
                        <col class="hoger_column"/>
                    </colgroup>
                    <tr>
                        <th class="namn" colspan="2">
                            <struts:property value="deltagareSasongStatistik.deltagare.namn"/>
                        </th>
                    </tr>
                    <tr>
                        <td>Matcher: <struts:property value="deltagareSasongStatistik.antalMatcherSpelade"/></td>
                        <td>Placering: <struts:property value="deltagareSasongStatistik.placering"/></td>
                    </tr>
                    <tr>
                        <td>Gjorda mål: <struts:property value="deltagareSasongStatistik.antalMalGjorda"/> (<struts:property value="deltagareSasongStatistik.antalMalGjordaSnitt"/>/match)</td>
                        <td>Poäng: <struts:property value="deltagareSasongStatistik.poang"/></td>
                    </tr>
                    <tr>
                        <td>Insläppta mål: <struts:property value="deltagareSasongStatistik.antalMalInslappta"/> (<struts:property value="deltagareSasongStatistik.antalMalInslapptaSnitt"/>/match)</td>
                        <td>Spelarepoäng: <struts:property value="deltagareSasongStatistik.spelarePoang"/></td>
                    </tr>
                </table>
            </div>

            <table class="matcher">
                <colgroup>
                    <col class="deltagare_column"/>
                    <col class="separator_column"/>
                    <col class="deltagare_column"/>
                    <col class="omgang_column"/>
                    <col class="resultat_column"/>
                    <col class="datum_column"/>
                </colgroup>
                <tr>
                    <th class="deltagare">Hemmadeltagare</th>
                    <th>&nbsp;</th>
                    <th class="deltagare">Bortadeltagare</th>
                    <th>Omgång</th>
                    <th>Resultat</th>
                    <th>Datum</th>
                </tr>
                <struts:iterator value="deltagareSasongStatistik.deMatcher">
                <tr>
                    <td class="deltagare">
                        <struts:url var="url" action="DeltagareMatcher">
                            <struts:param name="deltagareId" value="hemmaDeltagare.id"/>
                            <struts:param name="sasongId" value="sasongId"/>
                        </struts:url>
                        <struts:a href="%{url}"><struts:property value="hemmaDeltagare.namn"/></struts:a>                        
                    </td>
                    <td>-</td>
                    <td class="deltagare">
                        <struts:url var="url" action="DeltagareMatcher">
                            <struts:param name="deltagareId" value="bortaDeltagare.id"/>
                            <struts:param name="sasongId" value="sasongId"/>
                        </struts:url>
                        <struts:a href="%{url}"><struts:property value="bortaDeltagare.namn"/></struts:a>                        
                    </td>
                    <td>
                        <struts:url var="url" action="Dromelvan" anchor="%{deOmgang.id}">
                            <struts:param name="tavlingId" value="deOmgang.tavling.id"/>
                            <struts:param name="startOmgang" value="deOmgang.startOmgangNummer"/>
                        </struts:url>            
                        <struts:a href="%{url}"><struts:property value="deOmgang.nummer"/></struts:a>
                    </td>
                    <td>
                        <struts:url var="url" action="DromelvaMatchStatistik">
                            <struts:param name="matchId" value="id"/>
                        </struts:url>                                              
                        <struts:a href="%{url}">
                            <struts:property value="antalMalHemmaDeltagare"/> - <struts:property value="antalMalBortaDeltagare"/>
                        </struts:a>
                    </td>
                    <td><struts:property value="deOmgang.omgang.datumStr"/></td>
                </tr>
                </struts:iterator>
            </table>
            
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
