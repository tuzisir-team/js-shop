package admin.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.db.model.Admin;
import common.db.model.AdminFunction;
import common.db.model.AdminRole;
import common.db.model.Goods;
import common.db.model.Model;

public class AdminModel extends Model{
	
	/**
	 * 添加角色页面，需要的信息
	 * @return
	 * @throws SQLException
	 */
	public ArrayList[] addRoleView() throws SQLException {
		ArrayList[] menusList = new ArrayList[2];
		// 获取一级菜单
		ResultSet oneMenus = this.table("admin_function")
			.where(AdminFunction.instance().setPid(0).setAdminFunctionStatus(1).end().getCondition()).select();
		menusList[0] = this.getWhileMenus(oneMenus);
		// 获取二级菜单
		ResultSet twoMenus = this.table("admin_function")
			.where("pid <> 0 and admin_function_status = 1").select();
		menusList[1] = this.getWhileMenus(twoMenus);
		return menusList;
	}
	
	/**
	 * 获取角色列表
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList roleList() throws SQLException {
		AdminModel adminModel = new AdminModel();
		ResultSet oneMenus = adminModel.table("admin_role").select();
		AdminRole adminRole = null;
		ArrayList adminRoleList = new ArrayList();
		while (oneMenus.next()) {
			adminRole = AdminRole.instance().setAdminRoleId(oneMenus.getInt(1))
								.setAdminRoleName(oneMenus.getString(2))
								.setAdminRoleJurisdiction(oneMenus.getString(3))
								.setCreateTime(oneMenus.getInt(4))
								.setUpdateTime(oneMenus.getInt(5))
								.setAdminRoleStatus(oneMenus.getInt(6));
			adminRoleList.add(adminRole);
		}
		return adminRoleList;
	}
	
	/**
	 * 获取功能列表
	 * @return 
	 * @throws SQLException
	 */
	public ArrayList[] functionList() throws SQLException {
		ArrayList[] menusList = new ArrayList[2];
		// 获取一级菜单
		ResultSet oneMenus = this.table("admin_function")
			.where(AdminFunction.instance().setPid(0).end().getCondition()).select();
		menusList[0] = this.getWhileMenus(oneMenus);
		// 获取二级菜单
		ResultSet twoMenus = this.table("admin_function")
			.where("pid <> 0").select();
		menusList[1] = this.getWhileMenus(twoMenus);
		return menusList;
	}
	
	/**
	 * 获取菜单公共方法
	 * @param data
	 * @return
	 * @throws SQLException
	 */
	private ArrayList getWhileMenus(ResultSet data) throws SQLException {
		ArrayList menusList = new ArrayList();
		AdminFunction adminFunction;
		while (data.next()) {
			adminFunction = AdminFunction.instance()
					.setAdminFunctionId(data.getInt(1))
					.setAdminFunctionUrl(data.getString(2))
					.setAdminFunctionName(data.getString(3))
					.setAdminFunctionStatus(data.getInt(4))
					.setCreateTime(data.getInt(5))
					.setUpdateTime(data.getInt(6))
					.setPid(data.getInt(7));
			menusList.add(adminFunction);
		}
		return menusList;
	}

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
