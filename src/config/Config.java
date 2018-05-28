package config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Config {
	protected static HashMap<String, String> config = new HashMap<String, String>(); // 存放通用配置
	
	public Config() {
		// 日志默认目录+文件名称
		Config.config.put("logPath", this.getClass().getClassLoader().getResource("").getPath() + 
				"../../runtime/log/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString() + ".log");
	}
	
	/**
	 * 数据库的公共配置
	 * @return
	 */
	public static HashMap<String, String> config() {
		new Config();
		return Config.config;
	}
}
