<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: slc
  Date: 11.07.16
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
      integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
      integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
        integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
        crossorigin="anonymous"></script>


<html>
<head>
    <title>Add new player</title>
</head>
<body>


<form:form modelAttribute="player">
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
