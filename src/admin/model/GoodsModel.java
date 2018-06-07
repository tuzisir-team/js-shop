package admin.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.db.model.Goods;
import common.db.model.GoodsCategory;
import common.db.model.GoodsOrders;
import common.db.model.Model;
import common.db.model.UserAddress;
import common.db.model.Users;
import extend.time.Time;

public class GoodsModel extends Model{
	
	/**
	 * 添加商品分类
	 * @param goodsCategoryName
	 * @return
	 * @throws SQLException
	 */
	public static int addGoodsCategory(String goodsCategoryName) throws SQLException {
		GoodsModel goodsModel = new GoodsModel();
		// 查找是否存在
		if (goodsModel.table("goods_category").where(GoodsCategory.instantce().setGoodsCategoryName(goodsCategoryName).end().getCondition()).find()) {
			return 2; // 已经存在
		}
		// 添加
		GoodsCategory goodsCategory = GoodsCategory.instantce().setGoodsCategoryName(goodsCategoryName).
				setCreateTime(Time.getDateTime()).setUpdateTime(Time.getDateTime()).setGoodsCategoryStatus(1).end();
		return goodsModel.table("goods_category").add(goodsCategory.getFields(), goodsCategory.getData());
	}
	
	/**
	 * 删除商品类别
	 * @param goodsCategoryId
	 * @return
	 * @throws SQLException
	 */
	public static int delGoodsCategory(int goodsCategoryId) throws SQLException {
		GoodsModel goodsModel = new GoodsModel();
		// 查找商品是否存在
		Boolean findGoods = goodsModel.table("goods").where(Goods.instantce().setGoodsCategoryId(goodsCategoryId).end().getCondition()).find();
		if (findGoods) {
			return 2; // 商品已经存在
		}
		// 删除商品分类
		int delGoodsCategory = goodsModel.table("goods_category").where(GoodsCategory.instantce().setGoodsCategoryId(goodsCategoryId).end().getCondition()).delete();
		if (delGoodsCategory > 0) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * 改变商品类别状态
	 * @param goodsCategoryId
	 * @param goodsCategoryStatus
	 * @return
	 * @throws SQLException
	 */
	public static int changeCategoryStatus(int goodsCategoryId, int goodsCategoryStatus) throws SQLException {
		GoodsModel goodsModel = new GoodsModel();
		if (goodsCategoryStatus == 1) {
			goodsCategoryStatus = 0;
		} else if (goodsCategoryStatus == 0) {
			goodsCategoryStatus = 1;
		} else {
			return 0;
		}
		return goodsModel.table("goods_category").
				where(GoodsCategory.instantce().setGoodsCategoryId(goodsCategoryId).end().getCondition()).
				update(GoodsCategory.instantce().setGoodsCategoryStatus(goodsCategoryStatus).end().updateData());
	}
	
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
}
