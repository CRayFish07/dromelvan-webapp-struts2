            <%@ page contentType="text/html; charset=utf-8" %>
            <table class="uppstallning_table">
                <colgroup>
                    <col class="position_column"/>
                    <col class="spelare_column"/>
                    <col class="rating_column"/>
                    <col class="separator_column"/>
                    <col class="position_column"/>
                    <col class="spelare_column"/>
                    <col class="rating_column"/>
                </colgroup>
                <tr>
                    <th colspan="3">
                        Startuppställning
                    </th>
                    <th class="divider">
                        &nbsp;
                    </th>
                    <th colspan="3">
                        Startuppställning
                    </th>
                </tr>
                <struts:iterator value="startuppstallning">
                <tr>
                    <struts:push value="hemmaLagSpelare">
                        <%@ include file="match_statistik_spelare.jsp" %>
                    </struts:push>
                    <td class="divider">&nbsp;</td>
                    <struts:push value="bortaLagSpelare">
                        <%@ include file="match_statistik_spelare.jsp" %>
                    </struts:push>
                </tr>
                </struts:iterator>
                <tr>
                    <th colspan="3">
                        Reserver
                    </th>
                    <th class="divider">
                        &nbsp;
                    </th>
                    <th colspan="3">
                        Reserver
                    </th>
                </tr>
                <struts:iterator value="reserver">
                <tr>
                    <struts:push value="hemmaLagSpelare">
                        <%@ include file="match_statistik_spelare.jsp" %>
                    </struts:push>
                    <td class="divider">&nbsp;</td>
                    <struts:push value="bortaLagSpelare">
                        <%@ include file="match_statistik_spelare.jsp" %>
                    </struts:push>
                </tr>
                </struts:iterator>
            </table>
