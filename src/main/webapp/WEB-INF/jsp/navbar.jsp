<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
      integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
      integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
        integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
        crossorigin="anonymous"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">


<html>
<head>
    <title>Thank you for registration</title>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">TableTennisApp</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/">Home</a></li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown">Player
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/player/register">Register new player</a></li>
                    <li><a href="/player/presentation">Show all players</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown">Club
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/club/register">Register new club</a></li>
                    <li><a href="/club/addPlayer">Add player to club</a></li>
                    <li><a href="/club/presentation">Show all clubs</a></li>
                </ul>
            </li>
            <sec:authorize access="hasAuthority('ADMIN')">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">Admins
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/admins/test">Test</a></li>
                    </ul>
                </li>
            </sec:authorize>
        </ul>

        <a class="navbar-brand navbar-right" href="/logout">
            <span class="glyphicon glyphicon-log-out" title="Logout"></span>
        </a>
    </div>
</nav>
</body>
</html>
