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
                <form method="POST" action="UpdatePasswordController">
                        <h1>Change password</h1>
                        <div>
                                <label for="password">Password</label>
                                <input type="password" value="" id="password" name="password"/>
                                ${requestScope.passwordError}
                        </div>
                        <div>
                                <label for="newPassword">New password</label>
                                <input type="password" value="" id="newPassword" name="newPassword"/>
                                ${requestScope.newPasswordError}
                        </div>
                        <div>
                                <label for="confirmPassword">Confirm password</label>
                                <input type="password" value="" id="confirmPassword" name="confirmPassword"/>
                                ${requestScope.confirmPassword}
                        </div>
                        <div>
                                ${requestScope.errorMessage}
                        </div>
                        <button type="submit">Change Password</button>
                </form>
        </body>
</html>
