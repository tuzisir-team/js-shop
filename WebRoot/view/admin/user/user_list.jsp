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
  <h1>用户管理</h1>
  <hr>
<table class="layui-table mt0">
    <thead>
    <tr>
      <th>用户ID</th>
      <th>用户名称</th>
      <th>邮箱</th>
      <th>手机号</th>
      <th>用户状态</th>
      <th>注册时间</th>
      <th>更新时间</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.viewpage.list}" var="user">
	    <tr>
       		<td>${user.userId}</td>
  		    <td>${user.userName}</td>
  		    <td>${user.userEmail}</td>
  		    <td>${user.userPhone}</td>
  		    <c:choose>
			    <c:when test="${user.userStatus == 1}">
			    	<td>正常</td>
			    </c:when>
			    <c:when test="${user.userStatus == 0}">
  			    	<td>禁用</td>
			    </c:when>
			    <c:otherwise>
			    	<td>未知</td>
			    </c:otherwise>
			</c:choose>
  		    <td class="create-time">${user.createTime}</td>
  		    <td class="update-time">${user.updateTime}</td>
			<td>
			<div class="layui-btn-group" >
		  		<button class="layui-btn layui-btn-small user-address" data-userid="${user.userId}">送货地址</button>
				<button class="layui-btn layui-btn-small shopping-cart" data-userid="${user.userId}">购物车</button>     
				<c:choose>
				  <c:when test="${user.userStatus == 1}">
				    <button class="layui-btn layui-btn-small layui-btn-danger user-status" 
				    data-userid="${user.userId}" data-userstatus="${user.userStatus}">禁用</button>
				  </c:when>
				  <c:when test="${user.userStatus == 0}">
		  			<button class="layui-btn layui-btn-small user-status" 
		  			data-userid="${user.userId}" data-userstatus="${user.userStatus}" >启用</button>
				  </c:when>
				</c:choose>   
			</div>
			</td>
	    </tr>
    </c:forEach>
  </table>
	<jsp:include   page="../common/page.jsp" flush="true"/>
</div>
<script>
    $(function(){
    	userStatus();
    	shoppingCart();
    	userAddress();
    	timeConversion(); // 时间转换
    });
    var timeConversion = function() {
	    $(".create-time").html(timestampToTime($(".create-time").html()));
	    $(".update-time").html(timestampToTime($(".update-time").html()));
    }    

    layui.use('layer', function(){
      var layer = layui.layer;      
    });  
    
    // 改变用户状态
    function userStatus(){
    	$(".user-status").click(function() {
    		var userId = $(this).data('userid');
	        console.log(userId);
	        var userStatus = $(this).data("userstatus");
	        var data = {user_id: userId, user_status: userStatus, post_type: 'admin_change_user_status'};
	        console.log(data);
	        layer.confirm('是否确认更改状态?', {
	          btn: ['确定','取消'] //按钮
	        }, function(){
	       		is_ok("${pageContext.request.contextPath}/route", data, 'userStatusReturn');
	        }, function(){
	        }); 
    	});
    }
   	// 修改用户状态的返回结果
    var userStatusReturn = function(returnData) {
    	console.log(returnData);
        if (returnData.code != 200) {
        	layer.msg(returnData.msg);
        } else {
	        location.reload();
        }
    }
	 /**
	 * 查看详情
	 * @return {[type]} [description]
	 */
	function userAddress(){
		$('.user-address').click(function(){
			newView("${pageContext.request.contextPath}/route?get_type=user_address_list&user_id=" + $(this).data('userid'),
					'地址列表', ['60%', '50%']);
		}); 
	}
	
	// 购物车
	function shoppingCart() {
		$('.shopping-cart').click(function(){
			newView("${pageContext.request.contextPath}/route?get_type=admin_shopping_cart&user_id=" + $(this).data('userid'),
					'购物车清单', ['60%', '70%']);
		});
	}
</script>