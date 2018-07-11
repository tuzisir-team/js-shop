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
	<TITLE>商品介绍</TITLE>
	<script src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/myself.js"></script>
	<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/static/css/index/reset.css">
	<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/static/css/index/main.css">
	<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/static/css/index/common.css">
</head>
<body class="grey">
	<div class="headerBar">
		<div class="topBar">
			<div class="comWidth">
				<div class="leftArea">
					<a href="" class="collection">收藏本页</a>
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
				<div class="search_box fl">
					<input type="text" class="search_text fl">
					<input type="button" value="搜索" class="search_btn fr">
				</div>
				<div class="shopCar fr">
					<c:choose>
					<c:when test="${!empty sessionScope.user_name}">
						<a href="${pageContext.request.contextPath}/route?get_type=user_shopping_index">
						<span class="shopText fl">购物车</span>
						</a>			
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/route?get_type=user_login&user_login_status=1" target="view_window">
						<span class="shopText fl">购物车</span>
						</a>					
					</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
<div class="userPosition comWidth">
	<strong><a href="#">首页</a></strong>
	<span>&nbsp;&gt;&nbsp;</span>
	<a href="#">平板电脑</a>
	<span>&nbsp;&gt;&nbsp;</span>
	<a href="#">Apple苹果</a>
	<span>&nbsp;&gt;&nbsp;</span>
	<em>MD531CH/A</em>
</div>
<div class="description_info comWidth">
	<div class="description clearfix">
		<c:forEach items="${requestScope.goodsInfo}" var="Goods">
			<input type="hidden" class="goods_id" value="${Goods.goodsId}">
			<div class="leftArea">
				<div class="description_imgs">
				<div class="big">
					<img src="${Goods.goodsPic}">
				</div>
				</div>
			</div>
			<div class="rightArea">
				<div class="des_content" style="padding-top:5%;">
					<div class="dl clearfix">
						<div class="dt">商品售价:</div>
						<div class="dd"><span class="des_money"><em>￥</em>${Goods.goodsPrice/100}</span></div>
					</div>
					<div class="dl clearfix">
						<div class="dt">剩余数量:</div>
						<div class="dd"><span id="des_money">${Goods.goodsNum}</span></div>
					</div>
					<div class="des_position">
						<div class="dt">商品简介:</div>
						<div class="dd">${Goods.goodsDescribe}</div>
					</div>
					<div class="des_position">
						<div class="dt">选购数量:</div>
						<div class="dd">
						<p class="num_box">
						<a class="J_jian">-</a>
						<label><input type="text" style="text-align:center;width:60px;" 
						class="num" value="1"/></label>
						<a class="J_jia">+</a></p>
						</div>
					</div>
				</div>
			<div class="shop_buy">
				<c:choose>
					<c:when test="${!empty sessionScope.user_name}">
						<span class="add_cart">
						<img src="./static/img/index/banner/jiaru.png">
						</span>				
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/route?get_type=user_login&user_login_status=1" target="view_window">
						<img src="./static/img/index/banner/jiaru.png">
						</a>					
					</c:otherwise>
				</c:choose>
				<span class="line"></span>
				<a href="${pageContext.request.contextPath}/route?get_type=&class="shopping_btn"><img src="./static/img/index/banner/goumai.png"></a>
			</div>
			</div>
		</c:forEach>
	</div>
</div>
<div class="hr_15"></div>
<div class="des_info comWidth clearfix" style="height:722px;">
	<div class="leftArea" style="clear: both; height:100%;">
		<div class="recommend">
			<h3 class="tit">为您推荐</h3>
			<c:forEach items="${requestScope.chooseGoodsCategory}" var="Goods">
				<div class="item" style="border-top:1px solid #ccc;">
					<div class="item_cont">
						<div class="img_item">
						<a href="${pageContext.request.contextPath}/route?get_type=goods_info&goods_catecory_id=${Goods.goodsCategoryId}&goods_id=${Goods.goodsId}">
						<img src="${Goods.goodsPic}"></a>
						</div>
						<p>${Goods.goodsDescribe}</p>
						<p><em>￥</em>${Goods.goodsPrice/100}</p>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="hr_15"></div>
	</div>
	<div class="rightArea">
		<div class="des_infoContent">
			<div class="des_tit"><span><h3>产品介绍</h3></span></div>
			<c:forEach items="${requestScope.goodsInfo}" var="Goods">
				<div class="ad">
						<img src="${Goods.goodsPic}">
				</div>
				<div class="info_text">
					<div class="info_tit">
						<h3>强烈推荐</h3><h4>买到就是赚到</h4>
					</div>
					<p>${Goods.goodsDescribe}</p>
					<div class="hr_45"></div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<div class="hr_25"></div>
<div class="footer">
	<p><a href="#">超市简介</a><i>丨</i><a href="">超市公告</a><i>丨</i><a href="">招纳贤士</a><i>丨</i><a href="">联系我们</a><i>丨</i>客服热线:200-675-5634</p>
	<p>Copyright@2006-2018本店所有&nbsp;&nbsp;&nbsp;京ICP备789345号&nbsp;&nbsp;&nbsp;石ICP备756545号 某市公安局XX分局备案编号：112342345245</p>
	<p class="footer_pic"><a href="#"><img src="./static/img/index/banner/footer.png" alt=""></a><a href="#"><img src="./static/img/index/banner/footer.png" alt=""></a><a href="#"><img src="./static/img/index/banner/footer.png" alt=""></a><a href="#"><img src="./static/img/index/banner/footer.png" alt=""></a></p>
</div>
</body>
</html>
<script type="text/javascript">  
layui.use('layer', function(){
      var layer = layui.layer;      
    });
	$(function() {
		addNum();
		reduceNum();
		allNum();
		addCart();
	});
	var addCart = function () {
		$(".add_cart").click(function(){
			var data = {goods_id:$(".goods_id").val(),goods_num:$(".num").val(),post_type:"index_shopping_cart"};
			console.log(data);
			is_ok("${pageContext.request.contextPath}/route",data,"addCartReturn");
		});
	}
	var addCartReturn = function (dataReturn) {
		if (dataReturn.code == 200) {
			alert(dataReturn.msg);
		} else {
			alert(dataReturn.msg);
		}
	};
	var num =$("#des_money");
	var allNum = function(){
		$(".num").keypress(function(b){
		var keyCode = b.keyCode ? b.keyCode : b.charCode;
		if (keyCode != 0 && (keyCode < 48 || keyCode > 57) && keyCode != 8 && keyCode != 37 && keyCode != 39) {
			return false;
		} else {
			return true;
		}
	}).keyup(function(e) {
		var keyCode = e.keyCode ? e.keyCode : e.charCode;
		console.log(keyCode);
		if (keyCode != 8) {
			var numVal = parseInt($(".num").val()) || 0;
			numVal = numVal < 1 ? 1 : numVal;
			$(".num").val(numVal);
		}
	}).blur(function() {
		var numVal = parseInt($(".num").val()) || 0;
		numVal = numVal < 1 ? 1 : numVal;
		$(".num").val(numVal);
	}).bind('input propertychange', function() {  
		if($(".num").val() > Number(num.text())){
			$(".num").val(100);
		}
	});
    }
	var addNum = function(){
		$(".J_jia").click(function () {
			if($(".num").val() < Number(num.text())){
				$(".num").val(parseInt($(".num").val())+1);//.num,.为class
			}
			else{
				$(".num").val(100);
			}
		});
	}
	var reduceNum = function(){
		$(".J_jian").click(function () {
			if($(".num").val() > 1){
				$(".num").val($(".num").val()-1);
			}
			else{
				$(".num").val(1);
			}
		});
	}
</script>
