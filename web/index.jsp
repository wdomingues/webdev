<%-- 
    Document   : index
    Created on : 14/07/2022, 21:34:48
    Author     : winne
--%>
<%@page import="java.util.ArrayList, application.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <%@include file="./auxJSPs/Header.html"%>
        <title>CompraVenda</title>
    </head>
    <body>
        <div class="container mt-2">
            <%@include file="../auxJSPs/restricteds/NavBarSelector.jsp"%>    
        <% if (request.getSession() != null) {
                Funcionario usuario = (Funcionario) request.getSession().getAttribute("usuario");
                if (usuario != null){
                    String nomeUsuario = usuario.getNome();
                    String papelExtSession = (String)session.getAttribute("papelExt");
        %>
        
                <% if (papelExtSession != "") { %>
                    <h6 style="text-align: right">Área Restrita - Usuário logado: <%=nomeUsuario%> - Papel: <%=papelExtSession%>.</h6>
            <%     }
                }
            }
        %>
            <%@include file="./auxJSPs/Corpo.html" %>
            
            
        </div>
            
        <%@include file="./auxJSPs/BasicScripts.html" %>  
    </body>
</html>
