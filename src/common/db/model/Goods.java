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
	
	public Goods end()
	 {
		 if (this.getGoodsId() > 0) {
			  this.condition.put("goods_id", this.getGoodsId());
		 }
		 if (this.getGoodsCategoryId() > 0) {
			 this.condition.put("goods_category_id", this.getGoodsCategoryId());
		 }
		 
		 return this;
	 } 
	
}
