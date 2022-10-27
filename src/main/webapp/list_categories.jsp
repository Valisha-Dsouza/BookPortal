<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ page import="java.util.*" %>
    <%@ page import="java.lang.*" %>
    <%@ page import="onlinebookstore.models.CategoryDTO" %>
<%
List<CategoryDTO> categories=(List)session.getAttribute("categories");
%>
<%=categories%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- loop through all items in category list and show their names --%>

<c:forEach var="category" items="${categories}">
    <li><a href="category.jsp?id=${category.id}&page=1">${category.name}</a></li>
    <span class="hidden">${category.id}</span>
</c:forEach>
