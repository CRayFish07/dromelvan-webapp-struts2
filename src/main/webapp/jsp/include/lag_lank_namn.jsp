                        <struts:url var="url" action="LagSpelarTrupp">
                            <struts:param name="lagId" value="id"/>
                            <struts:param name="sasongId" value="sasongId"/>
                        </struts:url>
                        <struts:a href="%{url}"><struts:property value="namn"/></struts:a>
