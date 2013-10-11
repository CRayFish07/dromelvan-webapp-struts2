<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Administration > Byt lösenord</title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <%@ include file="include/context/auktorisering.jsp" %>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Administration</h2>

            <h3>Byt lösenord</h3>

            <div class="login_input">
                <p>
                    Ange nytt lösenord och bekräfta det nya lösenordet. Klicka
                    sedan på 'Ändra'.
                </p>
    
                <form id="passwordform" method="post" action="<struts:url action="EditPassword"/>">
                    <table class="input" id="logintable">
                        <struts:if test="%{getHasFieldError('password')}">
                        <tr>
                            <td>
                                &nbsp;
                            </td>
                            <td class="error">
                                <struts:property value="%{getFieldErrorMessage('password')}"/>
                            </td>
                        </tr>
                        </struts:if>
                        <tr>
                            <td>
                                Lösenord:
                            </td>
                            <td>
                                <struts:password name="password"/>
                            </td>
                        </tr>
                        <struts:if test="%{getHasFieldError('password2')}">
                        <tr>
                            <td>
                                &nbsp;
                            </td>
                            <td class="error">
                                <struts:property value="%{getFieldErrorMessage('password2')}"/>
                            </td>
                        </tr>
                        </struts:if>
                        <tr>
                            <td>
                                Lösenord:
                            </td>
                            <td>
                                <struts:password name="password2"/>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>
                                <struts:submit value="Ändra"/>
                                <struts:reset value="Töm"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
