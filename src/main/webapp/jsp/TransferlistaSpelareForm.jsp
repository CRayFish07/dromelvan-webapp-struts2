<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Administration > Transferlista <struts:property value="spelare.namn"/></title>

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

            <h3>Transferlista <struts:property value="spelare.namn"/></h3>

            <p>
                Klicka på 'Transferlista' för att göra denna spelare tillgänglig för byte.
            </p>

            <div class="spelare_input">
                <table>
                    <colgroup>
                        <col class="fornamn_column"/>
                        <col class="efternamn_column"/>
                        <col class="lag_kod_column"/>
                        <col class="position_column"/>                    
                        <col class="deltagare_column"/>
                    </colgroup>
                    <tr>
                        <th class="spelare">Förnamn</th>
                        <th class="spelare">Efternamn</th>
                        <th>Lag</th>
                        <th>Position</th>                    
                        <th>Listad av</th>
                    </tr>
                    <tr>
                        <td class="spelare">
                            <struts:property value="spelare.fornamn"/>
                        </td>
                        <td class="spelare">
                            <struts:property value="spelare.efternamn"/>
                        </td>                    
                        <td class="lag">
                            <struts:property value="spelare.lag.kod"/>
                        </td>                    
                        <td><struts:property value="spelare.position.kod"/></td>
                        <td class="deltagare">
                            <struts:property value="spelare.deltagare.namn"/>
                        </td>
                    </tr>
                </table>
    
                <form id="knappform" method="post" action="<struts:url action="TransferlistaSpelare"/>">
                    <div id="knappar">
                        <p>
                            <struts:submit value="Transferlista"/>
                            <struts:submit method="cancel" value="Avbryt"/>
                        </p>
                    </div>
                </form>
            </div>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
