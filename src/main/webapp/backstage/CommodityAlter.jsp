<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>详细信息</title>

    <script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
    <script src="${pageContext.request.contextPath}/ckeditor/samples/js/sample.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ckeditor/samples/css/samples.css">
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/backstage/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/backstage/css/main.css"/>
</head>
<body>
<div class="container clearfix">
    <div class="sidebar-wrap">
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
            <div class="crumb-list"><i class="icon-font"></i><a href="${pageContext.request.contextPath}/adminServlet?method=showUsers">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="${pageContext.request.contextPath}/adminServlet?method=showCommodity">商品管理</a><span class="crumb-step">&gt;</span><span>详细信息</span></div>
        </div>

        <div class="result-wrap">
            <div class="result-content">
                <form action="${pageContext.request.contextPath}/adminServlet?method=insertCommodity&isInsert=0" method="post" id="insertCommodity" name="insertCommodity" enctype = "multipart/form-data">
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                            <th width="120"><i class="require-red">*</i>ID：</th>
                            <td>
                                <input class="common-text required" value="${requestScope.commodity.commodityId}" name="commodityId" size="20" type="text">
                            </td>
                        </tr>

                        <tr>
                            <th width="120"><i class="require-red">*</i>商品名：</th>
                            <td>
                                <input class="common-text required" value="${requestScope.commodity.commodityName}" name="commodityName" size="20" type="text">
                            </td>
                        </tr>

                        <tr>
                            <th width="120"><i class="require-red">*</i>供应商：</th>
                            <td>
                                <input class="common-text required" value="${requestScope.commodity.source}" name="source" size="20" type="text">
                            </td>
                        </tr>

                        <tr>
                            <th width="120"><i class="require-red">*</i>成本价：</th>
                            <td>
                                <input class="common-text required" value="${requestScope.commodity.costPrice}" name="costPrice" size="20" type="text">
                            </td>
                        </tr>

                        <tr>
                            <th width="120"><i class="require-red">*</i>售价：</th>
                            <td>
                                <input class="common-text required" value="${requestScope.commodity.price}" name="price" size="20" type="text">
                            </td>
                        </tr>

                        <tr>
                            <th width="120"><i class="require-red">*</i>折扣价：</th>
                            <td>
                                <input class="common-text required" value="${requestScope.commodity.thePurchasePrice}" name="thePurchasePrice" size="20" type="text">
                            </td>
                        </tr>

                        <tr>
                            <th width="120"><i class="require-red">*</i>数量：</th>
                            <td>
                                <input class="common-text required" value="${requestScope.commodity.number}" name="number" size="20" type="text">
                            </td>
                        </tr>

                        <tr>
                            <th width="120"><i class="require-red">*</i>所属商店：</th>
                            <td>
                                <input class="common-text required" value="${requestScope.commodity.store}" name="store" size="20" type="text">
                            </td>
                        </tr>

                        <tr>
                            <th width="120"><i class="require-red">*</i>类型：</th>
                            <td>
                                <input class="common-text required" value="${requestScope.commodity.commodityType}" name="commodityType" size="20" type="text">
                            </td>
                        </tr>

                        <tr>
                            <th width="120"><i class="require-red">*</i>品牌：</th>
                            <td>
                                <input class="common-text required" value="${requestScope.commodity.brand}" name="brand" size="20" type="text">
                            </td>
                        </tr>

                        <tr>
                            <th><i class="require-red">*</i>商品图片：</th>
                            <td>
                                <input name="${requestScope.commodity.picture}"  id="photo" value="${requestScope.commodity.picture}" type="file">
                            </td>
                        </tr>

                        <tr>
                            <th>商品说明：</th>
                            <td><textarea name="illustrate" class="common-textarea" id="illustrate" cols="30" style="width: 98%;" rows="22" >${requestScope.commodity.illustrate}</textarea>
                                <script type="text/javascript">CKEDITOR.replace('illustrate', { height: '400px', width: '80%' });</script>
                            </td>
                        </tr>

                        <tr>
                            <th></th>
                            <td>
                                <input class="btn btn-primary btn6 mr10" value="更新" type="submit">
                                <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>