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
public class Produto {
    private int id; 
    private String nomeProduto; //100 caracteres
    private String descricao; //255 caracteres
    private float precoCompra; //10,2 caracteres
    private float precoVenda; //10,2 caracteres
    private int quantidadeDisponivel; 
    private String liberadoVenda; //1 caracter
    private int idCategoria; 

    public Produto() {
    }

    public Produto(int id, String nomeProduto, String descricao, float precoCompra, 
            float precoVenda, int quantidadeDisponivel, String liberadoVenda, int idCategoria) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.liberadoVenda = liberadoVenda;
        this.idCategoria = idCategoria;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(float precoCompra) {
        this.precoCompra = precoCompra;
    }

    public float getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int qtdDisponivel) {
        this.quantidadeDisponivel = qtdDisponivel;
    }

    public String getLiberadoVenda() {
        return liberadoVenda;
    }

    public void setLiberadoVenda(String liberadoVenda) {
        this.liberadoVenda = liberadoVenda;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}