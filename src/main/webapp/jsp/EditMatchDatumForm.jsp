<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Administration > Ändra datum: <struts:property value="match.hemmaLag.namn"/> - <struts:property value="match.bortaLag.namn"/></title>

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

            <h3 class="match_rubrik">Ändra datum: <struts:property value="match.hemmaLag.namn"/> - <struts:property value="match.bortaLag.namn"/></h3>
            <%@ include file="include/match_header.jsp" %>
            <%@ include file="include/match_statistik_resultat_table.jsp" %>

            <div class="match_input">
                <p>
                    Byt matchdatum, eller sätt datum till 1970-01-01 om matchen är flyttad till ett obestämt datum.
                </p>            
            
                <form id="datumform" method="post" action="<struts:url action="EditMatchDatum"/>">
                    <struts:if test="%{getHasFieldError('datum')}">
                    <p class="error">
                        <struts:property value="%{getFieldErrorMessage('datum')}"/>
                    </p>
                    </struts:if>
                    <p>
                        <label for="datum">Nytt matchdatum (CCYY-MM-DD):</label>                    
                        <struts:textfield cssClass="text" name="datum"/>
                    </p>
                    <p>
                        <struts:submit value="Ändra"/>
                        <struts:submit method="cancel" value="Avbryt"/>
                    </p>
                </form>
            </div>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
