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
	 */
	public Boolean register(Users users) {
		ResultSet res = this.table("users").
				where(Users.instantce().setUserName(users.getUserName()).
						setUserPassword(users.getUserPassword()).
						getCondition()).select(); // 查询操作
		int num;
		try {
			num = res.getRow();
			if (num > 0) {
				Log.instance().error("注册失败,用户名冲突");
				return false;
			}
		} catch (SQLException e) {
			Log.instance().error("查找用户名时异常");
		}
		if(this.login()){
			Users get_fieldvalue = Users.instantce()
					.setUserName(users.getUserName())
					.setUserPassword(users.getUserPassword())
					.setUserPhone(users.getUserPhone())
					.setUserEmail(users.getUserEmail())
					.setCreateTime(123)
					.setUpdateTime(222);
			int add = this.table("users").add(get_fieldvalue.getFields(), get_fieldvalue.getData()); // 添加操作。getFields得到字段名，getData得到数据
			if (add > 0) {
				return true;
			}
		}
		Log.instance().error("注册失败");
		return false;
	}
	
	/**
	 * 用户登陆
	 * @return
	 */
	public Boolean login() {
		return true;
	}
}

