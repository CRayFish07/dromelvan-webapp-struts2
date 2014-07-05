<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Transfers > Lägg bud</title>

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

            <h2>Transfer</h2>

            <h3>Lägg bud</h3>

            <p>
                Lägg ett bud på <struts:property value="spelare.namn"/> för bytesomgång <struts:property value="deByteOmgang.namn"/>. Maxbud: <struts:property value="pris"/>
            </p>

            <div class="spelare_input">                
                <form method="post" action="<struts:url action="InsertBud"/>">
                    <table>
                        <colgroup>
                            <col class="spelare_column"/>
                            <col class="kod_column"/>
                            <col class="position_column"/>
                            <col class="pris_column"/>
                        </colgroup>
                        <struts:if test="hasFieldErrors">
                        <tr>
                            <td class="error">
                                <struts:property value="%{getFieldErrorMessage('namn')}"/>                            
                            </td>
                            <td colspan="3" class="error">
                                <struts:property value="%{getFieldErrorMessage('pris')}"/>
                            </td>
                        </tr>
                        </struts:if>
                        <tr>
                            <th class="spelare">Spelare</th>
                            <th>Lag</th>
                            <th>Pos.</th>
                            <th>Bud</th>
                        </tr>
                        <tr>
                            <td>
                                <struts:property value="spelare.namn"/>
                            </td>
                            <td>
                                <struts:property value="spelare.lag.kod"/>
                            </td>
                            <td>
                                <struts:property value="spelare.position.kod"/>
                            </td>
                            <td>
                                <struts:textfield cssClass="text" name="pris"/>
                            </td>
                        </tr>
                    </table>
                    
                    <p>
                        <struts:submit value="Lägg in"/>
                        <struts:submit method="cancel" value="Avbryt"/>
                    </p>
                </div>                
            </form>    
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
