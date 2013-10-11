<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Administration > Ändra spelaruppgifter - <struts:property value="spelare.namn"/></title>
    
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

            <h3>Ändra spelaruppgifter - <struts:property value="spelare.namn"/> </h3>

            <div class="spelare_header">
                <img class="spelare_klubbmarke" src="bilder/lag/klubbmarke_<struts:property value="spelare.lag.id"/>.gif" alt="<struts:property value="spelare.lag.namn"/>"/>
                <table>
                    <colgroup>
                        <col class="vanster_column"/>
                        <col class="hoger_column"/>
                    </colgroup>
                    <tr>
                        <th class="namn" colspan="2">
                            <struts:property value="spelare.namn"/>
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <struts:push value="spelare.lag">
                            Lag: <%@ include file="include/lag_lank.jsp" %>
                            </struts:push>
                         </td>
                        <td>
                            <struts:push value="spelare.deltagare">
                            Deltagare: <%@ include file="include/deltagare_lank.jsp" %>
                            </struts:push>
                        </td>
                    </tr>
                    <tr>
                        <td>Position: <struts:property value="spelare.position"/></td>
                        <td>&nbsp;</td>
                    </tr>
                    
                </table>
            </div>

            <div class="spelare_input">            
                <form id="edit_spelare" method="post" action="<struts:url action="EditSpelare"/>">
                    <table>
                        <colgroup>
                            <col class="fornamn_column"/>
                            <col class="efternamn_column"/>
                            <col class="lag_column"/>
                            <col class="deltagare_column"/>
                            <col class="position_column"/>
                        </colgroup>
                        <struts:if test="hasFieldErrors">
                        <tr>
                            <td colspan="2" class="error">
                                <struts:property value="%{getFieldErrorMessage('namn')}"/>
                                <struts:property value="%{getFieldErrorMessage('fornamn')}"/> <struts:property value="%{getFieldErrorMessage('efternamn')}"/>                                                    
                            </td>
                            <td colspan="2" class="error">&nbsp;</td>
                            <td class="error">
                                <struts:property value="%{getFieldErrorMessage('position')}"/>
                            </td>
                        </tr>
                        </struts:if>
                        <tr>
                            <th class="spelare">Förnamn</th>
                            <th class="spelare">Efternamn</th>
                            <th>Lag</th>
                            <th>Deltagare</th>
                            <th>Position</th>
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
                               <struts:select name="deltagareId" list="deltagareList" listKey="id" listValue="namn" size="1"
                                              required="true" value="%{deltagareId}"/>                            
                            </td>
                            <td>
                               <struts:select name="position" list="positionList" listKey="%{ordinal()}" size="1"
                                              required="true" value="%{position}"/>                            
                            </td>
                        </tr>
                    </table>
                    
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
