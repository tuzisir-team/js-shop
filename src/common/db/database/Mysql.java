package common.db.database;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import config.GetConfig;

import common.base.inter.db.BaseDatabaseConnectInter;

public class Mysql implements BaseDatabaseConnectInter{
	private Connection conn=null;
	protected static GetConfig getConfig;
	protected static Mysql myself = null;
	
	//静态代码块加载驱动
	static{
		Mysql.getConfig = GetConfig.instance("MYSQL");
		try {
			Class.forName(Mysql.getConfig.getStringConfig("driver"));
		} catch (Exception e) {;
			e.printStackTrace();
		}
	}
	
	/**
	 * 单例模式返回此对象
	 * @return
	 */
	public static Mysql instance() {
		if (Mysql.myself == null) {
			Mysql.myself = new Mysql();
		}
		return Mysql.myself;
	}
	
	/**
	 * 得到数据库的连接
	 */
	public Connection getConnection(){
		if(this.conn==null){
			try {
				Context ctx = new InitialContext();	
				Context envContext  = (Context)ctx.lookup("java:/comp/env");
				DataSource ds = (DataSource)envContext.lookup("jdbc/mysql");
		        this.conn = ds.getConnection();	
//				this.conn = (Connection) DriverManager.getConnection(Mysql.getConfig.getStringConfig("url"), 
//						Mysql.getConfig.getStringConfig("username"), Mysql.getConfig.getStringConfig("password"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.conn;
	}
}