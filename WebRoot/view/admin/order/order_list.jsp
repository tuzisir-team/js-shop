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
  <h1>订单列表</h1>
  <hr>
	<div style="display:inline-block;width:90%;margin:0 auto;">
	</div>
	<form method="post" action="${pageContext.request.contextPath}/route" class="layui-form" style="margin-bottom: 20px;">
                <div class="layui-input-inline">
                	<input type="hidden" name="post_type" value="admin_order_find">
                    <input type="text" name="out_trade_no" class="layui-input" style="width:20rem;" placeholder="请输入用户订单号" required>
                </div>
                <button type="submit" class="layui-btn">搜索</button>
                <c:if test="${isPage == 0}">
	           		<a href="${pageContext.request.contextPath}/route?get_type=admin_orders_list" type="submit" class="layui-btn">返回</a>       
	          	</c:if>
            </form>

            <form method="post" action="{:url('group/pay_orders/allOrderList')}" class="layui-form time-select">
                <!-- <div class="layui-form-pane" >
                    <div class="layui-form-item" >
                        <label class="layui-form-label wd1">开始时间</label>
                        <div class="layui-input-inline">
                            <input class="layui-input" name="startTime" required {if isset($startTime)} value="" {/if} id="LAY_demorange_s" name="startTime">
                        </div>
                        <label class="layui-form-label wd1">结束时间</label>
                        <div class="layui-input-inline">
                            <input class="layui-input" name="endTime" required {if isset($endTime) } value="" {/if}
                            id="LAY_demorange_e" name="endTime">
                        </div>
                        <button type="submit" class="layui-btn">搜索</button>
                    </div>
                </div> -->
            </form>
</div>
<table class="layui-table mt0">
    <thead>
    <tr>
      <th>订单ID</th>
      <th>订单总额</th>
      <th>用户订单号</th>
      <th>用户名称</th>
      <th>地址名称</th>
      <th>订单状态</th>
      <th>下单时间</th>
      <th>完成时间</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    	<c:forEach items="${requestScope.viewpage.list}" var="orders">
		    <tr>
	       		<td>${orders.orderId}</td>
     			<td>${orders.orderTotal / 100}</td>
     			<td>${orders.outTradeNo}</td>
	  		    <td>${orders.userName}</td>
	       		<td>${orders.userAddressName}</td>
	       		<c:choose>
				    <c:when test="${orders.orderStatus == 1}">
				    	<td>支付成功</td>
				    </c:when>
				    <c:when test="${orders.orderStatus == 0}">
	  			    	<td>正在支付</td>
				    </c:when>
				    <c:when test="${orders.orderStatus == 2}">
	  			    	<td>支付失败</td>
				    </c:when>
				    <c:when test="${orders.orderStatus == 3}">
	  			    	<td>已退款</td>
				    </c:when>
				    <c:otherwise>
				    	<td>未知</td>
				    </c:otherwise>
				</c:choose>
	  		    <td class="create-time">${orders.createTime}</td>
	  		    <td class="update-time">${orders.completeTime}</td>
				<td>
					<div class="layui-btn-group" >
						<button class="layui-btn layui-btn-sm order-details" 
				  			data-orderid="${orders.orderId}" >订单详情</button>
					</div>
				</td>
		    </tr>
	    </c:forEach>
    </tbody>
  </table>
  <div id='prevModal' hidden>
	<img id='img_prev' style="width:100%; height:100%;"/>
  </div>
  <c:if test="${isPage != 0}">
  	<jsp:include page="../common/page.jsp" flush="true"/>
  </c:if>
</div>
<script>
	layui.use('layer', function(){
      var layer = layui.layer;      
    });
	$(function(){
		timeConversion(); // 时间转换
		orderDetails(); // 订单详情
	});
	var timeConversion = function() {
	  $(".create-time").html(timestampToTime($(".create-time").html()));
	  $(".update-time").html(timestampToTime($(".update-time").html()));
	 }
	var orderDetails = function () {
		$(".order-details").click(function () {
			newView("${pageContext.request.contextPath}/route?get_type=admin_order_details&order_id=" + $(this).data('orderid'),
							'订单详情', ['60%', '70%']);
		});
	};
</script>