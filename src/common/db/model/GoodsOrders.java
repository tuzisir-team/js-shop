package common.db.model;

public class GoodsOrders extends CommonModel{
	
	private int goodsOrderId;
	private int goodsId;
	private int orderId;
	private int goodsNum;
	private int goodsPrice;
	private int userId;
	private String goodsName;
	private int createTime;
	private int updateTime;
	private int goodsOrderStatus;
	
	public static GoodsOrders instantce() {
		return new GoodsOrders();
	}
	
	public int getGoodsOrderStatus() {
		return goodsOrderStatus;
	}
	public GoodsOrders setGoodsOrderStatus(int goodsOrderStatus) {
		this.goodsOrderStatus = goodsOrderStatus;
		return this;
	}
	public int getGoodsOrderId() {
		return goodsOrderId;
	}
	public GoodsOrders setGoodsOrderId(int goodsOrderId) {
		this.goodsOrderId = goodsOrderId;
		return this;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public GoodsOrders setGoodsId(int goodsId) {
		this.goodsId = goodsId;
		return this;
	}
	public int getOrderId() {
		return orderId;
	}
	public GoodsOrders setOrderId(int orderId) {
		this.orderId = orderId;
		return this;
	}
	public int getGoodsNum() {
		return goodsNum;
	}
	public GoodsOrders setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
		return this;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public GoodsOrders setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
		return this;
	}
	public int getUserId() {
		return userId;
	}
	public GoodsOrders setUserId(int userId) {
		this.userId = userId;
		return this;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public GoodsOrders setGoodsName(String goodsName) {
		this.goodsName = goodsName;
		return this;
	}
	public int getCreateTime() {
		return createTime;
	}
	public GoodsOrders setCreateTime(int createTime) {
		this.createTime = createTime;
		return this;
	}
	public int getUpdateTime() {
		return updateTime;
	}
	public GoodsOrders setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	public GoodsOrders end()
	 {
		 if (this.getGoodsId() > 0) {
			  this.condition.put("goods_id", this.getGoodsId());
		 }
		 if (this.getGoodsNum() > 0) {
			 this.condition.put("goods_num", this.getGoodsNum());
		 }
		 if (this.getGoodsPrice() > 0) {
			 this.condition.put("goods_price", this.getGoodsPrice());
		 }
		 if (this.getUserId() > 0) {
			 this.condition.put("user_id", this.getUserId());
		 }
		 if (this.getCreateTime() > 0) {
			 this.condition.put("create_time", this.getCreateTime());
		 }
		 if (this.getUpdateTime() > 0) {
			 this.condition.put("update_time", this.getUpdateTime());
		 }
//		 if (this.getUserEmail() != null ) {
//			 this.condition.put("user_email", "'"+this.getUserEmail()+"'");
//		 }
		 if (this.getGoodsOrderStatus() > -1) {
			 this.condition.put("goods_order_status", this.getGoodsOrderStatus());
		 }
		 return this;
	 }
}
