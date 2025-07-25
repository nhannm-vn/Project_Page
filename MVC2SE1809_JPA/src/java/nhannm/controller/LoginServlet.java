/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package nhannm.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Properties;
import nhannm.registration.Registration;
import nhannm.registration.RegistrationBLO;
import nhannm.registration.RegistrationDAO;
import nhannm.registration.RegistrationDTO;
import nhannm.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
public class LoginServlet extends HttpServlet {

    //private final String SEARCH_PAGE = "search.html";
    //_Vi login thi can show ra welcome + fullname nen can giao dien dong jsp
//    private final String SEARCH_PAGE = "searchPage";
//    private final String INVALID_PAGE = "invalidPage";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Lay contextCope
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        // Neu khong dem ra ngoai thi no se bi bug
        PrintWriter out = response.getWriter();
        //1. get all users information
//        String url = INVALID_PAGE;
        String url = siteMaps.getProperty(
                MyApplicationConstants.LoginFeature.INVALID_PAGE);
        //_Vi minh da chot nut nao ben dieu phoi roi nen se khong lay ra nua
        //String button = request.getParameter("btAction");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        try {
            //2. Controller calls method of Model
            //2.1 New DAO object
//            RegistrationDAO dao = new RegistrationDAO();
            RegistrationBLO blo = new RegistrationBLO();

            //2.2 call method of DAO object
            //boolean result = dao.checkLogin(username, password);
            //_Luc nay se khong con la check co tk khong nua nghia la result khong
            //con la boolean ma luc nay se la mot obj luon. Neu obj != null thi cho lam tiep
            Registration result = blo.checkLogin(username, password);
            //3. Process 
            if (result != null) { //_Nghia la co duoc obj dto
                //_Khi gt truyen vao la false, container se di tim xem gt co ton tai k
                //neu ton tai thi tra ve session hien hanh, k ton tai thi se tra
                //ve null
                //_Trong truong hop truyen vao true no di check xem co ton tai khong
                //neu k ton tai thi tu dong tao session moi
                //_Khi nao can check session co ton tai thi dung false
                //_Luu y khi khong truyen gi ca thi mac dinh la true
                //_Ben nay thang getSession giong giong setAttribute vay: chua co thi tao moi
                
                //_Hanh dong tao moi session. Ma session scope nay co khi nguoi dung 
                //gui yeu cau request len
                HttpSession session = request.getSession();
                
                //_Gia tri cua dto nam trong attribute. Vi dang thao tac tai server
                //nen minh se chon attribute
                session.setAttribute("USER_INFO", result);
                
                url = siteMaps.getProperty(
                    MyApplicationConstants.LoginFeature.SEARCH_PAGE);
                
                //write: login thanh cong thi luu cookies
                //1. Tien hanh tao cookie tu username va password
                Cookie cookie = new Cookie(username, password);
                //2. Setup thoi gian ton tai cho cookie
                cookie.setMaxAge(60 * 3);
                //3. Tra ve thong qua response
                response.addCookie(cookie);
            }//user is authenticated

//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
        } finally {
//            response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
