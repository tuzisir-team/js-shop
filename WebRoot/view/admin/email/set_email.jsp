<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include   page="../common/header.jsp" flush="true"/>
 <style>
	.thumb {margin-left:5px; margin-top:15px; height:128px}
	#prevModal {width:100%; height:100%; text-align:center; display:none;}
	#img_prev {max-width:98%; max-height:98%; margin: 10px auto}
  </style>
<div class="bread-crumbs">
  <span class="layui-breadcrumb">
  <a href="">首页</a>
  <a><cite></cite></a>
  </span>
</div>
<div class="page-concent-div">
  <h1>Email基本信息设置</h1>
  <hr>
</div>
<div class="page-concent-div">
  <form id="activity_form" class="layui-form" action="" method="post">
  	<input type="hidden" value="admin_edit_email_info" name="post_type">
  	<input type="hidden" value="${requestScope.emailInfo.emailInfoId}" name="email_info_id">
      <div class="layui-form-item">
	    <label class="layui-form-label">邮箱地址:</label>
	    <div class="layui-input-inline">
	      <input type="text" value="${requestScope.emailInfo.fromEmail}" name="from_email" lay-verify="required" placeholder="发送邮件的的邮箱地址" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">邮箱用户名:</label>
	    <div class="layui-input-inline">
	      <input type="text" value="${requestScope.emailInfo.fromEmailUserName}" name="from_email_user_name" lay-verify="required" placeholder="发送邮件的邮箱用户名" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">邮箱密码:</label>
	    <div class="layui-input-inline">
	      <input type="password" value="${requestScope.emailInfo.fromEmailPassword}" name="from_email_password" lay-verify="required" placeholder="发送邮件的邮箱密码" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">服务器:</label>
	    <div class="layui-input-inline">
	      <input type="text" name="host" value="${requestScope.emailInfo.host}" lay-verify="required" placeholder="请输入服务器地址" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">端口:</label>
	    <div class="layui-input-inline">
	      <input type="text" name="port" value="${requestScope.emailInfo.port}" lay-verify="required|number" placeholder="请输入协议" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">协议:</label>
	    <div class="layui-input-inline">
	      <input type="text" name="protocol" value="${requestScope.emailInfo.protocol}" lay-verify="required" placeholder="请输入协议" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">超时时间:</label>
	    <div class="layui-input-inline">
	      <input type="text" value="${requestScope.emailInfo.timeout}" name="timeout" lay-verify="required|number" placeholder="请输入超时时间(毫秒)" autocomplete="off" class="layui-input">
	    </div>
	  </div>
      <div class="layui-form-item">
	    <label class="layui-form-label">是否认证:</label>
	    <div class="layui-input-block">
			<c:choose>
			    <c:when test="${requestScope.emailInfo.auth == 1}">
					<input type="checkbox" checked="" name="auth" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF">
			    </c:when>
			    <c:otherwise>
					<input type="checkbox" name="auth" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF">
			    </c:otherwise>
			</c:choose>	      
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">更新时间:</label>
	    <div class="layui-input-inline">
    	      <input type="text" disabled value="${requestScope.emailInfo.updateTime}" name="" lay-verify="required" placeholder="" autocomplete="off" class="layui-input update-time">	   
	    </div>
	  </div>
	  <div class="layui-form-item">
	      <div class="layui-input-block">
	        <button class="layui-btn submit" lay-submit="" lay-filter="demo1">确认保存</button>
	      </div>
	 </div>
   </form>
</div>
<script>
layui.use(['form', 'layedit', 'upload'], function(){
  var $ = layui.jquery,
  upload = layui.upload
  ,form = layui.form;
  	// 监听提交
	form.on('submit(demo1)', function(data){
		//console.log(JSON.stringify(data.field));
		if (data.field.auth == "on") {
			data.field.auth = 1;
		} else {
			data.field.auth = 0;
		}
		layer.confirm('是否确认修改?', {
          btn: ['确定','取消'] //按钮
        }, function(){
			is_ok("${pageContext.request.contextPath}/route", data.field, 'editEmailInfoReturn');
        }, function(){
        });
		return false;
	});
});
// 修改邮件基本信息回调
var editEmailInfoReturn = function(returnData) {
	if (returnData.code == 200) {
		layer.msg(returnData.msg, {icon: 1}, function () {
			location.reload();
	    });
	} else {
		layer.msg(returnData.msg, {icon: 2}, function () {
			location.reload();
	    });
	}
}
$(function(){
	timeConversion();
});
var timeConversion = function() {
  $(".update-time").val(timestampToTime($(".update-time").val()));
} 
</script>
