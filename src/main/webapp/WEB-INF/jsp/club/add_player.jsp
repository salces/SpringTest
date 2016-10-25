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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add players to club</title>
    <jsp:include page="/WEB-INF/jsp/navbar.jsp"/>
</head>

<body>
<form:form modelAttribute="bindingForm">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                Available clubs:
                <form:select class="form-control" path="club" multiple="false">
                    <c:forEach var="Club" items="${clubList}">
                        <form:option value="${Club}">${Club.prefix} ${Club.location} ${Club.year}</form:option>
                    </c:forEach>
                </form:select></div>
            <div class="col-md-8">
                Available players:
                <form:select class="form-control" path="players" multiple="true">
                    <form:options items="${playersList}" itemLabel="fullName"/>
                </form:select></div>
        </div>
        <form:button class="btn btn-success" name="add">Add</form:button>

    </div>
</form:form>
<c:if test="${successMessage != null}">
    <span class="alert alert-success">${successMessage}</span>
</c:if>
</body>
</html>
