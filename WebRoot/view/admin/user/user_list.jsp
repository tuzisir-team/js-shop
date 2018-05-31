<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<!--   <a id="add-event1" class="layui-btn layui-btn-normal" onclick="addStore()">添加门店</a>
 -->  <table class="layui-table mt0">
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
  </div>
</div>
<!--添加门店弹出框-->
<form action="{:url('/store/add')}" method="post" class="layui-form pop-up-style"  id="form1" hidden>
  <div class="layui-form-item">
    <label class="layui-form-label wd0">门店名称</label>
    <div class="layui-input-inline">
      <input type="text"  name="store_name" class="layui-input" placeholder="请输入门店名称"/>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label wd0">门店登录名</label>
    <div class="layui-input-inline">
      <input type="text"name="store_username" class="layui-input" placeholder="请输入门店登录名"/>
    </div>
  </div>
  <div class="layui-form-item" hidden>
    <label class="layui-form-label wd0">商户号</label>
    <div class="layui-input-inline">
      <select class="wd0" name="sub_mch_id" lay-verify="">
      {volist name="all_mch_id" id="mch_id"}
        <option value="{$mch_id['mch_id']}">{$mch_id['mch_name']}</option>
      {/volist}
      </select> 
    </div>
  </div>
</form>
<!--修改门店-->
<form action="{:url('/store/edit')}"  method="post" class="layui-form pop-up-style" id="update" hidden >
  <div class="layui-form-item">
    <label class="layui-form-label wd0">修改门店</label>
    <div class="layui-input-inline">
      <input type="text"  id="modify_name" name="store_name" class="layui-input" placeholder="请输入门店名称"/>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label wd0">门店登录名</label>
    <div class="layui-input-inline">
      <input type="text" id="modify_account"  name="store_username" class="layui-input" placeholder="请输入门店登录名"/>
    </div>
  </div>
  <div hidden="" class="layui-form-item">
    <label class="layui-form-label wd0">商户号</label>
    <div class="layui-input-inline">
      <select class="wd0" name="sub_mch_id" lay-verify="">
      {volist name="all_mch_id" id="mch_id"}
        <option value="{$mch_id['mch_id']}">{$mch_id['mch_name']}</option>
      {/volist}
      </select> 
    </div>
  </div>
  <input name="store_id" class="store_id" type="hidden" value="">
</form>
<!--重置密码-->
<form action="{:url('/Store/reset')}" method="post" class="layui-form pop-up-style" id="reset_password" hidden>
  <div class="layui-form-item">
    <label class="layui-form-label">新的密码</label>
    <div class="layui-input-inline">
      <input type="password"  name="store_pwd" class="layui-input" placeholder="新密码不得少于6位"/>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">确认密码</label>
    <div class="layui-input-inline">
      <input  type="password" placeholder="请再次输入新的密码" class="layui-input" name="re_store_pwd"/>
      <input name="store_id" class="store_id" type="hidden" value="">
    </div>
  </div>
  </div>
</form>
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
