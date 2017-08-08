<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
<title>我的订单</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Custom Theme files -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/animate.min.css" rel="stylesheet" type="text/css" media="all" /><!-- animation -->
<link href="css/menu.css" rel="stylesheet" type="text/css" media="all" /> <!-- menu style -->
<!-- //Custom Theme files -->
<!-- font-awesome icons -->
<link href="css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome icons -->
<!-- js -->
<script src="js/jquery-2.2.3.min.js"></script>
<script src="js/jquery-scrolltofixed-min.js" type="text/javascript"></script><!-- fixed nav js -->
<script>
    $(document).ready(function() {

        // Dock the header to the top of the window when scrolled past the banner. This is the default behaviour.

        $('.header-two').scrollToFixed();  
        // previous summary up the page.

        var summaries = $('.summary');
        summaries.each(function(i) {
            var summary = $(summaries[i]);
            var next = summaries[i + 1];

            summary.scrollToFixed({
                marginTop: $('.header-two').outerHeight(true) + 10, 
                zIndex: 999
            });
        });
    });
</script>
<!-- //js --> 
<!-- web-fonts -->
<%--<link href='http://fonts.useso.com/css?family=Roboto+Condensed:400,300,300italic,400italic,700,700italic' rel='stylesheet' type='text/css'>--%>
<%--<link href='http://fonts.useso.com/css?family=Lovers+Quarrel' rel='stylesheet' type='text/css'>--%>
<%--<link href='http://fonts.useso.com/css?family=Offside' rel='stylesheet' type='text/css'> --%>
<!-- web-fonts -->  
<!-- start-smooth-scrolling -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
		jQuery(document).ready(function($) {
			$(".scroll").click(function(event){		
				event.preventDefault();
				$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
			});
		});
</script>
<!-- //end-smooth-scrolling -->
<!-- smooth-scrolling-of-move-up -->
	<script type="text/javascript">
		$(document).ready(function() {
		
			var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
			};
			
			$().UItoTop({ easingType: 'easeOutQuart' });
			
		});
	</script>
	<!-- //smooth-scrolling-of-move-up -->
</head>
<body>
	<!-- header -->
	<jsp:include   page="top.jsp" flush="true"/>
	<!-- //header --> 	
	<!-- login-page -->
	<div class="login-page">
		<div class="container"> 
			<h3 class="w3ls-title w3ls-title1">我的订单</h3>
			<div class="login-body">

				<c:forEach items="${requestScope.orderFroms}" var="orderFrom" varStatus="vs" >
					<form action="${pageContext.request.contextPath}/userServlet?method=cancellationOfOrder&orderNumber=${orderFrom.orderNumber}" method="post">
						<span>订单编号 ${orderFrom.orderNumber}</span>
						<input type="text" class="user" name="presetId" value="预定人ID ${orderFrom.presetId}" readonly>
						<input type="text" class="user" name="orderTotal" value="订单总额 ${orderFrom.orderTotal}" readonl>
						<input type="text" class="user" name="orderTime" value="下单时间 ${orderFrom.orderTime}" readonly>
						<input type="text" class="user" name="presetId" value="收货人姓名 ${orderFrom.name}" readonly>
						<input type="text" class="user" name="deliveryAddress" value="送货地址 ${orderFrom.deliveryAddress}" readonly>
						<input type="text" class="user" name="phone" value="联系电话 ${orderFrom.phone}" readonly>
						<input type="text" class="user" name="orderStat" value="订单状态 ${orderFrom.orderStat}" readonly>

						<span>购物明细</span>
						<c:forEach items="${orderFrom.commodityOfOrders}" var="commodity" varStatus="v">
							<input type="text" class="user" name="commodityId" value="商品编号 ${commodity.commodityId}" readonly>
							<input type="text" class="user" name="commodityName" value="商品名称 ${commodity.commodityName}" readonly>
							<input type="text" class="user" name="commoditySum" value="数量 ${commodity.commoditySum}" readonly>
							<input type="text" class="user" name="price" value="单价 ${commodity.price}" readonly>
							<input type="text" class="user" name="vipPrice" value="会员价 ${commodity.vipPrice}" readonly>
						</c:forEach>

						<input type="submit" value="取消订单">
					</form>
					<br>
					<br>
				</c:forEach>

			</div>
		</div>
	</div>
	<!-- //login-page --> 
	<!-- footer-top -->
	<jsp:include   page="tail.jsp" flush="true"/>
	<!-- cart-js -->
	<script src="js/minicart.js"></script>
	<script>
        w3ls.render();

        w3ls.cart.on('w3sb_checkout', function (evt) {
        	var items, len, i;

        	if (this.subtotal() > 0) {
        		items = this.items();

        		for (i = 0, len = items.length; i < len; i++) {
        			items[i].set('shipping', 0);
        			items[i].set('shipping2', 0);
        		}
        	}
        });
    </script>  
	<!-- //cart-js --> 	 
	<!-- menu js aim -->
	<script src="js/jquery.menu-aim.js"> </script>
	<script src="js/main.js"></script> <!-- Resource jQuery -->
	<!-- //menu js aim --> 
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/bootstrap.js"></script>
</body>
</html>