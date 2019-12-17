<%-- 
    Document   : errorlogin
    Created on : 17/12/2019, 11:23:25 AM
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
       <%
                String error = (String) request.getSession().getAttribute("error");
            %> 
         Hubo un error   <%=error%>
         
         <p>
            <a href="./index.html">Regresar a Inicio</a>
        </p>
    </body>
</html>
