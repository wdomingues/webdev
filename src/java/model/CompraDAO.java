/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import application.Compra;
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
@WebServlet(name = "CompraDAO", urlPatterns = {"/CompraDAO"})
public class CompraDAO extends HttpServlet {

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
    private static final String TABLE = "compras";
    private static final String TABLEATTRIBUTES = 
        "quantidade_compra, data_compra, valor_compra, "
            + "id_fornecedor, id_produto, id_funcionario";
            
    public CompraDAO() {
        try {
            connection = MyConnection.createConnection();
        }
        catch( SQLException e ) {
            System.out.println("Error while creating DAO connection");
            System.out.println(e);
        }
    }
    
    private int convertFloat2Int(float f){
        return Math.round (f * 100f);
    }
    private float convertInt2Float(int i){
        if (i == 0) return 0f;
        return i * 1f;
    }
    
    
    public ArrayList<Compra> getAll() {
        ArrayList<Compra> compras = new ArrayList<>();
        try {            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + TABLE);
            while(rs.next()) {
                Compra product = new Compra();
                product.setId(rs.getInt("id"));
                product.setQuantidadeCompra(rs.getInt("quantidade_compra"));
                product.setDataCompra(rs.getDate("data_compra"));
                product.setValorCompra(convertInt2Float(rs.getInt("valor_compra"))); //no BD está como int
                product.setIdFornecedor(rs.getInt("id_fornecedor"));
                product.setIdProduto(rs.getInt("id_produto"));
                product.setIdFuncionario(rs.getInt("id_funcionario"));

                compras.add(product);
            }
        } catch( SQLException e ) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return compras;
    }
    
    public Compra getById(int id) {
        Compra product = new Compra();
        try {
            String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setQuantidadeCompra(rs.getInt("quantidade_compra"));
                product.setDataCompra(rs.getDate("data_compra"));
                product.setValorCompra(convertInt2Float(rs.getInt("valor_compra"))); //no BD está como int
                product.setIdFornecedor(rs.getInt("id_fornecedor"));
                product.setIdProduto(rs.getInt("id_produto"));
                product.setIdFuncionario(rs.getInt("id_funcionario"));
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return product;
    }
    
    public boolean put(Compra product) {
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
            ps.setInt(1, product.getQuantidadeCompra());
            ps.setDate(2, new Date(product.getDataCompra().getTime()));
            ps.setInt(3, convertFloat2Int(product.getValorCompra())); //no BD está como int: gravando *100
            ps.setInt(4, product.getIdFornecedor());
            ps.setInt(5, product.getIdProduto());
            ps.setInt(6, product.getIdFuncionario());
           
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
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
}