<%-- 
    Document   : ListaClientesView
    Created on : 14/07/2022, 19:07:18
    Author     : winne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,application.Cliente" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../auxJSPs/Header.html" %>
    </head>
    <body>

        <div class="container mt-2">

            <jsp:include page="../auxJSPs/restricteds/NavBarSelector.jsp" />
                   
            <h1>Lista de Clientes</h1>     
            <p></p>
            <a href="ClienteController?option=insert" class="btn btn-outline-primary">Inserir Novo</a>
            <p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Nome do Cliente</th>
                            <th scope="col">CPF</th>
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
                            ArrayList<Cliente> clients = (ArrayList<Cliente>) request.getAttribute("myClients");
                            for (Cliente client : clients) {
                                String edit_link = "ClienteController?option=edit&id="+client.getId();
                                String delete_link = "ClienteController?option=delete&id="+client.getId();
                        %>
                        <tr>
                            <td><%=client.getId()%></td>
                            <td><%=client.getNome()%></td>
                            <td><%=client.getDocumento()%></td>
                            <td><%=client.getEndereco()%></td>
                            <td><%=client.getBairro()%></td>
                            <td><%=client.getCidade()%></td>
                            <td><%=client.getUf()%></td>
                            <td><%=client.getCep()%></td>
                            <td><%=client.getTelefone()%></td>
                            <td><%=client.getEmail()%></td>

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