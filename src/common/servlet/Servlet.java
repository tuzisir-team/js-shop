package common.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.controller.UsersController;
import common.controller.Controller;
import config.GetConfig;
import extend.log.Log;

public class Servlet extends HttpServlet {
	public HttpServletRequest request;
	public HttpServletResponse response;

	/**
		 * Constructor of the object.
		 */
	public Servlet() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.routeSelect(request, response); 
	}
	
	protected void routeSelect(HttpServletRequest request, HttpServletResponse response) {
		String requestType = null;
		String route = null;
		Controller.setRequest(request);
		Controller.setResponse(response);
		if (request.getParameter("getType") != null) {
			requestType = request.getParameter("getType");
			route = GetConfig.instance(GetConfig.GETROUTES).getStringConfig(requestType);
			Log.instance().notice("get请求:getType="+requestType+"请求地址:"+route);
		} else {
			requestType = request.getParameter("postType");
			route = GetConfig.instance(GetConfig.POSTROUTES).getStringConfig(requestType);
			Log.instance().notice("post请求:postType="+requestType+"请求地址:"+route);
		}
		if (route == null) {
			return;
		}
		Class getClass = null;
		try {
			String [] routeDeal = route.split("@");
			getClass = Class.forName(routeDeal[0]);
			 Method clazz = getClass.getMethod(routeDeal[1]);
	        clazz.invoke(getClass.newInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.routeSelect(request, response); 
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
