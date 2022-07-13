/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import application.Fornecedor;
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
@WebServlet(name = "FornecedorDAO", urlPatterns = {"/FornecedorDAO"})
public class FornecedorDAO extends HttpServlet {

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
    private static final String TABLE = "fornecedor";
    private static final String TABLEATTRIBUTES = 
        "id, razao_social, cnpj, endereco, bairro, cidade, uf, cep, telefone, email";
            
    public FornecedorDAO() {
        try {
            connection = MyConnection.createConnection();
        }
        catch( SQLException e ) {
            System.out.println("Error while creating DAO connection");
            System.out.println(e);
        }
    }
    public ArrayList<Fornecedor> getAll() {
        ArrayList<Fornecedor> suppliers = new ArrayList<>();
        try {            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + TABLE);
            while(rs.next()) {
                Fornecedor supplier = new Fornecedor();
                supplier.setId(rs.getLong("id"));
                supplier.setNome(rs.getString("razao_social"));
                supplier.setDocumento(rs.getString("cnpj"));
                supplier.setEndereco(rs.getString("endereco"));
                supplier.setBairro(rs.getString("bairro"));
                supplier.setCidade(rs.getString("cidade"));
                supplier.setUf(rs.getString("uf"));
                supplier.setCep(rs.getString("cep"));
                supplier.setTelefone(rs.getString("telefone"));
                supplier.setEmail(rs.getString("email"));

                suppliers.add(supplier);
            }
        } catch( SQLException e ) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return suppliers;
    }
    
    public Fornecedor getById(long id) {
        Fornecedor supplier = new Fornecedor();
        try {
            String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                supplier.setId(rs.getLong("id"));
                supplier.setNome(rs.getString("razaoSocial"));
                supplier.setDocumento(rs.getString("cnpj"));
                supplier.setEndereco(rs.getString("endereco"));
                supplier.setBairro(rs.getString("bairro"));
                supplier.setCidade(rs.getString("cidade"));
                supplier.setUf(rs.getString("uf"));
                supplier.setCep(rs.getString("cep"));
                supplier.setTelefone(rs.getString("telefone"));
                supplier.setEmail(rs.getString("email"));
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return supplier;
    }
    
    public boolean put(Fornecedor supplier) {
        try {
            String sql;
            List<String> attribList = Arrays.asList(TABLEATTRIBUTES.trim().split(","));
            attribList.forEach((p) -> { p = p+"=?"; });
            if (supplier.getId() == 0) {
                sql = "INSERT INTO " + TABLE + " (" +  TABLEATTRIBUTES + ") VALUES (?,?)";
            } else {
                sql = "UPDATE " + TABLE + " SET " + attribList.toString() + " WHERE id=?";
            }
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, supplier.getNome());
            ps.setString(2, supplier.getDocumento());
            ps.setString(3, supplier.getEndereco());
            ps.setString(4, supplier.getBairro());
            ps.setString(5, supplier.getCidade());
            ps.setString(6, supplier.getUf());
            ps.setString(7, supplier.getCep());
            ps.setString(8, supplier.getTelefone());
            ps.setString(9, supplier.getEmail());
           
            if (supplier.getId()> 0)
                ps.setLong(attribList.size(), supplier.getId());
            
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