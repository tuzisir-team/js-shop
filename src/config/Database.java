package config;

import java.util.HashMap;

public class Database {
	protected static HashMap<String, String> commonConfig = new HashMap<String, String>(); // 存放数据库的公共配置
	protected static HashMap<String, String> mysqlConfig = new HashMap<String, String>(); // 存放mysql数据库配置
	/**
	 * mysql 数据库的配置
	 * @return 
	 */
	public static HashMap<String, String> mysqlConfig() {
		Database.mysqlConfig.put("url", "jdbc:mysql://127.0.0.1:3306/shop?useUnicode=true&characterEncoding=utf8");
		Database.mysqlConfig.put("username", "root");
		Database.mysqlConfig.put("password", "guoyuzhao123");
		Database.mysqlConfig.put("driver", "com.mysql.jdbc.Driver");
		return Database.mysqlConfig;
	}
	
	/**
	 * 数据库的公共配置
	 * @return
	 */
	public static HashMap<String, String> commonConfig() {
		Database.commonConfig.put("defaultDatabase", "MYSQL");
		return Database.commonConfig;
	}

}
