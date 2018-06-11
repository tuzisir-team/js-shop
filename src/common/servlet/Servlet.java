package common.servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.Policy.Parameters;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.controller.Controller;
import common.route.Route;
import config.GetConfig;
import extend.file.MyselfFile;
import extend.log.Log;
import extend.time.Time;

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
	
	/**
	 * 路由选择
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void routeSelect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Controller.setRequest(request);
		Controller.setResponse(response);
		Controller.setServlet(this);
		if (request.getParameter("get_type") != null) { // 当get请求时
			Route.instance().selectGet(request.getParameter("get_type")).go();
		} else if (request.getParameter("post_type") != null){ // post请求
			Route.instance().selectPost(request.getParameter("post_type")).go();
		} else {
			HashMap<String, String> params = this.getParams(request, response);
			Controller.setParameters(params);
			if (params.get("get_type") != null) {
				Route.instance().selectGet(params.get("get_type")).go();
			} else if (params.get("post_type") != null) {
				Route.instance().selectPost(params.get("post_type")).go();
			}
		}  
	}
		
	/**
	 * 获取原始参数
	 * @return
	 */
	private HashMap<String, String> getParams(HttpServletRequest request, HttpServletResponse response) {
		DiskFileItemFactory factory = new DiskFileItemFactory();  
        ServletFileUpload diskFileUpload = new ServletFileUpload(factory);  
        diskFileUpload.setHeaderEncoding("utf-8");  
        // 定义一个hashmap存放请求参数  
        HashMap<String, String> parameters = new HashMap<String, String>();  
        List paramItems = null; 
        try {  
	        paramItems = diskFileUpload.parseRequest(request);  
	        Iterator i = paramItems.iterator();  
	        // 依次出来每个文件 请求参数  
	        while (i.hasNext()) {  
	            FileItem fi = (FileItem) i.next();  
	            if (!fi.isFormField()) { // 判断是文件还是 value值  
	            	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	            	// 放入
   	        		if (MyselfFile.instance(fi).checkImage().getIsWriter()) {
   	        			// 先删除此文件
   	        			String filePath = getServletContext().getRealPath("/")
	                            + "static/img/goods/";
   	        			String fileName = Integer.toString(Time.getDateTime()) + ".png";
	        			if (MyselfFile.instance(fi).setFilePath(filePath).setFileName(fileName).createFile()) {
	        				parameters.put("imgFile",  basePath + "static/img/goods/" + fileName);
	        			}
	        		}
	            } else {  
	                String name = fi.getFieldName();  
	                String value = null;  
	                value = fi.getString("utf-8");
	                parameters.put(name, value);  
	            }  
	        } 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return parameters;
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
