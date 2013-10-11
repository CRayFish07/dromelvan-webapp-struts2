<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Administration > Lägg in nyhet</title>
    
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

            <h3>Lägg in nyhet</h3>
            <div class="nyhet_input">
                <p>
                    Skriv in rubrik och ett nytt meddelande och klicka på 'Lägg in'
                    för att få in ett nytt meddelande på förstasidan. Lägg in tomma rader
                    för att få paragrafer i meddelandet, använd inga andra htmltaggar
                    än länkar.
                </p>
    
                <form id="nyhetform" method="post" action="<struts:url action="InsertNyhet"/>">
                    <h4>Rubrik:</h4>
                    <struts:if test="%{getHasFieldError('rubrik')}">
                    <p class="error">
                        <struts:property value="%{getFieldErrorMessage('rubrik')}"/>
                    </p>
                    </struts:if>
                    <p>
                        <struts:textfield cssClass="rubrik_text" name="rubrik"/>
                    </p>
                    <h4>Meddelande:</h4>
                    <struts:if test="%{getHasFieldError('meddelande')}">
                    <p class="error">
                        <struts:property value="%{getFieldErrorMessage('meddelande')}"/>
                    </p>
                    </struts:if>
                    <p>
                        <struts:textarea name="meddelande" rows="10" cols="70"/>
                    </p>
                    <p>
                        <struts:submit value="Lägg in"/>
                        <struts:submit method="cancel" value="Avbryt"/>
                    </p>
                </form>
            </div>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
