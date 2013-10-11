            <%@ page contentType="text/html; charset=utf-8" %>
            <h3>Deltagare</h3>
            <h4 class="top">Deltagare</h4>
            <ul>
                <struts:url var="url" action="DeltagareMatcher">
                    <struts:param name="deltagareId" value="deltagareId"/>
                    <struts:param name="sasongId" value="sasongId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Matcher</struts:a></li>
                <struts:url var="url" action="DeltagareSpelarTrupp">
                    <struts:param name="deltagareId" value="deltagareId"/>
                    <struts:param name="sasongId" value="sasongId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Spelartrupp</struts:a></li>
                <struts:url var="url" action="DeltagareSasongStatistik">
                    <struts:param name="deltagareId" value="deltagareId"/>
                    <struts:param name="sasongId" value="sasongId"/>
                </struts:url>            
                <li><struts:a href="%{url}">Säsongstatistik</struts:a></li>                
                <struts:url var="url" action="DeltagareSasongStatistikByOmgang">
                    <struts:param name="deltagareId" value="deltagareId"/>
                    <struts:param name="sasongId" value="sasongId"/>
                </struts:url>
                <struts:if test="mobile == false">
                <li><struts:a href="%{url}">Poäng per omgång</struts:a></li>
                </struts:if>
            </ul>
            <h4 class="big_menu">Säsonger</h4>
            <ul class="big_menu">
                <struts:iterator value="sasonger">
                <struts:url var="url" action="%{actionNamn}">
                    <struts:param name="deltagareId" value="deltagareId"/>
                    <struts:param name="sasongId" value="id"/>
                </struts:url>            
                <li><struts:a href="%{url}"><struts:property value="namn"/></struts:a></li>
                </struts:iterator>
            </ul>
            <h4 class="small_menu">Säsonger</h4>
            <ul class="small_menu">            
                <struts:url var="url" action="%{actionNamn}">
                    <struts:param name="deltagareId" value="deltagareId"/>
                    <struts:param name="sasongId" value="sasong.nastaSasong.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Nästa säsong</struts:a></li>
                <struts:url var="url" action="%{actionNamn}">
                    <struts:param name="deltagareId" value="deltagareId"/>
                    <struts:param name="sasongId" value="sasong.foregaendeSasong.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Föregående säsong</struts:a></li>                                                
            </ul>            



