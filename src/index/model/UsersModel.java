package index.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import common.db.model.*;
import extend.encryption.EncryptionTool;
import extend.log.Log;
import extend.time.Time;

public class UsersModel extends Model{
	protected static UsersModel myself = null;
	public static UsersModel instance() {
		if (myself == null) {
			myself = new UsersModel();
		}
		return myself;
	}
	/**
	 * 用户注册
	 * @param name
	 * @return
	 * @throws SQLException 
	 */

	public int register(Users users) throws SQLException {
		Boolean res = this.table("users").
				where(Users.instantce().setUserName(users.getUserName()).end().getCondition()).find(); // 查询操作
		if (res) {
			Log.instance().error("注册失败,信息冲突");
			return -1;
		}
		Users get_fieldvalue = Users.instantce()
				.setUserName(users.getUserName())
				.setUserPassword(EncryptionTool.md5(users.getUserPassword()))
				.setUserPhone(users.getUserPhone())
				.setUserEmail(users.getUserEmail())
				.setCreateTime(123)
				.setUpdateTime(222).end();
		int addResult = this.table("users").add(get_fieldvalue.getFields(), get_fieldvalue.getData()); // 添加操作。getFields得到字段名，getData得到数据
		if (addResult!=1) return -2;
		ResultSet rs = this.table("users")
				.where(Users.instantce().setUserName(users.getUserName())
						.setUserStatus(1)
						.end().getCondition()).select(); // 查询操作
		rs.next();
		return rs.getInt(1);
	}
	
	/**
	 * 用户登陆
	 * @return
	 * @throws SQLException 
	 */
	public int login(Users users) throws SQLException {
		ResultSet res = this.table("users")
				.where(Users.instantce().setUserName(users.getUserName())
						.setUserPassword(EncryptionTool.md5(users.getUserPassword()))
						.setUserStatus(1)
						.end().getCondition()).select(); // 查询操作
		
		if (!res.next()) {
			return 0;
		}
		return res.getInt(1);
	}
	/**
	 * 用户注销
	 * @param users
	 * @return
	 */
	public Boolean exit(Users users){
		return true;
	}
	/**
	 * 加入收货地址
	 * @return
	 * @throws SQLException
	 */
	public int addUsersAddress(int userId,String usersAddress) throws SQLException {
		UsersModel usersModel = new UsersModel();
		UserAddress num=UserAddress.instantce()
				.setUserAddressStatus(0)
				.end();
		// 开启事物
		usersModel.startTrans();
		try {
			usersModel.table("user_address").where("user_id= "+ userId).update(num.updateData());
			UserAddress get_fieldvalue = UserAddress.instantce()
					.setUserId(userId)
					.setUserAddressName(usersAddress)
					.setCreateTime(Time.getDateTime())
					.setUpdateTime(Time.getDateTime())
					.setUserAddressStatus(1).end();
			int insertValue=this.table("user_address").add(get_fieldvalue.getFields(),get_fieldvalue.getData());
			usersModel.commit();
			return insertValue;
		} catch (Exception e) {
			// 回滚并关闭事务
			usersModel.rollback().endTrans();
			Log.instance().error("添加收货地址失败。userId:"+userId+"--usersAddress:"+usersAddress);
		}
		return 0;
	}
}

