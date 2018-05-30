package index.controller;

import index.model.UsersModel;

import java.sql.SQLException;

import common.controller.Controller;
import common.db.model.Users;
import extend.log.Log;

public class UsersController1 extends Controller{

	/**
	 * 注册
	 * @throws SQLException 
	 */
	public void register() throws SQLException {
			UsersModel.instance().register(Users.instantce()
					.setUserName(request.getParameter("user_name"))
					.setUserPassword(request.getParameter("user_password"))
					.setUserPhone(request.getParameter("user_phone"))
					.setUserEmail(request.getParameter("user_email")));
	}
	
	/**
	 * 登录
	 * @throws SQLException 
	 */
	public void login() throws SQLException {
		UsersModel.instance().login(Users.instantce()
				.setUserName(request.getParameter("user_name"))
				.setUserPassword(request.getParameter("user_password"))
				);
		String name=request.getParameter("user_name");
		String psw=request.getParameter("user_password");
		String Scode=request.getSession().toString();
		
	}
	/**
	 * 注销
	 */
	public void exit(){
		
	}
	/**
	 * 修改用户信息
	 */
	public void updata(){
		
	}
}
