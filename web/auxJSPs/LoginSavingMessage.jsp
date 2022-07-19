<%-- 
    Document   : LoginSavingMessage
    Created on : 14/07/2022, 16:49:37
    Author     : winne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.lang.String"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="Header.html" %>
    </head>
    <body>
        <div class="container mt-2">

            <jsp:include page="NavBarNaoLogado.html" />

            <div class="col-8 mt-5">

        <% 
            String mensagem = (String) request.getAttribute("message");
            if (mensagem.toLowerCase().contains("erro")){
        %>
                <div class="alert alert-danger" role="alert">
                    <h5>
                        <%= mensagem %>
                    </h5>
                </div>    
        <%    
            } else {
        %>
                <div class="alert alert-success" role="alert">
                    <h5>
                        <%= mensagem %>
                    </h5>
                </div>
        <%
            }
        %>
                <p></p>
                <div><a href="LoginController">Voltar</a></div>
            </div>
        </div>

        <%@include file="BasicScripts.html" %>
    </body>
</html>