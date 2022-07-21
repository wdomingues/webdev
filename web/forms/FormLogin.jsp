<%-- 
    Document   : FormLogin
    Created on : 14/07/2022, 19:49:43
    Author     : winne
--%>

<%@page import="application.Funcionario, java.lang.String, java.lang.Integer, java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="pt-BR">
    <head>
        <%@include file="../auxJSPs/Header.html" %>
        <title>Login</title>
    </head>

    <body>
        <%@include file="../auxJSPs/restricteds/NavBarSelector.jsp" %>

        <div class="forms">
            <h2 class="m-5">
                Entrar na sua conta
            </h2>

            <div>
                <form method="POST" action="LoginController" name="login" id="login">
                    <div class="mb-4">
                        <label for="cpf-login" class="form-label">CPF:</label>
                        <input type="text" class="form-control" id="cpf-login" name="cpf" value="" required size="14" placeholder="999.999.999-00" data-mask="000.000.000-00"/>
                    </div>
                    <div class="mb-4">
                        <label for="senha-login" class="form-label">Senha:</label>
                        <input type="password" class="form-control" id="senha-login" name="senha" value="" required size="10" maxlength="10"/>
                    </div>
                    <div class="mb-4 center-horizontally">
                        <button type="submit" class="btn btn-dark" id="login-button">Logar</button>
                    </div>
                    
                    <a href="index.jsp" class="btn btn-outline-danger">Voltar</a>
                </form>
            </div>
        </div>
        <%@include file="../auxJSPs/BasicScripts.html" %>
    </body>
</html>
