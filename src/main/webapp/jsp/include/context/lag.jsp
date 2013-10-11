            <%@ page contentType="text/html; charset=utf-8" %>
            <h3>Lag</h3>
            <h4 class="top">Lag</h4>
            <ul>
                <struts:url var="url" action="LagMatcher">
                    <struts:param name="lagId" value="lagId"/>
                    <struts:param name="sasongId" value="sasongId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Matcher</struts:a></li>
                <struts:url var="url" action="LagSpelarTrupp">
                    <struts:param name="lagId" value="lagId"/>
                    <struts:param name="sasongId" value="sasongId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Spelartrupp</struts:a></li>
            </ul>
            <h4 class="big_menu">Säsonger</h4>
            <ul class="big_menu">
                <struts:iterator value="sasonger">
                    <struts:url var="url" action="%{actionNamn}">
                    <struts:param name="lagId" value="lagId"/>
                    <struts:param name="sasongId" value="id"/>
                </struts:url>            
                <li><struts:a href="%{url}"><struts:property value="namn"/></struts:a></li>
                </struts:iterator>
            </ul>
            <h4 class="small_menu">Säsonger</h4>
            <ul class="small_menu">            
                <struts:url var="url" action="%{actionNamn}">
                    <struts:param name="lagId" value="lagId"/>
                    <struts:param name="sasongId" value="sasong.nastaSasong.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Nästa säsong</struts:a></li>
                <struts:url var="url" action="%{actionNamn}">
                    <struts:param name="lagId" value="lagId"/>
                    <struts:param name="sasongId" value="sasong.foregaendeSasong.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Föregående säsong</struts:a></li>                                                
            </ul>                      
            <h4 class="administration_menu">Administration</h4>
            <ul class="administration_menu">
                <struts:url var="url" action="InsertSpelareForm">
                    <struts:param name="lagId" value="lagId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Lägg in ny spelare</struts:a></li>
            </ul>

