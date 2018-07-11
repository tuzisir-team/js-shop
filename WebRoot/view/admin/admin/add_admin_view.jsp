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
  <h1>添加管理员</h1>
  <hr>
</div>
<div class="page-concent-div">
  <form id="activity_form" class="layui-form" action="" method="post">
  	<input type="hidden" value="admin_add_admin" name="post_type">
      <div class="layui-form-item">
	    <label class="layui-form-label">管理员名称:</label>
	    <div class="layui-input-inline">
	      <input type="text" name="admin_name" lay-verify="required" placeholder="请输入管理员名称" autocomplete="off" class="layui-input">
	    </div>
	  </div> 
	  <!-- <div class="layui-form-item">
	    <label class="layui-form-label">管理员密码:</label>
	    <div class="layui-input-inline">
	      <input type="password" name="admin_password" lay-verify="required" placeholder="请输入管理员密码" autocomplete="off" class="layui-input">
	    </div>
	  </div> -->
	  <div class="layui-form-item">
	    <label class="layui-form-label">管理员角色:</label>
	    <div class="layui-input-block wd-100px">
	      <select name="admin_role_id" lay-filter="aihao" lay-verify="required">
    		<c:forEach items="${requestScope.adminRoleList}" var="adminRole">
		        <option value="${adminRole.adminRoleId}" >${adminRole.adminRoleName}</option>
    		</c:forEach>
	      </select>
	    </div>
	  </div>
	  <div class="layui-form-item">
	      <div class="layui-input-block">
	        <button class="layui-btn submit" lay-submit="" lay-filter="demo1">确认添加</button>
	          <a href="${pageContext.request.contextPath}/route?get_type=admin_admin_list"><span type="reset" class="layui-btn layui-btn-primary">返回</span></a>
	      </div>
	 </div>
   </form>
</div>
<script>
var form;
layui.use(['form', 'layedit', 'laydate'], function(){
  form = layui.form
  var layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
  
  //监听提交
  form.on('submit(demo1)', function(data){
  	console.log(data.field);
   	is_ok("${pageContext.request.contextPath}/route", data.field, 'addAdminReturn');
    return false;
  });
});
// 添加管理员回调函数
addAdminReturn = function (returnData) {
	console.log(returnData);
	if (returnData.code == 200) {
			layer.msg(returnData.msg, {icon: 1}, function () {
            	window.location.href = "${pageContext.request.contextPath}/route?get_type=admin_admin_list";
		    });	
	} else {
 		layer.msg(returnData.msg, function () {
       	});
	}
}
</script>
