<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<table class="cart_table">
    <tr class="cart_title">
        <td>Books</td>
        <td>Order Date</td>
        <td>Total Amount</td>
    </tr>

    <%-- loop through each order line in the cart to add item to cart table --%>
    <c:forEach var="order" items="${orders}" varStatus="status">
    </c:forEach>

</table>