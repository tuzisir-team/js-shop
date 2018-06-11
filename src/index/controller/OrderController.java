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
	 * 购物车操作
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void shoppingCart() throws ServletException, IOException, SQLException{
		String returnJson = "{}";
		OrdersModel ordersModel= new OrdersModel();
		int userId=(Integer)request.getSession().getAttribute("user_id");
		int resultCode=ordersModel.shoppingCart(
				Integer.parseInt(request.getParameter("goods_id")),
				userId,
				Integer.parseInt(request.getParameter("goods_num"))
		);
		if (resultCode == 1) {
			getOut().println(returnJson(200, "加入购物车成功"));return;
		}
		getOut().println(returnJson(400, "加入购物车失败"));return;
	}
}
