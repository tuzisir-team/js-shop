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
	 <a class="layui-btn" href="${pageContext.request.contextPath}/route?get_type=admin_add_goods">添加商品</a>
	</div>
</div>
<table class="layui-table mt0">
    <thead>
    <tr>
      <th>商品ID</th>
      <th>缩略图</th>
      <th>商品分类</th>
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
    layui.use('layer', function(){
      var layer = layui.layer;      
    });
   
    
</script>