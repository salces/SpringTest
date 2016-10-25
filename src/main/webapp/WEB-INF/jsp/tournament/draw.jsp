<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
      integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
      integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">


<html>
<head>
    <title>Draw tournament</title>
    <style>
        .scrollableCanvas {
            overflow: scroll;
            position: relative;
        }

        #div {
            position: absolute
        }

        #canvas {
            position: relative
        }
    </style>

</head>
<jsp:include page="../navbar.jsp"/>
<jsp:include page="LadderDrawerJS.jsp"/>
<script>
    var tournament = ${tournament};
    var stagesBoxes;
    $(document).ready(function () {
        initCanvas();
        var ladderDrawer = new LadderDrawer(tournament);
        stagesBoxes = ladderDrawer.draw();
    });


    function initCanvas() {
        var w = window.innerWidth - 50;
        var h = window.innerHeight - 100;
        console.log(w + ' ' + h);
        var canvas = '<div id="wrapper" class="scrollableCanvas"><canvas class ="position: relative" id=ladder width="' + w + '" height="' + h + '"></canvas></div>'
        $("body").append(canvas);
    }
</script>
<body>

<%--<div>--%>
<%--<div id="wrapper">--%>
<%--<canvas id="ladder" height=""></canvas>--%>
<%--</div>--%>
<%--</div>--%>

<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Edit pair</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tr>
                        <td><span id="firstPlayer"></span></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><span id="secondPlayer"></span></td>
                        <td></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>

</body>
</html>
