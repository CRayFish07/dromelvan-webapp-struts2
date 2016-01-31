<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Administration > Lägg in ny spelare</title>

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

            <h3>Lägg in ny spelare</h3>

            <p>
                Ange namn, lag och position för spelaren.
            </p>

            <div class="spelare_input">                
                <form method="post" action="<struts:url action="InsertSpelare"/>">
                    <table>
                        <colgroup>
                            <col class="fornamn_column"/>
                            <col class="efternamn_column"/>
                            <col class="lag_column"/>
                            <col class="position_column"/>
                            <col class="whoscored_id_column"/>
                        </colgroup>
                        <struts:if test="hasFieldErrors">
                        <tr>
                            <td colspan="2" class="error">
                                <struts:property value="%{getFieldErrorMessage('namn')}"/>                            
                            </td>
                            <td class="error">&nbsp;</td>
                            <td class="error">
                                <struts:property value="%{getFieldErrorMessage('position')}"/>
                            </td>
                            <td class="error">
                                <struts:property value="%{getFieldErrorMessage('whoScoredId')}"/>
                            </td>                            
                        </tr>
                        </struts:if>
                        <tr>
                            <th class="spelare">Förnamn</th>
                            <th class="spelare">Efternamn</th>
                            <th>Lag</th>
                            <th>Position</th>
                            <th>WhoScoredId</th>
                        </tr>
                        <tr>
                            <td>
                                <struts:textfield cssClass="text" name="fornamn"/>
                            </td>
                            <td>
                                <struts:textfield cssClass="text" name="efternamn"/>
                            </td>
                            <td>
                               <struts:select name="lagId" list="lagList" listKey="id" listValue="namn" size="1"
                                              required="true" value="%{lagId}"/>                            
                            </td>
                            <td>
                               <struts:select name="position" list="positionList" listKey="%{ordinal()}" size="1"
                                              required="true" value="%{position}"/>                            
                            </td>
                            <td>
                                <struts:textfield cssClass="text" name="whoScoredId"/>
                            </td>                            
                        </tr>
                    </table>
                    
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
