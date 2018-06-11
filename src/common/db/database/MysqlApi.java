package common.db.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;

public class MysqlApi {
	private static MysqlApi myself = null;
	private Connection conn = null;
	
	public MysqlApi() {
		this.conn = ConnectDb.instance().databaseFactory(); // 获取数据库连接
	}
	/**
	 * 单例模式方法如下
	 * @return
	 */
	public static MysqlApi instance()
	{
		if (myself == null) {
			myself = new MysqlApi();
			return myself;
		}
		return myself;
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getConn() {
		return this.conn;
	}
	
	/**
	 * 对数据库的查询操作
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public ResultSet selectApi(String sql) throws SQLException
	{
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		ptmt = (PreparedStatement) this.conn.prepareStatement(sql); 
		rs=ptmt.executeQuery();
		return rs;
	}
	
	/**
	 * 更新数据的操作
	 * @param sql
	 * @return
	 */
	public int updateApi(String sql)
	{
		PreparedStatement ptmt = null;
		int rs = 0;
		try {
			ptmt = (PreparedStatement) this.conn.prepareStatement(sql); 
			rs=ptmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
}
