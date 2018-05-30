package index.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import common.db.model.Users;

import index.model.*;

public class UsersController extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		
		this.doPost(request,response);
	
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String postType=request.getParameter("postType");
		
		if (postType.equals("register")) {
			this.register(Users.instantce()
					.setUserName(request.getParameter("user_name"))
					.setUserPassword(request.getParameter("user_password"))
					.setUserPhone(request.getParameter("user_phone"))
					.setUserEmail(request.getParameter("user_email"))
					
				);
		//获得session
		      HttpSession session = request.getSession();
		//获得session中保留的信息
		     String sCode = session.getAttribute("saveCode").toString();
		//给session中存储数据
		     session.setAttribute("name","JACK");
		}
	}
	
	protected void register (Users users) {
		UsersModel.instance().register(users);
		
	}
}

