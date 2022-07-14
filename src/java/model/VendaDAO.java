/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import application.Venda;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author winne
 */
@WebServlet(name = "VendaDAO", urlPatterns = {"/VendaDAO"})
public class VendaDAO extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private Connection connection;
    private static final String TABLE = "venda";
    private static final String TABLEATTRIBUTES = 
        "id, quantidade_venda, data_venda, valor_venda, "
            + "id_cliente, id_produto, id_funcionario";
            
    public VendaDAO() {
        try {
            connection = MyConnection.createConnection();
        }
        catch( SQLException e ) {
            System.out.println("Error while creating DAO connection");
            System.out.println(e);
        }
    }
    public ArrayList<Venda> getAll() {
        ArrayList<Venda> vendas = new ArrayList<>();
        try {            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + TABLE);
            while(rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getLong("id"));
                venda.setQuantidadeVenda(rs.getDouble("quantidade_venda"));
                venda.setDataVenda(rs.getDate("data_venda"));
                venda.setValorVenda(rs.getDouble("valor_venda"));
                venda.setIdCliente(rs.getLong("id_cliente"));
                venda.setIdProduto(rs.getLong("id_produto"));
                venda.setIdFuncionario(rs.getLong("id_funcionario"));

                vendas.add(venda);
            }
        } catch( SQLException e ) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return vendas;
    }
    
    public Venda getById(long id) {
        Venda venda = new Venda();
        try {
            String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                venda.setId(rs.getLong("id"));
                venda.setQuantidadeVenda(rs.getDouble("quantidade_venda"));
                venda.setDataVenda(rs.getDate("data_venda"));
                venda.setValorVenda(rs.getDouble("valor_venda"));
                venda.setIdCliente(rs.getLong("id_cliente"));
                venda.setIdProduto(rs.getLong("id_produto"));
                venda.setIdFuncionario(rs.getLong("id_funcionario"));
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return venda;
    }
    
    public boolean put(Venda venda) {
        try {
            String sql;
            List<String> attribList = Arrays.asList(TABLEATTRIBUTES.trim().split(","));
            attribList.forEach((p) -> { p = p+"=?"; });
            if (venda.getId() == 0) {
                sql = "INSERT INTO " + TABLE + " (" +  TABLEATTRIBUTES + ") VALUES (?,?)";
            } else {
                sql = "UPDATE " + TABLE + " SET " + attribList.toString() + " WHERE id=?";
            }
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, venda.getQuantidadeVenda());
            ps.setDate(2, new Date(venda.getDataVenda().getTime()));
            ps.setDouble(3, venda.getValorVenda());
            ps.setLong(4, venda.getIdCliente());
            ps.setLong(5, venda.getIdProduto());
            ps.setLong(6, venda.getIdFuncionario());
           
            if (venda.getId()> 0)
                ps.setLong(attribList.size(), venda.getId());
            
            ps.execute();
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean delete(long id) {
        try {
            String sql = "DELETE FROM " + TABLE + " WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.execute();
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    
}