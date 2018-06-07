package index.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ibm.db2.jcc.am.v;

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
}
