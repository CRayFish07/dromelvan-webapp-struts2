        <%@ page contentType="text/html; charset=utf-8" %>
        <div id="menu" class="group">
            <h3>Avdelningar</h3>
            <h4 class="top">Information</h4>
            <ul>            
                <li><a href="<struts:url action="Index"/>">Nyheter</a></li>
                <li><a href="<struts:url action="Regler"/>">Regler</a></li>
                <li><a href="<struts:url action="OmDromelvan"/>">Om Drömelvan</a></li>
            </ul>
            <h4>Drömelvan</h4>
            <ul>
                <struts:url var="url" action="Dromelvan">
                    <struts:param name="tavlingId" value="defaultDeTavlingId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Matcher</struts:a></li>
                <struts:url var="url" action="Dromelvan" anchor="%{senastSpeladDeOmgang.id}">
                    <struts:param name="tavlingId" value="defaultDeTavlingId"/>
                    <struts:param name="startOmgang" value="senastSpeladDeOmgang.omgang.startOmgangNummer"/>
                </struts:url>            
                <li><struts:a href="%{url}">Senaste omgång</struts:a></li>                                
                <struts:url var="url" action="DromelvanTabell">
                    <struts:param name="tavlingId" value="defaultDeTavlingId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Tabell</struts:a></li>                
                <struts:url var="url" action="AllasLag">
                    <struts:param name="tavlingId" value="defaultDeTavlingId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Allas lag</struts:a></li>
                <struts:url var="url" action="DromelvaByten">
                    <struts:param name="tavlingId" value="defaultDeTavlingId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Byten</struts:a></li>
            </ul>
            <h4>Premier League</h4>
            <ul>            
                <struts:url var="url" action="PremierLeague">
                    <struts:param name="tavlingId" value="defaultTavlingId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Matcher</struts:a></li>
                <struts:url var="url" action="PremierLeague" anchor="%{senastSpeladOmgang.id}">
                    <struts:param name="tavlingId" value="defaultTavlingId"/>
                    <struts:param name="startOmgang" value="senastSpeladOmgang.startOmgangNummer"/>
                </struts:url>            
                <li><struts:a href="%{url}">Senaste omgång</struts:a></li>                                
                <struts:url var="url" action="PremierLeagueTabell">
                    <struts:param name="tavlingId" value="defaultTavlingId"/>
                </struts:url>                
                <li><struts:a href="%{url}">Tabell</struts:a></li>                
            </ul>
            
            <h4>Statistik</h4>
            <ul>
                <struts:url var="url" action="Spelarstatistik">
                    <struts:param name="tavlingId" value="defaultTavlingId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Spelarstatistik</struts:a></li>
                <li><a href="<struts:url action="HallOfFame"/>">Hall of Fame</a></li>
                <li><a href="<struts:url action="Nerladdningar"/>">Statistikfiler</a></li>
            </ul>
            <h4 class="administration_menu">Administration</h4>
            <ul class="administration_menu">
                <li><a href="<struts:url action="LoginForm"/>">Auktorisering</a></li>                
                <li><a href="<struts:url action="Administration"/>">Administrationsfunktioner</a></li>
            </ul>

            <h4>Länkar</h4>
            <ul>
                <%-- <li><a href="/forum/index.php">Forum</a></li> --%>
                <li><a href="<struts:url action="Lankar"/>">Övrigt</a></li>
            </ul>                        
        </div>
        
        <div id="snabb_menu" class="group">
            <h3>Snabbmeny</h3>
            
            <h4>Snabbmeny</h4>
            <ul>
                <struts:url var="url" action="Dromelvan" anchor="%{senastSpeladDeOmgang.id}">
                    <struts:param name="tavlingId" value="defaultDeTavlingId"/>
                    <struts:param name="startOmgang" value="senastSpeladDeOmgang.omgang.startOmgangNummer"/>
                </struts:url>            
                <li><struts:a href="%{url}">Drömelvan - Senaste omgång</struts:a></li>                                
                <struts:url var="url" action="DromelvanTabell">
                    <struts:param name="tavlingId" value="defaultDeTavlingId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Drömelvan - Tabell</struts:a></li>                
                <struts:url var="url" action="AllasLag">
                    <struts:param name="tavlingId" value="defaultDeTavlingId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Drömelvan - Allas lag</struts:a></li>

                <struts:url var="url" action="PremierLeague" anchor="%{senastSpeladOmgang.id}">
                    <struts:param name="tavlingId" value="defaultTavlingId"/>
                    <struts:param name="startOmgang" value="senastSpeladOmgang.startOmgangNummer"/>
                </struts:url>            
                <li><struts:a href="%{url}">Premier League - Senaste omgång</struts:a></li>                                
                <struts:url var="url" action="PremierLeagueTabell">
                    <struts:param name="tavlingId" value="defaultTavlingId"/>
                </struts:url>                
                <li><struts:a href="%{url}">Premier League - Tabell</struts:a></li>                

                <struts:url var="url" action="Spelarstatistik">
                    <struts:param name="tavlingId" value="defaultTavlingId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Spelarstatistik</struts:a></li>
                
                <li><a href="<struts:url action="HuvudMeny"/>">HuvudMeny</a></li>                
            </ul>
        </div>        
        