            <%@ page contentType="text/html; charset=utf-8" %>
            <div>
                <table class="resultat_table">
                    <colgroup>
                        <col class="lag_column"/>
                        <col class="mal_column"/>
                        <col class="separator_column"/>
                        <col class="mal_column"/>
                        <col class="lag_column"/>
                    </colgroup>
                    <tr>
                        <th>
                            <struts:property value="match.hemmaLag.namn"/>
                        </th>
                        <th>
                            <struts:property value="match.antalMalHemmaLag"/>                        
                        </th>
                        <th>
                            -
                        </th>
                        <th>
                            <struts:property value="match.antalMalBortaLag"/>
                        </th>
                        <th>                                            
                            <struts:property value="match.bortaLag.namn"/>
                        </th>
                    </tr>
                    <struts:iterator value="mal">
                    <tr>
                        <struts:if test="hemmaLagMal.dummy">
                        <td class="divider" colspan="2">
                            &nbsp;
                        </td>                        
                        </struts:if>
                        <struts:else>
                        <td class="hemmalag_mal" colspan="2">
                            <struts:push value="hemmaLagMal">
                            <%@ include file="match_statistik_mal.jsp" %>
                            </struts:push>
                        </td>
                        </struts:else>
                        <td class="divider">
                            &nbsp;
                        </td>
                        <struts:if test="bortaLagMal.dummy">
                        <td class="divider" colspan="2">
                            &nbsp;
                        </td>                        
                        </struts:if>
                        <struts:else>                    
                        <td class="bortalag_mal" colspan="2">
                            <struts:push value="bortaLagMal">
                            <%@ include file="match_statistik_mal.jsp" %>
                            </struts:push>
                        </td>
                        </struts:else>
                    </tr>
                    </struts:iterator>
                </table>
            </div>