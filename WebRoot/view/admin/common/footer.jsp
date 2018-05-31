<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="layui-footer footer footer-demo" id="admin-footer">
    <div class="layui-main">
        <p>2018 &copy;
            <a href="#">tuzisir</a>
        </p>
    </div>
</div>
<div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
</div>
<div class="site-mobile-shade"></div>


<script>
    layui.use('layer', function () {
        var $ = layui.jquery,
            layer = layui.layer;

        $('#video1').on('click', function () {
            layer.open({
                title: 'YouTube',
                maxmin: true,
                type: 2,
                content: 'video.html',
                area: ['800px', '500px']
            });
        });

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
</div>
</body>

</html>
