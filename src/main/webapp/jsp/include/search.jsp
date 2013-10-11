        <%@ page contentType="text/html; charset=utf-8" %>
        <div id="search" class="group">
            <h3>Sökning</h3>
            <h4 class="top">Sök spelare, lag, deltagare</h4>
            <form id="searchform" method="get" action="<struts:url action="Sokning"/>">
                <div><input class="text" type="text" name="sokning"/></div>
                <div><input class="submit" type="submit" value="Sök"/></div>
            </form>
        </div>