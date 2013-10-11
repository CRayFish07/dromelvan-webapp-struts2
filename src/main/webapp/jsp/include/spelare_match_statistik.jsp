                        <%@ page contentType="text/html; charset=utf-8" %>
                        <struts:if test="deltog == 2 || inbyttTid > 0">
                        <struts:if test="mal > 0">
                        <td><struts:property value="mal"/></td>
                        </struts:if>
                        <struts:else>
                        <td>&nbsp;</td>
                        </struts:else>
                        <struts:if test="sjalvmal > 0">
                        <td><struts:property value="sjalvmal"/></td>
                        </struts:if>
                        <struts:else>
                        <td>&nbsp;</td>
                        </struts:else>
                        <struts:if test="assist > 0">
                        <td><struts:property value="assist"/></td>
                        </struts:if>
                        <struts:else>
                        <td>&nbsp;</td>
                        </struts:else>                        
                        <struts:if test="gultKortTid > 0">
                        <td><img src="bilder/gult_kort.gif" class="kort" alt="Gult kort"/></td>
                        </struts:if>
                        <struts:else>
                        <td>&nbsp;</td>
                        </struts:else>
                        <struts:if test="rottKortTid > 0">
                        <td><img src="bilder/rott_kort.gif" class="kort" alt="Rött kort"/></td>
                        </struts:if>
                        <struts:else>
                        <td>&nbsp;</td>
                        </struts:else>
                        <struts:if test="inbyttTid > 0">
                        <td><struts:property value="inbyttTid"/></td>
                        </struts:if>
                        <struts:else>
                        <td>&nbsp;</td>
                        </struts:else>
                        <struts:if test="utbyttTid > 0">
                        <td><struts:property value="utbyttTid"/></td>
                        </struts:if>
                        <struts:else>
                        <td>&nbsp;</td>
                        </struts:else>
                        <struts:if test="mom">
                        <td><img src="bilder/mom.gif" class="mom" alt="MoM"/></td>
                        </struts:if>
                        <struts:elseif test="deladMom">
                        <td><img src="bilder/delad_mom.gif" class="mom" alt="Delad MoM"/></td>
                        </struts:elseif>                        
                        <struts:else>
                        <td>&nbsp;</td>
                        </struts:else>
                        <struts:if test="synligRating > 0">
                        <td><struts:property value="synligRating"/></td>
                        </struts:if>
                        <struts:else>
                        <td>&nbsp;</td>
                        </struts:else>
                        </struts:if>
                        <struts:elseif test="deltog == 1">
                        <td colspan="9">Satt på bänken</td>
                        </struts:elseif>
                        <struts:elseif test="deltog == 0">
                        <td colspan="9">Deltog ej</td>
                        </struts:elseif>
                        <struts:elseif test="deltog == -1">
                        <td colspan="9">Ej inlagd</td>
                        </struts:elseif>
                        <td><struts:property value="poang"/></td>                        
                        
