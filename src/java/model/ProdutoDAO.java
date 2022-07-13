/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import application.Produto;
import java.io.IOException;
import java.sql.Connection;
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
@WebServlet(name = "ProdutoDAO", urlPatterns = {"/ProdutoDAO"})
public class ProdutoDAO extends HttpServlet {

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
    private static final String TABLE = "produto";
    private static final String TABLEATTRIBUTES = 
        "id, nome_produto, descricao, preco_compra, , "
            + "quantidade_disponivel, liberado_venda, id_categoria";
            
    public ProdutoDAO() {
        try {
            connection = MyConnection.createConnection();
        }
        catch( SQLException e ) {
            System.out.println("Error while creating DAO connection");
            System.out.println(e);
        }
    }
    public ArrayList<Produto> getAll() {
        ArrayList<Produto> products = new ArrayList<>();
        try {            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + TABLE);
            while(rs.next()) {
                Produto product = new Produto();
                product.setId(rs.getLong("id"));
                product.setNomeProduto(rs.getString("nome_produto"));
                product.setDescricao(rs.getString("descricao"));
                product.setPrecoCompra(rs.getDouble("preco_compra"));
                product.setPrecoVenda(rs.getDouble("preco_venda"));
                product.setQuantidadeDisponivel(rs.getDouble("quantidade_disponivel"));
                product.setLiberadoVenda(rs.getString("liberado_venda"));
                product.setIdCategoria(rs.getLong("id_categoria"));

                products.add(product);
            }
        } catch( SQLException e ) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return products;
    }
    
    public Produto getById(long id) {
        Produto product = new Produto();
        try {
            String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                product.setId(rs.getLong("id"));
                product.setNomeProduto(rs.getString("nome_produto"));
                product.setDescricao(rs.getString("descricao"));
                product.setPrecoCompra(rs.getDouble("preco_compra"));
                product.setPrecoVenda(rs.getDouble("preco_venda"));
                product.setQuantidadeDisponivel(rs.getDouble("quantidade_disponivel"));
                product.setLiberadoVenda(rs.getString("liberado_venda"));
                product.setIdCategoria(rs.getLong("id_categoria"));
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return product;
    }
    
    public boolean put(Produto product) {
        try {
            String sql;
            List<String> attribList = Arrays.asList(TABLEATTRIBUTES.trim().split(","));
            attribList.forEach((p) -> { p = p+"=?"; });
            if (product.getId() == 0) {
                sql = "INSERT INTO " + TABLE + " (" +  TABLEATTRIBUTES + ") VALUES (?,?)";
            } else {
                sql = "UPDATE " + TABLE + " SET " + attribList.toString() + " WHERE id=?";
            }
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getNomeProduto());
            ps.setString(2, product.getDescricao());
            ps.setDouble(3, product.getPrecoCompra());
            ps.setDouble(4, product.getPrecoVenda());
            ps.setDouble(5, product.gerQuantidadeDisponivel());
            ps.setString(6, product.getLiberadoVenda());
            ps.setString(7, product.getIdCategoria());
           
            if (product.getId()> 0)
                ps.setLong(attribList.size(), product.getId());
            
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