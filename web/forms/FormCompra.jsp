<%-- 
    Document   : FormCompra
    Created on : 14/07/2022, 19:16:15
    Author     : winne
--%>

<%@page import="application.Compra, application.Funcionario, application.Fornecedor, application.Produto, application.Funcionario, java.lang.String, java.lang.Integer, java.util.ArrayList, java.util.Date" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="pt-BR">
    <head>
        <%@include file="../auxJSPs/Header.html" %>
        <title>Cadastro de Compra</title>
    </head>

    <body>
        <%
            ArrayList<Compra> compras = (ArrayList<Compra>) request.getAttribute("myCompras");
            ArrayList<Fornecedor> suppliers = (ArrayList<Fornecedor>) request.getAttribute("mySuppliers");
            ArrayList<Produto> products = (ArrayList<Produto>) request.getAttribute("myProducts");
            ArrayList<Funcionario> employees = (ArrayList<Funcionario>) request.getAttribute("myEmployees");

            Compra compra = (Compra)request.getAttribute("compra");
            int qtdCompra = compra.getQuantidadeCompra();
            Date dataCompra = compra.getDataCompra();
            float valorCompra = compra.getValorCompra();

            String supplier = "";
            String selSup = "";
            int supId = 0;
            for(Fornecedor sup : suppliers){
                if (sup.getId() == compra.getIdFornecedor()) { 
                    supplier = sup.getNome();
                    supId = sup.getId();
                    break;
                }
            }
            String product = "";
            String selProd = "";
            int prodId = 0;
            for(Produto prod : products){
                if (prod.getId() == compra.getIdProduto()) {
                    product = prod.getNomeProduto();
                    prodId = prod.getId();
                    break;
                }
            }
            String employee = "";
            String selEmp = "";
            int empId = 0;
            for(Funcionario emp : employees){
                if (emp.getId() == compra.getIdFuncionario()) {
                    employee = emp.getNome();
                    empId = emp.getId();
                    break;
                }
            }
            if(supplier == "") selSup = "Selecione o fornecedor"; else selSup = supplier;
            if(product == "") selProd = "Selecione o produto"; else selProd = product;
            if(employee == "") selEmp = "Selecione o funcionario"; else selEmp = employee;

            Funcionario usuario = (Funcionario) request.getSession().getAttribute("usuario");
            String nomeUsuario = usuario.getNome();
            String papelExtForm = (String)session.getAttribute("papelExt");
        %>
        <div class="container mt-2">
            <%@include file="../auxJSPs/restricteds/NavBarSelector.jsp"%>
            <h6 style="text-align: right">Área Restrita - Usuário logado: <%=nomeUsuario%> - Papel: <%=papelExtForm%>.</h6>

            <div class="forms">
                <h2 class="m-5">
                    Cadastro de Compra
                </h2>

                <div>
                    <form method="POST" action="CompraController" name="cad-compra" id="cad-compra">
                        <input type="hidden" class="form-control" name="id" value="<%= compra.getId() %>">

                        <div class="mb-4">
                            <label for="quantidade-compra" class="form-label">Quantidade:</label>
                            <input type="number" class="form-control" id="quantidade-compra" name="quantidade_compra" value="<%= qtdCompra %>" required size="30" placeholder="Quantidade" step="1"/>
                        </div>
                        <div class="mb-4">
                            <label for="data-compra" class="form-label">Data da compra:</label>
                            <input type="date" class="form-control" id="data-compra" name="data_compra" value="<%= dataCompra %>" required size="30" placeholder="Data da Compra" step="1"/>
                        </div>
                        <div class="mb-4">
                            <label for="valor-compra" class="form-label">Valor:</label>
                            <input type="number" class="dinheiro form-control " id="valor-compra" name="valor_compra" value="<%= valorCompra %>" required size="30" placeholder="Valor" step="0.01"/>
                        </div>
                        <div class="mb-4">
                            <label for="fornecedor-compra" class="form-label">Fornecedor:</label>
                            <select class="form-control" id="fornecedor-compra" name="id_fornecedor" required>
                                <option value="<%= supId %>"><%= selSup %></option>
                            <%
                                String aux = "";
                                for (Fornecedor f : suppliers){
                                    aux = "<option value="+ f.getId() + ">" + f.getNome() + "</option>\n";
                                    out.println(aux);
                                }
                            %>        
                            </select>
                        </div>
                        <div class="mb-4">
                            <label for="produto-compra" class="form-label">Produto:</label>
                            <select class="form-control" id="produto-compra" name="id_produto" required>
                                <option value="<%= prodId %>"><%= selProd %></option>
                            <%
                                String aux2 = "";
                                for (Produto p : products){
                                    aux2 = "<option value="+ p.getId() + ">" + p.getNomeProduto() + "</option>\n";
                                    out.println(aux2);
                                }
                            %>        
                            </select>
                        </div>
                        <input type="hidden" class="form-control" name="id_funcionario" value="<%= usuario.getId() %>">
                        <div class="mb-4 center-horizontally">
                            <button type="submit" class="btn btn-dark" id="login-button">Salvar</button>
                        </div>

                        <a href="CompraController?option=get" class="btn btn-outline-danger">Voltar</a>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="../auxJSPs/BasicScripts.html" %>
    </body>
</html>