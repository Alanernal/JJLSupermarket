<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/backstage/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/backstage/css/main.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/backstage/js/modernizr.min.js"></script>
</head>
<body>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <ul class="sub-menu">
                  <li><a href="${pageContext.request.contextPath}/adminServlet?method=showUsers"><i class="icon-font">&#xe005;</i>用户管理</a></li>
                  <li><a href="${pageContext.request.contextPath}/adminServlet?method=showCommodity"><i class="icon-font">&#xe005;</i>商品管理</a></li>
                  <li><a href="${pageContext.request.contextPath}/adminServlet?method=showOrders"><i class="icon-font">&#xe005;</i>订单管理</a></li>
                </ul>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="${pageContext.request.contextPath}/adminServlet?method=showUsers">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">订单管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="${pageContext.request.contextPath}/adminServlet?method=showOrders" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="120">条件查询:</th>
                            <td><input class="common-text" placeholder="订单编号" name="orderId"  type="text"></td>
                            <td><input class="common-text" placeholder="预定人手机号" name="orderPhone"  type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">

                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>订单编号</th>
                            <th>预定人编号</th>
                            <th>预定人手机号</th>
                            <th>下单时间</th>
                            <th>订单总价</th>
                            <th>订单状态</th>
                            <th>操作</th>
                        </tr>

                        <c:forEach items="${requestScope.orderPageBean.pageData}" var="orderInfo" varStatus="vs" >
                         <tr>
                            <td>${orderInfo.orderNumber }</td>
                            <td>${orderInfo.presetId}</td>
                            <td>${orderInfo.phone}</td>
                            <td>${orderInfo.orderTime}</td>
                            <td>${orderInfo.orderTotal}</td>
                            <td>${orderInfo.orderStat}</td>

                            <td>
                                <a class="link-update" href="${pageContext.request.contextPath}/adminServlet?method=orderAlter&orderNumber=${orderInfo.orderNumber}">详细信息</a>
                                <a class="link-del" href="${pageContext.request.contextPath}/adminServlet?method=orderDelete&orderNumber=${orderInfo.orderNumber}">删除</a>
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                    <div class="list-page"> 
                    <a href="${pageContext.request.contextPath}/adminServlet?method=showCommodity&currentPage=${requestScope.orderPageBean.currentPage-1}"> <-</a>
                    ${requestScope.orderPageBean.currentPage}/${requestScope.orderPageBean.totalPage}页
                    <a href="${pageContext.request.contextPath}/adminServlet?method=showCommodity&currentPage=${requestScope.orderPageBean.currentPage+1}">-></a>
                    <a href="${pageContext.request.contextPath }/adminServlet?method=showCommodity&currentPage=${requestScope.orderPageBean.totalPage}">末页</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>