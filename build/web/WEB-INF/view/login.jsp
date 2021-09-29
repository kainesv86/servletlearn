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
                <title>Login</title>
        </head>
        <body>
                <form method="POST" action="LoginController">
                        <h1>Login</h1>
                        <div>
                                <label for="fullName">Fullname</label>
                                <input type="text" value="" id="fullName" name="fullName"/>
                                ${requestScope.fullNameError}

                        </div>
                        <div>
                                <label for="password">Password</label>
                                <input type="password" value="" id="password" name="password"/>
                                ${requestScope.passwordError}
                        </div>
                        ${requestScope.messageError}



                        <button type="submit">Login</button>
                </form>
        </body>
</html>
