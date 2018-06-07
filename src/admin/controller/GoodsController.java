package admin.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import admin.model.GoodsModel;
import admin.model.UsersModel;
import common.controller.Controller;
import common.db.model.Users;
import extend.page.Page;

public class GoodsController extends Controller {
	
	public void addGoodsPic() throws IOException {
		getOut().println(returnJson(200, "添加商品分类成功", this.parameters.get("imgFile")));
	}
	
	public void addGoods() throws ServletException, IOException {
		forward("/view/admin/goods/add_goods.jsp");
	}
	
	public void goodsList() throws ServletException, IOException {
		forward("/view/admin/goods/goods_list.jsp");
	}
	
	/**
	 * 添加商品类别
	 * @throws IOException
	 * @throws SQLException 
	 */
	public void addGoodsCategory() throws IOException, SQLException {
		String checkResult = checkParams("goods_category_name,post_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		switch(GoodsModel.addGoodsCategory(request.getParameter("goods_category_name"))) {
			case 1:
				getOut().println(returnJson(200, "添加商品分类成功"));
				break;
			case 2:
				getOut().println(returnJson(400, "商品分类名称重复"));
				break;
			default:
				getOut().println(returnJson(400, "添加商品分类失败"));
		}
	}
	
	/**
	 * 删除商品类别
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public void delGoodsCategory() throws IOException, NumberFormatException, SQLException {
		String checkResult = checkParams("goods_category_id,post_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		switch(GoodsModel.delGoodsCategory(Integer.parseInt(request.getParameter("goods_category_id")))) {
			case 1:
				getOut().println(returnJson(200, "删除商品类别成功"));
				break;
			case 2:
				getOut().println(returnJson(400, "该商品分类下有商品，不能删除"));
				break;
			default:
				getOut().println(returnJson(400, "删除商品分类失败"));
		}
	}
	/**
	 * 更改商品类别状态
	 * @throws IOException
	 * @throws SQLException
	 */
	public void changeCategoryStatus() throws IOException, SQLException {
		String checkResult = checkParams("goods_category_id,goods_category_status,post_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 更改状态
		int changeGoodsCategoryStatus = GoodsModel.changeCategoryStatus(Integer.parseInt(request.getParameter("goods_category_id")), 
				Integer.parseInt(request.getParameter("goods_category_status")));
		if (changeGoodsCategoryStatus == 1) {
			getOut().println(returnJson(200, "更改图书类别状态成功"));return;
		}
		getOut().println(returnJson(400, "更改图书类别状态失败"));return;
	}
	
	/**
	 * 商品类别列表
	 * @throws IOException
	 * @throws SQLException 
	 * @throws ServletException 
	 */
	public void goodsCategoryList() throws IOException, SQLException, ServletException {
		String checkResult = checkParams("get_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		String strcurpage = request.getParameter("currentpage");//接收当前页面值
		ArrayList goodsCategoryList = GoodsModel.goodsCategoryList();
        Page viewPage = Page.createPage(goodsCategoryList, strcurpage).setRoute("admin_goods_category_list");;//获取请求页面对象
		request.setAttribute("viewpage", viewPage);
		forward("/view/admin/goods/goods_category_list.jsp");
	}
}
