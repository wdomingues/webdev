<%-- 
    Document   : ListaFuncionariosView
    Created on : 14/07/2022, 18:33:14
    Author     : winne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,application.Funcionario" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../auxJSPs/Header.html" %>
    </head>
    <body>

        <div class="container mt-2">

            <jsp:include page="../auxJSPs/NavBar.html" />
                   
            <h1>Lista de Funcionários</h1>     
            <p></p>
            <a href="FuncionarioController?option=insert" class="btn btn-outline-primary">Inserir Novo</a>
            <p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Nome do Funcionario</th>
                            <th scope="col">CPF</th>
                            <th scope="col">Senha</th>
                            <th scope="col">Papel</th>
                            <th scope="col"><div class="float-right">Ações</div><br></th>
                        </tr>
                    </thead> 
                    <tbody>
                        <%
                            ArrayList<Funcionario> employees = (ArrayList<Funcionario>) request.getAttribute("myEmployees");
                            for (Funcionario employee : employees) {
                                String edit_link = "FuncionarioController?option=edit&id="+employee.getId();
                                String delete_link = "FuncionarioController?option=delete&id="+employee.getId();
                        %>
                        <tr>
                            <td><%=employee.getId()%></td>
                            <td><%=employee.getNome()%></td>
                            <td><%=employee.getDocumento()%></td>
                            <td><%=employee.getSenha()%></td>
                            <td><%=employee.getPapel()%></td>

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
