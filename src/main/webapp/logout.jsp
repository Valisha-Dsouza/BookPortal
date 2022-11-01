
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/jquery-1.5.1.min.js" type="text/javascript"></script>
<%-- if user logged in, remove all session variables --%>
<c:if test="${not empty user}">

    <c:remove var="user" scope="session" />
    <c:remove var="cart" scope="session" />
    <c:remove var="total" scope="session" />
    <c:remove var="manager" scope="session" />

    <script type="text/javascript">
    $(document).ready(function(){
     $.ajax({
        type: "GET",
        url: "Logout",
        dataType: "html",
        error: function(){
            alert("Successfully logged out!");
             window.location = "index.jsp";
        },
        success: function(result) {
            alert("Successfully logged out!");
             window.location = "index.jsp";
        }
    });
    });
    </script>

</c:if>
