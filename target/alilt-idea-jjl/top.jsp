<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="net.alily.service.impl.UserServiceImpl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="header">
    <div class="w3ls-header"><!--header-one-->
        <div class="w3ls-header-right">
            <ul>
                <li class="dropdown head-dpdn">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user" aria-hidden="true"></i> 我的账户<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="login.jsp">登陆 </a></li>
                        <li><a href="signup.jsp">注册</a></li>
                        <li><a href="${pageContext.request.contextPath}/userServlet?method=showUserInfo">我的信息</a></li>
                        <li><a href="${pageContext.request.contextPath}/userServlet?method=showOrderFrom">我的订单</a></li>
                    </ul>
                </li>
                <li class="dropdown head-dpdn">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-percent" aria-hidden="true"></i> 今日优惠<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/userServlet?method=showCommodities&isQuery=dis&discount=0.99">优惠商品</a></li>
                        <li><a href="${pageContext.request.contextPath}/userServlet?method=showCommodities&isQuery=dis&discount=0.5">特价商品</a></li>
                    </ul>
                </li>
                <li class="dropdown head-dpdn">
                    <a href="help.jsp" class="dropdown-toggle"><i class="fa fa-question-circle" aria-hidden="true"></i> 帮助</a>
                </li>
            </ul>
        </div>
        <div class="clearfix"> </div>
    </div>
    <div class="header-two"><!-- header-two -->
        <div class="container">
            <div class="header-logo">
                <h1><a href="index.jsp"><span>家</span>家乐 </a></h1>
                <h6> 汇聚万物•你的购物城</h6>
            </div>
            <div class="header-search">
                <form action="${pageContext.request.contextPath}/userServlet?method=showCommodities&isQuery=query" method="post">
                    <input type="search" name="name" placeholder="搜索你所想 ..." required="">
                    <button type="submit" class="btn btn-default" aria-label="Left Align">
                        <i class="fa fa-search" aria-hidden="true"> </i>
                    </button>
                </form>
            </div>
            <div class="header-cart">
                <div class="my-account">
                    <a href="contact.jsp"><i class="fa fa-map-marker" aria-hidden="true"></i> 联系我们 </a>
                </div>
                <div class="cart">
                    <form action="${pageContext.request.contextPath}/userServlet?method=showCart" method="post" class="last">
                        <button class="w3view-cart" type="submit" name="submit" value=""><i class="fa fa-cart-arrow-down" aria-hidden="true"></i></button>
                    </form>
                </div>
                <div class="clearfix"> </div>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div><!-- //header-two -->
    <div class="header-three"><!-- header-three -->
        <div class="container">
            <div class="menu">
                <div class="cd-dropdown-wrapper">
                    <a class="cd-dropdown-trigger" href="#0">商品库</a>
                    <nav class="cd-dropdown">
                        <a href="#0" class="cd-close">Close</a>
                        <ul class="cd-dropdown-content">
                            <%
                                Map<String, List<Map<String, List<String>>>> stockingShelves;
                                stockingShelves = new UserServiceImpl().queryStockingShelves();
                                session.setAttribute("stockingShelves", stockingShelves);
                            %>
                            <c:forEach items="<%=stockingShelves%>" var="store" varStatus="vs" >
                            <li class="has-children">
                                <a href="#">${store.key}</a>
                                <ul class="cd-secondary-dropdown is-hidden">
                                    <li class="go-back"><a href="#">Menu</a></li>
                                    <li class="see-all"><a href="#">选你所选</a></li>

                                <c:forEach items="${store.value}" var="types" varStatus="cs" >
                                    <c:forEach items="${types}" var="type" varStatus="ds" >
                                    <li class="has-children">
                                        <a href="#">${type.key}</a>
                                        <ul class="is-hidden" >
                                            <li class="go-back"><a href="#">All Electronics</a></li>
                                        <c:forEach items="${type.value}" var="brand" varStatus="es" >
                                        <form action="${pageContext.request.contextPath}/userServlet?method=showCommodities" method="post">
                                            <input type="text" value="${store.key}:${type.key}:${brand}" name="condition" style = "display: none">
                                            <input type="submit" value="${brand}" style="text-align:left;border:0px;background:none;height: 15px;font-size: 12px"/>
                                        </form>
                                        </c:forEach>
                                        </ul>
                                    </li>
                                </c:forEach>
                                </c:forEach>

                                </ul> <!-- .cd-secondary-dropdown -->
                            </li> <!-- .has-children -->
                            </c:forEach>

                        </ul> <!-- .cd-dropdown-content -->
                    </nav> <!-- .cd-dropdown -->
                </div> <!-- .cd-dropdown-wrapper -->
            </div>
            <div class="move-text">
                <div class="marquee"><a href="#"> 今日全场99折 <span>部分商品有10%的折扣 | 免税 </span> <span> 15天免费送货上门</span></a></div>
                <script type="text/javascript" src="js/jquery.marquee.min.js"></script>
                <script>
                    $('.marquee').marquee({ pauseOnHover: true });
                    //@ sourceURL=pen.js
                </script>
            </div>
        </div>
    </div>
</div>
