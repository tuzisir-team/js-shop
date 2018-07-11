package index.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import common.controller.Controller;
import index.model.AddressModel;

public class AddressController extends Controller{
	
	/**
	 * 删除地址
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public void delAddress() throws IOException, SQLException {
		String checkResult = checkParams("post_type,user_address_id");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		int userAddressId = Integer.parseInt(request.getParameter("user_address_id"));
		// 删除地址
		if (AddressModel.delAddress(userAddressId) == 1) {
			getOut().println(returnJson(200, "删除地址成功"));return;
		}
		getOut().println(returnJson(400, "删除地址失败"));return;		
	}
	
	/**
	 * @throws SQLException 
	 * 设置默认地址
	 * @throws IOException 
	 * @throws  
	 */
	public void setAddress() throws IOException, SQLException  {
		String checkResult = checkParams("post_type,user_address_id");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		int userId = (int)request.getSession().getAttribute("user_id");
		int userAddressId = Integer.parseInt(request.getParameter("user_address_id"));
		// 设置默认地址
		int setReturn = AddressModel.setAddress(userId, userAddressId);
		if (setReturn == 1) {
			getOut().println(returnJson(200, "设置默认地址成功"));return;
		} 
		getOut().println(returnJson(400, "设置默认地址失败"));return;
	}
	
	/**
	 * 收货地址页面
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws SQLException 
	 */
	public void addressView() throws IOException, ServletException, SQLException {
		request.getSession().setAttribute("user_id",5);
		String checkResult = checkParams("get_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		int userId = (int) request.getSession().getAttribute("user_id");
		// 查找所有收货地址
		ArrayList addressList = AddressModel.addressList(userId);
		request.setAttribute("addressList", addressList);
		// 跳转页面
		forward("./view/index/address.jsp");
	}
}
