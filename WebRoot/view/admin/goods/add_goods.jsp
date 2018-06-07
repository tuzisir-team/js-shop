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
  <h1>添加商品</h1>
  <hr>
</div>
<div class="page-concent-div">
  <form id="activity_form" class="layui-form" action="{:url('/activity/save_activity')}" method="post">
  	<div class="layui-form-item">
	    <label class="layui-form-label">商品类别:</label>
	    <div class="layui-input-block wd-100px">
	      <select name="interest" lay-filter="aihao">
	        <option value=""></option>
	        <option value="0">写作</option>
	        <option value="1" selected="">阅读</option>
	        <option value="2">游戏</option>
	        <option value="3">音乐</option>
	        <option value="4">旅行</option>
	      </select>
	    </div>
	  </div>
      <div class="layui-form-item">
	    <label class="layui-form-label">商品名称:</label>
	    <div class="layui-input-inline">
	      <input type="text" name="username" lay-verify="required" placeholder="请输入商品名称" autocomplete="off" class="layui-input">
	    </div>
	  </div> 
    
      <div class="layui-form-item">
	    <label class="layui-form-label">进货单价:</label>
	    <div class="layui-input-block wd-100px">
	      <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="进货单价" class="layui-input">
	    </div>
	  </div>
      <div class="layui-form-item">
	    <label class="layui-form-label">零售单价:</label>
	    <div class="layui-input-block wd-100px">
	      <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="零售单价" class="layui-input">
	    </div>
	  </div>
      <div class="layui-form-item">
	    <label class="layui-form-label">商品数量:</label>
	    <div class="layui-input-block wd-100px">
	      <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="商品数量" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">商品数量:</label>
	    <div class="layui-upload">
		  <button type="button" class="layui-btn" id="test1">上传图片</button>
		  <div class="layui-upload-list">
		    <img class="layui-upload-img" id="demo1">
		    <p id="demoText"></p>
		  </div>
		</div>
	  </div>
	  <div class="layui-form-item layui-form-text">
	    <label class="layui-form-label">商品描述:</label>
	    <div class="layui-input-block wd-500px">
	      <textarea placeholder="请输入商品描述" class="layui-textarea"></textarea>
	    </div>
	  </div>
   </form>
</div>
<script>
layui.use(['form', 'layedit', 'upload'], function(){
  var $ = layui.jquery,
  upload = layui.upload;
  //普通图片上传
  var uploadInst = upload.render({
    elem: '#test1'
    ,url: '${pageContext.request.contextPath}/route'
    ,exts: 'jpg|png|gif|bmp|jpeg'
    ,accept: 'images'
    ,before: function(obj){
    	layer.load(); //上传loading
		this.data={post_type:'admin_add_goods_pic'};
		console.log(this.data);
      //预读本地文件示例，不支持ie8
      obj.preview(function(index, file, result){
        $('#demo1').attr('src', result); //图片链接（base64）
      });
    }
    ,done: function(res){
      console.log(res);
    }
    ,error: function(){
      console.log("error");
    }
  });
});

</script>
