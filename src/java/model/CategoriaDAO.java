/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import application.Categoria;
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
@WebServlet(name = "CategoriaDAO", urlPatterns = {"/CategoriaDAO"})
public class CategoriaDAO extends HttpServlet {

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
    private static final String TABLE = "categoria";
    private static final String TABLEATTRIBUTES = 
        "id, categoria";
            
    public CategoriaDAO() {
        try {
            connection = MyConnection.createConnection();
        }
        catch( SQLException e ) {
            System.out.println("Error while creating DAO connection");
            System.out.println(e);
        }
    }
    public ArrayList<Categoria> getAll() {
        ArrayList<Categoria> categoryes = new ArrayList<>();
        try {            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + TABLE);
            while(rs.next()) {
                Categoria category = new Categoria();
                category.setId(rs.getLong("id"));
                category.setNomeCategoria(rs.getString("nome_categoria"));

                categoryes.add(category);
            }
        } catch( SQLException e ) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return categoryes;
    }
    
    public Categoria getById(long id) {
        Categoria category = new Categoria();
        try {
            String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                category.setId(rs.getLong("id"));
                category.setNomeCategoria(rs.getString("nome_categoria"));

            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return category;
    }
    
    public boolean put(Categoria category) {
        try {
            String sql;
            List<String> attribList = Arrays.asList(TABLEATTRIBUTES.trim().split(","));
            attribList.forEach((p) -> { p = p+"=?"; });
            if (category.getId() == 0) {
                sql = "INSERT INTO " + TABLE + " (" +  TABLEATTRIBUTES + ") VALUES (?,?)";
            } else {
                sql = "UPDATE " + TABLE + " SET " + attribList.toString() + " WHERE id=?";
            }
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, category.getNomeCategoria());
           
            if (category.getId()> 0)
                ps.setLong(attribList.size(), category.getId());
            
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