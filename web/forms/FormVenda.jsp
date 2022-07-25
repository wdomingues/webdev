<%-- 
    Document   : FormVenda
    Created on : 14/07/2022, 16:38:26
    Author     : winne
--%>

<%@page import="application.Venda, application.Funcionario, application.Cliente, application.Produto, application.Funcionario, java.lang.String, java.lang.Integer, java.util.ArrayList, java.util.Date" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="pt-BR">
    <head>
        <%@include file="../auxJSPs/Header.html" %>
        <title>Cadastro de Venda</title>
    </head>

    <body>
        <%
            ArrayList<Venda> vendas = (ArrayList<Venda>) request.getAttribute("myVendas");
            ArrayList<Cliente> clients = (ArrayList<Cliente>) request.getAttribute("myClients");
            ArrayList<Produto> products = (ArrayList<Produto>) request.getAttribute("myProducts");
            ArrayList<Funcionario> employees = (ArrayList<Funcionario>) request.getAttribute("myEmployees");
            ArrayList<Produto> availableProducts = new ArrayList<>();

            Venda venda = (Venda)request.getAttribute("venda");
            int qtdVenda = venda.getQuantidadeVenda();
            Date dataVenda = venda.getDataVenda();
            float valorVenda = venda.getValorVenda();

            String client = "";
            String selCli = "";
            int cliId = 0;
            for(Cliente cli : clients){
                if (cli.getId() == venda.getIdCliente()) { 
                    client = cli.getNome();
                    cliId = cli.getId();
                    break;
                }
            }
            String product = "";
            String selProd = "";
            int prodId = 0;
            for(Produto prod : products){
                if (prod.getId() == venda.getIdProduto()) {
                    product = prod.getNomeProduto();
                    prodId = prod.getId();
                    break;
                }
            }
            
            String employee = "";
            String selEmp = "";
            int empId = 0;
            for(Funcionario emp : employees){
                if (emp.getId() == venda.getIdFuncionario()) {
                    employee = emp.getNome();
                    empId = emp.getId();
                    break;
                }
            }
            if(client == "") selCli = "Selecione o cliente"; else selCli = client;
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
                    Cadastro de Venda
                </h2>

                <div>
                    <form method="POST" action="VendaController" name="cad-venda" id="cad-venda">
                        <input type="hidden" class="form-control" name="id" value="<%= venda.getId() %>">

                        <div class="mb-4">
                            <label for="quantidade-venda" class="form-label">Quantidade:</label>
                            <input type="number" class="form-control" id="quantidade-venda" name="quantidade_venda" value="<%= qtdVenda %>" required size="30" placeholder="Quantidade" step="1"/>
                        </div>
                        <div class="mb-4">
                            <label for="data-venda" class="form-label">Data da venda:</label>
                            <input type="date" class="form-control" id="data-venda" name="data_venda" value="<%= dataVenda %>" required size="30" placeholder="Data da Venda" step="1"/>
                        </div>
                        <div class="mb-4">
                            <label for="valor-venda" class="form-label">Valor:</label>
                            <input type="number" class="dinheiro form-control " id="valor-venda" name="valor_venda" value="<%= valorVenda %>" required size="30" placeholder="Valor" step="0.01"/>
                        </div>
                        <div class="mb-4">
                            <label for="fornecedor-venda" class="form-label">Cliente:</label>
                            <select class="form-control" id="fornecedor-venda" name="id_cliente" required>
                                <option value="<%= cliId %>"><%= selCli %></option>
                            <%
                                String aux = "";
                                for (Cliente f : clients){
                                    aux = "<option value="+ f.getId() + ">" + f.getNome() + "</option>\n";
                                    out.println(aux);
                                }
                            %>        
                            </select>
                        </div>
                        <div class="mb-4">
                            <label for="produto-venda" class="form-label">Produto:</label>
                            <select class="form-control" id="produto-venda" name="id_produto" required>
                                <option value="<%= prodId %>"><%= selProd %></option>
                            <%
                                String aux2 = "";
                                for (Produto p : products){
                                    if (p.getLiberadoVenda().equalsIgnoreCase("S") && p.getQuantidadeDisponivel()>0){
                                        aux2 = "<option value="+ p.getId() + ">" + p.getNomeProduto() + "</option>\n";
                                        out.println(aux2);
                                    } 
                                }
                            %>        
                            </select>
                        </div>
                        <input type="hidden" class="form-control" name="id_funcionario" value="<%= usuario.getId() %>">
                        <div class="mb-4 center-horizontally">
                            <button type="submit" class="btn btn-dark" id="login-button">Salvar</button>
                        </div>

                        <a href="VendaController?option=get" class="btn btn-outline-danger">Voltar</a>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="../auxJSPs/BasicScripts.html" %>
    </body>
</html>
