package index.controller;

import index.model.GoodsModel;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import common.controller.Controller;
import common.db.model.GoodsCategory;



public class GoodsController extends Controller{
	private ArrayList goodsCategoryList;
	public void goodsIndex() throws ServletException, IOException, SQLException{
		this.goodsCategoryList();
		ArrayList goodsList=GoodsModel.goodsList();
		request.setAttribute("goodsCategory", goodsCategoryList);
		request.setAttribute("goodsList", goodsList);
		forward("/view/index/index.jsp");
	}
	
	public void categoryGoods() throws ServletException, IOException, SQLException{
		this.goodsCategoryList();	
		request.setAttribute("goodsCategory",request.getParameter("goods_catecory_id")));
		ArrayList chooseGoodsCategory=GoodsModel.chooseGoodsCategory( Integer.parseInt(request.getParameter("goods_catecory_id")));
		request.setAttribute("goodsList", chooseGoodsCategory);
		forward("/view/index/index.jsp");
	}
	public void goodsIntroduce() throws ServletException, IOException, SQLException{

		forward("/view/index/goodsIntroduce.jsp");
	}
	
	protected void goodsCategoryList() throws SQLException {
		goodsCategoryList=GoodsModel.goodsCategoryList();
		request.setAttribute("goodsCategoryList", goodsCategoryList);
	}
}
