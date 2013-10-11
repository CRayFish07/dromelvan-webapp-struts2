                    <%@ page contentType="text/html; charset=utf-8" %>
                    <struts:push value="spelare">
                        <%@ include file="spelare_lank_namn.jsp" %> <struts:property value="tid"/>
                    </struts:push>
                    <struts:if test="straffspark">
                        &nbsp;(str)
                    </struts:if>
                    <struts:if test="sjalvmal">
                        &nbsp;(självmål)
                    </struts:if>
