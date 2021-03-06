package admin.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.db.model.Admin;
import common.db.model.AdminFunction;
import common.db.model.AdminRole;
import common.db.model.Goods;
import common.db.model.Model;
import extend.encryption.EncryptionTool;
import extend.log.Log;
import extend.time.Time;

public class AdminModel extends Model{
	
	/**
	 * 获取一级菜单以json格式
	 * @return
	 * @throws SQLException 
	 */
	public static String getOnemenuJson() throws SQLException {
		AdminModel adminModel = new AdminModel();
		// 获取一级菜单
		ResultSet functions = adminModel.table("admin_function")
			.where(AdminFunction.instance().setPid(0).setAdminFunctionStatus(1).end().getCondition()).select();
		String functionsJson = "[";
		while (functions.next()) {
			functionsJson += "{\"admin_function_name\":\"" + functions.getString(3) + 
					"\",\"admin_function_url\":\"" + functions.getString(2) + "\",\"admin_function_id\":\"" + functions.getInt(1) + "\"},";
		}
		if (functionsJson.length() > 1) {
			functionsJson = functionsJson.substring(0,functionsJson.length()-1);
		}
		functionsJson += "]";
		return functionsJson;
	}
	
	/**
	 * 改变管理员状态
	 * @param adminId
	 * @param adminStatus
	 * @return
	 * @throws SQLException 
	 */
	public static int changeAdminStatus(int adminId, int adminStatus) throws SQLException {
		AdminModel adminModel = new AdminModel();
		if (adminStatus == 1) {
			adminStatus = 0;
		} else if (adminStatus == 0) {
			adminStatus = 1;
		} else {
			return 0;
		}
		adminModel.table("admin")
				.where("admin_id="+adminId)
				.update(Admin.instantce().setAdminStatus(adminStatus).end().updateData());
		return 1;
	}
	
	/**
	 * 删除管理员
	 * @param adminId
	 * @return
	 * @throws SQLException
	 */
	public static int delAdmin(int adminId) throws SQLException {
		AdminModel adminModel = new AdminModel();
		return adminModel.table("admin").where("admin_id="+adminId).delete();
	}
	
	/**
	 * 重置管理员密码
	 * @param adminId
	 * @return
	 * @throws SQLException
	 */
	public static int resetAdminPassword(int adminId) throws SQLException {
		AdminModel adminModel = new AdminModel();
		// 修改管理员
		Admin admin = Admin.instantce().setAdminPassword(EncryptionTool.md5("123456")).setUpdateTime(Time.getDateTime()).end();
		return adminModel.table("admin").where("admin_id="+adminId).update(admin.updateData());
	}
	/**
	 * 修改管理员
	 * @return
	 * @throws SQLException 
	 */
	public static int editAdmin(String adminName, int adminRoleId, int adminId) throws SQLException {
		AdminModel adminModel = new AdminModel();
		// 查看有没有相同的管理员名称
		if (adminModel.table("admin").where("admin_name='"+adminName+"' and admin_id<>"+adminId).find()) return 2;
		// 修改管理员
		Admin admin = Admin.instantce().setAdminName(adminName).setAdminRoleId(adminRoleId)
			.setAdminStatus(1).setUpdateTime(Time.getDateTime()).end();
		return adminModel.table("admin").where("admin_id="+adminId).update(admin.updateData());
	}
	
	/**
	 * 获取管理员信息
	 * @param adminId
	 * @return
	 * @throws SQLException 
	 */
	public static Admin getAdminInfo(int adminId) throws SQLException {
		AdminModel adminModel = new AdminModel();
		ResultSet adminInfo = adminModel.table("admin").where(Admin.instantce().setAdminId(adminId).end().getCondition()).select();
		adminInfo.next();
		return Admin.instantce().setAdminId(adminInfo.getInt(1)).setAdminName(adminInfo.getString(2)).setAdminRoleId(adminInfo.getInt(7));
	} 
	
	/**
	 * 添加管理员
	 * @return
	 * @throws SQLException 
	 */
	public static int addAdmin(String adminName, String adminPassword, int adminRoleId) throws SQLException {
		AdminModel adminModel = new AdminModel();
		// 查看有没有相同的管理员名称
		if (adminModel.table("admin").where(Admin.instantce().setAdminName(adminName).end().getCondition()).find()) return 2;
		// 添加管理员
		Admin admin = Admin.instantce().setAdminName(adminName).setAdminPassword(adminPassword).setAdminRoleId(adminRoleId)
			.setAdminStatus(1).setCreateTime(Time.getDateTime()).setUpdateTime(Time.getDateTime()).end();
		return adminModel.table("admin").add(admin.getFields(), admin.getData());
	}
	
	/**
	 * 管理员列表
	 * @return
	 * @throws SQLException 
	 */
	public static ArrayList adminList() throws SQLException {
		AdminModel adminModel = new AdminModel();
		ResultSet admins = adminModel.querySelect(""
				+ "select admin.*,admin_role.admin_role_name from admin left join admin_role "
				+ "on admin.admin_role_id=admin_role.admin_role_id where admin.admin_role_id<>-1");
		Admin admin = null;
		ArrayList adminList = new ArrayList();
		while (admins.next()) {
			admin = Admin.instantce().setAdminId(admins.getInt(1))
					.setAdminName(admins.getString(2))
					.setAdminPassword(admins.getString(3))
					.setAdminStatus(admins.getInt(4))
					.setCreateTime(admins.getInt(5))
					.setUpdateTime(admins.getInt(6))
					.setAdminRoleId(admins.getInt(7))
					.setAdminRoleName(admins.getString(8));
			adminList.add(admin);
		}
		return adminList;
	}
	
	/**
	 * 改变角色状态
	 * @param adminRoleId
	 * @param adminRoleStatus
	 * @return
	 * @throws SQLException
	 */
	public static int changeAdminRoleStatus (int adminRoleId, int adminRoleStatus) throws SQLException {
		AdminModel adminModel = new AdminModel();
		if (adminRoleStatus == 1) {
			adminRoleStatus = 0;
		} else if (adminRoleStatus == 0) {
			adminRoleStatus = 1;
		} else {
			return 0;
		}
		try {
			// 开启事务
			adminModel.startTrans();
			// 把状态全部设置为0
			adminModel.table("admin")
					.where("admin_role_id="+adminRoleId)
					.update(Admin.instantce().setAdminStatus(0).end().updateData());
//			// 更改角色状态
//			if (adminModel.table("admin_role").
//					where(AdminRole.instance().setAdminRoleId(adminRoleId).end().getCondition()).
//					update(AdminRole.instance().setAdminRoleStatus(adminRoleStatus).end().updateData()) != 1) throw new Exception();
			// 提交事务
			adminModel.commit();
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// 关闭并且事务，回滚
			adminModel.rollback().endTrans();
			Log.instance().error("删除管理员角色出现异常，角色ID:"+adminRoleId);
			return 2;
		}		
	}
	
	/**
	 * 删除管理员角色
	 * @return
	 * @throws SQLException 
	 * @throws java.lang.Exception 
	 */
	public static int delAdminRole(int adminRoleId) throws SQLException{
		AdminModel adminModel = new AdminModel();
		try {
			// 开启事务
			adminModel.startTrans();
			// 清空管理员分配的角色
			adminModel.table("admin")
					.where("admin_role_id="+adminRoleId)
					.update(Admin.instantce().setAdminRoleId(0).setAdminStatus(0).end().updateData());
			// 删除角色
			if (!(adminModel.table("admin_role").where("admin_role_id="+adminRoleId).delete() <= 1)) throw new Exception();
			// 提交事务
			adminModel.commit();
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// 关闭并且事务，回滚
			adminModel.rollback().endTrans();
			Log.instance().error("删除管理员角色出现异常，角色ID:"+adminRoleId);
			return 2;
		}		
	}
	
	/**
	 * 修改管理员角色
	 * @param adminRoleName
	 * @param adminRoleId
	 * @param adminRoleJurisdiction
	 * @return
	 * @throws SQLException
	 */
	public static int editAdminRole(String adminRoleName, int adminRoleId, String adminRoleJurisdiction) throws SQLException {
		AdminModel adminModel = new AdminModel();
		// 检查有没有相同的角色名称
		if (adminModel.table("admin_role").where(
				"admin_role_name='"+adminRoleName+"' and admin_role_id<>"+adminRoleId
				).find()) {
			return 2;
		}
		// 更新角色信息
		return adminModel.table("admin_role").where(AdminRole.instance().setAdminRoleId(adminRoleId).end().getCondition())
				.update(AdminRole.instance().setAdminRoleName(adminRoleName).setAdminRoleJurisdiction(adminRoleJurisdiction).end().updateData());
	}
	
	/**
	 * 获取角色功能
	 * @return
	 * @throws SQLException 
	 */
	public static ResultSet getAdminRoleJurisdiction(int adminRoleId) throws SQLException {
		AdminModel adminModel = new AdminModel();
		ResultSet adminRoleInfo = adminModel.table("admin_role").where(AdminRole.instance().setAdminRoleId(adminRoleId).end().getCondition()).select();
		if (!adminRoleInfo.next()) return null;
		return adminRoleInfo;
	}
	/**
	 * 添加角色
	 * @param roleName
	 * @param adminRoleJurisdiction
	 * @return
	 * @throws SQLException
	 */
	public static int addRole(String adminRoteName, String adminRoleJurisdiction) throws SQLException {
		AdminModel adminModel = new AdminModel();
		// 检查有没有相同的角色名称
		if (adminModel.table("admin_role").where(
				AdminRole.instance().setAdminRoleName(adminRoteName).end().getCondition()
				).find()) {
			return 2;
		}
		// 添加入库
		AdminRole adminRole = AdminRole.instance().setAdminRoleJurisdiction(adminRoleJurisdiction)
		.setAdminRoleName(adminRoteName).setCreateTime(Time.getDateTime()).setUpdateTime(Time.getDateTime()).end();
		return adminModel.table("admin_role").add(adminRole.getFields(), adminRole.getData());
	}
	
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
				where(Admin.instantce().setAdminName(adminName).setAdminPassword(oldPassword).end().getCondition()).
				update(Admin.instantce().setAdminPassword(newPassword).end().updateData());
	}
}
