<%-- 
    Document   : error
    Created on : 16/12/2019, 10:50:07 PM
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
        <h3>
            <%
                String error = (String) request.getSession().getAttribute("error");
            %> 
         Hubo un error   <%=error%>
        </h3>
        <p>
            <a href="./index.html">Regresar a Inicio</a>
        </p>
    </body>
</html>
