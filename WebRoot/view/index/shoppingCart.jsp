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
	<div>
	</div>
	<div class="hr_25"></div>
	<div class="shopping_item">
		<h3 class="shopping_tit">购物车<span class="cart_message" style="line-heigth:10px;">
				<input id="allCheck" type="checkbox" value="全选" />   
			</span></h3>
		<div class="shopping_cont">
			<div class="cart_inner">
				<div class="cart_head clearfix">
					<div style="margin-left:50px;" class="cart_item tnum">选中</div>
					<div class="cart_item t_name" style="margin-left:-50px;">商品名称</div>
					<div class="cart_item t_price" style="margin-left:-200px;">单价</div>
					<div class="cart_item tnum">数量</div>
					<div class="cart_item t_subtotal">总计</div>
				</div>
			<c:forEach items="${requestScope.shoppingList}" var="GoodsOrders">
				<div class="cart_head clearfix" style="margin-left:50px;">
					<div class="cart_item tnum" style="margin-top:7px;">
						<input name="check" 
						data-totalprice="${GoodsOrders.goodsPrice * GoodsOrders.goodsNum}" 
						data-goodsorderid="${GoodsOrders.goodsOrderId}"
						type="checkbox" class="check ${GoodsOrders.goodsOrderId}" value="选中" />
					</div>
					<div style="margin-left:-50px;" class="cart_item t_name">${GoodsOrders.goodsName}</div>
					<div style="margin-left:-200px;" class="cart_item t_price">${GoodsOrders.goodsPrice/100}</div>
					<div class="cart_item tnum">${GoodsOrders.goodsNum}</div>
					<div style="width:100px;" id="zj" class="cart_item tnum zj">${GoodsOrders.goodsPrice * GoodsOrders.goodsNum/100}</div>
					<div class="cart_item tnum" style="margin-top:5px;">
						<input type="submit" data-totalprice="${GoodsOrders.goodsPrice*GoodsOrders.goodsNum/100}" data-goodsorderid="${GoodsOrders.goodsOrderId}" id="delete-cart-goods" value="删除" />
					</div>
				</div>										
			</c:forEach>
			</div>
		</div>
	</div>
	<div class="hr_25"></div>
	<div class="shopping_item">
		<h3 class="shopping_tit">订单结算</h3>
		<div class="shopping_cont padding_shop clearfix">
			<div class="leftArea" style="width:500px;font-size:20px;padding-top:20px;">
				<a href="${pageContext.request.contextPath}/route?get_type=index_address_view"><span>收货地址：</span></a>
				<div style="display:inline-block;">
					<select name="address-select" id="address-select" style="width: 100%">
						<c:forEach items="${requestScope.userAddress}" var="UserAddress">
							<c:choose>
								<c:when test="${UserAddress.userAddressStatus == 1}">
								    <option selected="selected" value="">${UserAddress.userAddressName}</option>						
								</c:when>
								<c:otherwise>
								    <option value="">${UserAddress.userAddressName}</option>						
								</c:otherwise>
							</c:choose>
						</c:forEach>
	                </select>
				</div>
			</div>
			<div class="rightArea">
				<div class="cart_count fr">
					<div class="cart_rmb">
						<i>总计:</i><span id="total_price">0</span>
					</div>
					<div class="cart_btnBox">
						<span class="tj">
							<input type="button" class="cart_btn submit-order" value="提交订单"/>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
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
var checkGoodsOrderId={};
var totalGoodsOrderNum=0;
layui.use('layer', function(){
      var layer = layui.layer;      
    });
	$(function() {
		addCart();
		delCartGoods(); // 删除购物车里面的商品
		submitOrder(); // 提交订单
	});
	// 提交订单
	var submitOrder = function () {
		$(".submit-order").click(function () {
			var goodsOrderIdStr="";
			// 获取商品订单id
			var goodsOrderIds = checkGoodsOrderId;
			if (goodsOrderIds == {}) {
				alert("请选中商品");return;
			}
			for (var item in goodsOrderIds) {
			     goodsOrderIdStr += item+",";
			}
			if (goodsOrderIdStr.length != 0) {
				goodsOrderIdStr = goodsOrderIdStr.substring(0,goodsOrderIdStr.length-1);
			}
			// 获取地址
			var address = $('#address-select option:selected').text();
			if (address.length == 0) {
				alert("地址不能为空");return;
			}
			// 获取订单总数
			if (totalGoodsOrderNum == 0) {
				alert("请选中商品");return;
			}
			// 获取总价格
			if ( $("#total_price").text() <= 0) {
				alert("请选中商品");return;
			}
			var data = {"post_type":"index_add_order","goods_order_id_str":goodsOrderIdStr,"user_address_name":address,"order_total":$("#total_price").html()};
			console.log(data);
			is_ok("${pageContext.request.contextPath}/route",data,"addOrderReturn");
		});
	}
	var addOrderReturn = function (returnData) {
		alert(returnData.msg);
		if (returnData.code == 200) {
		window.location.href = "${pageContext.request.contextPath}/route?get_type=user_goods_index";return;	
		}
		location.reload();
	}
	// 删除购物车商品
	var delCartGoods = function () {
		$("#delete-cart-goods").click(function () {
			var data = {goods_order_id:$(this).data('goodsorderid'),post_type:"index_del_cart_goods"};
			is_ok("${pageContext.request.contextPath}/route",data,"delCartGoodsReturn");
		});
	}
	// 删除购物车商品回调函数
	var delCartGoodsReturn = function(returnData) {
		if (returnData.code == 200) {
			// 判断此商品是否被选中，如果被选中减掉总支付价格
			if ($("."+$(this).data('goodsorderid')).is(':checked')) {
				total_price -= $(this).data('totalprice');
				$("#total_price").text(total_price);
				delete checkGoodsOrderId[$(this).data("goodsorderid")]
			}
			alert(returnData.msg);
	        location.reload();			
		} else {
			alert(returnData.msg);
		}
	}
	var addCart = function () {
		$(".add_cart").click(function(){
			var data = {user_address_name:$("#user_address_name").val(),post_type:"user_address"};
			console.log(data);
			is_ok("${pageContext.request.contextPath}/route",data,"addCartReturn");
		});
	}
	$("#allCheck").click(function(){
		checkGoodsOrderId = {};
		var a = document.getElementById("allCheck");
		var b = document.getElementsByName("check");
		total=0;
		if(a.checked){
			for(var i = 0; i < b.length; i++){
				b[i].checked = true;
			}
			$("input[name='check']").each(function(j,item){
			    total += $(this).data("totalprice");
			    var goodsOrderId = $(this).data("goodsorderid");
			    checkGoodsOrderId[goodsOrderId] = goodsOrderId;
			    totalGoodsOrderNum ++;
			 });
			 console.log(checkGoodsOrderId);
			 total = total/100;			
		}else{
			for(var i = 0; i < b.length; i++){
				b[i].checked = false;
			}
			totalGoodsOrderNum = 0;
			total = 0;
		}
		$("#total_price").text(total);
	});
	
	$(".check").click(function() {
		goodsOrderId = $(this).data('goodsorderid');
		total_price=parseFloat($("#total_price").text());
		var price=$(this).data("totalprice")/100;
		if($(this).is(':checked')){
			total_price+=price;
			checkGoodsOrderId[goodsOrderId] = goodsOrderId;
			totalGoodsOrderNum++;
		}else{
			delete checkGoodsOrderId[goodsOrderId]
			totalGoodsOrderNum--;
			total_price-=price;
		}
		console.log(checkGoodsOrderId);
		$("#total_price").text(total_price);
	});
	$(".price").each(function(){
		$(this).attr("checked",false);
	});
</script>