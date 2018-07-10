package admin.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;

import admin.model.AdminModel;
import admin.model.GoodsModel;
import common.controller.Controller;
import common.db.model.Admin;
import extend.encryption.EncryptionTool;
import extend.page.Page;

public class AdminController extends Controller{
	
	/**
	 * 更改管理员状态
	 * @throws IOException
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public void changeAdminStatus() throws IOException, NumberFormatException, SQLException {
		String checkResult = checkParams("admin_id,admin_status,post_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 更改状态
		int changeAdminRoleStatus = AdminModel.changeAdminStatus(Integer.parseInt(request.getParameter("admin_id")), 
				Integer.parseInt(request.getParameter("admin_status")));
		if (changeAdminRoleStatus == 1) {
			getOut().println(returnJson(200, "更改管理员状态成功"));return;
		}
		getOut().println(returnJson(400, "更改管理员状态失败"));return;
	}
	
	/**
	 * 删除管理员
	 * @throws IOException
	 * @throws SQLException 
	 */
	public void delAdmin() throws IOException, SQLException {
		String checkResult = checkParams("post_type,admin_id");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		int adminId = Integer.parseInt(request.getParameter("admin_id"));
		// 重置管理员密码
		switch (AdminModel.delAdmin(adminId)) {
			case 1:
				getOut().println(returnJson(200, "删除管理员成功。"));
				break;
			default:
				getOut().println(returnJson(400, "删除管理员失败。"));
		}
	}
	
	/**
	 * 重置管理员密码
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public void resetAdminPassword() throws SQLException, IOException {
		String checkResult = checkParams("post_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		int adminId = Integer.parseInt(request.getParameter("admin_id"));
		// 重置管理员密码
		switch (AdminModel.resetAdminPassword(adminId)) {
			case 1:
				getOut().println(returnJson(200, "重置密码成功。"));
				break;
			default:
				getOut().println(returnJson(400, "重置密码失败。"));
		}
	}
	
	/**
	 * 添加管理员
	 * @throws IOException
	 * @throws SQLException
	 */
	public void editAdmin() throws IOException, SQLException {
		String checkResult = checkParams("post_type,admin_role_id,admin_name");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 获取参数
		String adminName = request.getParameter("admin_name");
		int adminRoleId = Integer.parseInt(request.getParameter("admin_role_id"));
		int adminId = Integer.parseInt(request.getParameter("admin_id"));
		// 添加管理员
		switch (AdminModel.editAdmin(adminName, adminRoleId, adminId)) {
			case 1:
				getOut().println(returnJson(200, "修改管理员成功。"));
				break;
			case 2:
				getOut().println(returnJson(400, "已经有这个管理员名称，不能重复添加。"));
				break;
			default:
				getOut().println(returnJson(400, "修改管理员失败。"));
		}
	}
	
	/**
	 * 修改管理员页面
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 * @throws ServletException 
	 */
	public void editAdminView() throws IOException, NumberFormatException, SQLException, ServletException {
		String checkResult = checkParams("get_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 获取管理员信息
		Admin adminInfo = AdminModel.getAdminInfo(
					Integer.parseInt(request.getParameter("admin_id"))
				);
		request.setAttribute("adminInfo", adminInfo);
		// 获取所有角色
		ArrayList adminRoleList = AdminModel.roleList();
		request.setAttribute("adminRoleList", adminRoleList);
		forward("./view/admin/admin/edit_admin_view.jsp");
	}
	
	/**
	 * 添加管理员
	 * @throws IOException
	 * @throws SQLException
	 */
	public void addAdmin() throws IOException, SQLException {
		String checkResult = checkParams("post_type,admin_role_id,admin_name");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 获取参数
		String adminName = request.getParameter("admin_name");
		String adminPassword = EncryptionTool.md5("123456");
		int adminRoleId = Integer.parseInt(request.getParameter("admin_role_id"));
		// 添加管理员
		switch (AdminModel.addAdmin(adminName, adminPassword, adminRoleId)) {
			case 1:
				getOut().println(returnJson(200, "添加管理员成功。"));
				break;
			case 2:
				getOut().println(returnJson(400, "已经有这个管理员名称，不能重复添加。"));
				break;
			default:
				getOut().println(returnJson(400, "添加管理员失败。"));
		}
	}
	
	/**
	 * 添加管理员的页面
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ServletException 
	 */
	public void addAdminView() throws IOException, SQLException, ServletException {
		String checkResult = checkParams("get_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		ArrayList adminRoleList = AdminModel.roleList();
		request.setAttribute("adminRoleList", adminRoleList);
		forward("./view/admin/admin/add_admin_view.jsp");
	}
	
	/**
	 * 管理员列表
	 * @throws IOException
	 * @throws SQLException 
	 * @throws ServletException 
	 */
	public void adminList() throws IOException, SQLException, ServletException {
		String strcurpage = request.getParameter("currentpage"); // 接收当前页面值
		String checkResult = checkParams("get_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 获取管理员列表
		ArrayList adminList = AdminModel.adminList();
		if (adminList.isEmpty()) {
			forward("./view/admin/admin/admin_list.jsp");
		}
		Page viewPage = Page.createPage(adminList, strcurpage).setRoute("admin_admin_list"); // 获取请求页面对象
		request.setAttribute("viewpage", viewPage);
		forward("./view/admin/admin/admin_list.jsp");
	}
	
	/**
	 * 改变角色状态
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public void changeAdminRoleStatus () throws IOException, NumberFormatException, SQLException {
		String checkResult = checkParams("admin_role_id,admin_role_status,post_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 更改状态
		int changeAdminRoleStatus = AdminModel.changeAdminRoleStatus(Integer.parseInt(request.getParameter("admin_role_id")), 
				Integer.parseInt(request.getParameter("admin_role_status")));
		if (changeAdminRoleStatus == 1) {
			getOut().println(returnJson(200, "更改角色状态成功"));return;
		}
		getOut().println(returnJson(400, "更改角色状态失败"));return;
	}
	
	/**
	 * @throws IOException 
	 * @throws SQLException 
	 * 
	 */
	public void delAdminRole() throws IOException, SQLException {
		String checkResult = checkParams("post_type,admin_role_id");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 获取参数信息
		int adminRoleId = Integer.parseInt(request.getParameter("admin_role_id"));
		// 删除角色(如果被分配了这个角色，清空所有管理员角色)
		switch (AdminModel.delAdminRole(adminRoleId)) {
			case 1:
				getOut().println(returnJson(200, "删除角色成功。"));
				break;
			case 2:
				getOut().println(returnJson(400, "删除角色异常。"));
				break;
			default:
				getOut().println(returnJson(400, "未知错误"));
		}
	}
	
	/**
	 * 修改角色信息
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public void editAdminRole() throws IOException, SQLException {
		String checkResult = checkParams("post_type,admin_role_id");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 获取参数信息
		String adminRoleJurisdiction = request.getParameter("admin_role_jurisdiction");
		String adminRoleName = request.getParameter("admin_rote_name");
		int adminRoleId = Integer.parseInt(request.getParameter("admin_role_id"));
		// 修改角色信息
		switch (AdminModel.editAdminRole(adminRoleName, adminRoleId, adminRoleJurisdiction)) {
			case 1:
				getOut().println(returnJson(200, "修改角色成功。"));
				break;
			case 2:
				getOut().println(returnJson(400, "已经有这个角色，不能重复添加。"));
				break;
			default:
				getOut().println(returnJson(400, "修改角色失败。"));
		}
	}
	
	/**
	 * 修改管理员角色页面
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 */
	public void editRoteView() throws IOException, ServletException, SQLException {
		String checkResult = checkParams("get_type,admin_role_id");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		AdminModel adminModel = new AdminModel();
		// 获取所有功能
		ArrayList[] menusList = adminModel.addRoleView();
		// 获取当前角色的功能
		int adminRoleId = Integer.parseInt(request.getParameter("admin_role_id"));
		ResultSet adminRole = AdminModel.getAdminRoleJurisdiction(adminRoleId);
		request.setAttribute("oneMenusList", menusList[0]);
		request.setAttribute("twoMenusList", menusList[1]);
		request.setAttribute("adminRoleJurisdiction", ','+adminRole.getString(3)+',');
		request.setAttribute("adminRoleName", adminRole.getString(2));
		request.setAttribute("adminRoleId", adminRoleId);
		forward("./view/admin/admin/edit_rote_view.jsp");
	}
	
	/**
	 * 添加管理员角色
	 * @throws IOException
	 * @throws SQLException
	 */
	public void addRole() throws IOException, SQLException {
		String checkResult = checkParams("post_type,admin_rote_name");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		String adminRoleJurisdiction = request.getParameter("admin_role_jurisdiction");
		String adminRoteName = request.getParameter("admin_rote_name");
		// 保存到库
		switch (AdminModel.addRole(adminRoteName, adminRoleJurisdiction)) {
			case 1:
				getOut().println(returnJson(200, "添加角色成功。"));
				break;
			case 2:
				getOut().println(returnJson(400, "已经有这个角色，不能重复添加。"));
				break;
			default:
				getOut().println(returnJson(400, "添加角色失败。"));
		}
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
		String strcurpage = request.getParameter("currentpage"); // 接收当前页面值
		String checkResult = checkParams("get_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		Page viewPage = Page.createPage(AdminModel.roleList(), strcurpage).setRoute("admin_rote_list"); // 获取请求页面对象
		request.setAttribute("viewpage", viewPage);
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
				EncryptionTool.md5(request.getParameter("old_password")),
				EncryptionTool.md5(request.getParameter("new_password")),
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
