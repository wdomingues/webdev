/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import application.Cliente;
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
@WebServlet(name = "ClienteDAO", urlPatterns = {"/ClienteDAO"})
public class ClienteDAO extends HttpServlet {

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
    private static final String TABLE = "clientes";
    private static final String TABLEATTRIBUTES = 
        "nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email";
            
    public ClienteDAO() {
        try {
            connection = MyConnection.createConnection();
        }
        catch( SQLException e ) {
            System.out.println("Error while creating DAO connection");
            System.out.println(e);
        }
    }
    public ArrayList<Cliente> getAll() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + TABLE);
            while(rs.next()) {
                Cliente client = new Cliente();
                client.setId(rs.getInt("id"));
                client.setNome(rs.getString("nome"));
                client.setDocumento(rs.getString("cpf"));
                client.setEndereco(rs.getString("endereco"));
                client.setBairro(rs.getString("bairro"));
                client.setCidade(rs.getString("cidade"));
                client.setUf(rs.getString("uf"));
                client.setCep(rs.getString("cep"));
                client.setTelefone(rs.getString("telefone"));
                client.setEmail(rs.getString("email"));

                clientes.add(client);
            }
        } catch( SQLException e ) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return clientes;
    }
    
    public Cliente getById(int id) {
        Cliente client = new Cliente();
        try {
            String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                client.setId(rs.getInt("id"));
                client.setNome(rs.getString("nome"));
                client.setDocumento(rs.getString("cpf"));
                client.setEndereco(rs.getString("endereco"));
                client.setBairro(rs.getString("bairro"));
                client.setCidade(rs.getString("cidade"));
                client.setUf(rs.getString("uf"));
                client.setCep(rs.getString("cep"));
                client.setTelefone(rs.getString("telefone"));
                client.setEmail(rs.getString("email"));
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return client;
    }
    
    public boolean put(Cliente client) {
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
                
            if (client.getId() == 0) {
                sql = "INSERT INTO " + TABLE + " (" +  TABLEATTRIBUTES + ") VALUES" + paramVars;
            } else {
                sql = "UPDATE " + TABLE + " SET " + parameters + " WHERE id=?";
            }
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, client.getNome());
            ps.setString(2, client.getDocumento());
            ps.setString(3, client.getEndereco());
            ps.setString(4, client.getBairro());
            ps.setString(5, client.getCidade());
            ps.setString(6, client.getUf());
            ps.setString(7, client.getCep());
            ps.setString(8, client.getTelefone());
            ps.setString(9, client.getEmail());
           
            if (client.getId()> 0)
                ps.setInt(attribList.size()+1, client.getId());
            
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