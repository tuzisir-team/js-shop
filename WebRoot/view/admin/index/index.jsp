<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<jsp:include   page="../common/base.jsp" flush="true"/>
<!DOCTYPE html>
<html>
  <head>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
  	<meta name="renderer" content="webkit|ie-comp|ie-stand">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/logo.png" type="image/x-icon">
  
    <title>超市后台管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/index/sccl.css">
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/admin/index/skin/blue/skin.css" id="layout-skin"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/liaotian/font_Icon/iconfont.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/liaotian/css/chat.css">
  </head>
  <body>
  <div class="layout-admin">
    <header class="layout-header">
      <span class="header-logo">超市后台管理系统</span> 
      <a class="header-menu-btn" href="javascript:;"><i class="icon-font">&#xe600;</i></a>
      <ul class="header-bar">
        <li class="header-bar-role"><a href="javascript:;">您好:<% out.println((String)session.getAttribute("admin_name")); %></a></li>
        <li class="header-bar-nav">
          <a href="javascript:;"><i class="icon-font" style="margin-left:5px;">&#xe60c;</i></a>
          <ul class="header-dropdown-menu">
            <li><a href="#" class='edit_password'>修改密码</a></li>
            <li><a href="${pageContext.request.contextPath}/route?get_type=admin_unlogin">退出</a></li>
          </ul>
        </li>
		<li class="header-bar-nav"> 
          <a href="javascript:;" title="换肤"><i class="icon-font">&#xe608;</i></a>
          <ul class="header-dropdown-menu right dropdown-skin">
            <li><a href="javascript:;" data-val="qingxin" title="清新">清新</a></li>
            <li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
            <li><a href="javascript:;" data-val="molv" title="墨绿">墨绿</a></li>
          </ul>
        </li>	
      </ul>
    </header>
    <aside class="layout-side">
      <ul class="side-menu">
        
      </ul>
    </aside>
    
    <div class="layout-side-arrow"><div class="layout-side-arrow-icon"><i class="icon-font">&#xe60d;</i></div></div>
    
    <section class="layout-main">
      <div class="layout-main-tab">
        <button class="tab-btn btn-left"><i class="icon-font">&#xe60e;</i></button> 
                <nav class="tab-nav">
                    <div class="tab-nav-content">
                        <a href="javascript:;" class="content-tab active" data-id="">首页</a>
                    </div>
                </nav>
                <button class="tab-btn btn-right"><i class="icon-font">&#xe60f;</i></button>
      </div>
      <div class="layout-main-body">
        <iframe class="body-iframe" name="iframe0" width="100%" height="99%" src="${pageContext.request.contextPath}/route?get_type=admin_main" frameborder="0" data-id="" seamless></iframe>
      </div>
    </section>
    <div class="layout-footer">2018 &copy; tuzisir</div>
  </div>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/sccl.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/sccl-util.js"></script>
  <div>
<div class="chatContainer" >
    <div class="chatBtn" style="z-index:9999;">
        <i class="iconfont icon-xiaoxi1"></i>
    </div>
    <div class="chat-message-num">10</div>
    <div class="chatBox" ref="chatBox">
        <div class="chatBox-head">
            <div class="chatBox-head-one">
                tuzisir客服系统
                <div class="chat-close" style="margin: 10px 10px 0 0;font-size: 14px">关闭</div>
            </div>
            <div class="chatBox-head-two">
                <div class="chat-return">返回</div>
                <div class="chat-people">
                    <div class="ChatInfoHead">
                        <img src="" alt="头像">
                    </div>
                    <div class="ChatInfoName">这是用户的名字，看看名字到底能有多长</div>
                </div>
                <div class="chat-close">关闭</div>
            </div>
        </div>
        <div class="chatBox-info">
            <div class="chatBox-list" ref="chatBoxlist">
            </div>
            <div class="chatBox-kuang" ref="chatBoxkuang">
                <div class="chatBox-content">
                    <div class="chatBox-content-demo" id="chatBox-content-demo">
                        <!--<div class="clearfloat">-->
                            <!--<div class="author-name">-->
                                <!--<small class="chat-date">2017-12-02 14:26:58</small>-->
                            <!--</div>-->
                            <!--<div class="left">-->
                                <!--<div class="chat-avatars"><img src="img/icon01.png" alt="头像"></div>-->
                                <!--<div class="chat-message">-->
                                    <!--给你看张图-->
                                <!--</div>-->
                            <!--</div>-->
                        <!--</div>-->
                        <!--<div class="clearfloat">-->
                            <!--<div class="author-name">-->
                                <!--<small class="chat-date">2017-12-02 14:26:58</small>-->
                            <!--</div>-->
                            <!--<div class="left">-->
                                <!--<div class="chat-avatars"><img src="img/icon01.png" alt="头像"></div>-->
                                <!--<div class="chat-message">-->
                                    <!--<img src="img/1.png" alt="">-->
                                <!--</div>-->
                            <!--</div>-->
                        <!--</div>-->

                        <!--<div class="clearfloat">-->
                            <!--<div class="author-name">-->
                                <!--<small class="chat-date">2017-12-02 14:26:58</small>-->
                            <!--</div>-->
                            <!--<div class="right">-->
                                <!--<div class="chat-message">嗯，适合做壁纸</div>-->
                                <!--<div class="chat-avatars"><img src="img/icon02.png" alt="头像"></div>-->
                            <!--</div>-->
                        <!--</div>-->
                    </div>
                </div>
                <div class="chatBox-send">
                    <div class="div-textarea" contenteditable="true"></div>
                    <div>
                        <button id="chat-biaoqing" class="btn-default-styles">
                            <i class="iconfont icon-biaoqing"></i>
                        </button>
                        <label id="chat-tuxiang" title="发送图片" for="inputImage" class="btn-default-styles">
                            <input type="file" onchange="selectImg(this)" accept="image/jpg,image/jpeg,image/png"
                                   name="file" id="inputImage" class="hidden">
                            <i class="iconfont icon-tuxiang"></i>
                        </label>
                        <button id="chat-fasong" class="btn-default-styles"><i class="iconfont icon-fasong"></i>
                        </button>
                    </div>
                    <div class="biaoqing-photo">
                        <ul>
                            <li><span class="emoji-picker-image" style="background-position: -9px -18px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -40px -18px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -71px -18px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -102px -18px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -133px -18px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -164px -18px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -9px -52px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -40px -52px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -71px -52px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -102px -52px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -133px -52px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -164px -52px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -9px -86px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -40px -86px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -71px -86px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -102px -86px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -133px -86px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -164px -86px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -9px -120px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -40px -120px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -71px -120px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -102px -120px;"></span>
                            </li>
                            <li><span class="emoji-picker-image" style="background-position: -133px -120px;"></span>
                            </li>
                            <li><span class="emoji-picker-image" style="background-position: -164px -120px;"></span>
                            </li>
                            <li><span class="emoji-picker-image" style="background-position: -9px -154px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -40px -154px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -71px -154px;"></span></li>
                            <li><span class="emoji-picker-image" style="background-position: -102px -154px;"></span>
                            </li>
                            <li><span class="emoji-picker-image" style="background-position: -133px -154px;"></span>
                            </li>
                            <li><span class="emoji-picker-image" style="background-position: -164px -154px;"></span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/liaotian/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/liaotian/js/cookie.js"></script>
<script src="${pageContext.request.contextPath}/static/liaotian/js/time.js"></script>
<script src="${pageContext.request.contextPath}/static/liaotian/js/myself.js"></script>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
</div>
  </div>
  <input type="hidden" id="functionsJson" value='${requestScope.functionsJson}'/>
<input type="hidden" id="onemenuJson" value='${requestScope.onemenuJson}'/>
  </body>
</html>

<script type="text/javascript">
layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
});

  $(function(){
        link();
	var functions = JSON.parse($("#functionsJson").val());
	var onemenus = JSON.parse($("#onemenuJson").val());
	var menu1 = '[{"id":"1","name":"首页","parentId":"0","url":"${pageContext.request.contextPath}/view/admin/index/main.jsp","icon":"","order":"1","isHeader":"1","childMenus":[';
  	for (var j=0; j<onemenus.length; j++) {
  		var flag = 0;
 		for (var i=0;i<functions.length;i++)
		{
			if (flag == 0 && onemenus[j]['admin_function_id'] == functions[i]['pid']) {
				menu1 +='{"id":"'+onemenus[j]['admin_function_id']+'","name":"'+onemenus[j]['admin_function_name']+'","parentId":"1","url":"","icon":"&#xe609;","order":"1","isHeader":"0","childMenus":[';
				flag = 1;
			}
			if (flag == 1 && onemenus[j]['admin_function_id'] == functions[i]['pid']) {
				menu1 += '{"id":"'+functions[i]['pid']+'","name":"'+functions[i]['admin_function_name']+'","parentId":"20","url":"${pageContext.request.contextPath}/route?get_type='+functions[i]['admin_function_url']+'","icon":"","order":"1","isHeader":"0","childMenus":""},'
			}
		}
		if (flag == 1) {
			flag = 0;
			menu1 = menu1.substr(0,menu1.length-2);
			menu1 += "}]},";
		}
	} 	
	menu1 = menu1.substr(0,menu1.length-1);
 	menu1 += "]}]";
	var menu = [{"id":"1","name":"首页","parentId":"0","url":"${pageContext.request.contextPath}/view/admin/index/main.jsp","icon":"","order":"1","isHeader":"1","childMenus":[
            {"id":"10","name":"用户管理","parentId":"1","url":"","icon":"&#xe609;","order":"1","isHeader":"0","childMenus":[
            	{"id":"11","name":"用户列表","parentId":"20","url":"${pageContext.request.contextPath}/route?get_type=user_list","icon":"","order":"1","isHeader":"0","childMenus":""},
            ]},
            {"id":"20","name":"商品管理","parentId":"1","url":"","icon":"&#xe609;","order":"1","isHeader":"0","childMenus":[
              {"id":"21","name":"商品类别","parentId":"20","url":"${pageContext.request.contextPath}/route?get_type=admin_goods_category_list","icon":"","order":"1","isHeader":"0","childMenus":""},
              {"id":"22","name":"商品列表","parentId":"20","url":"${pageContext.request.contextPath}/route?get_type=admin_goods_list","icon":"","order":"1","isHeader":"0","childMenus":""},
            ]},
            {"id":"30","name":"订单管理","parentId":"1","url":"","icon":"&#xe609;","order":"1","isHeader":"0","childMenus":[
              {"id":"31","name":"订单列表","parentId":"20","url":"${pageContext.request.contextPath}/route?get_type=admin_orders_list","icon":"","order":"1","isHeader":"0","childMenus":""},
            ]},
            {"id":"40","name":"邮件管理","parentId":"1","url":"","icon":"&#xe609;","order":"1","isHeader":"0","childMenus":[
              {"id":"41","name":"邮件设置","parentId":"40","url":"${pageContext.request.contextPath}/route?get_type=admin_set_email","icon":"","order":"1","isHeader":"0","childMenus":""},
              {"id":"42","name":"邮件模板","parentId":"40","url":"${pageContext.request.contextPath}/route?get_type=admin_email_template_list","icon":"","order":"1","isHeader":"0","childMenus":""},
              {"id":"43","name":"已发邮件","parentId":"40","url":"${pageContext.request.contextPath}/route?get_type=admin_email_list","icon":"","order":"1","isHeader":"0","childMenus":""},
            ]},
            {"id":"50","name":"统计管理","parentId":"1","url":"","icon":"&#xe609;","order":"1","isHeader":"0","childMenus":[
              {"id":"51","name":"订单统计","parentId":"20","url":"${pageContext.request.contextPath}/route?get_type=admin_order_count","icon":"","order":"1","isHeader":"0","childMenus":""},
            ]},
            {"id":"60","name":"权限管理","parentId":"1","url":"","icon":"&#xe609;","order":"1","isHeader":"0","childMenus":[
              {"id":"61","name":"功能列表","parentId":"20","url":"${pageContext.request.contextPath}/route?get_type=admin_function_list","icon":"","order":"1","isHeader":"0","childMenus":""},
              {"id":"62","name":"角色列表","parentId":"20","url":"${pageContext.request.contextPath}/route?get_type=admin_rote_list","icon":"","order":"1","isHeader":"0","childMenus":""},
              {"id":"63","name":"管理员列表","parentId":"20","url":"${pageContext.request.contextPath}/route?get_type=admin_admin_list","icon":"","order":"1","isHeader":"0","childMenus":""},
            ]},
            /* {"id":"70","name":"<div style='display:inline-block;width:80%;height:100%;' class='edit_password'>修改密码</div>","parentId":"1","url":"","icon":"&#xe609;","order":"1","isHeader":"0","childMenus":[
            ]}, */
          ]},
        ] 
     init_menu(JSON.parse(menu1)); // 初始化菜单
      edit_password(); // 修改管理员密码
  });
  	var ws;
    var clientId;
    var sendImgUrl;
    function link () {
        ws = new WebSocket("ws://0.0.0.0:8282");//连接服务器
        ws.onopen = function(event){
            ws.send(dataDeal().sendDataType(CS_CONNECT, '', {'customer_service_name': 'kefu1', 'customer_service_sign': '123456'}));
        };
        ws.onmessage = function (event) {
            console.log(event.data);
            data = JSON.parse(event.data);
            if (data.return_code === 200) {
                responseType(data);
            }
        };
        //ws.onclose = function(event){alert("已经与服务器断开连接\r\n当前连接状态："+this.readyState);};
        //ws.onerror = function(event){alert("WebSocket异常！");};
    }

    // 匹配消息类型
    var responseType = function (data) {
        switch (data.msg_type) {
            // 客服连接
            case "CS_CONNECT":
                msgType.CONNECT(data);
                break;
            // 用户连接
            case "C_CONNECT":
                msgType.CUSTOMER_CONNECT(data);
                break;
            // 接收用户消息
            case "C_NEWS":
                msgType.CUSTOMER_NEWS(data);
                break;
            // 客户关闭连接
            case "C_CLOSE":
                msgType.C_CLOSE(data);
                break;
        }
    };
    
    /**
     * 处理对应的消息
     * @returns {{CONNECT: CONNECT, CUSTOMER_CONNECT: CUSTOMER_CONNECT}}
     */
    var dealCorrespondingMsgType = function () {
        var msgType = {
            CONNECT: function (data) {
              alert("您已经连接服务器成功");
            },
            CUSTOMER_CONNECT: function (data) {
                addCustomerlement(data);
            },
            CUSTOMER_NEWS: function (data) {
                getNews(data);
            },
            C_CLOSE: function (data) {
                cClose(data);
            }
        };
        return msgType;
    };
    var msgType = dealCorrespondingMsgType();

    // 用户关闭了连接
    var cClose = function (data) {
        $.cookie(data.data.client_id, null); // 清理此连接的缓存
        $('.'+data.data.client_id).remove();
        chatListPeople();
    };

    // 接收消息
    var getNews = function (data) {
        var time = new Date().Format("yyyy-MM-dd hh:mm:ss");
        var news = data.data.news;
        // setNewsCookie(C, data.data.client_id, news);
        $("#chatBox-content-demo").append(getNewsModdle(time, news, data.data.news_type));
        scrollBottom('chatBox-content-demo');
    };

    // 发送信息
    $("#chat-fasong").click(function () {
        var textContent = $(".div-textarea").html().replace(/[\n\r]/g, '<br>');
        var sendData;
        if (textContent != "") {
            sendData = dataDeal().sendDataType(CS_NEWS, clientId, {'news_type': NEWS_TYPE_ORDINARY, 'news': textContent});
            setNewsCookie(CS, clientId, textContent, NEWS_TYPE_ORDINARY); // 把客服消息放入cookie
            ws.send(sendData);
            $(".chatBox-content-demo").append(sendNewsModdle(new Date().Format("yyyy-MM-dd hh:mm:ss"), textContent));
            //发送后清空输入框
            $(".div-textarea").html("");
            //聊天框默认最底部
            scrollBottom('chatBox-content-demo');
        }
    });

    // 设置消息cookie
    var setNewsCookie = function (type, key, news, news_type) {
        var customerNews = JSON.parse($.cookie(key));
        customerNews.push({'type': type, 'time': new Date().Format("yyyy-MM-dd hh:mm:ss"), 'news': news, 'news_type': news_type});
        $.cookie(key, JSON.stringify(customerNews));
        console.log($.cookie(key));
    };

    // 增加客户连接
    var addCustomerlement = function (data) {
        $.clearCookie();
        $.cookie(data.data.client_id, '[]');
        var customer = "<div data-clientId='"+ data.data.client_id +"' class='chat-list-people " + data.data.client_id + "'>" +
                            "<div>" +
                                "<img src='${pageContext.request.contextPath}/static/liaotian/img/icon01.png' alt='头像'>" +
                            "</div>" +
                            "<div class='chat-name'>" +
                                " <p>" + data.data.client_id + "</p>" +
                            " </div> " +
                            "<div class='message-num'>10</div>" +
                        "</div>";
        $('.chatBox-list').append(customer);
        chatListPeople();
    };

    // 接收聊天模板
    var getNewsModdle = function (time, news, newsType) {
        var data;
        if (newsType == NEWS_TYPE_IMAGE) {
            data = "<img src='" + news + "'>"
        } else {
            data = news;
        }
        return "<div class='clearfloat'>" +
            "<div class='author-name'>" +
            "<small class='chat-date'>" + time + "</small> " +
            "</div>" +
            "<div class='left'>" +
            "<div class='chat-avatars'>" +
            "<img src='${pageContext.request.contextPath}/static/img/icon01.png' alt='头像'>" +
            "</div>" +
            "<div class='chat-message'>" + data + " </div>" +
            "</div>" +
            "</div>";
    };

    // 发送聊天模板
    var sendNewsModdle = function (time, news, newsType) {
        var data;
        if (newsType == NEWS_TYPE_IMAGE) {
            data = "<img src='" + news + "'>"
        } else {
            data = news;
        }
        return "<div class=\"clearfloat\">" +
        "<div class=\"author-name\"><small class=\"chat-date\">"+time+"</small> </div> " +
        "<div class=\"right\"> <div class=\"chat-message\"> " + data + " </div> " +
        "<div class=\"chat-avatars\"><img src=\"${pageContext.request.contextPath}/static/img/icon01.png\" alt=\"头像\" /></div> </div> </div>";
    };

    screenFuc();
    function screenFuc() {
        var topHeight = $(".chatBox-head").innerHeight();//聊天头部高度
        //屏幕小于768px时候,布局change
        var winWidth = $(window).innerWidth();
        if (winWidth <= 768) {
            var totalHeight = $(window).height(); //页面整体高度
            $(".chatBox-info").css("height", totalHeight - topHeight);
            var infoHeight = $(".chatBox-info").innerHeight();//聊天头部以下高度
            //中间内容高度
            $(".chatBox-content").css("height", infoHeight - 46);
            $(".chatBox-content-demo").css("height", infoHeight - 46);

            $(".chatBox-list").css("height", totalHeight - topHeight);
            $(".chatBox-kuang").css("height", totalHeight - topHeight);
            $(".div-textarea").css("width", winWidth - 106);
        } else {
            $(".chatBox-info").css("height", 495);
            $(".chatBox-content").css("height", 448);
            $(".chatBox-content-demo").css("height", 448);
            $(".chatBox-list").css("height", 495);
            $(".chatBox-kuang").css("height", 495);
            $(".div-textarea").css("width", 260);
        }
    }
    (window.onresize = function () {
        screenFuc();
    })();
    //未读信息数量为空时
    var totalNum = $(".chat-message-num").html();
    if (totalNum == "") {
        $(".chat-message-num").css("padding", 0);
    }
    $(".message-num").each(function () {
        var wdNum = $(this).html();
        if (wdNum == "") {
            $(this).css("padding", 0);
        }
    });


    //打开/关闭聊天框
    $(".chatBtn").click(function () {
        $(".chatBox").toggle(10);
    })
    $(".chat-close").click(function () {
        $(".chatBox").toggle(10);
    })

    var chatListPeople= function () {
        //进聊天页面
        $(".chat-list-people").each(function () {
            $(this).click(function () {
                var n = $(this).index();
                var sendNews;
                clientId = $(this).data('clientid');
                var customerNews = JSON.parse($.cookie(clientId));
                $(".chatBox-head-one").toggle();
                $(".chatBox-head-two").toggle();
                $(".chatBox-list").fadeToggle();
                $("#chatBox-content-demo").html('');
                $.each(customerNews, function (key, value) {
                    if (value.type == C) {
                        if (value.news_type == NEWS_TYPE_IMAGE) {
                            sendNews = getNewsModdle(value.time, value.news, NEWS_TYPE_IMAGE);
                        } else if (value.news_type == NEWS_TYPE_EMOJI) {
                            sendNews = getNewsModdle(value.time, value.news, NEWS_TYPE_EMOJI);
                        } else if (value.news_type == NEWS_TYPE_ORDINARY) {
                            sendNews = getNewsModdle(value.time, value.news, NEWS_TYPE_EMOJI);
                        }
                    } else {
                        if (value.news_type == NEWS_TYPE_IMAGE) {
                            sendNews = sendNewsModdle(value.time, value.news, NEWS_TYPE_IMAGE);
                        } else if (value.news_type == NEWS_TYPE_EMOJI) {
                            sendNews = sendNewsModdle(value.time, value.news, NEWS_TYPE_EMOJI);
                        } else if (value.news_type == NEWS_TYPE_ORDINARY) {
                            sendNews = sendNewsModdle(value.time, value.news, NEWS_TYPE_EMOJI);
                        }
                    }
                    $("#chatBox-content-demo").append(sendNews);
                });
                $(".chatBox-kuang").fadeToggle();
                //传名字
                $(".ChatInfoName").text($(this).children(".chat-name").children("p").eq(0).html());
                //传头像
                $(".ChatInfoHead>img").attr("src", $(this).children().eq(0).children("img").attr("src"));
                //聊天框默认最底部
                scrollBottom('chatBox-content-demo');
            })
        });
    };

    //返回列表
    $(".chat-return").click(function () {
        $(".chatBox-head-one").toggle(1);
        $(".chatBox-head-two").toggle(1);
        $(".chatBox-list").fadeToggle(1);
        $(".chatBox-kuang").fadeToggle(1);
    });

    // 发送表情
    $("#chat-biaoqing").click(function () {
        $(".biaoqing-photo").toggle();
    });
    $(document).click(function () {
        $(".biaoqing-photo").css("display", "none");
    });
    $("#chat-biaoqing").click(function (event) {
        event.stopPropagation();//阻止事件
    });

    $(".emoji-picker-image").each(function () {
        $(this).click(function () {
            var bq = $(this).parent().html();
            var time = new Date().Format("yyyy-MM-dd hh:mm:ss");
            setNewsCookie(CS, clientId, bq, NEWS_TYPE_EMOJI);
            sendData = dataDeal().sendDataType(CS_NEWS, clientId, {'news_type': NEWS_TYPE_EMOJI, 'news': bq});
            $(".chatBox-content-demo").append(sendNewsModdle(time, bq));
            ws.send(sendData);
            //发送后关闭表情框
            $(".biaoqing-photo").toggle();
            scrollBottom('chatBox-content-demo');
        })
    });

    // 发送图片
    function selectImg(pic) {
        var url = 'http://localhost/CustomerService/public/index.php/save_img';
        var data;
        if (!pic.files || !pic.files[0]) {
            return;
        }
        //发送数据
        var reader = new FileReader();
        reader.onload = function (evt) {
            var images = evt.target.result;
            sendImgUrl = imgUrl(images);
            var time = new Date().Format("yyyy-MM-dd hh:mm:ss");
            setNewsCookie(CS, clientId, images, NEWS_TYPE_IMAGE);
            sendData = dataDeal().sendDataType(CS_NEWS, clientId, {'news_type': NEWS_TYPE_IMAGE, 'news': sendImgUrl});
            $(".chatBox-content-demo").append(sendNewsModdle(time, images, NEWS_TYPE_IMAGE));
            ws.send(sendData);
            pic.value = "";
            scrollBottom('chatBox-content-demo');
        };
        reader.readAsDataURL(pic.files[0]);
    }
  // 修改管理员密码
  function edit_password(){
  	$(".edit_password").click(function(){
		layer.open({
		  type: 2,
		  title: '修改管理员密码',
		  shadeClose: true,
		  shade: 0.8,
		  area: ['50%', '45%'],
		  content: '${pageContext.request.contextPath}/view/admin/admin/edit_password.jsp'
		});
	});
  }
</script>


