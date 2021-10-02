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
                        <c:if test="${requestScope.userList != null}">
                            <div>userId - fullName - role</div>
                            <c:forEach items="${requestScope.userList}" var="user">
                                <form method="POST" action="UpdateUserInfoController">
                                        <p>${user.toString()}
                                                <a  href="UpdateUserInfoController?fullName=${user.getFullName()}">Update</a>
                                        </p>

                                </form>
                            </c:forEach>
                        </c:if>
                </div>
        </body>
</html>
