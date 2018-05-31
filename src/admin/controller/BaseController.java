package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import common.controller.Controller;

public class BaseController extends Controller{
	
	public BaseController() throws ServletException, IOException {
		this.checkSession();
	}
	
	/**
	 * 验证登录session，空则跳转到登录页面
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void checkSession() throws ServletException, IOException {
		if(request.getSession().getAttribute("admin_name") != null)  
		{  
			forward("/view/admin/common/404.jsp");
			return;
		}
	}
}
