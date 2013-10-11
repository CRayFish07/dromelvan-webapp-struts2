<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Premier League > <struts:property value="match.hemmaLag.namn"/> - <struts:property value="match.bortaLag.namn"/> (<struts:property value="match.omgang.tavling.sasong.namn"/>)</title>

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

            <h2>Premier League</h2>

            <h3 class="match_rubrik">Matchstatistik: <struts:property value="match.hemmaLag.namn"/> - <struts:property value="match.bortaLag.namn"/> (<struts:property value="match.omgang.tavling.sasong.namn"/>)</h3>
            <%@ include file="include/match_header.jsp" %>
            <%@ include file="include/match_statistik_resultat_table.jsp" %>
            <div class="match_input">
                <p>
                    Välj en .xml fil med ratings för matchen och klicka på 'Ladda upp' för att skicka den
                    till servern. Om filen är korrekt kommer ratings för matchen att läggas in.
                </p>            
    
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
                    Följande namn hittades inte bland spelarna som har matchstatistik för matchen.
                </p>                 
                <struts:iterator value="okandSpelareSet">
                    
                <h4><struts:property value="namn"/></h4>
                <p>
                    <struts:url var="url" action="InsertSpelareForm">
                        <struts:param name="fornamn" value="fornamn"/>
                        <struts:param name="efternamn" value="efternamn"/>
                    </struts:url>                            
                    Lägg in en <struts:a href="%{url}">ny spelare</struts:a>.
                </p>
                <p>
                    Alternativ:
                </p>
                <ul>
                    <struts:iterator value="alternativ">
                    <struts:url var="url" action="EditSpelareForm">
                        <struts:param name="spelareId" value="id"/>
                    </struts:url>                                                                    
                    <li><struts:a href="%{url}"><struts:property value="namn"/></struts:a> (<struts:property value="lag.kod"/>)</li>
                    </struts:iterator>
                </ul>
                </struts:iterator>            
                </struts:if>           
                <form id="uploadRatingsForm" method="post" action="<struts:url action="UploadRatings"/>" enctype="multipart/form-data">
                    <p>
                        <struts:file name="uploadFile" accept="text/xml"/>
                    </p>
                    <p>
                        <struts:submit value="Ladda up"/>
                        <struts:submit method="cancel" value="Avbryt"/>
                    </p>                
                </form>   
            </div>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
