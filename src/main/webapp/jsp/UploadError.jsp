<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Uppladdningsfel</title>

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

            <div class="upload_input">
                <h3>Uppladdningsfel</h3>
    
                <struts:if test="%{hasActionErrors()}">
                <p>
                    Följande problem uppstod då filen skulle laddas upp.
                    <ul>
                    <struts:iterator value="actionErrors">
                    <li><struts:property/></li>
                    </struts:iterator>
    
                    </ul>
                </p>
                </struts:if>                
                <struts:if test="!okandSpelareSet.empty">
                <p>
                    Följande namn hittades inte bland spelarna som är tillgängliga för byte denna bytesomgång.
                </p>
                <p>
                    <ul>
                        <struts:iterator value="okandSpelareSet">                
                        <li>
                            <struts:property value="namn"/>
                        </li>
                        </struts:iterator>
                    </ul>
                </p>
                </struts:if>           
            </div>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
