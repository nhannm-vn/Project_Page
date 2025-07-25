/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package nhannm.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Properties;
import nhannm.registration.RegistrationCreateError;
import nhannm.registration.RegistrationDAO;
import nhannm.registration.RegistrationDTO;
import nhannm.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

//    private final String ERROR_PAGE = "createAccountPage";
//    private final String LOGIN_PAGE = "login.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        
        String url = siteMaps.getProperty(
            MyApplicationConstants.CreateFeature.CREATE_ACCOUNT_PAGE);
        //1. get all user's information
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        boolean foundErr = false; // để check có lỗi
        //DTO
        RegistrationCreateError errors = new RegistrationCreateError();// để hứng lỗi

        try {
            //1.1 verify all user's error
            //Có 4 error user và 1 error system
            //1.2. Check User Errors

            if (username.trim().length() < 6
                    || username.trim().length() > 30) {
                foundErr = true;
                errors.setUsernameIsExisted(
                siteMaps.getProperty(MyApplicationConstants.CreateFeature.USERNAME_LEN_ERR));
            }
            // *Doi voi loi nay thi neu password valid thi moi check tiep xem co match voi
            //confirm password
            if (password.trim().length() < 8
                    || password.trim().length() > 20) {
                foundErr = true;
                errors.setPasswordLengthErr(MyApplicationConstants.CreateFeature.PASSWORD_LEN_ERR);
            } else if (!confirm.trim().equals(password.trim())) {//*confirm chi bat khi va chi khi password dung
                foundErr = true;
                errors.setConfirmNotMatched(MyApplicationConstants.CreateFeature.CONFIRM_NOT_MATCH_PASSWORD);
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 40) {
                foundErr = true;
                errors.setFullNameLengthErr(MyApplicationConstants.CreateFeature.FULLNAME_LEN_ERR);
            }

            // **Neu co loi
            if (foundErr) {// error ocur
                request.setAttribute("CREATE_ERRORS", errors);
            } else {//**Neu khong co loi 
                //2. controller calls method of model
                //2.1 new DAO object
                RegistrationDAO dao = new RegistrationDAO();
                //2.2 call method of DAO object
                RegistrationDTO account = new RegistrationDTO(
                        username, password, fullname, false);
                boolean result = dao.createAccount(account);
                //3. process
                if(result){
                    url = siteMaps.getProperty(
                    MyApplicationConstants.CreateFeature.LOGIN_PAGE);
                }
            }
        //**Loi he thong la sau khi thuc thi
        //**Neu co loi khi tao account thi loi se no va nhay xuong catch. Luc nay
        //se la loi he thong se duoc tao va se duoc set vao ben trong obj errors
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("SQL: " + msg);
            if(msg.contains("duplicate")){
                errors.setUsernameIsExisted(username + "is EXISTED");
                request.setAttribute("CREATE_ERRORS", errors);
            }
        } catch (ClassNotFoundException ex) {
            log("Class Not Found : " + ex.getMessage());
        } finally {
            // Dang chua 5 parameter va 1 attribute
            // Ca parameter va attribute deu giu lai vi minh muon show loi
            //va hien thi lai gia tri tren o nhap
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
