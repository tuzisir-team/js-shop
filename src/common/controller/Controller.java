package common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import extend.log.Log;
import extend.myjson.MyJson;

public class Controller {
	public static HttpServletRequest request;
	public static HttpServletResponse response;
	public PrintWriter out = null;
	protected static HttpServlet servlet;
	
	public static void setServlet(HttpServlet servlet) {
		Controller.servlet = servlet;
	}
	
	public static void setRequest(HttpServletRequest request) {
		Controller.request = request;
	}
	
	public static void setResponse(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		Controller.response = response;
	}
	
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
		for(Enumeration e=res;e.hasMoreElements();){
		       String thisName=e.nextElement().toString();
		       if (!Arrays.asList(params).contains(thisName)) {
		    	   resultCheck = thisName;
		    	   Log.instance().error("管理员登录时缺少必要参数:" + thisName);
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
	
}