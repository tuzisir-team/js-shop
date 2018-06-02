package config;

import java.util.HashMap;

public class Routes {
	protected static HashMap<String, String> getRoutes = new HashMap<String, String>(); // get路由配置
	protected static HashMap<String, String> postRoutes = new HashMap<String, String>(); // post路由配置
	
	public static HashMap<String, String> getRoutes() {
		Routes.getRoutes.put("getVertifCode", "admin.controller.LoginController@getVertifCode");
		Routes.getRoutes.put("admin_unlogin", "admin.controller.LoginController@adminUnlogin");
		Routes.getRoutes.put("user_list", "admin.controller.UsersController@userList");
		Routes.getRoutes.put("user_unlogin", "index.controller.LoginController@unlogin");
		Routes.getRoutes.put("user_goods_index", "index.controller.GoodsController@goodsIndex");
		return Routes.getRoutes;
	}

	public static HashMap<String, String> postRoutes() {
		Routes.postRoutes.put("admin_login", "admin.controller.LoginController@login");
		Routes.postRoutes.put("user_register", "index.controller.LoginController@register");
		Routes.postRoutes.put("user_login", "index.controller.LoginController@login");
		return Routes.postRoutes;
	}

}
