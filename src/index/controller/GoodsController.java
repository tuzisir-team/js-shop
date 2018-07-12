package index.controller;

import index.model.GoodsModel;
import index.model.OrdersModel;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import common.controller.Controller;
import common.db.model.Goods;
import common.db.model.GoodsCategory;

public class GoodsController extends Controller{
	private ArrayList goodsCategoryList;//拿到所有商品类别
	/**
	 * 查找商品类别和商品
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void goodsIndex() throws ServletException, IOException, SQLException{
		//查找商品类别并返回
		this.goodsCategoryList();
		request.setAttribute("goodsCategory", goodsCategoryList);
		//查找商品并返回
		ArrayList goodsList=GoodsModel.goodsList();
		request.setAttribute("goodsList", goodsList);
		forward("/view/index/index.jsp");
	}
	
	/**
	 * 主页面中根据商品类别ID查找该类别下的商品
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void categoryGoods() throws ServletException, IOException, SQLException{
		this.goodsCategoryList();
		ArrayList goodsCategoryList = new ArrayList();
		//根据页面传来的ID，得到商品类别ID和商品类别名并返回
		GoodsCategory v = new GoodsCategory();
		v.setGoodsCategoryId(Integer.parseInt(request.getParameter("goods_catecory_id"))).setGoodsCategoryName(request.getParameter("goods_catecory_name"));
		goodsCategoryList.add(v);
		request.setAttribute("goodsCategory",goodsCategoryList);
		//完成对商品类别下所有商品的查找
		ArrayList chooseGoodsCategory=GoodsModel.
				chooseGoodsCategory( 
						Integer.parseInt(request.getParameter("goods_catecory_id")
				)
		);
		request.setAttribute("goodsList", chooseGoodsCategory);
		forward("/view/index/index.jsp");
	}
	
	/**
	 * 商品详情页面
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void goodsIntroduce() throws ServletException, IOException, SQLException{
		//对商品详情查找
		ArrayList goodsInfo=GoodsModel.goodsInfo(Integer.parseInt(request.getParameter("goods_id")),Integer.parseInt(request.getParameter("goods_catecory_id")));
		request.setAttribute("goodsInfo", goodsInfo);
		//完成对商品类别下商品的查找，指定查找3条
		ArrayList chooseGoodsCategory=GoodsModel.
				chooseGoodsCategory( 
						Integer.parseInt(request.getParameter("goods_catecory_id")
				),3
		);
		request.setAttribute("chooseGoodsCategory", chooseGoodsCategory);
		//返回到页面
		forward("/view/index/goodsIntroduce.jsp");
	}
	protected void goodsCategoryList() throws SQLException {
		goodsCategoryList=GoodsModel.goodsCategoryList();
		request.setAttribute("goodsCategoryList", goodsCategoryList);
	}
	/**
	 * 主页对商品的查找
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void findShop() throws ServletException, IOException, SQLException{
		GoodsModel ordersModel= new GoodsModel();
		goodsCategoryList=GoodsModel.goodsCategoryList();
		System.out.println(request.getParameter("shop_name"));
		ArrayList goodList=GoodsModel.findShop(request.getParameter("shop_name"),true);
			request.setAttribute("findGoods", goodList);
			request.setAttribute("goodsCategoryList", goodsCategoryList);
		forward("/view/index/index.jsp");
	}
}
