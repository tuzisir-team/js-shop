package admin.controller;

import common.servlet.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import common.controller.Controller;
import common.db.model.Model;
import common.db.model.Users;
import extend.log.Log;

public class UsersController extends BaseController{

	public void userList() throws ServletException, IOException {
		System.out.println("我执行了");
		forward("/view/admin/user/user_list.jsp");
	}
	public UsersController() throws ServletException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	
}