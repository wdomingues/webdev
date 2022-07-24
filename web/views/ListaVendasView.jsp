<%-- 
    Document   : ListVendasView
    Created on : 14/07/2022, 16:37:38
    Author     : winne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,application.Venda, application.Cliente, 
         application.Produto, application.Funcionario, utils.Validators" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../auxJSPs/Header.html" %>
    </head>
    <body>

        <div class="container mt-2">

            <jsp:include page="../auxJSPs/restricteds/NavBarSelector.jsp" />
                   
            <h1>Lista de Vendas</h1>     
            <p></p>
            <a href="VendaController?option=insert" class="btn btn-outline-primary">Inserir Novo</a>
            <p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Quantidade</th>
                            <th scope="col">Data da Venda</th>
                            <th scope="col">Valor</th>
                            <th scope="col">Cliente</th>
                            <th scope="col">Produto</th>
                            <th scope="col">Funcionário</th>
                            <th scope="col"><div class="float-right">Ações</div></th>
                        </tr>
                    </thead> 
                    <tbody>
                        <%
                            Funcionario usuario = (Funcionario) request.getSession().getAttribute("usuario");
                            
                            ArrayList<Venda> vendas = (ArrayList<Venda>) request.getAttribute("myVendas");
                            ArrayList<Cliente> clients = (ArrayList<Cliente>) request.getAttribute("myClients");
                            ArrayList<Produto> products = (ArrayList<Produto>) request.getAttribute("myProducts");
                            ArrayList<Funcionario> employees = (ArrayList<Funcionario>) request.getAttribute("myEmployees");

                            for (Venda venda : vendas) {
                                String client = "";
                                for(Cliente cli : clients){
                                    if (cli.getId() == venda.getIdCliente()) { 
                                        client = cli.getNome();
                                        break;
                                    }
                                }
                                String product = "";
                                for(Produto prod : products){
                                    if (prod.getId() == venda.getIdProduto()) { 
                                        product = prod.getNomeProduto();
                                        break;
                                    }
                                }
                                String employee = "";
                                for(Funcionario emp : employees){
                                    if (emp.getId() == venda.getIdFuncionario()) {
                                        employee = emp.getNome();
                                        break;
                                    }
                                }
                                
                                String edit_link = "VendaController?option=edit&id="+venda.getId();
                                String delete_link = "VendaController?option=delete&id="+venda.getId();
                        %>
                        <tr>
                            <td><%=venda.getId()%></td>
                            <td><%=venda.getQuantidadeVenda()%></td>
                            <td><%=Validators.convertDate2String(venda.getDataVenda())%></td>
                            <td><%=Validators.valorViewFormatter(venda.getValorVenda())%></td>
                            <td><%=client%></td>
                            <td><%=product%></td>
                            <td><%=employee%></td>

                            <td>
                            <%
                                if (usuario.getId() == venda.getIdFuncionario()) { 
                            %>
                                <a href="<%=edit_link%>" class="btn btn-outline-secondary float-right">Editar</a>
                                <a href="<%=delete_link%>" class="btn btn-outline-danger float-right">Apagar</a>
                            <%
                                }
                            %>
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
