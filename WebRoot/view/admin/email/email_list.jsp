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
  <h1>邮件列表</h1>
  <hr>
 <form method="post" action="${pageContext.request.contextPath}/route" class="layui-form" style="margin-bottom: 20px;">
 <div class="layui-input-inline">
 	<input type="hidden" name="post_type" value="admin_find_email">
     <input type="text" name="user_id" class="layui-input" style="width:20rem;" placeholder="请输入邮件ID" required>
 </div>
 <button type="submit" class="layui-btn">搜索</button>
 <c:if test="${isPage == 0}">
<a href="${pageContext.request.contextPath}/route?get_type=admin_email_list" type="submit" class="layui-btn">返回</a>       
</c:if>
</form>
<table class="layui-table mt0">
    <thead>
    <tr>
      <th>邮件ID</th>
      <th>收件人</th>
      <th>收件人邮箱</th>
      <th>邮件标题</th>
      <th>邮件内容</th>
      <th>邮件类型</th>
      <th>邮件状态</th>
      <th>发送时间</th>
    </tr>
    </thead>
    <tbody>
    	<c:forEach items="${requestScope.viewpage.list}" var="email">
		    <tr>
	       		<td>${email.emailId}</td>
     			<td>${email.userName}</td>
     			<td>${email.userEmail}</td>
     			<td>${email.emailTitle}</td>
	       		<td data-emailcontent="${email.emailContent}" class="email-content">
	       			<span>点击查看</span>
       			</td>
	       		<c:choose>
				    <c:when test="${email.emailType == 1}">
				    	<td>注册</td>
				    </c:when>
				    <c:when test="${email.emailType == 2}">
	  			    	<td>登录</td>
				    </c:when>
				    <c:when test="${email.emailType == 3}">
				    	<td>下单</td>
				    </c:when>
				    <c:when test="${email.emailType == 4}">
	  			    	<td>退款</td>
				    </c:when>
				    <c:otherwise>
				    	<td>未知</td>
				    </c:otherwise>
				</c:choose>
	       		<c:choose>
				    <c:when test="${email.emailStatus == 1}">
				    	<td>已发送</td>
				    </c:when>
				    <c:when test="${email.emailStatus == 0}">
	  			    	<td>禁用</td>
				    </c:when>
				    <c:otherwise>
				    	<td>未知</td>
				    </c:otherwise>
				</c:choose>
	  		    <td class="create-time">${email.createTime}</td>
		    </tr>
	    </c:forEach>
    </tbody>
  </table>
  <jsp:include page="../common/page.jsp" flush="true"/>
</div>
<script>
    layui.use('layer', function(){
      var layer = layui.layer;      
    });
    $(function() {
    	emailContent();
    	timeConversion();
    });
    
    
    var timeConversion = function() {
	    $(".create-time").html(timestampToTime($(".create-time").html()));
	    $(".update-time").html(timestampToTime($(".update-time").html()));
    } 
    var emailContent = function () {
    	$(".email-content").click(function () {
    		layer.tips($(this).data("emailcontent"),  $(this));
    	});
    }
</script>
</body>
</html>