<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
      integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
      integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<html>
<head>
    <title>Club registration</title>
</head>
<jsp:include page="/WEB-INF/jsp/navbar.jsp"/>
<body>


<form:form modelAttribute="club" method="post" enctype="multipart/form-data">
    <table class="table table-striped">
        <tr>
            <td><spring:message code="club.register.prefix"/></td>
            <td><form:input path="prefix"/></td>
            <td><form:errors class="alert alert-danger" path="prefix"/></td>
        </tr>
        <tr>
            <td><spring:message code="club.register.location"/></td>
            <td><form:input path="location"/></td>
            <td><form:errors class="alert alert-danger" path="location"/></td>
        </tr>
        <tr>
            <td><spring:message code="club.register.year"/></td>
            <td><form:input type="number" min="1700" value="1900" path="year"/></td>
            <td><form:errors class="alert alert-danger" path="year"/></td>
        </tr>
        <tr>
            <td><spring:message code="club.register.email"/></td>
            <td><form:input path="email"/></td>
            <td><form:errors class="alert alert-danger" path="email"/></td>
        </tr>
        <tr>
            <td><spring:message code="club.register.homepage"/></td>
            <td><form:input path="homePage"/></td>
            <td><form:errors class="alert alert-danger" path="homePage"/></td>
        </tr>
        <tr>
            <td><label><spring:message code="club.register.clubImg"/></label></td>
            <td>
                <input type="file" name="clubImg" value="Upload" accept="image/x-png, image/gif, image/jpeg">
            </td>
        </tr>
        <tr>
            <td><form:button class="btn btn-success" name="register">
                <spring:message code="club.register.button.register"/>
            </form:button></td>
        </tr>
    </table>
</form:form>
<c:if test="${successMessage != null}">
    <span class="alert alert-success">${successMessage}</span>
</c:if>
</body>
</html>
