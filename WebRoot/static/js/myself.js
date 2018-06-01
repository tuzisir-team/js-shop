/**
 * 数据是否成功
 * @param  {String}  url       [请求的地址]
 * @param  {String}  data      [请求的数据]
 * @param  {String}  data      [传入的回调函数名称]
 * @param  {String}  type      [请求的类型]
 * @param  {String}  async     [异步传输还是同步]
 * @param  {String}  data_type [请求的数据类型]
 */
function is_ok(url="", data="", call_back="result",type="POST", async=true, data_type="json"){
//	layer.load(1);
    var return_result = 99;
    var is_result = is_result;
    var aj = $.ajax({
        type: type,
        url: url,
        async: async,
        dataType:data_type,
        data: data,//也可以是字符串链接"name=张三&sex=1"，建议用对象
        success: function(data){
            console.log(data);
            if(call_back != null){
                var call_back_func = eval(call_back);
                new call_back_func(data);
            }else{
                return_result = data;
            }
        }
    });
    return return_result;
}

function DateDiff(sDate1, sDate2) {  //sDate1和sDate2是yyyy-MM-dd格式

 

    var aDate, oDate1, oDate2, iDays;

    aDate = sDate1.split("-");

    oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);  //转换为yyyy-MM-dd格式

    aDate = sDate2.split("-");

    oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);

    iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24); //把相差的毫秒数转换为天数
    if(oDate1 > oDate2){
        iDays = -iDays;
    }
    return iDays;  //返回相差天数

}


function layer_alert(title="提示信息",type=4,btn=['确定']){
    layer.alert(title, {
        skin: 'layui-layer-lan'
        ,closeBtn: 0
        ,anim: type //动画类型
        ,btn:btn
      });
}

//用于删除某一节点下的子元素
function del_children(id){
    var div = document.getElementById(id);
    while(div.hasChildNodes()) //当div下还存在子节点时 循环继续
    {
        div.removeChild(div.firstChild);
    }
}

//休眠
function sleep(numberMillis) { 
    var now = new Date(); 
    var exitTime = now.getTime() + numberMillis; 
    while (true) { 
        now = new Date(); 
        if (now.getTime() > exitTime) 
        return; 
    } 
}

//验证是否为正数
function check_money(n){
  return /^\d+(\.\d{1,2})?$/.test(n+"");
}
(function($) {
    $.extend({
        myTime: {
            /**
             * 当前时间戳
             * @return <int>        unix时间戳(秒)   
             */
            CurTime: function(){
                return Date.parse(new Date())/1000;
            },
            /**               
             * 日期 转换为 Unix时间戳 
             * @param <string> 2014-01-01 20:20:20  日期格式               
             * @return <int>        unix时间戳(秒)               
             */
            DateToUnix: function(string) {
                var f = string.split(' ', 2);
                var d = (f[0] ? f[0] : '').split('-', 3);
                var t = (f[1] ? f[1] : '').split(':', 3);
                return (new Date(
                        parseInt(d[0], 10) || null,
                        (parseInt(d[1], 10) || 1) - 1,
                        parseInt(d[2], 10) || null,
                        parseInt(t[0], 10) || null,
                        parseInt(t[1], 10) || null,
                        parseInt(t[2], 10) || null
                        )).getTime() / 1000;
            },
            /**               
             * 时间戳转换日期               
             * @param <int> unixTime    待时间戳(秒)               
             * @param <bool> isFull    返回完整时间(Y-m-d 或者 Y-m-d H:i:s)               
             * @param <int>  timeZone   时区               
             */
            UnixToDate: function(unixTime, isFull, timeZone) {
                if (typeof (timeZone) == 'number')
                {
                    unixTime = parseInt(unixTime) + parseInt(timeZone) * 60 * 60;
                }
                var time = new Date(unixTime * 1000);
                var ymdhis = "";
                ymdhis += time.getUTCFullYear() + "-";
                ymdhis += (time.getUTCMonth()+1) + "-";
                ymdhis += time.getUTCDate();
                var hours =   time.getUTCHours();
                var minutes = time.getUTCMinutes();
                var seconds = time.getSeconds();
                if(hours < 10){
                	hours = "0"+hours;
                }
                if(minutes < 10){
                	minutes = "0"+minutes;
                }
                if(seconds < 10){
                	console.log(seconds);
                	seconds = "0"+seconds;
                	console.log("我执行了");
                }
                if (isFull === true)
                {
                    ymdhis += " " + hours + ":";
                    ymdhis += minutes + ":";
                    ymdhis += seconds;
                }
                return ymdhis;
            }
        }
    });
})(jQuery); 













