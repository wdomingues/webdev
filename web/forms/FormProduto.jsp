<%-- 
    Document   : FormProduto
    Created on : 14/07/2022, 16:38:26
    Author     : winne
--%>

<%@page import="application.Produto, application.Funcionario, application.Categoria, application.Produto, application.Funcionario, java.lang.String, java.lang.Integer, java.util.ArrayList, java.util.Date" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="pt-BR">
    <head>
        <%@include file="../auxJSPs/Header.html" %>
        <title>Cadastro de Produto</title>
    </head>

    <body>
        <%
            ArrayList<Produto> products = (ArrayList<Produto>) request.getAttribute("myProducts");
            ArrayList<Categoria> categories = (ArrayList<Categoria>) request.getAttribute("myCategories");

            Produto product = (Produto)request.getAttribute("product");
            String nome = product.getNomeProduto();
            String descricao = product.getDescricao();
            float precoCompra = product.getPrecoCompra();
            float precoVenda = product.getPrecoVenda();
            int qtdDisponivel = product.getQuantidadeDisponivel();
            String liberadoVenda = product.getLiberadoVenda();

            String category = "";
            String selCat = "";
            String libVendaS = "";
            String libVendaN = "";
            int catId = 0;
            for(Categoria cat : categories){
                if (cat.getId() == product.getIdCategoria()) { 
                    category = cat.getNomeCategoria();
                    catId = cat.getId();
                    break;
                }
            }
            if(category == "") selCat = "Selecione a categoria"; else selCat = category;
            if (liberadoVenda.equalsIgnoreCase("")){
                libVendaN = "";
                libVendaS = "";
            } else if(liberadoVenda.equalsIgnoreCase("S")) {
                libVendaS = "checked";
                libVendaN = "";
            } else if(liberadoVenda.equalsIgnoreCase("N")){
                libVendaN = "checked";
                libVendaS = "";
            }
            Funcionario usuario = (Funcionario) request.getSession().getAttribute("usuario");
            String nomeUsuario = usuario.getNome();
            String papelExtForm = (String)session.getAttribute("papelExt");
        %>
        <div class="container mt-2">
            <%@include file="../auxJSPs/restricteds/NavBarSelector.jsp"%>
            <h6 style="text-align: right">Área Restrita - Usuário logado: <%=nomeUsuario%> - Papel: <%=papelExtForm%>.</h6>

            <div class="forms">
                <h2 class="m-5">
                    Cadastro de Produto
                </h2>

                <div>
                    <form method="POST" action="ProdutoController" name="cad-product" id="cad-product">
                        <input type="hidden" class="form-control" name="id" value="<%= product.getId() %>">

                        <div class="mb-4">
                            <label for="nome-produto" class="form-label">Nome:</label>
                            <input type="text" class="form-control" id="quantidade-produto" name="nome_produto" value="<%= nome %>" required size="30" placeholder="Nome do Produto"/>
                        </div>
                        <div class="mb-4">
                            <label for="descricao-produto" class="form-label">Descricao:</label>
                            <input type="text" class="form-control" id="descricao-product" name="descricao" value="<%= descricao %>" required size="30" placeholder="Descricao do Produto"/>
                        </div>
                        <div class="mb-4">
                            <label for="preco-compra-produto" class="form-label">Preco de Compra:</label>
                            <input type="number" class="dinheiro form-control " id="preco-compra-produto" name="preco_compra" value="<%= precoCompra %>" required size="30" placeholder="Preco de Compra" step="0.01"/>
                        </div>
                        <div class="mb-4">
                            <label for="preco-venda-produto" class="form-label">Preco de Venda</label>
                            <input type="number" class="dinheiro form-control " id="preco-venda-produto" name="preco_venda" value="<%= precoVenda %>" required size="30" placeholder="Preco de Venda" step="0.01"/>
                        </div>
                        <div class="mb-4">
                            <label for="quantidade-disponivel-produto" class="form-label">Quantidade Disponivel</label>
                            <input type="number" class="dinheiro form-control " id="quantidade-disponivel-produto" name="quantidade_disponivel" value="<%= qtdDisponivel %>" required size="30" placeholder="Quantidade Disponivel" step="1"/>
                        </div>
                        <div class="mb-4">
                            <label for="liberado-venda-produto" class="form-label">Liberado para venda:</label><br>
                            <input type="radio" <%= libVendaS %> id="liberado-venda-produto-S" name="liberado_venda" value="S"/>
                            <label for="liberado-venda-produto-S" class="form-label">Sim</label><br>
                            <input type="radio" <%=libVendaN %> id="liberado-venda-produto-N" name="liberado_venda" value="N"/>
                            <label for="liberado-venda-produto-N" class="form-label">Não</label>
                        </div>
                        <div class="mb-4">
                            <label for="cateroria-produto" class="form-label">Categoria:</label>
                            <select class="form-control" id="categoria-produto" name="id_categoria" required>
                                <option value="<%= catId %>"><%= selCat %></option>
                            <%
                                String aux = "";
                                for (Categoria c : categories){
                                    aux = "<option value="+ c.getId() + ">" + c.getNomeCategoria() + "</option>\n";
                                    out.println(aux);
                                }
                            %>        
                            </select>
                        </div>
                        <div class="mb-4 center-horizontally">
                            <button type="submit" class="btn btn-dark" id="login-button">Salvar</button>
                        </div>

                        <a href="ProdutoController?option=get" class="btn btn-outline-danger">Voltar</a>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="../auxJSPs/BasicScripts.html" %>
    </body>
</html>
