<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Add players to club</title>
</head>
<body>
<form:form modelAttribute="bindingForm">
    <form:select path="club" multiple="false">
       <c:forEach var="Club" items="${clubList}">
           <form:option value="${Club}">${Club.prefix} ${Club.location} ${Club.year}</form:option>
       </c:forEach>
    </form:select>
    <form:select path="players" multiple="true">
        <form:options items="${playersList}" itemLabel="fullName"/>
    </form:select>
    <br><br><form:button name="add">Add</form:button>
</form:form>
</body>
</html>
