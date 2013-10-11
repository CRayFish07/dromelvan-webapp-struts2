<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Administration > Administrationsfunktioner</title>

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

            <h3>Administrationsfunktioner</h3>
            <p>
                Funktioner för att administrera det hela. Man måste logga in som
                administratör för att använda dessa.
            </p>

            <h4>Byteomgång</h4>
            <p>
                <a href="<struts:url action="InsertDeByteOmgangForm"/>">Lägg in</a> en ny byteomgång för Drömelvan.
            </p>

            <h4>Bud</h4>
            <p>
                Ladda upp .xml fil med bud.
            </p>
             <form id="uploadBudForm" method="post" action="<struts:url action="UploadBud"/>" enctype="multipart/form-data">
                <p>
                    <struts:file name="uploadFile" accept="text/xml"/>
                </p>
                <p>
                    <struts:submit value="Ladda up"/>
                </p>                
            </form>   

            <h4>Byten</h4>
            <p>
                Ladda upp .xml fil med byten.
            </p>
             <form id="uploadDeBytenForm" method="post" action="<struts:url action="UploadDeByten"/>" enctype="multipart/form-data">
                <p>
                    <struts:file name="uploadFile" accept="text/xml"/>
                </p>
                <p>
                    <struts:submit value="Ladda up"/>
                </p>                
            </form>   

            <h4>Cache</h4>
            <p>
                <a href="<struts:url action="EvictCache"/>">Töm cachen</a>. 
            </p>

            <h4>Statistik om webapplikationen</h4>
            <p>
                <a href="<struts:url action="WebappStatistik"/>">Statistik</a> över användningen av webapplikationen.
            </p>

        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
