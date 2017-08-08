<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/backstage/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/backstage/css/main.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/backstage/js/libs/modernizr.min.js"></script>
</head>
<body>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
             <ul class="sub-menu">
                 <li><a href="${pageContext.request.contextPath}/adminServlet?method=showUsers"><i class="icon-font">&#xe005;</i>用户管理</a></li>
                 <li><a href="${pageContext.request.contextPath}/adminServlet?method=showCommodity"><i class="icon-font">&#xe005;</i>商品管理</a></li>
                 <li><a href="${pageContext.request.contextPath}/adminServlet?method=showOrders"><i class="icon-font">&#xe005;</i>订单管理</a></li>
                       
                    </ul>
                </li>
                <li>
                  
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="${pageContext.request.contextPath}/adminServlet?method=showUsers">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="${pageContext.request.contextPath}/adminServlet?method=showUsers">用户管理</a><span class="crumb-step">&gt;</span><span>添加用户</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="${pageContext.request.contextPath}/adminServlet?method=insertUser" method="post" id="insertUser" name="insertUser">
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                                <th width="120"><i class="require-red">*</i>用户名：</th>
                                <td>
                                    <input class="common-text required"  name="userName"  value="" size="20" type="text" >
                                </td>
                            </tr>
                            <tr>
                              <th width="120"><i class="require-red">*</i>密码：</th>
                                <td>
                                    <input class="common-text required"  name="userCode"  value="" size="20" type="password">
                                </td>
                            </tr>
                            <tr>
                              <th width="120"><i class="require-red">*</i>再次输入密码：</th>
                                <td>
                                    <input class="common-text required"  name="againUserCode"  value="" size="20" type="password">
                                </td>
                            </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>姓名：</th>
                                <td>
                                    <input class="common-text required"  name="name"  value="" size="20" type="text">
                                </td>
                            </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>年龄：</th>
                                <td>
                                    <input class="common-text required"  name="age"  value="" size="20" type="text">
                                </td>
                            </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>性别：</th>
                                <td>
                                    <input class="common-text required"  name="sex"  value="" size="20" type="text">
                                </td>
                            </tr>
                           <tr>
                                <th width="120"><i class="require-red">*</i>手机号：</th>
                                <td>
                                    <input class="common-text required"  name="phone"  value="" size="20" type="text">
                                </td>
                            </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>邮箱：</th>
                                <td>
                                    <input class="common-text required"  name="email"  value="" size="20" type="text">
                                </td>
                             </tr>
                            <tr>
                                 <th width="120"><i class="require-red">*</i>地址：</th>
                                 <td>
                                     <input class="common-text required"  name="address"  value="" size="20" type="text">
                                 </td>
                             </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                                    <%--<input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">--%>
                                    <a href="${pageContext.request.contextPath}/adminServlet?method=showUsers"><input class="btn btn6" value="返回" type="button"></a>
                                </td>
                            </tr>
                        </tbody></table>
                </form>
            </div>
        </div>

    </div>
    <!--/main-->
</div>
</body>
</html>