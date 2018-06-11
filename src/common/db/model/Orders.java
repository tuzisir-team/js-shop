package common.db.model;

public class Orders {
	
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
	
}
