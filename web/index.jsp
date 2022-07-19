<%-- 
    Document   : index
    Created on : 14/07/2022, 21:34:48
    Author     : winne
--%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <%@include file="./auxJSPs/Header.html"%>
        <title>CompraVenda</title>
    </head>
    <body>
        <div class="container mt-2">
            <%@include file="./auxJSPs/NavBarNaoLogado.html" %>
            <%@include file="./auxJSPs/Corpo.html" %>
            
            <a href = "./forms/FormLogin.jsp">Fazer Login</a>
        </div>
            
        <%@include file="./auxJSPs/BasicScripts.html" %>  
    </body>
</html>
