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

	public void goodsIndex() throws ServletException, IOException, SQLException{
		ArrayList goodsCategoryList=GoodsModel.goodsCategoryList();
		request.setAttribute("goodsCategoryList", goodsCategoryList);
		forward("/view/index/index.jsp");
	}
}
