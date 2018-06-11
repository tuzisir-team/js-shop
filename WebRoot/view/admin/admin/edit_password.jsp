<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include   page="../common/header.jsp" flush="true"/>
<form class="layui-form" action="" style="display:inline-block;width:70%;margin:0 auto;padding-top:5%;padding-left:5%;">
 <div class="layui-form-item">
 	<input type="hidden" name="post_type" value="admin_edit_admin_password">
    <label class="layui-form-label">旧密码:</label>
    <div class="layui-input-block">
      <input type="password" name="old_password" lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">新密码:</label>
    <div class="layui-input-block">
      <input type="password" name="new_password" lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">重复输入:</label>
    <div class="layui-input-block">
      <input type="password" name="repeat_password" lay-verify="required" placeholder="请重新输入新密码" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="demo1">立即修改</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
<script>
layui.use(['form', 'layedit', 'upload'], function(){
  var $ = layui.jquery,
  upload = layui.upload
  ,form = layui.form;
  //自定义验证规则
	form.verify({
	  positiveNumber: function(value){
	    if(value <= 0){
	      return '必须为正数';
	    }
	  }
	});
  //监听提交
  form.on('submit(demo1)', function(data){
    var url = 'edit_password';
    var old_password = data.field.old_password;
    var new_password = data.field.new_password;
    var repeat_password = data.field.repeat_password;
    if(new_password != repeat_password){
    	layer.msg('两次输入的密码不一样', {icon: 0});
    }else{
     	is_ok("${pageContext.request.contextPath}/route", data.field, 'editPasswordReturn');
    }
    return false;
  });
});

var editPasswordReturn = function (returnData) {
	console.log(returnData);
	if (returnData.code == 200) {
		layer.msg(returnData.msg,{icon: 1},function () {
			top.location.href = "${pageContext.request.contextPath}/route?get_type=admin_unlogin";
		});		
	} else {
		layer.msg(returnData.msg);
	}
}
//关闭当iframe
function quiet(){
  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
  window.parent.location.reload(true);
  parent.layer.close(index); //再执行关闭
}
</script>
