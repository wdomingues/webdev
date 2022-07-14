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
public class Venda {
    private long id;
    private double quantidadeVenda;
    private Date dataVenda;
    private double valorVenda;
    private long idCliente;
    private long idProduto;
    private long idFuncionario;

    public Venda() {}

    public Venda(long id, long quantidadeVenda, Date dataVenda, double valorVenda, long idCliente, long idProduto, long idFuncionario) {
        this.id = id;
        this.quantidadeVenda = quantidadeVenda;
        this.dataVenda = dataVenda;
        this.valorVenda = valorVenda;
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.idFuncionario = idFuncionario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getQuantidadeVenda() {
        return quantidadeVenda;
    }

    public void setQuantidadeVenda(double quantidadeVenda) {
        this.quantidadeVenda = quantidadeVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
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