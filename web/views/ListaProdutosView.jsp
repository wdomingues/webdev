<%-- 
    Document   : ListaProdutosView
    Created on : 14/07/2022, 16:37:38
    Author     : winne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,application.Produto, application.Categoria" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../auxJSPs/Header.html" %>
    </head>
    <body>

        <div class="container mt-2">

            <jsp:include page="../auxJSPs/NavBar.html" />
                   
            <h1>Lista de Produtos</h1>     
            <p></p>
            <a href="ProdutoController?option=insert" class="btn btn-outline-primary">Inserir Novo</a>
            <p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Nome do Produto</th>
                            <th scope="col">Descricao</th>
                            <th scope="col">Preço de Compra</th>
                            <th scope="col">Preço de Venda</th>
                            <th scope="col">Quantidade</th>
                            <th scope="col">Liberado para venda</th>
                            <th scope="col">Categoria</th>
                            <th scope="col"><div class="float-right">Ações</div></th>
                        </tr>
                    </thead> 
                    <tbody>
                        <%
                            ArrayList<Produto> products = (ArrayList<Produto>) request.getAttribute("myProducts");
                            ArrayList<Categoria> categories = (ArrayList<Categoria>) request.getAttribute("myCategories");

                            for (Produto product : products) {
                                String category = "";
                                for(Categoria cat : categories){
                                    if (cat.getId() == product.getIdCategoria()) { 
                                        category = cat.getNomeCategoria();
                                        break;
                                    }
                                }
                                String edit_link = "ProdutoController?option=edit&id="+product.getId();
                                String delete_link = "ProdutoController?option=delete&id="+product.getId();
                        %>
                        <tr>
                            <td><%=product.getId()%></td>
                            <td><%=product.getNomeProduto()%></td>
                            <td><%=product.getDescricao()%></td>
                            <td><%=product.getPrecoCompra()%></td>
                            <td><%=product.getPrecoVenda()%></td>
                            <td><%=product.getQuantidadeDisponivel()%></td>
                            <td><%=product.getLiberadoVenda()%></td>
                            <td><%=category%></td>

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
