package index.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ibm.db2.jcc.am.v;

import common.db.model.Goods;
import common.db.model.GoodsCategory;
import common.db.model.Model;


public class GoodsModel extends Model{

	/**
	 * 商品类别列表
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList goodsCategoryList() throws SQLException {
		GoodsModel usersModel = new GoodsModel();
		ResultSet rs = usersModel.table("goods_category").select();
		ArrayList goodsCategoryList = new ArrayList();
		while(rs.next()){
			GoodsCategory v = new GoodsCategory();
			v.setGoodsCategoryId(rs.getInt(1)).setGoodsCategoryName(rs.getString(2)).setGoodsCategoryStatus(rs.getInt(3)).
			setCreateTime(rs.getInt(4)).setUpdateTime(rs.getInt(5));
			goodsCategoryList.add(v);
        }
		return goodsCategoryList;
	}
	/**
	 * 商品详情列表
	 * @return
	 * @throws SQLException
	 */
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
	/**
	 * 某类商品下商品
	 * @param goodsCategoryId
	 * @param limit
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList chooseGoodsCategory(int goodsCategoryId,int... limit) throws SQLException{
		GoodsModel goodsmodel=new GoodsModel();
		Model sqlModel = goodsmodel.table("goods")
				.where("goods_category_id=" + goodsCategoryId +" and goods_status=1");
		if (limit.length == 1){
			sqlModel = sqlModel.limit(limit[0]);
		}
		ResultSet rs = sqlModel.select();
		ArrayList chooseGoodsCategory=new ArrayList();
		while (rs.next()){
			Goods v=new Goods();
			v.setGoodsId(rs.getInt(1)).setGoodsName(rs.getString(2))
			.setGoodsPic(rs.getString(3)).setGoodsDescribe(rs.getString(5))
			.setGoodsNum(rs.getInt(7)).setGoodsCategoryId(rs.getInt(9))
			.setGoodsPrice(rs.getInt(10));
			chooseGoodsCategory.add(v);
		}
		return chooseGoodsCategory;
	}
	/**
	 * 某类商品下的某个商品，即商品详情
	 * @param goodsId
	 * @param goodsCategoryId
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList goodsInfo(int goodsId,int goodsCategoryId) throws SQLException {
		GoodsModel usersModel = new GoodsModel();
		ResultSet rs = usersModel.table("goods")
				.where("goods_id=" + goodsId +" and goods_category_id=" + goodsCategoryId +" and goods_status=1 and goods_num > 0")
				.select();
		ArrayList goodsInfo = new ArrayList();
		while (rs.next()){
			Goods v=new Goods();
			v.setGoodsId(rs.getInt(1)).setGoodsName(rs.getString(2))
			.setGoodsPic(rs.getString(3)).setGoodsDescribe(rs.getString(5))
			.setGoodsNum(rs.getInt(7)).setGoodsCategoryId(rs.getInt(9))
			.setGoodsPrice(rs.getInt(10));
			goodsInfo.add(v);
		}
		return goodsInfo;
	}
	public static ArrayList findShop(String shopName) throws SQLException{
		GoodsModel goodsmodel=new GoodsModel();
		ResultSet rs = goodsmodel.
				querySelect("select * from goods where goods_name like '%"+shopName+"%'");
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
}
