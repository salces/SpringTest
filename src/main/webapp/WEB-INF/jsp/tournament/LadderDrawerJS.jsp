<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <script>
        function LadderDrawer() {

            var tournamentStages = [];

            <c:forEach items="${tournament.stages}" var="stage">
            tournamentStages.push
            ({
                firstPlayerFullName: '${stage.firstPlayer.name} ${stage.firstPlayer.surname}',
                secondPlayerFullName: '${stage.secondPlayer.name}  ${stage.secondPlayer.surname}'

            });
            </c:forEach>

            this.getFirstFullName = function () {
                return tournamentStages[0].firstPlayerFullName;
            };

        }
    </script>
</head>