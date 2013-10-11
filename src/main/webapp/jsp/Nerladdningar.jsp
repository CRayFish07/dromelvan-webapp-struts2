<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Statistik > Nerladdningar</title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <h3>Nerladdningar</h3>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Statistik</h2>

            <h3>Statistikfiler</h3>
            <p>
                Smått och gott som kan vara av intresse.
            </p>

            <h4>spelarstat.xls</h4>
            <p>
                Gamla hederliga excelfilen med spelarstatistik.
            </p>
            <ul>
                <struts:iterator value="sasonger">
                <struts:url var="url" action="DownloadSpelarstat">
                    <struts:param name="sasongId" value="id"/>
                </struts:url>            
                <li><struts:a href="%{url}">spelarstat-<struts:property value="namn"/></struts:a></li>
                </struts:iterator>
            </ul>

            <h4>Spelarnamn</h4>
            <p>
                Dump av alla spelarnamn i databasen. Kan och bör användas som konfigurationsfil för
                verktygen som parsar och genererar statistikfiler.
            </p>
            <ul>
                <li><a href="<struts:url action="DownloadSpelareNamnLista"/>">spelarenamn.properties</a></li>                
            </ul>


        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
