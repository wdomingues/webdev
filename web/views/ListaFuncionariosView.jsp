<%-- 
    Document   : ListaFuncionariosView
    Created on : 14/07/2022, 18:33:14
    Author     : winne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, application.Funcionario" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../auxJSPs/Header.html" %>
    </head>
    <body>
        <% Funcionario usuarioSesao = (Funcionario) request.getSession().getAttribute("usuario");
            String nomeUsuario = usuarioSesao.getNome();
            String papelExtForm = (String)session.getAttribute("papelExt");
        %>
        <div class="container mt-2">
            <%@include file="../auxJSPs/restricteds/NavBarSelector.jsp"%>
            <h6 style="text-align: right">Área Restrita - Usuário logado: <%=nomeUsuario%> - Papel: <%=papelExtForm%>.</h6>

                   
            <h1>Lista de Funcionários</h1>     
            <p></p>
            <a href="FuncionarioController?option=insert" class="btn btn-outline-primary">Inserir Novo</a>
            <p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Nome do Funcionário</th>
                            <th scope="col">CPF</th>
                            <th scope="col">Senha</th>
                            <th scope="col">Papel</th>
                            <th scope="col"><div class="float-right">Ações</div></th>
                        </tr>
                    </thead> 
                    <tbody>
                        <%
                            ArrayList<Funcionario> employees = (ArrayList<Funcionario>) request.getAttribute("myEmployees");
                            for (Funcionario employee : employees) {
                                String edit_link = "FuncionarioController?option=edit&id="+employee.getId();
                                String delete_link = "FuncionarioController?option=delete&id="+employee.getId();
                                
                                String papel = employee.getPapel();
                                if (papel == "") papel = "";
                                else if (papel.equals("0")) papel = "Administrador";
                                else if (papel.equals("1")) papel = "Vendedor";
                                else if (papel.equals("2")) papel = "Comprador";
                                

                        %>
                        <tr>
                            <td><%= employee.getId() %></td>
                            <td><%= employee.getNome() %></td>
                            <td><%= employee.getDocumento() %></td>
                            <td><%= employee.getSenha() %></td>
                            <td><%= papel %></td>

                            <td>
                                <a href="<%=edit_link%>" class="btn btn-outline-secondary float-right">Editar</a>
                                <a href="<%=delete_link%>" class="btn btn-outline-danger float-right">Apagar</a>
                            </td> 
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="../auxJSPs/BasicScripts.html" %>
    </body>
</html>
