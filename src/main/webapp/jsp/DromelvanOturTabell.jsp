<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Drömelvan > Oturtabell <struts:property value="tavling.sasong.namn"/></title>
    
    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <%@ include file="include/context/dromelvan_tabell.jsp" %>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Drömelvan oturtabell</h2>

            <h3><struts:property value="tavling.namn"/> <struts:property value="tavling.sasong.namn"/> - Oturtabell</h3>

            <table class="tabell" id="dromelvan_tabell">
                <colgroup>
                    <col class="placering_column"/>
                    <col class="deltagare_column"/>
                    <col class="matcher_spelade_column"/>
                    <col class="matcher_vunna_column"/>
                    <col class="matcher_oavgjorda_column"/>
                    <col class="matcher_forlorade_column"/>
                    <col class="mal_gjorda_column"/>
                    <col class="separator_column"/>
                    <col class="mal_inslappta_column"/>
                    <col class="poang_column"/>
                </colgroup>
                <tr>
                    <th>#</th>
                    <th class="deltagare">Deltagare</th>
                    <th>M.</th>
                    <th>V.</th>
                    <th>O.</th>
                    <th>F.</th>
                    <th colspan="3">Målskillnad</th>
                    <th>OP</th>
                </tr>
    
                <struts:iterator value="tabell.tabellObjekt">
                <struts:if test="isSeparator">
                <tr class="separator">
                    <td class="separator" colspan="10"></td>
                </tr>                
                </struts:if>
                <struts:else>
                <tr>
                    <td><struts:property value="placering"/></td>
                    <td class="deltagare">
                        <struts:push value="deltagare">
                            <%@ include file="include/deltagare_lank.jsp" %>
                        </struts:push>                    
                    </td>
                    <td><struts:property value="antalMatcherSpelade"/></td>
                    <td><struts:property value="antalMatcherVunna"/></td>
                    <td><struts:property value="antalMatcherOavgjorda"/></td>
                    <td><struts:property value="antalMatcherForlorade"/></td>
                    <td><struts:property value="antalMalGjorda"/></td>
                    <td> - </td>
                    <td><struts:property value="antalMalInslappta"/></td>
                    <td><struts:property value="oturPoang"/></td>                
                </tr>
                </struts:else>
                </struts:iterator>
                <tr class="tabell_forklaring">
                    <td colspan="10"># = Placering, M = Matcher, V = Vunna, O = Oavgjorda, F = Förlorade, OP = Oturpoäng</td>
                </tr>
            </table>
            
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
