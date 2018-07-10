<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <title>超市后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js" charset="utf-8" ></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <link href="${pageContext.request.contextPath}/static/layui/css/layui.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/css/admin/login/app.css" rel="stylesheet"/>
    <link rel="shortcut icon" href="" type="image/x-icon">
    <script type="text/javascript">
      //刷新验证码
      function changeImg(){
          document.getElementById("validateCodeImg").src="${pageContext.request.contextPath}/route?get_type=getVertifCode&"+Math.random();
      }
    </script>
   
</head>
<body style="background:#f2f2f2">
<div class="login-div">
    <h1 class="login-title">超市后台管理系统</h1>
    <div class="web_login">
        <form  class="layui-form" onsubmit="return to_login()">
        	<input type="hidden" name="post_type" value="admin_login">
            <div class="layui-form-item layui-center">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input required type="text" name="admin_name" class="layui-input wd6"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input required type="password" name="admin_password" class="layui-input wd6"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">验证码</label>
                <div class="layui-input-inline">
                    <input required style="width:80px;float:left" type="text" name="captcha" class="layui-input wd6"/>
                    <img alt="验证码看不清，换一张" src="${pageContext.request.contextPath}/route?get_type=getVertifCode"
                    id="validateCodeImg" onclick="changeImg()">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-center">
                    <input type="submit" value="登 录" class="layui-btn login-button"/>
                </div>
            </div>
        </form>
    </div>
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
            		$("#validateCodeImg").click();
            	} else {
            		window.location.href = "${pageContext.request.contextPath}/route?get_type=admin_main_view";
            	}	
            }
        });
        return false;
    }
</script>

