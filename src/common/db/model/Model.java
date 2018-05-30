package common.db.model;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.db.database.ConnectDb;
import common.db.database.MysqlApi;
import config.GetConfig;

public class Model {
	protected String table;
	protected String tableModel;
	protected String where;
	protected String group;
	protected String having;
	protected ResultSet selectResult;
	protected int limit = 0;
	public static final String USERS = "USERS";
	public final String USERSMODEL = "common.db.model.UsersModel";
	
	/**
	 * 设置表
	 * @param table
	 * @return
	 */
	public Model table(String table) {
		this.table = table;
		return this;
	}
	
	/**
	 * 设置sql条件
	 * @param tableModel
	 * @return
	 */
	public Model where(String where) {
        this.where = where;
		return this;
	}
	
	/**
	 * 查询条数
	 * @param limit
	 * @return
	 */
	public Model limit(int limit) {
		this.limit = limit;
		return this;
	}
	
	/**
	 * 分组
	 * @param group
	 * @return
	 */
	public Model group(String group) {
		this.group = group;
		return this;
	}
	
	/**
	 * 分组条件
	 * @param having
	 * @return
	 */
	public Model having(String having) {
		this.having = having;
		return this;
	}
	
	/**
	 * 添加
	 * @return 
	 * @throws SQLException 
	 */
	public int add(String fields, String values) throws SQLException {
		String sql;
		sql = "insert into " + this.table + "("+ fields +") " +
				"values(" + values +")";
		return this.databaseType(sql);
	}
	
	/**
	 * 删除记录
	 * @return
	 * @throws SQLException 
	 */
	public int delete() throws SQLException {
		String sql;
		sql = "delete from " + this.table + " where " + this.where;
		return this.databaseType(sql);
	}
	
	/**
	 * 更新数据
	 * @param data
	 * @return
	 * @throws SQLException 
	 */
	public int update(String data) throws SQLException {
		String sql;
		sql = "update "+ this.table + " set " + data + " where " + this.where;
		return this.databaseType(sql);
	}
	
	public Boolean find() throws SQLException {
		ResultSet findResult = this.select();
		findResult.last();
		if (findResult.getRow() > 0)  {
			return true;
		}
		return false;
	}
	
	/**
	 * 查询操作
	 * @return
	 * @throws SQLException 
	 */
	public ResultSet select() throws SQLException {
		String sql;
		ResultSet res = null;
		sql = "select * from " + this.table;
		if (this.where != null) {
			sql +=  " where " + this.where;
		}
		if (this.group != null) {
			sql += " group " + this.group;
		}
		if (this.having != null) {
			sql += " having " + this.having;
		}
		if (this.limit != 0) {
			sql += " limit " + this.limit;
		}
		this.databaseType(sql, true);
		return this.selectResult;
	}
	
	/**
	 * 判断数据库类型，并且按照查询或者更新进行操作
	 * @param sql
	 * @param isSelect
	 * @return
	 * @throws SQLException 
	 */
	protected int databaseType(String sql, boolean... isSelect) throws SQLException {
		System.out.println(sql);
		int result = 0;
		if (GetConfig.instance(GetConfig.COMMON).getStringConfig("defaultDatabase").equals(GetConfig.MYSQL)) {
			if (isSelect.length == 1 && isSelect[0]) {
				this.selectResult = MysqlApi.instance().selectApi(sql);
			} else {
				result = MysqlApi.instance().updateApi(sql);
			}
		}
		return result;
	}
}
