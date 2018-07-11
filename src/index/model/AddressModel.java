package index.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.db.model.Model;
import common.db.model.UserAddress;
import extend.log.Log;
import extend.time.Time;

public class AddressModel extends Model{
	
	/**
	 * 删除地址
	 * @param userId
	 * @param userAddressId
	 * @return
	 * @throws SQLException 
	 */
	public static int delAddress(int userAddressId) throws SQLException {
		UsersModel usersModel = new UsersModel();
		return usersModel.table("user_address").where("user_address_id="+userAddressId).delete();
	}
	
	/**
	 * 设置默认地址
	 * @param userAddressId
	 * @throws SQLException 
	 */
	public static int setAddress(int userId, int userAddressId) throws SQLException {
		UsersModel usersModel = new UsersModel();
		UserAddress num=UserAddress.instantce()
				.setUserAddressStatus(0)
				.end();
		// 开启事物
		usersModel.startTrans();
		try {
			// 更新地址状态都为0
			usersModel.table("user_address").where("user_id= "+ userId).update(num.updateData());
			// 设置默认
			int setAddress = usersModel.table("user_address").where("user_address_id="+userAddressId).
					update(UserAddress.instantce().setUserAddressStatus(1).end().updateData());
			usersModel.commit();
		} catch (Exception e) {
			// 回滚并关闭事务
			usersModel.rollback().endTrans();
			Log.instance().error("添加收货地址失败。userId:"+userId+"--userAddressId:"+userAddressId);
			return 0;
		}
		return 1;
	}
	
	/**
	 * 查询所有地址
	 * @return 
	 * @throws SQLException 
	 */
	public static ArrayList addressList(int userId) throws SQLException {
		AddressModel addressModel = new AddressModel();
		ResultSet res = addressModel.table("user_address").where("user_id="+userId).select();
		ArrayList userAddress = new ArrayList();
		while(res.next()){
			UserAddress v = new UserAddress();
			v.setUserAddressName(res.getString(2)).setUserAddressId(res.getInt(1)).setUserAddressStatus(res.getInt(5));
			userAddress.add(v);
	    }
		return userAddress;
	}
}
