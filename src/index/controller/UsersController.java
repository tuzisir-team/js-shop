package index.controller;

import index.model.OrdersModel;
import index.model.UsersModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;

import admin.model.LoginModel;

import common.controller.Controller;
import common.db.model.UserAddress;
import common.db.model.Users;
import extend.email.EmailFactory;
import extend.log.Log;
import extend.vertifcode.VertifCode;

public class UsersController extends Controller{
	private String userLoginStatus;//拿到登录状态
	/**
	 * 登录
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ServletException 
	 */
	public void login() throws IOException, SQLException, ServletException {
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
			request.getSession().setAttribute("user_id",resultCode);
			returnJson = this.returnJson(200, "登录成功",userLoginStatus);
		}
		getOut().println(returnJson);
	}
	/**
	 * 登录状态
	 */
	public void loginStatus() throws IOException, SQLException, ServletException {
		userLoginStatus=request.getParameter("user_login_status");
		request.setAttribute("user_login_status", userLoginStatus);
		forward("/view/index/login.jsp");
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
					EmailFactory.instance().send(8,EmailFactory.REGISTER); // 发送邮件
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
		request.getSession().invalidate();
		forward("/route?get_type=user_goods_index");
	}
	/**
	 * 加入收货地址
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void addUsersAddress() throws IOException, SQLException, ServletException {
		UsersModel usersModel = new UsersModel();
		int userId;
		try {
			userId=(Integer)request.getSession().getAttribute("user_id");
		} catch(Exception e) {
			forward("/view/index/login.jsp");
			return;
		}
		int resultCode=usersModel.addUsersAddress(userId,request.getParameter("user_address_name"));
		if (resultCode == 1) {
			getOut().println(returnJson(200, "加入地址成功"));return;
		}
		else{
			getOut().println(returnJson(400, "加入地址失败"));return;
		}
	}
	
}