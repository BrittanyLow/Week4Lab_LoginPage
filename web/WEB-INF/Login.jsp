<%-- 
    Document   : Login
    Created on : Sep 27, 2018, 12:08:17 PM
    Author     : 707114
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyLogin</title>
    </head>
    <body>
        <h1>Remember Me Login Page</h1>
            <form action="login" method="post">
        <input type="hidden" name="action" value="add">        
        <label class="pad_top">Username:</label>
        <input type="text" name="name" value = "${username}"required><br>
        <label class="pad_top">Password:</label>
        <input type="password" name="pass" required><br>
        <label>&nbsp;</label>
        <input type="submit" value="Login" class="margin_left">
        <br>
        <input type="checkbox" name= "remember" class="margin_left"> Remember me
    </form>
        
        
       
    </body>
</html>
