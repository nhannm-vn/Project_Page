/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package nhannm.controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
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
public class LoginServlet extends HttpServlet {

    //_Tao hai hang so de chuyen page
    //_Trang giao dien dong
    private final String SEARCH_PAGE = "search.jsp";
    private final String INVALID_PAGE = "invalid.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //_Neu khong dem ra ngoai thi bi bug
        PrintWriter out = response.getWriter();
        //1. get all users information
        String url = INVALID_PAGE;

        //_Vi minh da chot nut nao ben dieu phoi roi nen se khong lay ra nua
        //String button = request.getParameter("btAction");
        //_Lay hai parameter ra tu form
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");

        try {
            //2. controller calls method Model
            //2.1 new Dao object
            RegistrationDAO dao = new RegistrationDAO();
            //2.2 call method of Dao object
            RegistrationDTO result = dao.checkLogin(username, password);
            //3. process
            if (result != null) { // Nghia la co duoc du lieu dto sau khi login success
                //_Trong truong hop truyen vao true no di check xem co ton tai khong
                //neu k ton tai thi tu dong tao session moi
                //_Khi nao can check session co ton tai thi dung false con neu
                //khong de gi thi mac dinh se la true
                //_Ben nay thang getSession giong giong setAttribute vay: chua co thi tao moi

                //_Hanh dong tao moi session. Ma session scope nay co khi nguoi dung 
                //gui yeu cau request len
                HttpSession session = request.getSession();

                //_Sau khi co vung scope roi thi se tien hanh luu obj dto vao
                //ben trong Attribute tai dang thao tac tai phia server
                session.setAttribute("USER_INFO", result);

                //_Tien hanh dieu huong sang trang jsp vi bay gio dao dien da 
                //khong con la tinh nua ma da chuyen thanh dong
                url = SEARCH_PAGE;

                //write: moi lan login thanh cong thi minh se luu cookies
                //==> Nho thang nay ma co the luu thong tin va duy tri dang nhap
                //1. Tao cookie tu cap username and password
                Cookie cookie = new Cookie(username, password);
                //2. Set thoi gian ton tai cho cookies
                cookie.setMaxAge(60 * 3);
                //3. Tra cookies ra thong qua response
                response.addCookie(cookie);
            }

        } catch (SQLException ex) {
            log("SQL: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("Class Not Found: " + ex.getMessage());
        } finally {
            //_Thang nay se lam minh bi show duong truyen
            //response.sendRedirect(url);
            //fix:
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            //Luc nay cai contextpath sau cung se duoc an di ma se hien anh dieu phoi
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
/*
    Flow: open browser nghia la co request dau tien voi button co value la null
thi se duoc DispatchServlet dieu phoi vao CheckAccountServlet 
_Sau do no se lay mang cookies xuong 
_Neu co mang cookies thi no lay thang cuoi do theo quy luat ghi de
_Lay ra name va value tu cookie cuoi cung do
_Goi method checkLogin ra neu nhu co result thi nghia la hop le va cho vao trang search luon
_Sau do phai tao scope va luu cai result do bang attribute de co duoc welcome + fullname
Flow: neu mo lan dau ma chua co cookie thi khi bam button login thi se duoc dispatchServlet
    dieu phoi vao LoginServlet va tao cookie o lan login lan dau tien
*/
