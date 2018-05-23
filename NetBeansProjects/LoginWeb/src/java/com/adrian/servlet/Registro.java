/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adrian.servlet;


import com.adrian.beans.Dao;
import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Adrian Stan
 */
public class Registro extends HttpServlet {
    

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        
        
        HttpSession respuesta = request.getSession(true);
        PrintWriter out = response.getWriter();
        //Declaro e inicio las variables
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("correo");
        String nombre_usuario = request.getParameter("nombreUsuario");
        String password = request.getParameter("password");
        String repeatPass = request.getParameter("repeatPassword");
        Dao d = new Dao();
        
        //verificamos si las contraseñas son iguales
        if(password.equals(repeatPass)){
            try {
                d.conectar();
                if (d.isAcountExists(nombre_usuario)) {
                    respuesta.setAttribute("error", "Este usuario ya esta registrado!");
                }else{
                    d.registerUser(nombre, apellido, nombre_usuario, password, email);
                    respuesta.setAttribute("error", null);
                }
                d.desconectar();
            } catch (Exception e) {
                out.println("Ocurrio el siguiente error: " + e);
            }
        }else {
            respuesta.setAttribute("error", "Las contraseñas no son iguales");
            //response.sendRedirect("CrearCuenta.html");
        }
        
        //response.sendRedirect("RegistroExitoso.html");
        
        
        
        /*
        Esto era otra manera de insertar los usuarios
        
        response.setContentType("text/html");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("correo");
        String nombre_usuario = request.getParameter("nombreUsuario");
        String password = request.getParameter("password");
        String repeatPass = request.getParameter("repeatPassword");
        
        
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("el driver se ha cargado");

                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/login", "root", "root");
                System.out.println("La conexion se ha creado");
                
                String condicion = ("SELECT * FROM users WHERE nombre_usuario='nombre_usuario'");

                    String sql = "INSERT INTO users VALUES(?,?,?,?,?,?)";
                System.out.println("el query sql se ha creado");

                PreparedStatement ps = conn.prepareStatement(sql); //creamos el query sql

                ps.setString(1, null);
                ps.setString(2, nombre);
                ps.setString(3, apellido);
                ps.setString(4, nombre_usuario);
                ps.setString(5, password);
                ps.setString(6, email);
                
                /**
                 * para los numeros, hay que parsearlos --> ps.setInt(5,
                 * Integer.parseInt(age)); *
                 */
            /*
                ps.executeUpdate(); //ejecutamos sobre la base de datos
                System.out.println("Registro exitoso");
                ps.close();
                conn.close();
                
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            RequestDispatcher rd = request.getRequestDispatcher("RegistroExitoso.html");
            rd.forward(request, response);
            */
        
    }  
        
}      
