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
  <h1>角色列表</h1>
  <hr>
	<div style="display:inline-block;width:90%;margin:0 auto;">
		<div style="display:inline-block;width:90%;margin:0 auto;">
		 <a href="${pageContext.request.contextPath}/route?get_type=admin_add_rote_view" class="layui-btn add-rote" >添加角色</a>
		</div>
	</div>
</div>
<table class="layui-table mt0">
    <thead>
    <tr>
      <th>角色ID</th>
      <th>角色名称</th>
      <th>角色状态</th>
      <th>创建时间</th>
      <th>更新时间</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    	<c:forEach items="${requestScope.viewpage.list}" var="adminRole">
			<tr>
				<td>${adminRole.adminRoleId}</td>
				<td>${adminRole.adminRoleName}</td>
				<c:choose>
				    <c:when test="${adminRole.adminRoleStatus == 1}">
				    	<td>正常</td>
				    </c:when>
				    <c:when test="${adminRole.adminRoleStatus == 0}">
	  			    	<td>禁用</td>
				    </c:when>
				    <c:otherwise>
				    	<td>未知</td>
				    </c:otherwise>
				</c:choose>
				<td class="create-time">${adminRole.createTime}</td>
		    	<td class="update-time">${adminRole.updateTime}</td>
		    	<td>
					<div class="layui-btn-group" >
						<a href="${pageContext.request.contextPath}/route?get_type=admin_edit_rote_view&admin_role_id=${adminRole.adminRoleId}" ><button class="layui-btn layui-btn-sm edit-role" 
				  			data-adminroteid="${adminRole.adminRoleId}" >修改信息</button></a>
						<button class="layui-btn layui-btn-sm layui-btn-danger del-admin-role" 
						    data-adminroteid="${adminRole.adminRoleId}">删除</button>	
					    <c:choose>
						  <c:when test="${adminRole.adminRoleStatus == 1}">
						    <button class="layui-btn layui-btn-sm layui-btn-danger role-status" 
						    data-adminroleid="${adminRole.adminRoleId}" data-adminrolestatus="${adminRole.adminRoleStatus}">禁用</button>
						  </c:when>
						  <c:when test="${adminRole.adminRoleStatus == 0}">
				  			<button class="layui-btn layui-btn-sm role-status" 
				  			data-adminroleid="${adminRole.adminRoleId}" data-adminrolestatus="${adminRole.adminRoleStatus}" >启用</button>
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
  	    delAdminRole(); // 删除角色
  	    changeAdminRoleChange(); // 改变角色状态
	});
	var changeAdminRoleChange = function () {
		$(".role-status").click(function() {
    		var adminRoleId = $(this).data('adminroleid');
	        var adminRoleStatus = $(this).data("adminrolestatus");
	        var data = {admin_role_id: adminRoleId, admin_role_status: adminRoleStatus, post_type: 'admin_change_admin_role_status'};
	        console.log(data);
	        layer.confirm('是否确认更改状态?', {
	          btn: ['确定','取消'] //按钮
	        }, function(){
	       		is_ok("${pageContext.request.contextPath}/route", data, 'roleStatusReturn');
	        }, function(){
	        }); 
    	});
	}
	
	var roleStatusReturn = function (returnData) {
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
	var delAdminRole = function() {
		$(".del-admin-role").click(function() {
    		var adminRoteId = $(this).data('adminroteid');
	        var data = {admin_role_id: adminRoteId, post_type: 'admin_del_admin_role'};
	        console.log(data);
	        layer.confirm('是否确认删除?', {
	          btn: ['确定','取消'] //按钮
	        }, function(){
	       		is_ok("${pageContext.request.contextPath}/route", data, 'delAdminRoleReturn');
	        }, function(){
	        });
    	});
	}
	var delAdminRoleReturn = function(returnData) {
		console.log(returnData);
        if (returnData.code != 200) {
        	layer.msg(returnData.msg);
        }
        window.location.href = "${pageContext.request.contextPath}/route?get_type=admin_rote_list";
	}
</script>