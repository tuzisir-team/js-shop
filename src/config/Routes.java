package config;

import java.util.HashMap;

public class Routes {
	protected static HashMap<String, String> getRoutes = new HashMap<String, String>(); // get路由配置
	protected static HashMap<String, String> postRoutes = new HashMap<String, String>(); // post路由配置
	
	public static HashMap<String, String> getRoutes() {
		Routes.getRoutes.put("test", "admin.controller.UsersController@test");
		return Routes.getRoutes;
	}

	public static HashMap<String, String> postRoutes() {
		Routes.postRoutes.put("testPost", "admin.controller.UsersController@test");
		Routes.postRoutes.put("register", "index.controller.UsersController1@register");
		return Routes.postRoutes;
	}

}
