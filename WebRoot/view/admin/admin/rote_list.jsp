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
    	<c:forEach items="${requestScope.adminRoleList}" var="adminRole">
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
						<button class="layui-btn layui-btn-sm edit-Role" 
				  			data-adminroteid="${adminRole.adminRoleId}" >修改信息</button>
						<button class="layui-btn layui-btn-sm layui-btn-danger del-Role" 
						    data-adminroteid="${adminRole.adminRoleId}">删除</button>	
					    <c:choose>
						  <c:when test="${adminRole.adminRoleStatus == 1}">
						    <button class="layui-btn layui-btn-sm layui-btn-danger Role-status" 
						    data-adminroteid="${adminRole.adminRoleId}" data-adminrotestatus="${adminRole.adminRoleStatus}">禁用</button>
						  </c:when>
						  <c:when test="${adminRole.adminRoleStatus == 0}">
				  			<button class="layui-btn layui-btn-sm Role-status" 
				  			data-adminroteid="${adminRole.adminRoleId}" data-adminrotestatus="${adminRole.adminRoleStatus}" >启用</button>
						  </c:when>
						</c:choose>
					</div>
				</td>
		    </tr>
	    </c:forEach>
    </tbody>
  </table>
</div>
<script>
	layui.use('layer', function(){
      var layer = layui.layer;      
    });
	$(function(){
		timeConversion(); // 时间转换
	});
	var timeConversion = function() {
	  $(".create-time").html(timestampToTime($(".create-time").html()));
	  $(".update-time").html(timestampToTime($(".update-time").html()));
	 }
	
</script>