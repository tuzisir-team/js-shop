package extend.email;

import java.sql.ResultSet;
import java.sql.SQLException;

import common.db.model.EmailInfo;
import common.db.model.EmailTemplate;
import common.db.model.Emails;
import common.db.model.Model;
import common.db.model.Users;
import extend.log.Log;
import extend.time.Time;

public class EmailFactory extends Model{
	public static final String REGISTER = "REGISTER";
	public static final String LOGIN = "LOGIN";
	public static final String PAY = "PAY";
	public static final String REFUND = "REFUND"; 
	// 本对象
	private static EmailFactory myself;
	private EmailInfo emailInfo;
	
	// 用户信息
	private ResultSet userInfo;
	
	// 模板信息
	private ResultSet emailTemplate;

	/**
	 * 构造参数
	 * @throws SQLException 
	 */
	public EmailFactory() throws SQLException {
		this.getEmailInfo();
	}
	
	/**
	 * 得到邮箱基本参数
	 * @throws SQLException
	 */
	private void getEmailInfo() throws SQLException {
		// 查出邮箱基本参数
		ResultSet emailInfoRes = this.table("email_info").select();
		emailInfoRes.next();
		emailInfo = EmailInfo.instance().setEmailInfoId(emailInfoRes.getInt(1))
		.setFromEmail(emailInfoRes.getString(2))
		.setFromEmailUserName(emailInfoRes.getString(3))
		.setFromEmailPassword(emailInfoRes.getString(4))
		.setHost(emailInfoRes.getString(5))
		.setProtocol(emailInfoRes.getString(6))
		.setTimeout(emailInfoRes.getInt(7))
		.setAuth(emailInfoRes.getInt(8))
		.setUpdateTime(emailInfoRes.getInt(9))
		.setPort(emailInfoRes.getInt(10));
	}
	
	/**
	 * 单例模式返回对象
	 * @return
	 * @throws SQLException 
	 */
	public static EmailFactory instance() throws SQLException {
		if (myself == null) {
			myself = new EmailFactory();
		}
		return myself;
	}
	
	public boolean send(int userId, String emailType) throws SQLException {
		Log.instance().debug("发送邮件,用户id:"+userId+"邮件类型:"+emailType);
		// 查找用户信息
		if (!this.getUserInfo(userId)) return false;
		// 查找对应的邮件模板
		if (!this.getEmailTemplate(emailType)) return false;
		// 保存邮件进库
		if (!this.addEmail()) return false;
		SendEmail sendEmail = new SendEmail(emailInfo);
		sendEmail.setEmailTitle(emailTemplate.getString(2))
			.setEmailContent(emailTemplate.getString(3))
			.setSendEmail(userInfo.getString(4)).start();
		return true;
	} 
	
	/**
	 * 添加发送邮件记录
	 * @return
	 * @throws SQLException
	 */
	public boolean addEmail() throws SQLException {
		Emails emails = Emails.instantce()
				.setUserId(userInfo.getInt(1))
				.setEmailTitle(emailTemplate.getString(2))
				.setEmailContent(emailTemplate.getString(3))
				.setCreateTime(Time.getDateTime())
				.setUpdateTime(Time.getDateTime())
				.setEmailStatus(1)
				.setEmailType(emailTemplate.getInt(4))
				.setUserEmail(userInfo.getString(4)).end();
		if (this.table("emails").add(emails.getFields(), emails.getData()) != 1) {
			Log.instance().error("添加邮件记录失败");
			return false;
		} 
		return true;
	}
	
	/**
	 * 得到对应的邮件模板
	 * @param emailType
	 * @return
	 * @throws SQLException
	 */
	private boolean getEmailTemplate(String emailType) throws SQLException {
		int type;
		switch (emailType) {
			case EmailFactory.REGISTER:
				type = 1;
				break;
			case EmailFactory.LOGIN:
				type = 2;
				break;
			case EmailFactory.PAY:
				type = 3;
				break;
			case EmailFactory.REFUND:
				type = 4;
				break;
			default:
				Log.instance().debug("没有此邮件模板类型:"+emailType);
				return false;
		}
		ResultSet emailTemplate = this.table("email_template")
				.where(
						EmailTemplate.instance()
						.setEmailTemplateType(type)
						.setEmailTemplateStatus(1).end().getCondition()
				).select();
		if (emailTemplate.next()) {
			this.emailTemplate = emailTemplate;
			return true;
		}
		Log.instance().debug("没有查到邮件模板，模板类型:"+type);
		return false;
	}
	
	/**
	 * 查找用户信息
	 * @param userId
	 * @return 
	 * @throws SQLException 
	 */
	private boolean getUserInfo(int userId) throws SQLException {
		ResultSet userInfoRes = this.table("users").where(Users.instantce().setUserId(userId).end().getCondition())
				.select();
		if (userInfoRes.next()) {
			this.userInfo = userInfoRes;
			return true;
		}
		Log.instance().debug("发送邮件,没有找到此用户，用户id："+userId);
		return false;
	}
	
}
