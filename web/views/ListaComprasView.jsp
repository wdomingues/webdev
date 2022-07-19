<%-- 
    Document   : ListaComprasView
    Created on : 14/07/2022, 19:15:51
    Author     : winne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,application.Compra, application.Fornecedor, application.Produto, application.Funcionario" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../auxJSPs/Header.html" %>
    </head>
    <body>

        <div class="container mt-2">

            <jsp:include page="../auxJSPs/NavBar.html" />
                   
            <h1>Lista de Compras</h1>     
            <p></p>
            <a href="CompraController?option=insert" class="btn btn-outline-primary">Inserir Novo</a>
            <p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Quantidade</th>
                            <th scope="col">Data da Compra</th>
                            <th scope="col">Valor</th>
                            <th scope="col">Fornecedor</th>
                            <th scope="col">Produto</th>
                            <th scope="col">Funcionário</th>
                            <th scope="col"><div class="float-right">Ações</div></th>
                        </tr>
                    </thead> 
                    <tbody>
                        <%
                            ArrayList<Compra> compras = (ArrayList<Compra>) request.getAttribute("myCompras");
                            ArrayList<Fornecedor> suppliers = (ArrayList<Fornecedor>) request.getAttribute("mySuppliers");
                            ArrayList<Produto> products = (ArrayList<Produto>) request.getAttribute("myProducts");
                            ArrayList<Funcionario> employees = (ArrayList<Funcionario>) request.getAttribute("myEmployees");

                            for (Compra compra : compras) {
                                String supplier = "";
                                for(Fornecedor sup : suppliers){
                                    if (sup.getId() == compra.getIdFornecedor()) {
                                        supplier = sup.getNome();
                                        break;
                                    }
                                }
                                String product = "";
                                for(Produto prod : products){
                                    if (prod.getId() == compra.getIdProduto()) {
                                        product = prod.getNomeProduto();
                                        break;
                                    }
                                }
                                String employee = "";
                                for(Funcionario emp : employees){
                                    if (emp.getId() == compra.getIdFuncionario()) { 
                                        employee = emp.getNome();
                                        break;
                                    }
                                }
                                
                                String edit_link = "CompraController?option=edit&id="+compra.getId();
                                String delete_link = "CompraController?option=delete&id="+compra.getId();
                        %>
                        <tr>
                            <td><%=compra.getId()%></td>
                            <td><%=compra.getQuantidadeCompra()%></td>
                            <td><%=compra.getDataCompra()%></td>
                            <td><%=compra.getValorCompra()%></td>
                            <td><%=supplier%></td>
                            <td><%=product%></td>
                            <td><%=employee%></td>

                            <td>
                                <a href="<%=edit_link%>" class="btn btn-outline-secondary float-right">Editar</a>
                                <a href="<%=delete_link%>" class="btn btn-outline-danger float-right">Apagar</a>
                            </td> 
                        </tr>
                        <%
                            }
//
//                            request.setAttribute("myCompras", compras);
//                            request.setAttribute("mySuppliers", suppliers);
//                            request.setAttribute("myProducts", products);
//                            request.setAttribute("myEmployees", employees);
//                            RequestDispatcher show = getServletContext().getRequestDispatcher("/forms/FormCompra.jsp");
//                            show.forward(request, response);
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="../auxJSPs/BasicScripts.html" %>
    </body>
</html>