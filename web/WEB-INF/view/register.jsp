<%--
    Document   : login
    Created on : Sep 19, 2021, 4:06:23 AM
    Author     : Kaine
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Register</title>
        </head>
        <body>
                <form method="POST" action="RegisterController">
                        <h1>Register</h1>
                        <div>
                                <label for="userId">userId</label>
                                <input type="text" value="" id="userId" name="userId"/>
                                ${requestScope.userId}

                        </div>
                        <div>
                                <label for="fullName">fullName</label>
                                <input type="text" value="" id="fullName" name="fullName"/>
                                ${requestScope.fullNameError}

                        </div>
                        <div>
                                <label for="password">Password</label>
                                <input type="password" value="" id="password" name="password"/>
                                ${requestScope.passwordError}
                        </div>
                        ${requestScope.messageError}
                        <div>
                                <label for="role">Role</label>
                                <input type="role" value="" id="role" name="role"/>
                                ${requestScope.roleError}
                        </div>
                        ${requestScope.errorMessage}
                        <button type="submit">Register</button>
                </form>
        </body>
</html>
