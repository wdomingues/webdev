<%-- 
    Document   : FormCategoria
    Created on : 14/07/2022, 19:26:23
    Author     : winne
--%>
<%@page import="application.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="pt-BR">
    <head>
        <%@include file="../auxJSPs/Header.html" %>
        <title>Cadastro de Categoria de Produto</title>
    </head>

    <body>
        <%@include file="../auxJSPs/NavBar.html" %>
        <%
            Categoria cat = (Categoria)request.getAttribute("category");
        %>
        <div class="forms">
            <h2 class="m-5">
                Cadastro de Categoria
            </h2>

            <div>
                <form method="POST" action="CategoriaController" name="cad-categoria" id="cad-categoria">
                    <input type="hidden" class="form-control" name="id" value="<%= cat.getId() %>">
                    <div class="mb-4">
                        <label for="category-name" class="form-label">Nome da Categoria:</label>
                        <input type="text" class="form-control" id="category-name" name="nome_categoria" value="<%= cat.getNomeCategoria() %>" required size="30" maxlength="50" placeholder="Nome da Categoria"/>
                    </div>
                    <div class="mb-4 center-horizontally">
                        <button type="submit" class="btn btn-dark" id="login-button">Salvar</button>
                    </div>
                    <a href="CategoriaController?option=get" class="btn btn-outline-danger">Voltar</a>
                </form>
            </div>
        </div>

        <%@include file="../auxJSPs/BasicScripts.html" %>
    </body>
</html>