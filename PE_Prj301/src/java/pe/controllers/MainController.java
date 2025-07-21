/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
@WebServlet(name="MainController", urlPatterns={"/MainController"})
public class MainController extends HttpServlet {
    private static final String WELCOME="login.jsp";

    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_CONTROLLER = "SearchServlet";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    private final String UPDATE_CONTROLLER = "UpdateController";
    private final String DELETE_CONTROLLER = "DeleteServlet";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url= WELCOME;
        try {
            String action= request.getParameter("action");
            //-----            your code here   --------------------------------
            if(action == null){// first request
                
            }else{
                switch (action) {
                    case "Login":
                        url = LOGIN_CONTROLLER;
                        break;
                    case "Logout":{
                        url = LOGOUT_CONTROLLER;
                        break;
                    }
                    case "Search":{
                        url = SEARCH_CONTROLLER;
                        break;
                    }
                    case "Update":{
                        url = UPDATE_CONTROLLER;
                        break;
                    }
                    case "Delete": {
                        url = DELETE_CONTROLLER;
                        break;
                    }
                    default:
                        throw new AssertionError();
                }
            }
            //-----            your code here   --------------------------------
        } catch (Exception e) {
            log("error at MainController: "+ e.toString());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
