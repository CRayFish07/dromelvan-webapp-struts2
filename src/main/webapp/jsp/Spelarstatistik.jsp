<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Statistik > Spelarstatistik <struts:property value="tavling.sasong.namn"/></title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <h3>Spelarstatistik</h3>
            <h4 class="sasong_menu">Säsong <struts:property value="tavling.sasong.namn"/></h4>
            <ul class="sasong_menu">
                <struts:url var="url" action="DownloadSpelarstat">
                    <struts:param name="sasongId" value="tavling.sasong.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Statistik i excelformat</struts:a></li>
            </ul>
            <h4 class="big_menu">Säsonger</h4>
            <ul class="big_menu">
                <struts:iterator value="tavlingar">
                <struts:url var="url" action="Spelarstatistik">
                    <struts:param name="tavlingId" value="id"/>
                </struts:url>            
                <li><struts:a href="%{url}"><struts:property value="sasong.namn"/></struts:a></li>
                </struts:iterator>
            </ul>
            
            <h4 class="small_menu">Säsonger</h4>
            <ul class="small_menu">            
                <struts:url var="url" action="Spelarstatistik">
                    <struts:param name="tavlingId" value="tavling.nastaSasongTavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Nästa säsong</struts:a></li>
                <struts:url var="url" action="Spelarstatistik">
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

            <h2>Spelarstatistik</h2>

            <h3><struts:property value="tavling.namn"/> <struts:property value="tavling.sasong.namn"/></h3>

            <p>
                Spelarstatistik för den aktuella säsongen. Klicka på en kolumnrubrik
                för att sortera det hela efter den kolumnen.
            </p>

            <table id="spelarstatistik" class="max">
                <colgroup>
                    <col class="placering_column"/>
                    <col class="spelare_column"/>
                    <col class="lag_kod_column"/>
                    <col class="position_column"/>
                    <col class="matcher_column"/>
                    <col class="mal_column"/>
                    <col class="assist_column"/>
                    <col class="mom_column"/>
                    <col class="gult_kort_column"/>
                    <col class="rott_kort_column"/>
                    <col class="medel_rating_column"/>
                    <col class="poang_summa_column"/>
                    <col class="deltagare_column"/>
                </colgroup>
                <tr>
                    <th>#</th>
                    <th class="spelare">Spelare</th>
                    <th>Lag</th>
                    <struts:url var="url" action="Spelarstatistik">
                        <struts:param name="tavlingId" value="tavlingId"/>
                        <struts:param name="kolumn" value="3"/>                        
                    </struts:url>            
                    <th><struts:a href="%{url}">Pos</struts:a></th>
                    <struts:url var="url" action="Spelarstatistik">
                        <struts:param name="tavlingId" value="tavlingId"/>
                        <struts:param name="kolumn" value="4"/>                        
                    </struts:url>            
                    <th><struts:a href="%{url}">M.</struts:a></th>
                    <struts:url var="url" action="Spelarstatistik">
                        <struts:param name="tavlingId" value="tavlingId"/>
                        <struts:param name="kolumn" value="5"/>                        
                    </struts:url>            
                    <th><struts:a href="%{url}">Mål</struts:a></th>
                    <struts:url var="url" action="Spelarstatistik">
                        <struts:param name="tavlingId" value="tavlingId"/>
                        <struts:param name="kolumn" value="10"/>                        
                    </struts:url>            
                    <th><struts:a href="%{url}">A.</struts:a></th>                    
                    <struts:url var="url" action="Spelarstatistik">
                        <struts:param name="tavlingId" value="tavlingId"/>
                        <struts:param name="kolumn" value="6"/>                        
                    </struts:url>            
                    <th><struts:a href="%{url}">MoM</struts:a></th>
                    <struts:url var="url" action="Spelarstatistik">
                        <struts:param name="tavlingId" value="tavlingId"/>
                        <struts:param name="kolumn" value="7"/>                        
                    </struts:url>            
                    <th><struts:a href="%{url}">GK</struts:a></th>
                    <struts:url var="url" action="Spelarstatistik">
                        <struts:param name="tavlingId" value="tavlingId"/>
                        <struts:param name="kolumn" value="8"/>                        
                    </struts:url>            
                    <th><struts:a href="%{url}">RK</struts:a></th>
                    <struts:url var="url" action="Spelarstatistik">
                        <struts:param name="tavlingId" value="tavlingId"/>
                        <struts:param name="kolumn" value="9"/>                        
                    </struts:url>            
                    <th><struts:a href="%{url}">R.</struts:a></th>
                    <struts:url var="url" action="Spelarstatistik">
                        <struts:param name="tavlingId" value="tavlingId"/>
                    </struts:url>            
                    <th><struts:a href="%{url}">P.</struts:a></th>
                    <th class="deltagare">Deltagare</th>
                </tr>
                    
                <struts:iterator value="spelareSasongStatistik">
                <tr class="tabell_rad" valign="top">
                    <td><struts:property value="placering"/></td>
                    <td class="spelare">
                        <struts:url var="url" action="SpelareStatistik">
                            <struts:param name="spelareId" value="spelare.id"/>
                            <struts:param name="sasongId" value="sasong.id"/>
                        </struts:url>
                        <struts:if test="mobile">
                        <struts:a href="%{url}"><struts:property value="spelare.kortNamn"/></struts:a>
                        </struts:if>
                        <struts:else>
                        <struts:a href="%{url}"><struts:property value="spelare.namn"/></struts:a>
                        </struts:else>
                    </td>
                    <td>
                        <struts:url var="url" action="LagSpelarTrupp">
                            <struts:param name="lagId" value="lag.id"/>
                            <struts:param name="sasongId" value="sasong.id"/>
                        </struts:url>            
                        <struts:a href="%{url}"><struts:property value="lag.kod"/></struts:a>              
                    </td>
                    <td><struts:property value="position.kod"/></td>
                    <%@ include file="include/spelare_sasong_statistik.jsp" %>
                    <td class="deltagare">
                        <struts:push value="deltagare">
                        <%@ include file="include/deltagare_lank.jsp" %>
                        </struts:push>
                    </td>
                </tr>
                </struts:iterator>
                <tr class="tabell_forklaring">
                    <td colspan="13">
                        # = Placering (enl. poäng), Pos = Position, M = Matcher, A = Assist, GK = gula kort,
                        RK = röda kort, R = Medelrating, P = Drömelvapoäng                        
                    </td>
                </tr>
            </table>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
