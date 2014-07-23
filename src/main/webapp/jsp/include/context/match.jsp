            <%@ page contentType="text/html; charset=utf-8" %>
            <h3>Match</h3>
            <h4 class="top">Information</h4>
            <ul>
                <struts:url var="url" action="MatchStatistik">
                    <struts:param name="matchId" value="match.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Matchstatistik</struts:a></li>
                <struts:url var="url" action="MatchStatistikDromelvan">
                    <struts:param name="matchId" value="match.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Drömelvastatistik</struts:a></li>
            </ul>
            <h4 class="sasong_menu">Säsong <struts:property value="match.omgang.tavling.sasong.namn"/></h4>
            <ul class="sasong_menu">
                <struts:url var="url" action="PremierLeague">
                    <struts:param name="tavlingId" value="match.omgang.tavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Matcher</struts:a></li>
                <struts:url var="url" action="PremierLeague" anchor="%{match.omgang.id}">
                    <struts:param name="tavlingId" value="match.omgang.tavling.id"/>
                    <struts:param name="startOmgang" value="match.omgang.startOmgangNummer"/>
                </struts:url>            
                <li><struts:a href="%{url}"><struts:property value="match.omgang.namn"/></struts:a></li>
                <struts:url var="url" action="PremierLeagueTabell">
                    <struts:param name="tavlingId" value="match.omgang.tavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Tabell</struts:a></li>                                
            </ul>

            <h4 class="administration_menu">Administration</h4>
            <ul class="administration_menu">
                <struts:url var="url" action="EditMatchDatumForm">
                    <struts:param name="matchId" value="match.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Ändra datum</struts:a></li>
                <struts:if test="match.isInlagd">
                <struts:url var="url" action="UploadJAXBRatingsForm">
                    <struts:param name="matchId" value="match.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Ladda upp ratings</struts:a></li>                                    
                <struts:url var="url" action="UpdateJAXBMatchStatistikForm">
                    <struts:param name="matchId" value="match.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Uppdatera statistik</struts:a></li>                
                </struts:if>
                <struts:else>
                <struts:url var="url" action="UploadJAXBMatchStatistikForm">
                    <struts:param name="matchId" value="match.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Ladda upp statistik</struts:a></li>                                    
                </struts:else>
            </ul>
