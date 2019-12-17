<%-- 
    Document   : login
    Created on : 17/12/2019, 11:11:16 AM
    Author     : stive
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <form action="../../login.do">
                <label>Ingrese el usuario</label>
                <input name="usuario" type="email" required="true"> </br>
                <label>ingrese la contraseña</label>
                <input name="password" type="password" required="true"></br>
                <input type="submit" placeholder="Ingresar">
            </form>
    </body>
</html>
