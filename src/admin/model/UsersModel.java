package admin.model;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.db.model.GoodsOrders;
import common.db.model.Model;
import common.db.model.Users;
import common.db.model.UserAddress;
import extend.page.Page;

public class UsersModel extends Model{
	
	public static ArrayList shoppingCart(String user_id) throws SQLException {
		UsersModel usersModel = new UsersModel();
		ResultSet rs = usersModel.table("goods_orders").
				fields("goods_name, goods_orders.*").
				hasOne("goods", "goods_id", "goods_id").where("goods_orders.user_id="+user_id+" and goods_orders.goods_order_status=0").select();
		ArrayList shoppingCartList = new ArrayList();
		while(rs.next()){
			GoodsOrders v = new GoodsOrders();
            v.setGoodsName(rs.getString(1)).setGoodsOrderId(rs.getInt(2)).setGoodsNum(rs.getInt(5)).
            		setGoodsPrice(rs.getInt(6)).setCreateTime(rs.getInt(8)).setUpdateTime(rs.getInt(9));
            shoppingCartList.add(v);
        }
		return shoppingCartList;
	}
	/**
	 * 用户地址列表
	 * @param userAddress
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList userAddressList(UserAddress userAddress) throws SQLException {
		UsersModel usersModel = new UsersModel();
		ResultSet rs = usersModel.table("user_address").where(userAddress.end().getCondition()).select();
		ArrayList usersAddressList = new ArrayList();
		while(rs.next()){
			UserAddress v = new UserAddress();
            v.setUserAddressId(rs.getInt(1)).setUserAddressName(rs.getString(2)).
            	setCreateTime(rs.getInt(3)).setUpdateTime(rs.getInt(4)).setUserAddressStatus(rs.getInt(5));
            usersAddressList.add(v);
        }
		return usersAddressList;
	}
	
	/**
	 * 改变用户状态
	 * @param users
	 * @return
	 * @throws SQLException
	 */
	public static int changeUserStatus(Users users) throws SQLException {
		int status;
		UsersModel usersModel = new UsersModel();
		if (users.getUserStatus() == 1) {
			status = 0;
		} else if (users.getUserStatus() == 0) {
			status = 1;
		} else {
			return 0;
		}
		return usersModel.table("users").where(Users.instantce().setUserId(users.getUserId()).end().getCondition()).
						update(Users.instantce().setUserStatus(status).end().updateData());
	}
	
	/**
	 * 获取所有用户信息
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList userList() throws SQLException {
		UsersModel usersModel = new UsersModel();
		ResultSet rs = usersModel.table("users").select();
		ArrayList videoList = new ArrayList();
        try {//将查询结果放进容器中
            while(rs.next()){
                Users v = new Users();
                v.setUserId(rs.getInt(1)).setUserName(rs.getString(2))
                	.setUserEmail(rs.getString(4)).setUserPhone(rs.getString(5)).
                	setCreateTime(rs.getInt(6)).setUpdateTime(rs.getInt(7)).setUserStatus(rs.getInt(8));
                videoList.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videoList;
	}
}
