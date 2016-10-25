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
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/navbar.jsp"/>
    <script>
        var selectedPlayersID = [];
        $(document).ready(function () {
            $('#phase').val('ONE_EIGHT');
            $('#selectedPlayers').append('<tr><td>No players selected</td></tr>')
        });

        function clickAllPlayersList(tr) {
            console.log($(tr).find('input#hidPlayer').val())
            var player = $(tr).find('input#hidPlayer').val();
            var allPlayersTable = $(tr).parent().parent();
            var allPlayersTableCount = $(tr).parent().parent().find('tr').length;
            selectedPlayersID.push(player);
            if ($('#selectedPlayers').find('tr').length == 1 &&
                    $('#selectedPlayers').find('td:first').text() == 'No players selected') {
                $('#selectedPlayers').empty();
            }
            $(tr).remove();
            $(tr).find('td').find('input').attr('onclick', 'clickSelectPlayerList($(this).parent().parent())');
            $(tr).find('td').find('input[type != "hidden"]').attr('value', 'Remove');
            $('#selectedPlayers').append(tr);
            $('input#hidSelectedPlayers').attr('value', getSelectedPlayerList());
            if (allPlayersTableCount == 1) {
                allPlayersTable.append('<tr><td>No more players available</td></tr>');
            }
            $($('#playersCounter').text('' + selectedPlayersID.length + '/' + $('#phase').find('option:selected').attr('name') * 2))

        }

        function clickSelectPlayerList(tr) {
            selectedPlayersID.splice(selectedPlayersID.indexOf($(tr).find('input#hidPlayer').val()), 1);
            var selectedTable = $(tr).parent().parent();
            var selectedTableCount = $(tr).parent().parent().find('tr').length;


            if ($('#players').find('tr').length == 1 &&
                    $('#players').find('td:first').text() == 'No more players available') {
                $('#players').empty();
            }
            $(tr).remove();
            $(tr).find('td').find('input').attr('onclick', 'clickAllPlayersList($(this).parent().parent())');
            $(tr).find('td').find('input').attr('value', 'Add');
            $('#players').append(tr);
            if (selectedTableCount == 1) {
                selectedTable.append('<tr><td>No players selected</td></tr>');
            }
            $($('#playersCounter').text('' + selectedPlayersID.length + '/' + $('#phase').find('option:selected').attr('name') * 2))

        }
        function getSelectedPlayerList() {
            return selectedPlayersID;
        }

        function phaseChange() {
            $($('#playersCounter').text('' + selectedPlayersID.length + '/' + $('#phase').find('option:selected').attr('name') * 2))

        }


    </script>
<body>
<div class="row">
    <form:form modelAttribute="someObj" method="post" enctype="multipart/form-data">
    <div class="col-md-4">
        <table class="table table-striped">
            <tr>
                <td>
                    Name:
                </td>
                <td>
                    <input name="name" type="text">
                </td>
            <tr>
                <td>Tournament logo</td>
                <td>
                    <input type="file" name="tournamentImg" value="Upload"
                           accept="image/x-png, image/gif, image/jpeg">
                </td>
            </tr>
            <tr>
                <td>
                    Start phase:
                </td>
                <td><select class="form-control" id="phase" name="phase" onchange="phaseChange()">
                    <c:forEach var="Phase" items="${availablePhases}">
                        <option value="${Phase}" name="${Phase.lvl}">1/${Phase.lvl}</option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td>Selected players:</td>
                <td id="playersCounter">0/16</td>
            </tr>
        </table>
    </div>
    <div class="col-md-4">
        Selected:
        <div class="pre-scrollable">
            <table id="selectedPlayers" class="table table-striped">
            </table>
        </div>
    </div>
    <input id="hidSelectedPlayers" name="players" type="hidden" value="asd">

    <div class="col-md-4">
        All players:
        <div class="pre-scrollable">
            <table id="players" class="table table-striped">

                <c:forEach var="Player" items="${allPlayers}">
                    <tr>
                        <td>${Player.name} ${Player.surname}</td>
                        <td><input id="hidPlayer" type="hidden" value="${Player.ID}"/></td>
                        <td><input type="button" value="Add"
                                   onclick="clickAllPlayersList($(this).parent().parent())">
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-4">
        <form:button class="btn btn-success" name="create">Create tournament</form:button>
    </div>
</div>
</form:form>
<br>
<c:if test="${failureMessage != null}">
    <span class="alert alert-danger">${failureMessage}</span>
</c:if>
</body>
</head>

</html>