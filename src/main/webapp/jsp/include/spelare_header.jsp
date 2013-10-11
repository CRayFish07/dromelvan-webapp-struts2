            <%@ page contentType="text/html; charset=utf-8" %>
            <div class="spelare_header">
                <img class="spelare_klubbmarke" src="bilder/lag/klubbmarke_<struts:property value="spelareSasongStatistik.lag.id"/>.gif" alt="<struts:property value="spelareSasongStatistik.lag.namn"/>"/>
                <table>
                    <colgroup>
                        <col class="vanster_column"/>
                        <col class="hoger_column"/>
                    </colgroup>
                    <tr>
                        <th class="namn" colspan="2">
                            <struts:property value="spelareSasongStatistik.spelare.namn"/>
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <struts:push value="spelareSasongStatistik.lag">
                            Lag: <%@ include file="lag_lank.jsp" %>
                            </struts:push>
                         </td>
                        <td>
                            <struts:push value="spelareSasongStatistik.deltagare">
                            Deltagare: <%@ include file="deltagare_lank.jsp" %>
                            </struts:push>
                        </td>
                    </tr>
                    <tr>
                        <td>Position: <struts:property value="spelareSasongStatistik.position"/></td>
                        <td>Poäng: <struts:property value="spelareSasongStatistik.poang"/></td>
                    </tr>
                    <tr>
                        <td>Matcher: <struts:property value="spelareSasongStatistik.antalMatcher"/> (<struts:property value="spelareSasongStatistik.antalMatcherStartade"/>/<struts:property value="spelareSasongStatistik.antalMatcherInbytt"/>)</td>
                        <td>Placering: <struts:property value="spelareSasongStatistik.placering"/></td>
                    </tr>
                    <tr>
                        <td>Mål: <struts:property value="spelareSasongStatistik.antalMal"/></td>
                        <td>Assist: <struts:property value="spelareSasongStatistik.antalAssist"/></td>
                    </tr>
                    <tr>
                        <td>Medelrating: <struts:property value="spelareSasongStatistik.rating"/></td>
                        <td>MoM: <struts:property value="spelareSasongStatistik.antalMom"/>/<struts:property value="spelareSasongStatistik.antalDeladMom"/></td>
                    </tr>
                    <tr>
                        <td>Kort (G/R): <struts:property value="spelareSasongStatistik.antalGultKort"/>/<struts:property value="spelareSasongStatistik.antalRottKort"/></td>
                        <td>Pris: <struts:property value="spelareSasongStatistik.pris"/></td>
                    </tr>
                    
                </table>
            </div>
