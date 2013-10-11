<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Drömelvan > Bud - <struts:property value="deByteOmgang.namn"/> <struts:property value="deByteOmgang.deOmgang.omgang.tavling.sasong.namn"/></title>

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

            <h2>Bud</h2>

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
            <table id="bud_table">
                <colgroup>
                    <col class="deltagare_column"/>
                    <col class="spelare_column"/>
                    <col class="lag_kod_column"/>
                    <col class="position_column"/>
                    <col class="bud_column"/>
                    <col class="bud_column"/>
                    <col class="bud_column"/>
                    <col class="bud_column"/>
                    <col class="bud_column"/>
                </colgroup>
                <tr>
                    <th class="deltagare">Deltagare</th>
                    <th class="spelare">Listad</th>
                    <th class="spelare_lag">Lag</th>
                    <th class="spelare_position">Pos</th>
                    <th>Bud 1</th>
                    <th>Bud 2</th>
                    <th>Bud 3</th>
                    <struts:if test="listadeSpelareBud.maxAntalBud > 3">
                    <th>Bud 4</th>
                    </struts:if>
                    <struts:else>
                    <td class="empty">&nbsp;</td>
                    </struts:else>
                    <struts:if test="listadeSpelareBud.maxAntalBud > 4">
                    <th>Bud 5</th>
                    </struts:if>
                    <struts:else>
                    <td class="empty">&nbsp;</td>
                    </struts:else>                    
                </tr>
                    
                <struts:iterator value="listadeSpelareBud">
                <tr>
                    <td class="deltagare">
                        <struts:push value="deltagare">
                        <%@ include file="include/deltagare_lank.jsp" %>
                        </struts:push>
                    </td>
                    <td class="spelare">
                        <struts:url var="url" action="SpelareStatistik">
                            <struts:param name="spelareId" value="spelare.id"/>
                            <struts:param name="sasongId" value="sasongId"/>
                        </struts:url>
                        <struts:if test="mobile">
                            <struts:a href="%{url}"><struts:property value="spelare.efternamn"/></struts:a>
                        </struts:if>
                        <struts:else>
                        <struts:a href="%{url}"><struts:property value="spelare.kortNamn"/></struts:a>
                        </struts:else>
                    </td>
                    <td class="spelare_lag">
                        <struts:property value="spelare.lag.kod"/>
                    </td>
                    <td class="spelare_position">
                        <struts:property value="spelare.position.kod"/>
                    </td>
                    <struts:iterator value="bud">
                    <td <struts:if test="lyckat">class="lyckat"</struts:if><struts:else>class="spelare"</struts:else>>
                        <struts:if test="dummy">
                        Ingen
                        </struts:if>
                        <struts:else>
                        <struts:url var="url" action="SpelareStatistik">
                            <struts:param name="spelareId" value="spelare.id"/>
                            <struts:param name="sasongId" value="sasongId"/>
                        </struts:url>                                              
                        <struts:a href="%{url}"><struts:property value="spelare.efternamn"/></struts:a> <struts:property value="pris"/>
                        </struts:else>                        
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
