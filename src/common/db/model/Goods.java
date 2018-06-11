package common.db.model;

public class Goods extends CommonModel{
	protected int goodsId;
	protected String goodsName;
	protected String goodsPic;
	protected String goodsDescribe;
	protected int goodsNum;
	protected int goodsPrice;
	protected int goodsCategoryId;
	protected int goodsStatus = -1;
	protected int createTime;
	protected int updateTime;
	protected int goodsBid;
	
	/**
	 * 目的是为了省代码
	 * @return
	 */
	public static Goods instantce() {
		return new Goods();
	}
	
	public int getGoodsId() {
		return goodsId;
	}
	public Goods setGoodsId(int goodsId) {
		this.goodsId = goodsId;
		return this;
	}
	public int getGoodsCategoryId() {
		return goodsCategoryId;
	}
	public Goods setGoodsCategoryId(int goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
		return this;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public Goods setGoodsName(String goodsName) {
		this.goodsName = goodsName;
		return this;
	}
	public String getGoodsPic() {
		return goodsPic;
	}
	public Goods setGoodsPic(String goodsPic) {
		this.goodsPic = goodsPic;
		return this;
	}
	public String getGoodsDescribe() {
		return goodsDescribe;
	}
	public Goods setGoodsDescribe(String goodsDescribe) {
		this.goodsDescribe = goodsDescribe;
		return this;
	}
	public int getGoodsNum() {
		return goodsNum;
	}
	public Goods setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
		return this;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public Goods setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
		return this;
	}
	public int getGoodsStatus() {
		return goodsStatus;
	}
	public Goods setGoodsStatus(int goodsStatus) {
		this.goodsStatus = goodsStatus;
		return this;
	}
	public int getCreateTime() {
		return createTime;
	}
	public Goods setCreateTime(int createTime) {
		this.createTime = createTime;
		return this;
	}
	public int getUpdateTime() {
		return updateTime;
	}
	public Goods setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	public int getGoodsBid() {
		return goodsBid;
	}
	public Goods setGoodsBid(int goodsBid) {
		this.goodsBid = goodsBid;
		return this;
	}
	
	public Goods end()
	 {
		 if (this.getGoodsId() > 0) {
			  this.condition.put("goods_id", this.getGoodsId());
		 }
		 if (this.getGoodsCategoryId() > 0) {
			 this.condition.put("goods_category_id", this.getGoodsCategoryId());
		 }
		 if (this.getCreateTime() > 0) {
			 this.condition.put("create_time", this.getCreateTime());
		 }
		 if (this.getUpdateTime() > 0) {
			 this.condition.put("update_time", this.getUpdateTime());
		 }
		 if (this.getGoodsBid() > 0) {
			 this.condition.put("goods_bid", this.getGoodsBid());
		 }
		 if (this.getGoodsPrice() > 0) {
			 this.condition.put("goods_price", this.getGoodsPrice());
		 }
		 if (this.getGoodsNum() > 0) {
			 this.condition.put("goods_num", this.getGoodsNum());
		 }
		 if (this.getGoodsStatus() > -1) {
			 this.condition.put("goods_status", this.getGoodsStatus());
		 }
		 if (this.getGoodsDescribe() != null) {
			 this.condition.put("goods_describe", "'"+this.getGoodsDescribe()+"'");
		 }
		 if (this.getGoodsName() != null) {
			 this.condition.put("goods_name", "'"+this.getGoodsName()+"'");
		 }
		 if (this.getGoodsPic() != null) {
			 this.condition.put("goods_pic", "'"+this.getGoodsPic()+"'");
		 } 
		 return this;
	 } 
	
}
