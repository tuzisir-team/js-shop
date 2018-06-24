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
  <h1>后台功能管理</h1>
  <hr>
	<div style="display:inline-block;width:90%;margin:0 auto;">
	</div>
	
</div>
<table class="layui-table mt0">
    <thead>
    <tr>
      <th>功能ID</th>
      <th>功能名称</th>
      <th>功能URL</th>
      <th>功能状态</th>
      <th>创建时间</th>
      <th>更新时间</th>
    </tr>
    </thead>
    <tbody>
    	<c:forEach items="${requestScope.oneMenusList}" var="oneMenus">
			<tr style="color:green;background-color:#FFFFE0;">
				<td>${oneMenus.adminFunctionId}</td>
				<td>${oneMenus.adminFunctionName}</td>
				<td>${oneMenus.adminFunctionUrl}</td>
				<c:choose>
				    <c:when test="${oneMenus.adminFunctionStatus == 1}">
				    	<td>正常</td>
				    </c:when>
				    <c:when test="${oneMenus.adminFunctionStatus == 0}">
	  			    	<td>禁用</td>
				    </c:when>
				    <c:otherwise>
				    	<td>未知</td>
				    </c:otherwise>
				</c:choose>
				<td class="create-time">${oneMenus.createTime}</td>
		    	<td class="update-time">${oneMenus.updateTime}</td>
		    </tr>
    		<c:forEach items="${requestScope.twoMenusList}" var="twoMenus">
		    	<c:if test="${twoMenus.pid == oneMenus.adminFunctionId}">
		    		<tr>
		    			<td>${twoMenus.adminFunctionId}</td>
						<td>${twoMenus.adminFunctionName}</td>
						<td>${twoMenus.adminFunctionUrl}</td>
						<c:choose>
						    <c:when test="${twoMenus.adminFunctionStatus == 1}">
						    	<td>正常</td>
						    </c:when>
						    <c:when test="${twoMenus.adminFunctionStatus == 0}">
			  			    	<td>禁用</td>
						    </c:when>
						    <c:otherwise>
						    	<td>未知</td>
						    </c:otherwise>
						</c:choose>
						<td class="create-time">${oneMenus.createTime}</td>
	  		    		<td class="update-time">${oneMenus.updateTime}</td>
		    		</tr>
		    	</c:if>
	    	</c:forEach>
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