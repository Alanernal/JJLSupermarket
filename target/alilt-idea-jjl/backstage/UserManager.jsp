<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="${pageContext.request.contextPath}/adminServlet?method=showUsers">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">用户管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="${pageContext.request.contextPath}/adminServlet?method=showUsers" method="post">
                    <table class="search-tab">
                    <tr>
                        <th width="120">条件查询:</th>
                        <td><input class="common-text" placeholder="ID" name="inUserId"  type="text"></td>
                        <td><input class="common-text" placeholder="姓名" name="inName"  type="text"></td>
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
                        <a href="${pageContext.request.contextPath}/backstage/UserInsert.jsp"><i class="icon-font"></i>添加用户</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>ID</th>
                            <th>姓名</th>
                            <th>年龄</th>
                            <th>性别</th>
                            <th>手机</th>
                            <th>操作</th>
                        </tr>

                        <c:forEach items="${requestScope.pageBean.pageData}" var="userInfo" varStatus="vs" >
                        <tr>
                            <td>${userInfo.id}</td>
                            <td>${userInfo.name}</td>
                            <td>${userInfo.age}</td>
                            <td>${userInfo.CHSex}</td>
                            <td>${userInfo.phone}</td>
                            <td>
                                <a class="link-update" href="${pageContext.request.contextPath}/adminServlet?method=alterUser&userId=${userInfo.id}">详细信息</a>
                                <a class="link-del" href="${pageContext.request.contextPath}/adminServlet?method=deleteUser&userId=${userInfo.id}">删除</a>
                            </td>
                        </tr>
                        </c:forEach>

                      
                    </table>
                    <div class="list-page"> 
                    <a href="${pageContext.request.contextPath}/adminServlet?method=showUsers&currentPage=${requestScope.pageBean.currentPage-1}"> <-</a>
                    ${requestScope.pageBean.currentPage}/${requestScope.pageBean.totalPage}页
                    <a href="${pageContext.request.contextPath}/adminServlet?method=showUsers&currentPage=${requestScope.pageBean.currentPage+1}">-></a>
                    <a href="${pageContext.request.contextPath }/adminServlet?method=showUsers&currentPage=${requestScope.pageBean.totalPage}">末页</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>