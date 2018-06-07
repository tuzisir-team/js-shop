package common.db.model;

public class GoodsCategory extends CommonModel{

	protected int goodsCategoryId;
	protected String goodsCategoryName;
	protected int goodsCategoryStatus = -1;
	protected int createTime;
	protected int updateTime;
	
	/**
	 * 目的是为了省代码
	 * @return
	 */
	public static GoodsCategory instantce() {
		return new GoodsCategory();
	}
	
	public int getGoodsCategoryId() {
		return goodsCategoryId;
	}
	public GoodsCategory setGoodsCategoryId(int goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
		return this;
	}
	public String getGoodsCategoryName() {
		return goodsCategoryName;
	}
	public GoodsCategory setGoodsCategoryName(String goodsCategoryName) {
		this.goodsCategoryName = goodsCategoryName;
		return this;
	}
	public int getGoodsCategoryStatus() {
		return goodsCategoryStatus;
	}
	public GoodsCategory setGoodsCategoryStatus(int goodsCategoryStatus) {
		this.goodsCategoryStatus = goodsCategoryStatus;
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
		 if (this.getGoodsCategoryId() > 0) {
			  this.condition.put("goods_category_id", this.getGoodsCategoryId());
		 }
		 if (this.getGoodsCategoryStatus() > -1) {
			 this.condition.put("goods_category_status", this.getGoodsCategoryStatus());
		 }
		 if (this.getGoodsCategoryName() != null) {
			 this.condition.put("goods_category_name", this.getGoodsCategoryName());
		 }
		 if (this.getCreateTime() > 0) {
			 this.condition.put("create_time", this.getCreateTime());
		 }
		 if (this.getUpdateTime() > 0) {
			 this.condition.put("update_time", this.getUpdateTime());
		 }
		 return this;
	} 
}
