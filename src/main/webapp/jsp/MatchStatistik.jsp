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
            <%@ include file="include/match_statistik_uppstallning_table.jsp" %>
            </struts:if>
            <struts:else>
            <div class="match_input">
                <p>
                    Matchstatistik saknas för den här matchen. Antingen har matchen inte spelats eller så har informationen inte lagts in i databasen än. 
                </p>
            </div>
            </struts:else>

        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
