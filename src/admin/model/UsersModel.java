package admin.model;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.db.model.Model;
import common.db.model.Users;
import extend.page.Page;

public class UsersModel extends Model{
	
	public static ArrayList userList() throws SQLException {
		UsersModel usersModel = new UsersModel();
		ResultSet rs = usersModel.table("users").select();
		ArrayList videoList = new ArrayList();
        try {//将查询结果放进容器中
            while(rs.next()){
                Users v = new Users();
                v.setUserId(rs.getInt(1));
                videoList.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videoList;
	}
	
	//构建Page模型
    public static Page construPage(int currentPage, int pageSize) throws SQLException{
        //获取数据库查询结果集
        ArrayList videoList = new ArrayList();
        videoList = userList();
        //构建page模型
        int totalRecord = videoList.size();
        System.out.println("数据规模："+totalRecord);//调试可忽略
        Page pg = new Page(currentPage,pageSize,totalRecord);//构建page对象
        ArrayList currentList = new ArrayList();//每页显现的那九条数据的容器
        int startIndex = pg.getStartIndex();//每一页的起始索引
        int endIndex = pg.getEndIndex();//每一页的终止索引
        System.out.println("起始数据索引："+startIndex);//调试可忽略
        System.out.println("最后数据索引："+endIndex);//调试可忽略
        //根据每页的起始数据和最后一条数据的索引选出那九条数据并复制到currentlist
        currentList.addAll(videoList.subList(startIndex, endIndex+1));
        System.out.println("展示数据规模："+currentList.size());
        pg.setList(currentList);//将currentlist设置到page对象的容器
        pg.setRoute("user_list");
        return pg;
    }

}
