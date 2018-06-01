<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="page" class="page">
<ul class="pagination">
        <!-- 总页数大于1如果当前页为第一页 上一页无效下一页有效-->
        <c:if test="${requestScope.viewpage.totalPage > 1 && requestScope.viewpage.currentPage == 1}">
            <li class="page-item"><a class="page-link" href="" onclick="return false" data-toggle="popover1" data-content="没有了">上一页</a></li>
            <c:forEach var="i" begin="${requestScope.viewpage.start}" end="${requestScope.viewpage.end}" step="1">
                <c:if test="${requestScope.viewpage.currentPage == i}">
                	<li class="page-item active">
                		<a class="page-link" href="">${i}</a>
               		</li>
             	</c:if>
                <c:if test="${requestScope.viewpage.currentPage != i}">
                	<li class="page-item">
                		<a class="page-link" href="${pageContext.request.contextPath}/route?get_type=${requestScope.viewpage.getRoute}&currentpage=${i}">${i}</a>
               		</li>
              	</c:if>
            </c:forEach>
            <li class="page-item">
            	<a class="page-link" href="${pageContext.request.contextPath}/route?get_type=${requestScope.viewpage.getRoute}&currentpage=${requestScope.viewpage.currentPage+1}">下一页</a>
           	</li>
        </c:if>
        <!-- 如果当前页不是第一页也不是最后一页，上一页和下一页都有效 -->
        <c:if test="${requestScope.viewpage.currentPage > 1 && requestScope.viewpage.currentPage < requestScope.viewpage.totalPage}">
            <li class="page-item">
            	<a class="page-link" href="${pageContext.request.contextPath}/route?get_type=${requestScope.viewpage.getRoute}&currentpage=${requestScope.viewpage.currentPage-1}">上一页</a>
           	</li>
            <c:forEach var="i" begin="${requestScope.viewpage.start}" end="${requestScope.viewpage.end}" step="1">
                <c:if test="${requestScope.viewpage.currentPage == i}">
                	<li class="page-item active">
                		<a class="page-link" href="">${i}</a>
               		</li>
              	</c:if>
                <c:if test="${requestScope.viewpage.currentPage != i}">
                	<li class="page-item">
                		<a class="page-link" href="${pageContext.request.contextPath}/route?get_type=${requestScope.viewpage.getRoute}&currentpage=${i}">${i}</a>
               		</li>
              	</c:if>
            </c:forEach>
            <li class="page-item">
            	<a class="page-link" href="${pageContext.request.contextPath}/route?get_type=${requestScope.viewpage.getRoute}&currentpage=${requestScope.viewpage.currentPage+1}">下一页</a>
            </li>
        </c:if>
        <!-- 总页数大于1 如果当前页为最后一页 下一页无效-->
        <c:if test="${requestScope.viewpage.totalPage > 1 && requestScope.viewpage.currentPage == requestScope.viewpage.totalPage}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/route?get_type=user_list&currentpage=${requestScope.viewpage.currentPage-1}">上一页</a></li>
            <c:forEach var="i" begin="${requestScope.viewpage.start}" end="${requestScope.viewpage.end}" step="1">
                <c:if test="${requestScope.viewpage.currentPage == i}"><li class="page-item active"><a class="page-link" href="">${i}</a></li></c:if>
                <c:if test="${requestScope.viewpage.currentPage != i}">
                	<li class="page-item">
                		<a class="page-link" href="${pageContext.request.contextPath}/route?get_type=${requestScope.viewpage.getRoute}&currentpage=${i}">${i}</a>
               		</li>
            	</c:if>
            </c:forEach>
            <li class="page-item">
            	<a class="page-link" href="#" onclick="return false" data-toggle="popover1" data-content="没有了">下一页</a>
           	</li>
        </c:if> 
        <!-- 总页数==1 -->
        <c:if test="${requestScope.viewpage.totalPage == 1}">
            <li class="page-item">
            	<a class="page-link" href="#" onclick="return false" data-toggle="popover1" data-content="没有了">上一页</a>
           	</li>
            <li class="page-item active">
            	<a class="page-link" href="">1</a>
           	</li>
            <li class="page-item">
            	<a class="page-link" href="#" onclick="return false" data-toggle="popover1" data-content="没有了">下一页</a>
           	</li>
        </c:if>
    </ul>
</div>
