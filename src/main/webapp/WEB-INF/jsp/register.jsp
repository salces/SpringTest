<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: slc
  Date: 11.07.16
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>

<form:form modelAttribute="registerForm" >
    <table>
        <tr>
            <td>Username*</td>
            <td><form:input path="username" autocomplete="off"/></td>
            <td><form:errors path="username"/></td>
        </tr>
        <tr>
            <td>Password*</td>
            <td><form:password path="password"/></td>
            <td><form:errors path="password"/></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><form:input path="name"/></td>
            <td><form:errors path="name" autocomplete="off"/></td>
        </tr>
        <tr>
            <td>Surname</td>
            <td><form:input path="surname"/></td>
            <td><form:errors path="surname"/></td>
        </tr>
        <tr>
            <td>Email*</td>
            <td><form:input path="email" value=""/></td>
            <td><form:errors path="email"/></td>
        </tr>
        <tr>
            <td><form:button value="register" name="register"><b>Submit form</b></form:button></td>
        </tr>
    </table>
</form:form>

</body>
</html>
