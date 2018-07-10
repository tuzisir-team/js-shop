<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include   page="../common/header.jsp" flush="true"/>
<div class="bread-crumbs">
  <span class="layui-breadcrumb">
  <a href="">首页</a>
  <a><cite></cite></a>
  </span>
</div>
<div class="page-concent-div">
  <h1>管理员列表</h1>
  <hr>
	<div style="display:inline-block;width:90%;margin:0 auto;">
		<div style="display:inline-block;width:90%;margin:0 auto;">
		 <a href="${pageContext.request.contextPath}/route?get_type=admin_add_admin_view" class="layui-btn add-rote" >添加管理员</a>
		</div>
	</div>
</div>
<table class="layui-table mt0">
    <thead>
    <tr>
      <th>管理员ID</th>
      <th>管理员名称</th>
      <th>管理员状态</th>
      <th>管理员角色</th>
      <th>创建时间</th>
      <th>更新时间</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    	<c:forEach items="${requestScope.viewpage.list}" var="admin">
			<tr>
				<td>${admin.adminId}</td>
				<td>${admin.adminName}</td>
				<c:choose>
				    <c:when test="${admin.adminStatus == 1}">
				    	<td>正常</td>
				    </c:when>
				    <c:when test="${admin.adminStatus == 0}">
	  			    	<td>禁用</td>
				    </c:when>
				    <c:otherwise>
				    	<td>未知</td>
				    </c:otherwise>
				</c:choose>				
				<c:choose>
				    <c:when test="${admin.adminRoleId > 0}">
				    	<td>${admin.adminRoleName}</td>
				    </c:when>
				    <c:when test="${admin.adminRoleId == 0}">
	  			    	<td>无角色</td>
				    </c:when>
				    <c:otherwise>
				    	<td>未知</td>
				    </c:otherwise>
				</c:choose>
				<td class="create-time">${admin.createTime}</td>
		    	<td class="update-time">${admin.updateTime}</td>
		    	<td>
					<div class="layui-btn-group" >
						<a href="${pageContext.request.contextPath}/route?get_type=admin_edit_admin_view&admin_id=${admin.adminId}" ><button class="layui-btn layui-btn-sm edit-role" 
				  			data-adminid="${admin.adminId}" >修改信息</button></a>
				  		<button class="layui-btn layui-btn-sm reset-admin-password" 
				  			data-adminid="${admin.adminId}" >重置密码</button>
						<button class="layui-btn layui-btn-sm layui-btn-danger del-admin" 
						    data-adminid="${admin.adminId}">删除</button>	
					    <c:choose>
						  <c:when test="${admin.adminStatus == 1}">
						    <button class="layui-btn layui-btn-sm layui-btn-danger admin-status" 
						    data-adminid="${admin.adminId}" data-adminstatus="${admin.adminStatus}">禁用</button>
						  </c:when>
						  <c:when test="${admin.adminStatus == 0}">
				  			<button class="layui-btn layui-btn-sm admin-status" 
				  			data-adminid="${admin.adminId}" data-adminstatus="${admin.adminStatus}" >启用</button>
						  </c:when>
						</c:choose>
					</div>
				</td>
		    </tr>
	    </c:forEach>
    </tbody>
  </table>
</div>
  <jsp:include page="../common/page.jsp" flush="true"/>
<script>
	layui.use('layer', function(){
      var layer = layui.layer;      
    });
	$(function(){
		timeConversion(); // 时间转换
		resetAdminPassword(); // 重置管理员密码
  	    delAdmin(); // 删除管理员
  	    changeAdminChange(); // 改变管理员状态
	});
	var resetAdminPassword = function() {
		$(".reset-admin-password").click(function() {
    		var adminId = $(this).data('adminid');
	        var data = {admin_id: adminId, post_type: 'admin_reset_admin_password'};
	        console.log(data);
	        layer.confirm('是否重置管理员密码?', {
	          btn: ['确定','取消'] //按钮
	        }, function(){
	       		is_ok("${pageContext.request.contextPath}/route", data, 'resetAdminPasswordReturn');
	        }, function(){
	        }); 
    	});
	}
	var resetAdminPasswordReturn = function (returnData) {
		console.log(returnData);
        if (returnData.code != 200) {
        	layer.msg(returnData.msg);
        } else {
	        location.reload();
        }
	}
	var changeAdminChange = function () {
		$(".admin-status").click(function() {
    		var adminId = $(this).data('adminid');
	        var adminStatus = $(this).data("adminstatus");
	        var data = {admin_id: adminId, admin_status: adminStatus, post_type: 'admin_change_admin_status'};
	        console.log(data);
	        layer.confirm('是否确认更改状态?', {
	          btn: ['确定','取消'] //按钮
	        }, function(){
	       		is_ok("${pageContext.request.contextPath}/route", data, 'adminStatusReturn');
	        }, function(){
	        }); 
    	});
	}
	
	var adminStatusReturn = function (returnData) {
		console.log(returnData);
        if (returnData.code != 200) {
        	layer.msg(returnData.msg);
        } else {
	        location.reload();
        }
	}
	
	var timeConversion = function() {
	  $(".create-time").html(timestampToTime($(".create-time").html()));
	  $(".update-time").html(timestampToTime($(".update-time").html()));
	 }
	var delAdmin = function() {
		$(".del-admin").click(function() {
    		var adminId = $(this).data('adminid');
	        var data = {admin_id: adminId, post_type: 'admin_del_admin'};
	        console.log(data);
	        layer.confirm('是否确认删除?', {
	          btn: ['确定','取消'] //按钮
	        }, function(){
	       		is_ok("${pageContext.request.contextPath}/route", data, 'delAdminReturn');
	        }, function(){
	        });
    	});
	}
	var delAdminReturn = function(returnData) {
		console.log(returnData);
        if (returnData.code != 200) {
        	layer.msg(returnData.msg);
        }
        window.location.href = "${pageContext.request.contextPath}/route?get_type=admin_admin_list";
	}
</script>