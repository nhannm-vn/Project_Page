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
@WebServlet(name="CheckAccountServlet", urlPatterns={"/CheckAccountServlet"})
public class CheckAccountServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String SEARCH_PAGE = "search.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1. get all cookies
        String url = LOGIN_PAGE;
        //_Lay ra cai mang cookies
        Cookie[] cookies = request.getCookies();
        try  {
            //2. check existed cookies
            if(cookies != null){
                //2.1 get username and password
                //_Lay thang cuoi vi co tinh chat ghi de
                Cookie recentCookie = cookies[cookies.length - 1];
                //_Cap name va value neu nhu co coolies
                String username = recentCookie.getName();
                String password = recentCookie.getValue();
                //2.2 controller calls method Model
                RegistrationDAO dao = new RegistrationDAO();
                //_Dua vao cap name value do co duoc dto object khong
                RegistrationDTO result = dao.checkLogin(username, password);
                //2.3 process
                if(result != null){ // neu co thi chuyen qua trang search
                    //_Neu ma dua vao cap gia tri cua cookies tim ra duoc obj dto
                    //thi minh se chuyen trang vao trang search dong thoi luu ket qua
                    //vao session de no hien thi welcome tren trang search
                    url = SEARCH_PAGE;
                    //_Tao ra vung session
                    HttpSession session = request.getSession();
                    session.setAttribute("USER_INFO", result);
                }
            }
        }catch (SQLException ex) {
            log("SQL:" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("Class Not Found:" + ex.getMessage());
        } finally{
            // Thich dung gi thi dung do minh khong so no mat gi
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
