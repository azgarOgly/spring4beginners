<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello!</title>
</head>
<body style="background: #A0B0A0">
    <h2>Checking known hosts</h2>

    <hr />
    <c:forEach var="host" items="${hosts}">
        <p>${host.ip} ${host.name}</p>
        <c:if test="${host.hello != null}">
            <p style="color:darkgreen; white-space: pre-line;">${host.hello}</p>
        </c:if>
        <c:if test="${host.error != null}">
            <p style="color:darkred; white-space: pre-line;">${host.error}</p>
        </c:if>
        <hr />
    </c:forEach>
</body>
</html>
