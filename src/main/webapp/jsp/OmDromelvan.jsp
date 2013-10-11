<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Information > Om Drömelvan</title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <h3>Om Drömelvan</h3>
        </div>
        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Information</h2>

            <h3>Om Drömelvan</h3>
            <p>
                Drömelvan använder, sponsras av (eller kanske inte), och rekommenderar
                följande produkter.
            </p>

            <table class="info">
                <tr>
                    <td class="image">
                        <a href="http://java.sun.com">
                            <img src="bilder/logo_java.gif" alt="Java"/>
                        </a>
                    </td>
                    <td>
                        Webapplikationen är skriven i java och använder
                        <a href="http://www.hibernate.org/">Hibernate</a> för
                        databashantering och 
                        <a href="http://struts.apache.org/2.x/">Struts2</a>
                        för att generera jsp. Javaversionen som
                        används är 1.5.0.
                    </td>
                </tr>
                <tr>
                    <td class="image">
                        <a href="http://tomcat.apache.org/">
                            <img src="bilder/tomcat.gif" alt="Tomcat Servlet Container"/>
                        </a>
                    </td>
                    <td>
                        Tomcat 6.0.18 Servlet/jsp motor som kör webapplikationen och
                        genererar de sidor som skickas tillbaka till användarens browser.
                    </td>
                </tr>
                <tr>
                    <td class="image">
                        <a href="http://httpd.apache.org/">
                            <img src="bilder/apache.png" alt="Apache HTTP Server"/>
                        </a>
                    </td>
                    <td>
                        Apache 2.0 http server. Webserver som Tomcat pluggas in i.
                    </td>
                </tr>
                <tr>
                    <td class="image">
                        <a href="http://www.postgresql.org/">
                            <img src="bilder/postgres.png" alt="PostgreSQL"/>
                        </a>
                    </td>
                    <td>
                        PostgreSQL 8.3 databasserver med tillhörande verktyg.
                    </td>
                </tr>
                <tr>
                    <td class="image">
                        <a href="http://www.apple.com">
                            <img src="bilder/mac_os.png" alt="Mac OS X"/>
                        </a>
                    </td>
                    <td>
                        Allt snurrar på en 1.42 Ghz PPC Mac Mini med 512 meg minne
                        och inte mycket mer som kör Mac OS X 1.4.
                    </td>
                </tr>
            </table>

            <p>
                Övrigt som inte direkt används för att köra Drömelvan men som använts
                för att utveckla det hela.
            </p>

            <table class="info">
                <tr>
                    <td class="image">
                        <a href="http://www.pgadmin.org/">
                            <img src="bilder/pgadmin.gif" alt="PGAdmin III"/>
                        </a>
                    </td>
                    <td>
                        pgAdmin III. Administrationsverktyg för PostgreSQL databaser.
                    </td>
                </tr>
                <tr>
                    <td class="image">
                        <a href="http://www.eclipse.org">
                            <img src="bilder/eclipse.jpg" alt="Eclipse"/>
                        </a>
                    </td>
                    <td>
                        Eclipse Platform 3.4. Programmeringsverktyg som används vid utvecklingen
                        av javadelen av Drömelvan.
                    </td>
                 </tr>
                 <tr>
                    <td class="image">
                        <a href="http://gimp.org/">
                            <img src="bilder/gimp.png" alt="GIMP"/>
                        </a>
                    </td>
                    <td>
                        GIMP, grafikprogram som använts till att producera loggan.
                    </td>
                </tr>
            </table>

            <p>
                Layouten är utformad i och för Firefox 3. Använder man andra browsers får man skylla
                sig själv om det ser underligt ut! Den har dock kontrollerats en del med IE7 och Safari 3.
            </p>

        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
