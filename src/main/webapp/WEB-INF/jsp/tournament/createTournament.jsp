<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
      integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
      integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
        integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
        crossorigin="anonymous"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/navbar.jsp"/>
<script>
    var selectedPlayersID = [];
    $(document).ready(function () {
        $('#phase').val('ONE_EIGHT');
    });

    function clickAllPlayersList(tr) {

        var player = $(tr).find('input#hidPlayer').val();
        console.log(player);
        selectedPlayersID.push(player);
        console.log(selectedPlayersID);

        $(tr).remove();
        $(tr).find('td').find('input').attr('onclick','clickSelectPlayerList($(this).parent().parent())');
        $(tr).find('td').find('input').attr('value','Remove');
        $('#selectedPlayers').append(tr);
        $('input#hidSelectedPlayers').attr('value',getSelectedPlayerList());
    }

    function clickSelectPlayerList(tr){
        var player = $(tr).find('input#hidPlayer').val();
        $(tr).remove();
        $(tr).find('td').find('input').attr('onclick','clickAllPlayersList($(this).parent().parent())');
        $(tr).find('td').find('input').attr('value','Add');
        $('#players').append(tr);
    }
    function getSelectedPlayerList(){
        return selectedPlayersID;
    }


</script>
    <body>
<form:form modelAttribute="someObj" method="post">

    Start phase: <select id="phase" name="phase" multiple="false">
    <%--<form:select id="phase" onchange="formChange()" path="phase" multiple="false">--%>
        <c:forEach var="Phase" items="${availablePhases}">
           <option value="${Phase}">1/${Phase.lvl}</option>
            <%--<form:option value="${Phase}">1/${Phase.lvl}</form:option>--%>
        </c:forEach>
</select>
            <%--</form:select>--%>
        Selected:
        <table id="selectedPlayers">

        </table>
    <input id="hidSelectedPlayers" name="players" type="hidden" value="asd">
    <form:button name="create" >Create tournament</form:button>
</form:form>

All players:<table id="players">

<c:forEach var="Player" items="${allPlayers}">
    <tr><td>${Player.name} ${Player.surname}</td>
        <td><input id="hidPlayer" type="hidden" value="${Player.ID}"/></td>
        <td><input type="button" value="Add" onclick="clickAllPlayersList($(this).parent().parent())"></td>
    </tr>
</c:forEach>
</table>

</body>
</head>

</html>