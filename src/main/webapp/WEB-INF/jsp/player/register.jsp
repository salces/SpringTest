<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
      integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
      integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<html>
<head>
    <title>Add new player</title>
</head>
<jsp:include page="/WEB-INF/jsp/navbar.jsp"/>
<body>
<form:form modelAttribute="player" method="post" enctype="multipart/form-data">
    <table class="table table-striped">
        <tr>
            <td><label><spring:message code="player.register.name"/></label></td>
            <td><form:input path="name"/></td>
            <td><form:errors class="alert alert-danger" path="name"/></td>
        </tr>
        <tr>
            <td><label><spring:message code="player.register.surname"/></label></td>
            <td><form:input path="surname"/></td>
            <td><form:errors class="alert alert-danger" path="surname"/></td>
        </tr>
        <tr>
            <td><label><spring:message code="player.register.height"/></label></td>
            <td><form:input type="number" min="140" value="140" path="height"/></td>
            <td><form:errors class="alert alert-danger" path="height"/></td>
        </tr>
        <tr>
            <td><label><spring:message code="player.register.dominantHand"/></label></td>
            <td>
                <form:select path="dominantHand">
                    <form:option value="RIGHT"/>
                    <form:option value="LEFT"/>
                </form:select>
            </td>
            <td><form:errors class="alert alert-danger" path="dominantHand"/></td>
        </tr>
        <tr>
            <td><label><spring:message code="player.register.citizienship"/></label></td>
            <td>
                <form:select path="citizienship">
                    <form:options items="${citizienshipCodes}"/>
                </form:select>
            </td>
            <td><form:errors path="citizienship"/></td>
        </tr>
        <tr>
            <td><label><spring:message code="player.register.playerImg"/></label></td>
            <td>
                <input type="file" name="playerImg" value="Upload" accept="image/x-png, image/gif, image/jpeg">
            </td>
            <td><form:errors path="citizienship"/></td>
        </tr>
        <tr>
            <td><form:button class="btn btn-success" value="add" name="add">
                <spring:message code="player.register.button.add"/>
            </form:button></td>

        </tr>
    </table>
    <br>
    <c:if test="${successMessage != null}">
        <span class="alert alert-success">${successMessage}</span>
    </c:if>
</form:form>
</body>
</html>
