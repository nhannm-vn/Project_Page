/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package nhannm.controller;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Properties;
import nhannm.registration.RegistrationDAO;
import nhannm.registration.RegistrationDTO;
import nhannm.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CheckAccountServlet", urlPatterns = {"/CheckAccountServlet"})
public class CheckAccountServlet extends HttpServlet {

//    private final String LOGIN_PAGE = "login.html";
//    private final String SEARCH_PAGE = "search.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Lay contextCope
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        
        //1. get all cookies 
        //**Neu nhu check ma cookie khong co hoac khong dung thi cho ve trang LOGIN_IN
        //va sau khi login vao lan dau thi se tao cookie cho no
        String url = siteMaps.getProperty(
                    MyApplicationConstants.CheckAccountFeature.LOGIN_PAGE);
        //_Lay mang cookies xuong
        Cookie[] cookies = request.getCookies();
        try {
            //2. check existed cookies
            if (cookies != null) {// Nghia la neu co mang cookies
                //2.1 get username and password
                //_Lay nhu vay vi theo quy tac no luu domain va lay thang cuoi cung
                Cookie recentCookie = cookies[cookies.length - 1];
                //_Lay ra nhu vay theo cap name va value
                String username = recentCookie.getName();
                String password = recentCookie.getValue();
                //2.2 Controller calls method Model
                RegistrationDAO dao = new RegistrationDAO();
                //_Tu name va value do kt xem co result nao khong
                RegistrationDTO result = dao.checkLogin(username, password);
                //2.3 process
                if(result != null){// Neu co kq tu checkLogin
                    //_Chuyen sang trang search luon
                    url = siteMaps.getProperty(
                    MyApplicationConstants.CheckAccountFeature.SEARCH_PAGE);
                    //_Neu co dto thi lap tuc tao session va luu bang attribute
                    //phai luu thi no moi co the lay ra de the hien dong welcome + fullname
                    HttpSession session = request.getSession();
                    session.setAttribute("USER_INFO", result);
                }//end user existed
                
                //Note: Neu nhu no khong vao if thi nghia la no se chuyen sang trang login
                //luc nay sau khi no login vao lan dau minh se tao cookie cho no 
            }
        } catch (SQLException ex) {
            log("SQL:" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("Class Not Found:" + ex.getMessage());
        } finally {
            // Thich dung gi dung do no khong mat
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
    Flow: open browser nghia la co request dau tien voi button co value la null
thi se duoc DispatchServlet dieu phoi vao CheckAccountServlet 
_Sau do no se lay mang cookies xuong 
_Neu co mang cookies thi no lay thang cuoi do theo quy luat ghi de
_Lay ra name va value tu cookie cuoi cung do
_Goi method checkLogin ra neu nhu co result thi nghia la hop le va cho vao trang search luon
_Sau do phai tao scope va luu cai result do bang attribute de co duoc welcome + fullname
Flow: neu mo lan dau ma chua co cookie thi khi bam button login thi se duoc dispatchServlet
    dieu phoi vao LoginServlet va tao cookie o lan login lan dau
*/