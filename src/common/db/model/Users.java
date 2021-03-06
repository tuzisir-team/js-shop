package common.db.model;

public class Users  extends CommonModel{

	protected int userId;
	protected String userName;
	protected String userPassword;
	protected String userEmail;
	protected String userPhone;
	protected int addressId;
	protected int userStatus = -1;
	protected int createTime;
	protected int updateTime;
	
	/**
	 * 目的是为了省代码
	 * @return
	 */
	public static Users instantce() {
		return new Users();
	}
	
	public int getUserId() {
		return userId;
	}
	public Users setUserId(int userId) {
		this.userId = userId;
		return this;
	}
	public String getUserName() {
		return userName;
	}
	public Users setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public Users setUserPassword(String userPassword) {
		this.userPassword = userPassword;
		return this;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public Users setUserEmail(String userEmail) {
		this.userEmail = userEmail;
		return this;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public Users setUserPhone(String userPhone) {
		this.userPhone = userPhone;
		return this;
	}
	public int getAddressId() {
		return addressId;
	}
	public Users setAddressId(int addressId) {
		this.addressId = addressId;
		return this;
	}
	public int getUserStatus() {
		return userStatus;
	}
	public Users setUserStatus(int userStatus) {
		this.userStatus = userStatus;
		return this;
	}
	public int getCreateTime() {
		return createTime;
	}
	public Users setCreateTime(int createTime) {
		this.createTime = createTime;
		return this;
	}
	public int getUpdateTime() {
		return updateTime;
	}
	public Users setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	public Users end()
	 {
		 if (this.getUserId() > 0) {
			  this.condition.put("user_id", this.getUserId());
		 }
		 if(this.getUserName() != null) {
			 this.condition.put("user_name", "'"+this.getUserName()+"'");
		 }
		 if (this.getAddressId() > 0) {
			 this.condition.put("address_id", this.getAddressId());
		 }
		 if (this.getUserPassword() != null ) {
			 this.condition.put("user_password", "'"+this.getUserPassword()+"'");
		 }
		 if (this.getUserPhone() != null ) {
			 this.condition.put("user_phone", "'"+this.getUserPhone()+"'");
		 }
		 if (this.getCreateTime() > 0) {
			 this.condition.put("create_time", this.getCreateTime());
		 }
		 if (this.getUpdateTime() > 0) {
			 this.condition.put("update_time", this.getUpdateTime());
		 }
		 if (this.getUserEmail() != null ) {
			 this.condition.put("user_email", "'"+this.getUserEmail()+"'");
		 }
		 if (this.getUserStatus() > -1) {
			 this.condition.put("user_status", this.getUserStatus());
		 }
		 return this;
	 } 
	
}
