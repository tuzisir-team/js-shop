package admin.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import common.db.model.Goods;
import common.db.model.GoodsCategory;
import common.db.model.GoodsOrders;
import common.db.model.Model;
import common.db.model.UserAddress;
import common.db.model.Users;
import extend.file.MyselfFile;
import extend.time.Time;

public class GoodsModel extends Model{
	
	/**
	 * 修改图书信息
	 * @param goods
	 * @param goods_id
	 * @return
	 * @throws SQLException
	 */
	public static int editGoods(Goods goods, int goodsId) throws SQLException {
		GoodsModel goodsModel = new GoodsModel();
		// 添加
		return goodsModel.table("goods").where(Goods.instantce().setGoodsId(goodsId).end().getCondition()).
				update(goods.updateData());
	}
	
	/**
	 * 查找商品信息
	 * @param goodsId
	 * @return 
	 * @throws SQLException
	 */
	public static Goods findGoodsInfo(int goodsId) throws SQLException {
		GoodsModel goodsModel = new GoodsModel();
		ResultSet goods = goodsModel.table("goods").where(Goods.instantce().setGoodsId(goodsId).end().getCondition()).select();
		Goods goodsInfo = null;
		while (goods.next()) {
			goodsInfo = Goods.instantce().setGoodsId(goodsId)
			.setGoodsName(goods.getString(2))
			.setGoodsPic(goods.getString(3))
			.setGoodsBid(goods.getInt(4))
			.setGoodsDescribe(goods.getString(5))
			.setGoodsNum(goods.getInt(7))
			.setGoodsStatus(goods.getInt(8))
			.setGoodsCategoryId(goods.getInt(9))
			.setGoodsPrice(goods.getInt(10))
			.setCreateTime(goods.getInt(11))
			.setUpdateTime(goods.getInt(12));
		}
		return goodsInfo;
	}
	
	/**
	 * 删除商品
	 * @param goodsId
	 * @return
	 * @throws SQLException
	 */
	public static int delGoods(int goodsId, HttpServlet servlet) throws SQLException {
		GoodsModel goodsModel = new GoodsModel();
		String picUrl = null;
		// 查找这条记录
		ResultSet goods = goodsModel.table("goods").where(Goods.instantce().setGoodsId(goodsId).end().getCondition()).select();
		while (goods.next()) {
			picUrl = goods.getString(3);
		}
		goodsModel.startTrans(); // 开启事务
		try {
			// 删除记录
			if (goodsModel.table("goods").where(Goods.instantce().setGoodsId(goodsId).end().getCondition()).delete() == 0) {
				throw new Exception(); // 主动抛出异常
			}
			// 删除图片
			MyselfFile.delFile(servlet.getServletContext().getRealPath("/") + "static/img/goods/", 
					picUrl.substring(picUrl.lastIndexOf("/")+1));
			goodsModel.commit(); // 事务提交
		} catch (Exception e) {
			goodsModel.endTrans().rollback(); // 关闭事务，事务回滚
			return 0;
		}
		return 1;
	}
	
	/**
	 * 改变商品状态
	 * @param goodsCategoryId
	 * @param goodsCategoryStatus
	 * @return
	 * @throws SQLException
	 */
	public static int changeGoodsStatus(int goodsId, int goodsStatus) throws SQLException {
		GoodsModel goodsModel = new GoodsModel();
		if (goodsStatus == 1) {
			goodsStatus = 0;
		} else if (goodsStatus == 0) {
			goodsStatus = 1;
		} else {
			return 0;
		}
		return goodsModel.table("goods").
				where(Goods.instantce().setGoodsId(goodsId).end().getCondition()).
				update(Goods.instantce().setGoodsStatus(goodsStatus).end().updateData());
	}
	
	/**
	 * 查询所有商品
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList selectGoods() throws SQLException {
		GoodsModel goodsModel = new GoodsModel();
		ResultSet allGoods = goodsModel.table("goods").fields("goods.*,goods_category_name").
				hasOne("goods_category", "goods_category_id", "goods_category_id").
				where("goods_category.goods_category_id=goods.goods_category_id").select();
		ArrayList goodsList = new ArrayList();
		while(allGoods.next()){
			Goods goods = new Goods();
			goods.setGoodsId(allGoods.getInt(1)).setGoodsName(allGoods.getString(2)).setGoodsPic(allGoods.getString(3)).
			setGoodsBid(allGoods.getInt(4)).setGoodsDescribe(allGoods.getString(5)).setGoodsNum(allGoods.getInt(7)).setGoodsStatus(allGoods.getInt(8))
			.setGoodsCategoryName(allGoods.getString(13)).setGoodsPrice(allGoods.getInt(9)).setCreateTime(allGoods.getInt(11))
			.setUpdateTime(allGoods.getInt(12));
			goodsList.add(goods);
        }
		return goodsList;
	}
	
	/**
	 * 查找商品类别是否存在
	 * @param goodsCategory
	 * @return
	 * @throws SQLException
	 */
	public static Boolean findGoodsCategory(GoodsCategory goodsCategory) throws SQLException {
		GoodsModel goodsModel = new GoodsModel();
		return goodsModel.table("goods_category").where(goodsCategory.end().getCondition()).find();
	}
	
	/**
	 * 添加商品
	 * @param goods
	 * @return
	 * @throws SQLException
	 */
	public static int addGoods(Goods goods) throws SQLException {
		GoodsModel goodsModel = new GoodsModel();
		// 添加
		return goodsModel.table("goods").add(goods.getFields(), goods.getData());
	}
	
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
	public static ArrayList goodsCategoryList(int... status) throws SQLException {
		GoodsModel usersModel = new GoodsModel();
		ResultSet rs;
		if (status.length != 1) {
			rs = usersModel.table("goods_category").select();
		} else {
			rs = usersModel.table("goods_category").
					where(GoodsCategory.instantce().setGoodsCategoryStatus(status[0]).end().getCondition()).select();
		}
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
