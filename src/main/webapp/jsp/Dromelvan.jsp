<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Drömelvan > Matcher <struts:property value="tavling.sasong.namn"/></title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <h3>Matcher</h3>
            <h4 class="top">Omgångar</h4>
            <ul>
                <struts:url var="url" action="Dromelvan">
                    <struts:param name="tavlingId" value="tavling.id"/>
                    <struts:param name="startOmgang" value="1"/>                    
                </struts:url>            
                <li><struts:a href="%{url}">Omgång 1-10</struts:a></li>                
                <struts:url var="url" action="Dromelvan">
                    <struts:param name="tavlingId" value="tavling.id"/>
                    <struts:param name="startOmgang" value="11"/>                    
                </struts:url>            
                <li><struts:a href="%{url}">Omgång 11-20</struts:a></li>                
                <struts:url var="url" action="Dromelvan">
                    <struts:param name="tavlingId" value="tavling.id"/>
                    <struts:param name="startOmgang" value="21"/>                    
                </struts:url>            
                <li><struts:a href="%{url}">Omgång 21-30</struts:a></li>                
                <struts:url var="url" action="Dromelvan">
                    <struts:param name="tavlingId" value="tavling.id"/>
                    <struts:param name="startOmgang" value="31"/>                    
                </struts:url>            
                <li><struts:a href="%{url}">Omgång 31-38</struts:a></li>
                <struts:url var="url" action="Dromelvan" anchor="%{senastSpeladDeOmgang.id}">
                    <struts:param name="tavlingId" value="tavling.id"/>
                    <struts:param name="startOmgang" value="senastSpeladDeOmgang.omgang.startOmgangNummer"/>
                </struts:url>            
                <li><struts:a href="%{url}">Senast spelad</struts:a></li>                
                
            </ul>
            <h4 class="sasong_menu">Säsong <struts:property value="tavling.sasong.namn"/></h4>
            <ul class="sasong_menu">
                <struts:url var="url" action="DromelvanTabell">
                    <struts:param name="tavlingId" value="tavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Tabell</struts:a></li>
                <struts:url var="url" action="AllasLag">
                    <struts:param name="tavlingId" value="tavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Allas lag</struts:a></li>
                <struts:url var="url" action="DromelvaByten">
                    <struts:param name="tavlingId" value="tavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Byten</struts:a></li>
            </ul>
            <h4 class="big_menu">Säsonger</h4>            
            <ul class="big_menu">
                <struts:iterator value="tavlingar">
                <struts:url var="url" action="Dromelvan">
                    <struts:param name="tavlingId" value="id"/>
                </struts:url>            
                <li><struts:a href="%{url}"><struts:property value="sasong.namn"/></struts:a></li>                
                </struts:iterator>
            </ul>                
            <h4 class="small_menu">Säsonger</h4>
            <ul class="small_menu">            
                <struts:url var="url" action="Dromelvan">
                    <struts:param name="tavlingId" value="tavling.nastaSasongTavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Nästa säsong</struts:a></li>
                <struts:url var="url" action="Dromelvan">
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

            <h3><struts:property value="tavling.namn"/> <struts:property value="tavling.sasong.namn"/></h3>
            
            <p class="omgangShortcuts">
                Omgång:
                <struts:iterator value="omgangarSubList">
                    <span class="anchor_links">
                        <a href="#<struts:property value="id"/>">
                            <struts:property value="nummer"/>
                        </a>
                    </span>
                </struts:iterator>
            </p>
            
            <struts:iterator value="omgangarSubList">
            <div>
                <table class="matcher">
                    <colgroup>
                        <col class="deltagare_column"/>
                        <col class="separator_column"/>
                        <col class="deltagare_column"/>
                        <col class="resultat_column"/>
                        <col class="poang_column"/>
                        <col class="separator_column"/>
                        <col class="poang_column"/>
                    </colgroup>
                    <tr>
                        <th class="omgang_namn" colspan="3">
                            <a name="<struts:property value="id"/>"/>
                            <struts:url var="url" action="PremierLeague" anchor="%{omgang.id}">
                                <struts:param name="tavlingId" value="omgang.tavling.id"/>
                                <struts:param name="startOmgang" value="omgang.startOmgangNummer"/>
                            </struts:url>            
                            <struts:a href="%{url}"><struts:property value="namn"/></struts:a>
                            <struts:if test="omgang.isSpelad && !omgang.isInlagd">
                            &nbsp;(<struts:property value="omgang.antalMatcherKvar"/> matcher kvar)
                            </struts:if>
                        </th>
                        <th>
                            Resultat
                        </th>
                        <th colspan="3">
                            Poäng
                        </th>
                    </tr>
                    <struts:iterator value="deMatcher">
                    <tr>
                        <td class="lag"><struts:push value="hemmaDeltagare">
                            <%@ include file="include/deltagare_lank.jsp" %>
                        </td></struts:push>
                        <td>-</td>
                        <td class="lag"><struts:push value="bortaDeltagare">
                            <%@ include file="include/deltagare_lank.jsp" %>
                        </td></struts:push>
                        <td>
                            <struts:if test="omgang.isSpelad">
                            <struts:url var="url" action="DromelvaMatchStatistik">
                                <struts:param name="matchId" value="id"/>
                            </struts:url>            
                            <struts:a href="%{url}">
                                <struts:if test="omgang.isPaborjad">
                                <struts:property value="antalMalHemmaDeltagare"/> - <struts:property value="antalMalBortaDeltagare"/>
                                </struts:if>
                                <struts:else>
                                    Spelad
                                </struts:else>                            
                            </struts:a>
                            </struts:if>
                            <struts:else>
                                Ej spelad
                            </struts:else>
                        </td>
                        <td><struts:property value="antalPoangHemmaDeltagare"/></td>
                        <td> - </td>
                        <td><struts:property value="antalPoangBortaDeltagare"/></td>
                    </tr>
                    </struts:iterator>
                </table>
            </div>
            </struts:iterator>

        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
