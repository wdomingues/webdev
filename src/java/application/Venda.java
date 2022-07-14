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
    private int id;
    private int quantidadeVenda;
    private Date dataVenda;
    private float valorVenda;
    private int idCliente;
    private int idProduto;
    private int idFuncionario;

    public Venda() {}

    public Venda(int id, int quantidadeVenda, Date dataVenda, float valorVenda, int idCliente, int idProduto, int idFuncionario) {
        this.id = id;
        this.quantidadeVenda = quantidadeVenda;
        this.dataVenda = dataVenda;
        this.valorVenda = valorVenda;
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.idFuncionario = idFuncionario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidadeVenda() {
        return quantidadeVenda;
    }

    public void setQuantidadeVenda(int quantidadeVenda) {
        this.quantidadeVenda = quantidadeVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
}