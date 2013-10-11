                        <struts:if test="isSpelad">
                        <struts:url var="url" action="MatchStatistik">
                            <struts:param name="matchId" value="id"/>
                        </struts:url>                                              
                        <struts:a href="%{url}">
                            <struts:if test="isInlagd">
                                <struts:if test="ratingsInlagd">
                                    <struts:property value="antalMalHemmaLag"/> - <struts:property value="antalMalBortaLag"/>
                                </struts:if>
                                <struts:else>
                                    (<struts:property value="antalMalHemmaLag"/> - <struts:property value="antalMalBortaLag"/>)
                                </struts:else>                                
                            </struts:if>
                            <struts:else>
                            Spelad
                            </struts:else>
                        </struts:a>
                        </struts:if>
                        <struts:else>
                        Ej spelad
                        </struts:else>