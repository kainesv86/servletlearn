<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
    Document   : home
    Created on : Sep 19, 2021, 11:31:14 AM
    Author     : Kaine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>JSP Page</title>
        </head>
        <body>
                <h1>This is home page and nothing here!</h1>
                <div>

                        <p>
                                userId: ${sessionScope.user.userId}
                        </p>
                        <p>
                                fullName: ${sessionScope.user.fullName}
                        </p>
                        <p>
                                password: ${sessionScope.user.password}
                        </p>
                        <p>
                                role:  ${sessionScope.user.role}
                        </p>
                        <c:if test="${sessionScope.user.fullName != null}" scope="session" var="something">
                            <a href="./UpdatePasswordController">Change password</a>
                        </c:if>
                        <c:if test="${sessionScope.user.role == 1}" scope="session" var="something">
                            <a href="./AdminController">Request all user</a>
                        </c:if>
                </div>
        </body>
</html>
