package index.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import common.db.model.*;
import extend.log.Log;

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
				.setUserPassword(users.getUserPassword())
				.setUserPhone(users.getUserPhone())
				.setUserEmail(users.getUserEmail())
				.setCreateTime(123)
				.setUpdateTime(222).end();
		return this.table("users").add(get_fieldvalue.getFields(), get_fieldvalue.getData()); // 添加操作。getFields得到字段名，getData得到数据
	}
	
	/**
	 * 用户登陆
	 * @return
	 * @throws SQLException 
	 */
	public int login(Users users) throws SQLException {
		Boolean res = this.table("users").
				where(Users.instantce().setUserName(users.getUserName()).setUserPassword(users.getUserPassword()).end().getCondition()).find(); // 查询操作
		if (!res) {
			Log.instance().error("没有找到该用户");
			return 0;
		}
		return 1;
	}
	/**
	 * 用户注销
	 * @param users
	 * @return
	 */
	public Boolean exit(Users users){
		
		return true;
	}
}

