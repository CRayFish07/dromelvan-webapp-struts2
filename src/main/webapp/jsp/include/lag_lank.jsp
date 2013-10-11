                        <struts:url var="url" action="LagSpelarTrupp">
                            <struts:param name="lagId" value="id"/>
                            <struts:param name="sasongId" value="sasongId"/>
                        </struts:url>
                        <struts:if test="mobile">
                        <struts:a href="%{url}"><struts:property value="kod"/></struts:a>
                        </struts:if>
                        <struts:else>
                        <struts:a href="%{url}"><struts:property value="namn"/></struts:a>
                        </struts:else>
