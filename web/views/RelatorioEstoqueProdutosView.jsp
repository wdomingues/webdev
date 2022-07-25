<%-- 
    Document   : ListaProdutosView
    Created on : 14/07/2022, 16:37:38
    Author     : winne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="utils.Validators, java.util.*,application.Produto, application.Categoria, application.Funcionario" %>
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

                   
            <h1>Relatório do Estoque de Produtos</h1>     
            <p></p>
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
                        %>
                        <tr>
                            <td><%=product.getId()%></td>
                            <td><%=product.getNomeProduto()%></td>
                            <td><%=product.getDescricao()%></td>
                            <td><%=Validators.valorViewFormatter(product.getPrecoCompra())%></td>
                            <td><%=Validators.valorViewFormatter(product.getPrecoVenda())%></td>
                            <td><%=product.getQuantidadeDisponivel()%></td>
                            <td><%=product.getLiberadoVenda()%></td>
                            <td><%=category%></td>
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
