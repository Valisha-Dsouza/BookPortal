<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- if cart session variable empty, initialize it --%>
<c:if test="${empty cart}">
    <c:set var="cart" value="<%= new ArrayList()%>" scope="session" />
    <c:set var="total" value="0" scope="session" />
    <c:set var="cart_books" value="<%= new ArrayList()%>" scope="session" />
</c:if>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
        <title>Book Store</title>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <script src="js/jquery-1.5.1.min.js" type="text/javascript"></script>
    </head>
    <body>
        <script type="text/javascript">
            $(document).ready(function(){
                //get and display category list
                $.ajax({
                    type: "GET",
                    url: "ListCategories",
                    dataType: "html",
                    success: function(result) {
                        var dom = $(result);
                        $("#categories").hide().html(dom).fadeIn('def');
                        //update category selections
                        $("#categories li").each(function(){
                            var selected = (${not empty param.category} && $(this).index("#categories li") + 1 == '${param.category}' + 0)?" selected='selected'":"";
                            $("#category_list").append("<option value='" +
                                $("span.hidden").get($(this).index("#categories li")).innerHTML + "'" +
                                selected + ">" + $(this).text() + "</option>");
                        });
                    }
                });

                //move focus to Title field
                $("#title").focus();

                //format input fields
                $("input:text").blur(function(){
                    $(this).val(jQuery.trim($(this).val()));
                });

                //if no condition provided, hide search box
                if(${empty param.title && empty param.author && empty param.category}){
                    $("#search").hide();
                }else{
                    //else, get and display search result
                    $.ajax({
                        type: "GET",
                        url: "SearchBooks?title=${param.title}&author=${param.author}&category=${param.category}&page=${param.page}",
                        dataType: "html",
                        error: function(){
                            $("#content").hide().html("<b><span class='red'>" +
                                "Cannot find book(s) with specified condition!" +
                                "</span></b>").fadeIn('def');
                        },
                        success: function(result) {
                            var dom = $(result);
                            $("#content").hide().html(dom).fadeIn('def');
                        }
                    });
                }
            });
        </script>

        <div id="wrap">

            <div class="header">
                <div class="logo"><a href="index.jsp"><img src="images/logo.gif" alt="" title="" border="0" /></a></div>
                <div id="menu">
                    <ul>
                        <li><a href="index.jsp">home</a></li>
                        <li><a href="about.jsp">about us</a></li>
                        <li><a href="category.jsp">books</a></li>
                        <li class="selected"><a href="search.jsp">search</a></li>
                        <%-- if user logged in, show "my account"; else, show "login" and "register" menus --%>
                        <c:choose>
                            <c:when test="${not empty user}">
                                <li><a href="myaccount.jsp">my accout</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="login.jsp">login</a></li>
                                <li><a href="register.jsp">register</a></li>
                            </c:otherwise>
                        </c:choose>
                        <li><a href="contact.jsp">contact</a></li>
                    </ul>
                </div>


            </div>


            <div class="center_content">
                <div class="left_content">
                    <div class="title">
                        <span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>
                        Search
                    </div>

                    <div class="contact_form">
                        <div class="form_subtitle">search for books</div>
                        <form name="search" action="search.jsp" method="GET">
                            <div class="form_row">
                                <label class="contact"><strong>Title:</strong></label>
                                <input type="text" class="contact_input" name="title" value="${param.title}" id="title" />
                            </div>


                            <div class="form_row">
                                <label class="contact"><strong>Author:</strong></label>
                                <input type="text" class="contact_input" name="author" value="${param.author}"/>
                            </div>

                            <div class="form_row">
                                <label class="contact"><strong>Category:</strong></label>
                                <select class="contact_input" id="category_list" name="category">
                                    <option value="0">All categories</option>
                                </select>
                            </div>

                            <div class="form_row">
                                <input type="submit" class="register" value="search" />
                            </div>

                        </form>

                    </div>

                    <div id="search">
                        <div class="title">
                            <span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>
                            Result
                        </div>

                        <div class="new_products" id="content">
                            <img src="images/loading.gif" alt="Loading..." />
                        </div>
                    </div>

                    <div class="clear"></div>
                </div><!--end of left content-->

                <div class="right_content">
                    <div class="languages_box">
                        <span class="red">
                            <c:choose>
                                <c:when test="${not empty user}">
                                    Welcome, ${user.login}!
                                </c:when>
                                <c:otherwise>
                                    Welcome, Guest!
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </div>
                    <div class="login">
                        <%-- if user logged in, show "Logout" button; else, show "Login" button --%>
                        <c:choose>
                            <c:when test="${not empty user}">
                                <a class="selected" href="logout.jsp">Logout</a>
                            </c:when>
                            <c:otherwise>
                                <a class="selected" href="login.jsp">Login</a>
                            </c:otherwise>
                        </c:choose>
                    </div>


                    <div class="cart">
                        <%-- if user logged in, show cart details --%>
                        <div class="title"><span class="title_icon"><img src="images/cart.gif" alt="" title="" /></span>My cart</div>
                        <div class="home_cart_content">
                            <c:choose>
                                <c:when test="${not empty cart}">
                                <% if (request.getSession().getAttribute("cart") != null) { %>
                                    <%= ((ArrayList) request.getSession().getAttribute("cart")).size()%> x items |
                                    <span class="red">TOTAL: <fmt:formatNumber value="${total}" type="currency" currencyCode="USD" currencySymbol="$" minFractionDigits="1" maxFractionDigits="2" /></span>
                                <%} %>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="cart" value="<%= new ArrayList()%>" scope="session" />
                                    <c:set var="total" value="0" scope="session" />
                                    <% if (request.getSession().getAttribute("cart") != null) { %>
                                    <%= ((ArrayList) request.getSession().getAttribute("cart")).size()%> x items |
                                    <span class="red">TOTAL: <fmt:formatNumber value="${total}" type="currency" currencyCode="USD" currencySymbol="$" minFractionDigits="1" maxFractionDigits="2" /></span>
                                	<%} %>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <a href="cart.jsp" class="view_cart">view cart</a>
                    </div>




                    <div class="title"><span class="title_icon"><img src="images/bullet3.gif" alt="" title="" /></span>About Our Store</div>
                    <div class="about">
                        <p>
                            <img src="images/about.gif" alt="" title="" class="right" />
                            Coming soon!<br/><br/><br/><br/><br/>
                        </p>

                    </div>

                    <div class="right_box">

                        <div class="title"><span class="title_icon"><img src="images/bullet4.gif" alt="" title="" /></span>Promotions</div>
                        <div class="new_prod_box">
                            <a href="#">product name</a>
                            <div class="new_prod_bg">
                                <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                                <a href="#"><img src="images/thumb1.gif" alt="" title="" class="thumb" border="0" /></a>
                            </div>
                        </div>

                        <div class="new_prod_box">
                            <a href="#">product name</a>
                            <div class="new_prod_bg">
                                <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                                <a href="#"><img src="images/thumb2.gif" alt="" title="" class="thumb" border="0" /></a>
                            </div>
                        </div>

                        <div class="new_prod_box">
                            <a href="#">product name</a>
                            <div class="new_prod_bg">
                                <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                                <a href="#"><img src="images/thumb3.gif" alt="" title="" class="thumb" border="0" /></a>
                            </div>
                        </div>

                    </div>

                    <div class="right_box">

                        <div class="title"><span class="title_icon"><img src="images/bullet5.gif" alt="" title="" /></span>Categories</div>

                        <ul class="list" id="categories">
                            <img src="images/loading.gif" alt="Loading..." />
                        </ul>

                        <div class="title"><span class="title_icon"><img src="images/bullet6.gif" alt="" title="" /></span>Partners</div>

                        <ul class="list">
                            <li><a href="http://www.amazon.com">Amazon.com</a></li>
                            <li><a href="http://www.csscreme.com/">CSS Creme</a></li>
                            <li><a href="http://www.google.com/">Google</a></li>
                            <li><a href="http://www.netbeans.org/">Netbeans</a></li>
                        </ul>

                    </div>


                </div><!--end of right content-->




                <div class="clear"></div>
            </div><!--end of center content-->


            <div class="footer">
                <div class="left_footer"><img src="images/footer_logo.gif" alt="" title="" /><br /> <a href="http://csscreme.com/freecsstemplates/" title="free templates"><img src="images/csscreme.gif" alt="free templates" title="free templates" border="0" /></a></div>
                <div class="right_footer">
                    <a href="index.jsp">home</a>
                    <a href="about.jsp">about us</a>
                    <a href="#">services</a>
                    <a href="#">privacy policy</a>
                    <a href="contact.jsp">contact us</a>

                </div>


            </div>


        </div>

    </body>
</html>