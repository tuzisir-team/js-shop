package index.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.db.model.*;
import extend.log.Log;
import extend.time.Time;

public class OrdersModel extends Model{
	/**
	 * 加入购物车
	 * @return
	 * @throws SQLException
	 */
	public int addShoppingCart(int goodsId,int userId,int goodsNum) throws SQLException {
		int insertValue;
		OrdersModel ordersModel = new OrdersModel();
		ResultSet res = ordersModel.table("goods_orders")
				.where("goods_id= " + goodsId + " and user_id="+userId)
				.select();
		if(!res.next()){
			ResultSet rs = ordersModel.table("goods")
					.where("goods_id="+goodsId)
					.select();
			rs.next();
			GoodsOrders get_fieldvalue = GoodsOrders.instantce()
					.setGoodsId(rs.getInt(1))
					.setGoodsNum(goodsNum)
					.setGoodsPrice(rs.getInt(10))
					.setUserId(userId)
					.setCreateTime(123)
					.setUpdateTime(222)
					.setGoodsOrderStatus(0).end();
			insertValue=this.table("goods_orders").add(get_fieldvalue.getFields(),get_fieldvalue.getData());
			return insertValue;
		}
		else{
			GoodsOrders num=GoodsOrders.instantce()
					.setGoodsNum(res.getInt(4)+goodsNum)
					.end();
			insertValue=ordersModel.table("goods_orders")
					.where("goods_id= " + goodsId + " and user_id= "+ userId)
					.update(num.updateData());
			return insertValue;
		}
	}
	/**
	 * 购物车查询
	 * @param goodsId
	 * @param userId
	 * @param goodsNum
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList shoppingCart(int userId) throws SQLException {
		OrdersModel ordersModel = new OrdersModel();
		ResultSet rs = ordersModel.
				querySelect("select goods.goods_name,goods.goods_price,goods_orders.goods_num,goods.goods_id from goods_orders,goods"
						+ " where user_id="+userId +" and goods.goods_id = goods_orders.goods_id and goods_orders.goods_order_status =0");
		ArrayList shoppingList = new ArrayList();
		while(rs.next()){
			GoodsOrders goodsOrder = new GoodsOrders();
			goodsOrder.setGoodsName(rs.getString(1)).setGoodsPrice(rs.getInt(2)).setGoodsNum(rs.getInt(3)).setGoodsId(rs.getInt(4));
			shoppingList.add(goodsOrder);
        }
		return shoppingList;
	}
/**
 * 添加地址
 * @param userId
 * @return
 * @throws SQLException
 */
	public static ArrayList userAddress(int userId) throws SQLException {
		OrdersModel ordersModel = new OrdersModel();
		ResultSet res = ordersModel.
				querySelect("select user_address_name from user_address where user_id="+userId);
		ArrayList userAddress = new ArrayList();
		while(res.next()){
			UserAddress v = new UserAddress();
			v.setUserAddressName(res.getString(1));
			userAddress.add(v);
	    }
		return userAddress;
	}
	/**
	 * 加入订单表
	 * @return
	 * @throws SQLException
	 */
	public int addOrder(int total,int userId,String outTradeNo,String useraddname) throws SQLException {
		OrdersModel ordersModel = new OrdersModel();
		Orders get_fieldvalue = Orders.instantce()
				.setOrderTotal(total)
				.setOutTradeNo(outTradeNo)
				.setUserId(userId)
				.setUserAddressName(useraddname)
				.setCreateTime(Time.getDateTime())
				.setUpdateTime(Time.getDateTime())
				.setCompleteTime(Time.getDateTime()).end();
		int insertValue=this.table("orders").add(get_fieldvalue.getFields(),get_fieldvalue.getData());
		return insertValue;
	}
	/**
	 * 购买商品
	 * @param goodsId
	 * @param userId
	 * @param goodsNum
	 * @param total
	 * @return
	 * @throws SQLException
	 */
	public int addShoppingOrder(int goodsId,int userId,int goodsNum,int total) throws SQLException {
		ResultSet rs;
		OrdersModel ordersModel = new OrdersModel();
		//加入购物车
		rs = ordersModel.
				querySelect("select goods_price from goods where goods_id="+goodsId);
		rs.next();
		GoodsOrders get_fieldvalue = GoodsOrders.instantce()
				.setGoodsId(goodsId)
				.setGoodsNum(goodsNum)
				.setGoodsPrice(rs.getInt(1))
				.setUserId(userId)
				.setCreateTime(Time.getDateTime())
				.setUpdateTime(Time.getDateTime())
				.setGoodsOrderStatus(1).end();
		this.table("goods_orders").add(get_fieldvalue.getFields(),get_fieldvalue.getData());
		//下单支付
		rs = ordersModel.
				querySelect("select user_address_name from user_address"
						+ " where user_id="+userId+" and user_address_status = 1");
		rs.next();
		Orders get_field_value = Orders.instantce()
				.setOrderTotal(total)
				.setOutTradeNo("40001")
				.setUserId(userId)
				.setUserAddressName(rs.getString(1))
				.setCreateTime(Time.getDateTime())
				.setUpdateTime(Time.getDateTime())
				.setCompleteTime(Time.getDateTime())
				.setOrderStatus(1).end();
		int insertValue=this.table("orders").add(get_field_value.getFields(),get_field_value.getData());
		//更新数量
		rs = ordersModel.
				querySelect("select goods_num from goods where goods_id="+goodsId);
		rs.next();
		Goods num=Goods.instantce()
				.setGoodsNum(rs.getInt(1)-goodsNum)
				.end();
		int updateValue=ordersModel.table("goods")
				.where("goods_id= " + goodsId)
				.update(num.updateData());
		return insertValue;
	}
}
