package index.model;

import java.sql.SQLException;

import common.db.model.Users;
import common.db.model.Model;
import extend.log.Log;

public class LoginModel extends Model{
	private Users users;
	
	public LoginModel(Users users) {
		this.users = users;
	}
	
	public static LoginModel instance(Users users) {
		return new LoginModel(users);
	}
	
	/**
	 * 登录业务处理
	 * @throws SQLException 
	 */
	public Boolean login() throws SQLException {
		// 查找用户
		if (!this.table("users").where(users.end().getCondition()).find()) {
			Log.instance().error("账号或者密码错误,账号:" + users.getUserName() + ",密码:" + users.getUserPassword());
			return false;
		}
		return true;
	}
}
