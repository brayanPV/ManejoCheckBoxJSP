<%-- 
    Document   : crearusuario
    Created on : 16/12/2019, 10:32:16 PM
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
        <div>
            <form action="../../registrar.do">
                <label>Ingrese el usuario</label>
                <input name="usuario" type="email" required="true"> </br>
                <label>ingrese la contraseña</label>
                <input name="password" type="password" required="true"></br>
                <input type="submit" placeholder="Registrar">
            </form>
        </div>
    </body>
</html>
