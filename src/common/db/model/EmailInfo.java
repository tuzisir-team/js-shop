package common.db.model;

public class EmailInfo extends CommonModel{
	private int emailInfoId;
	private String fromEmail;
	private String fromEmailUserName;
	private String fromEmailPassword;
	private String host;
	private String protocol;
	private int timeout;
	private int auth = -1;
	private int port;
	private int updateTime;
	public int getUpdateTime() {
		return updateTime;
	}
	public EmailInfo setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	public static EmailInfo instance() {
		return new EmailInfo();
	}
	public int getEmailInfoId() {
		return emailInfoId;
	}
	public EmailInfo setEmailInfoId(int emailInfoId) {
		this.emailInfoId = emailInfoId;
		return this;
	}
	public String getFromEmail() {
		return fromEmail;
	}
	public EmailInfo setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
		return this;
	}
	public String getFromEmailUserName() {
		return fromEmailUserName;
	}
	public EmailInfo setFromEmailUserName(String fromEmailUserName) {
		this.fromEmailUserName = fromEmailUserName;
		return this;
	}
	public String getFromEmailPassword() {
		return fromEmailPassword;
	}
	public EmailInfo setFromEmailPassword(String fromEmailPassword) {
		this.fromEmailPassword = fromEmailPassword;
		return this;
	}
	public String getHost() {
		return host;
	}
	public EmailInfo setHost(String host) {
		this.host = host;
		return this;
	}
	public String getProtocol() {
		return protocol;
	}
	public EmailInfo setProtocol(String protocol) {
		this.protocol = protocol;
		return this;
	}
	public int getTimeout() {
		return timeout;
	}
	public EmailInfo setTimeout(int timeout) {
		this.timeout = timeout;
		return this;
	}
	public int getAuth() {
		return auth;
	}
	public EmailInfo setAuth(int auth) {
		this.auth = auth;
		return this;
	}
	public int getPort() {
		return port;
	}
	public EmailInfo setPort(int port) {
		this.port = port;
		return this;
	}
	public EmailInfo end() {
		if (this.getEmailInfoId() > 0) {
			this.condition.put("email_info_id", this.getEmailInfoId());
		}
		if (this.getAuth() > -1) {
			this.condition.put("auth", this.getAuth());
		}
		if (this.getEmailInfoId() > 0) {
			this.condition.put("email_info_id", this.getEmailInfoId());
		}
		if (this.getFromEmailPassword() != null) {
			this.condition.put("from_email_password", "'" + this.getFromEmailPassword() + "'");
		}
		if (this.getFromEmail() != null) {
			this.condition.put("from_email", "'" + this.getFromEmail() + "'");		
		}
		if (this.getFromEmailUserName() != null) {
			this.condition.put("from_email_user_name", "'" + this.getFromEmailUserName() + "'");	
		}
		if (this.getHost() != null) {
			this.condition.put("host", "'" + this.getHost() + "'");				
		}
		if (this.getPort() > 0) {
			this.condition.put("port", this.getPort());			
		}
		if (this.getTimeout() > 0) {
			this.condition.put("timeout", this.getTimeout());
		}
		if (this.getUpdateTime() > 0) {
			this.condition.put("update_time", this.getUpdateTime());
		}
		return this;
	}
}
