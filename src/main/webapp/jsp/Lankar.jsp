<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Information > Länkar</title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <h3>Länkar</h3>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Länkar</h2>

            <h3>Länkar</h3>
            <p>
                Länkar som kanske kan vara av intresse.
            </p>

            <h4>Matchstatistik</h4>
            <ul>
                <li><a href="http://www.soccernet.com">Soccernet</a></li>
                <li><a href="http://www.premierleague.com">Premierleague.com</a></li>
            </ul>
            <p>
                Statistik tas i första hand från Soccernet eftersom de brukar
                vara kvicka då det gäller att få upp statistiken efter att matcherna
                spelats. Om något går snett för dem så är det Premierleague.com som gäller.
            </p>

            <h4>Ratings</h4>
            <ul>
                <li><a href="http://www.skysports.com/football/premier-league/">Sky Sports</a></li>
                <li><a href="http://www.dagbladet.no/pls/dbprod/fotball.terminliste_runde?pliga=117">Dagbladet</a></li>
            </ul>
            <p>
                Sky Sports är förstaval för ratings. Om de inte lyckas få upp sina ratings för en match inom
                rimlig tid tar vi dem från Dagbladet.
            </p>
            <p>
                Sky Sports har en tendens att ibland ge 5 i rating till spelare som kommer in mot
                slutet och inte hinner göra så mycket. Därför tas bara rating för spelare som
                spelat minst 20 minuter med i statistiken.
            </p>
            
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
