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
import java.util.List;
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
//anotation: dung cho nhung servlet it thay doi. Tai sao?
@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {

    // Bay gio thi trang default cua chung ta la trang search.jsp luon boi vi
    //minh muon khi voa lai trang search thi se khong bi mat dong welcome + fullname
    // Nghia la view cua giao dien la view dong luon chu khong con 1 tinh 1 dong nua
//    private final String SEARCH_PAGE = "search.jsp";
    //private final String SEARCH_PAGE = "search.html";
//    private final String SEARCH_RESULT = "search.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Lay contextCope
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        
        //1. lay toan bo thong tin cua nguoi dung
        String searchValue = request.getParameter("txtSearchValue");
        String url = siteMaps.getProperty(MyApplicationConstants.SearchFeature.SEARCH_PAGE);

        try {
            if (searchValue != null && !searchValue.trim().isEmpty()) {
                //2. controller calls method of Model
                //2.1 New DAO object
//                RegistrationDAO dao = new RegistrationDAO();
                RegistrationBLO blo = new RegistrationBLO();

                //2.2 call method of DAO object
//                dao.searchLastname(searchValue);
                //3. process
                //_Lay du lieu nam trong List<DTO> 
                //_Chot chuc nang cua SearchLastnameServlet la show
                //vi moi servlet chi thuc hien duy nhat 1 chuc nang
                //_Bat buoc phai su dung trang dong vi
//                List<RegistrationDTO> result = dao.getAccounts();
    
                List<Registration> result = blo.searchLastname(searchValue);
                //_Vi giao dien la dong nen phai chuyen sang tran JSP
                //nghia la sang trang trinh bay
                url = siteMaps.getProperty(MyApplicationConstants.SearchFeature.SEARCH_RESULT);
                //_Minh se chon requestScope vi no se luu tru tam thoi de in 
                //de xuat ket qua cho nguoi dung. Minh se chon parameter vi minh
                //chi doc chu khong them xoa sua. Tuy nhien chung ta dung o
                //server nen chon Atribute, chon khi them xoa sua va khi dung 
                //tai server. 
                //_Tao moi atributte nghia la set atribute. Chu chang can dat ten
                //moi do ten atributte la duy nhat va khong bao gio trung
                //_update la uu tien hon vi co moi update ==> kbh co chuyen new
                //_Dat ten theo quy tac uppercase
                //_Tai thoi diem nay co 2 parameter btAction, va txtSearchValue 
                //do form search dang truyen ve phia server va qua dispatcher,
                //1 attribute SEARCH_RESULT
                request.setAttribute("SEARCH_RESULT", result);
            }
//        } catch (SQLException ex) {
//            log("SQL: " + ex.getMessage());
//        } catch (ClassNotFoundException ex) {
//            log("Class Not Found : " + ex.getMessage());
        } finally {
            //_Phai dung request dispatch vi duy tri ket qua trinh bay
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
