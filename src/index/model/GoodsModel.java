package index.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ibm.db2.jcc.am.v;

import common.db.model.GoodsCategory;
import common.db.model.Model;


public class GoodsModel extends Model{

	public static ArrayList goodsCategoryList() throws SQLException{
		GoodsModel goodsmodel=new GoodsModel();
		ResultSet rs=goodsmodel.table("goods_category").select();
		ArrayList goodsCategoryList=new ArrayList();
		while (rs.next()){
			System.out.print(rs.getString(2));
			GoodsCategory v=new GoodsCategory();
			v.setGoodscategoryid(rs.getInt(1)).setGoodscategoryname(rs.getString(2));
			goodsCategoryList.add(v);
		}
		return goodsCategoryList;
	}	
}
