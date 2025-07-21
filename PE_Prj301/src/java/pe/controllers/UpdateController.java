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
@WebServlet(name="UpdateController", urlPatterns={"/UpdateController"})
public class UpdateController extends HttpServlet {
    private final String INVALID_PAGE = "invalid.html";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1. get user's infor
        // Hien tai dang co parameter
        String id = request.getParameter("txtPK");
        String description = request.getParameter("txtDescription");
        String price = request.getParameter("txtPrice"); 
        String quantity = request.getParameter("txtQuantity");
        String lastSearchMinValue = request.getParameter("lastSearchMinValue");
        String lastSearchMaxValue = request.getParameter("lastSearchMaxValue");
        String url = INVALID_PAGE;
        try  {
            //2. controller call method of model 
            //2.1 new dao object
            TblWatchDAO dao = new TblWatchDAO();
            //2.2 call method of DAO object
            boolean result = dao.updateAccount(Integer.parseInt(id),
                    description, Double.parseDouble(price), Integer.parseInt(quantity));
            //3. process
            if (result) {// Neu update xong thi se tu dong refresh bang cach nhac lai chuc nang truoc do
                //refresh --> call previous functions again
                //--> remind --> add request parameters based on how many input controls
                url = "MainController"
                        + "?action=Search"
                        + "&txtSearchMinValue=" + lastSearchMinValue
                        + "&txtSearchMaxValue=" + lastSearchMaxValue;
            }
        }catch (SQLException ex) {
            log("SQL" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("Class Not Found" + ex.getMessage());
        }finally{
            response.sendRedirect(url);
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
