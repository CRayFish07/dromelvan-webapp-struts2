<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Drömelvan > Tillgängliga spelare - <struts:property value="deByteOmgang.namn"/> <struts:property value="tavling.sasong.namn"/></title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <%@ include file="include/context/byten.jsp" %>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Drömelvan</h2>

            <h3 id="tillgangliga_spelare_rubrik">Tillgängliga spelare - <struts:property value="deByteOmgang.namn"/> <struts:property value="tavling.sasong.namn"/></h3>

            <div class="info">
                <h4 id="tillgangliga_spelare_sub_rubrik">Spelarlista - <struts:property value="deByteOmgang.namn"/> <struts:property value="tavling.sasong.namn"/></h4>
            </div>

            <table id="tillgangliga_spelare_print">
                <struts:iterator value="rader">
                <tr>
                    <struts:iterator>
                    <td>
                        <table id="lag_tillgangliga_spelare">
                            <colgroup>
                                <col class="spelare_column"/>
                                <col class="position_column"/>
                            </colgroup>
                            <tr>
                                <th colspan="2">
                                    <struts:property value="lag.namn"/>
                                </th>
                            </tr>
                            <struts:iterator value="tillgangligaSpelare">
                            <tr>
                                <td class="spelare">
                                <struts:if test="ny">* </struts:if>
                                <struts:property value="spelare.kortNamn"/>
                                </td>
                                <td><struts:property value="spelare.position.kod"/></td>
                            </tr>
                            </struts:iterator>
                        </table>
                    </td>
                    </struts:iterator>
                </tr>
                </struts:iterator>
            </table>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
