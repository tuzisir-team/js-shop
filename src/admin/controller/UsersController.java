package admin.controller;

import common.servlet.Controller;

import common.db.model.Model;
import common.db.model.Users;
import extend.log.Log;

public class UsersController extends Model{
	public void test() {
//		ResultSet res = this.table("users").where(UsersModel.instantce().setUserName("2").getCondition()).select(); // 查询操作
//		UsersModel usersModel = UsersModel.instantce().setAddressId(123).setUserName("郭玉朝");
//		int w = this.table("users").add(usersModel.getFields(), usersModel.getData()); // 添加操作
//		
//		this.table("users").where(UsersModel.instantce().setUserId(1).getCondition()).delete(); // 删除操作
//		
//		this.table("users").where(UsersModel.instantce().setUserId(1).getCondition()).
//		update(UsersModel.instantce().setUserEmail("123").getData()); // 更新操作
		
        try {
            Log.instance().error("梦老二");

        } catch (Exception e) {
        }
		
		
	}
}