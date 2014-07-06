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

            <h3>Bud - <struts:property value="deByteOmgang.namn"/> <struts:property value="deByteOmgang.deOmgang.omgang.tavling.sasong.namn"/></h3>

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
                <struts:if test="deByteOmgang.status == 1">
                <p>
	                <struts:url var="url" action="HanteraBud">
	                    <struts:param name="deByteOmgangId" value="deByteOmgang.id"/>
	                </struts:url>                                        
	                <struts:a href="%{url}">Hantera bud</struts:a>.
                </p>
                </struts:if>
            </div>
            <table id="bud_table">
                <colgroup>
                    <col class="deltagare_column"/>
                    <col class="position_column"/>
                    <col class="spelare_column"/>
                    <col class="lag_kod_column"/>
                    <col class="position_column"/>
                    <col class="poang_column"/>
                    <col class="bud_column"/>
                    <col class="bud_column"/>
                    <col class="lyckate_column"/>
                    <struts:if test="deByteOmgang.status == 1">
                    <col/>
                    </struts:if>                    
                </colgroup>
                <tr>
                    <th class="deltagare">Deltagare</th>
                    <th class="deltagare">#</th>
                    <th class="spelare">Listad</th>
                    <th class="spelare_lag">Lag</th>
                    <th class="spelare_position">Pos</th>
                    <th class="spelare_position">P.</th>
                    <th>Bud</th>
                    <th>Aktivt bud</th>
                    <th>Lyckat</th>
                    <struts:if test="deByteOmgang.status == 1">
                    <th>Ångra</th>
                    </struts:if>                    
                </tr>
                <struts:iterator value="bud">
                <tr>
                    <td class="deltagare">
                        <struts:push value="deltagare">
                        <%@ include file="include/deltagare_lank.jsp" %>
                        </struts:push>
                    </td>
                    <td>
                        <struts:property value="listadSpelarePrioritet"/>
                    </td>                    
                    <td class="spelare <struts:if test="lyckat">lyckat</struts:if>">
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
                    <td class="poang">
                        <struts:property value="prioritet"/>
                    </td>                    
                    <td>
                        <struts:property value="pris"/>
                    </td>
                    <td>
                        <struts:property value="aktivtPris"/>
                    </td>                    
                    <td>
                        <struts:property value="lyckat"/>
                    </td>                                        
                    <struts:if test="deByteOmgang.status == 1">
                    <td>
	                    <struts:url var="url" action="AngraBud">
	                        <struts:param name="budId" value="id"/>
	                    </struts:url>                                        
                        <struts:a href="%{url}">Ångra</struts:a>
                    </td>
                    
                    </struts:if>                                        
                </tr>
                </struts:iterator>            
            </table>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
