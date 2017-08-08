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
            <div class="crumb-list"><i class="icon-font"></i><a href="${pageContext.request.contextPath}/adminServlet?method=showUsers">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">商品管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="${pageContext.request.contextPath}/adminServlet?method=showCommodity" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="120">选择分类:</th>
                            <td>
                                <select name="checkStore" id="checkStore" >
                                    <option value="ALL">全部</option>
                                    <c:forEach items="${requestScope.stores}" var="store" varStatus="vs" >
                                        <option value="${store}">${store}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <select name="checkType" id="checkType" >
                                    <option value="ALL">全部</option>
                                    <c:forEach items="${requestScope.types}" var="type" varStatus="vs" >
                                        <option value="${type}">${type}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <select name="checkBrand" id="checkBrands" >
                                    <option value="ALL">全部</option>
                                    <c:forEach items="${requestScope.brands}" var="brand" varStatus="vs" >
                                        <option value="${brand}">${brand}</option>
                                    </c:forEach>
                                </select>
                            </td>

                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="${pageContext.request.contextPath}/backstage/CommodityInsert.jsp"><i class="icon-font"></i>添加商品</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>ID</th>
                            <th>商品名</th>
                            <th>售价</th>
                            <th>商店</th>
                            <th>类型</th>
                            <th>品牌</th>
                            <th>操作</th>
                        </tr>

                        <c:forEach items="${requestScope.adminPageBean.pageData}" var="commodityInfo" varStatus="vs" >
                         <tr>
                            <td>${commodityInfo.commodityId }</td>
                            <td>${commodityInfo.commodityName}</td>
                            <td>${commodityInfo.thePurchasePrice}</td>
                            <td>${commodityInfo.store}</td>
                            <td>${commodityInfo.commodityType}</td>
                            <td>${commodityInfo.brand}</td>

                            <td>
                                <a class="link-update" href="${pageContext.request.contextPath}/adminServlet?method=commodityAlter&commodityId=${commodityInfo.commodityId}">详细信息</a>
                                <a class="link-del" href="${pageContext.request.contextPath}/adminServlet?method=commodityDelete&commodityId=${commodityInfo.commodityId}">删除</a>
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                    <div class="list-page"> 
                    <a href="${pageContext.request.contextPath}/adminServlet?method=showCommodity&currentPage=${requestScope.adminPageBean.currentPage-1}"> <-</a>
                    ${requestScope.adminPageBean.currentPage}/${requestScope.adminPageBean.totalPage}页
                    <a href="${pageContext.request.contextPath}/adminServlet?method=showCommodity&currentPage=${requestScope.adminPageBean.currentPage+1}">-></a>
                    <a href="${pageContext.request.contextPath }/adminServlet?method=showCommodity&currentPage=${requestScope.adminPageBean.totalPage}">末页</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>