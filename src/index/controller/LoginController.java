package index.controller;

import index.model.UsersModel;

import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;

import admin.model.LoginModel;

import common.controller.Controller;
import common.db.model.Admin;
import common.db.model.Users;
import extend.log.Log;
import extend.vertifcode.VertifCode;

public class LoginController extends Controller{

	/**
	 * 登录
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ServletException 
	 */
	public void login() throws IOException, SQLException, ServletException {
//		String checkResult = checkParams("user_name,user_password");
//		// 验证参数
//		if (checkResult != null) {
//			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
//		}
		// 登录逻辑处理
		String returnJson = "{}";
		int resultCode = UsersModel.instance().login(Users.instantce()
				.setUserName(request.getParameter("user_name"))
				.setUserPassword(request.getParameter("user_password")));
		if(resultCode==0){
			returnJson = this.returnJson(400, "您没有注册，请注册后再登录");
		}
		else{
			request.getSession().setAttribute("user_name", request.getParameter("user_name"));
			returnJson = this.returnJson(200, "登录成功");
		}
		getOut().println(returnJson);
	}
	
	/**
	 * 用户注册
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public void register() throws IOException, SQLException {
		String returnJson = "{}";
		if(request.getParameter("user_password").equals(request.getParameter("password_again"))){
			int resultCode = UsersModel.instance().register(Users.instantce()
					.setUserName(request.getParameter("user_name"))
					.setUserPassword(request.getParameter("user_password"))
					.setUserPhone(request.getParameter("user_phone"))
					.setUserEmail(request.getParameter("user_email")));
			switch(resultCode) {
				case -1:
					returnJson = this.returnJson(444, "注册信息冲突");
					break;
				case 0:
					returnJson = this.returnJson(400, "注册信息冲突");
					break;
				case 1:
					returnJson = this.returnJson(200, "注册成功");
					break;
			}
		}
		else{
			returnJson = this.returnJson(445, "密码不统一，请重新输入");
		}
		getOut().println(returnJson);
	}
	
	/**
	 * 退出登录
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void unlogin() throws IOException, ServletException {
//		String checkResult = checkParams("get_type");
//		if (checkResult != null) {
//			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
//		}
		request.getSession().invalidate();
		forward("/route?get_type=user_goods_index");
	}
}
