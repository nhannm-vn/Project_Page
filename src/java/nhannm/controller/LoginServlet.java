/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package nhannm.controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import nhannm.registration.RegistrationDAO;

/**
 *
 * @author ADMIN
 */
public class LoginServlet extends HttpServlet {

    //_Tao hai hang so de chuyen page
    private final String SEARCH_PAGE = "search.html";
    private final String INVALID_PAGE = "invalid.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //_Neu khong dem ra ngoai thi bi bug
        PrintWriter out = response.getWriter();
        //1. get all users information
        String url = INVALID_PAGE;

        //_Vi minh da chot nut nao ben dieu phoi roi nen se khong lay ra nua
        //String button = request.getParameter("btAction");
        
        //_Lay hai parameter ra tu form
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");

        try {

            //2. controller calls method Model
            //2.1 new Dao object
            RegistrationDAO dao = new RegistrationDAO();
            //2.2 call method of Dao object
            boolean result = dao.checkLogin(username, password);
            //3. process
            if (result) {
                url = SEARCH_PAGE;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            //_Thang nay se lam minh bi show duong truyen
            //response.sendRedirect(url);
            //fix:
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            //Luc nay cai contextpath sau cung se duoc an di ma se hien anh dieu phoi
            out.close();
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
