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
  <h1>商品类别</h1>
  <hr>
	<div style="display:inline-block;width:90%;margin:0 auto;">
	 <button class="layui-btn" id="add-goodscategory">添加商品类别</button>
	</div>
</div>
<table class="layui-table mt0">
    <thead>
    <tr>
      <th>商品类别ID</th>
      <th>商品类别名称</th>
      <th>商品类别状态</th>
      <th>创建时间</th>
      <th>更新时间</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.viewpage.list}" var="goodsCategory">
	    <tr>
       		<td>${goodsCategory.goodsCategoryId}</td>
  		    <td>${goodsCategory.goodsCategoryName}</td>
  		    <c:choose>
			    <c:when test="${goodsCategory.goodsCategoryStatus == 1}">
			    	<td>正常</td>
			    </c:when>
			    <c:when test="${goodsCategory.goodsCategoryStatus == 0}">
  			    	<td>禁用</td>
			    </c:when>
			    <c:otherwise>
			    	<td>未知</td>
			    </c:otherwise>
			</c:choose>
  		    <td class="create-time">${goodsCategory.createTime}</td>
  		    <td class="update-time">${goodsCategory.updateTime}</td>
			<td>
				<div class="layui-btn-group" >
					<button class="layui-btn layui-btn-small layui-btn-danger del-goods-category" 
					    data-goodscategoryid="${goodsCategory.goodsCategoryId}">删除</button>	
				    <c:choose>
					  <c:when test="${goodsCategory.goodsCategoryStatus == 1}">
					    <button class="layui-btn layui-btn-small layui-btn-danger goods-category-status" 
					    data-goodscategoryid="${goodsCategory.goodsCategoryId}" data-goodscategorystatus="${goodsCategory.goodsCategoryStatus}">禁用</button>
					  </c:when>
					  <c:when test="${goodsCategory.goodsCategoryStatus == 0}">
			  			<button class="layui-btn layui-btn-small goods-category-status" 
			  			data-goodscategoryid="${goodsCategory.goodsCategoryId}" data-goodscategorystatus="${goodsCategory.goodsCategoryStatus}" >启用</button>
					  </c:when>
					</c:choose>   
				</div>
			</td>
	    </tr>
    </c:forEach>
  </table>
  <form action="#" method="post" class="layui-form pop-up-style" id="addGoodsCategoryForm" hidden>
     <br>
     <div class="layui-form-item">
       	<label class="layui-form-label" style="width:85px;">商品分类名称</label>
       	<input type="hidden" value="admin_add_goods_category" name="post_type">
       	<div class="layui-input-inline">
             <input type="text" lay-verify="required" required id="goodsCategoryName" name="goods_category_name" class="layui-input" placeholder="请输入商品分类名称"/>
       	</div>
   	</div>
 </form>
  <jsp:include page="../common/page.jsp" flush="true"/>
</div>
<script>
    $(function(){
    	addGoodsCategory(); // 添加商品分类
    	delGoodsCategory(); // 删除商品分类
    	goodsCategoryStatus();  // 修改商品分类状态
    	timeConversion(); //时间转换
    });
    var timeConversion = function() {
	    $(".create-time").html(timestampToTime($(".create-time").html()));
	    $(".update-time").html(timestampToTime($(".update-time").html()));
    }    
    
    layui.use('layer', function(){
      var layer = layui.layer;      
    });
   
    // 添加商品分类
    function addGoodsCategory() {
    	$("#add-goodscategory").click(function() {
   	 	    layerOpen('添加商品分类', '#addGoodsCategoryForm', addGoodsCategorySend, ['添加', '取消'], ['50%', 'auto']);
    	});
    }
    
    // 发送添加商品分类的数据
    function addGoodsCategorySend(data) {
		if (!data.goods_category_name) {
        	layer.msg("商品类别名称不能为空");
		}
 	  	is_ok("${pageContext.request.contextPath}/route", data, 'delGoodsCategoryReturn');
    }
 
    // 删除商品类别状态
    function delGoodsCategory(){
    	$(".del-goods-category").click(function() {
    		var goodsCategoryId = $(this).data('goodscategoryid');
	        console.log(goodsCategoryId);
	        var data = {goods_category_id: goodsCategoryId, post_type: 'admin_del_goods_category'};
	        console.log(data);
	        layer.confirm('是否确认删除?', {
	          btn: ['确定','取消'] //按钮
	        }, function(){
	       		is_ok("${pageContext.request.contextPath}/route", data, 'delGoodsCategoryReturn');
	        }, function(){
	        }); 
    	});
    }
    
    // 改变商品类别状态
    function goodsCategoryStatus(){
    	$(".goods-category-status").click(function() {
    		var goodsCategoryId = $(this).data('goodscategoryid');
	        console.log(goodsCategoryId);
	        var goodsCategoryStatus = $(this).data("goodscategorystatus");
	        var data = {goods_category_id: goodsCategoryId, goods_category_status: goodsCategoryStatus, post_type: 'change_category_status'};
	        console.log(data);
	        layer.confirm('是否确认更改状态?', {
	          btn: ['确定','取消'] //按钮
	        }, function(){
	       		is_ok("${pageContext.request.contextPath}/route", data, 'categoryStatusReturn');
	        }, function(){
	        }); 
    	});
    }
    
    // 更改商品类别价格状态的回调函数
    function categoryStatusReturn(returnData) {
    	console.log(returnData);
        if (returnData.code != 200) {
        	layer.msg(returnData.msg);
        } else {
	        location.reload();
        }
    }
    
    // 删除商品分类回调函数
    function delGoodsCategoryReturn(returnData) {
    	console.log(returnData);
        if (returnData.code != 200) {
        	layer.msg(returnData.msg);
        }
    	location.reload();
    }
</script>