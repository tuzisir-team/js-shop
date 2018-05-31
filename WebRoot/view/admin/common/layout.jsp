<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>超市后台管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!--<link href="__STATIC__/layui/css/layui.css" rel="stylesheet" media="all"/>-->
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js" charset="utf-8"></script>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/admin/nav/logo.png" type="image/x-icon">

    <!--新-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <!--新增css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/new_css/global.css" media="all"> <!--!!主要样式-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/new_css/font-awesome.css"> <!--!!小图标-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/new_css/main.css"/>

    <!--新增js-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/index.js"></script>

    <script>
        layui.config({
            base: "${pageContext.request.contextPath}/static/js/"
        });
    </script>
</head>


<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header header-demo">
        <div class="layui-main">
            <div class="admin-login-box">
                <a class="logo" style="left: 0;"><span style="font-size: 22px;">超市后台管理系统</span></a>
                <div class="admin-side-toggle">
                    <i class="fa fa-bars" aria-hidden="true"></i>
                </div>
            </div>
            <ul class="layui-nav admin-header-item">
                <li class="layui-nav-item">
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;" class="admin-header-user">
                        <img src="${pageContext.request.contextPath}/static/img/admin/nav/logo.png"/>                       
                        <span>管理</span>
                    </a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="{:url('/login_logout')}"><i class="fa fa-sign-out" aria-hidden="true"></i>注销</a>
                        </dd>
                    </dl>
                </li>
            </ul>
            <ul class="layui-nav admin-header-item-mobile">
                <li class="layui-nav-item">
                    <a href="{:url('/login_logout')}"><i class="fa fa-sign-out" aria-hidden="true"></i>注销</a>
                </li>
            </ul>
        </div>
    </div>

    <script>
        var navs = [
            {
                "title": "用户管理",
                "icon": "<img src='${pageContext.request.contextPath}/static/img/admin/nav/订单管理.png' style='width: 20px;height: 19px;'>",
                "spread": false,
                "children": [
                    {
                        "title": "用户列表",
                        "icon": "",
                        "href": '/store'
                    },
                ]
            },
            
            {
                "title": "商品管理",
                "icon": "<img src='${pageContext.request.contextPath}/static/img/admin/nav/订单管理.png' style='width: 20px;height: 19px;'>",
                "spread": false,
                "children": [
                    {
                        "title": "商品类别",
                        "icon": "",
                        "href": '/store'
                    },
                    {
                        "title": "商品列表",
                        "icon": "",
                        "href": '/store'
                    },
                ]
            },
            {
                "title": "订单管理",
                "icon": "<img src='${pageContext.request.contextPath}/static/img/admin/nav/订单管理.png' style='width: 20px;height: 19px;'>",
                "spread": false,
                "children": [
                    {
                        "title": "订单列表",
                        "icon": "",
                        "href": '/store'
                    },
                ]
            },
            {
                "title": "邮件管理",
                "icon": "<img src='${pageContext.request.contextPath}/static/img/admin/nav/订单管理.png' style='width: 20px;height: 19px;'>",
                "spread": false,
                "children": [
                    {
                        "title": "邮件列表",
                        "icon": "",
                        "href": '/store'
                    },
                ]
            },
        ];
        layui.use(['jquery', 'layer'], function () {
            var $ = layui.jquery
                , layer = layui.layer;

            $(document).ready(function (e) {
                var counter = 0;
                if (window.history && window.history.pushState) {
                    $(window).on('popstate', function () {
                        window.history.pushState('forward', null, '#');
                        window.history.forward(1);
                        layer.msg("不可回退");  //如果需在弹框就有它
                        //self.location="orderinfo.html"; //如查需要跳转页面就用它
                    });
                }

                window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
                window.history.forward(1);
            });
        });

    </script>
    <div class="layui-side layui-bg-black" id="admin-side">
        <div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side"></div>
    </div>
