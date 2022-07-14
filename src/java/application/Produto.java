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
    private long id;
    private String nomeProduto;
    private String descricao;
    private double precoCompra;
    private double precoVenda;
    private double qtdDisponivel;
    private String liberadoVenda;
    private long idCategoria;

    public Produto() {
    }

    public Produto(long id, String nomeProduto, String descricao, double precoCompra, 
            double precoVenda, double qtdDisponivel, String liberadoVenda, long idCategoria) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.qtdDisponivel = qtdDisponivel;
        this.liberadoVenda = liberadoVenda;
        this.idCategoria = idCategoria;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public double getQtdDisponivel() {
        return qtdDisponivel;
    }

    public void setQtdDisponivel(double qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }

    public String getLiberadoVenda() {
        return liberadoVenda;
    }

    public void setLiberadoVenda(String liberadoVenda) {
        this.liberadoVenda = liberadoVenda;
    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }
}