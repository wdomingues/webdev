<%-- 
    Document   : ListaCategoriasView
    Created on : 14/07/2022, 19:24:47
    Author     : winne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,application.Categoria" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../auxJSPs/Header.html" %>
    </head>
    <body>

        <div class="container mt-2">

            <jsp:include page="../auxJSPs/restricteds/NavBarSelector.jsp" />
                   
            <h1>Lista de Categorias</h1>     
            <p></p>
            <a href="CategoriaController?option=insert" class="btn btn-outline-primary">Inserir Novo</a>
            <p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Nome da Categoria</th>
                            <th scope="col"><div class="float-right">Ações</div></th>
                        </tr>
                    </thead> 
                    <tbody>
                        <%
                            ArrayList<Categoria> categories = (ArrayList<Categoria>) request.getAttribute("myCategories");
                            for (Categoria cat : categories) {
                                String edit_link = "CategoriaController?option=edit&id="+cat.getId();
                                String delete_link = "CategoriaController?option=delete&id="+cat.getId();
                        %>
                        <tr>
                            <td><%=cat.getId()%></td>
                            <td><%=cat.getNomeCategoria()%></td> 
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
