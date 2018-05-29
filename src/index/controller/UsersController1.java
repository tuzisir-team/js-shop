package index.controller;

import index.model.UsersModel;
import common.controller.Controller;
import common.db.model.Users;
import extend.log.Log;

public class UsersController1 extends Controller{

	/**
	 * 注册
	 */
	public void register() {
			UsersModel.instance().register(Users.instantce()
					.setUserName(request.getParameter("user_name"))
					.setUserPassword(request.getParameter("user_password"))
					.setUserPhone(request.getParameter("user_phone"))
					.setUserEmail(request.getParameter("user_email")));
	}
	
	/**
	 * 登录
	 */
	public void login() {
		Log.instance().notice("我是登录");
	}
}
