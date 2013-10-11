<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Administration > Webappstatistik</title>

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

            <h3>Webappstatistik</h3>
            <p>
                Sida med sammanställning av statistik för hur webapplikationen
                har använts sedan senaste omstart.
            </p>

            <div id="applikation_statistik">
                <h4>20 senaste sidorna</h4>
                <table>
                    <colgroup>
                        <col class="tidpunkt_column"/>
                        <col class="namn_column"/>
                        <col class="remoteaddr_column"/>
                        <col class="tid_column"/>
                    </colgroup>
                    <tr>
                        <th>Tidpunkt</th>
                        <th>Namn</th>
                        <th>RemoteAddr</th>
                        <th>Tid</th>
                    </tr>
                    <struts:iterator value="actionExecutionHistory">
                    <tr>
                        <td><struts:property value="tidpunktStr"/></td>
                        <td><struts:property value="actionNamn"/>.jsp</td>
                        <td><struts:property value="remoteAddr"/></td>
                        <td><struts:property value="tid"/></td>
                    </tr>
                    </struts:iterator>
                </table>
                
                <h4>User Agents</h4>
                <table>
                    <colgroup>
                        <col class="user_agent_device_id_column"/>
                        <col class="user_agent_namn_column"/>
                        <col class="user_agent_antal_column"/>
                        <col class="user_agent_mobil_column"/>
                    </colgroup>
                    <tr>
                        <th>Device ID</th>
                        <th>Namn</th>
                        <th>Antal</th>
                        <th>Mobil</th>
                    </tr>
                    <struts:iterator value="userAgents">
                    <tr>
                        <td><struts:property value="deviceId"/></td>
                        <td><struts:property value="namn"/></td>
                        <td><struts:property value="antal"/></td>
                        <td><struts:property value="mobile"/></td>
                    </tr>
                    </struts:iterator>
                </table>
                
            </div>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
