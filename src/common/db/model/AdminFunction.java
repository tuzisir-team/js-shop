package common.db.model;

public class AdminFunction extends CommonModel{
	private int adminFunctionId;
	private String adminFunctionUrl;
	private String adminFunctionName;
	private int adminFunctionStatus;
	private int createTime;
	private int updateTime;
	private int pid = -1;
	
	/**
	 * 写代码好看
	 * @return
	 */
	public static AdminFunction instance() {
		return new AdminFunction();
	}
	
	public int getAdminFunctionId() {
		return adminFunctionId;
	}
	public AdminFunction setAdminFunctionId(int adminFunctionId) {
		this.adminFunctionId = adminFunctionId;
		return this;
	}
	public String getAdminFunctionUrl() {
		return adminFunctionUrl;
	}
	public AdminFunction setAdminFunctionUrl(String adminFunctionUrl) {
		this.adminFunctionUrl = adminFunctionUrl;
		return this;
	}
	public String getAdminFunctionName() {
		return adminFunctionName;
	}
	public AdminFunction setAdminFunctionName(String adminFunctionName) {
		this.adminFunctionName = adminFunctionName;
		return this;
	}
	public int getAdminFunctionStatus() {
		return adminFunctionStatus;
	}
	public AdminFunction setAdminFunctionStatus(int adminFunctionStatus) {
		this.adminFunctionStatus = adminFunctionStatus;
		return this;
	}
	public int getCreateTime() {
		return createTime;
	}
	public AdminFunction setCreateTime(int createTime) {
		this.createTime = createTime;
		return this;
	}
	public int getUpdateTime() {
		return updateTime;
	}
	public AdminFunction setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	public int getPid() {
		return pid;
	}
	public AdminFunction setPid(int pid) {
		this.pid = pid;
		return this;
	}
	
	public AdminFunction end() {
		if (this.getAdminFunctionId() > 0) {
			this.condition.put("admin_function_id", this.getAdminFunctionId());
		}
		if (this.getAdminFunctionStatus() > 0) {
			this.condition.put("admin_function_status", this.getAdminFunctionStatus());
		}
		if (this.getPid() > -1) {
			this.condition.put("pid", this.getPid());
		}
		if (this.getCreateTime() > 0) {
			 this.condition.put("create_time", this.getCreateTime());
		 }
		 if (this.getUpdateTime() > 0) {
			 this.condition.put("update_time", this.getUpdateTime());
		 }
		if (this.getAdminFunctionName() != null) {
			this.condition.put("admin_function_name", "'" + this.getAdminFunctionUrl() + "'");
		}
		if (this.getAdminFunctionUrl() != null) {
			this.condition.put("admin_function_url", "'" + this.getAdminFunctionUrl() + "'");
		}
		return this;
	}
	
}
