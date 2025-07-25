/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package nhannm.controller;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Properties;
import nhannm.registration.RegistrationBLO;
import nhannm.registration.RegistrationDAO;
import nhannm.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {
//    private final String ERROR_PAGE = "error.html";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        //1. get all user's information
        String username = request.getParameter("pk");
        String searchValue = request.getParameter("lastSearchValue");
        String url = siteMaps.getProperty(
                    MyApplicationConstants.DeleteFeature.ERROR_PAGE);
        try {
            //2. controller calls method model
            //2.1 new dao object
//            RegistrationDAO dao = new RegistrationDAO();
            RegistrationBLO blo = new RegistrationBLO();
            //2.2 call method of dao object
            // Minh chi can truyen vao pk thi no se biet chinh xac thang nao can xoa
            boolean result = blo.deleteAccount(username);
            //3. process
            if(result){// Neu xoa thang cong thi refresh lai chuc nang search
                //refresh --> call previous functions again
                //--> remind --> add request parameters based on how many input controls
                url = "DispatchServlet"
                        + "?btAction=Search"
                        + "&txtSearchValue=" + searchValue;
            }//delete is successfully
//        } catch (SQLException ex) {
//            log("SQL: " + ex.getMessage());
//        } catch (ClassNotFoundException ex) {
//            log("Class Not Found : " + ex.getMessage());
        } finally {
            //forward
            // Luc nay co 5 parameter khi detele thanh cong
            //nhu vay thi btAction se thanh cai mang lay ten lam ten mang
            //==> nen phai xai send direct de huy 3 thang kia di 
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
/*
Loi duoc chia thanh 2 nhom:
_Loi nguoi dung: nguoi dung co tinh vi pham rang buoc cua he thong
==> thuc hien su dung 1 attribute de luu tru loi do sau do chuyen qua tran loi de
trinh bay va ghi logfile
_Loi he thong: xay ra sau khi he thong duoc thuc thi va co lien qua den 
nguoi deverloper
==> thuc hien co che delogfile
*/