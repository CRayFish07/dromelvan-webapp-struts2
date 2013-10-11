            <%@ page contentType="text/html; charset=utf-8" %>
            <h3>Tabell</h3>
            <h4 class="top">Tabeller</h4>
            <ul>
                <struts:url var="url" action="DromelvanTabell">
                    <struts:param name="tavlingId" value="tavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Tabell</struts:a></li>                
                <struts:url var="url" action="DromelvanTabell">
                    <struts:param name="tavlingId" value="tavling.id"/>
                    <struts:param name="haltande" value="true"/>
                </struts:url>            
                <li><struts:a href="%{url}">Haltande tabell</struts:a></li>
                <struts:url var="url" action="DromelvanOturTabell">
                    <struts:param name="tavlingId" value="tavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Oturtabell</struts:a></li>
            </ul>
            <h4 class="sasong_menu">Säsong <struts:property value="tavling.sasong.namn"/></h4>            
            <ul class="sasong_menu">
                <struts:url var="url" action="Dromelvan">
                    <struts:param name="tavlingId" value="tavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Matcher</struts:a></li>
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
                <struts:url var="url" action="DromelvanTabell">
                    <struts:param name="tavlingId" value="id"/>
                </struts:url>            
                <li><struts:a href="%{url}"><struts:property value="sasong.namn"/></struts:a></li>                
                </struts:iterator>
            </ul>
            <h4 class="small_menu">Säsonger</h4>
            <ul class="small_menu">            
                <struts:url var="url" action="DromelvanTabell">
                    <struts:param name="tavlingId" value="tavling.nastaSasongTavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Nästa säsong</struts:a></li>
                <struts:url var="url" action="DromelvanTabell">
                    <struts:param name="tavlingId" value="tavling.foregaendeSasongTavling.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Föregående säsong</struts:a></li>                                                
            </ul>