package admin.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import common.db.model.GoodsOrders;
import common.db.model.Model;
import common.db.model.Orders;
import extend.time.Time;

public class OrdersModel extends Model {
	
	private static int[] days;
	
	/**
	 * 订单统计
	 * @return 
	 * @throws SQLException 
	 */
	public static HashMap<String, String> orderCount() throws SQLException {
		HashMap<String, String> orderCountData = new HashMap<String, String>(); // 订单统计数据
		OrdersModel ordersModel = new OrdersModel();
		orderCountData.put("order_num", "0");
		orderCountData.put("ok_order_num", "0");
		orderCountData.put("ok_order_total", "0");
		orderCountData.put("refund_order_num", "0");
		orderCountData.put("refund_order_total", "0");
		// 订单总量,只统计订单成功和退款的，其它状态没意义
		ResultSet count1 = ordersModel.table("orders")
				.fields("count(*) as order_num").where("order_status in (1,3)").select();
		if (count1.next()) {
			orderCountData.put("order_num", Integer.toString(count1.getInt("order_num")));
		}
		// 成功数量，失败数量
		ResultSet count2 = ordersModel.table("orders")
				.fields("order_status,count(*) as order_status_num,sum('order_total') as order_total_status")
				.group("order_status").where("order_status in (1,3)").select();
		while (count2.next()) {
			if (count2.getInt("order_status") == 1) {
				orderCountData.put("ok_order_num", Integer.toString(count2.getInt("order_status_num")));
				orderCountData.put("ok_order_total", Integer.toString(count2.getInt("order_total_status")));
			} else if (count2.getInt("order_status") == 3) {
				orderCountData.put("refund_order_num", Integer.toString(count2.getInt("order_status_num")));
				orderCountData.put("refund_order_total", Integer.toString(count2.getInt("order_total_status")));
			}
		}
		return orderCountData;
	}

	/**
	 * 本周统计
	 * @return 
	 * @throws SQLException 
	 */
	public static int[] weekCount() throws SQLException {
		OrdersModel ordersModel = new OrdersModel();
		System.out.println(Time.getWeekStartTime());
		System.out.println(Time.getWeekEndTime());
		ResultSet ordersRes = ordersModel.table("orders").where("order_status=1 and "
				+ "create_time between " + Time.getWeekStartTime() +" and " + Time.getWeekEndTime()).select();
		days = new int[7];
		while(ordersRes.next()) {
			int create_time = ordersRes.getInt(4);
			System.out.println("我执行了");
			if (create_time>=Time.getWeekDayStartTime(1) && create_time<Time.getWeekDayEndTime(1)) {
				days[0] = days[0] + 1;
			} else if (create_time>=Time.getWeekDayStartTime(2) && create_time<Time.getWeekDayEndTime(2)) {
				days[1] = days[1] + 1;
			} else if (create_time>=Time.getWeekDayStartTime(3) && create_time<Time.getWeekDayEndTime(3)) {
				days[2] = days[2] + 1;
			} else if (create_time>=Time.getWeekDayStartTime(4) && create_time<Time.getWeekDayEndTime(4)) {
				days[3] = days[3] + 1;
			} else if (create_time>=Time.getWeekDayStartTime(5) && create_time<Time.getWeekDayEndTime(5)) {
				days[4] = days[4] + 1;
			} else if (create_time>=Time.getWeekDayStartTime(6) && create_time<Time.getWeekDayEndTime(6)) {
				days[5] = days[5] + 1;
			} else if (create_time>=Time.getWeekDayStartTime(7) && create_time<Time.getWeekDayEndTime(7)) {
				days[6] = days[6] + 1;
			}
		}
		return days;
	}
	
	/**
	 * 根据订单号查询订单
	 * @param outTradeNo
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList orderFind(String outTradeNo) throws SQLException {
		OrdersModel ordersModel = new OrdersModel();
		ResultSet ordersRes = ordersModel.table("orders").hasOne("users", "user_id", "user_id").
				fields("orders.*, users.user_name").where("orders.user_id=users.user_id and orders.out_trade_no='"+outTradeNo+"'").select();
		ArrayList ordersList = new ArrayList();
		while (ordersRes.next()) {
			Orders orders = new Orders();
			orders.setOrderId(ordersRes.getInt(1))
			.setOrderTotal(ordersRes.getInt(2))
			.setOrderStatus(ordersRes.getInt(3))
			.setCreateTime(ordersRes.getInt(4))
			.setUpdateTime(ordersRes.getInt(5))
			.setUserAddressName(ordersRes.getString(7))
			.setOutTradeNo(ordersRes.getString(8))
			.setCompleteTime(ordersRes.getInt(9))
			.setUserName(ordersRes.getString(10));
			ordersList.add(orders);
		}
		return ordersList;
	}
	
	/**
	 * 订单详情
	 * @return 
	 * @return 
	 * @throws SQLException 
	 */
	public static ArrayList orderDetails(int orderId) throws SQLException {
		OrdersModel ordersModel = new OrdersModel();
		ResultSet goodsOrdersRes = ordersModel.querySelect("select goods_orders.*, goods.goods_name"
				+ " from goods_orders,goods"
				+ " where goods_orders.goods_id=goods.goods_id"
				+ " and goods_orders.order_id=" + orderId);
		ArrayList goodsOrdersList = new ArrayList();
		while(goodsOrdersRes.next()) {
			GoodsOrders goodsOrders = new GoodsOrders();
			goodsOrders = goodsOrders.instantce()
					.setGoodsOrderId(goodsOrdersRes.getInt(1))
					.setGoodsName(goodsOrdersRes.getString(10))
					.setGoodsNum(goodsOrdersRes.getInt(4))
					.setGoodsPrice(goodsOrdersRes.getInt(5));
			goodsOrdersList.add(goodsOrders);
		}
		return goodsOrdersList;
	}
	
	/**
	 * 获取订单列表
	 * @return 
	 * @throws SQLException 
	 */
	public static ArrayList ordersList() throws SQLException {
		OrdersModel ordersModel = new OrdersModel();
		ResultSet ordersRes = ordersModel.table("orders").hasOne("users", "user_id", "user_id").
				fields("orders.*, users.user_name").where("orders.user_id=users.user_id").select();
		ArrayList ordersList = new ArrayList();
		while (ordersRes.next()) {
			Orders orders = new Orders();
			orders.setOrderId(ordersRes.getInt(1))
			.setOrderTotal(ordersRes.getInt(2))
			.setOrderStatus(ordersRes.getInt(3))
			.setCreateTime(ordersRes.getInt(4))
			.setUpdateTime(ordersRes.getInt(5))
			.setUserAddressName(ordersRes.getString(7))
			.setOutTradeNo(ordersRes.getString(8))
			.setCompleteTime(ordersRes.getInt(9))
			.setUserName(ordersRes.getString(10));
			ordersList.add(orders);
		}
		return ordersList;
	}
}
