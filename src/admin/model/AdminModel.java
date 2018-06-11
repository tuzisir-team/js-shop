package admin.model;

import java.sql.SQLException;

import common.db.model.Admin;
import common.db.model.Goods;
import common.db.model.Model;

public class AdminModel extends Model{

	/**
	 * 修改管理员密码
	 * @param oldPassword
	 * @param newPassword
	 * @param adminName
	 * @return 
	 * @throws SQLException 
	 */
	public static int editAdminPassword(String oldPassword, String newPassword, String adminName) throws SQLException {
		AdminModel adminModel = new AdminModel();
		// 查询此用户是否存在
		if (!adminModel.table("admin").
				where(Admin.instantce().setAdminName(adminName).setAdminPassword(oldPassword).end().getCondition()).
				find()) {
			 return 2;
		}
		// 更改用户密码
		return adminModel.table("admin").
				where(Admin.instantce().setAdminPassword(adminName).setAdminPassword(oldPassword).end().getCondition()).
				update(Admin.instantce().setAdminPassword(newPassword).end().updateData());
	}
}
