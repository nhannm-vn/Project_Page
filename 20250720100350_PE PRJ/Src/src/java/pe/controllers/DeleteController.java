package pe.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pe.model.AccountDto;
import pe.model.RoomForRentDao;

@WebServlet(name = "DeleteController", urlPatterns = {"/DeleteController"})
public class DeleteController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        
        try {
            HttpSession session = request.getSession();
            AccountDto account = (AccountDto) session.getAttribute("ACCOUNT");
            
            if (account != null) {
                String roomIdStr = request.getParameter("id");
                if (roomIdStr != null && !roomIdStr.trim().isEmpty()) {
                    int roomId = Integer.parseInt(roomIdStr);
                    
                    RoomForRentDao dao = new RoomForRentDao();
                    boolean result = dao.deleteRoom(roomId);
                    
                    if (result) {
                        
                        String searchValue = request.getParameter("searchValue");
                        if (searchValue != null && !searchValue.trim().isEmpty()) {
                            url = SUCCESS + "?searchValue=" + searchValue;
                        } else {
                            url = SUCCESS;
                        }
                    } else {
                        request.setAttribute("ERROR_MESSAGE", "Failed to delete the room or room is already rented!");
                    }
                }
            }
        } catch (Exception e) {
            log("Error at DeleteController: " + e.toString());
            request.setAttribute("ERROR_MESSAGE", "An error occurred: " + e.getMessage());
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
