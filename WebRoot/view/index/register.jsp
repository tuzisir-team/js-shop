<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>注册</title>
	<link rel="stylesheet" type="text/css"href="../../static/css/index/reset.css">
	<link rel="stylesheet" type="text/css"href="../../static/css/index/main.css">
</head>
<body>
<div class="headerBar">
	<div class="logoBar red_logo">
		<div class="comWidth">
			<div class="logo fl">
				<a href="#"><img src="./images/icon/logo.png" alt="慕课网"></a>
			</div>
			<h3 class="welcome_title">欢迎注册</h3>
		</div>
	</div>
</div>
<div class="regBox clearfix">
	<div class="login_cont">
		<form action="UserServlet" method="POST">
			<ul class="login">
				<input type="hidden" value="register" name="postType">
				<li class="mb_10"><span class="reg_item"><i>*</i>账户名：</span><div class="input_item"><input name="user_name" type="text" class="login_input user_icon"></div></li>
				<li class="mb_10"><span class="reg_item"><i>*</i>密码：</span><div class="input_item"><input name="user_password" type="password" class="login_input user_icon"></div></li>
				<li class="mb_10"><span class="reg_item"><i>*</i>确认密码</span><div class="input_item"><input name="password_again" type="text" class="login_input user_icon"></div></li>
				<li class="mb_10"><span class="reg_item"><i>*</i>电话</span><div class="input_item"><input name="user_phone" type="text" class="login_input user_icon"></div></li>
				<li class="mb_10"><span class="reg_item"><i>*</i>邮箱地址</span><div class="input_item"><input name="user_email" type="text" class="login_input user_icon"></div></li>
				<li class="mb_10"><span class="reg_item"><i>*</i>收货地址</span><div class="input_item"><input name="user_address" type="text" class="login_input user_icon"></div></li>
				<li class="autoLogin"><span class="reg_item">&nbsp;</span><input type="checkbox" id="t1" class="checked"><label for="t1">我同意协议条款</label></li>
				<li><span class="reg_item">&nbsp;</span></li>
				<button class="login_btn">注册</button>
			</ul>
		</form>
	</div>
</div>
<div class="moddle_25px"></div>
<div class="footer">
	<p><a href="#">慕课简介</a><i>丨</i><a href="">慕课公告</a><i>丨</i><a href="">招纳贤士</a><i>丨</i><a href="">联系我们</a><i>丨</i>客服热线:400-675-1234</p>
	<p>Copyright@2006-2014慕课版权所有&nbsp;&nbsp;&nbsp;京ICP备789345号&nbsp;&nbsp;&nbsp;京ICP备789345号 某市公安局XX分局备案编号：112342345245</p>
	<p class="footer_pic"><a href="#"><img src="./images/banner/footer.png" alt=""></a><a href="#"><img src="./images/banner/footer.png" alt=""></a><a href="#"><img src="./images/banner/footer.png" alt=""></a><a href="#"><img src="./images/banner/footer.png" alt=""></a></p>
</div>
</body>
</html>