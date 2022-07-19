<%-- 
    Document   : AreaRestritaAdministrador
    Created on : 19/07/2022, 07:46:26
    Author     : winne
--%>

<%@page import="application.Funcionario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <%@include file="./auxJSPs/Header.html"%>
        <title>CompraVenda</title>
    </head>
    <body>
        <% if (session == null) {%>
                <jsp:forward page="./forms/LoginForm.jsp" />
        
        <% } else {
                if (session.getAttribute("usuario") == null) {%>
                    <jsp:forward page="./forms/LoginForm.jsp" />
        <%      }
            }%>
                
        <div class="container mt-2">
            
            <%@include file="./auxJSPs/NavBarAdministrador.html" %>
            <%= request.getAttribute("mensagem") %>
            
            <% 
                Funcionario usuario = (Funcionario) session.getAttribute("usuario");
            %>
            <h5> Area Restrita - Usuario: <%= usuario.getNome() %>.</h5>
            <a href="LogOut">Sair</a>
            
            <h1>CompraVenda</h1>
            <h2>Sistema comercial da WiseShop</h2>
            <h3>IC/UFF - 2022-1</h3>
            <h3>Trabalho da disciplina Desenvolvimento Web</h3> 
            <h3>DevWeb 2022-2!</h1>
            <h3>Professor: Leonardo Cruz Costa</h3>
            <h3>Alunos: Felipe Genú e Winne Domingues</h3>
            
        </div>
            
<!--        <script type="text/javascript" src="assets/js/jquery-3.6.0.min.js"></script>
        <script type="text/javascript" src="assets/js/jquery.mask.js"></script>
        <script type="text/javascript" src="assets/bootstrap/js/bootstrap.bundle.min.js"></script>-->
        
        <%@include file="./auxJSPs/BasicScripts.html" %>  
    </body>
</html>

