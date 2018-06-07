package common.route;

import java.lang.reflect.Method;

import config.GetConfig;
import extend.log.Log;

public class Route {
	
	private String route = null;

	/**
	 * 写起来好看
	 * @return
	 */
	public static Route instance() {
		return new Route();
	}
	
	/**
	 * 查找get路由
	 * @param requestType
	 * @return
	 */
	public Route selectGet(String requestType) {
		route = GetConfig.instance(GetConfig.GETROUTES).getStringConfig(requestType);
		Log.instance().notice("get请求:get_type="+requestType+"请求地址:"+route);
		return this;
	}
	
	/**
	 * 查找post路由
	 * @param requestType
	 * @return
	 */
	public Route selectPost(String requestType) {
		route = GetConfig.instance(GetConfig.POSTROUTES).getStringConfig(requestType);
		Log.instance().notice("post请求:post_type="+requestType+"请求地址:"+route);
		return this;
	}
	
	/**
	 * 执行路由
	 */
	public void go() {
		Log.instance().debug("执行的方法:" + route);
		Class getClass = null;
		try {
			String [] routeDeal = route.split("@");
			getClass = Class.forName(routeDeal[0]);
			Log.instance().notice(routeDeal[0]);
			Method clazz = getClass.getMethod(routeDeal[1]);
	        clazz.invoke(getClass.newInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.instance().debug("执行路由选择go方法的时候异常" + route);
		} 
	}
	
}
