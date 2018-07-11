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
import extend.email.EmailFactory;

public class OrderController extends Controller{
	
	/**
	 * 删除购物车商品
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public void delCartGoods() throws IOException, NumberFormatException, SQLException {
		String checkResult = checkParams("post_type,goods_order_id");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 删除
		if (OrdersModel.delCartGoods(Integer.parseInt(request.getParameter("goods_order_id")))==1) {
			getOut().println(returnJson(200, "删除购物车商品成功"));return;
		}
		getOut().println(returnJson(400, "删除购物车商品失败"));return;
	}
	
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
		String checkResult = checkParams("post_type,goods_order_id_str,user_address_name,order_total");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		OrdersModel ordersModel= new OrdersModel();
		int userId=(Integer)request.getSession().getAttribute("user_id");
		String goodsOrderIdStr = request.getParameter("goods_order_id_str");
		String userAddressName = request.getParameter("user_address_name");
		int orderTotal = Integer.parseInt(request.getParameter("order_total"));
		// 添加订单
		int resultCode=ordersModel.addOrder(orderTotal, userId, userAddressName, goodsOrderIdStr);
		if (resultCode == 1) {
			EmailFactory.instance().send(userId,EmailFactory.PAY);
			getOut().println(returnJson(200, "支付成功"));return;
		}
		else{
		getOut().println(returnJson(400, "支付失败"));return;
		}
	}
	/**
	 * 加入购物车并购买
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
		switch(resultCode) {
		case -1:
			getOut().println(returnJson(404, "请添加收货地址"));
			break;
		case 1:
			EmailFactory.instance().send(userId,EmailFactory.PAY);
			getOut().println(returnJson(200, "购买成功"));
			break;
		default:
			getOut().println(returnJson(400, "购买失败"));
			break;
	}
	}
}
