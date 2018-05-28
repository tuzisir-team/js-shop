package index.model;

import java.sql.ResultSet;
import java.sql.SQLException;



import common.db.model.*;

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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(this.login()){
			Users ss = Users.instantce()
					.setUserName(users.getUserName())
					.setUserPassword(users.getUserPassword())
					.setUserPhone(users.getUserPhone())
					.setUserEmail(users.getUserEmail())
					.setCreateTime(123)
					.setUpdateTime(222);
			int w = this.table("users").add(ss.getFields(), ss.getData()); // 添加操作。getFields得到字段名，getData得到数据
		}
		return true;
	}
	
	/**
	 * 用户登陆
	 * @return
	 */
	public Boolean login() {
		return true;
	}
}

