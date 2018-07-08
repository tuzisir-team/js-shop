package common.db.model;

public class AdminRole extends CommonModel{
	private int adminRoleId;
	private String adminRoleName;
	private String adminRoleJurisdiction;
	private int createTime;
	private int updateTime;
	private int adminRoleStatus = -1;
	public static AdminRole instance() {
		return new AdminRole();
	}
	public int getAdminRoleId() {
		return adminRoleId;
	}
	public AdminRole setAdminRoleId(int adminRoleId) {
		this.adminRoleId = adminRoleId;
		return this;
	}
	public String getAdminRoleName() {
		return adminRoleName;
	}
	public AdminRole setAdminRoleName(String adminRoleName) {
		this.adminRoleName = adminRoleName;
		return this;
	}
	public String getAdminRoleJurisdiction() {
		return adminRoleJurisdiction;
	}
	public AdminRole setAdminRoleJurisdiction(String adminRoleJurisdiction) {
		this.adminRoleJurisdiction = adminRoleJurisdiction;
		return this;
	}
	public int getCreateTime() {
		return createTime;
	}
	public AdminRole setCreateTime(int createTime) {
		this.createTime = createTime;
		return this;
	}
	public int getUpdateTime() {
		return updateTime;
	}
	public AdminRole setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	public int getAdminRoleStatus() {
		return adminRoleStatus;
	}
	public AdminRole setAdminRoleStatus(int adminRoleStatus) {
		this.adminRoleStatus = adminRoleStatus;
		return this;
	}
	
	public AdminRole end() {
		if (this.getAdminRoleName() != null) {
			this.condition.put("admin_role_name", "'" + this.getAdminRoleName() + "'");				
		}
		if (this.getAdminRoleJurisdiction() != null) {
			this.condition.put("admin_role_jurisdiction", "'" + this.getAdminRoleJurisdiction() + "'");				
		}
		if (this.getCreateTime() > 0) {
			this.condition.put("create_time", this.getCreateTime());
		}
		if (this.getUpdateTime() > 0) {
			this.condition.put("update_time", this.getUpdateTime());
		}
		if (this.getAdminRoleId() > 0) {
			this.condition.put("admin_role_id", this.getAdminRoleId());
		}
		if (this.getAdminRoleStatus() > -1) {
			this.condition.put("admin_role_status", this.getAdminRoleStatus());
		}
		return this;
	}
	
}
