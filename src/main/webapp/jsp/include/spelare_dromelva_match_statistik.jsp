                        <%@ page contentType="text/html; charset=utf-8" %>
                        <td class="spelare">
                            <struts:push value="spelare">
                            <%@ include file="spelare_lank.jsp" %>
                            </struts:push>
                        </td>
                        <td><struts:property value="position.kod"/></td>
                        <%@ include file="spelare_match_statistik.jsp" %>
                        <struts:if test="deltagare.id > 1">                    
                        <td class="deltagare">
                            <struts:push value="deltagare">
                            <%@ include file="deltagare_lank.jsp" %>
                            </struts:push>                            
                        </td>
                        </struts:if>
                        <struts:else>
                        <td>&nbsp;</td>
                        </struts:else>
