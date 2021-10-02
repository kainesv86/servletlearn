<%--
    Document   : userUpdate
    Created on : Oct 2, 2021, 5:44:18 AM
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
                <h1>Update Info user</h1>
                <!--<div>   <label>User Id: ${user}</label></div>-->
                <form method="POST" action="UpdateUserInfoController?fullName=${requestScope.userInfo.getFullName()}">

                        <div>
                                <label for="userId">User Id</label>
                                <input type="text" value="${requestScope.userInfo.getUserId()}" id="userId" name="userId" readonly/>
                        </div>
                        <div>
                                <label for="fullName">Fullname</label>
                                <input type="text" value="${requestScope.userInfo.getFullName()}" id="fullName" name="fullName" readonly/>
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
                        <div>
                                <label for="role">Role</label>
                                <input type="text" value="${requestScope.userInfo.getRole()}" id="role" name="role"/>
                                ${requestScope.roleError}
                        </div>
                        <button type="submit">Change Password</button>
                </form>
        </body>
</html>
