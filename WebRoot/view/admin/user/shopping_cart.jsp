<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include   page="../common/header.jsp" flush="true"/>
<table class="layui-table mt0">
    <thead>
    <tr>
      <th>购物车ID</th>
      <th>商品名称</th>
      <th>商品数量</th>
      <th>商品单价</th>
      <th>创建时间</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.shoppingCartList}" var="shoppingCart">
       		<td>${shoppingCart.goodsOrderId}</td>
  		    <td>${shoppingCart.goodsName}</td>
  		    <td>${shoppingCart.goodsNum}</td>
  		    <td>${shoppingCart.goodsPrice}</td>
  		    <td class="create-time">${shoppingCart.createTime}</td>
			</td>
	    </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<script type="text/javascript">
  layui.use(['form','layer'],function(){
      var layer = layui.layer;
      form = layui.form;
  });
  $(function(){
       	timeConversion(); // 时间转换
  });
 
	var timeConversion = function() {
	   	$(".create-time").html(timestampToTime($(".create-time").html()));
	    $(".update-time").html(timestampToTime($(".update-time").html()));
	};
</script>