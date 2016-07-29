<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: slc
  Date: 11.07.16
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

<html>
<head>
    <title>Register</title>
</head>
<body>

<form:form modelAttribute="registerForm" >
    <table class="table table-striped">
        <tr>
            <td><spring:message code="register.username"/></td>
            <td><form:input path="username" autocomplete="off"/></td>
            <td><form:errors class="alert alert-danger" path="username"/></td>
        </tr>
        <tr>
            <td><spring:message code="register.password"/></td>
            <td><form:password path="password"/></td>
            <td><form:errors class="alert alert-danger" path="password"/></td>
        </tr>
        <tr>
            <td><spring:message code="register.name"/></td>
            <td><form:input path="name"/></td>
            <td><form:errors class="alert alert-danger" path="name" autocomplete="off"/></td>
        </tr>
        <tr>
            <td><spring:message code="register.surname"/></td>
            <td><form:input path="surname"/></td>
            <td><form:errors class="alert alert-danger" path="surname"/></td>
        </tr>
        <tr>
            <td><spring:message code="register.email"/></td>
            <td><form:input path="email" value=""/></td>
            <td><form:errors class="alert alert-danger" path="email"/></td>
        </tr>
        <tr>
            <td><form:button class="btn btn-success" value="register" name="register"><b><spring:message code="register.button.register" /></b></form:button></td>
        </tr>
    </table>
</form:form>

</body>
</html>
