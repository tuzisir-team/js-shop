package extend.myjson;

import extend.log.Log;

public class MyJson {
	
	public String returnJson = "{";
	
	/**
	 * 省代码
	 * @return
	 */
	public static MyJson instance() {
		return new MyJson();
	}
	
	/**
	 * 设置数据格式
	 * @param keyValue
	 * @return
	 */
	public MyJson setKeyValue(String... keyValue) {
		if (keyValue.length == 2) {
			this.returnJson = this.returnJson + "\"" + keyValue[0] + "\":\"" + keyValue[1] + "\","; 
		} else if (keyValue.length == 1) {
			this.returnJson = this.returnJson + "\""  + keyValue[0] + "\","; 
		} else {
			Log.instance().error("设置json数据有误");
		}
		Log.instance().debug(this.returnJson);
		return this;
	}
	
	public String toString() {
		
		String returnData;
		if (this.returnJson.equals("{")) {
			returnData = this.returnJson + "}";
		} else {
			returnData = this.returnJson.substring(0, this.returnJson.length()-1) + "}";
		}
		Log.instance().debug(returnData);
		return returnData;
	}
}
