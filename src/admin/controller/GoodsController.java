package admin.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import admin.model.GoodsModel;
import admin.model.UsersModel;
import common.controller.Controller;
import common.db.model.Goods;
import common.db.model.GoodsCategory;
import common.db.model.Users;
import extend.file.MyselfFile;
import extend.page.Page;
import extend.time.Time;

public class GoodsController extends Controller {
	
	/**
	 * 修改商品信息
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public void editGoods() throws IOException, NumberFormatException, SQLException {
		String checkResult = checkParams("goods_category_id,goods_name,goods_bid,goods_price,goods_num,goods_pic,goods_describe,post_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		if (GoodsModel.editGoods(
				Goods.instantce().setGoodsCategoryId(Integer.parseInt(request.getParameter("goods_category_id"))).
				setGoodsName(request.getParameter("goods_name")).
				setGoodsBid(Integer.parseInt(request.getParameter("goods_bid"))*100).
				setGoodsPrice(Integer.parseInt(request.getParameter("goods_price"))*100).
				setGoodsNum(Integer.parseInt(request.getParameter("goods_num"))).
				setGoodsPic(request.getParameter("goods_pic")).
				setGoodsDescribe(request.getParameter("goods_describe")).
				setCreateTime(Time.getDateTime()).
				setUpdateTime(Time.getDateTime()).end(), Integer.parseInt(request.getParameter("goods_id"))
		) > 0) {
			getOut().println(returnJson(200, "修改商品成功,正在跳转..."));return;
		}
		getOut().println(returnJson(400, "修改商品失败"));return;
	}
	
	/**
	 * 修改图书信息页面
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editGoodsView() throws SQLException, ServletException, IOException {
		String checkResult = checkParams("goods_id,get_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 查找所有分类
		request.setAttribute("goodsCategoryList", GoodsModel.goodsCategoryList(1));
		// 查找商品信息
		request.setAttribute("goodsInfo", GoodsModel.findGoodsInfo(Integer.parseInt(request.getParameter("goods_id"))));
		forward("/view/admin/goods/edit_goods.jsp");
	}
	
	/**
	 * 删除商品
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public void delGoods() throws IOException, NumberFormatException, SQLException {
		String checkResult = checkParams("goods_id,post_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		switch(GoodsModel.delGoods(Integer.parseInt(request.getParameter("goods_id")), servlet)) {
			case 1:
				getOut().println(returnJson(200, "删除商品成功"));
				break;
			default:
				getOut().println(returnJson(400, "删除商品失败"));
		}
	}
	
	/**
	 * 改变商品的状态
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public void changeGoodsStatus() throws IOException, NumberFormatException, SQLException {
		String checkResult = checkParams("goods_id,goods_status,post_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 更改状态
		int changeGoodsStatus = GoodsModel.changeGoodsStatus(Integer.parseInt(request.getParameter("goods_id")), 
				Integer.parseInt(request.getParameter("goods_status")));
		if (changeGoodsStatus == 1) {
			getOut().println(returnJson(200, "更改商品状态成功"));return;
		}
		getOut().println(returnJson(400, "更改商品状态失败"));return;
	}
	
	/**
	 * 检测是否存在商品类别
	 * @throws IOException
	 * @throws SQLException 
	 */
	public void isGoodsCategory() throws IOException, SQLException {
		String checkResult = checkParams("post_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 查询商品类别
		if (GoodsModel.findGoodsCategory(GoodsCategory.instantce().setGoodsCategoryStatus(1))) {
			getOut().println(returnJson(200, "存在商品类别"));return;
		}
		getOut().println(returnJson(400, "请先添加商品类别"));return;
	}
	
	/**
	 * 添加商品图片，返回商品地址
	 * @throws IOException
	 */
	public void addGoodsPic() throws IOException {
		if (this.parameters.get("goods_pic").length() > 0) {
			MyselfFile.delFile(servlet.getServletContext().getRealPath("/") + "static/img/goods/", 
					this.parameters.get("goods_pic").substring(this.parameters.get("imgFile").lastIndexOf("/")+1));
		}
		getOut().println(returnJson(200, "添加商品图片成功", this.parameters.get("imgFile")));
	}
	
	/**
	 * 添加商品
	 * @throws IOException
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public void addGoods() throws IOException, NumberFormatException, SQLException {
		String checkResult = checkParams("goods_category_id,goods_name,goods_bid,goods_price,goods_num,goods_pic,goods_describe,post_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		if (GoodsModel.addGoods(
				Goods.instantce().setGoodsCategoryId(Integer.parseInt(request.getParameter("goods_category_id"))).
				setGoodsName(request.getParameter("goods_name")).
				setGoodsBid(Integer.parseInt(request.getParameter("goods_bid"))*100).
				setGoodsPrice(Integer.parseInt(request.getParameter("goods_price"))*100).
				setGoodsNum(Integer.parseInt(request.getParameter("goods_num"))).
				setGoodsPic(request.getParameter("goods_pic")).
				setGoodsDescribe(request.getParameter("goods_describe")).
				setCreateTime(Time.getDateTime()).
				setUpdateTime(Time.getDateTime()).end()
		) > 0) {
			getOut().println(returnJson(200, "添加商品成功"));return;
		}
		getOut().println(returnJson(400, "添加商品失败"));return;
	}
	
	
	/**
	 * 添加商品页面
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public void addGoodsView() throws ServletException, IOException, SQLException {
		// 查找所有分类
		request.setAttribute("goodsCategoryList", GoodsModel.goodsCategoryList(1));
		forward("/view/admin/goods/add_goods.jsp");
	}
	
	/**
	 * 商品列表
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public void goodsList() throws ServletException, IOException, SQLException {
		String strcurpage = request.getParameter("currentpage"); // 接收当前页面值
		// 查询所有商品
		ArrayList goodsList = GoodsModel.selectGoods();
		if (goodsList.isEmpty()) {
			forward("/view/admin/goods/goods_list.jsp");
		}
		Page viewPage = Page.createPage(goodsList, strcurpage).setRoute("admin_goods_list"); // 获取请求页面对象
		request.setAttribute("viewpage", viewPage);
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
