package config;

import java.util.HashMap;

public class Routes {
	protected static HashMap<String, String> getRoutes = new HashMap<String, String>(); // get路由配置
	protected static HashMap<String, String> postRoutes = new HashMap<String, String>(); // post路由配置
	
	public static HashMap<String, String> getRoutes() {
		// 秃子
		Routes.getRoutes.put("getVertifCode", "admin.controller.LoginController@getVertifCode"); // 管理员验证码
		Routes.getRoutes.put("admin_unlogin", "admin.controller.LoginController@adminUnlogin"); // 管理员退出
		Routes.getRoutes.put("user_address_list", "admin.controller.UsersController@userAddressList"); // 用户地址列表
		Routes.getRoutes.put("user_list", "admin.controller.UsersController@userList"); // 用户列表
		Routes.getRoutes.put("admin_shopping_cart", "admin.controller.UsersController@shoppingCart"); // 显示用户购物车信息
		Routes.getRoutes.put("admin_goods_category_list", "admin.controller.GoodsController@goodsCategoryList"); // 商品类别列表
		Routes.getRoutes.put("admin_goods_list", "admin.controller.GoodsController@goodsList"); // 商品列表
		Routes.getRoutes.put("admin_add_goods", "admin.controller.GoodsController@addGoods"); // 添加商品
		
		// 老二
		Routes.getRoutes.put("user_unlogin", "index.controller.LoginController@unlogin");
		Routes.getRoutes.put("user_goods_index", "index.controller.GoodsController@goodsIndex");
		Routes.getRoutes.put("index_category_goods", "index.controller.GoodsController@categoryGoods");
		Routes.getRoutes.put("goods_info", "index.controller.GoodsController@goodsIntroduce");
		Routes.getRoutes.put("user_login", "index.controller.LoginController@loginStatus");
		return Routes.getRoutes;
	}

	public static HashMap<String, String> postRoutes() {
		// 秃子
		Routes.postRoutes.put("admin_login", "admin.controller.LoginController@login");
		Routes.postRoutes.put("admin_change_user_status", "admin.controller.UsersController@changeUserStatus");
		Routes.postRoutes.put("change_category_status", "admin.controller.GoodsController@changeCategoryStatus"); // 更改商品分类状态
		Routes.postRoutes.put("admin_del_goods_category", "admin.controller.GoodsController@delGoodsCategory"); // 删除商品分类
		Routes.postRoutes.put("admin_add_goods_category", "admin.controller.GoodsController@addGoodsCategory"); // 添加商品分类
		Routes.postRoutes.put("admin_add_goods_pic", "admin.controller.GoodsController@addGoodsPic"); // 添加图片

		// 老二
		Routes.postRoutes.put("user_register", "index.controller.LoginController@register");
		Routes.postRoutes.put("user_login", "index.controller.LoginController@login");
		Routes.postRoutes.put("index_shopping_cart", "index.controller.OrderController@shoppingCart");
		return Routes.postRoutes;
	}

}
