<%-- 
    Document   : ListaFornecedoresView
    Created on : 14/07/2022, 18:52:28
    Author     : winne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,application.Fornecedor, application.Funcionario" %>
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

                   
            <h1>Lista de Fornecedores</h1>     
            <p></p>
            <a href="FornecedorController?option=insert" class="btn btn-outline-primary">Inserir Novo</a>
            <p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Razão Social</th>
                            <th scope="col">CNPJ</th>
                            <th scope="col">Endereço</th>
                            <th scope="col">Bairro</th>
                            <th scope="col">Cidade</th>
                            <th scope="col">UF</th>
                            <th scope="col">CEP</th>
                            <th scope="col">Telefone</th>
                            <th scope="col">e-mail</th>
                            <th scope="col"><div class="float-right">Ações</div></th>
                        </tr>
                    </thead> 
                    <tbody>
                        <%
                            ArrayList<Fornecedor> suppliers = (ArrayList<Fornecedor>) request.getAttribute("mySuppliers");
                            for (Fornecedor supplier : suppliers) {
                                String edit_link = "FornecedorController?option=edit&id="+supplier.getId();
                                String delete_link = "FornecedorController?option=delete&id="+supplier.getId();
                        %>
                        <tr>
                            <td><%=supplier.getId()%></td>
                            <td><%=supplier.getNome()%></td>
                            <td><%=supplier.getDocumento()%></td>
                            <td><%=supplier.getEndereco()%></td>
                            <td><%=supplier.getBairro()%></td>
                            <td><%=supplier.getCidade()%></td>
                            <td><%=supplier.getUf()%></td>
                            <td><%=supplier.getCep()%></td>
                            <td><%=supplier.getTelefone()%></td>
                            <td><%=supplier.getEmail()%></td>

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

