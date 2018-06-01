<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/404/css/main.css" type="text/css" media="screen, projection" /> <!-- main stylesheet -->
<link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/static/css/admin/404/css/tipsy.css" /> <!-- Tipsy implementation -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/css/admin/404/scripts/jquery-1.7.2.min.js"></script> <!-- uiToTop implementation -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/css/admin/404/scripts/custom-scripts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/css/admin/404/scripts/jquery.tipsy.js"></script> <!-- Tipsy -->

<script type="text/javascript">

$(document).ready(function(){
			
	universalPreloader();
						   
});

$(window).load(function(){

	//remove Universal Preloader
	universalPreloaderRemove();
	
	rotate();
    dogRun();
	dogTalk();
	
	//Tipsy implementation
	$('.with-tooltip').tipsy({gravity: $.fn.tipsy.autoNS});
						   
});

</script>


<title>404 - Not found</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>

<body onClick="login();">

<!-- Universal preloader -->
<div id="universal-preloader">
    <div class="preloader">
        <img src="${pageContext.request.contextPath}/static/img/admin/404/universal-preloader.gif" alt="universal-preloader" class="universal-preloader-preloader" />
    </div>
</div>
<!-- Universal preloader -->

<div id="wrapper">
<!-- 404 graphic -->
	<div class="graphic"></div>
<!-- 404 graphic -->
<!-- Not found text -->
	<div class="not-found-text">
    	<h1 class="not-found-text">File not found, sorry!</h1>
    </div>
<!-- Not found text -->

<!-- search form -->
<!-- <div class="search">
	<form name="search" method="get" action="#" />
        <input type="text" name="search" value="Search ..." />
        <input class="with-tooltip" title="Search!" type="submit" name="submit" value="" />
    </form>
</div> -->
<!-- search form -->

<!-- top menu -->
<div class="top-menu">
<!-- 	<a href="" onClick="login();" class="with-tooltip" title="重新登录">重新登录</a>
 --></div>
<!-- top menu -->

<div class="dog-wrapper">
<!-- dog running -->
	<div class="dog"></div>
<!-- dog running -->
	
<!-- dog bubble talking -->
	<div class="dog-bubble">
    	
    </div>
    
    <!-- The dog bubble rotates these -->
    <div class="bubble-options">
		
        <p class="dog-bubble">
        	没有狗粮了，点击任意地方添加。。。<br /><img style="margin-top:8px" src="${pageContext.request.contextPath}/static/css/admin/404/images/cookie.png" alt="cookie" />
        </p>
    
    </div>
    <!-- The dog bubble rotates these -->
<!-- dog bubble talking -->
</div>

<!-- planet at the bottom -->
	<div class="planet"></div>
<!-- planet at the bottom -->
</div>

<script>
	var login = function () {
		parent.location.href="${pageContext.request.contextPath}/route?get_type=admin_unlogin";
	};
</script>
</body>
</html>