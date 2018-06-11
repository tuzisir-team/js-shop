package index.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.db.model.*;
import extend.log.Log;

public class OrdersModel extends Model{
//	protected static OrdersModel myself = null;
//	
//	public static OrdersModel instance() {
//		if (myself == null) {
//			myself = new OrdersModel();
//		}
//		return myself;
//	}
	/**
	 * 购物车
	 * @return
	 * @throws SQLException
	 */
	public int shoppingCart(int goodsId,int userId,int goodsNum) throws SQLException {
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
			return this.table("goods_orders").add(get_fieldvalue.getFields(),get_fieldvalue.getData());
		}
		else{
			GoodsOrders num=GoodsOrders.instantce()
					.setGoodsNum(res.getInt(4)+goodsNum)
					.end();
			System.out.println(num);
			int updateValue=ordersModel.table("goods_orders")
					.where("goods_id= " + goodsId + " and user_id= "+ userId)
					.update(num.updateData());
			return updateValue;
		}
	}
}
