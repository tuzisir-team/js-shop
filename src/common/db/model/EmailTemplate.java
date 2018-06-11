package common.db.model;

public class EmailTemplate extends CommonModel{
	private int emailTemplateId;
	private String emailTemplateTitle;
	private String emailTemplateContent;
	private int emailTemplateType;
	private int emailTemplateStatus = -1;
	private int createTime;
	private int updateTime;
	public static EmailTemplate instance() {
		return new EmailTemplate();
	}
	public int getEmailTemplateId() {
		return emailTemplateId;
	}
	public EmailTemplate setEmailTemplateId(int emailTemplateId) {
		this.emailTemplateId = emailTemplateId;
		return this;
	}
	public String getEmailTemplateTitle() {
		return emailTemplateTitle;
	}
	public EmailTemplate setEmailTemplateTitle(String emailTemplateTitle) {
		this.emailTemplateTitle = emailTemplateTitle;
		return this;
	}
	public String getEmailTemplateContent() {
		return emailTemplateContent;
	}
	public EmailTemplate setEmailTemplateContent(String emailTemplateContent) {
		this.emailTemplateContent = emailTemplateContent;
		return this;
	}
	public int getEmailTemplateType() {
		return emailTemplateType;
	}
	public EmailTemplate setEmailTemplateType(int emailTemplateType) {
		this.emailTemplateType = emailTemplateType;
		return this;
	}
	public int getEmailTemplateStatus() {
		return emailTemplateStatus;
	}
	public EmailTemplate setEmailTemplateStatus(int emailTemplateStatus) {
		this.emailTemplateStatus = emailTemplateStatus;
		return this;
	}
	public int getCreateTime() {
		return createTime;
	}
	public EmailTemplate setCreateTime(int createTime) {
		this.createTime = createTime;
		return this;
	}
	public int getUpdateTime() {
		return updateTime;
	}
	public EmailTemplate setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	public EmailTemplate end() {
		if (this.getEmailTemplateId() > 0) {
			this.condition.put("email_template_id", this.getEmailTemplateId());
		}
		if (this.getCreateTime() > 0) {
			this.condition.put("create_time", this.getCreateTime());
		}
		if (this.getUpdateTime() > 0) {
			this.condition.put("update_time", this.getUpdateTime());
		}
		if (this.getEmailTemplateContent() != null) {
			 this.condition.put("email_template_content", "'"+this.getEmailTemplateContent()+"'");
		}
		if (this.getEmailTemplateTitle() != null) {
			 this.condition.put("email_template_title", "'"+this.getEmailTemplateTitle()+"'");
		}
		if (this.getEmailTemplateStatus() > -1) {
			this.condition.put("email_template_status", this.getEmailTemplateStatus());
		}
		if (this.getEmailTemplateType() > 0) {
			this.condition.put("email_template_type", this.getEmailTemplateType());
		}
		return this;
	}
	
}
