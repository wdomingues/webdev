<%-- 
    Document   : FormFornecedor
    Created on : 14/07/2022, 18:56:11
    Author     : winne
--%>

<%@page import="application.Fornecedor, application.Funcionario, java.lang.String, java.lang.Integer, java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="pt-BR">
    <head>
        <%@include file="../auxJSPs/Header.html" %>
        <title>Cadastro de Fornecedor</title>
    </head>

    <body>
        <%
            ArrayList<Fornecedor> suppliers = (ArrayList<Fornecedor>) request.getAttribute("mySuppliers");

            Fornecedor supplier = (Fornecedor)request.getAttribute("supplier");
            int id = supplier.getId();
            String razaoSocial = supplier.getNome();
            String cnpj = supplier.getDocumento();
            String endereco = supplier.getEndereco();
            String bairro = supplier.getBairro();
            String cidade = supplier.getBairro();
            String uf = supplier.getUf();
            String cep = supplier.getCep();
            String telefone = supplier.getTelefone();
            String email = supplier.getEmail();
            
            String selUf = "";
            if(uf == "") selUf = "Selecione a UF"; else selUf = uf;

            Funcionario usuario = (Funcionario) request.getSession().getAttribute("usuario");
            String nomeUsuario = usuario.getNome();
            String papelUsr = usuario.getPapel();
            String papelExt = "";
            switch (papelUsr) {
                case Funcionario.Papeis.ADMINISTRADOR:
                    papelExt = "Administrador";
                    break;
                case Funcionario.Papeis.VENDEDOR:
                    papelExt = "Vendedor";
                    break;
                case Funcionario.Papeis.COMPRADOR:
                    papelExt = "Comprador";
                    break;
            }
        %>
        <%@include file="../auxJSPs/restricteds/NavBarSelector.jsp"%>
        <h3 style='text-align: right'>Usuário logado: <%=nomeUsuario%> - Papel: <%=papelExt%>.</h3>
        
        <div class="forms">
            <h2 class="m-5">
                Cadastro de Fornecedor
            </h2>

            <div>
                <form method="POST" action="FornecedorController" name="cad-fornecedor" id="cad-fornecedor">
                    <input type="hidden" class="form-control" name="id" value="<%= id %>">
                    
                    <div class="mb-4">
                        <label for="razao-social-fornecedor" class="form-label">Razão Social:</label>
                        <input type="text" class="form-control" id="razao-social-fornecedor" name="razao_social" value="<%= razaoSocial %>" required size="80" placeholder="The Popular Store Ltd."/>
                    </div>
                    <div class="mb-4">
                        <label for="cnpj-fornecedor" class="form-label">CNPJ:</label>
                        <input type="text" class="form-control" id="cnpj-fornecedor" name="cnpj" value="<%= cnpj %>" required size="20" placeholder="99.999.999/0001-00" data-mask="00.000.000/0000-00"/>
                    </div>
                    <div class="mb-4">
                        <label for="endereco-fornecedor" class="form-label">Endereço:</label>
                        <input type="text" class="form-control" id="endereco-fornecedor" name="endereco" value="<%= endereco %>" required size="80" placeholder="Av. dos Álamos, 451"/>
                    </div>
                    <div class="mb-4">
                        <label for="bairro-fornecedor" class="form-label">Bairro:</label>
                        <input type="text" class="form-control" id="bairro-fornecedor" name="bairro" value="<%= bairro %>" required size="30" placeholder="Jardim Botânico"/>
                    </div>
                    <div class="mb-4">
                        <label for="cidade-fornecedor" class="form-label">Cidade:</label>
                        <input type="text" class="form-control" id="cidade-fornecedor" name="cidade" value="<%= cidade %>" required size="30" placeholder="Rio de Janeiro"/>
                    </div>
                    <div class="mb-4">
                        <label for="uf-fornecedor" class="form-label">UF:</label>
                        <select class="form-control" id="uf-fornecedor" name="uf" required>
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
                        <label for="cep-fornecedor" class="form-label">CEP:</label>
                        <input type="text" class="form-control" id="cep-fornecedor" name="cep" value="<%= cep %>" required size="30" placeholder="99.999-999" data-mask="00.000-000"/>
                    </div>
                    <div class="mb-4">
                        <label for="telefone-fornecedor" class="form-label">Telefone:</label>
                        <input type="text" class="form-control" id="telefone-fornecedor" name="telefone" value="<%= telefone %>" required size="30" placeholder="(99) 999 999 999" data-mask="(99) 999 999 999"/>
                    </div>
                    <div class="mb-4">
                        <label for="email-fornecedor" class="form-label">e-mail:</label>
                        <input type="text" class="form-control" id="email-fornecedor" name="email" value="<%= email %>" required size="30" placeholder="abc@gmail.com"/>
                    </div>
                    <div class="mb-4 center-horizontally">
                        <button type="submit" class="btn btn-dark" id="login-button">Salvar</button>
                    </div>
                    
                    <a href="FornecedorController?option=get" class="btn btn-outline-danger">Voltar</a>
                </form>
            </div>
        </div>
        <%@include file="../auxJSPs/BasicScripts.html" %>
    </body>
</html>
