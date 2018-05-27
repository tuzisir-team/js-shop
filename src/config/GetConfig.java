package config;

import java.util.HashMap;
import common.db.database.*;
public class GetConfig {
	
	protected HashMap<String, String> config = new HashMap<String, String>();

	protected static GetConfig myself = null;
	public static final String MYSQL = "MYSQL"; // mysql数据库配置
	public static final String COMMON = "COMMON"; // 数据库公共配置
	
	/**
	 * 构造函数用来初始化数据库配置
	 * @param databaseType
	 */
	public GetConfig(String databaseType) {
		if (databaseType.equals(GetConfig.MYSQL)) {
			this.config = Database.mysqlConfig();
		} else if (databaseType.equals(GetConfig.COMMON)) {
			this.config = Database.commonConfig();
		} else {
			// 其它数据库
		}
	}
	
	/**
	 * 模仿单例模式目的是为了省代码
	 * @param databaseType
	 * @return
	 */
	public static GetConfig instance(String databaseType) {
		GetConfig.myself = new GetConfig(databaseType);
		return GetConfig.myself;
	}
	
	/**
	 * 获取整型配置参数
	 * @param key
	 * @return
	 */
	public int getIntConfig(String key) {
		int value = 0;
		if (this.config.containsKey(key)) {
			 value = Integer.parseInt(this.config.get(key));
		}
		return value;
	}
	
	/**
	 * 获取字符串类型的参数
	 * @param key
	 * @return
	 */
	public String getStringConfig(String key) {
		String value = null;
		if (this.config.containsKey(key)) {
			value = this.config.get(key);
		}
		System.out.println(key + value);
		return value;
	}
	
	/**
	 * 获取某种类型的所有配置
	 * @return
	 */
	public HashMap<String, String> getAll() {
		return this.config;
	}
	
}
