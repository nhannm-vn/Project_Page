/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package nhannm.controller;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Properties;
import nhannm.registration.RegistrationDAO;
import nhannm.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {

//    private final String ERROR_PAGE = "error.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Lay contextCope
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        
        //1. get user's infor
        // Hien tai dang co parameter
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String isAdmin = request.getParameter("chkAdmin");
        // Thang nay se lay chuoi "ON" truyen ve neu nhu co check="checked" 
        //con neu khong checked thi se != "ON"
        String searchValue = request.getParameter("lastSearchValue");
        String url = siteMaps.getProperty(
                MyApplicationConstants.UpdateFeature.ERROR_PAGE);

        try {

            //2. controller call method of model 
            //2.1 new dao object
            RegistrationDAO dao = new RegistrationDAO();
            //2.2 call method of DAO object
            boolean result = dao.updateAccount(username, password, isAdmin);
            //3. process
            if (result) {// Neu update xong thi se tu dong refresh bang cach nhac lai chuc nang truoc do
                //refresh --> call previous functions again
                //--> remind --> add request parameters based on how many input controls
                url = "DispatchServlet"
                        + "?btAction=Search"
                        + "&txtSearchValue=" + searchValue;
            }

        } catch (SQLException ex) {
            log("SQL: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("Class Not Found : " + ex.getMessage());
        } finally {
            //forward 
            //se khong duoc su dung boi vi luc nay minh co tan 7 parameter
            //chinh vi vay neu khong huy thi se co tan 2 btAction va khong biet chinh xac la cai nao
            //==> xai sendRedirect
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
