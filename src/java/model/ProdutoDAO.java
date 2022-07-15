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
    private static final String TABLE = "produtos";
    private static final String TABLEATTRIBUTES = 
        "nome_produto, descricao, preco_compra, preco_venda, "
            + "quantidade_disponível, liberado_venda, id_categoria";
            
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
                product.setId(rs.getInt("id"));
                product.setNomeProduto(rs.getString("nome_produto"));
                product.setDescricao(rs.getString("descricao"));
                product.setPrecoCompra(rs.getFloat("preco_compra"));
                product.setPrecoVenda(rs.getFloat("preco_venda"));
                product.setQuantidadeDisponivel(rs.getInt("quantidade_disponível"));
                product.setLiberadoVenda(rs.getString("liberado_venda"));
                product.setIdCategoria(rs.getInt("id_categoria"));

                products.add(product);
            }
        } catch( SQLException e ) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return products;
    }
    
    public Produto getById(int id) {
        Produto product = new Produto();
        try {
            String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setNomeProduto(rs.getString("nome_produto"));
                product.setDescricao(rs.getString("descricao"));
                product.setPrecoCompra(rs.getFloat("preco_compra"));
                product.setPrecoVenda(rs.getFloat("preco_venda"));
                product.setQuantidadeDisponivel(rs.getInt("quantidade_disponível"));
                product.setLiberadoVenda(rs.getString("liberado_venda"));
                product.setIdCategoria(rs.getInt("id_categoria"));
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
            
            String parameters = "", paramVars = "" ;
            for (String attrib : attribList){
                parameters += attrib+"=?, ";
                paramVars += "?,";
            }
            parameters = parameters.substring(0, parameters.length()-2);
            paramVars = "(" + paramVars.substring(0, paramVars.length()-1) + ")";
                
            if (product.getId() == 0) {
                sql = "INSERT INTO " + TABLE + " (" +  TABLEATTRIBUTES + ") VALUES" + paramVars;
            } else {
                sql = "UPDATE " + TABLE + " SET " + parameters + " WHERE id=?";
            }
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getNomeProduto());
            ps.setString(2, product.getDescricao());
            ps.setFloat(3, product.getPrecoCompra());
            ps.setFloat(4, product.getPrecoVenda());
            ps.setInt(5, product.getQuantidadeDisponivel());
            ps.setString(6, product.getLiberadoVenda().substring(0,1)); //get just the first char
            ps.setInt(7, product.getIdCategoria());
           
            if (product.getId()> 0)
                ps.setInt(attribList.size()+1, product.getId());
            
            ps.execute();
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean delete(int id) {
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