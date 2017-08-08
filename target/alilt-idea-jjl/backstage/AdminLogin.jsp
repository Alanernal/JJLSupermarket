<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<!-- Custom Theme files -->
<link href="${pageContext.request.contextPath}/backstage/css/style.css" rel="stylesheet" type="text/css" media="all"/>
<!-- Custom Theme files -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta name="keywords" content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />

</head>
<body>
<div class="login">
	<h2>欢迎！</h2>
	<div class="login-top">
		<h2>管理员登陆</h2>
		<form action="${pageContext.request.contextPath}/adminServlet?method=checkLogin" method="post">
			<input type="text" placeholder="用户名" name="userName">
			<input type="password" placeholder="密码" name="userCode">
	    <div class="forgot">
	    	<a href="#">忘记密码</a>
	    	<input type="submit" value="登陆" >
	    </div>
	    </form>
	</div>
	<div class="login-bottom">
		<h3>申请注册 &nbsp;<a href="mailto:eternallove_2013@outlook.com">发送申请</a>&nbsp 请点击</h3>
	</div>
</div>	
<div class="copyright">
	<p>Copyright &copy; 2016 Mr.Zhao</p>
</div>
</body>
</html>