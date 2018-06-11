<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include   page="../common/header.jsp" flush="true"/>
 <style>
	.thumb {margin-left:5px; margin-top:15px; height:128px}
	#prevModal {width:100%; height:100%; text-align:center; display:none;}
	#img_prev {max-width:98%; max-height:98%; margin: 10px auto}
  </style>
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
  <form id="activity_form" class="layui-form" action="" method="post">
  	<input type="hidden" value="admin_add_goods" name="post_type">
  	<div class="layui-form-item">
	    <label class="layui-form-label">商品类别:</label>
	    <div class="layui-input-block wd-100px">
	      <select name="goods_category_id" lay-filter="aihao" lay-verify="required">
    		<c:forEach items="${requestScope.goodsCategoryList}" var="goodsCategory">
		        <option value="${goodsCategory.goodsCategoryId}" >${goodsCategory.goodsCategoryName}</option>
    		</c:forEach>
	      </select>
	    </div>
	  </div>
      <div class="layui-form-item">
	    <label class="layui-form-label">商品名称:</label>
	    <div class="layui-input-inline">
	      <input type="text" name="goods_name" lay-verify="required" placeholder="请输入商品名称" autocomplete="off" class="layui-input">
	    </div>
	  </div> 
    
      <div class="layui-form-item">
	    <label class="layui-form-label">进货单价:</label>
	    <div class="layui-input-block wd-100px">
	      <input type="text" name="goods_bid" lay-verify="required|number|positiveNumber" autocomplete="off" placeholder="进货单价" class="layui-input">
	    </div>
	  </div>
      <div class="layui-form-item">
	    <label class="layui-form-label">零售单价:</label>
	    <div class="layui-input-block wd-100px">
	      <input type="text" name="goods_price" lay-verify="required|number|positiveNumber" autocomplete="off" placeholder="零售单价" class="layui-input">
	    </div>
	  </div>
      <div class="layui-form-item">
	    <label class="layui-form-label">商品数量:</label>
	    <div class="layui-input-block wd-100px">
	      <input type="text" name="goods_num" lay-verify="required|number|positiveNumber" autocomplete="off" placeholder="商品数量" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">商品图片:</label>
	    <div class="layui-upload">
		  <button type="button" class="layui-btn" id="test1">上传图片</button>
		  <input type="hidden" lay-verify="required" name="goods_pic" class="goods_pic" value=""/>
		  <div class='layui-input-block' id='div_prev' title=''></div>
		</div>
	  </div>
	  <div id='prevModal'>
		<img id='img_prev'/>
	  </div>
	  <div class="layui-form-item layui-form-text">
	    <label class="layui-form-label">商品描述:</label>
	    <div class="layui-input-block wd-500px">
	      <textarea name="goods_describe" lay-verify="required" placeholder="请输入商品描述" class="layui-textarea"></textarea>
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <input type="hidden" class="test">
	      <div class="layui-input-block">
	        <button class="layui-btn submit" lay-submit="" lay-filter="demo1">确认添加</button>
	          <a href="${pageContext.request.contextPath}/route?get_type=admin_goods_list"><span type="reset" class="layui-btn layui-btn-primary">返回</span></a>
	      </div>
	 </div>
   </form>
</div>
<script>
var addGoods;
var uploadInst;
layui.use(['form', 'layedit', 'upload'], function(){
  var $ = layui.jquery,
  upload = layui.upload
  ,form = layui.form;
  	// 监听提交
	 form.on('submit(demo1)', function(data){
	 	console.log(JSON.stringify(data.field));
	is_ok("${pageContext.request.contextPath}/route", data.field, 'addGoodsReturn');
	   return false;
	 });
  	//自定义验证规则
	form.verify({
	  positiveNumber: function(value){
	    if(value <= 0){
	      return '必须为正数';
	    }
	  }
	});
  //普通图片上传
  uploadInst = upload.render({
    elem: '#test1'
    ,url: '${pageContext.request.contextPath}/route'
    ,exts: 'jpg|png|gif|bmp|jpeg'
    ,accept: 'images'
    ,before: function(obj){
    	layer.load(); //上传loading
		this.data={post_type:'admin_add_goods_pic', goods_pic: $(".goods_pic").val()};
      //预读本地文件示例，不支持ie8
      obj.preview(function(index, file, result){
        $('#demo1').attr('src', result); //图片链接（base64）
      });
    }
    ,choose: function(obj){
			//图像预览，如果是多文件，会逐个添加。(不支持ie8/9)
			obj.preview(function(index, file, result){
				var imgobj = new Image(); //创建新img对象
				imgobj.src = result; //指定数据源
				imgobj.className = 'thumb';
				imgobj.onclick = function(result) {
					//单击预览
					img_prev.src = this.src;
					var w = $(window).width() - 42, h = $(window).height() - 42;
					layer.open({
						title: '预览',
						type: 1,
						area: [w, h], //宽高
						content: $('#prevModal')
					});
				};
				document.getElementById("div_prev").innerHTML = "";
				document.getElementById("div_prev").appendChild(imgobj); //添加到预览区域
			});
		}
    ,done: function(res){
    	console.log(res);
    	if (res.code == 200) {
  			$(".goods_pic").val(res.data);
    	}
    	layer.closeAll('loading');            
    }
    ,error: function(){
    }
  });
  // 添加商品回调函数
  addGoodsReturn = function addBookReturn(returnData) {
  		if (returnData.code == 200) {
			location.reload();
  		} else {
	  		layer.msg(returnData.msg, function () {
	        });
  		}
  }
});
</script>
