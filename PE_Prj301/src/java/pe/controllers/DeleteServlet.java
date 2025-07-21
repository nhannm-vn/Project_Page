/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import pe.model.TblWatchDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DeleteServlet", urlPatterns = {"/DeleteServlet"})
public class DeleteServlet extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("txtPK");
        String searchMinValue = request.getParameter("lastSearchMinValue");
        String searchMaxValue = request.getParameter("lastSearchMaxValue");
        String url = INVALID_PAGE;
        try {
            //2. controller calls method model
            //2.1 new dao object
            TblWatchDAO dao = new TblWatchDAO();
            //2.2 call method of dao object
            // Minh chi can truyen vao pk thi no se biet chinh xac thang nao can xoa
            boolean result = dao.deleteAccount(Integer.parseInt(id));
            //3. process
            if (result) {// Neu xoa thang cong thi refresh lai chuc nang search
                //refresh --> call previous functions again
                //--> remind --> add request parameters based on how many input controls
                url = "MainController"
                        + "?action=Search"
                        + "&txtSearchMinValue=" + searchMinValue
                        + "&txtSearchMaxValue=" + searchMaxValue;
            }//delete is successfully
        }catch(SQLException ex){
            log("SQL: " + ex.getMessage());
        }catch(ClassNotFoundException ex){
            log("SQL: " + ex.getMessage());
        }
        finally {
            response.sendRedirect(url);
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
