/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;


/**
 *
 * @author winne
 */
@WebServlet(name = "MyConnection", urlPatterns = {"/MyConnection"})
public class MyConnection extends HttpServlet {
private static Connection connection = null;

//DataBase Credentials
private static final String db = "jdbc:mysql://localhost:3306/estoque";
private static final String user = "root";
private static final String pass = "";

    public static Connection createConnection() throws SQLException {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver"); //load driver                       
                System.out.println("Driver has been loaded successfully!");
                connection = DriverManager.getConnection(db, user, pass);
                System.out.println("Connection has been started successfully!");
            }
            catch( ClassNotFoundException e ) {
                System.out.println("Driver not found!");
            }
        }
        return connection;
    }
}
