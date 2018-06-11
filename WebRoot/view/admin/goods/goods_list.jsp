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
  <h1>商品列表</h1>
  <hr>
	<div style="display:inline-block;width:90%;margin:0 auto;">
	 <span class="layui-btn add-goods" >添加商品</span>
	</div>
</div>
<table class="layui-table mt0">
    <thead>
    <tr>
      <th>商品ID</th>
      <th>缩略图</th>
      <th>分类名称</th>
      <th>商品名称</th>
      <th>商品描述</th>
      <th>进货价</th>
      <th>零售价</th>
      <th>商品数量</th>
      <th>商品状态</th>
      <th>添加时间</th>
      <th>更新时间</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    	<c:forEach items="${requestScope.viewpage.list}" var="goods">
		    <tr>
	       		<td>${goods.goodsId}</td>
	       		<td>
	       			<img class="goods-pic" src="${goods.goodsPic}"/>
	       		</td>
     			<td>${goods.goodsCategoryName}</td>
	  		    <td>${goods.goodsName}</td>
	       		<td data-goodsdescribe="${goods.goodsDescribe}" class="goods-describe"><span>点击查看</span></td>
	       		<td>${goods.goodsBid / 100}</td>
	       		<td>${goods.goodsPrice / 100}</td>
	       		<td>${goods.goodsNum}</td>
	       		<c:choose>
				    <c:when test="${goods.goodsStatus == 1}">
				    	<td>正常</td>
				    </c:when>
				    <c:when test="${goods.goodsStatus == 0}">
	  			    	<td>禁用</td>
				    </c:when>
				    <c:otherwise>
				    	<td>未知</td>
				    </c:otherwise>
				</c:choose>
	  		    <td class="create-time">${goods.createTime}</td>
	  		    <td class="update-time">${goods.updateTime}</td>
				<td>
					<div class="layui-btn-group" >
						<button class="layui-btn layui-btn-xs edit-goods" 
				  			data-goodsid="${goods.goodsId}" >修改信息</button>
						<button class="layui-btn layui-btn-xs layui-btn-danger del-goods" 
						    data-goodsid="${goods.goodsId}">删除</button>	
					    <c:choose>
						  <c:when test="${goods.goodsStatus == 1}">
						    <button class="layui-btn layui-btn-xs layui-btn-danger goods-status" 
						    data-goodsid="${goods.goodsId}" data-goodsstatus="${goods.goodsStatus}">禁用</button>
						  </c:when>
						  <c:when test="${goods.goodsStatus == 0}">
				  			<button class="layui-btn layui-btn-xs goods-status" 
				  			data-goodsid="${goods.goodsId}" data-goodsstatus="${goods.goodsStatus}" >启用</button>
						  </c:when>
						</c:choose>
					</div>
				</td>
		    </tr>
	    </c:forEach>
    </tbody>
  </table>
  <div id='prevModal' hidden>
	<img id='img_prev' style="width:100%; height:100%;"/>
  </div>
  <jsp:include page="../common/page.jsp" flush="true"/>
</div>
<script>
    layui.use('layer', function(){
      var layer = layui.layer;      
    });
    $(function() {
    	checkIsGoodsCategory(); 
    	lookGoodsPic();
    	goodsDescribe();
    	timeConversion();
  	    goodsStatus();  // 修改商品状态
  	    delGoods();
  	    editGoods(); // 修改图书信息
    });
    
    // 修改图书信息
    var editGoods = function () {
    	$(".edit-goods").click(function () {
    		window.location.href = "${pageContext.request.contextPath}/route?get_type=admin_edit_goods_view&goods_id=" + $(this).data('goodsid');
    	});
    };
    
    // 删除商品
    function delGoods(){
    	$(".del-goods").click(function() {
    		var goodsId = $(this).data('goodsid');
	        var data = {goods_id: goodsId, post_type: 'admin_del_goods'};
	        console.log(data);
	        layer.confirm('是否确认删除?', {
	          btn: ['确定','取消'] //按钮
	        }, function(){
	       		is_ok("${pageContext.request.contextPath}/route", data, 'delGoodsReturn');
	        }, function(){
	        }); 
    	});
    }
    
    // 删除商品分类回调函数
    function delGoodsReturn(returnData) {
    	console.log(returnData);
        if (returnData.code != 200) {
        	layer.msg(returnData.msg);
        }
        window.location.href = "${pageContext.request.contextPath}/route?get_type=admin_goods_list";
    }
    
    // 改变商品状态
    function goodsStatus(){
    	$(".goods-status").click(function() {
    		var goodsId = $(this).data('goodsid');
	        var goodsStatus = $(this).data("goodsstatus");
	        var data = {goods_id: goodsId, goods_status: goodsStatus, post_type: 'admin_change_goods_status'};
	        layer.confirm('是否确认更改状态?', {
	          btn: ['确定','取消'] //按钮
	        }, function(){
	       		is_ok("${pageContext.request.contextPath}/route", data, 'goodsStatusReturn');
	        }, function(){
	        }); 
    	});
    }
    
    // 更改商品状态的回调函数
    function goodsStatusReturn(returnData) {
    	console.log(returnData);
        if (returnData.code != 200) {
        	layer.msg(returnData.msg);
        } else {
	        location.reload();
        }
    }
    
    var timeConversion = function() {
	    $(".create-time").html(timestampToTime($(".create-time").html()));
	    $(".update-time").html(timestampToTime($(".update-time").html()));
    } 
    var goodsDescribe = function () {
    	$(".goods-describe").click(function () {
    		layer.tips($(this).data("goodsdescribe"),  $(this));
    	});
    }
    
    var lookGoodsPic = function () {
    	$(".goods-pic").click(function () {
    		//单击预览
			img_prev.src = this.src;
			$(".img_prev").show();
			layer.open({
				title: '预览',
				type: 1,
				area: ["50%", "50%"], //宽高
				content: $('#prevModal'),
				cancel: function(){
					$(".img_prev").hide();
					img_prev.src = "";
				}
			});
    	});
    };
    
    // 添加商品时检测有没有商品类别
   	var checkIsGoodsCategory = function () {
   		$(".add-goods").click(function () {
   			var data = {post_type: "admin_is_goods_category"};
			is_ok("${pageContext.request.contextPath}/route", data, 'checkIsGoodsCategoryReturn');
   		});
   	}
   	
   	// 检测商品类别时的回调函数
   	var checkIsGoodsCategoryReturn = function (returnData) {
   		if (returnData.code != 200) {
   			layer.msg(returnData.msg);
   		} else {
   			window.location.href = "${pageContext.request.contextPath}/route?get_type=admin_add_goods_view";
   		}
   	}
    
</script>