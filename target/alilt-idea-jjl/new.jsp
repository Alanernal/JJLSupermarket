<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="net.alily.db.SqlHelper" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="net.alily.entity.Commodity" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="recommend">
    <h3 class="w3ls-title">最新商品</h3>
    <script>
    $(document).ready(function() {
        $("#owl-demo5").owlCarousel({

            autoPlay: 3000, //Set AutoPlay to 3 seconds

            items :4,
            itemsDesktop : [640,5],
            itemsDesktopSmall : [414,4],
            navigation : true

        });

    });
   </script>
    <div id="owl-demo5" class="owl-carousel">

        <%
            SqlHelper sqlHelper = null;
            ResultSet resultSet;
            List<Commodity> commodities = null;
            try{
                sqlHelper = new SqlHelper();
                String sql = "select commodityId, commodityName, thePurchasePrice, price, commodityType, brand, picture, store, number, illustrate from commodity order by commodityId desc limit 0, 10";
                resultSet = sqlHelper.query(sql);
                commodities = new ArrayList<>();
                while (resultSet.next()) {
                    Commodity commodity = new Commodity();
                    commodity.setCommodityId(resultSet.getString("commodityId"));
                    commodity.setCommodityName(resultSet.getString("commodityName"));
                    commodity.setPrice(resultSet.getBigDecimal("price"));
                    BigDecimal thePurchasePrice = resultSet.getBigDecimal("thePurchasePrice");
                    if(thePurchasePrice == null) {
                        thePurchasePrice = new BigDecimal(0);
                    }
                    commodity.setThePurchasePrice(thePurchasePrice);
                    commodity.setCommodityType(resultSet.getString("commodityType"));
                    commodity.setBrand(resultSet.getString("brand"));
                    commodity.setPicture(resultSet.getString("picture"));
                    commodity.setStore(resultSet.getString("store"));
                    commodity.setNumber(resultSet.getBigDecimal("number") + "");
                    commodity.setIllustrate(resultSet.getString("illustrate"));

                    commodities.add(commodity);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(sqlHelper != null) sqlHelper.close();
            }

        %>

    <c:forEach items="<%=commodities%>" var="commodity" varStatus="vs" >
        <div class="item">
            <div class="glry-w3agile-grids agileits">
                <div class="new-tag"><h6>${commodity.discount}<br>${commodity.status}</h6></div>
                <a href="${pageContext.request.contextPath}/userServlet?method=singleCommodity&commodityId=${commodity.commodityId}"><img src="${pageContext.request.contextPath}/upload/${commodity.picture}" alt="img"></a>
                <div class="view-caption agileits-w3layouts">
                    <h4><a href="${pageContext.request.contextPath}/userServlet?method=singleCommodity&commodityId=${commodity.commodityId}">${commodity.commodityName}</a></h4>
                    <p>${commodity.brand}·品质保障</p>
                    <h5>${commodity.thePurchasePrice}</h5>
                    <form action="${pageContext.request.contextPath}/userServlet?method=addCart" method="post" target="hiddenFrame">
                         <input type="hidden" name="commodityId" value="${commodity.commodityId}" />
                         <input type="hidden" name="commodityNumber" value="1" />
                         <input type="hidden" name="commodityName" value="${commodity.commodityName}" />
                         <input type="hidden" name="thePurchasePrice" value="${commodity.thePurchasePrice}" />
                         <button type="submit" class="w3ls-cart"><i class="fa fa-cart-plus" aria-hidden="true"></i>添加到购物车</button>
                    </form>
                    <iframe name="hiddenFrame" id="hiddenFrame" style="display:none"></iframe>
                </div>
            </div>
        </div>
    </c:forEach>

    </div>
</div>
