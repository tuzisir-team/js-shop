package common.db.model;

public class Emails extends CommonModel{
	private int emailId;
	private int userId;
	private String userName;
	private String emailTitle;
	private String emailContent;
	private int createTime;
	private int updateTime;
	private int emailStatus = -1;
	private int emailType = -1;
	private String userEmail;
	public String getUserEmail() {
		return userEmail;
	}
	public Emails setUserEmail(String userEmail) {
		this.userEmail = userEmail;
		return this;
	}
	public static Emails instantce() {
		return new Emails();
	}
	public int getEmailId() {
		return emailId;
	}
	public Emails setEmailId(int emailId) {
		this.emailId = emailId;
		return this;
	}
	public int getUserId() {
		return userId;
	}
	public Emails setUserId(int userId) {
		this.userId = userId;
		return this;
	}
	public String getUserName() {
		return userName;
	}
	public Emails setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public String getEmailTitle() {
		return emailTitle;
	}
	public Emails setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
		return this;
	}
	public String getEmailContent() {
		return emailContent;
	}
	public Emails setEmailContent(String emailContent) {
		this.emailContent = emailContent;
		return this;
	}
	public int getCreateTime() {
		return createTime;
	}
	public Emails setCreateTime(int createTime) {
		this.createTime = createTime;
		return this;
	}
	public int getUpdateTime() {
		return updateTime;
	}
	public Emails setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	public int getEmailStatus() {
		return emailStatus;
	}
	public Emails setEmailStatus(int emailStatus) {
		this.emailStatus = emailStatus;
		return this;
	}
	public int getEmailType() {
		return emailType;
	}
	public Emails setEmailType(int emailType) {
		this.emailType = emailType;
		return this;
	}
	
	public Emails end() {
		if (this.getEmailContent() != null) {
			this.condition.put("email_content", "'" + this.getEmailContent() + "'");
		}
		if (this.getEmailTitle() != null) {
			this.condition.put("email_title", "'" + this.getEmailTitle() + "'");
		}
		if (this.getUserEmail() != null) {
			this.condition.put("user_email", "'" + this.getUserEmail() + "'");
		}
		if (this.getEmailId() > 0) {
			this.condition.put("email_id", this.getEmailId());
		}
		if (this.getEmailStatus() > -1) {
			this.condition.put("email_status", this.getEmailStatus());
		}
		if (this.getEmailType() > -1) {
			this.condition.put("email_type", this.getEmailType());
		}
		if (this.getUserId() > 0) {
			this.condition.put("user_id", this.getUserId());
		}
		if (this.getUserName() != null) {
			this.condition.put("user_name", "'" + this.getUserName() + "'");
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
