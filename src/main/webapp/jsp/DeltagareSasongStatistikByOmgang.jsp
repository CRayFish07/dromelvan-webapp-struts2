<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <title>Drömelvan > Drömelvan > <struts:property value="deltagare.namn"/> - Säsongstatistik per omgång <struts:property value="tavling.sasong.namn"/></title>

    <%@ include file="include/head.jsp" %>

</head>

<body id="body">

    <div id="header">
        <h1>Drömelvan</h1>
    </div>

    <div id="sidebar">

        <h2>Meny</h2>

        <div id="context" class="group">
            <%@ include file="include/context/deltagare.jsp" %>
        </div>

        <%@ include file="include/menu.jsp" %>
        <%@ include file="include/search.jsp" %>
        <%@ include file="include/icon.jsp" %>

    </div>

    <div id="main">

        <div id="content">

            <h2>Premier League</h2>

            <h3>Säsongstatistik per omgång <struts:property value="tavling.sasong.namn"/></h3>
            <%@ include file="include/deltagare_header.jsp" %>

            <div id="scrollview">

                <table id="poang_by_omgang">
                    <colgroup>
                        <col class="spelare_column"/>
                        <col class="position_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_column"/>
                        <col class="poang_summa_column"/>
                    </colgroup>
                    <tr>
                        <th class="spelare">Namn</th>
                        <th>Pos.</th>
                        <th>1</th>
                        <th>2</th>
                        <th>3</th>
                        <th>4</th>
                        <th>5</th>
                        <th>6</th>
                        <th>7</th>
                        <th>8</th>
                        <th>9</th>
                        <th>10</th>
                        <th>11</th>
                        <th>12</th>
                        <th>13</th>
                        <th>14</th>
                        <th>15</th>
                        <th>16</th>
                        <th>17</th>
                        <th>18</th>
                        <th>19</th>
                        <th>20</th>
                        <th>21</th>
                        <th>22</th>
                        <th>23</th>
                        <th>24</th>
                        <th>25</th>
                        <th>26</th>
                        <th>27</th>
                        <th>28</th>
                        <th>29</th>
                        <th>30</th>
                        <th>31</th>
                        <th>32</th>
                        <th>33</th>
                        <th>34</th>
                        <th>35</th>
                        <th>36</th>
                        <th>37</th>
                        <th>38</th>
                        <th>Tot.</th>
                    </tr>
                    <struts:iterator value="spelareDeltagareSasongStatistik">
                    <tr>
                        <td class="spelare">
                            <struts:push value="spelareSasong.spelare">
                            <%@ include file="include/spelare_lank.jsp" %>
                            </struts:push>
                        </td>
                        <td><struts:property value="spelareSasong.position.kod"/></td>
                        <struts:iterator value="poangPerOmgang">
                        <td><struts:property/></td>
                        </struts:iterator>
                        <td><struts:property value="poang"/></td>
                    </tr>
                    </struts:iterator>
                    <tr>
                        <td class="totalt_label" colspan="2">Totalt:</td>
                        <struts:iterator value="poangPerOmgangSumma">
                        <td class="totalt"><struts:property/></td>
                        </struts:iterator>
                        <td class="totalt"><struts:property value="totalt"/></td>
                    </tr>

                </table>

            </div>
        </div>

        <%@ include file="include/footer.jsp" %>

    </div>
</body>
</html>
