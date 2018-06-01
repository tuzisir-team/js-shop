<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include   page="../common/header.jsp" flush="true"/>
<div class="bread-crumbs">
  <span class="layui-breadcrumb">
  <a href="">首页</a>
  <a><cite></cite></a>
  </span>
</div>
<div class="page-concent-div">
  <h1>用户管理</h1>
  <hr>
<table class="layui-table mt0">
    <thead>
    <tr>
      <th>用户ID</th>
      <th>用户名称</th>
      <th>邮箱</th>
      <th>手机号</th>
      <th>用户状态</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td></td>
      <td></td>
      <td></td>
      <td></td>   
      <td></td>      
      <td>
        <div class="layui-btn-group" >
          <button class="layui-btn layui-btn-small" onclick="reset_password()">详情</button>
          <button class="layui-btn layui-btn-small" onclick="reset_password()">购物车</button>        
          <a href="#" class="del_alert" data-id="{$store.store_id}"><button class="layui-btn layui-btn-small layui-btn-danger">禁用</button></a>
        </div>
      </td>
    </tr>
  </table>
<div id="page" class="page">
  <ul class="pagination">
        <!-- 总页数大于1如果当前页为第一页 上一页无效下一页有效-->
        <c:if test="${requestScope.viewpage.totalPage > 1 && requestScope.viewpage.currentPage == 1}">
            <li class="page-item"><a class="page-link" href="" onclick="return false" data-toggle="popover1" data-content="没有了">上一页</a></li>
            <c:forEach var="i" begin="${requestScope.viewpage.start}" end="${requestScope.viewpage.end}" step="1">
                <c:if test="${requestScope.viewpage.currentPage == i}"><li class="page-item active"><a class="page-link" href="">${i}</a></li></c:if>
                <c:if test="${requestScope.viewpage.currentPage != i}"><li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/route?get_type=user_list&currentpage=${i}">${i}</a></li></c:if>
            </c:forEach>
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/route?get_type=user_list&currentpage=${requestScope.viewpage.currentPage+1}">下一页</a></li>
        </c:if>
        <!-- 如果当前页不是第一页也不是最后一页，上一页和下一页都有效 -->
        <c:if test="${requestScope.viewpage.currentPage > 1 && requestScope.viewpage.currentPage < requestScope.viewpage.totalPage}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/route?get_type=user_list&currentpage=${requestScope.viewpage.currentPage-1}">上一页</a></li>
            <c:forEach var="i" begin="${requestScope.viewpage.start}" end="${requestScope.viewpage.end}" step="1">
                <c:if test="${requestScope.viewpage.currentPage == i}"><li class="page-item active"><a class="page-link" href="">${i}</a></li></c:if>
                <c:if test="${requestScope.viewpage.currentPage != i}"><li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/route?get_type=user_list&currentpage=${i}">${i}</a></li></c:if>
            </c:forEach>
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/route?get_type=user_list&currentpage=${requestScope.viewpage.currentPage+1}">下一页</a></li>
        </c:if>
        <!-- 总页数大于1 如果当前页为最后一页 下一页无效-->
        <c:if test="${requestScope.viewpage.totalPage > 1 && requestScope.viewpage.currentPage == requestScope.viewpage.totalPage}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/route?get_type=user_list&currentpage=${requestScope.viewpage.currentPage-1}">上一页</a></li>
            <c:forEach var="i" begin="${requestScope.viewpage.start}" end="${requestScope.viewpage.end}" step="1">
                <c:if test="${requestScope.viewpage.currentPage == i}"><li class="page-item active"><a class="page-link" href="">${i}</a></li></c:if>
                <c:if test="${requestScope.viewpage.currentPage != i}"><li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/route?get_type=user_list&currentpage=${i}">${i}</a></li></c:if>
            </c:forEach>
            <li class="page-item"><a class="page-link" href="#" onclick="return false" data-toggle="popover1" data-content="没有了">下一页</a></li>
        </c:if> 
        <!-- 总页数==1 -->
        <c:if test="${requestScope.viewpage.totalPage == 1}">
            <li class="page-item"><a class="page-link" href="#" onclick="return false" data-toggle="popover1" data-content="没有了">上一页</a></li>
            <li class="page-item active"><a class="page-link" href="">1</a></li>
            <li class="page-item"><a class="page-link" href="#" onclick="return false" data-toggle="popover1" data-content="没有了">下一页</a></li>
        </c:if>
    </ul>
  </div>
</div>
<script>
    $(function(){
      del_alert();
    });
    var brand;
    var regionId;
    var form;
    layui.use(['form','layer'],function(){
        var layer = layui.layer;
        form = layui.form();
    });
    layui.use('layer', function(){
      var layer = layui.layer;      
    });  
    function addStore(){
        layer.open({
            title: '添加门店',
            type:1,
            content:$("#form1"),
            btn: ['添加','取消'],
            area:['30%','35%'],
            yes:function(index, layero){
                $('#form1').submit();
            }
        });
    }
    function updateStore(id,name,account,form){
         $(".store_id").val(id);
         $("#modify_name").val(name);
         $("#modify_account").val(account);
        layer.open({
            title: '修改门店信息',
            type:1,
            content:$("#update"),
            btn: ['确定', '取消'],
            area:['30%','35%'],
            yes:function(index, layero){
                $('#update').submit();
            }
        });
    }
    function reset_password(id) {
         $(".store_id").val(id);
        layer.open({
            title: '重置密码',
            type:1,
            content:$("#reset_password"),
            btn: ['确定', '取消'],
            area:['30%','auto'],
            yes:function(index, layero){
                $('#reset_password').submit();
            }
        });
    }
    function del_alert(){
      $(".del_alert").click(function(){
        var id = $(this).data('id');
        //询问框
        layer.confirm('您确定要删除吗？', {
          btn: ['确定','取消'] //按钮
        }, function(){
          click_ajax("{:url('/store/del')}",{id:id},'删除成功','删除失败');
        }, function(){
        }); 
      });
    }
    function now_load(data){
      location.reload();
      //console.log(data);
    }
</script>