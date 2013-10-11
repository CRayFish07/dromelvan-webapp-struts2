<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Statistik > Hall of Fame</title>

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

            <h3>Hall of Fame</h3>
            <p>
                Diverse statistikfunktioner. Finns inte så mycket här, men förslag på funktioner
                mottages gärna och läggs in då tid och ork finns.
            </p>
            <h4>Hall of Fame</h4>
            <p>
                <a href="<struts:url action="SpelareMatchStatistikTop20"/>">Top 20 matchinsatser</a> - De 20 bästa
                instatserna av en spelare i en och samma match.
            </p>
            <h4>Hall of Shame</h4>
            <p>
                <a href="<struts:url action="SpelareMatchStatistikBottom20"/>">Bottom 20 matchinsatser</a> - De 20 sämsta
                instatserna av en spelare i en och samma match.
            </p>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
