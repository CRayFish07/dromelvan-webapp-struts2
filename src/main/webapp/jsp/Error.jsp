<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Oops!!</title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Error</h2>

            <h3>Oops!!</h3>
            <p>
                Ett fel uppstod. Närmare bestämt hade vi följande problem då sidan skulle hämtas.
            </p>

            <ul>
                <struts:iterator value="actionErrors">
                <li><struts:property/></li>
                </struts:iterator>
            </ul>

        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
