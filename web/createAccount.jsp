<%-- 
    Document   : createAccount
    Created on : Jul 1, 2025, 11:30:58 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        <div>Create New Account</div>
        <form action="DispatchServlet" method="POST">
            Username* <input type="text" name="txtUsername" value="" />(6 - 30 chars)<br/>
            Password* <input type="password" name="txtPassword" value="" />(8 - 20 chars)<br/>
            Confirm* <input type="password" name="txtConfirm" value="" /><br/>
            Full name* <input type="text" name="txtFullname" value="" />(2 - 40 chars)<br/>
            <input type="submit" name="btAction" value="Create New Account" />
            <input type="reset" name="Reset"/>
        </form>
    </body>
</html>
