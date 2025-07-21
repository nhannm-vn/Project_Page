/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package nhannm.controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="DispatchServlet", urlPatterns={"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
    //default
    private final String LOGIN_PAGE = "login.html";
    //Dc URL cua LoginServlet
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String CHECK_ACCOUNT_CONTROLLER = "CheckAccountServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
    private final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountServlet"; 
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //1. Which button did user click?
        String button = request.getParameter("btAction");
        String url = LOGIN_PAGE;
        //set default cua minh la trang login
        //luc nay button bang null vi no k truyen parameter tuc la ko dau "?"
        //vi ben web.xml ngay cho welcome file chung ta k truyen tham so
        
        response.setContentType("text/html;charset=UTF-8");
        try {
            if(button == null){ //first request
               url = CHECK_ACCOUNT_CONTROLLER;
            } else{
                switch(button){
                    case "Login":{
                        url = LOGIN_CONTROLLER;
                        break;
                    }
                    case "Search":{
                        url = SEARCH_LASTNAME_CONTROLLER;
                        break;
                    }
                    case "Delete":{
                        url = DELETE_ACCOUNT_CONTROLLER;
                        break;
                    }
                    case "Update":{
                        url = UPDATE_ACCOUNT_CONTROLLER;
                        break;
                    }
                    case "Create New Account":{
                        url = CREATE_ACCOUNT_CONTROLLER;
                        break;
                    }
                    case "Logout":{
                        url = LOGOUT_CONTROLLER;
                        break;
                    }
                }
            }
        }
        finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

