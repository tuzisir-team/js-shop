package admin.controller;

import java.io.IOException;
import java.sql.SQLException;

import admin.model.AdminModel;
import common.controller.Controller;

public class AdminController extends Controller{
	
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
