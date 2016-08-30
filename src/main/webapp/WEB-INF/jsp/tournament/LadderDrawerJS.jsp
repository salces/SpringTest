<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <script>
        function LadderDrawer() {

            var root = document.getElementById('ladder');
            var dataSource = new DataFromJSP().getData();

            this.draw = function () {

                for(var i in dataSource){
                    var stageElement = new StageElement(dataSource[i]);
                    root.appendChild(stageElement.getHTML());
                }
            }

        }

        function StageElement(stageDataSrc){

            var stageData = stageDataSrc;

            var template = '<div> <ul>' +
                            '<li> #arg1 </li>' +
                            '<li> #arg2 </li>' +
                            '</ul></div><br>'

            this.getHTML = function () {
                template = template.replace('#arg1',stageData.firstPlayerFullName);
                template = template.replace('#arg2',stageData.secondPlayerFullName);
                var e =document.createElement('div');
                e.innerHTML = template;
                return e;
            }
        }

        function DataFromJSP() {
            var tournamentStages = [];

            <c:forEach items="${tournament.stages}" var="stage">
            tournamentStages.push
            ({
                firstPlayerFullName: '${stage.firstPlayer.name} ${stage.firstPlayer.surname}',
                secondPlayerFullName: '${stage.secondPlayer.name}  ${stage.secondPlayer.surname}'

            });
            </c:forEach>

            this.getData = function () {
                return tournamentStages;
            }
        }

    </script>
</head>