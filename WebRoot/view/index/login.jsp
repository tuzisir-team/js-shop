<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>登录</title>
	<link rel="stylesheet" type="text/css"href="../../static/css/index/reset.css">
	<link rel="stylesheet" type="text/css"href="../../static/css/index/main.css">
</head>
<body>
<div class="headerBar">
	<div class="logoBar login_logo">
		<div class="comWidth">
			<div class="logo fl">
				<a href="#"><img src="../../static/img/index/icon/logo.png" alt="慕课网"></a>
			</div>
			<h3 class="welcome_title">欢迎登录</h3>
		</div>
	</div>
</div>
<div class="loginBox clearfix">
	<div class="login_cont">
	<form action="" method="post">
		<ul class="login">
			<li class="l_tit">邮箱/用户名/手机号</li>
			<li class="mb_10"><input type="text" class="login_input user_icon"></li>
			<li class="l_tit">密码</li>
			<li class="mb_10"><input type="text" class="login_input user_icon"></li>
			<li class="autoLogin"><input type="checkbox" id="a1" class="checked"><label for="a1">自动登录</label></li>
		</ul>
		<input type="submit"  value="登录" class="login_btn">
	</form>
		<div class="login_partners">
			<p class="l_tit">使用合作方账号登录网站</p>
			<ul class="login_list clearfix">
				<li><a href="#">QQ</a></li>
				<li><span>丨</span></li>
				<li><a href="#">网易</a></li>
				<li><span>丨</span></li>
				<li><a href="#">腾讯微博</a></li>
				<li><span>丨</span></li>
				<li><a href="#">新浪微博</a></li>
				<li><span>丨</span></li>
			</ul>
		</div>
	</div>
	<a href="#" class="login_link fr" href="#"></a>
</div>
<div class="moddle_25px"></div>
<div class="footer">
	<p><a href="#">慕课简介</a><i>丨</i><a href="">慕课公告</a><i>丨</i><a href="">招纳贤士</a><i>丨</i><a href="">联系我们</a><i>丨</i>客服热线:400-675-1234</p>
	<p>Copyright@2006-2014慕课版权所有&nbsp;&nbsp;&nbsp;京ICP备789345号&nbsp;&nbsp;&nbsp;京ICP备789345号 某市公安局XX分局备案编号：112342345245</p>
	<p class="footer_pic"><a href="#"><img src="../../static/img/index/banner/footer.png" alt=""></a><a href="#"><img src="../../static/img/index/banner/footer.png" alt=""></a><a href="#"><img src="../../static/img/index/banner/footer.png" alt=""></a><a href="#"><img src="../../static/img/index/banner/footer.png" alt=""></a></p>
</div>
</body>
</html>