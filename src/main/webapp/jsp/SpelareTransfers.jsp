<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Premier League > <struts:property value="spelareSasongStatistik.spelare.namn"/> - Transfers</title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <%@ include file="include/context/spelare.jsp" %>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Premier League</h2>
            <h3>Transfers</h3>

            <%@ include file="include/spelare_header.jsp" %>

            <table class="top" id="transfers">
                <colgroup>
                    <col class="transfer_datum_column"/>
                    <col class="deltagare_column"/>
                    <col class="deltagare_column"/>
                    <col class="transfer_pris_column"/>
                </colgroup>
                <tr>
                    <th>Datum</th>
                    <th class="deltagare">Från</th>
                    <th class="deltagare">Till</th>
                    <th>Pris</th>
                </tr>
                <struts:iterator value="spelareTransfers">
                <tr>
                    <td><struts:property value="deByteOmgang.datumStr"/></td>
                    <td class="deltagare">
                    <struts:push value="deltagareSald">
                        <%@ include file="include/deltagare_lank.jsp" %>
                    </struts:push>
                    </td>
                    <td class="deltagare">
                    <struts:push value="deltagareKopt">
                        <%@ include file="include/deltagare_lank.jsp" %>
                    </struts:push>
                    </td>
                    <td><struts:property value="pris"/></td>
                </tr>
                </struts:iterator>
            </table>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
