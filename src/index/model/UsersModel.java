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
		String where = Users.instantce().setUserName(users.getUserName()).
				end().getCondition();
		System.out.println(where);
		ResultSet res = this.table("users").
				where(where).select(); // 查询操作
		int num;
		boolean value=true;
		try {
			num = res.getRow();
			if (num > 0) {
				Log.instance().error("注册失败,用户名冲突");
				value=false;
			}
			else{
				Users get_fieldvalue = Users.instantce()
						.setUserName(users.getUserName())
						.setUserPassword(users.getUserPassword())
						.setUserPhone(users.getUserPhone())
						.setUserEmail(users.getUserEmail())
						.setCreateTime(123)
						.setUpdateTime(222).end();
				int add = this.table("users").add(get_fieldvalue.getFields(), get_fieldvalue.getData()); // 添加操作。getFields得到字段名，getData得到数据
			}
		} catch (SQLException e) {
			Log.instance().error("查找用户名时异常");
		}
		return value;
	}
	
	/**
	 * 用户登陆
	 * @return
	 */
	public Boolean login(Users users) {
		String where = Users.instantce().setUserName(users.getUserName())
				.setUserPassword(users.getUserPassword())
				.end().getCondition();
		System.out.println(where);
		ResultSet res = this.table("users").
				where(where).select(); // 查询操作
		int num;
		boolean value=false;
		try {
			num = res.getRow();
			if (num > 0) {
				value=true;
			}
		} catch (SQLException e) {
			Log.instance().error("用户登录异常");
		}
		return value;
	}
	public Boolean exit(Users users){
		return true;
	}
}

