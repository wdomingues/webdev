<%-- 
    Document   : FormFuncionario
    Created on : 14/07/2022, 18:36:09
    Author     : winne
--%>

<%@page import="application.Funcionario, java.lang.String, java.lang.Integer, java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="pt-BR">
    <head>
        <%@include file="../auxJSPs/Header.html" %>
        <title>Cadastro de Funcionário</title>
    </head>

    <body>
        <%@include file="../auxJSPs/NavBar.html" %>
        <%
            ArrayList<Funcionario> employees = (ArrayList<Funcionario>) request.getAttribute("myEmployees");

            Funcionario employee = (Funcionario)request.getAttribute("employee");
            int id = employee.getId();
            String nome = employee.getNome();
            String cpf = employee.getDocumento();
            String senha = employee.getSenha();
            String papel = employee.getPapel();
            
            String selPapel = "";
            String selPapelExt = "";
            if(papel == "") selPapelExt = "Selecione o papel"; 
            else if(papel.substring(0,1) == "V") {
                selPapel = "V";
                selPapelExt = "Vendedor";
            } else if(papel.substring(0,1) == "C") {
                selPapel = "C";
                selPapelExt = "Comprador";
            } else if(papel.substring(0,1) == "A") {
                selPapel = "A";
                selPapelExt = "Administrador";
            }
        %>
        
        <div class="forms">
            <h2 class="m-5">
                Cadastro de Funcionario
            </h2>

            <div>
                <form method="POST" action="FuncionarioController" name="cad-funcionario" id="cad-funcionario">
                    <input type="hidden" class="form-control" name="id" value="<%= id %>">
                    
                    <div class="mb-4">
                        <label for="nome-funcionario" class="form-label">Nome:</label>
                        <input type="text" class="form-control" id="nome-funcionario" name="nome" value="<%= nome %>" required size="80" placeholder="José da Silva"/>
                    </div>
                    <div class="mb-4">
                        <label for="cpf-funcionario" class="form-label">CPF:</label>
                        <input type="text" class="form-control" id="cpf-funcionario" name="cpf" value="<%= cpf %>" required size="14" placeholder="999.999.999-00" data-mask="000.000.000-00"/>
                    </div>
                    <div class="mb-4">
                        <label for="senha-funcionario" class="form-label">Senha:</label>
                        <input type="password" class="form-control" id="senha-funcionario" name="senha" value="<%= senha %>" required size="10"/>
                    </div>
                    <div class="mb-4">
                        <label for="papel-funcionario" class="form-label">Papel:</label>
                        <select class="form-control" id="papel-funcionario" name="papel" required>
                            <option value="<%= selPapel %>"><%= selPapelExt %></option>
                            <option value="V">Vendedor</option>
                            <option value="C">Comprador</option>
                            <option value="A">Administrador</option>
                        </select>
                    </div>
                    <div class="mb-4 center-horizontally">
                        <button type="submit" class="btn btn-dark" id="login-button">Salvar</button>
                    </div>
                    
                    <a href="FuncionarioController?option=get" class="btn btn-outline-danger">Voltar</a>
                </form>
            </div>
        </div>
        <%@include file="../auxJSPs/BasicScripts.html" %>
    </body>
</html>
