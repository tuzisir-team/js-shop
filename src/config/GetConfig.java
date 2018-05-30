package config;

import java.util.HashMap;
import common.db.database.*;
public class GetConfig {
	
	protected HashMap<String, String> config = new HashMap<String, String>();

	protected static GetConfig myself = null;
	public static final String MYSQL = "MYSQL"; // mysql数据库配置
	public static final String COMMON = "COMMON"; // 数据库公共配置
	public static final String CONFIG = "CONFIG"; // 普通配置
	public static final String GETROUTES = "GETROUTES"; // get路由
	public static final String POSTROUTES = "POSTROUTES"; // post路由

	/**
	 * 构造函数用来初始化配置
	 * @param databaseType
	 */
	public GetConfig(String configType) {
		if (configType.equals(GetConfig.MYSQL)) {
			this.config = Database.mysqlConfig();
		} else if (configType.equals(GetConfig.COMMON)) {
			this.config = Database.commonConfig();
		} else if (configType.equals(GetConfig.CONFIG)) {
			this.config = Config.config();
		} else if (configType.equals(GetConfig.GETROUTES)) {
			this.config = Routes.getRoutes();
		} else if (configType.equals(GetConfig.POSTROUTES)) {
			this.config = Routes.postRoutes();
		}
	}
	
	/**
	 * 模仿单例模式目的是为了省代码
	 * @param databaseType
	 * @return
	 */
	public static GetConfig instance(String configType) {
		GetConfig.myself = new GetConfig(configType);
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
