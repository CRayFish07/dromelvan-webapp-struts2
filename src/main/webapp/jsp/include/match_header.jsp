            <%@ page contentType="text/html; charset=utf-8" %>
            <div class="match_header">
                <img class="hemma_klubbmarke" src="bilder/lag/klubbmarke_<struts:property value="match.hemmaLag.id"/>.gif" alt="<struts:property value="match.hemmaLag.namn"/>"/>
                <img class="borta_klubbmarke" src="bilder/lag/klubbmarke_<struts:property value="match.bortaLag.id"/>.gif" alt="<struts:property value="match.bortaLag.namn"/>"/>
                <div class="match_info">
                    <p>
                        <struts:property value="match.omgang.tavling.namn"/>
                    </p>
                    <p>
                        <struts:property value="match.omgang.namn"/>
                    </p>
                    <p>
                        Säsong: <struts:property value="match.omgang.tavling.sasong.namn"/>                    
                    </p>
                    <p>
                        Datum: <struts:property value="match.datumStr"/>                    
                    </p>
                </div>
            </div>
