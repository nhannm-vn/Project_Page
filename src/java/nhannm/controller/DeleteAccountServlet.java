/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package nhannm.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import nhannm.registration.RegistrationBLO;
import nhannm.registration.RegistrationDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1. get all user's information 
        // Tu duong link da tao ben phia jsp truyen ve voi 2 parameter khong tinh
        //nut lenh bao gom: lastSearchValue va pk
        String username = request.getParameter("pk");
        String searchValue = request.getParameter("lastSearchValue");
        //_Mac dinh cho url la trang loi
        String url = ERROR_PAGE;
        try {
            //2. controller calls method model
            //2.1 new DAO object
//            RegistrationDAO dao = new RegistrationDAO();
            RegistrationBLO blo = new RegistrationBLO();
            //2.2 call method of DAO object
            boolean result = blo.deleteAccount(username);
            //3. process
            if (result) {
                // *Neu xoa thanh cong thi goi lai chuc nang truoc do de no
                //refresh lai trang
                //refresh --> call previos functions again 
                //--> remind --> add request parameter based on how many input control
                url = "DispatchServlet"
                        + "?btAction=Search"
                        + "&txtSearchValue=" + searchValue;
            }//delete is successfully
//        } catch (SQLException ex) {
//            log("SQL: " + ex.getMessage());
//        } catch (ClassNotFoundException ex) {
//            log("Class Not Found: " + ex.getMessage());
        } finally {
            //forward
            // Luc nay co 5 parameter khi delete thanh cong ma nhu vay se co tan 
            //hai thang btAction luc nay no se lay lam ten mang va se gia tri lon xon
            //==>nen sau khi lay value tu parameter roi thi phai huy 3 thang kia
            //nen se xai send direct
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

/**
 * Loi duoc chia thanh 2 nhom: +Loi nguoi dung: nguoi dung co tinh vi pham rang
 * buoc quy dinh cua he thong --> thuc hien su dung 1 attribute de luu loi va
 * tien hanh chuyen qua trang loi de trinh bay va tien hanh ghi logfile +Loi he
 * thong: xay ra khi he thong duoc thuc thi va co lien quan den nguoi dev -->
 * thuc hien co che delogfile
 */
