/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package nhannm.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import nhannm.registration.RegistrationDAO;
import nhannm.registration.RegistrationDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CheckAccountServlet", urlPatterns = {"/CheckAccountServlet"})
public class CheckAccountServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String SEARCH_PAGE = "search.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        Cookie[] cookies = request.getCookies();
        try {
            if (cookies != null) {
                Cookie recentCookie = cookies[cookies.length - 1];
                String username = recentCookie.getName();
                String password = recentCookie.getValue();
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO result = dao.checkLogin(username, password);
                //2.3 process
                if(result != null){
                    url = SEARCH_PAGE;
                    
                    HttpSession session = request.getSession();
                    session.setAttribute("USER_INFO", result);
                }//end user existed
                
            }
        } catch (SQLException ex) {
            log("SQL:" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("Class Not Found:" + ex.getMessage());
        } finally {
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