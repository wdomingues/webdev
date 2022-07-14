/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import application.Funcionario;
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
@WebServlet(name = "FuncionarioDAO", urlPatterns = {"/FuncionarioDAO"})
public class FuncionarioDAO extends HttpServlet {

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
    private static final String TABLE = "funcionario";
    private static final String TABLEATTRIBUTES = 
        "id, nome, senha, papel";
            
    public FuncionarioDAO() {
        try {
            connection = MyConnection.createConnection();
        }
        catch(SQLException e) {
            System.out.println("Error while creating DAO connection");
            System.out.println(e);
        }
    }
    public ArrayList<Funcionario> getAll() {
        ArrayList<Funcionario> employees = new ArrayList<>();
        try {            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + TABLE);
            while(rs.next()) {
                Funcionario employee = new Funcionario();
                employee.setId(rs.getInt("id"));
                employee.setNome(rs.getString("nome"));
                employee.setDocumento(rs.getString("cpf"));
                employee.setSenha(rs.getString("senha"));
                employee.setPapel(rs.getString("papel"));

                employees.add(employee);
            }
        } catch( SQLException e ) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return employees;
    }
    
    public Funcionario getById(int id) {
        Funcionario employee = new Funcionario();
        try {
            String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                employee.setId(rs.getInt("id"));
                employee.setNome(rs.getString("nome"));
                employee.setDocumento(rs.getString("cpf"));
                employee.setSenha(rs.getString("senha"));
                employee.setPapel(rs.getString("papel"));
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return employee;
    }
    
    public boolean put(Funcionario employee) {
        try {
            String sql;
            List<String> attribList = Arrays.asList(TABLEATTRIBUTES.trim().split(","));
            attribList.forEach((p) -> { p = p+"=?"; });
            if (employee.getId() == 0) {
                sql = "INSERT INTO " + TABLE + " (" +  TABLEATTRIBUTES + ") VALUES (?,?)";
            } else {
                sql = "UPDATE " + TABLE + " SET " + attribList.toString() + " WHERE id=?";
            }
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, employee.getNome());
            ps.setString(2, employee.getDocumento());
            ps.setString(3, employee.getSenha());
            ps.setString(4, employee.getPapel());
           
            if (employee.getId()> 0)
                ps.setInt(attribList.size(), employee.getId());
            
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