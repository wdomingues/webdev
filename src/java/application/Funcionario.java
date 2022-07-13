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
public class Funcionario implements IPessoa {
    private long id;
    private String nome; //nome
    private String documento; //cpf
    private String senha;
    private String papel;

    public Funcionario(){}

    public Funcionario(long id, String nome, String documento, String senha, String papel) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.senha = senha;
        this.papel = papel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
}
