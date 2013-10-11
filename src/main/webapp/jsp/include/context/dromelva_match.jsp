            <%@ page contentType="text/html; charset=utf-8" %>
            <h3>Drömelvamatch</h3>
            <h4 class="top sasong_menu">Säsong <struts:property value="deMatch.deOmgang.tavling.sasong.namn"/></h4>
            <ul class="sasong_menu">
                <struts:url var="url" action="Dromelvan">
                    <struts:param name="tavlingId" value="deMatch.deOmgang.tavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Matcher</struts:a></li>

                <struts:url var="url" action="Dromelvan" anchor="%{deMatch.deOmgang.id}">
                    <struts:param name="tavlingId" value="deMatch.deOmgang.tavling.id"/>
                    <struts:param name="startOmgang" value="deMatch.deOmgang.omgang.startOmgangNummer"/>
                </struts:url>            
                <li><struts:a href="%{url}"><struts:property value="deMatch.deOmgang.namn"/></struts:a></li>

                <struts:url var="url" action="DromelvanTabell">
                    <struts:param name="tavlingId" value="deMatch.deOmgang.tavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Tabell</struts:a></li>
                <struts:url var="url" action="AllasLag">
                    <struts:param name="tavlingId" value="deMatch.deOmgang.tavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Allas lag</struts:a></li>
                <struts:url var="url" action="DromelvaByten">
                    <struts:param name="tavlingId" value="deMatch.deOmgang.tavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Byten</struts:a></li>
            </ul>
