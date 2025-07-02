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
import java.sql.SQLException;
import nhannm.registration.RegistrationCreateError;
import nhannm.registration.RegistrationDAO;
import nhannm.registration.RegistrationDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "createAccount.jsp";
    private final String LOGIN_PAGE = "login.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR_PAGE;
        //1. get all user's information
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        // Bien dung de check xem cuoi cung co loi khong
        boolean foundErr = false;
        // Tao ra object hung loi
        RegistrationCreateError errors = new RegistrationCreateError();

        try {
            //1.1 verify all user's error
            //*4 user error, *1 system error
            if (username.trim().length() < 6 || username.trim().length() > 30) {
                foundErr = true;
                errors.setUsernameLengthErr("Username is required from 6 to 30 characters");
            }
            //**Luu y: check password chuan roi moi check match confirm password
            //Nghia la new khong vao if dau thi password da chuan thi moi vao else-if de check match
            //con neu invalid nghia la vao if dau thi se khong check xem cofirm match password khong
            if (password.trim().length() < 8
                    || password.trim().length() > 20) {
                foundErr = true;
                errors.setPasswordLengthErr("Password is required from 8 to 20 characters");
            } else if (!confirm.trim().equals(password.trim())) {
                foundErr = true;
                errors.setConfirmNotMatched("Confirm must match password");
            }

            if (fullname.trim().length() < 2 || fullname.trim().length() > 40) {
                foundErr = true;
                errors.setFullNameLengthErr("Full name is required from 2 to 40 characters");
            }

            // Neu co loi 
            if (foundErr) {
                //_Minh se chon requestScope vi no se luu tam thoi de xuat ra 
                //ket qua cho nguoi dung. Thuong thi show ra se khong them xoa 
                //sua nen minh se chon parameter la hop li nhat. Tuy nhien dang
                //dung tai phia server nen se chon Attribute, chon khi them xoa
                //sua va khi dung tai server.
                request.setAttribute("CREATE_ERRORS", errors);
                //**Phai luu thi moi co cai lay ra ben jsp de show ra duoc
                //vi minh chi muon luu tam thoi de show ra nen se xai requestScope
                //va dang dung tai server nen se su dung attribute
            } else {// Neu khong co loi thi tao account
                //2. call methods of model
                //2.1 new DAO object
                RegistrationDAO dao = new RegistrationDAO();
                //2.2 call method of DAO object
                RegistrationDTO account = new RegistrationDTO(username, password, fullname, false);
                boolean result = dao.createAccount(account);
                //3. process
                //**Neu tao thanh cong account thi vao login
                if(result){
                    url = LOGIN_PAGE;
                }
            }
        //**Loi he thong la sau khi thuc thi
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("SQL: " +msg);
            //**Neu o tren khong co loi va vao else thi se tao account. Nhung neu 
            //username trung trong DB thi loi se vao catch nay
            //**Loi nay phat sinh khi khong co cac loi entity va trung primary key
            if(msg.contains("duplicate")){// Neu co loi nay thi minh se set loi vao obj errors 
                errors.setUsernameIsExisted(username + "IS EXISTED");
                //**Luu bang attribute vao request scope de show ra loi ben jsp
                request.setAttribute("CREATE_ERRORS", errors);
            }
        } catch (ClassNotFoundException ex) {
            log("Class Not Found: " + ex.getMessage());
        } finally {
            //Dang chua 5 parameter va 1 attribute
            //Ca parameter va attribute deu giu lai vi minh muon show loi
            //va hien lai gia tri o tren o nhap
            //==>
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
