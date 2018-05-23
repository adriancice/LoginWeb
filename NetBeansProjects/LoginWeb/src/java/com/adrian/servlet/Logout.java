/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adrian.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

/**
 *
 * @author Adrian Stan
 */
public class Logout extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws SerialException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sesion = request.getSession(true);
        
        //Cerrar sesion
        sesion.invalidate();
        
        //Redirecciono a index.html
        response.sendRedirect("index.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (SerialException ex) {
            Logger.getLogger(Logout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try { 
            processRequest(request, response);
        } catch (SerialException ex) {
            Logger.getLogger(Logout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
