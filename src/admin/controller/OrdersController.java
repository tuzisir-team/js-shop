package admin.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;

import admin.model.OrdersModel;
import common.controller.Controller;
import extend.page.Page;

public class OrdersController extends Controller{
	
	/**
	 * 订单统计
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws SQLException 
	 */
	public void orderCount() throws ServletException, IOException, SQLException {
		HashMap<String, String> orderCountData = OrdersModel.orderCount();
		request.setAttribute("orderCountData", orderCountData);
		forward("/view/admin/order/order_count.jsp");
	}
	
	/**
	 * 本周订单统计
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ServletException 
	 */
	public void weekCount() throws IOException, SQLException, ServletException {
		String checkResult = checkParams("get_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		int[] days = OrdersModel.weekCount();
		request.setAttribute("days", days);
		forward("/view/admin/index/main.jsp");
	}
	
	/**
	 * 订单查询
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ServletException 
	 */
	public void orderFind() throws IOException, SQLException, ServletException {
		String checkResult = checkParams("out_trade_no,post_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		String strcurpage = request.getParameter("currentpage"); // 接收当前页面值
		// 根据订单号查询订单
		ArrayList ordersList = OrdersModel.orderFind(request.getParameter("out_trade_no"));
		request.setAttribute("isPage", 0);
		if (ordersList.isEmpty()) {
			forward("/view/admin/order/order_list.jsp");	
		}
		Page viewPage = Page.createPage(ordersList, strcurpage).setRoute("admin_orders_list");//获取请求页面对象
		request.setAttribute("viewpage", viewPage);
		forward("/view/admin/order/order_list.jsp");
	}
	
	/**
	 * 订单详情页面
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void orderDetails() throws NumberFormatException, SQLException, ServletException, IOException {
		String strcurpage = request.getParameter("currentpage"); // 接收当前页面值
		ArrayList ordersDetailsList = OrdersModel.orderDetails(Integer.parseInt(request.getParameter("order_id")));
		request.setAttribute("ordersDetailsList", ordersDetailsList);
		forward("/view/admin/order/order_details.jsp");
	}
	
	/**
	 * 订单列表
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public void ordersList() throws ServletException, IOException, SQLException {
		String strcurpage = request.getParameter("currentpage"); // 接收当前页面值
		ArrayList ordersList = OrdersModel.ordersList();
		if (ordersList.isEmpty()) {
			forward("/view/admin/order/order_list.jsp");
		}
		Page viewPage = Page.createPage(ordersList, strcurpage).setRoute("admin_orders_list");//获取请求页面对象
		request.setAttribute("viewpage", viewPage);
		forward("/view/admin/order/order_list.jsp");
	}
	
}
