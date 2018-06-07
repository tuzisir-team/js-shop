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
					<div class="shopClass_show">
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
					<li><a href="#" class="active">数码城</a></li>
					<li><a href="#">天黑黑</a></li>
					<li><a href="#">团购</a></li>
					<li><a href="#">发现</a></li>
					<li><a href="#">二手特卖</a></li>
					<li><a href="#">名品会</a></li>
				</ul>
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
		<div class="leftArea">
			<div class="description_imgs">
				<div class="big">
					<img src="./images/banner/shouji.png" alt="">
				</div>
				<ul class="des_smimg clearfix">
					<li><a href=""><img src="./images/banner/shoujism1.png" class="active" alt=""></a></li>
					<li><a href=""><img src="./images/banner/shoujism1.png" alt=""></a></li>
					<li><a href=""><img src="./images/banner/shoujism1.png" alt=""></a></li>
					<li><a href=""><img src="./images/banner/shoujism1.png" alt=""></a></li>
					<li><a href=""><img src="./images/banner/shoujism1.png" alt=""></a></li>
				</ul>
			</div>
		</div>
		<div class="rightArea">
			<div class="des_content">
				<h3 class="des_content_tit">全网底价 Apple 苹果 iPad mini 16G wifi版 平板电脑 前白后银 MD531CH/A 银白两色生产批次不同混色发货</h3>
				<div class="dl clearfix">
					<div class="dt">慕课价</div>
					<div class="dd"><span class="des_money"><em>￥</em>999.00</span></div>
				</div>
				<div class="dl clearfix">
					<div class="dt">慕课价</div>
					<div class="dd"><span class="hg"><i class="hg_icon"></i><span>购ipad加价优惠够配件或USB充电插座</span></div>
				</div>
				<div class="des_position">
					<div class="dl clearfix">
						<div class="dt">送到</div>
						<div class="dd clearfix">
							<div class="select">
								<h3>海滨区五环内</h3><span></span>
								<ul class="show_select">
									<li>上帝</li>
									<li>五道口</li>
									<li>上帝</li>
								</ul>
							</div>
							<span class="theGoods">有货，可当日出货</span>
						</div>
					</div>
					<div class="dl clearfix">
						<div class="dt colorSelect">选择颜色</div>
						<div class="dd clearfix">
							<div class="des_item des_item_active">
								WIFI 16GB
							</div>
							<div class="des_item">
								WIFI 16GB
							</div>
							<div class="des_item">
								WIFI 16GB
							</div>
						</div>
					</div>
					<div class="dl clearfix">
						<div class="dt des_select_more">选择版本</div>
						<div class="dd clearfix">
							<div class="des_item des_item_sm des_item_active">
								WIFI 16GB
							</div>
							<div class="des_item des_item_sm">
								WIFI 16GB+3G
							</div>
							<div class="des_item des_item_sm">
								WIFI 16GB
							</div>
							<div class="des_item des_item_sm">
								WIFI 16GB
							</div>
							<div class="des_item des_item_sm">
								WIFI 16GB+3G
							</div>
							<div class="des_item des_item_sm">
								WIFI 16GB
							</div>
							<div class="des_item des_item_sm">
								WIFI 16GB+3G
							</div>
							<div class="des_item des_item_sm">
								WIFI 16GB
							</div>
							<div class="des_item des_item_sm">
								WIFI 16GB+3G
							</div>
							<div class="des_item des_item_sm">
								WIFI 16GB
							</div>
						</div>
					</div>
					<div class="dl">
						<div class="dt des_num">数量</div>
						<div class="dd clearfix">
							<div class="des_number">
								<div class="reduction">-</div>
								<div class="des_input">
									<input type="text">
								</div>
								<div class="plus">+</div>
							</div>
							<span class="xg">限购<em>9</em>件</span>
						</div>
					</div>
				</div>
				<div class="des_select">
					已选择<span>“白色丨WIFI 16G”</span>
				</div>
				<div class="shop_buy">
					<a href="#" class="shopping_btn"></a>
					<span class="line"></span>
					<a href="#" class="buy_btn"></a>
				</div>
				<div class="notes">
					注意：此商品可提供普通发票，不提供增值税发票。
				</div>
			</div>
		</div>
	</div>
</div>
<div class="hr_15"></div>
<div class="des_info comWidth clearfix">
	<div class="leftArea">
		<div class="recommend">
			<h3 class="tit">同价位</h3>
			<div class="item">
					<div class="item_cont">
						<div class="img_item">
						<a href="#"><img src="./images/banner/bottom_sm1.png" alt=""></a>
						</div>
						<p><a href="#">文字介绍文字介绍文字介绍文字介绍文字介绍文字介绍</a></p>
						<p class="money">￥888</p>
					</div>
			</div>
			<div class="item">
					<div class="item_cont">
						<div class="img_item">
						<a href="#"><img src="./images/banner/bottom_sm1.png" alt=""></a>
						</div>
						<p><a href="#">文字介绍文字介绍文字介绍文字介绍文字介绍文字介绍</a></p>
						<p class="money">￥888</p>
					</div>
			</div>
			<div class="item">
					<div class="item_cont">
						<div class="img_item">
						<a href="#"><img src="./images/banner/bottom_sm1.png" alt=""></a>
						</div>
						<p><a href="#">文字介绍文字介绍文字介绍文字介绍文字介绍文字介绍</a></p>
						<p class="money">￥888</p>
					</div>
			</div>
		</div>
		<div class="hr_15"></div>
		<div class="recommend">
			<h3 class="tit">看了还买</h3>
			<div class="item">
					<div class="item_cont">
						<div class="img_item">
						<a href="#"><img src="./images/banner/bottom_sm1.png" alt=""></a>
						</div>
						<p><a href="#">文字介绍文字介绍文字介绍文字介绍文字介绍文字介绍</a></p>
						<p class="money">￥888</p>
					</div>
			</div>
			<div class="item">
					<div class="item_cont">
						<div class="img_item">
						<a href="#"><img src="./images/banner/bottom_sm1.png" alt=""></a>
						</div>
						<p><a href="#">文字介绍文字介绍文字介绍文字介绍文字介绍文字介绍</a></p>
						<p class="money">￥888</p>
					</div>
			</div>
			<div class="item">
					<div class="item_cont">
						<div class="img_item">
						<a href="#"><img src="./images/banner/bottom_sm1.png" alt=""></a>
						</div>
						<p><a href="#">文字介绍文字介绍文字介绍文字介绍文字介绍文字介绍</a></p>
						<p class="money">￥888</p>
					</div>
			</div>
		</div>
	</div>
	<div class="rightArea">
		<div class="des_infoContent">
			<ul class="des_tit">
				<li class="active"><span>产品介绍</span></li>
				<li><span>产品评价(1234)</span></li>
			</ul>
			<div class="ad">
				<a href="#"><img src="../images/banner/ad.jpg" alt="这里是一张图片。"></a>
			</div>
			<div class="info_text">
				<div class="info_tit">
					<h3>强烈推荐</h3><h4>货比三家,还选</h4>
				</div>
				<p>地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方</p>
				<div class="hr_45"></div>
				<div class="info_tit">
					<h3>强烈推荐</h3><h4>货比三家,还选</h4>
				</div>
				<p>地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方地方会计法第三方</p>
				<div class="hr_45"></div>
				<img src="../images/banner/datu.jpg" alt="我是一张大图。" class="center">
			</div>
		</div>
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