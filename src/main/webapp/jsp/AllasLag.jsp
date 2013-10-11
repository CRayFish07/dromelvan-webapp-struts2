<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Drömelvan > Allas lag <struts:property value="tavling.sasong.namn"/></title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <h3>Allas lag</h3>
            <h4 class="top">Allas lag</h4>
            <ul>
                <struts:url var="url" action="AllasLag">
                    <struts:param name="tavlingId" value="defaultDeTavlingId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Allas lag</struts:a></li>
            </ul>
            <h4 class="sasong_menu">Säsong <struts:property value="tavling.sasong.namn"/></h4>
            <ul class="sasong_menu">
                <struts:url var="url" action="Dromelvan">
                    <struts:param name="tavlingId" value="tavlingId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Matcher</struts:a></li>
                <struts:url var="url" action="DromelvanTabell">
                    <struts:param name="tavlingId" value="tavlingId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Tabell</struts:a></li>
                <struts:url var="url" action="DromelvaByten">
                    <struts:param name="tavlingId" value="tavlingId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Byten</struts:a></li>
            </ul>
            <h4 class="big_menu">Säsonger</h4>
            <ul class="big_menu">
                <struts:iterator value="tavlingar">
                <struts:url var="url" action="AllasLag">
                    <struts:param name="tavlingId" value="id"/>
                </struts:url>                                              
                <li><struts:a href="%{url}"><struts:property value="sasong.namn"/></struts:a></li>                    
                </struts:iterator>
            </ul>
            <h4 class="small_menu">Säsonger</h4>
            <ul class="small_menu">            
                <struts:url var="url" action="AllasLag">
                    <struts:param name="tavlingId" value="tavling.nastaSasongTavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Nästa säsong</struts:a></li>
                <struts:url var="url" action="AllasLag">
                    <struts:param name="tavlingId" value="tavling.foregaendeSasongTavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Föregående säsong</struts:a></li>                                                
            </ul>            
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Drömelvan</h2>

            <h3 class="allas_lag_rubrik">Allas lag <struts:property value="tavling.sasong.namn"/></h3>

            <%-- 
            <p>Målvakter: <struts:property value="antalMalvakter"/>
            <br>Ytterbackar: <struts:property value="antalYtterbackar"/>
            <br>Innerbackar: <struts:property value="antalInnerbackar"/>
            <br>Mittfältare: <struts:property value="antalMittfaltare"/>
            <br>Anfallare: <struts:property value="antalAnfallare"/></p>
            --%>
            
            <struts:iterator value="deltagareLag">
            <table class="top" id="allas_lag">
                <tr>
                    <struts:iterator>
                    <td class="rad">
                    <table id="deltagare_lag">
                        <colgroup>
                            <col class="position_column"/>
                            <col class="spelare_column"/>
                            <col class="pris_column"/>
                        </colgroup>
                        <tr class="screen_header">
                            <th colspan="3">
                                <struts:push value="deltagare">
                                <%@ include file="include/deltagare_lank.jsp" %>
                                </struts:push>
                            </th>
                        </tr>
                        <tr class="print_header">
                            <th colspan="2">
                                <struts:property value="deltagare.namn"/>
                            </th>
                            <th>
                                <struts:property value="pris"/>
                            </th>
                        </tr>

                        <struts:iterator value="spelareSasonger">
                        <tr>
                            <td><struts:property value="position.kod"/></td>
                            <td class="spelare">
                                <struts:url var="url" action="SpelareStatistik">
                                    <struts:param name="spelareId" value="spelare.id"/>
                                    <struts:param name="sasongId" value="sasong.id"/>
                                </struts:url>            
                                <struts:a href="%{url}"><struts:property value="spelare.kortNamn"/></struts:a> (<struts:property value="lag.kod"/>)
                            </td>
                            <td><struts:property value="pris"/></td>
                        </tr>
                        </struts:iterator>

                        <tr>
                            <td class="totalt_label" colspan="2">Totalt:</td>
                            <td class="totalt"><struts:property value="pris"/></td>
                        </tr>
                    </table>
                    </td>
                    </struts:iterator>
                </tr>
            </table>
            </struts:iterator>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
