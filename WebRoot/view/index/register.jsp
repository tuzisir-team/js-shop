<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>注册</title>
	<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/static/css/index/reset.css">
	<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/static/css/index/main.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js" charset="utf-8" ></script>
</head>
<body>
<div class="headerBar">
	<div class="topBar">
		<div class="comWidth">
		</div>
	</div>
	<div class="logoBar red_logo">
		<div class="comWidth">
			<h3 class="welcome_title">欢迎免费注册新用户</h3>
		</div>
	</div>
</div>

<div class="regBox clearfix">
	<div class="login_cont">
		<form action="" method="POST" onsubmit="return to_login()">
			<ul class="login">
				<input type="hidden" value="user_register" name="post_type">
				<li class="mb_10"><span class="reg_item"><i>*</i>账户名：</span><div class="input_item"><input required name="user_name" type="text" class="login_input user_icon"></div></li>
				<li class="mb_10"><span class="reg_item"><i>*</i>密码：</span><div class="input_item"><input required name="user_password" type="password" class="login_input user_icon"></div></li>
				<li class="mb_10"><span class="reg_item"><i>*</i>确认密码</span><div class="input_item"><input required name="password_again" type="password" class="login_input user_icon"></div></li>
				<li class="mb_10"><span class="reg_item"><i>*</i>电话</span><div class="input_item"><input required name="user_phone" type="tel" class="login_input user_icon"></div></li>
				<li class="mb_10"><span class="reg_item"><i>*</i>邮箱地址</span><div class="input_item"><input required name="user_email" type="email" class="login_input user_icon"></div></li>
			</ul>
			<input type="submit" value="注册" class="login_btn2">
		</form>
	</div>
</div>
<div class="moddle_25px"></div>
<div class="footer">
	<p><a href="#">超市简介</a><i>丨</i><a href="">超市公告</a><i>丨</i><a href="">招纳贤士</a><i>丨</i><a href="">联系我们</a><i>丨</i>客服热线:200-675-5634</p>
	<p>Copyright@2006-2018本店所有&nbsp;&nbsp;&nbsp;京ICP备789345号&nbsp;&nbsp;&nbsp;石ICP备756545号 某市公安局XX分局备案编号：112342345245</p>
	<p class="footer_pic"><a href="#"><img src="./static/img/index/banner/footer.png" alt=""></a><a href="#"><img src="./static/img/index/banner/footer.png" alt=""></a><a href="#"><img src="./static/img/index/banner/footer.png" alt=""></a><a href="#"><img src="./static/img/index/banner/footer.png" alt=""></a></p>
</div>
</body>
</html>
<script>
//layui模块的使用
layui.use(['layer'], function () {
    var layer = layui.layer,
        $ = layui.jquery;
});
</script>
<script>
    function to_login() {
        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath}/route',
            dataType: 'json',
            data: $("form").serialize(),
            success: function (data) {
            	console.log(data);
            	if (data.code != 200) {
            		layer.msg(data.msg);
            	} else {
            		window.location.href = "${pageContext.request.contextPath}/route?get_type=user_login&user_login_status=0";
            	}
            }
        });
        return false;
    }
</script>