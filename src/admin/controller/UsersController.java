package admin.controller;

import common.servlet.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;

import admin.model.UsersModel;
import common.controller.Controller;
import common.db.model.Model;
import common.db.model.Users;
import common.db.model.UserAddress;
import extend.log.Log;
import extend.page.Page;

public class UsersController extends BaseController{

	/**
	 * 用户购物车
	 * @throws IOException
	 * @throws SQLException
	 * @throws ServletException
	 */
	public void shoppingCart() throws IOException, SQLException, ServletException {
		String checkResult = checkParams("user_id,get_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		ArrayList shoppingCartList = UsersModel.shoppingCart(request.getParameter("user_id"));
		request.setAttribute("shoppingCartList", shoppingCartList); //将查询出来的地址放入
		forward("/view/admin/user/shopping_cart.jsp");
	}
	
	/**
	 * 用户地址
	 * @throws IOException
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 * @throws ServletException 
	 */
	public void userAddressList() throws IOException, NumberFormatException, SQLException, ServletException {
		String checkResult = checkParams("user_id,get_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		ArrayList addressList = UsersModel.userAddressList(UserAddress.instantce().
				setUserId(Integer.parseInt(request.getParameter("user_id"))));
        request.setAttribute("addressList", addressList); //将查询出来的地址放入
		forward("/view/admin/user/user_address.jsp");
	}
	
	/**
	 * 用户列表
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public void userList() throws ServletException, IOException, SQLException {
		String strcurpage = request.getParameter("currentpage");//接收当前页面值
        Page viewPage = Page.createPage(UsersModel.userList(), strcurpage).setRoute("user_list");;//获取请求页面对象
        request.setAttribute("viewpage", viewPage);//将页面对象放入request中
		forward("/view/admin/user/user_list.jsp");
	}
	
	/**
	 * 改变用户状态
	 * @throws IOException
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public void changeUserStatus() throws IOException, NumberFormatException, SQLException {
		String checkResult = checkParams("user_status,user_id,post_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 更新用户状态
		int changeUserStatusResult = UsersModel.changeUserStatus(Users.instantce().
				setUserId(Integer.parseInt(request.getParameter("user_id"))).
				setUserStatus(Integer.parseInt(request.getParameter("user_status"))).end()
				);
		if (changeUserStatusResult == 1) {
			getOut().println(returnJson(200, "更改用户状态成功"));return;
		}
		getOut().println(returnJson(400, "更改状态失败"));return;
	}
	
	public UsersController() throws ServletException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	
}