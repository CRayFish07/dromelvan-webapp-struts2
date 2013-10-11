                    <struts:if test="dummy">
                    <td class="divider" colspan="3">
                        &nbsp;
                    </td>
                    </struts:if>
                    <struts:else>
                    <td><struts:property value="position.kod"/></td>
                    <td class="spelare">
                        <struts:push value="spelare">
                        <%@ include file="spelare_lank.jsp" %>
                        </struts:push>
                        <struts:if test="inbyttTid > 0">
                        <img src="bilder/inbytt.gif" class="byte" alt="Inbytt"/> (<struts:property value="inbyttTid"/>)                            
                        </struts:if>
                        <struts:bean name="org.apache.struts2.util.Counter" var="counter">
                          <struts:param name="last" value="%{assist}" />
                        </struts:bean>
                        <struts:iterator value="#counter">
                        <img src="bilder/assist.gif" class="assist" alt="Assist"/>
                        </struts:iterator>                         
                        <struts:if test="gultKortTid > 0">
                        <img src="bilder/gult_kort.gif" class="kort" alt="Gult kort"/> (<struts:property value="gultKortTid"/>)
                        </struts:if>
                        <struts:if test="rottKortTid > 0">
                        <img src="bilder/rott_kort.gif" class="kort" alt="Rött kort"/> (<struts:property value="rottKortTid"/>)
                        </struts:if>
                        <struts:if test="mom">
                        <img src="bilder/mom.gif" class="mom" alt="MoM"/>
                        </struts:if>
                        <struts:if test="deladMom">
                        <img src="bilder/delad_mom.gif" class="delad_mom" alt="Delad MoM"/>
                        </struts:if>                                                
                        <struts:if test="utbyttTid > 0">
                        <img src="bilder/utbytt.gif" class="byte" alt="Utbytt"/> (<struts:property value="utbyttTid"/>)                            
                        </struts:if>
                    </td>
                    <td>
                        <struts:if test="synligRating > 0">
                        <struts:property value="synligRating"/>
                        </struts:if>
                        <struts:else>
                        &nbsp;
                        </struts:else>
                    </td>
                    </struts:else>
