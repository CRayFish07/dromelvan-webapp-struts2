            <%@ page contentType="text/html; charset=utf-8" %>
            <div class="deltagare_header">
                <img class="deltagare_klubbmarke" src="bilder/deltagare/deltagare_<struts:property value="deltagare.id"/>.gif" alt="<struts:property value="deltagare.namn"/>"/>
                <table>
                    <colgroup>
                        <col class="vanster_column"/>
                        <col class="hoger_column"/>
                    </colgroup>
                    <tr>
                        <th class="namn" colspan="2">
                            <struts:property value="deltagare.namn"/>
                        </th>
                    </tr>
                    <tr>
                        <td>Poäng: <struts:property value="deltagareSpelarTruppInfoBean:poang"/></td>
                        <td>Mål: <struts:property value="deltagareSpelarTruppInfoBean:mal"/></td>
                    </tr>
                    <tr>
                        <td>Gula kort: <struts:property value="deltagareSpelarTruppInfoBean:gulaKort"/></td>
                        <td>Röda kort: <struts:property value="deltagareSpelarTruppInfoBean:rodaKort"/></td>
                    </tr>
                    <tr>
                        <td>MoM: <struts:property value="deltagareSpelarTruppInfoBean:mom"/></td>
                        <td>Delad MoM: <struts:property value="deltagareSpelarTruppInfoBean:deladMom"/></td>
                    </tr>
                    <tr>
                        <td>Medelrating: <struts:property value="deltagareSpelarTruppInfoBean:medelRating"/></td>
                        <td>Medelpoäng: <struts:property value="deltagareSpelarTruppInfoBean:medelPoang"/></td>
                    </tr>
                </table>
            </div>