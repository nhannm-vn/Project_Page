<%-- 
    Document   : search
    Created on : Jun 15, 2025, 10:38:38 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- 
_Luu y khi cai su dung thang nay chon dung duong dan
_Va phai them prefix dat ten neu khong se bug
--%>

<%--<%@page import="java.util.List" %>--%>
<%--<%@page import="nhannm.registration.RegistrationDTO" %>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.USER_INFO.fullName}
        </font>
        <h1>Search Page</h1> 
        <form action="DispatchServlet" >
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}"/> <br/>
            <input type="submit" value="Search" name="btAction"/>
        </form><br/>
        <%--Khai bao bien va gan cho no gt lay tu request gui len--%>
        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <%--not empty dung de check khong null va khong rong--%>
        <%--Neu co gia tri search thi tien hanh lam viec--%>
        <c:if test="${not empty searchValue}">
            <%--Khi co gia tri search nghia la se co list dto--%>
            <c:set var="result" value="${requestScope.SEARCH_RESULT}" />
            <%--Sau khi gan list dto vao bien thi can check list co ton tai khong--%>
            <%--Neu ton tai thi show ra table--%>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                </td>
                                <td>
                                    ${dto.password}
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    ${dto.role}
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <%--Neu khong ton tai thi show ra thong bao--%>
            <c:if test="${empty result}">
                <h2>
                    <font color="red">No record is matched!!!</font>
                </h2>
            </c:if>
        </c:if>
        
        <%--
        <% 
            //_scriplet nhu service() o ben trong servlet object don nhan va xu li
            //+phai enter xuong dong vi tuan thu luat viet code cua java
            //--> jspService() chua tat ca cac code cua expression va scripless
            //phat sinh ben trong phuong thuc nay
            //_doi voi declaration thi dua vao giua va enter hai cai nghia la no 
            //viet code duoc nhieu dong dung de khai bao bien va dinh nghia phuong
            //thuc. Khi phat sinh thi se phat sinh ben ngoai phuong thuc _jspService
            //==> 3 bo JSP Scripting 
            
            //lay value tu parameter, tu o input nhap(lay lai value)
            String searchValue = request.getParameter("txtSearchValue");
            //_logic
            //**Luu y: phai bat khac null vi khi nguoi dung vao thang trang search.jsp
            //thi se khong co ? nghia la khong co parameter thi value luc nay se la null
            //nen minh can chan
            if(searchValue != null){
                List<RegistrationDTO> result = 
                            (List<RegistrationDTO>)request.getAttribute("SEARCH_RESULT");
                //_Sau khi co duoc value la danh sach tu Attribute roi thi tiep theo
                //coi thu co ds khong(nghia co co search ra khong)
                if(result != null){//found
                    %>
                    <!-- Cu phap dong mo the scriplet giup chen doan ma html vao giua --> 
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full name</th>
                                <th>Role</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                //Viet code java
                                //_Tao con bien de dem no.
                                int count = 0;
                                for (RegistrationDTO dto : result) {
                                    %>
                                    <!-- Cu phap dong mo the scriplet giup chen doan ma html vao giua --> 
                                    <tr>
                                        <td>
                                            <%= ++count %>
                                        </td>
                                        <td>
                                            <%= dto.getUsername() %>
                                        </td>
                                        <td>
                                            <%= dto.getPassword() %>
                                        </td>
                                        <td>
                                            <%= dto.getFullName() %>
                                        </td>
                                        <td>
                                            <%= dto.isRole() %>
                                        </td>
                                    </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>

        <%
                }else{// not found
                    %>
                    <!-- Cu phap dong mo the scriplet giup chen doan ma html vao giua --> 
                    <h2>
                        <font color="red">
                            No record is matched!!!
                        </font>
                    </h2>
        <%
                }
            }//having request parameter
        %>
    --%>
    </body>
</html>
