/**
 * 数据是否成功
 * @param  {String}  url       [请求的地址]
 * @param  {String}  data      [请求的数据]
 * @param  {String}  data      [传入的回调函数名称]
 * @param  {String}  type      [请求的类型]
 * @param  {String}  async     [异步传输还是同步]
 * @param  {String}  data_type [请求的数据类型]
 */
function is_ok(url="", data="", call_back="result", type="POST", async=true, data_type="json"){
	layer.load();
    var return_result = 99;
    var is_result = is_result;
    var aj = $.ajax({
        type: type,
        url: url,
        async: async,
        dataType:data_type,
        data: data,//也可以是字符串链接"name=张三&sex=1"，建议用对象
        success: function(data){
        	layer.closeAll('loading');            
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

/**
 * 展示页面
 */
function newView(url, title="", area=['50%', '50%'], type=2) {
    layer.open({
      title: title,
      type: type, 
      area: area,
      content: url,
      end: function(){
        location.reload();
      }
    }); 
}


function layerOpen(title, formId, callBack, btn, area, params) {
    layer.open({
        title: title,
        type: 1,
        content: $(formId),
        btn: btn,
        area: area,
        yes: function (index, layero) {
            var addFormData = $(formId).serializeArray();
            callBack(addFormData, params);
            layer.close(index);
        }
    });
}

/**
 * 时间戳转日期
 */
function timestampToTime(timestamp) {
    var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    return Y+M+D+h+m+s;
}