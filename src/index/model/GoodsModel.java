package index.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ibm.db2.jcc.am.v;

import common.db.model.Goods;
import common.db.model.GoodsCategory;
import common.db.model.Model;


public class GoodsModel extends Model{

	public static ArrayList goodsCategoryList() throws SQLException{
		GoodsModel goodsmodel=new GoodsModel();
		ResultSet rs=goodsmodel.table("goods_category").select();
		ArrayList goodsCategoryList=new ArrayList();
		while (rs.next()){
			GoodsCategory v=new GoodsCategory();
			v.setGoodsCategoryId(rs.getInt(1)).setGoodsCategoryName(rs.getString(2));
			goodsCategoryList.add(v);
		}
		return goodsCategoryList;
	}
	public static ArrayList goodsList() throws SQLException{
		GoodsModel goodsmodel=new GoodsModel();
		ResultSet rs = goodsmodel.
				querySelect("SELECT * FROM goods a,goods_category WHERE a.goods_category_id=goods_category.goods_category_id and " +
						"a.goods_status=1 and goods_category.goods_category_status=1 and " +
						"8 > (SELECT COUNT(*) FROM goods WHERE  " +
						"goods.goods_category_id=a.goods_category_id and goods.goods_id < a.goods_id) " +
						"ORDER BY a.goods_category_id,a.goods_id ASC;");
		ArrayList goodsList=new ArrayList();
		while (rs.next()){
			Goods v=new Goods();
			v.setGoodsId(rs.getInt(1)).setGoodsName(rs.getString(2))
			.setGoodsPic(rs.getString(3)).setGoodsDescribe(rs.getString(5))
			.setGoodsNum(rs.getInt(7)).setGoodsCategoryId(rs.getInt(9))
			.setGoodsPrice(rs.getInt(10));
			goodsList.add(v);
		}
		return goodsList;
	}
	public static ArrayList chooseGoodsCategory(int goodsCategoryId) throws SQLException{
		GoodsModel goodsmodel=new GoodsModel();
		ResultSet rs=goodsmodel.table("goods")
				.where("goods_category_id=" + goodsCategoryId +" and goods_status=1")
				.select();
		ArrayList chooseGoodsCategory=new ArrayList();
		while (rs.next()){
			System.out.print(rs.getString(2));
			Goods v=new Goods();
			v.setGoodsId(rs.getInt(1)).setGoodsName(rs.getString(2))
			.setGoodsPic(rs.getString(3)).setGoodsDescribe(rs.getString(5))
			.setGoodsNum(rs.getInt(7)).setGoodsCategoryId(rs.getInt(9))
			.setGoodsPrice(rs.getInt(10));
			chooseGoodsCategory.add(v);
		}
		return chooseGoodsCategory;
	}
}
