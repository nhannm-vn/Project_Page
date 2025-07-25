<%-- 
    Document   : search
    Created on : Jun 13, 2025, 8:21:14 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        Welcome, ${sessionScope.USER_INFO.lastname}
        </font>
        <div style="display: flex; justify-items: center; align-items: center">
            <h1 style="margin-right: 10px">Search Page</h1>
            <form action="DispatchServlet">
                <input type="submit" value="Logout" name="btAction" />
            </form>
        </div>
        <form action="DispatchServlet" >
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" /> <br/>
            <input type="submit" value="Search" name="btAction" />
        </form> 
        <%--Khai bao bien va gan gt cua parameter txtSearchValue cho no--%>
        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <%--Kiem tra xem bien searchValue co gia tri hay khong--%>
        <c:if test="${not empty searchValue}">
            <%--
                Neu vao toi day thi nghia la gia tri co gt search 
                khi do minh se co duoc gia tri list dto luu trong bien result
            --%>
            <c:set var="result" value="${requestScope.SEARCH_RESULT}" />
            <%--Kiem tra xem bien result co gia tri hay khong neu co thi duyet for ra--%>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%--Trong result list dto co bao nhieu item thi render ra het--%>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <%--Moi item de co the update duoc thi minh can boc no vao trong
                        form vi neu khong co form thi khong the truyen gt di duoc--%>
                        <form action="DispatchServlet" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}
                                .</td>
                                <td>
                                    <%--Doi voi username thi minh van phai truyen 
                                    no di vi neu khong co no thi khong biet chinh xac thang nao
                                    de ma update. Tuy nhien de truyen di thi can o input
                                    ==> phai la hidden de user khong the chinh sua hay gi het
                                    ==> khi hidden di roi thi phai render ra luon de thay duoc gt hien dien--%>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.username}" />
                                </td>
                                <td>
                                    <%--Doi voi password thi minh se cho update chinh vi vay
                                    ma minh se cho input type text 
                                    ==> minh khong can render ra du lieu ma se thay truc tiep tren o text--%>
                                    <input type="text" name="txtPassword" 
                                           value="${dto.password}" /><br/>
                                </td>
                                <td>
                                    ${dto.lastname}
                                </td>
                                <td>
                                    <%--Doi voi thang role thi se db hon va hien thi luon vi 
                                    minh co the cho nguoi ta update role--%>
                                    <%--Khi ma tren DB role = 1 thi se cho no thuoc tinh
                                    checked thang nao co checked thi value truyen ve server
                                    se la "ON"--%>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                            <c:if test="${dto.isAdmin}">
                                               checked="checked"
                                            </c:if>
                                    />
                                </td>
                                <td>
                                    <%--Minh can truyen di 3 paramater de tien hanh delete--%>
                                    <%--nut lenh, pk, searchValue de refresh--%>
                                    <c:url var="deleteLink" value="DispatchServlet">
                                        <c:param name="btAction" value="Delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" 
                                                 value="${param.txtSearchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <%--Khi update minh can goi lai chuc nang truoc do de
                                    refresh nen minh can truyen them value search
                                    ==> muon truyen di thi can o input nen se la hidden--%>
                                    <input type="hidden" name="lastSearchValue" 
                                           value="${searchValue}" />
                                    <%--Day la nut submit giup cho nguoi dung truyen du lieu lieu di--%>
                                    <input type="submit" value="Update" name="btAction" />
                                </td>
                            </tr>
                        </form>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <%--Neu list dto khong co thi thong bao ra man hinh--%>
            <%--Nghia la neu minh search voi du lieu khong map trong DB thi
            se khong co du lieu de hien thi luc nay se hien dong nay len
            **Con truong hop khong nhap gi vao o search thi no se refresh lai chuc nang search--%>
            <c:if test="${empty result}">
                <h2>
                    <font color="red">
                        No record is matched!!!
                    </font>
                </h2>
            </c:if>
        </c:if>
    <%--
    <% 
        //_nhu service() ben trong servlet object don nhan va xu li
        //_phai enter xuong dong vi tuan thu luat viet code cua java
        // --> _jspService() tat ca code cua expression va scriptless deu
        //phat sinh ben trong phuong thuc nay
        //_doi voi declaration thi dua vao giua enter 2 cai nghia la no
        //viet code duoc nhieu dong dung de khai bao bien toan cuc servlet
        //class va dinh nghia phuong thuc. Khi phat sinh thi se phat sinh
        //ben ngoai phuong thuc _jspService()
        //==> 3 bo JSP Scripting
        //_lay lai value
        String searchValue = request.getParameter("txtSearchValue");
        //_Phai bat khac null tai nguoi dung truy cap truc tiep thang vao
        //trang search.jsp thi parameter se la null vi khong co dau ? nao
        if(searchValue != null){
            List<RegistrationDTO> result = 
                    (List<RegistrationDTO>)request.getAttribute("SEARCH_RESULT");
            if(result != null){//found
                %>
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
                            int count = 0;
                            for (RegistrationDTO dto : result) {
                                %>
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
                            }//traverse dto in result
                        %>
                    </tbody>
                </table>
    <%
            }else{//not found
                %>
                <h2>
                    <font color="red">
                        No record is matched!!!!
                    </font>
                </h2>
    <%
            }
        }//having request parameter
    %>--%>
</body>
</html>
