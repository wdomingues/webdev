/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

/**
 *
 * @author winne
 */
public class Fornecedor implements IPessoa, IContato{
    private long id;
    private String nome; //Razão Social
    private String documento; //CNPJ
    private String endereco;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String telefone;
    private String email;
}
