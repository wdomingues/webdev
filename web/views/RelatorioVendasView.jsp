<%-- 
    Document   : ListVendasView
    Created on : 14/07/2022, 16:37:38
    Author     : winne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,application.Venda, application.Cliente, 
         application.Produto, application.Funcionario, utils.Validators" %>
, application.Funcionario" %>
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

                   
            <h1>Relatório de Vendas</h1>     
            <p></p>
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
                        </tr>
                    </thead> 
                    <tbody>
                        <%
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
                                
                        %>
                        <tr>
                            <td><%=venda.getId()%></td>
                            <td><%=venda.getQuantidadeVenda()%></td>
                            <td><%=Validators.convertDate2String(venda.getDataVenda())%></td>
                            <td><%=Validators.valorViewFormatter(venda.getValorVenda())%></td>
                            <td><%=client%></td>
                            <td><%=product%></td>
                            <td><%=employee%></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <br>
            <br>
            <h1>Relatório de Vendas Totais Diárias</h1>     
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Data da Venda</th>
                            <th scope="col"><div class="float-right">Total Diário:</div></th>
                        </tr>
                    </thead> 
                    <tbody>
                        <%
                            Map<Date,Float> map = new HashMap<>();
                            for (Venda v : vendas) {
                                Float vl = 0f;
                                Date dt = (Date)v.getDataVenda();
                                float vlAcc = (float) v.getValorVenda();
                                
                                if(map.containsKey(dt)){
                                    vl = (float)map.get(dt);
                                    map.put(dt, vlAcc+vl);
                                } else {
                                    map.put(dt, vlAcc);
                                }
                            }
                            for (Date key : map.keySet()) {
                                float value = map.get(key);
                        %>
                        <tr>
                            
                            <td><%=Validators.convertDate2String(key)%></td>
                            <td><%=Validators.valorViewFormatter(value)%></td>
                            <td></td> 
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
