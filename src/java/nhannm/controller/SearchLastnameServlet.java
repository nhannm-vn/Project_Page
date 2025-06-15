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
import java.util.List;
import nhannm.registration.RegistrationDAO;
import nhannm.registration.RegistrationDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {

    //_Mac dinh khi vao servlet nay thi url cua no se dung tai trang search.html
    private final String SEARCH_PAGE = "search.html";
    //_Khi co search value ca co hanh dong seach thi no chuyen sang trang 
    //search.jsp
    private final String SEARCH_RESULT = "search.jsp";
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //(search theo lastname)
        //_Mac dinh cho url la trang search
        String url = SEARCH_PAGE;
        //1. Lay toan bo thong tin cua nguoi dung 
        String searchValue = request.getParameter("txtSearchValue");
        try {
            //_Khi co value thi moi tien hanh search con neu khong co thi se 
            //cho no load lai trang
            if (!searchValue.trim().isEmpty()) {
                //2. Controller call method of Model
                //2.1 new DAO object
                RegistrationDAO dao = new RegistrationDAO();
                //2.2 call method DAO object
                //_Sau khi chay dong nay thi ben trong list account se co gt
                dao.searchLastname(searchValue);
                //3. process
                //_Sau khi buoc tren co gia tri thi minh se lay danh sach ra 
                //thong qua method getAccount vi list da bi private
                List<RegistrationDTO> result = dao.getAccounts();
                //*Chot chuc nang cua SearchLastnameServlet la show vi moi servlet
                //chi thuc hien duy nhat mot chuc nang
                //*Bat buoc phai su dung trang dong, vi giao dien la dong nen bat
                //buoc phai chuyen sang trang jsp nghia la trang trinh bay
                //_Luc nao cung se mac dinh la url dung tai trang default sau
                //do la gan lai duong dan url cuoi cung la send ra 
                url = SEARCH_RESULT;
                //_Minh se chon requestScope vi no se luu tam thoi de xuat ra 
                //ket qua cho nguoi dung. Thuong thi show ra se khong them xoa 
                //sua nen minh se chon parameter la hop li nhat. Tuy nhien dang
                //dung tai phia server nen se chon Attribute, chon khi them xoa
                //sua va khi dung tai server.
                //_Tao moi attribute nghia la set attribute chu chang can dat ten moi
                //do ten attribute la duy nhat va se khong bao gio trung nhau
                //*Luu y: dat ten attribute theo quy tac uppercase hang so
                //****Chot lai phai luu gia tri trong nay moi co cai hien thi
                //ra giao dien dong
                request.setAttribute("SEARCH_RESULT", result);
                //*Tai thoi diem nay co 2 parameter btAction, txtSearchValue do form
                //search dang truyen ve phia server thong qua dispatcher la btAction, 1 attribute
                //SEARCH_RESULT
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        finally {
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
