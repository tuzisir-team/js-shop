package common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import extend.log.Log;
import extend.myjson.MyJson;

public class Controller {
	public static HttpServletRequest request;
	public static HttpServletResponse response;
	public static HashMap<String, String> parameters;
	public PrintWriter out = null;
	protected static HttpServlet servlet;
	
	/**
	 * 设置servlet
	 * @param servlet
	 */
	public static void setServlet(HttpServlet servlet) {
		Controller.servlet = servlet;
	}
	
	/**
	 * 设置原始请求数据
	 * @param parameters
	 */
	public static void setParameters(HashMap<String, String> parameters) {
		Controller.parameters = parameters;
	}
	
	/**
	 * 设置原始request对象
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	public static void setRequest(HttpServletRequest request) throws UnsupportedEncodingException {
		Controller.request = request;
		Controller.request.setCharacterEncoding("utf-8");
	}
	
	/**
	 * 设置原始response对象
	 * @param response
	 * @throws IOException
	 */
	public static void setResponse(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		Controller.response = response;
	}
	
	/**
	 * 获得out对象
	 * @return
	 * @throws IOException
	 */
	public PrintWriter getOut() throws IOException {
		if (this.out == null) {
			this.out = response.getWriter();
		}
		return this.out;
	}
	
	/**
	 * 终止servlet的运行
	 */
	public void die() {
		this.servlet.destroy();
	}

	/**
	 * 验证参数
	 * @param checkParams
	 * @return
	 */
	public String checkParams(String checkParams) {
		String [] params = checkParams.split(",");
		String resultCheck = null;
		Enumeration<String> res = request.getParameterNames();
		String getParamsStr = "";
		String[] getParams;
		for(Enumeration e=res;e.hasMoreElements();){
			String thisName=e.nextElement().toString();
			System.out.println(thisName);
			getParamsStr += thisName + ",";
		}
		getParams = getParamsStr.substring(0, getParamsStr.length()-1).split(",");
        for(String param: params){
        	if (!Arrays.asList(getParams).contains(param)) {
 	    	   resultCheck = param;
 	    	   Log.instance().error("管理员登录时缺少必要参数:" + param);
 	    	   break;
 	        }
        }
		return resultCheck;
	}
	
	/**
	 * 设置返回的json格式
	 * @param i 返回的状态码 200:成功 400:失败 444:参数错误 445:验证码错误
	 * @param msg 返回的提示信息
	 * @param Data 返回的数据
	 * @return
	 */
	public String returnJson(int i, String... msg) {
		MyJson myJson = MyJson.instance().setKeyValue("code", Integer.toString(i));
		if (msg.length == 1) {
			myJson.setKeyValue("msg", msg[0]);
		} else if(msg.length == 2) {
			myJson.setKeyValue("msg", msg[0]).setKeyValue("data", msg[1]);
		} else {
			return "{}";
		}
		return myJson.toString();
	}
	
	/**
	 * 
	 * @param url
	 * @throws IOException
	 * response.sendRedirect(“/index.jsp”);页面的路径是相对路径。sendRedirect可以将页面跳转到任何页面，
	 * 不一定局限于web应用中，如：response.sendRedirect（”http://www.baidu.com“）； 
	 * 跳转后浏览器地址发生变化，只能在url总带parameter或者放在session中，无法使用request.setAttribute来传递值。 
	 */
	public void redirect(String url) throws IOException {
		response.sendRedirect(url);
	}
	
	/**
	 * request.getRequestDispatcher(“/index.jsp”).forward(request,response);页面的路径是对路径。
	 * forward方式只能跳转到本web应用中的页面上。跳转后地址栏不会发生变化。使用这种方式跳转，
	 * 传值可以使用三种方法：url中带parameter，session，request.setAttribute
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 */
	public void forward(String url) throws ServletException, IOException {
        request.getRequestDispatcher(url).forward(request, response); 
	}
	
}
