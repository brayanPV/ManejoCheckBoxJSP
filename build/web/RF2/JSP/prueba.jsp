<%-- 
    Document   : prueba
    Created on : 17/12/2019, 11:16:10 AM
    Author     : stive
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="DTO.Usuario"%>
<%@page import="NEGOCIO.SegundoParcial"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>

            <%
                Usuario s = (Usuario) request.getSession().getAttribute("usuario");
                String usuario = s.getCorreo();

            %>

            <h3>Hola esta es la prueba </h3>
            <form action="./presentarPrueba.do">
                <input type="hidden" name="correo" value="<%=usuario%>"  />
                <p> 10 <input type="checkbox" name="check" value="10">  20 <input type="checkbox" name="check" value="20"> </p>
                <p> 30 <input type="checkbox" name="check" value="30">  40 <input type="checkbox" name="check" value="40"> </p>
                <p> 50 <input type="checkbox" name="check" value="50">  60 <input type="checkbox" name="check" value="60"> </p>
                <p> 70 <input type="checkbox" name="check" value="70">  80 <input type="checkbox" name="check" value="80"> </p>
                <p> 90 <input type="checkbox" name="check" value="90"> </p>
                <p> <input type="submit" placeholder="Presentar"></p>

            </form>
        </div>
    </body>
</html>
