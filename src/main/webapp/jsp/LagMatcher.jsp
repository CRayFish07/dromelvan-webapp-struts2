<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Premier League > <struts:property value="lagSasongStatistik.lag.namn"/> - Säsongstatistik <struts:property value="lagSasongStatistik.sasong.namn"/></title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <%@ include file="include/context/lag.jsp" %>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Premier League</h2>
            <h3>Säsongstatistik <struts:property value="lagSasongStatistik.sasong.namn"/></h3>

            <div class="lag_header">
                <img class="lag_klubbmarke" src="bilder/lag/klubbmarke_<struts:property value="lagSasongStatistik.lag.id"/>.gif" alt="<struts:property value="lagSasongStatistik.namn"/>"/>
                <table>
                    <colgroup>
                        <col class="vanster_column"/>
                        <col class="hoger_column"/>
                    </colgroup>
                    <tr>
                        <th class="namn" colspan="2">
                            <struts:property value="lagSasongStatistik.lag.namn"/>
                        </th>
                    </tr>
                    <tr>
                        <td>Matcher: <struts:property value="lagSasongStatistik.antalMatcherSpelade"/></td>
                        <td>Placering: <struts:property value="lagSasongStatistik.placering"/></td>
                    </tr>
                    <tr>
                        <td>Gjorda mål: <struts:property value="lagSasongStatistik.antalMalGjorda"/> (<struts:property value="lagSasongStatistik.antalMalGjordaSnitt"/>/match)</td>
                        <td>Poäng: <struts:property value="lagSasongStatistik.poang"/></td>
                    </tr>
                    <tr>
                        <td>Insläppta mål: <struts:property value="lagSasongStatistik.antalMalInslappta"/> (<struts:property value="lagSasongStatistik.antalMalInslapptaSnitt"/>/match)</td>
                        <td>Försvararepoäng*: <struts:property value="lagSasongStatistik.forsvararePoang"/></td>
                    </tr>
                    <tr>
                        <td>Nollor: <struts:property value="lagSasongStatistik.nollor"/> (<struts:property value="lagSasongStatistik.procentNollor"/>%)</td>
                        <td>Ettor: <struts:property value="lagSasongStatistik.ettor"/> (<struts:property value="lagSasongStatistik.procentEttor"/>%)</td>
                    </tr>                    
                </table>
            </div>

            <table class="matcher">
                <colgroup>
                    <col class="lag_column"/>
                    <col class="separator_column"/>
                    <col class="lag_column"/>
                    <col class="omgang_column"/>
                    <col class="resultat_column"/>
                    <col class="datum_column"/>
                </colgroup>
                <tr>
                    <th class="lag">Hemmalag</th>
                    <th>&nbsp;</th>
                    <th class="lag">Bortalag</th>
                    <th>Omgång</th>
                    <th>Resultat</th>
                    <th>Datum</th>
                </tr>
                <struts:iterator value="lagSasongStatistik.matcher">
                <tr>
                    <td class="lag">
                        <struts:url var="url" action="LagMatcher">
                            <struts:param name="lagId" value="hemmaLag.id"/>
                            <struts:param name="sasongId" value="sasongId"/>
                        </struts:url>
                        <struts:a href="%{url}"><struts:property value="hemmaLag.namn"/></struts:a>                        
                    </td>
                    <td>-</td>
                    <td class="lag">
                        <struts:url var="url" action="LagMatcher">
                            <struts:param name="lagId" value="bortaLag.id"/>
                            <struts:param name="sasongId" value="sasongId"/>
                        </struts:url>
                        <struts:a href="%{url}"><struts:property value="bortaLag.namn"/></struts:a>                        
                    </td>
                    <td>
                        <struts:url var="url" action="PremierLeague" anchor="%{omgang.id}">
                            <struts:param name="tavlingId" value="omgang.tavling.id"/>
                            <struts:param name="startOmgang" value="omgang.startOmgangNummer"/>
                        </struts:url>            
                        <struts:a href="%{url}"><struts:property value="omgang.nummer"/></struts:a>
                    </td>
                    <td>
                        <%@ include file="include/match_lank.jsp" %>
                    </td>
                    <struts:if test="mobile">
                    <td><struts:property value="kortDatumStr"/></td>
                    </struts:if>
                    <struts:else>
                    <td><struts:property value="datumStr"/></td>
                    </struts:else>
                </tr>
                </struts:iterator>
            </table>
            
            <div class="finstilt">
                <p>
                    * Antal bonus (eller minus) poäng en försvarare som spelat samtliga matcher i
                      laget skulle ha fått från insläppta mål/ettor/nollor.
                </p>
            </div>            
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
