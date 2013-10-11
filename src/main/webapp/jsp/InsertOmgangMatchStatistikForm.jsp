<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Administration > Lägg in information för <struts:property value="omgang.namn"/></title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <h3>Adminfunktioner</h3>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Administration</h2>

            <h3>Lägg in information för <struts:property value="omgang.namn"/></h3>

            <p>
                Info om lag, deltagare och match som skall räknas för spelare denna omgång
                saknas. Välj 'lägg in' för att lägga in informationen i databasen. Detta
                måste göras före matchstatistik och ratings kan laddas up.
            </p>
            <p>
                OBS: Matcher som flyttats så att de spelas tidigare än planerat bör inte läggas in före det är
                dags att spela resten av omgången.
            </p>
            <form id="insertOmgangMatchStatistik" method="post" action="<struts:url action="InsertOmgangMatchStatistik"/>">
                <p>
                    <struts:submit value="Lägg in"/>
                    <struts:submit method="cancel" value="Avbryt"/>
                </p>                
            </form>   

        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
