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
  <h1>邮件模板列表</h1>
  <hr>
	<div style="display:inline-block;width:90%;margin:0 auto;">
	 <a href="${pageContext.request.contextPath}/view/admin/email/add_email_template_view.jsp" class="layui-btn add-email-templete" >添加邮件模板</a>
	</div>
</div>
<table class="layui-table mt0">
    <thead>
    <tr>
      <th>模板ID</th>
      <th>模板标题</th>
      <th>模板文字</th>
      <th>模板类型</th>
      <th>模板状态</th>
      <th>创建时间</th>
      <th>更新时间</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    	<c:forEach items="${requestScope.viewpage.list}" var="emailTemplate">
		    <tr>
	       		<td>${emailTemplate.emailTemplateId}</td>
	       		<%-- <td>
	       			<img class="goods-pic" src="${emailTemplate.goodsPic}"/>
	       		</td> --%>
     			<td>${emailTemplate.emailTemplateTitle}</td>
	       		<td data-emailtemplatecontent="${emailTemplate.emailTemplateContent}" class="email-template-content">
	       			<span>点击查看</span>
       			</td>
	       		<c:choose>
				    <c:when test="${emailTemplate.emailTemplateType == 1}">
				    	<td>注册</td>
				    </c:when>
				    <c:when test="${emailTemplate.emailTemplateType == 2}">
	  			    	<td>登录</td>
				    </c:when>
				    <c:when test="${emailTemplate.emailTemplateType == 3}">
				    	<td>下单</td>
				    </c:when>
				    <c:when test="${emailTemplate.emailTemplateType == 4}">
	  			    	<td>退款</td>
				    </c:when>
				    <c:otherwise>
				    	<td>未知</td>
				    </c:otherwise>
				</c:choose>
	       		<c:choose>
				    <c:when test="${emailTemplate.emailTemplateStatus == 1}">
				    	<td>正常</td>
				    </c:when>
				    <c:when test="${emailTemplate.emailTemplateStatus == 0}">
	  			    	<td>禁用</td>
				    </c:when>
				    <c:otherwise>
				    	<td>未知</td>
				    </c:otherwise>
				</c:choose>
	  		    <td class="create-time">${emailTemplate.createTime}</td>
	  		    <td class="update-time">${emailTemplate.updateTime}</td>
				<td>
					<div class="layui-btn-group" >
						<button class="layui-btn layui-btn-xs edit-email-template" 
				  			data-emailtemplateid="${emailTemplate.emailTemplateId}" >修改信息</button>
						<button class="layui-btn layui-btn-xs layui-btn-danger del-email-template" 
						    data-emailtemplateid="${emailTemplate.emailTemplateId}">删除</button>	
					    <c:choose>
						  <c:when test="${emailTemplate.emailTemplateStatus == 1}">
						    <button class="layui-btn layui-btn-xs layui-btn-danger email-template-status" 
						    data-emailtemplateid="${emailTemplate.emailTemplateId}"
						    data-emailtemplatetype="${emailTemplate.emailTemplateType}"
						    data-emailtemplatestatus="${emailTemplate.emailTemplateStatus}">禁用</button>
						  </c:when>
						  <c:when test="${emailTemplate.emailTemplateStatus == 0}">
				  			<button class="layui-btn layui-btn-xs email-template-status" 
				  			data-emailtemplateid="${emailTemplate.emailTemplateId}" 
						    data-emailtemplatetype="${emailTemplate.emailTemplateType}"				  			
				  			data-emailtemplatestatus="${emailTemplate.emailTemplateStatus}" >启用</button>
						  </c:when>
						</c:choose>
					</div>
				</td>
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
    	emailTemplateContent();
    	timeConversion();
    	editEmailTemplate();
    	emailTemplateStatus();
    	delEmailTemplate();
    });
    
    // 删除邮件模板
    function delEmailTemplate(){
    	$(".del-email-template").click(function() {
    		var emailTemplateId = $(this).data('emailtemplateid');
	        var data = {email_template_id: emailTemplateId, post_type: 'admin_del_email_template'};
	        console.log(data);
	        layer.confirm('是否确认删除?', {
	          btn: ['确定','取消'] //按钮
	        }, function(){
	       		is_ok("${pageContext.request.contextPath}/route", data, 'delEmailTemplateReturn');
	        }, function(){
	        }); 
    	});
    }
    
    // 删除邮件模板回调函数
    function delEmailTemplateReturn(returnData) {
    	console.log(returnData);
        if (returnData.code != 200) {
        	layer.msg(returnData.msg);
        }
        window.location.href = "${pageContext.request.contextPath}/route?get_type=admin_email_template_list";
    }
    
    // 改变邮件模板状态
    function emailTemplateStatus(){
    	$(".email-template-status").click(function() {
    		var emailTemplateId = $(this).data('emailtemplateid');
	        var emailTemplateStatus = $(this).data("emailtemplatestatus");
	        var emailTemplateType = $(this).data("emailtemplatetype");
	        var data = {email_template_id: emailTemplateId, 
	        	email_template_status: emailTemplateStatus, 
	        	post_type: 'admin_change_email_template_status',
	        	email_template_type: emailTemplateType
       		};
	        console.log(data);
	        layer.confirm('是否确认更改状态?', {
	          btn: ['确定','取消'] //按钮
	        }, function(){
	       		is_ok("${pageContext.request.contextPath}/route", data, 'emailTemplateStatusReturn');
	        }, function(){
	        }); 
    	});
    }
    // 更改邮件模板状态的回调函数
    function emailTemplateStatusReturn(returnData) {
    	console.log(returnData);
        if (returnData.code != 200) {
        	layer.msg(returnData.msg);
        } else {
	        location.reload();
        }
    }
    // 修改邮件模板信息
    var editEmailTemplate = function () {
    	$(".edit-email-template").click(function () {
    		window.location.href = "${pageContext.request.contextPath}/route?get_type=admin_edit_email_template_view&email_template_id=" + $(this).data('emailtemplateid');
    	});
    };
    var timeConversion = function() {
	    $(".create-time").html(timestampToTime($(".create-time").html()));
	    $(".update-time").html(timestampToTime($(".update-time").html()));
    } 
    var emailTemplateContent = function () {
    	$(".email-template-content").click(function () {
    		layer.tips($(this).data("emailtemplatecontent"),  $(this));
    	});
    }
</script>
</body>
</html>