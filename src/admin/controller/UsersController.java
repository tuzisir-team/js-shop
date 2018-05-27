package admin.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import common.db.model.Model;
import common.db.model.UsersModel;

public class UsersController extends Model{
	public void test() {
		ResultSet res = this.table("users").where(UsersModel.instantce().setUserName("2").getCondition()).select();
		UsersModel usersModel = UsersModel.instantce().setAddressId(123).setUserName("郭玉朝");
		int w = this.table("users").add(usersModel.getFields(), usersModel.getData());
		
		this.table("users").where(UsersModel.instantce().setUserId(1).getCondition()).delete();
		
		this.table("users").where(UsersModel.instantce().setUserId(1).getCondition()).
		update(UsersModel.instantce().setUserEmail("123").getData());
		
		
	}
}
