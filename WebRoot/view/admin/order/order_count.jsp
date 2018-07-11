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
  <h1>订单统计</h1>
  <hr>
	<div style="display:inline-block;width:90%;margin:0 auto;">
	</div>
	
</div>
<table class="layui-table mt0">
    <thead>
    <tr>
      <th>订单总量</th>
      <th>成功数量</th>
      <th>退款数量</th>
      <th>退款总额</th>
      <th>实收总额</th>
    </tr>
    </thead>
    <tbody>
    	<tr>
    		<td>${requestScope.orderCountData.order_num}单</td>
    		<td>${requestScope.orderCountData.ok_order_num}单</td>
    		<td>${requestScope.orderCountData.refund_order_num}单</td>
    		<td>${requestScope.orderCountData.refund_order_total / 100} 元</td>
    		<td>${requestScope.orderCountData.ok_order_total / 100} 元</td>
    	</tr>
    </tbody>
  </table>
  <div id='prevModal' hidden>
	<img id='img_prev' style="width:100%; height:100%;"/>
  </div>
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