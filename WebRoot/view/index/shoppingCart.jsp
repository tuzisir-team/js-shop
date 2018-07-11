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
				<a href="#"><img src="./static/img/index/icon/logo.png" alt="慕课网"></a>
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
				<li><span class="shopping_list_text"><em>*</em>详细地址:</span><input id="user_address_name" type="text"  class="input input_b"></li>
			</ul>
			<div class="cart_btnBox">
				<c:choose>
					<c:when test="${!empty sessionScope.user_name}">
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
	<div>
	</div>
	<div class="hr_25"></div>
	<div class="shopping_item">
		<h3 class="shopping_tit">购物车</h3>
		<div class="shopping_cont">
			<div class="cart_inner">
				<div class="cart_head clearfix">
					<div class="cart_item t_name">商品名称</div>
					<div class="cart_item t_price">单价</div>
					<div class="cart_item tnum">数量</div>
					<div class="cart_item t_subtotal">总计</div>
					<div class="cart_item tnum">购买</div>
				</div>
			<c:forEach items="${requestScope.shoppingList}" var="GoodsOrders">
				<div class="cart_head clearfix">
					<div class="cart_item t_name">${GoodsOrders.goodsName}</div>
					<div class="cart_item t_price">${GoodsOrders.goodsPrice/100}</div>
					<div class="cart_item tnum">${GoodsOrders.goodsNum}</div>
					<div id="zj" class="zj">${GoodsOrders.goodsPrice * GoodsOrders.goodsNum/100}￥</div>
					<div class="cart_item tnum">
					<input name="check" 
					data-totalprice="${GoodsOrders.goodsPrice * GoodsOrders.goodsNum}" 
					data-goodsid="${GoodsOrders.goodsId}"
					type="checkbox" class="check" value="选中" />
					</div>
				</div>
			</c:forEach>
			<div class="cart_message">
				<input id="allCheck" type="checkbox" value="全选" />全选     
				<input type="submit" id="delete" value="删除" />
			</div>
			</div>
		</div>
	</div>
	<div class="hr_25"></div>
	<div class="shopping_item">
		<h3 class="shopping_tit">订单结算</h3>
		<div class="shopping_cont padding_shop clearfix">
			<div class="leftArea">
				请选择送货地址：
				<select name="schoolId" id="schoolId" style="width: 100%">
					<option value="0">==请选择==</option>
					<c:forEach items="${requestScope.userAddress}" var="UserAddress">
					    <option value="">${UserAddress.userAddressName}</option>
					</c:forEach>
                </select>
			</div>
			<div class="rightArea">
				<div class="cart_count fr">
					<div class="cart_rmb">
						<i>总计:</i><span id="total_price">0￥</span>
					</div>
					<div class="cart_btnBox">
						<span class="tj">
							<input type="button" class="cart_btn" value="提交订单"/>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="moddle_25px"></div>
<div class="footer">
	<p><a href="#">超市简介</a><i>丨</i><a href="">超市公告</a><i>丨</i><a href="">招纳贤士</a><i>丨</i><a href="">联系我们</a><i>丨</i>客服热线:200-675-5634</p>
	<p>Copyright@2006-2018本店所有&nbsp;&nbsp;&nbsp;京ICP备789345号&nbsp;&nbsp;&nbsp;石ICP备756545号 某市公安局XX分局备案编号：112342345245</p>
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
		addCart();
		addAddress();
		console.log($("#schoolId").val());
	});
	var addCart = function () {
		$(".add_cart").click(function(){
			var data = {users_id:$(".users_id").val(),user_address_name:$("#user_address_name").val(),post_type:"user_address"};
			console.log(data);
			is_ok("${pageContext.request.contextPath}/route",data,"addCartReturn");
		});
	}
	var addAddress = function () {
		$(".tj").click(function(){
			var data = {order_total:total_price,users_id:$(".users_id").val(),user_address_name:$("#user_address_name").val(),post_type:"orders"};
			console.log(data);
			is_ok("${pageContext.request.contextPath}/route",data,"addCartReturn");
		});
	}
	$("#allCheck").click(function(){
		var a = document.getElementById("allCheck");
		var b = document.getElementsByName("check");
		total=0;
		if(a.checked){
			for(var i = 0; i < b.length; i++){
				b[i].checked = true;
			}
			$("input[name='check']").each(function(j,item){
			    total += $(this).data("totalprice");
			    good_id_list += $(this).data("totalprice") +",";
			 });
			 total = total/100;			
		}else{
			for(var i = 0; i < b.length; i++){
				b[i].checked = false;
			}
			total = 0;
		}
		$("#total_price").text(total+"￥");
	});
	$(".check").click(function() {
		total_price=parseFloat($("#total_price").text());
		var price=$(this).data("totalprice")/100;
		if($(this).is(':checked')){
			total_price+=price;
			good_id_list += $(this).data("totalprice") +",";
		}else{
			total_price-=price;
		}
		$("#total_price").text(total_price+"￥");
	});
	$(".price").each(function(){
		$(this).attr("checked",false);
	});
</script>