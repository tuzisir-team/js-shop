package admin.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import admin.model.AdminModel;
import common.controller.Controller;

public class AdminController extends Controller{
	
	public void addRole() throws IOException {
		String checkResult = checkParams("post_type,admin_rote_name");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		String[] functions = request.getParameterValues("function"); 
		String function = "";
        for(int i=0;i<functions.length;i++)
        	function+=functions[i]+","; 
        System.out.println(function);
	}
	
	/**
	 * 添加管理员角色页面
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 */
	public void addRoteView() throws IOException, ServletException, SQLException {
		String checkResult = checkParams("get_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		AdminModel adminModel = new AdminModel();
		ArrayList[] menusList = adminModel.addRoleView();
		request.setAttribute("oneMenusList", menusList[0]);
		request.setAttribute("twoMenusList", menusList[1]);
		forward("./view/admin/admin/add_rote_view.jsp");
	}
	
	/**
	 * 角色列表
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public void roteList() throws ServletException, IOException, SQLException {
		String checkResult = checkParams("get_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		request.setAttribute("adminRoleList", AdminModel.roleList());
		forward("./view/admin/admin/rote_list.jsp");
	}
	
	/**
	 * 管理员功能列表
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public void functionList() throws ServletException, IOException, SQLException {
		String checkResult = checkParams("get_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		AdminModel adminModel = new AdminModel();
		ArrayList[] menusList = adminModel.functionList();
		request.setAttribute("oneMenusList", menusList[0]);
		request.setAttribute("twoMenusList", menusList[1]);
		forward("./view/admin/admin/function_list.jsp");
	}
	
	/**
	 * 修改管理员密码
	 * @throws IOException
	 * @throws SQLException 
	 */
	public void editAdminPassword() throws IOException, SQLException {
		String checkResult = checkParams("old_password,new_password,repeat_password,post_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		if (!request.getParameter("new_password").equals(request.getParameter("repeat_password"))) {
			getOut().println(returnJson(400, "两次输入的密码不一致，请重新输入..."));return;
		}
		switch (AdminModel.editAdminPassword(
				request.getParameter("old_password"),
				request.getParameter("new_password"),
				(String)request.getSession().getAttribute("admin_name")
				)) {
				case 1:
					getOut().println(returnJson(200, "修改密码成功"));
				break;
				case 2:
					getOut().println(returnJson(446, "您输入的密码不正确"));
				break;
				default:
					getOut().println(returnJson(400, "修改密码失败"));
		}
		
	}
}
