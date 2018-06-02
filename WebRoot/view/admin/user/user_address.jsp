<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include   page="../common/header.jsp" flush="true"/>
<table class="layui-table mt0">
    <thead>
    <tr>
      <th>地址ID</th>
      <th>用户地址</th>
      <th>地址状态</th>
      <th>创建时间</th>
      <th>更新时间</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.addressList}" var="address">
    	<c:choose>
		    <c:when test="${address.userAddressStatus == 1}">
		    	<tr style="color:red;">
		    </c:when>
		    <c:otherwise>
		    	<tr>
		    </c:otherwise>
		</c:choose>
       		<td>${address.userAddressId}</td>
  		    <td>${address.userAddressName}</td>
  		    <c:choose>
			    <c:when test="${address.userAddressStatus == 1}">
			    	<td>默认地址</td>
			    </c:when>
			    <c:when test="${address.userAddressStatus == 0}">
  			    	<td>正常</td>
			    </c:when>
			    <c:otherwise>
			    	<td>未知</td>
			    </c:otherwise>
			</c:choose>
  		    <td class="create-time">${address.createTime}</td>
  		    <td class="update-time">${address.updateTime}</td>
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
	}  
  
</script>