package admin.controller;

import common.servlet.Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import admin.model.UsersModel;
import common.controller.Controller;
import common.db.model.Model;
import common.db.model.Users;
import extend.log.Log;
import extend.page.Page;

public class UsersController extends BaseController{

	/**
	 * 用户列表
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public void userList() throws ServletException, IOException, SQLException {
		String strcurpage = request.getParameter("currentpage");//接收当前页面值
        int currentPage;//通常第一次请求currentpage值为空这时设置默认值为1
        if(strcurpage!=null) {
            currentPage = Integer.parseInt(strcurpage);
        }else{ 
                currentPage = 1;
        }
        int pageSize = 1;//页面大小
        Page viewPage = new UsersModel().construPage(currentPage, pageSize);//获取请求页面对象
        request.setAttribute("viewpage", viewPage);//将页面对象放入request中
		forward("/view/admin/user/user_list.jsp");
	}
	public UsersController() throws ServletException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	
}