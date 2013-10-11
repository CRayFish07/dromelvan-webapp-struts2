            <%@ page contentType="text/html; charset=utf-8" %>
            <h3>Spelare</h3>
            <h4 class="top">Spelare</h4>
            <ul>
                <struts:url var="url" action="SpelareStatistik">
                    <struts:param name="spelareId" value="spelareId"/>
                </struts:url>                                              
                <li><struts:a href="%{url}">Säsongstatistik</struts:a></li>
                <struts:url var="url" action="SpelareTransfers">
                    <struts:param name="spelareId" value="spelareId"/>
                </struts:url>                                              
                <li><struts:a href="%{url}">Transfers</struts:a></li>                
            </ul>
            <h4 class="big_menu">Säsonger</h4>
            <ul class="big_menu">
                <struts:iterator value="sasonger">
                <struts:url var="url" action="SpelareStatistik">
                    <struts:param name="spelareId" value="spelareId"/>
                    <struts:param name="sasongId" value="id"/>
                </struts:url>                                              
                <li><struts:a href="%{url}"><struts:property value="namn"/></struts:a></li>                    
                </struts:iterator>
            </ul>
            <h4 class="small_menu">Säsonger</h4>
            <ul class="small_menu">            
                <struts:url var="url" action="SpelareStatistik">
                    <struts:param name="spelareId" value="spelareId"/>
                    <struts:param name="sasongId" value="sasong.nastaSasong.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Nästa säsong</struts:a></li>
                <struts:url var="url" action="SpelareStatistik">
                    <struts:param name="spelareId" value="spelareId"/>
                    <struts:param name="sasongId" value="sasong.foregaendeSasong.id"/>
                </struts:url>            
                <li><struts:a href="%{url}">Föregående säsong</struts:a></li>                                                
            </ul>                      
            
            <h4 class="administration_menu">Administration</h4>
            <ul class="administration_menu">
                <struts:url var="url" action="EditSpelareForm">
                    <struts:param name="spelareId" value="spelareId"/>
                </struts:url>                                              
                <li><struts:a href="%{url}">Ändra uppgifter</struts:a></li>                
                <struts:url var="url" action="TransferlistaSpelareForm">
                    <struts:param name="spelareId" value="spelareId"/>
                </struts:url>                                              
                <li><struts:a href="%{url}">Transferlista</struts:a></li>                
            </ul>
