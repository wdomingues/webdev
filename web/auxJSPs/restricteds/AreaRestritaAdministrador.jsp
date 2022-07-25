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
        <%@include file="../Header.html"%>
        <title>CompraVenda</title>
    </head>
    <body>
        <% if (session == null) {%>
                <jsp:forward page="../../forms/LoginForm.jsp" />
        
        <% } else {
                if (session.getAttribute("usuario") == null) {%>
                    <jsp:forward page="../../forms/LoginForm.jsp" />
        <%      }
            }%>
                
        <div class="container mt-2">
            
            <%@include file="NavBarAdministrador.html" %>
            <%--<%= request.getAttribute("mensagem") %>--%>
            
            <% 
                Funcionario usuario = (Funcionario) session.getAttribute("usuario");
                String nomeUsuario = usuario.getNome();
                String papelExtSession = (String)session.getAttribute("papelExt");
            %>
            <h6 style="text-align: right">Área Restrita - Usuário logado: <%=nomeUsuario%> - Papel: <%=papelExtSession%>.</h6>

            <%@include file="../Corpo.html"%>
            
        </div>
            
<!--        <script type="text/javascript" src="assets/js/jquery-3.6.0.min.js"></script>
        <script type="text/javascript" src="assets/js/jquery.mask.js"></script>
        <script type="text/javascript" src="assets/bootstrap/js/bootstrap.bundle.min.js"></script>-->
        
        <%@include file="../BasicScripts.html" %>  
    </body>
</html>

