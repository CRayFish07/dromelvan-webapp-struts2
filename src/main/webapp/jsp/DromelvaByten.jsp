<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Drömelvan > Byten - <struts:property value="deByteOmgang.namn"/> <struts:property value="deByteOmgang.deOmgang.omgang.tavling.sasong.namn"/></title>

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

            <h3><struts:property value="deByteOmgang.namn"/> <struts:property value="deByteOmgang.deOmgang.omgang.tavling.sasong.namn"/></h3>

            <div class="info">
                <p>
                    Datum: <struts:property value="deByteOmgang.datumStr"/>
                </p>
                <p>
                    Deadline för transferlistning: <struts:property value="deByteOmgang.listDatumStr"/>
                </p>
                <p>
                    Byten i kraft fr.o.m: <struts:property value="deByteOmgang.deOmgang.omgang.namn"/>
                </p>
            </div>
            <table>
                <colgroup>
                    <col class="deltagare_column"/>
                    <col class="spelare_column"/>
                    <col class="spelare_column"/>
                    <col class="pris_column"/>
                </colgroup>
                <tr>
                    <th class="deltagare">Deltagare</th>
                    <th class="spelare">Spelare ut</th>
                    <th class="spelare">Spelare in</th>
                    <th>Pris</th>
                </tr>
                    
                <struts:iterator value="deByteOmgang.deByten">
                <struts:if test="pris > 0.0">
                <tr>
                    <td class="deltagare">
                        <struts:push value="deltagare">
                        <%@ include file="include/deltagare_lank.jsp" %>
                        </struts:push>
                    </td>
                    <td class="spelare">
                        <struts:push value="saldSpelare">
                        <%@ include file="include/spelare_lank.jsp" %>
                        </struts:push>
                    </td>
                    <td class="spelare">
                        <struts:push value="koptSpelare">
                        <%@ include file="include/spelare_lank.jsp" %>
                        </struts:push>
                    </td>
                    <td><struts:property value="pris"/></td>
                </tr>
                </struts:if>
                </struts:iterator>
                
            </table>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
