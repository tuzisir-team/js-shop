<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>购物车</title>
	<script src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/myself.js"></script>
	<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/static/css/index/reset.css">
	<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/static/css/index/main.css">
	<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/static/css/index/common.css">
	<style>
		.each-address:hover .each-address-div{
			background-color:Gainsboro;
		}
		.each-address-div {
			width:200px;height:100px;border:1px solid #ccc;background-color:WhiteSmoke;vertical-align:middle;
			    text-align:center;line-height:100px;display:inline-block;margin-left:80px;margin-top:50px;
		}
	</style>
</head>
<body>
<div class="headerBar">
	<div class="topBar">
		<div class="comWidth">
			<div class="leftArea">
				<a href="" class="collection">收藏慕课</a>
			</div>
			<div class="rightArea">
				欢迎来到跨洋购物！${sessionScope.user_name}
				<c:if test="${empty sessionScope.user_name}">
			   		<a href="${pageContext.request.contextPath}/route?get_type=user_login&user_login_status=1" target="view_window">[登录]</a>
				</c:if>
				<c:if test="${empty sessionScope.user_name}">
					<a href="${pageContext.request.contextPath}/view/index/register.jsp">[免费注册]</a>
				</c:if>
				<c:if test="${!empty sessionScope.user_name}">
					<a href="${pageContext.request.contextPath}/route?get_type=user_unlogin">[退出]</a>
				</c:if>
			</div>
		</div>
	</div>
	<div class="logoBar">
		<div class="comWidth">
			<div class="logo fl">
				<a style="color:White;font-size:30px;font-weight:bolder;font-family:'楷体';" href="${pageContext.request.contextPath}/route?get_type=user_goods_index">
				跨洋购物广场
				</a>
			</div>
			<div class="stepBox fr">
				<ul class="step_text">
					<li class="s01 active">我的购物车</li>
					<li class="s02 active">填写核对订单</li>
					<li class="s03">订单提交成功</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="shoppingCart comWidth">
	<div class="shopping_item">
		<h3 class="shopping_tit">收货地址</h3>
		<div class="shopping_cont padding_shop">
			<ul class="shopping_list">
				<li><span class="shopping_list_text"><em>*</em>详细地址:</span><input id="user_address_name" type="text" required class="input input_b"></li>
			</ul>
			<div class="cart_btnBox">
				<c:choose>
					<c:when test="${!empty sessionScope.user_id}">
						<span class="add_cart">
							<img src="./static/img/index/banner/jrdz.png">
						</span>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/route?get_type=user_login&user_login_status=1" target="view_window">
							<img src="./static/img/index/banner/jrdz.png">
						</a>					
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<div class="shopping_item">
		<h3 class="shopping_tit">所有地址</h3>
		<div class="shopping_cont padding_shop">
			<c:forEach items="${requestScope.addressList}" var="address">
				<c:choose>
					<c:when test="${address.userAddressStatus == 1}">
						<a class="each-address">
							<div class="each-address-div each-address-checked" data-useraddressid="${address.userAddressId}" style="background-color:#FF8C00;color:#fff;">
						    	${address.userAddressName}
					    	</div>
					    	<a href="#">
					    		<span class="del-address" data-useraddressid="${address.userAddressId}">删除</span>
					    	</a>					    	
						</a>
					</c:when>
					<c:otherwise>
						<a class="each-address">
							<div class="each-address-div" style="" data-useraddressid="${address.userAddressId}">
						    	${address.userAddressName}
					    	</div>
					    	<a href="#">
						    	<span class="del-address" data-useraddressid="${address.userAddressId}">删除</span>				    	
					    	</a>
						</a>			
					</c:otherwise>
				</c:choose>
				
			</c:forEach>
		</div>
	</div>
	<div>
	</div>
	<div class="hr_25"></div>
<div class="moddle_25px"></div>
<div class="footer">
	<p><a href="#">慕课简介</a><i>丨</i><a href="">慕课公告</a><i>丨</i><a href="">招纳贤士</a><i>丨</i><a href="">联系我们</a><i>丨</i>客服热线:400-675-1234</p>
	<p>Copyright@2006-2014慕课版权所有&nbsp;&nbsp;&nbsp;京ICP备789345号&nbsp;&nbsp;&nbsp;京ICP备789345号 某市公安局XX分局备案编号：112342345245</p>
	<p class="footer_pic"><a href="#"><img src="./static/img/index/banner/footer.png" alt=""></a><a href="#"><img src="./static/img/index/banner/footer.png" alt=""></a><a href="#"><img src="./static/img/index/banner/footer.png" alt=""></a><a href="#"><img src="./static/img/index/banner/footer.png" alt=""></a></p>
</div>
</body>
</html>
<script type="text/javascript">
var total_price=0;
var good_id_list="";
layui.use('layer', function(){
      var layer = layui.layer;      
    });
	$(function() {
		addAddress(); // 添加收货地址
		setAddress(); // 收货地址为默认地址
		delAddress(); // 删除收货地址
	});
	var delAddress = function () {
		$(".del-address").click(function(){
			var userAddressId = $(this).data("useraddressid");
			var data = {user_address_id:userAddressId,post_type:"index_del_address"};
			console.log(data);
			is_ok("${pageContext.request.contextPath}/route",data,"delAddressReturn");
		});
	}
	var delAddressReturn = function (returnData) {
		if (returnData.code!=200) {
			alert(returnData.msg);
			return;
		}
		location.reload();		
	}
	var setAddress = function () {
		$(".each-address-div").click(function(){
			var userAddressId = $(this).data("useraddressid");
			var data = {user_address_id:userAddressId,post_type:"index_set_address"};
			console.log(data);
			is_ok("${pageContext.request.contextPath}/route",data,"setAddressReturn");
		});
	}
	var setAddressReturn = function (returnData) {
		console.log(returnData);
		if (returnData.code != 200) {
			alert("设置默认地址失败");
			return;
		}
		location.reload();
	}
	// 添加收货地址
	var addAddress = function () {
		$(".add_cart").click(function(){
			var address = $("#user_address_name").val();
			if (address.length == 0) {
				alert("地址不能为空");
				return;
			}
			var data = {user_address_name:address,post_type:"user_address"};
			console.log(data);
			is_ok("${pageContext.request.contextPath}/route",data,"addAddressReturn");
		});
	}
	// 添加收货地址的回调函数
	var addAddressReturn = function (returnData) {
		if (returnData.code==200) {
			alert(returnData.msg);
	        location.reload();				
		}
	}
</script>