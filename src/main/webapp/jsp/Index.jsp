<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan</title>

    <%@ include file="include/head.jsp" %>

    <script type="text/javascript">
    <!--
        function bekraftaStrykning() {
            return confirm('Bekräfta strykning av meddelande.');
        }
    //-->
    </script>
</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>
    
    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <h3>Nyheter</h3>
            <h4 class="top">Fler nyheter</h4>
            <ul>
                <li><a href="<struts:url action="Index"/>">10 senaste</a></li>
                <struts:url var="url" action="Index">
                    <struts:param name="visaAlla" value="true"/>
                </struts:url>
                <li><struts:a href="%{url}">Alla nyheter</struts:a></li>
            </ul>
            <h4 class="administration_menu">Administration</h4>
            <ul class="administration_menu">
                <li><a href="<struts:url action="InsertNyhetForm"/>">Lägg in nytt meddelande</a></li>
            </ul>

        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">
            <h2>Nyheter</h2>
            <struts:iterator value="nyheter">
            <div class="meddelande">
                <h3><struts:property value="rubrik"/></h3>
                <struts:iterator value="paragrafer">
                <p>
                    <struts:property escape="false"/>
                </p>
                </struts:iterator>                
                <p class="finstilt">
                    Postat av <struts:property value="anvandare.namn"/> den <struts:property value="tidpunktStr"/> |
                    <a href="EditNyhetForm.action?nyhetId=<struts:property value="id"/>">Ändra</a> |
                    <a href="DeleteNyhet.action?nyhetId=<struts:property value="id"/>" onclick="return bekraftaStrykning();">Ta bort</a>
                </p>
            </div>
            </struts:iterator>
        </div>
        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
