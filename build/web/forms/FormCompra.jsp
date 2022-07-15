<%-- 
    Document   : FormCompra
    Created on : 14/07/2022, 19:16:15
    Author     : winne
--%>

<%@page import="application.Compra, application.Fornecedor, application.Produto, application.Funcionario, java.lang.Integer, java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="pt-BR">
    <head>
        <%@include file="../auxJSPs/Header.html" %>
        <title>Cadastro de Compra</title>
    </head>

    <body>
        <%@include file="../auxJSPs/NavBar.html" %>
        <%
            ArrayList<Compra> compras = (ArrayList<Compra>) request.getAttribute("myCompras");
            ArrayList<Fornecedor> suppliers = (ArrayList<Fornecedor>) request.getAttribute("mySuppliers");
            ArrayList<Produto> products = (ArrayList<Produto>) request.getAttribute("myProducts");
            ArrayList<Funcionario> employees = (ArrayList<Funcionario>) request.getAttribute("myEmployees");

            Compra compra = (Compra)request.getAttribute("compra");
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
        %>
        
        <div class="forms">
            <h2 class="m-5">
                Cadastro de Compra
            </h2>

            <div>
                <form method="POST" action="CompraController" name="cad-compra" id="cad-compra">
                    <input type="hidden" class="form-control" name="id" value="<%= compra.getId() %>">
                    
                    <div class="mb-4">
                        <label for="quantidade-compra" class="form-label">Quantidade:</label>
                        <input type="text" class="form-control" id="quantidade-compra" name="quantidade_compra" value="<%= compra.getQuantidadeCompra() %>" required size="30" placeholder="Quantidade"/>
                    </div>
                    <div class="mb-4">
                        <label for="data-compra" class="form-label">Data da compra:</label>
                        <input type="text" class="form-control" id="data-compra" name="data_compra" value="<%= compra.getDataCompra() %>" required size="30" placeholder="Data da Compra"/>
                    </div>
                    <div class="mb-4">
                        <label for="valor-compra" class="form-label">Valor:</label>
                        <input type="text" class="form-control" id="valor-compra" name="valor_compra" value="<%= compra.getValorCompra() %>" required size="30" placeholder="Valor"/>
                    </div>
                    <div class="mb-4">
                        <label for="fornecedor-compra" class="form-label">ID do Fornecedor:</label>
                        <input type="text" class="form-control" id="fornecedor-compra" name="id_fornecedor" value="<%= supplier %>" required size="30" placeholder="Fornecedor"/>
                    </div>
                    <div class="mb-4">
                        <label for="produto-compra" class="form-label">ID do Produto:</label>
                        <input type="text" class="form-control" id="produto-compra" name="id_produto" value="<%= product %>" required size="30" placeholder="Produto"/>
                    </div>
                    <div class="mb-4">
                        <label for="funcionario-compra" class="form-label">Funcionário:</label>
                        <select class="form-control" id="funcionario-compra" name="id_funcionario" required>
                            <option value="">Selecione o Funcionário</option>
                        <%
                            String options = "";
                            for (Funcionario e : employees){
                                options += "<option value="+ e.getId() + ">" + e.getNome() + "</option>\n";
                                out.println(options);
                        %>        
                        </select>
                    </div>
                    <div class="mb-4 center-horizontally">
                        <button type="submit" class="btn btn-dark" id="login-button">Salvar</button>
                    </div>
                    
                    <a href="CompraController?option=get" class="btn btn-outline-danger">Voltar</a>
                </form>
            </div>
        </div>

        <%@include file="../auxJSPs/BasicScripts.html" %>
    </body>
</html>

