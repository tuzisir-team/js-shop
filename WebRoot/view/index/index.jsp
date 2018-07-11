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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
	<script src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
</head>
<body>
	<div class="headerBar">
		<div class="topBar">
			<div class="comWidth">
				<div class="leftArea">
					<a class="collection"  href="javascript:void(0);" onclick="shoucang('我的网站',location.href)">
					 <span>收藏本页</span></a>
				</div>
				<div class="rightArea">
					欢迎来到跨洋购物！${sessionScope.user_name}
					<c:if test="${empty sessionScope.user_name}">
				   		<a href="${pageContext.request.contextPath}/route?get_type=user_login&user_login_status=0">[登录]</a>
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
				<div class="search_box fl">
				<form action="${pageContext.request.contextPath}/route">
					<input type="hidden" value="find_shop" name="post_type">
					<input name="shop_name" type="text" class="search_text fl">
					<input type="submit" value="搜索" class="search_btn fr">
				</form>
				</div>
				<div class="shopCar fr">
					<c:choose>
					<c:when test="${!empty sessionScope.user_name}">
						<a href="${pageContext.request.contextPath}/route?get_type=user_shopping_index">
						<span class="shopText fl">购物车</span>
						</a>			
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/route?get_type=user_login">
						<span class="shopText fl">购物车</span>
						</a>					
					</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<div class="navBox">
			<div class="comWidth">
				<div class="shopClass fl">
					<h3>全部商品分类<i></i></h3>		
				</div>
				<ul class="nav fl">
					<c:forEach items="${requestScope.goodsCategoryList}" var="GoodsCategory">
					   <li><a href="${pageContext.request.contextPath}/route?get_type=index_category_goods&goods_catecory_id=${GoodsCategory.goodsCategoryId}&goods_catecory_name=${GoodsCategory.goodsCategoryName}">
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
						<a class="remove-a-style" href="${pageContext.request.contextPath}/route?get_type=index_category_goods&goods_catecory_id=${GoodsCategory.goodsCategoryId}&goods_catecory_name=${GoodsCategory.goodsCategoryName}">
							<h3 class="nav_title">
								${GoodsCategory.goodsCategoryName}
							</h3>
						</a>
				</div>
			</c:forEach>
		</div>
	<div class="rightArea">
		<div class="banner_bar banner_big">
				<!-- <li><a href="#"><img src="./static/img/index/banner/banner.01.png" alt="banner"></a></li>
				<li><a href="#"><img src="./static/img/index/banner/banner.02.png" alt="banner"></a></li> -->
				<div class="layui-carousel" id="test10">
				<div carousel-item="">
				    <div><img src="./static/img/index/banner/banner.01.png"></div>				
				    <div><img src="//res.layui.com/images/layui/demo/1.png"></div>
				    <div><img src="//res.layui.com/images/layui/demo/5.png"></div>
				  </div>
				</div>
		</div>
		<div class="hr_7"></div>
		<c:forEach items="${requestScope.goodsCategory}" var="GoodsCategory">
			<div class="products_title">
				<h3>${GoodsCategory.goodsCategoryName}</h3>
			</div>
			<div class="products_list clearfix">
			<c:forEach items="${requestScope.goodsList}" var="Goods">
				<c:if test="${GoodsCategory.goodsCategoryId==Goods.goodsCategoryId}">
					<a href="${pageContext.request.contextPath}/route?get_type=goods_info&goods_catecory_id=${GoodsCategory.goodsCategoryId}&goods_id=${Goods.goodsId}">
					<div class="item">
						<div class="item_cont">
							<div class="img_item">
								<img src="${Goods.goodsPic}" alt="">
							</div>
							<p>商品名称：${Goods.goodsName}</p>
							<p>商品简介：${Goods.goodsDescribe}</p>
							<p class="money">零售价：${Goods.goodsPrice/100}<em>￥</em></p>
							<p class="money">剩余数量：${Goods.goodsNum}</p>
						</div>
					</div>
					</a>
				</c:if>
			</c:forEach>
			</div>
		</c:forEach>
		<c:forEach items="${requestScope.findGoods}" var="Goods">
			<a href="${pageContext.request.contextPath}/route?get_type=goods_info&goods_catecory_id=${Goods.goodsCategoryId}&goods_id=${Goods.goodsId}">
			<div class="item">
				<div class="item_cont">
					<div class="img_item">
						<img src="${Goods.goodsPic}" alt="">
					</div>
					<p>商品名称：${Goods.goodsName}</p>
					<p>商品简介：${Goods.goodsDescribe}</p>
					<p class="money">零售价：${Goods.goodsPrice/100}<em>￥</em></p>
					<p class="money">剩余数量：${Goods.goodsNum}</p>
				</div>
			</div>
			</a>
		</c:forEach>
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
<script>
		function shoucang(sTitle,sURL) 
{ 
try 
{ 
window.external.addFavorite(sURL, sTitle); 
} 
catch (e) 
{ 
try 
{ 
window.sidebar.addPanel(sTitle, sURL, ""); 
} 
catch (e) 
{ 
alert("加入收藏失败，请使用Ctrl+D进行添加"); 
} 
} 
} 
    function to_login() {
    $('.layui-carousel').css('height',345+'px');
	$('.layui-carousel').css('width',810+'px');
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
<script>
layui.use(['carousel', 'form'], function(){
  var carousel = layui.carousel
  ,form = layui.form;
  
  //常规轮播
  carousel.render({
    elem: '#test1'
    ,arrow: 'always'
  });
  
  //改变下时间间隔、动画类型、高度
  carousel.render({
    elem: '#test2'
    ,interval: 1800
    ,anim: 'fade'
    ,height: '120px'
  });
  
  //设定各种参数
  var ins3 = carousel.render({
    elem: '#test3'
  });
  //图片轮播
  carousel.render({
    elem: '#test10'
    ,width: '778px'
    ,height: '440px'
    ,interval: 5000
  });
});
</script>