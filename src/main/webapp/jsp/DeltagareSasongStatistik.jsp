<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Drömelvan > <struts:property value="deltagare.namn"/> - Säsongstatistik <struts:property value="tavling.sasong.namn"/></title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <%@ include file="include/context/deltagare.jsp" %>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Premier League</h2>

            <h3>Säsongstatistik <struts:property value="tavling.sasong.namn"/></h3>
            <%@ include file="include/deltagare_header.jsp" %>

            <table>
                <colgroup>
                    <col class="spelare_column"/>
                    <col class="position_column"/>
                    <col class="placering_column"/>
                    <col class="matcher_column"/>
                    <col class="mal_column"/>
                    <col class="assist_column"/>
                    <col class="mom_column"/>
                    <col class="gult_kort_column"/>
                    <col class="rott_kort_column"/>
                    <col class="medel_rating_column"/>
                    <col class="poang_column"/>
                    <col class="lag_column"/>
                </colgroup>
                <tr>
                    <th class="spelare">Namn</th>
                    <th>Pos.</th>
                    <th>#</th>
                    <th>M.</th>
                    <th>Mål</th>
                    <th>A.</th>
                    <th>MoM</th>
                    <th>GK</th>
                    <th>RK</th>
                    <th>R.</th>
                    <th>P.</th>
                    <th class="lag">Lag</th>
                </tr>
                <struts:iterator value="spelareDeltagareSasongStatistik">
                <tr>
                    <td class="spelare">
                        <struts:push value="spelareSasong.spelare">
                        <%@ include file="include/spelare_lank.jsp" %>                            
                        </struts:push>
                    </td>
                    <td><struts:property value="spelareSasong.position.kod"/></td>
                    <td><struts:property value="spelareSasong.placering"/></td>
                    <%@ include file="include/spelare_sasong_statistik.jsp" %>
                    <td class="lag">
                        <struts:push value="spelareSasong.lag">                            
                        <%@ include file="include/lag_lank.jsp" %>
                        </struts:push>
                    </td>
                </tr>
                </struts:iterator>
                <tr class="tabell_forklaring">
                   <td colspan="12">
                      Pos = Position, # = Placering i statistiken, M = Matcher, A = Assist,
                      GK = gula kort, RK = röda kort, R = Medelrating, P = Drömelvapoäng
                   </td>
                </tr>                
            </table>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
