<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
<title>Products</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Custom Theme files -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/menu.css" rel="stylesheet" type="text/css" media="all" /> <!-- menu style -->
<link href="css/animate.min.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/owl.carousel.css" rel="stylesheet" type="text/css" media="all"> <!-- carousel slider -->
<!-- //Custom Theme files -->
<!-- font-awesome icons -->
<link href="css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome icons -->
<!-- js -->
<script src="js/jquery-2.2.3.min.js"></script>
<script src="js/owl.carousel.js"></script>
<!-- //js --> 
<!-- web-fonts -->
<%--<link href='http://fonts.useso.com/css?family=Roboto+Condensed:400,300,300italic,400italic,700,700italic' rel='stylesheet' type='text/css'>--%>
<%--<link href='http://fonts.useso.com/css?family=Lovers+Quarrel' rel='stylesheet' type='text/css'>--%>
<%--<link href='http://fonts.useso.com/css?family=Offside' rel='stylesheet' type='text/css'>--%>
<%--<link href='http://fonts.useso.com/css?family=Tangerine:400,700' rel='stylesheet' type='text/css'>--%>
<!-- web-fonts --> 
<!-- scroll to fixed--> 
<script src="js/jquery-scrolltofixed-min.js" type="text/javascript"></script>
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
<!-- //scroll to fixed--> 
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
<!-- the jScrollPane script -->
<script type="text/javascript" src="js/jquery.jscrollpane.min.js"></script>
<script type="text/javascript" id="sourcecode">
	$(function()
	{
		$('.scroll-pane').jScrollPane();
	});
</script>
<!-- //the jScrollPane script -->
<script type="text/javascript" src="js/jquery.mousewheel.js"></script>
<!-- the mousewheel plugin --> 
</head>
<body>
	<!-- header -->
	<jsp:include   page="top.jsp" flush="true"/>
	<!-- //header --> 	
	<!-- products -->
	<div class="products">	 
		<div class="container">
			<div class="col-md-9 product-w3ls-right">
				<!-- breadcrumbs --> 
				<ol class="breadcrumb breadcrumb1">
					<li><a href="index.jsp">主页</a></li>
					<li class="active"><%=((String[])session.getAttribute("conditions"))[0]%></li>
					<li class="active"><%=((String[])session.getAttribute("conditions"))[1]%></li>
				</ol> 
				<div class="clearfix"> </div>
				<!-- //breadcrumbs -->
				<div class="product-top">
					<h4><%=((String[])session.getAttribute("conditions"))[2]%></h4>
					<ul> 
						<li class="dropdown head-dpdn">
							<a href="${pageContext.request.contextPath}/userServlet?method=showCommodities&currentPage=${requestScope.commoditiesBean.currentPage+1}" >></a>
						</li>
						<li class="dropdown head-dpdn">
							<a href="${pageContext.request.contextPath}/userServlet?method=showCommodities&currentPage=${requestScope.commoditiesBean.currentPage-1}" ><</a>
						</li>
					</ul> 
					<div class="clearfix"> </div>
				</div>
				<div class="products-row">

					<c:forEach items="${requestScope.commoditiesBean.pageData}" var="commodity" varStatus="ts" >
					<div class="col-md-3 product-grids"> 
						<div class="agile-products">
							<div class="new-tag"><h6>${commodity.discount}<br>${commodity.status}</h6></div>
							<a href="${pageContext.request.contextPath}/userServlet?method=singleCommodity&commodityId=${commodity.commodityId}"><img src="${pageContext.request.contextPath}/upload/${commodity.picture}" class="img-responsive" alt="img"></a>
							<div class="agile-product-text">              
								<h5><a href="single.jsp">${commodity.commodityName}</a></h5>
								<h6><del>${commodity.price}</del>${commodity.thePurchasePrice}</h6>
								<form action="${pageContext.request.contextPath}/userServlet?method=addCart" method="post" target="hiddenFrame">
									<input type="hidden" name="commodityId" value="${commodity.commodityId}" />
									<input type="hidden" name="commodityNumber" value="1" />
									<input type="hidden" name="commodityName" value="${commodity.commodityName}" />
									<input type="hidden" name="thePurchasePrice" value="${commodity.thePurchasePrice}" />
									<button type="submit" class="w3ls-cart pw3ls-cart"><i class="fa fa-cart-plus" aria-hidden="true"></i>添加到购物车</button>
								</form>
							</div>
						</div> 
					</div>
					</c:forEach>

					<div class="clearfix"> </div>
				</div>
				<!-- add-products --> 
				<div class="w3ls-add-grids w3agile-add-products">
					<a href="#"> 
						<h4>还有许多意想不到的优惠在等着您！</h4>
						<h6>赶快购物吧 <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></h6>
					</a>
				</div> 
				<!-- //add-products -->
			</div>
			<div class="col-md-3 rsidebar">
				<div class="rsidebar-top">


					<div class="sidebar-row">
						<!-- script for tabs -->
						<script type="text/javascript">
                            $(function() {

                                var menu_ul = $('.faq > li > ul'),
                                    menu_a  = $('.faq > li > a');

                                menu_ul.hide();

                                menu_a.click(function(e) {
                                    e.preventDefault();
                                    if(!$(this).hasClass('active')) {
                                        menu_a.removeClass('active');
                                        menu_ul.filter(':visible').slideUp('normal');
                                        $(this).addClass('active').next().stop(true,true).slideDown('normal');
                                    } else {
                                        $(this).removeClass('active');
                                        $(this).next().stop(true,true).slideUp('normal');
                                    }
                                });

                            });
						</script>
						<!-- script for tabs -->
					</div>





					<%
						Map<String, List<Map<String, List<String>>>> stockingShelves;
//						String store = ((String[])session.getAttribute("conditions"))[0];
//						List<Map<String, List<String>>> types_brand = ((Map<String, List<Map<String, List<String>>>>)session.getAttribute("stockingShelves")).get(store);
						stockingShelves = (Map<String, List<Map<String, List<String>>>>)session.getAttribute("stockingShelves");
					%>
					<c:forEach items="<%=stockingShelves%>" var="st" varStatus="vs" >
					<div class="sidebar-row">
						<h4>${st.key}</h4>
						<ul class="faq">
							<c:forEach items="${st.value}" var="t" varStatus="bs" >
								<c:forEach items="${t}" var="ts" varStatus="ds" >
									<li class="item1"><a href="#">${ts.key}<span class="glyphicon glyphicon-menu-down"></span></a>
										<ul>
											<c:forEach items="${ts.value}" var="b" varStatus="zs" >

												<form action="${pageContext.request.contextPath}/userServlet?method=showCommodities" method="post">
													<input type="text" value="${st.key}:${ts.key}:${b}" name="condition" style = "display: none">
													<li class="subitem1"><input type="submit" value="${b}" style="text-align:left;border:0px;background:none"/></li>
												</form>
											<%--<li class="subitem1"><a href="#">${b}</a></li>  height: 15px;font-size: 12px--%>
											</c:forEach>
										</ul>
									</li>
								</c:forEach>
							</c:forEach>
						</ul>
						<!-- script for tabs -->
						<%--<script type="text/javascript">--%>
							<%--$(function() {--%>

								<%--var menu_ul = $('.faq > li > ul'),--%>
									<%--menu_a  = $('.faq > li > a');--%>

								<%--menu_ul.hide();--%>

								<%--menu_a.click(function(e) {--%>
									<%--e.preventDefault();--%>
									<%--if(!$(this).hasClass('active')) {--%>
										<%--menu_a.removeClass('active');--%>
										<%--menu_ul.filter(':visible').slideUp('normal');--%>
										<%--$(this).addClass('active').next().stop(true,true).slideDown('normal');--%>
									<%--} else {--%>
										<%--$(this).removeClass('active');--%>
										<%--$(this).next().stop(true,true).slideUp('normal');--%>
									<%--}--%>
								<%--});--%>

							<%--});--%>
						<%--</script>--%>
						<!-- script for tabs -->
					</div>
					</c:forEach>
				</div>
				<div class="related-row">
					<h4>我们的宗旨</h4>
					<ul>
						<li><a href="products.jsp">快乐购物 健康生活</a></li>
						<li><a href="products.jsp">品质为上</a></li>
						<li><a href="products.jsp">微笑服务</a></li>
						<li><a href="products.jsp">顾客就是上帝</a></li>
						<li><a href="products.jsp">优质的商品源</a></li>
						<li><a href="products.jsp">安全的供应链</a></li>
						<li><a href="products.jsp">快速送货</a></li>
						<li><a href="products.jsp">众多折扣</a></li>
						<li><a href="products.jsp">琳琅满目的商品</a></li>
						<li><a href="products.jsp">物美价廉的购物城</a></li>
					</ul>
				</div>
			</div>
			<div class="clearfix"> </div>
			<!-- recommendations -->
			<jsp:include   page="new.jsp" flush="true"/>
			<!-- //recommendations -->
		</div>
	</div>
	<!--//products-->  
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