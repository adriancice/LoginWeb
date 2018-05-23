/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adrian.beans;
import java.sql.*;

/**
 *
 * @author Adrian Stan
 */
public class Dao {
    public Connection conexion;
    public final static String userDb = "root";
    public final static String passDb = "root";
    
    
    //Conectar a la Base de datos
    public void conectar() throws SQLException,ClassNotFoundException{
         Class.forName("com.mysql.jdbc.Driver");
         conexion=DriverManager.getConnection("jdbc:mysql://localhost:8889/login",userDb, passDb);
    }
    //Desconectar a la Base de datos
    public void desconectar() throws SQLException, ClassNotFoundException{
        conexion.close();
    }
    
    //Metodo para consultar si un email y contraseñan pertenecen a una cuenta registrada
    public boolean isAcountExists(String nombre_usuario) throws SQLException{
        String sql = "SELECT * FROM users WHERE nombre_usuario='"+nombre_usuario+"'";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        return rs.next();
    }
    
    //Metodo para consultar si el email recibido ya esta registrado
    public boolean isEmailRegistered(String email) throws SQLException{
        String sql = "SELECT * FROM users WHERE email='"+email+"'";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
 
        return rs.next();
    }
    
    //Metodo para registrar una cuenta
    public void registerUser(String nombre, String apellido, String nombre_usuario, String password, String email) throws SQLException{
        String sql = "INSERT INTO `users`(`nombre`,`apellido`,`nombre_usuario`,`password`,`email` ) VALUES ('"+nombre+"','"+apellido+"','"+nombre_usuario+"','"+password+"','"+email+"')";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.executeUpdate();
    }
    
}
