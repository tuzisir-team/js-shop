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
<<<<<<< HEAD
	public void register() {
		UsersModel.instance().register(Users.instantce()
				.setUserName(request.getParameter("user_name"))
				.setUserPassword(request.getParameter("user_password"))
				.setUserPhone(request.getParameter("user_phone"))
				.setUserEmail(request.getParameter("user_email")));

=======
	public void register() throws SQLException {
			UsersModel.instance().register(Users.instantce()
					.setUserName(request.getParameter("user_name"))
					.setUserPassword(request.getParameter("user_password"))
					.setUserPhone(request.getParameter("user_phone"))
					.setUserEmail(request.getParameter("user_email")));
>>>>>>> 8558109cfead70e214ec8a28e6d9b166fdc24c57
	}
	
	/**
	 * 登录
	 */
	public void login() {
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
