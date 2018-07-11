package index.controller;

import index.model.GoodsModel;
import index.model.OrdersModel;
import index.model.UsersModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import common.controller.Controller;
import common.db.model.Users;

public class OrderController extends Controller{
	/**
	 * 加入购物车
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void addShoppingCart() throws ServletException, IOException, SQLException{
		OrdersModel ordersModel= new OrdersModel();
		int userId=(Integer)request.getSession().getAttribute("user_id");
		int resultCode=ordersModel.addShoppingCart(
				Integer.parseInt(request.getParameter("goods_id")),
				userId,
				Integer.parseInt(request.getParameter("goods_num"))
		);
		if (resultCode == 1) {
			getOut().println(returnJson(200, "加入购物车成功"));return;
		}
		else{
			getOut().println(returnJson(400, "加入购物车失败"));return;
		}
	}
	/**
	 * 购物车查询
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void shoppingCart() throws ServletException, IOException, SQLException{
		OrdersModel ordersModel= new OrdersModel();
		int userId;
		try {
			userId=(Integer)request.getSession().getAttribute("user_id");
		} catch(Exception e) {
			forward("/view/index/login.jsp");
			return;
		}
		ArrayList shoppingList=OrdersModel.shoppingCart(userId);
		ArrayList userAddress=OrdersModel.userAddress(userId);
		request.setAttribute("shoppingList", shoppingList);
		request.setAttribute("userAddress", userAddress);
		forward("/view/index/shoppingCart.jsp");
	}
	/**
	 * 加入订单表
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void addOrder() throws ServletException, IOException, SQLException{
		OrdersModel ordersModel= new OrdersModel();
		int userId=(Integer)request.getSession().getAttribute("user_id");
		int resultCode=ordersModel.addOrder(
				Integer.parseInt(request.getParameter("order_total"))
				,userId,"41000",
				request.getParameter("user_address_name"));
		if (resultCode == 1) {
			getOut().println(returnJson(200, "加入购物车成功"));return;
		}
		else{
		getOut().println(returnJson(400, "加入购物车失败"));return;
		}
	}
	/**
	 * 加入购物车
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void addShoppingOrder() throws ServletException, IOException, SQLException{
		OrdersModel ordersModel= new OrdersModel();
		int userId=(Integer)request.getSession().getAttribute("user_id");
		int resultCode=ordersModel.addShoppingOrder(
				Integer.parseInt(request.getParameter("goods_id")),
				userId,
				Integer.parseInt(request.getParameter("goods_num")),
				Integer.parseInt(request.getParameter("total_price"))
		);
		if (resultCode == 1) {
			getOut().println(returnJson(200, "购买成功"));return;
		}
		else{
		getOut().println(returnJson(400, "购买失败"));return;
		}
	}
}
