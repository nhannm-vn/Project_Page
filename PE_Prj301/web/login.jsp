<%-- 
    Document   : login
    Created on : Apr 26, 2025, 8:58:20 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form method="POST" action="MainController">
            Username <input type="text" name="txtUsername" value="" /> <br/>
            Password <input type="password" name="txtPassword" value="" /> <br/>
            <input type="submit" name="action" value="Login" />
            <input type="reset" value="Reset"  />
        </form>
    </body>
</html>
