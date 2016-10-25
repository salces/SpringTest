<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
      integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
      integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<html>
<head>
    <style>

        .myList {
            display: block;
            float: left;
            width: 300px;
            heigh: 150px;
            padding: 5px;
            marigin: 1em 0;
        }

        .playerDescription {
            resize: both;
            overflow: auto;
            position: absolute;
            background: white;
            border: 2px solid black;
            border-radius: 5px;
        }
    </style>
    <title>Players presentation</title>
    <script type="text/javascript">
        $(document).ready(function () {
            $("img").click(function () {
                var id = e.target.id;
                id = id.substring(4, id.length);
                $("#des-" + id).toggle();
            });

            $("div").on("click", function (e) {
                var id = e.target.id;
                id = id.substring(4, id.length);
                $("#des-" + id).css("top", e.pageY)
                $("#des-" + id).css("left", e.pageX)
                $("#des-" + id).toggle();
            });

            $(document).keypress(function (e) {
                if (e.which == 0) {
                    $(".playerDescription").hide();
                }
            })
        });
    </script>
</head>
<jsp:include page="/WEB-INF/jsp/navbar.jsp"/>
<body>
<c:if test="${clubList.size() == 0}">
    <b>No clubs to show</b>
</c:if>
    <ul>
        <c:forEach items="${clubList}" var="club">
            <li class="myList">
                <div style="text-align: center;" id="div-${club.ID}">
                    <img id="img-${club.ID}"
                         src="${club.htmlImage}">
                    <br>${club.fullName}
                </div>
                <div id="des-${club.ID}" class="playerDescription" hidden="true">
                    Email: ${club.email}<br>
                    <a href="${club.homePage}" target="_blank">Home page</a><br>
                    <a href="/player/presentation/fromClub/${club.ID}"> Show current players</a><br>
                </div>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
