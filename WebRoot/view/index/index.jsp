<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>产品分类</title>
	<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/static/css/index/reset.css">
	<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/static/css/index/main.css">
	<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/static/css/index/common.css">
</head>
<body>
	<div class="headerBar">
		<div class="topBar">
			<div class="comWidth">
				<div class="leftArea">
					<a href="" class="collection">收藏本页</a>
				</div>
				<div class="rightArea">
					欢迎来到跨洋购物！${sessionScope.user_name}
					<c:if test="${empty sessionScope.user_name}">
				   		<a href="${pageContext.request.contextPath}/view/index/login.jsp">[登录]</a>
					</c:if>
					<a href="${pageContext.request.contextPath}/view/index/register.jsp">[免费注册]</a>
					<a href="${pageContext.request.contextPath}/route?get_type=user_unlogin">[退出]</a>
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
					<span class="shopText fl">购物车</span>
					<span class="shopNum fl">10</span>
				</div>
			</div>
		</div>
		<div class="navBox">
			<div class="comWidth">
				<div class="shopClass fl">
					<h3>全部商品分类<i></i></h3>
					<div class="shopClass_show" hidden>
							<dl class="shopClass_item">
							<dt class="b"><a href="#">手机</a> <a href="#">数码</a> <a href="#" class="alink">合约机</a></dt>
							<dd><a href="#">荣耀3X</a> <a href="#">单反</a> <a href="#">智能设备</a></dd>
							</dl>
							<dl class="shopClass_item">
							<dt class="b"><a href="#">手机</a> <a href="#">数码</a> <a href="#" class="alink">合约机</a></dt>
							<dd><a href="#">荣耀3X</a> <a href="#">单反</a> <a href="#">智能设备</a></dd>
							</dl>
							<dl class="shopClass_item">
							<dt class="b"><a href="#">手机</a> <a href="#">数码</a> <a href="#" class="alink">合约机</a></dt>
							<dd><a href="#">荣耀3X</a> <a href="#">单反</a> <a href="#">智能设备</a></dd>
							</dl>
							<dl class="shopClass_item">
							<dt class="b"><a href="#">手机</a> <a href="#">数码</a> <a href="#" class="alink">合约机</a></dt>
							<dd><a href="#">荣耀3X</a> <a href="#">单反</a> <a href="#">智能设备</a></dd>
							</dl>
							<dl class="shopClass_item">
							<dt class="b"><a href="#">手机</a> <a href="#">数码</a> <a href="#" class="alink">合约机</a></dt>
							<dd><a href="#">荣耀3X</a> <a href="#">单反</a> <a href="#">智能设备</a></dd>
							</dl>
					</div>			
				</div>
				<ul class="nav fl">
					<c:forEach items="${requestScope.goodsCategoryList}" var="GoodsCategory">
					   <li><a href="${pageContext.request.contextPath}/route?get_type=index_category_goods&goods_catecory_id=${GoodsCategory.goodsCategoryId}">
					   ${GoodsCategory.goodsCategoryName}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<div class="banner comWidth clearfix products">
		<div class="leftArea">
			<c:forEach items="${requestScope.goodsCategoryList}" var="GoodsCategory">
				<div class="leftNav">
					
						<a class="remove-a-style" href="${pageContext.request.contextPath}/route?get_type=index_category_goods&goods_catecory_id=${GoodsCategory.goodsCategoryId}">
							<h3 class="nav_title">
								${GoodsCategory.goodsCategoryName}
							</h3>
						</a>
				
				</div>
			</c:forEach>
			<div class="leftNav">
				<h3 class="nav_title">
						<a href="#">123456</a>

				</h3>
			</div>
			<div class="leftNav">
				<h3 class="nav_title">手机、数码</h3>
			</div>
			<div class="leftNav">
				<h3 class="nav_title">手机、数码</h3>
				<div class="nav_cont">
					<h3>手机通讯</h3>
					<ul class="navCont_list clear_list">
						<li><a href="#">全部手机</a></li>
						<li><a href="#">全部手机</a></li>
						<li><a href="#">全部手机</a></li>
						<li><a href="#">全部手机</a></li>
						<li><a href="#">全部手机</a></li>
						<li><a href="#">全部手机</a></li>
						<li><a href="#">全部手机</a></li>
					</ul>
				</div>
			</div>
			
		</div>
		<div class="rightArea">
			<div class="banner_bar banner_big">
				<ul class="imgBox">
					<li><a href="#"><img src="./static/img/index/banner/banner.01.png" alt="banner"></a></li>
					<li><a href="#"><img src="./static/img/index/banner/banner.02.png" alt="banner"></a></li>
				</ul>
				<div class="imgNum">
					<a href="#" class="active"></a> <a href="#"></a> <a href="#"></a> <a href="#"></a>
				</div>
			</div>
			<div class="hr_7"></div>
				<c:forEach items="${requestScope.goodsCategory}" var="GoodsCategory">
					<div class="products_title">
						<h3><a href="#">${GoodsCategory.goodsCategoryName}</a></h3>
					</div>
					<div class="products_list clearfix">
					<c:forEach items="${requestScope.goodsList}" var="Goods">
						<c:if test="${GoodsCategory.goodsCategoryId==Goods.goodsCategoryId}">
							<a href="${pageContext.request.contextPath}/route?get_type=goods_info&goods_id=${Goods.goodsId}">
							<div class="item">
								<div class="item_cont">
									<div class="img_item">
										<img src="${Goods.goodsPic}" alt="">
									</div>
									<p>商品简介：${Goods.goodsDescribe}</p>
									<p class="money">零售价：${Goods.goodsPrice}</p>
									<p class="money">剩余数量：${Goods.goodsNum}</p>
								</div>
							</div>
							</a>
						</c:if>
					</c:forEach>
					</div>
				</c:forEach>
		</div>
</div>
<div class="hr_25"></div>
<div class="footer">
	<p><a href="#">超市简介</a><i>丨</i><a href="">超市公告</a><i>丨</i><a href="">招纳贤士</a><i>丨</i><a href="">联系我们</a><i>丨</i>客服热线:200-675-5634</p>
	<p>Copyright@2006-2014本店所有&nbsp;&nbsp;&nbsp;京ICP备789345号&nbsp;&nbsp;&nbsp;石ICP备756545号 某市公安局XX分局备案编号：112342345245</p>
	<p class="footer_pic"><a href="#"><img src="./static/img/index/banner/footer.png" alt=""></a><a href="#"><img src="./static/img/index/banner/footer.png" alt=""></a><a href="#"><img src="./static/img/index/banner/footer.png" alt=""></a><a href="#"><img src="./static/img/index/banner/footer.png" alt=""></a></p>
</div>
</body>
</html>
<script>
    function to_login() {
        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath}/route',
            dataType: 'json',
            data: $("form").serialize(),
            success: function (data) {
            	console.log(data);
            	if (data.code != 200) {
            		layer.msg(data.msg);
            	} else {
            		window.location.href = "${pageContext.request.contextPath}/route?get_type=user_unlogin";
            	}
            }
        });
        return false;
    }
</script>