<%-- 
    Document   : search
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.USER_INFO.fullName}
        </font>
        <div style="display: flex; justify-items: center; align-items: center">
            <h1 style="margin-right: 10px">Search Page</h1>
            <form action="MainController">
                <input type="submit" value="Logout" name="action" />
            </form>
        </div>
        <form action="MainController" >
            Min Value <input type="text" name="txtSearchMinValue" 
                                value="${param.txtSearchMinValue}" /> <br/>
            Max Value <input type="text" name="txtSearchMaxValue" 
                                value="${param.txtSearchMaxValue}" /> <br/>
            <input type="submit" value="Search" name="action" />
        </form> 
        <%--Khai bao bien va gan gt cua parameter txtSearchValue cho no--%>
        <c:set var="searchMinValue" value="${param.txtSearchMinValue}" />
        <c:set var="searchMaxValue" value="${param.txtSearchMaxValue}" />
        <c:if test="${not empty searchMinValue and not empty searchMinValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}" />
            <c:if test="${not empty result}">
                <form action="MainController" method="POST">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Brand Name</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Size</th>
                                <th>Quantity</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter" >
                            <tr>
                                <td>
                                    ${counter.count}.
                                </td>
                                <td>
                                    ${dto.name}
                                </td>
                                <td>
                                    ${dto.brandName}
                                </td>
                                <td>
                                    <input type="text" name="txtDescription"
                                           value="${dto.description}"/>
                                </td>
                                <td>
                                    <input type="text" name="txtPrice"
                                           value="${dto.price}"/>
                                </td>
                                <td>
                                    ${dto.size}
                                </td>
                                <td>
                                    <input type="text" name="txtQuantity"
                                           value="${dto.quantity}"/>
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="MainController">
                                        <c:param name="action" value="Delete"/>
                                        <c:param name="txtPK" value="${dto.id}"/>
                                        <c:param name="lastSearchMinValue" 
                                                 value="${searchMinValue}"/>
                                        <c:param name="lastSearchMaxValue" 
                                                 value="${searchMaxValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="hidden" name="lastSearchMinValue" 
                                           value="${searchMinValue}" />
                                    <input type="hidden" name="lastSearchMaxValue" 
                                           value="${searchMaxValue}" />
                                    <input type="hidden" name="txtPK" 
                                           value="${dto.id}" />
                                    <input type="submit" value="Update" name="action" />
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </form>
            </c:if>
            <c:if test="${empty result}">
                <h2>
                    <font color="red">
                        No record is matched!!!
                    </font>
                </h2>
            </c:if>
        </c:if>
    </body>
</html>
