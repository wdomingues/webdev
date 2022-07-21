<%-- 
    Document   : FormCliente
    Created on : 14/07/2022, 19:10:13
    Author     : winne
--%>

<%@page import="application.Cliente, application.Funcionario, java.lang.String, java.lang.Integer, java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="pt-BR">
    <head>
        <%@include file="../auxJSPs/Header.html" %>
        <title>Cadastro de Cliente</title>
    </head>

    <body>
        <%
            ArrayList<Cliente> clients = (ArrayList<Cliente>) request.getAttribute("myClients");

            Cliente client = (Cliente)request.getAttribute("client");
            int id = client.getId();
            String nome = client.getNome();
            String cpf = client.getDocumento();
            String endereco = client.getEndereco();
            String bairro = client.getBairro();
            String cidade = client.getBairro();
            String uf = client.getUf();
            String cep = client.getCep();
            String telefone = client.getTelefone();
            String email = client.getEmail();
            
            String selUf = "";
            if(uf == "") selUf = "Selecione a UF"; else selUf = uf;

            Funcionario usuario = (Funcionario) request.getSession().getAttribute("usuario");
            String nomeUsuario = usuario.getNome();
            
        %>
        <%@include file="../auxJSPs/restricteds/NavBarSelector.jsp"%>
            <%=nomeUsuario%>
        <%=".html" %>
        
        <div class="forms">
            <h2 class="m-5">
                Cadastro de Cliente
            </h2>

            <div>
                <form method="POST" action="ClienteController" name="cad-client" id="cad-cliente">
                    <input type="hidden" class="form-control" name="id" value="<%= id %>">
                    
                    <div class="mb-4">
                        <label for="nome-cliente" class="form-label">Nome:</label>
                        <input type="text" class="form-control" id="nome-cliente" name="nome" value="<%= nome %>" required size="80" placeholder="José da Silva"/>
                    </div>
                    <div class="mb-4">
                        <label for="cpf-cliente" class="form-label">CPF:</label>
                        <input type="text" class="form-control" id="cpf-cliente" name="cpf" value="<%= cpf %>" required size="14" placeholder="999.999.999-00" data-mask="000.000.000-00"/>
                    </div>
                    <div class="mb-4">
                        <label for="endereco-cliente" class="form-label">Endereço:</label>
                        <input type="text" class="form-control" id="endereco-cliente" name="endereco" value="<%= endereco %>" required size="30" placeholder="Av. dos Álamos, 451"/>
                    </div>
                    <div class="mb-4">
                        <label for="bairro-cliente" class="form-label">Bairro:</label>
                        <input type="text" class="form-control" id="bairro-cliente" name="bairro" value="<%= bairro %>" required size="80" placeholder="Jardim Botânico"/>
                    </div>
                    <div class="mb-4">
                        <label for="cidade-cliente" class="form-label">Cidade:</label>
                        <input type="text" class="form-control" id="cidade-cliente" name="cidade" value="<%= cidade %>" required size="30" placeholder="Rio de Janeiro"/>
                    </div>
                    <div class="mb-4">
                        <label for="uf-cliente" class="form-label">UF:</label>
                        <select class="form-control" id="uf-cliente" name="uf" required>
                            <option value="<%= selUf %>"><%= selUf %></option>
                            <option value="AC">AC</option>
                            <option value="AL">AL</option>
                            <option value="AP">AP</option>
                            <option value="AM">AM</option>
                            <option value="BA">BA</option>
                            <option value="CE">CE</option>
                            <option value="DF">DF</option>
                            <option value="ES">ES</option>
                            <option value="GO">GO</option>
                            <option value="MA">MA</option>
                            <option value="MS">MS</option>
                            <option value="MT">MT</option>
                            <option value="MG">MG</option>
                            <option value="PA">PA</option>
                            <option value="PB">PB</option>
                            <option value="PR">PR</option>
                            <option value="PE">PE</option>
                            <option value="PI">PI</option>
                            <option value="RJ">RJ</option>
                            <option value="RN">RN</option>
                            <option value="RS">RS</option>
                            <option value="RO">RO</option>
                            <option value="RR">RR</option>
                            <option value="SC">SC</option>
                            <option value="SP">SP</option>
                            <option value="SE">SE</option>
                            <option value="TO">TO</option>
                        </select>
                    </div>
                    <div class="mb-4">
                        <label for="cep-cliente" class="form-label">CEP:</label>
                        <input type="text" class="form-control" id="cep-cliente" name="cep" value="<%= cep %>" required size="30" placeholder="99.999-999" data-mask="00.000-000"/>
                    </div>
                    <div class="mb-4">
                        <label for="telefone-cliente" class="form-label">Telefone:</label>
                        <input type="text" class="form-control" id="telefone-cliente" name="telefone" value="<%= telefone %>" required size="30" placeholder="(99) 999 999 999" data-mask="(00) 000 000 000"/>
                    </div>
                    <div class="mb-4">
                        <label for="email-cliente" class="form-label">e-mail:</label>
                        <input type="text" class="form-control" id="email-cliente" name="email" value="<%= email %>" required size="30" placeholder="abc@gmail.com"/>
                    </div>
                    <div class="mb-4 center-horizontally">
                        <button type="submit" class="btn btn-dark" id="login-button">Salvar</button>
                    </div>
                    
                    <a href="ClienteController?option=get" class="btn btn-outline-danger">Voltar</a>
                </form>
            </div>
        </div>
        <%@include file="../auxJSPs/BasicScripts.html" %>
    </body>
</html>
