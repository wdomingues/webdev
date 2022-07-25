<%-- 
    Document   : FormCategoria
    Created on : 14/07/2022, 19:26:23
    Author     : winne
--%>
<%@page import="application.Categoria, application.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="pt-BR">
    <head>
        <%@include file="../auxJSPs/Header.html" %>
        <title>Cadastro de Categoria de Produto</title>
    </head>

    <body>
        <%
            Categoria cat = (Categoria)request.getAttribute("category");
            Funcionario usuario = (Funcionario) request.getSession().getAttribute("usuario");
            String nomeUsuario = usuario.getNome();
            String papelUsr = usuario.getPapel();
            String papelExt = "";
            switch (papelUsr) {
                case Funcionario.Papeis.ADMINISTRADOR:
                    papelExt = "Administrador";
                    break;
                case Funcionario.Papeis.VENDEDOR:
                    papelExt = "Vendedor";
                    break;
                case Funcionario.Papeis.COMPRADOR:
                    papelExt = "Comprador";
                    break;
            }
        %>
        <%@include file="../auxJSPs/restricteds/NavBarSelector.jsp"%>
        <h3 style='text-align: right'>Usuário logado: <%=nomeUsuario%> - Papel: <%=papelExt%>.</h3>
        
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