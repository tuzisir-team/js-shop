package common.db.model;

public class Admin extends CommonModel{
	protected int adminId;
	protected String adminName;
	protected String adminPassword;
	protected int adminStatus = 1;
	protected int createTime;
	protected int updateTime;
	
	/**
	 * 目的是为了省代码
	 * @return
	 */
	public static Admin instantce() {
		return new Admin();
	}
	
	public int getAdminId() {
		return adminId;
	}
	public Admin setAdminId(int adminId) {
		this.adminId = adminId;
		return this;
	}
	public String getAdminName() {
		return adminName;
	}
	public Admin setAdminName(String adminName) {
		this.adminName = adminName;
		return this;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public Admin setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
		return this;
	}
	public int getAdminStatus() {
		return adminStatus;
	}
	public Admin setAdminStatus(int adminStatus) {
		this.adminStatus = adminStatus;
		return this;
	}
	public int getCreateTime() {
		return createTime;
	}
	public Admin setCreateTime(int createTime) {
		this.createTime = createTime;
		return this;
	}
	public int getUpdateTime() {
		return updateTime;
	}
	public Admin setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	public Admin end()
	 {
		 if (this.getAdminId() > 0) {
			  this.condition.put("admin_id", this.getAdminId());
		 }
		 if(this.getAdminName() != null) {
			 this.condition.put("admin_name", "'"+this.getAdminName()+"'");
		 }
		 if (this.getAdminPassword() != null ) {
			 this.condition.put("admin_password", "'"+this.getAdminPassword()+"'");
		 }
		 if (this.getCreateTime() > 0) {
			 this.condition.put("create_time", this.getCreateTime());
		 }
		 if (this.getUpdateTime() > 0) {
			 this.condition.put("update_time", this.getUpdateTime());
		 }
		 if (this.getAdminStatus() > -1) {
			 this.condition.put("admin_status", this.getAdminStatus());
		 }
		 return this;
	 } 
	
}
