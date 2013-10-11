<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Premier League > <struts:property value="lagSpelarTrupp.lag.namn"/> - Spelartrupp <struts:property value="lagSpelarTrupp.sasong.namn"/></title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <%@ include file="include/context/lag.jsp" %>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Premier League</h2>
            <h3>Spelartrupp <struts:property value="lagSpelarTrupp.sasong.namn"/></h3>

            <div class="lag_header">
                <img class="lag_klubbmarke" src="bilder/lag/klubbmarke_<struts:property value="lagSpelarTrupp.lag.id"/>.gif" alt="<struts:property value="lagSpelarTrupp.lag.namn"/>"/>
                <table>
                    <colgroup>
                        <col class="vanster_column"/>
                        <col class="hoger_column"/>
                    </colgroup>
                    <tr>
                        <th class="namn" colspan="2">
                            <struts:property value="lagSpelarTrupp.lag.namn"/>
                        </th>
                    </tr>
                    <tr>
                        <td>Spelare: <struts:property value="lagSpelarTrupp.antalSpelare"/></td>
                        <td>Pris: <struts:property value="lagSpelarTrupp.pris"/></td>
                    </tr>
                    <tr>
                        <td>Mål/Assist: <struts:property value="lagSpelarTrupp.antalMal"/>/<struts:property value="lagSpelarTrupp.antalAssist"/></td>
                        <td>Kort (G/R): <struts:property value="lagSpelarTrupp.antalGultKort"/>/<struts:property value="lagSpelarTrupp.antalRottKort"/></td>
                    </tr>
                    <tr>
                        <td>Poäng*: <struts:property value="lagSpelarTrupp.poang"/></td>
                        <td>Medelpoäng*: <struts:property value="lagSpelarTrupp.medelPoang"/></td>
                    </tr>
                    <tr>
                        <td>MoM: <struts:property value="lagSpelarTrupp.antalMom"/>/<struts:property value="lagSpelarTrupp.antalDeladMom"/></td>                                            
                        <td>Medelrating*: <struts:property value="lagSpelarTrupp.medelRating"/></td>                        
                    </tr>
                </table>
            </div>

            <div>
                <table class="lag_del">
                    <colgroup>
                        <col class="spelare_column"/>
                        <col class="matcher_column"/>
                        <col class="mal_column"/>
                        <col class="assist_column"/>
                        <col class="mom_column"/>
                        <col class="gult_kort_column"/>
                        <col class="rott_kort_column"/>
                        <col class="medel_rating_column"/>
                        <col class="poang_column"/>
                        <col class="deltagare_column"/>
                    </colgroup>
                    <tr>
                        <th class="spelare">Målvakter</th>
                        <th>M.</th>
                        <th>Mål</th>
                        <th>A.</th>
                        <th>MoM</th>
                        <th>GK</th>
                        <th>RK</th>
                        <th>R.</th>
                        <th>P.</th>
                        <th class="deltagare">Deltagare</th>
                    </tr>
                    <struts:iterator value="lagSpelarTrupp.malvakter">
                    <tr>
                        <struts:push value="spelare">
                        <td class="spelare"><%@ include file="include/spelare_lank.jsp" %></td>
                        </struts:push>
                        <%@ include file="include/spelare_sasong_statistik.jsp" %>
                        <td class="deltagare">
                        <struts:push value="deltagare">
                            <%@ include file="include/deltagare_lank.jsp" %>
                        </struts:push>
                        </td>
                    </tr>
                    </struts:iterator>
                </table>
            </div>
            <struts:if test="lagSpelarTrupp.sasong.uppdeladBacklinje">
            <div>
                <table class="lag_del">
                    <colgroup>
                        <col class="spelare_column"/>
                        <col class="matcher_column"/>
                        <col class="mal_column"/>
                        <col class="assist_column"/>
                        <col class="mom_column"/>
                        <col class="gult_kort_column"/>
                        <col class="rott_kort_column"/>
                        <col class="medel_rating_column"/>
                        <col class="poang_column"/>
                        <col class="deltagare_column"/>
                    </colgroup>
                    <tr>
                        <th class="spelare">Ytterbackar</th>
                        <th>M.</th>
                        <th>Mål</th>
                        <th>A.</th>
                        <th>MoM</th>
                        <th>GK</th>
                        <th>RK</th>
                        <th>R.</th>
                        <th>P.</th>
                        <th class="deltagare">Deltagare</th>
                    </tr>
                    <struts:iterator value="lagSpelarTrupp.ytterbackar">
                    <tr>
                        <struts:push value="spelare">
                        <td class="spelare"><%@ include file="include/spelare_lank.jsp" %></td>
                        </struts:push>
                        <%@ include file="include/spelare_sasong_statistik.jsp" %>
                        <td class="deltagare">
                        <struts:push value="deltagare">
                            <%@ include file="include/deltagare_lank.jsp" %>
                        </struts:push>
                        </td>
                    </tr>
                    </struts:iterator>
                </table>
            </div>
            </struts:if>
            <div>
                <table class="lag_del">
                    <colgroup>
                        <col class="spelare_column"/>
                        <col class="matcher_column"/>
                        <col class="mal_column"/>
                        <col class="assist_column"/>
                        <col class="mom_column"/>
                        <col class="gult_kort_column"/>
                        <col class="rott_kort_column"/>
                        <col class="medel_rating_column"/>
                        <col class="poang_column"/>
                        <col class="deltagare_column"/>
                    </colgroup>
                    <tr>
                        <th class="spelare"><struts:if test="lagSpelarTrupp.sasong.uppdeladBacklinje">Innerbackar</struts:if><struts:else>Backar</struts:else></th>
                        <th>M.</th>
                        <th>Mål</th>
                        <th>A.</th>
                        <th>MoM</th>
                        <th>GK</th>
                        <th>RK</th>
                        <th>R.</th>
                        <th>P.</th>
                        <th class="deltagare">Deltagare</th>
                    </tr>
                    <struts:iterator value="lagSpelarTrupp.innerbackar">
                    <tr>
                        <struts:push value="spelare">
                        <td class="spelare"><%@ include file="include/spelare_lank.jsp" %></td>
                        </struts:push>
                        <%@ include file="include/spelare_sasong_statistik.jsp" %>
                        <td class="deltagare">
                        <struts:push value="deltagare">
                            <%@ include file="include/deltagare_lank.jsp" %>
                        </struts:push>
                        </td>
                    </tr>
                    </struts:iterator>
                </table>
            </div>
            <div>
                <table class="lag_del">
                    <colgroup>
                        <col class="spelare_column"/>
                        <col class="matcher_column"/>
                        <col class="mal_column"/>
                        <col class="assist_column"/>
                        <col class="mom_column"/>
                        <col class="gult_kort_column"/>
                        <col class="rott_kort_column"/>
                        <col class="medel_rating_column"/>
                        <col class="poang_column"/>
                        <col class="deltagare_column"/>
                    </colgroup>
                    <tr>
                        <th class="spelare">Mittfältare</th>
                        <th>M.</th>
                        <th>Mål</th>
                        <th>A.</th>
                        <th>MoM</th>
                        <th>GK</th>
                        <th>RK</th>
                        <th>R.</th>
                        <th>P.</th>
                        <th class="deltagare">Deltagare</th>
                    </tr>
                    <struts:iterator value="lagSpelarTrupp.mittfaltare">
                    <tr>
                        <struts:push value="spelare">
                        <td class="spelare"><%@ include file="include/spelare_lank.jsp" %></td>
                        </struts:push>
                        <%@ include file="include/spelare_sasong_statistik.jsp" %>
                        <td class="deltagare">
                        <struts:push value="deltagare">
                            <%@ include file="include/deltagare_lank.jsp" %>
                        </struts:push>
                        </td>
                    </tr>
                    </struts:iterator>
                </table>
            </div>
            <div>
                <table class="lag_del">
                    <colgroup>
                        <col class="spelare_column"/>
                        <col class="matcher_column"/>
                        <col class="mal_column"/>
                        <col class="assist_column"/>
                        <col class="mom_column"/>
                        <col class="gult_kort_column"/>
                        <col class="rott_kort_column"/>
                        <col class="medel_rating_column"/>
                        <col class="poang_column"/>
                        <col class="deltagare_column"/>
                    </colgroup>
                    <tr>
                        <th class="spelare">Anfallare</th>
                        <th>M.</th>
                        <th>Mål</th>
                        <th>A.</th>
                        <th>MoM</th>
                        <th>GK</th>
                        <th>RK</th>
                        <th>R.</th>
                        <th>P.</th>
                        <th class="deltagare">Deltagare</th>
                    </tr>
                    <struts:iterator value="lagSpelarTrupp.anfallare">
                    <tr>
                        <struts:push value="spelare">
                        <td class="spelare"><%@ include file="include/spelare_lank.jsp" %></td>
                        </struts:push>
                        <%@ include file="include/spelare_sasong_statistik.jsp" %>
                        <td class="deltagare">
                        <struts:push value="deltagare">
                            <%@ include file="include/deltagare_lank.jsp" %>
                        </struts:push>
                        </td>
                    </tr>
                    </struts:iterator>
                <tr class="tabell_forklaring">
                    <td colspan="10">
                        Pos = Position, M = Matcher (Med tillräcklig medverkan för att få rating),
                        A = Assist, GK = gula kort, RK = röda kort, R = Medelrating, P = Drömelvapoäng
                    </td>
                </tr>
                <tr class="tabell_forklaring">
                    <td colspan="10">
                        *Bara statistik för spelare som spelat minst en match räknas. Försvarare som
                        spenderat hela säsongen på bänken eller i reservlaget drar alltså inte ner värdet.
                    </td>
                </tr>
                </table>               
            </div>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
