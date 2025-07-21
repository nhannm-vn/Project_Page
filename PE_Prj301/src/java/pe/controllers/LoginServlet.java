/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package pe.controllers;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import pe.model.TblUserDAO;
import pe.model.TblUserDTO;

/**
 *
 * @author ADMIN
 */
public class LoginServlet extends HttpServlet {
    private final String SEARCH_PAGE = "search.jsp";
    private final String ERROR_PAGE = "error.html";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Neu khong dem ra ngoai thi no se bi bug
        PrintWriter out = response.getWriter();
        String url = ERROR_PAGE;
        //1. get all user's info
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        try {
            //2. controller call method of model
            //2.1 New DAO object
            TblUserDAO tblUser = new TblUserDAO();
            //2.2 call method of DAO object
            TblUserDTO result = tblUser.checkLogin(username, Integer.parseInt(password));
            //3. process
            if(result != null){
                HttpSession session = request.getSession();
                session.setAttribute("USER_INFO", result);
                url = SEARCH_PAGE;
            }
        }catch (SQLException ex) {
            log("SQL" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("Class Not Found" + ex.getMessage());
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
