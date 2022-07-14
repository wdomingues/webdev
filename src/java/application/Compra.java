/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.Date;

/**
 *
 * @author winne
 */
public class Compra {
    private long id;
    private double quantidadeCompra;
    private Date dataCompra;
    private double valorCompra;
    private long idFornecedor;
    private long idProduto;
    private long idFuncionario;

    public Compra() {}

    public Compra(long id, double quantidadeCompra, Date dataCompra, double valorCompra, long idFornecedor, long idProduto, long idFuncionario) {
        this.id = id;
        this.quantidadeCompra = quantidadeCompra;
        this.dataCompra = dataCompra;
        this.valorCompra = valorCompra;
        this.idFornecedor = idFornecedor;
        this.idProduto = idProduto;
        this.idFuncionario = idFuncionario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getQuantidadeCompra() {
        return quantidadeCompra;
    }

    public void setQuantidadeCompra(double quantidadeCompra) {
        this.quantidadeCompra = quantidadeCompra;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public long getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(long idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
}