<%-- 
    Document   : ListaProdutosView
    Created on : 14/07/2022, 16:37:38
    Author     : winne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="utils.Validators, java.util.*,application.Produto, application.Categoria" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../auxJSPs/Header.html" %>
    </head>
    <body>

        <div class="container mt-2">

            <jsp:include page="../auxJSPs/restricteds/NavBarSelector.jsp" />
                   
            <h1>Lista de Produtos Disponíveis</h1>     
            <p></p>
            <p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Nome do Produto</th>
                            <th scope="col">Descricao</th>
                            <th scope="col">Preço de Venda</th>
                            <th scope="col">Quantidade</th>
                            <th scope="col">Categoria</th>
                        </tr>
                    </thead> 
                    <tbody>
                        <%
                            ArrayList<Produto> products = (ArrayList<Produto>) request.getAttribute("availableProducts");
                            ArrayList<Categoria> categories = (ArrayList<Categoria>) request.getAttribute("myCategories");

                            ArrayList<Produto> availableProducts = new ArrayList<>();
                            for (Produto prod : products){
                                if (prod.getLiberadoVenda().equalsIgnoreCase("S") && prod.getQuantidadeDisponivel()>0)
                                    availableProducts.add(prod);
                            }
                            
                            for (Produto product : availableProducts) {
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
                            <td><%=Validators.valorViewFormatter(product.getPrecoVenda())%></td>
                            <td><%=product.getQuantidadeDisponivel()%></td>
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
