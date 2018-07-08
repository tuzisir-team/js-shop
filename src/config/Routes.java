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
		Routes.getRoutes.put("admin_add_goods_view", "admin.controller.GoodsController@addGoodsView"); // 添加商品页面
		Routes.getRoutes.put("admin_edit_goods_view", "admin.controller.GoodsController@editGoodsView"); // 修改商品页面
		Routes.getRoutes.put("admin_orders_list", "admin.controller.OrdersController@ordersList"); // 订单列表页面
		Routes.getRoutes.put("admin_order_details", "admin.controller.OrdersController@orderDetails"); // 商品详情
		Routes.getRoutes.put("admin_main", "admin.controller.OrdersController@weekCount"); // 周统计
		Routes.getRoutes.put("send_email", "admin.controller.EmailController@sendEmail"); // 周统计
		Routes.getRoutes.put("admin_set_email", "admin.controller.EmailController@setEmail"); // 设置邮件基本信息
		Routes.getRoutes.put("admin_email_template_list", "admin.controller.EmailController@emailTemplateList"); // 邮件模板里诶博爱
		Routes.getRoutes.put("admin_add_email_template_view", "admin.controller.EmailController@addEmailTemplateView"); // 添加邮件模板页面
		Routes.getRoutes.put("admin_edit_email_template_view", "admin.controller.EmailController@editEmailTemplateView"); // 添加邮件模板页面
		Routes.getRoutes.put("admin_email_list", "admin.controller.EmailController@emailList"); // 发送过的邮件列表
		Routes.getRoutes.put("admin_order_count", "admin.controller.OrdersController@orderCount"); // 订单统计
		Routes.getRoutes.put("admin_function_list", "admin.controller.AdminController@functionList"); // 管理员功能列表
		Routes.getRoutes.put("admin_rote_list", "admin.controller.AdminController@roteList"); // 管理员角色列表
		Routes.getRoutes.put("admin_add_rote_view", "admin.controller.AdminController@addRoteView"); // 添加管理员角色
		Routes.getRoutes.put("admin_edit_rote_view", "admin.controller.AdminController@editRoteView"); // 修改管理员角色

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
		Routes.postRoutes.put("admin_add_goods", "admin.controller.GoodsController@addGoods"); // 添加商品
		Routes.postRoutes.put("admin_edit_goods", "admin.controller.GoodsController@editGoods"); // 添加商品
		Routes.postRoutes.put("admin_is_goods_category", "admin.controller.GoodsController@isGoodsCategory"); // 检测商品类别是否存在
		Routes.postRoutes.put("admin_change_goods_status", "admin.controller.GoodsController@changeGoodsStatus"); // 更改商品状态
		Routes.postRoutes.put("admin_del_goods", "admin.controller.GoodsController@delGoods"); // 删除商品
		Routes.postRoutes.put("admin_order_find", "admin.controller.OrdersController@orderFind"); // 根据订单号查询订单
		Routes.postRoutes.put("admin_edit_admin_password", "admin.controller.AdminController@editAdminPassword"); // 修改管理员密码
		Routes.postRoutes.put("admin_edit_email_info", "admin.controller.EmailController@editEmailInfo"); // 修改邮件基本信息
		Routes.postRoutes.put("admin_add_email_template", "admin.controller.EmailController@addEmailTemplate"); // 添加邮件模板
		Routes.postRoutes.put("admin_edit_email_template", "admin.controller.EmailController@editEmailTemplate"); // 修改邮件模板
		Routes.postRoutes.put("admin_change_email_template_status", "admin.controller.EmailController@chagneEmailTemplateStatus"); // 修改邮件模板状态
		Routes.postRoutes.put("admin_del_email_template", "admin.controller.EmailController@delEmailTemplate"); // 删除邮件模板
		Routes.postRoutes.put("admin_find_email", "admin.controller.EmailController@findEmail"); // 查找邮件根据用户id
		Routes.postRoutes.put("admin_add_role", "admin.controller.AdminController@addRole"); // 添加角色
		Routes.postRoutes.put("admin_edit_role", "admin.controller.AdminController@editAdminRole"); // 修改角色
		Routes.postRoutes.put("admin_del_admin_role", "admin.controller.AdminController@delAdminRole"); // 删除角色
		Routes.postRoutes.put("admin_change_admin_role_status", "admin.controller.AdminController@changeAdminRoleStatus"); // 改变角色状态

		// 老二
		Routes.postRoutes.put("user_register", "index.controller.LoginController@register");
		Routes.postRoutes.put("user_login", "index.controller.LoginController@login");
		Routes.postRoutes.put("index_shopping_cart", "index.controller.OrderController@shoppingCart");
		return Routes.postRoutes;
	}

}
