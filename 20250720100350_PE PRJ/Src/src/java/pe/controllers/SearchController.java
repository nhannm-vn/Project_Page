package pe.controllers;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pe.model.AccountDto;
import pe.model.RoomForRentDao;
import pe.model.RoomForRentDto;

@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "search.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        
        try {
            HttpSession session = request.getSession();
            AccountDto account = (AccountDto) session.getAttribute("ACCOUNT");
            
            if (account != null) {
                url = SUCCESS;
                String searchValue = request.getParameter("searchValue");
                
                if (searchValue != null && !searchValue.trim().isEmpty()) {
                    RoomForRentDao dao = new RoomForRentDao();
                    List<RoomForRentDto> result = dao.searchRooms(searchValue);
                    
                    if (result != null && !result.isEmpty()) {
                        request.setAttribute("SEARCH_RESULT", result);
                    } else {
                        request.setAttribute("SEARCH_MESSAGE", "No data matching the search criteria found!");
                    }
                    
                    request.setAttribute("SEARCH_VALUE", searchValue);
                }
            }
        } catch (Exception e) {
            log("Error at Search: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
