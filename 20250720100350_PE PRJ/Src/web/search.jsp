<%-- 
    Document   : search
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
--%>
<%@page import="pe.model.AccountDto"%>
<%@page import="pe.model.RoomForRentDto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <style>
            table, th, td {
                border: 1px solid black;
            }
        </style>
        <script>
            function confirmDelete(formId) {
                if (confirm('Are you sure you want to delete this room?')) {
                    document.getElementById(formId).submit();
                }
                return false;
            }
        </script>
    </head>
    <body>
        <%
            AccountDto account = (AccountDto) session.getAttribute("ACCOUNT");
            if (account == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            List<RoomForRentDto> list = (List<RoomForRentDto>) request.getAttribute("SEARCH_RESULT");
            String searchValue = (String) request.getAttribute("SEARCH_VALUE");
            if (searchValue == null) {
                searchValue = "";
            }
        %>
        
        <h2>Room For Rent Management</h2>
        <div><a href="LogoutController">Logout</a></div>
        
        <form action="SearchController" method="GET">
            Search: <input type="text" name="searchValue" value="<%= searchValue %>" />
            <input type="submit" value="Search" />
        </form>
        
        <% String message = (String) request.getAttribute("SEARCH_MESSAGE"); %>
        <% if (message != null) { %>
            <p><%= message %></p>
        <% } %>

        <table>
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Price</th>
                    <th>Location</th>
                    <th>Description</th>
                    <th>Posted Date</th>
                    <th>Status</th>
                    <th>Username</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% if (list != null) {
                       int count = 0;
                       for (RoomForRentDto room : list) {
                           count++;
                           String formId = "deleteForm_" + room.getId();
                %>
                <tr>
                    <td><%= count %></td>
                    <td><%= room.getId() %></td>
                    <td><%= room.getTitle() %></td>
                    <td><%= room.getPrice() %></td>
                    <td><%= room.getLocation() %></td>
                    <td><%= room.getDescription() %></td>
                    <td><%= room.getPostedDate() %></td>
                    <td><%= room.getStatus() %></td>
                    <td><%= room.getUsername() %></td>
                    <td>
                        <% if (room.getStatus() != 1) { %>
                            <form id="<%= formId %>" action="DeleteController" method="GET" style="display: inline;">
                                <input type="hidden" name="id" value="<%= room.getId() %>" />
                                <input type="hidden" name="searchValue" value="<%= searchValue %>" />
                                <input type="button" value="Delete" onclick="confirmDelete('<%= formId %>')" />
                            </form>
                        <% } else { %>
                            <input type="button" value="Delete" disabled style="color: gray;" />
                        <% } %>
                    </td>
                </tr>
                <% }
                } %>
            </tbody>
        </table> 
    </body>
</html>
