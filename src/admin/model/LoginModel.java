package admin.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.db.model.Admin;
import common.db.model.AdminFunction;
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
	 * 获取用户的所有功能列表
	 * @return
	 * @throws SQLException 
	 */
	public String getFunctions(int adminId) throws SQLException {
		// 获取账户角色id
		ResultSet adminInfo = this.table("admin").where("admin_id="+adminId).select();
		adminInfo.next();
		int adminRoleId= adminInfo.getInt(7);
		ResultSet functions;
		if (adminRoleId == -1) {
			functions = this.table("admin_function").where("admin_function_status=1").select();
		} else {
			// 获取功能id
			ResultSet adminRoleInfo = this.table("admin_role").where("admin_role_id="+adminRoleId).select();
			if (!adminRoleInfo.next()){}
			String jurisdiction = adminRoleInfo.getString(3);
			// 获取功能列表
			functions = this.table("admin_function").where("admin_function_status=1 "
					+ "and admin_function_id in(" + jurisdiction + ")").select();
		}
		return this.getFunctionsJson(functions);
	}
	
	private String getFunctionsJson(ResultSet functions) throws SQLException {
		String functionsJson = "[";
		while (functions.next()) {
			functionsJson += "{\"admin_function_name\":\"" + functions.getString(3) + 
					"\",\"admin_function_url\":\"" + functions.getString(2) + "\",\"pid\":\"" + functions.getInt(7) + "\"},";
		}
		if (functionsJson.length() > 1) {
			functionsJson = functionsJson.substring(0,functionsJson.length()-1);
		}
		functionsJson += "]";
		return functionsJson;
	}
	
	/**
	 * 登录业务处理
	 * @return 
	 * @throws SQLException 
	 */
	public int login() throws SQLException {
		// 先查找有没有此管理员
		ResultSet adminInfo = this.table("admin").where(admin.end().getCondition()).select();
		if (!adminInfo.next()) {
			Log.instance().error("账号或者密码错误,账号:" + admin.getAdminName() + ",密码:" + admin.getAdminPassword());
			return 0;
		}
		return adminInfo.getInt(1);
	}
}
