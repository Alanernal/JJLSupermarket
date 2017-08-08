<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
<title>Help</title>
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
	<!-- help-page -->
	<div class="help">
		<div class="container"> 
			<h3 class="w3ls-title w3ls-title1">有什么可以帮助你的么？</h3>
			<div class="header-search">
				<form action="#" method="post">
					<input type="search" name="Search" placeholder="常见问题搜索..." required="">
					<i class="fa fa-search" aria-hidden="true"> </i>
					<button type="submit" class="btn btn-default" aria-label="Left Align">
						提交
					</button>
				</form>
			</div> 
			<div class="faq-w3agile"> 
				<h5>常见问题汇总 (TOP10)</h5>
				<ul class="faq">
					<li class="item1"><a href="#" title="click here">如何注册账号？</a>
						<ul>
							<li class="subitem1"><p> 如果您还没有家家乐账号，请您到注册页面，直接注册即可。</p></li>
						</ul>
					</li>
					<li class="item3"><a href="#" title="click here">如何查看购物车中的商品？</a>
						<ul>
							<li class="subitem1"><p>在您已经登陆的情况下，直接点击右上角的购物车按钮，即可查看购物车中的商品。</p></li>
						</ul>
					</li>
					<li class="item4"><a href="#" title="click here">如何填写收货地址？</a>
						<ul>
							<li class="subitem1"><p>在购物车提交订单后，可在订单填写页面填写收货地址。</p></li>
						</ul>
					</li>
					<li class="item4"><a href="#" title="click here">我可以直接拨打客服电话下订单吗？</a>
						<ul>
							<li class="subitem1"><p>客服现在不提供购买商品的服务，所以是不可以的。</p></li>
						</ul>
					</li>
					<li class="item4"><a href="#" title="click here">谨防借家家乐名义诈骗!</a>
						<ul>
								<li class="subitem1"><p>家家乐平台及平台商家不会存在订单卡单、无效等情况；家家乐客服人员不会通过私人手机号码、400电话或短信形式告知您订单异常，更不会引导您ATM转账、QQ提供退款链接等操作。</p></li>
						</ul>
					</li>
					<li class="item4"><a href="#" title="click here">在哪里可以查询促销活动？</a>
						<ul>
							<li class="subitem1"><p>在网站右上角的今日优惠处可以查询。</p></li>
						</ul>
					</li>
					<li class="item4"><a href="#" title="click here">预售商品，定金支付和尾款支付的定义是？</a>
						<ul>
							<li class="subitem1"><p>定金是指在购买预售商品时，预先支付的一定金额，且需在拍下商品后30分钟内完成支付，定金不支持白条支付；尾款是指预售商品价格减去定金后还需支付的对应货款，尾款支持白条支付。</p></li>
						</ul>
					</li>
					<li class="item4"><a href="#" title="click here">合约机套餐费用如何收取？？</a>
						<ul>
							<li class="subitem1"><p>合约机套餐各有不同，具体费用收取可以在//help.jd.com/user/issue/244-757.html页面进行查看，也可以拨打联通：10010、电信：10000、移动：10086服务电话了解详情。</p></li>
						</ul>
					</li>
					<li class="item4"><a href="#" title="click here">礼品包装收费吗？</a>
						<ul>
							<li class="subitem1"><p>礼品服务：礼盒包装（15元）；彩纸包装（15元）；特色贺卡（3元）；其他具体详情请以商品页面为准。</p></li>
						</ul>
					</li>
					<li class="item1"><a href="#" title="click here">什么是价格保护？</a>
						<ul>
							<li class="subitem1"><p> 价格保护是指：为了更好的提升您的购物体验，即如您在家家乐商城购物后，如商品出现降价情况，在价保规则范围内，将赠送您与差额部分等值的款项、或京券、京豆。注：差额以客户实际支付金额计算。.</p></li>
						</ul>
					</li>
				</ul>
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
		</div>
	</div>
	<!-- //login-page --> 
	<!-- footer-top -->
	<jsp:include   page="tail.jsp" flush="true"/>
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