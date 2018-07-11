package common.db.model;

public class Orders extends CommonModel{
	
	private int orderId;
	private int orderTotal;
	private int orderStatus;
	private int createTime;
	private int updateTime;
	private int userId;
	private String userAddressName;
	private String outTradeNo;
	private int completeTime;
	private String userName;
	/**
	 * 目的是为了省代码
	 * @return
	 */
	public static Orders instantce() {
		return new Orders();
	}
	public String getUserName() {
		return userName;
	}
	public Orders setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public int getOrderId() {
		return orderId;
	}
	public Orders setOrderId(int orderId) {
		this.orderId = orderId;
		return this;
	}
	public int getOrderTotal() {
		return orderTotal;
	}
	public Orders setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
		return this;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public Orders setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
		return this;
	}
	public int getCreateTime() {
		return createTime;
	}
	public Orders setCreateTime(int createTime) {
		this.createTime = createTime;
		return this;
	}
	public int getUpdateTime() {
		return updateTime;
	}
	public Orders setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	public int getUserId() {
		return userId;
	}
	public Orders setUserId(int userId) {
		this.userId = userId;
		return this;
	}
	public String getUserAddressName() {
		return userAddressName;
	}
	public Orders setUserAddressName(String userAddressName) {
		this.userAddressName = userAddressName;
		return this;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public Orders setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
		return this;
	}
	public int getCompleteTime() {
		return completeTime;
	}
	public Orders setCompleteTime(int completeTime) {
		this.completeTime = completeTime;
		return this;
	}
	public Orders end()
	 {
		 if (this.getOrderTotal() > 0) {
			 this.condition.put("order_total", this.getOrderTotal());
		 }
		 if (this.getOrderStatus() > -1) {
			  this.condition.put("order_status", this.getOrderStatus());
		 }
		 if (this.getUserId() > 0) {
			 this.condition.put("user_id", this.getUserId());
		 }
		 if (this.getUserAddressName() != null) {
			 this.condition.put("user_address_name", "'"+this.getUserAddressName()+"'");
		 }
		 if (this.getOutTradeNo() != null) {
			 this.condition.put("out_trade_no", "'"+this.getOutTradeNo()+"'");
		 }
		 if (this.getCreateTime() > 0) {
			 this.condition.put("create_time", this.getCreateTime());
		 }
		 if (this.getUpdateTime() > 0) {
			 this.condition.put("update_time", this.getUpdateTime());
		 }
		 if (this.getCompleteTime() > 0) {
			 this.condition.put("complete_time", this.getCompleteTime());
		 }
		 return this;
	 }
}
