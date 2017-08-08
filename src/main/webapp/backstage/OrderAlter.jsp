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
            <div class="crumb-list"><i class="icon-font"></i><a href="${pageContext.request.contextPath}/adminServlet?method=showUsers">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="${pageContext.request.contextPath}/adminServlet?method=showOrders">订单管理</a><span class="crumb-step">&gt;</span><span>详细信息</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="${pageContext.request.contextPath}/adminServlet?method=updateOrder" method="post" id="insertUser" name="insertUser">
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="120"><i class="require-red">*</i>订单编号：</th>
                                <td>
                                     <input class="common-text required"  name="orderNumber"  value="${requestScope.order.orderNumber}" size="10" type="text" readonly="readonly">
                                 </td>
                            </tr>

                            <tr>
                                <th width="120"><i class="require-red">*</i>预定人编号：</th>
                                <td>
                                    <input class="common-text required"  name="presetId"  value="${requestScope.order.presetId}" size="10" type="text" readonly="readonly">
                                </td>
                            </tr>

                            <tr>
                                <th width="120"><i class="require-red">*</i>下单时间：</th>
                                <td>
                                    <input class="common-text required"  name="orderTime"  value="${requestScope.order.orderTime }" size="20" type="text">
                                </td>
                            </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>订单总价：</th>
                                <td>
                                    <input class="common-text required"  name="orderTotal"  value="${requestScope.order.orderTotal }" size="20" type="text">
                                </td>
                            </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>送货地址：</th>
                                <td>
                                    <input class="common-text required"  name="deliveryAddress"  value="${requestScope.order.deliveryAddress}" size="20" type="text">
                                </td>
                            </tr>
                           <tr>
                                <th width="120"><i class="require-red">*</i>手机号：</th>
                                <td>
                                    <input class="common-text required"  name="phone"  value="${requestScope.order.phone}" size="20" type="text">
                                </td>
                            </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>订单状态：</th>
                                <td>
                                    <input class="common-text required"  name="orderStat"  value="${requestScope.order.orderStat }" size="20" type="text">
                                </td>
                             </tr>
                            <tr>
                                <th width="120"><i class="require-red">*</i>订单详情：</th>
                                <td>
                                    <input class="common-text required"  name="orderDetails"  value="${requestScope.order.orderDetails }" size="80" type="text" >
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="更新" type="submit">
                                    <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
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