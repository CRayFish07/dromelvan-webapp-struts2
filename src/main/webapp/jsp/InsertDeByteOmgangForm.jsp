<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Administration > Lägg in byteomgång</title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <h3>Adminfunktioner</h3>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Administration</h2>

            <h3>Lägg in ny byteomgång</h3>
            <p>
                Ange namn, datum för listningsdeadline och bytesdatum för byteomgången
                och välj den drömelvaomgång bytena gäller fr.o.m för att lägga in en ny
                byteomgång för pågående säsong.
            </p>
            <div class="byte_omgang_input">
                <form method="post" action="<struts:url action="InsertDeByteOmgang"/>">
                    <table>
                        <colgroup>
                            <col class="namn_column"/>
                            <col class="listdatum_column"/>
                            <col class="datum_column"/>
                            <col class="de_omgang_column"/>
                        </colgroup>
                        <struts:if test="hasFieldErrors">
                        <tr>
                            <td class="error"><struts:property value="%{getFieldErrorMessage('namn')}"/></td>
                            <td class="error"><struts:property value="%{getFieldErrorMessage('listDatumStr')}"/></td>
                            <td class="error"><struts:property value="%{getFieldErrorMessage('datumStr')}"/></td>
                            <td class="error"><struts:property value="%{getFieldErrorMessage('deOmgangId')}"/></td>                        
                        </tr>
                        </struts:if>                                        
                        <tr>
                            <th>Namn</th>
                            <th>Listdatum</th>
                            <th>Datum</th>
                            <th>Drömelvaomgång</th>
                        </tr>
                        <tr>
                            <td>
                                <struts:textfield cssClass="text" name="namn"/>
                            </td>
                            <td>
                                <struts:textfield cssClass="text" name="listDatumStr"/>
                            </td>
                            <td>
                                <struts:textfield cssClass="text" name="datumStr"/>
                            </td>
                            <td>
                               <struts:select name="deOmgangId" list="deOmgangList" listKey="id" listValue="namn" size="1"
                                              required="true" value="%{deOmgangId}"/>                            
                            </td>
                        </tr>
                    </table>
                    <div id="knappar">
                        <p>
                            <struts:submit value="Lägg in"/>
                            <struts:submit method="cancel" value="Avbryt"/>
                        </p>
                    </div>
                </form>
            </div>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
