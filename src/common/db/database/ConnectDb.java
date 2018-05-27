package common.db.database;


import java.sql.Connection;

import config.GetConfig;

import common.base.inter.db.BaseDatabaseConnectInter;

public class ConnectDb {
	protected Connection conn = null;
	
	/**
	 * 为了看起来代码好看
	 * @return
	 */
	public static ConnectDb instance() {
		return new ConnectDb();
	}
	
	/**
	 * 工厂模式根据参数选择相应数据库
	 * @param databaseName
	 * @return
	 */
	public Connection databaseFactory(String... databaseName) {
		// 当有参数传递的时候
		if (databaseName.length == 1) {
			this.selectDatabaseConn(databaseName[0]);
		} else {
			// 选择数据库的默认连接
			this.selectDatabaseConn(GetConfig.instance(GetConfig.COMMON).getStringConfig("defaultDatabase"));
		}
		return conn;
	}
	
	/**
	 * 选择相应的数据库连接
	 * @param databaseName
	 * @return 
	 */
	protected Connection selectDatabaseConn(String databaseName) {
		if (databaseName.equals(GetConfig.MYSQL)) {
			conn = Mysql.instance().getConnection();
		} else {
			// 其它数据库连接
		}
		return conn;
	}
}
