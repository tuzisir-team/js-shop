package admin.model;

import java.sql.SQLException;

import common.db.model.Admin;
import common.db.model.Model;
import extend.log.Log;

public class LoginModel extends Model{
	private Admin admin;
	
	public LoginModel(Admin admin) {
		this.admin = admin;
	}
	
	public static LoginModel instance(Admin admin) {
		return new LoginModel(admin);
	}
	
	/**
	 * 登录业务处理
	 * @throws SQLException 
	 */
	public Boolean login() throws SQLException {
		// 先查找有没有此管理员
		if (!this.table("admin").where(admin.end().getCondition()).find()) {
			Log.instance().error("账号或者密码错误,账号:" + admin.getAdminName() + ",密码:" + admin.getAdminPassword());
			return false;
		}
		return true;
	}
}
