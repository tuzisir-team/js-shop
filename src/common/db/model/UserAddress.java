package common.db.model;

public class UserAddress  extends CommonModel{

	private int userAddressId;
	private String userAddressName;
	private int userAddressStatus=-1;
	private int createTime;
	private int updateTime;
	private int userId;
	
	/**
	 * 目的是为了省代码
	 * @return
	 */
	public static UserAddress instantce() {
		return new UserAddress();
	}
	
	
	public int getUserAddressId() {
		return userAddressId;
	}


	public UserAddress setUserAddressId(int userAddressId) {
		this.userAddressId = userAddressId;
		return this;
	}


	public String getUserAddressName() {
		return userAddressName;
	}


	public UserAddress setUserAddressName(String userAddressName) {
		this.userAddressName = userAddressName;
		return this;
	}


	public int getUserAddressStatus() {
		return userAddressStatus;
	}


	public UserAddress setUserAddressStatus(int userAddressStatus) {
		this.userAddressStatus = userAddressStatus;
		return this;
	}


	public int getCreateTime() {
		return createTime;
	}


	public UserAddress setCreateTime(int createTime) {
		this.createTime = createTime;
		return this;
	}


	public int getUpdateTime() {
		return updateTime;
	}


	public UserAddress setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	public int getUserId() {
		return userId;
	}


	public UserAddress setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	public UserAddress end()
	 {
		 if (this.getUserAddressId() > 0) {
			  this.condition.put("user_address_id", this.getUserAddressId());
		 }
		 if (this.getUserAddressStatus() > -1) {
			  this.condition.put("user_address_status", this.getUserAddressStatus());
		 }
		 if (this.getUserId() > 0) {
			 this.condition.put("user_id", this.getUserId());
		 }
		 if (this.getUserAddressName() != null) {
			 this.condition.put("user_address_name", "'"+this.getUserAddressName()+"'");
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
