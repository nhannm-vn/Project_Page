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
import java.util.Properties;
import nhannm.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="DispatchServlet", urlPatterns={"/DispatchServlet"})
//anotation phat sinh code them dau '/' vao
//name khi chay phat sinh thanh servlet name
//_Name khi phat sinh se thanh servlet-name
//_urlPatterns: con goi la code sorthand
//*Ben welcom-file se copy ten trong urlPattern cua DispatchServlet
//de bo vao ben kia. Khong can dau '/' Vi khi phat sinh code anotattion da
//co san luon dau '/'
public class DispatchServlet extends HttpServlet {
    //default
//    private final String LOGIN_PAGE = "login.html";
    //Dc URL cua LoginServlet
//    private final String LOGIN_CONTROLLER = "LoginServlet";
//    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
//    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String CHECK_ACCOUNT_CONTROLLER = "CheckAccountServlet";
//    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
//    private final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountServlet"; 
//    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        // Lay contextCope
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        
        //1. Which button did user click?
        String button = request.getParameter("btAction");
        String url = siteMaps.getProperty(
                    MyApplicationConstants.DispatchFeature.LOGIN_PAGE);
        //set default cua minh la trang login
        //luc nay button bang null vi no k truyen parameter tuc la ko dau "?"
        //vi ben web.xml ngay cho welcome file chung ta k truyen tham so
        
        response.setContentType("text/html;charset=UTF-8");
        try {
            if(button == null){ //first request
               //_check cookies co ton tai hay khong
               //neu chua co thi chac chan lan thu 1 
               url = siteMaps.getProperty(
                        MyApplicationConstants.DispatchFeature.CHECK_ACCOUNT_CONTROLLER);
               //_Nghia la bat browser lan dau tien thi se chua bam nut nao nghia 
               //la button = null. Luc nay no se dieu phoi vao controller check cookie
            } else{
                switch(button){
                    case "Login":{
                        url = siteMaps.getProperty(
                        MyApplicationConstants.DispatchFeature.LOGIN_CONTROLLER);
                        break;
                    }
                    case "Search":{
                        url = siteMaps.getProperty(
                        MyApplicationConstants.DispatchFeature.SEARCH_CONTROLLER);
                        break;
                    }
                    case "Delete":{
                        url = siteMaps.getProperty(
                        MyApplicationConstants.DispatchFeature.DELETE_CONTROLLER);
                        break;
                    }
                    case "Update":{
                        url = siteMaps.getProperty(
                        MyApplicationConstants.DispatchFeature.UPDATE_CONTROLLER);
                        break;
                    }
                    case "Create New Account":{
                        url = siteMaps.getProperty(
                        MyApplicationConstants.DispatchFeature.CREATE_ACCOUNT_CONTROLLER);
                        break;
                    }
                    case "Logout":{
                        url = siteMaps.getProperty(
                        MyApplicationConstants.DispatchFeature.LOGOUT_CONTROLLER);
                        break;
                    }
                }
            }
        }
        finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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

//De them mot chuc nang moi vao mo hinh MVC BreakDown
//+B1: tao tat ca cac giao dien view tro den dieu phoi(dispatcher)
//va copy gia tri parameter tu dieu phoi(dispatcher) vao ben trong name nut len
//+B2: mapping tinh nang moi vao ben trong dieu phoi(DispatchServlet) va su dung
//value cua nut lenh
//+B3: tao servlet chuc nang(LoginServlet) sau do copy dia chia url-pattern tai WDD
//+B4: tao hay goi DAO chua co thi tao co roi thi goi
//+B5: tao hoac goi DTO new view la view dong