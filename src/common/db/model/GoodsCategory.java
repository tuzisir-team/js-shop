package common.db.model;

public class GoodsCategory extends CommonModel{

	protected int goodscategoryid;
	protected String goodscategoryname;
	protected int goodscategorystatus = -1;
	protected int createTime;
	protected int updateTime;
	public int getGoodscategoryid() {
		return goodscategoryid;
	}
	public GoodsCategory setGoodscategoryid(int goodscategoryid) {
		this.goodscategoryid = goodscategoryid;
		return this;
	}
	public String getGoodscategoryname() {
		return goodscategoryname;
	}
	public GoodsCategory setGoodscategoryname(String goodscategoryname) {
		this.goodscategoryname = goodscategoryname;
		return this;
	}
	public int getGoodscategorystatus() {
		return goodscategorystatus;
	}
	public GoodsCategory setGoodscategorystatus(int goodscategorystatus) {
		this.goodscategorystatus = goodscategorystatus;
		return this;
	}
	public int getCreateTime() {
		return createTime;
	}
	public GoodsCategory setCreateTime(int createTime) {
		this.createTime = createTime;
		return this;
	}
	public int getUpdateTime() {
		return updateTime;
	}
	public GoodsCategory setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	public GoodsCategory end()
	 {
		 if (this.getGoodscategoryid() > 0) {
			  this.condition.put("goodscategory_id", this.getGoodscategoryid());
		 }
		 return this;
	 } 
}
