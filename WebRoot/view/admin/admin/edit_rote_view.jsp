<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
  <h1>修改管理员角色</h1>
  <hr>
</div>
<div class="page-concent-div">
  <form id="activity_form" class="layui-form" action="" method="post">
  	<input type="hidden" value="admin_edit_role" name="post_type">
  	<input type="hidden" name="admin_role_id" value="${adminRoleId}">
      <div class="layui-form-item">
	    <label class="layui-form-label">角色名称:</label>
	    <div class="layui-input-inline">
	      <input type="text" name="admin_rote_name" value="${requestScope.adminRoleName }" lay-verify="required" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
	    </div>
	  </div> 
	  <div class="layui-form-item">
	    <label class="layui-form-label">功能列表:</label>
	    <br>
	    <div class="layui-input-block">
    		<c:forEach items="${requestScope.oneMenusList}" var="oneMenus">
  				<a href="#" ><span class="oneMenu" data-functionid="${oneMenus.adminFunctionId}">${oneMenus.adminFunctionName}</span></a><br>
  				<c:forEach items="${requestScope.twoMenusList}" var="twoMenus">
			    		<c:if test="${twoMenus.pid == oneMenus.adminFunctionId}">
			    		    <c:set var="function" value=",${twoMenus.adminFunctionId},"/>
			    			<c:choose>
			    				<c:when test="${fn:contains(adminRoleJurisdiction,function)}">
 						  			<input class="admin-function ${oneMenus.adminFunctionId}" checked type="checkbox" lay-skin="primary" value="${twoMenus.adminFunctionId }" title="${twoMenus.adminFunctionName}">
			    				</c:when>
			    				<c:otherwise>
			  						<input class="admin-function ${oneMenus.adminFunctionId}" type="checkbox" lay-skin="primary" value="${twoMenus.adminFunctionId }" title="${twoMenus.adminFunctionName}">
			    				</c:otherwise>
			    			</c:choose>			    			
			    		</c:if>
		    	</c:forEach>
	  			<br>
	  			<br>
    		</c:forEach>
	    </div>
	  </div> 
	  <div class="layui-form-item">
	      <div class="layui-input-block">
	        <button class="layui-btn submit" lay-submit="" lay-filter="demo1">确认修改</button>
	          <a href="${pageContext.request.contextPath}/route?get_type=admin_rote_list"><span type="reset" class="layui-btn layui-btn-primary">返回</span></a>
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
  	var admin_role_jurisdiction="";
  	$.each($('.admin-function'),function(){
        if(this.checked){
        	admin_role_jurisdiction+=$(this).val()+",";
        }
    });
    if (admin_role_jurisdiction.length > 0) {
    	admin_role_jurisdiction = admin_role_jurisdiction.substr(0, admin_role_jurisdiction.length - 1);
    } else {
    	layer.msg("请选择角色功能。", {},function () {
    		return false;
       	});
       	return false;
    }
    data.field.admin_role_jurisdiction = admin_role_jurisdiction;
   	is_ok("${pageContext.request.contextPath}/route", data.field, 'editRoleReturn');
    return false;
  });
});

$(function() {
	classFunction();
});
// 修改角色回调函数
editRoleReturn = function (returnData) {
	console.log(returnData);
	if (returnData.code == 200) {
			layer.msg(returnData.msg, {icon: 1}, function () {
            	window.location.href = "${pageContext.request.contextPath}/route?get_type=admin_rote_list";
		    });	
	} else {
 		layer.msg(returnData.msg, function () {
       	});
	}
}
// 选择某一类的所有功能
var classFunction = function() {
	$(".oneMenu").click(function () {
		var functionId = $(this).data("functionid");
		console.log($(this).hasClass("checked"));
		if ($(this).hasClass("checked")) {
			$("."+functionId).removeAttr("checked");
			$(this).removeClass("checked");
		} else {
			$("."+functionId).attr("checked",'true');
			$(this).addClass("checked");
		}
	    form.render();
	});
}
</script>
