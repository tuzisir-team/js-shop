package config;

import java.util.HashMap;

public class Routes {
	protected static HashMap<String, String> getRoutes = new HashMap<String, String>(); // get路由配置
	protected static HashMap<String, String> postRoutes = new HashMap<String, String>(); // post路由配置
	
	public static HashMap<String, String> getRoutes() {
		// 秃子
		Routes.getRoutes.put("getVertifCode", "admin.controller.LoginController@getVertifCode");
		Routes.getRoutes.put("admin_unlogin", "admin.controller.LoginController@adminUnlogin");
		Routes.getRoutes.put("user_address_list", "admin.controller.UsersController@userAddressList"); // 用户地址列表
		Routes.getRoutes.put("user_list", "admin.controller.UsersController@userList");
		Routes.getRoutes.put("admin_shopping_cart", "admin.controller.UsersController@shoppingCart");
		
		// 老二
		Routes.getRoutes.put("user_unlogin", "index.controller.LoginController@unlogin");
		Routes.getRoutes.put("user_goods_index", "index.controller.GoodsController@goodsIndex");
		Routes.getRoutes.put("index_category_goods", "index.controller.GoodsController@categoryGoods");
		Routes.getRoutes.put("goods_info", "index.controller.GoodsController@goodsIntroduce");
		return Routes.getRoutes;
	}

	public static HashMap<String, String> postRoutes() {
		// 秃子
		Routes.postRoutes.put("admin_login", "admin.controller.LoginController@login");
		Routes.postRoutes.put("admin_change_user_status", "admin.controller.UsersController@changeUserStatus");
		Routes.postRoutes.put("register", "index.controller.UsersController1@register");
		
		// 老二
		Routes.postRoutes.put("user_register", "index.controller.LoginController@register");
		Routes.postRoutes.put("user_login", "index.controller.LoginController@login");
		return Routes.postRoutes;
	}

}
