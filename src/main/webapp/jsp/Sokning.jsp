<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Sökning</title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <h3>Sökning</h3>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Sökning</h2>

            <div>
                <h3>Sökresultat för '<struts:property value="sokning"/>'</h3>
                <p>
                    Följande spelare, lag och deltagare hade namn som liknade
                    den givna söksträngen.
                </p>

                <h4>Spelare</h4>
                <struts:if test="spelare.empty">
                <p>
                    Inga spelare med passande namn hittades.
                </p>                    
                </struts:if>
                <struts:else>
                <ul>
                    <struts:iterator value="spelare">
                    <li><%@ include file="include/spelare_lank.jsp" %></li>
                    </struts:iterator>
                </ul>                    
                </struts:else>

                <h4>Lag</h4>
                <struts:if test="lag.empty">
                <p>
                    Inga lag med passande namn hittades.
                </p>                    
                </struts:if>
                <struts:else>
                <ul>                    
                    <struts:iterator value="lag">
                    <li><%@ include file="include/lag_lank.jsp" %></li>
                    </struts:iterator>
                </ul>                    
                </struts:else>

                <h4>Deltagare</h4>
                <struts:if test="deltagare.empty">
                <p>
                    Inga deltagare med passande namn hittades.
                </p>                    
                </struts:if>
                <struts:else>
                <ul>
                    <struts:iterator value="deltagare">
                    <li><%@ include file="include/deltagare_lank.jsp" %></li>
                    </struts:iterator>
                </ul>                    
                </struts:else>
            </div>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
