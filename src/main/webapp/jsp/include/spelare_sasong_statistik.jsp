                    <%@ page contentType="text/html; charset=utf-8" %>
                    <td><struts:property value="antalMatcher"/></td>
                    <td><struts:property value="antalMal"/></td>
                    <td><struts:property value="antalAssist"/></td>
                    <struts:if test="antalMom > 0 || antalDeladMom > 0">
                    <td><struts:property value="antalMom"/>/<struts:property value="antalDeladMom"/></td>
                    </struts:if>                    
                    <struts:else>
                    <td>&nbsp;</td>
                    </struts:else>
                    <td><struts:property value="antalGultKort"/></td>
                    <td><struts:property value="antalRottKort"/></td>
                    <struts:if test="rating > 0.00">
                    <td><struts:property value="rating"/></td>
                    </struts:if>                    
                    <struts:else>
                    <td>&nbsp;</td>
                    </struts:else>
                    <td><struts:property value="poang"/></td>
                        
