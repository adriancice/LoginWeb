/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adrian.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Adrian Stan
 */
public class Login extends HttpServlet {              
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        String userName = request.getParameter("inputUserName");
        String password = request.getParameter("inputPassword");        
        // conectando con mysql y verificando user        
        try {
            //carga de driver
            Class.forName("com.mysql.jdbc.Driver");
            //creamos una conexion
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/login", "root", "root");
            
            PreparedStatement ps = conn.prepareStatement("SELECT nombre_usuario,password FROM users WHERE nombre_usuario=? and password=?");
            ps.setString(1, userName);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                response.sendRedirect("SuccessLogin.html");
                return;                
            }
            response.sendRedirect("ErrorLogin.html");
            return;
       }catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        
        
    }

}
