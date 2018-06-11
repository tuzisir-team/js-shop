<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include   page="../common/header.jsp" flush="true"/>
<div style="width:80%;height:70%;margin: 0 auto; padding-top:5%;">
	<canvas id="myChart" style=""></canvas>
</div>
<script>
var ctx = document.getElementById("myChart").getContext('2d');
var days=new Array();
    var index=0;
    <c:forEach items="${requestScope.days}" var="lst">
        days[index]="${lst}";
        index++;
    </c:forEach>
var step = 1;
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
        datasets: [{
            label: '本周订单数量统计',
            data: days,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true,
                    min: 0,
                  	stepSize: 1,
                }
            }]
        }
    }
});
</script>