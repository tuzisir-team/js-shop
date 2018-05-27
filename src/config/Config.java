package config;

import java.util.HashMap;

public class Config {
	protected static HashMap<String, String> config = new HashMap<String, String>(); // 存放数据库的公共配置
	
	/**
	 * 日志配置
	 * @return
	 */
	public static HashMap<String, String> commonConfig() {
		Config.config.put("logPath", this.getClass().getClassLoader().getResource("").getPath() + "../../runtime/"); // 日志目录
		return Database.commonConfig;
	}
}
