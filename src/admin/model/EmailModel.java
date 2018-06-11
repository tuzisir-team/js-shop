package admin.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.db.model.EmailInfo;
import common.db.model.EmailTemplate;
import common.db.model.Emails;
import common.db.model.Goods;
import common.db.model.Model;
import extend.log.Log;
import extend.time.Time;

public class EmailModel extends Model{
	
	/**
	 * 通过用户id查找邮件
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList findEmail(int userId) throws SQLException {
		EmailModel emailModel = new EmailModel();
		ResultSet emailsRes = emailModel.table("emails")
				.where(Emails.instantce().setEmailId(userId).end().getCondition())
				.hasOne("users", "user_id", "user_id")
				.fields("emails.*,users.user_name,users.user_email").select();
		ArrayList emailsList = new ArrayList();
		Emails emails = null;
		if (emailsRes.next()) {
			emails = Emails.instantce()
					.setEmailId(emailsRes.getInt(1))
					.setUserId(emailsRes.getInt(2))
					.setUserName(emailsRes.getString(10))
					.setEmailTitle(emailsRes.getString(3))
					.setEmailContent(emailsRes.getString(4))
					.setCreateTime(emailsRes.getInt(5))
					.setUpdateTime(emailsRes.getInt(6))
					.setEmailStatus(emailsRes.getInt(7))
					.setEmailType(emailsRes.getInt(8))
					.setUserEmail(emailsRes.getString(9));
			emailsList.add(emails);	
		}
		return emailsList;
	}
	
	/**
	 * 获取已发送邮件数据
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList emailList() throws SQLException {
		EmailModel emailModel = new EmailModel();
		ResultSet emailsRes = emailModel.table("emails")
				.hasOne("users", "user_id", "user_id")
				.fields("emails.*,users.user_name,users.user_email").select();
		ArrayList emailsList = new ArrayList();
		while (emailsRes.next()) {
			Emails emails = Emails.instantce()
					.setEmailId(emailsRes.getInt(1))
					.setUserId(emailsRes.getInt(2))
					.setUserName(emailsRes.getString(10))
					.setEmailTitle(emailsRes.getString(3))
					.setEmailContent(emailsRes.getString(4))
					.setCreateTime(emailsRes.getInt(5))
					.setUpdateTime(emailsRes.getInt(6))
					.setEmailStatus(emailsRes.getInt(7))
					.setEmailType(emailsRes.getInt(8))
					.setUserEmail(emailsRes.getString(9));
			emailsList.add(emails);	
		}
		return emailsList;
	}
	
	/**
	 * 删除邮件模板
	 * @param email_template_id
	 * @return
	 * @throws SQLException 
	 */
	public static int delEmailTemplate(int email_template_id) throws SQLException {
		EmailModel emailModel = new EmailModel();
		return emailModel.table("email_template")
			.where(EmailTemplate.instance().setEmailTemplateId(email_template_id).end().getCondition())
			.delete();
	}
	
	/**
	 * 改变邮件模板状态
	 * @param request
	 * @return 
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public static int chagneEmailTemplateStatus(HttpServletRequest request) throws NumberFormatException, SQLException {
		EmailModel emailModel = new EmailModel();
		int status;
		if (Integer.parseInt(request.getParameter("email_template_status")) == 0) {
			status = 1;
		} else {
			status = 0;
		}
		int updateRes = 0;
		emailModel.startTrans(); // 开启事务
		try {
			// 如果为启动，则把此类型的其它状态改为关闭
			if (Integer.parseInt(request.getParameter("email_template_status")) == 0) {
				emailModel.table("email_template")
					.where(EmailTemplate.instance().setEmailTemplateType(Integer.parseInt(request.getParameter("email_template_type"))).end().getCondition())
					.update(EmailTemplate.instance().setEmailTemplateStatus(0).end().updateData());
			}
			updateRes = emailModel.table("email_template").
				where(EmailTemplate.instance().setEmailTemplateId(Integer.parseInt(request.getParameter("email_template_id"))).end().getCondition())
				.update(EmailTemplate.instance().setEmailTemplateStatus(status).end().updateData());
			emailModel.commit(); // 提交事务
		} catch (Exception e) {
			emailModel.rollback().endTrans();
			Log.instance().error("更改邮件模板状态抛出异常,模板id:" + request.getParameter("email_template_id"));
		}
		return updateRes;
	}
	
	/**
	 * 修改email模板
	 * @param request
	 * @return
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public static int editEmailTemplate(HttpServletRequest request) throws NumberFormatException, SQLException {
		EmailModel emailModel = new EmailModel();
		// 修改
		return emailModel.table("email_template").
				where(EmailTemplate.instance().setEmailTemplateId(Integer.parseInt(request.getParameter("email_template_id"))).end().getCondition()).
				update(
						EmailTemplate.instance().setUpdateTime(Time.getDateTime())
						.setEmailTemplateTitle(request.getParameter("email_template_title"))
						.setEmailTemplateContent(request.getParameter("email_template_content")).end().updateData()
				);
	}
	
	/**
	 * 查找某一条记录
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	public static EmailTemplate findEmailTemplate(HttpServletRequest request) throws SQLException {
		EmailModel emailModel = new EmailModel();
		ResultSet emailTemplateRes = emailModel.table("email_template").where(EmailTemplate.instance().
				setEmailTemplateId(Integer.parseInt(request.getParameter("email_template_id"))).end().getCondition()).select();
		if (emailTemplateRes.next()) {
			return EmailTemplate.instance().setEmailTemplateId(emailTemplateRes.getInt(1))
			.setEmailTemplateTitle(emailTemplateRes.getString(2))
			.setEmailTemplateContent(emailTemplateRes.getString(3))
			.setEmailTemplateType(emailTemplateRes.getInt(4))
			.setEmailTemplateStatus(emailTemplateRes.getInt(5))
			.setCreateTime(emailTemplateRes.getInt(6))
			.setUpdateTime(emailTemplateRes.getInt(7));
		}
		return null;
	}
	
	/**
	 * 邮件模板列表
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList emailTemplateList (HttpServletRequest request) throws SQLException {
		EmailModel emailModel = new EmailModel();
		ResultSet emailTemplateRes = emailModel.table("email_template").select();
		ArrayList emailTemplateList = new ArrayList();
		//EmailTemplate emailTemplate;
		while (emailTemplateRes.next()) {
			EmailTemplate emailTemplate = EmailTemplate.instance().setEmailTemplateId(emailTemplateRes.getInt(1))
				.setEmailTemplateTitle(emailTemplateRes.getString(2))
				.setEmailTemplateContent(emailTemplateRes.getString(3))
				.setEmailTemplateType(emailTemplateRes.getInt(4))
				.setEmailTemplateStatus(emailTemplateRes.getInt(5))
				.setCreateTime(emailTemplateRes.getInt(6))
				.setUpdateTime(emailTemplateRes.getInt(7));
			emailTemplateList.add(emailTemplate);
		}
		return emailTemplateList;
	}
	
	/**
	 * 添加邮件模板
	 * @param request
	 * @return 
	 * @throws SQLException 
	 */
	public static int addEmailTemplate(HttpServletRequest request) throws SQLException {
		EmailModel emailModel = new EmailModel();
		EmailTemplate emailTemplete = EmailTemplate.instance().setCreateTime(Time.getDateTime())
				.setEmailTemplateContent(request.getParameter("email_template_content"))
				.setEmailTemplateTitle(request.getParameter("email_template_title"))
				.setEmailTemplateType(Integer.parseInt(request.getParameter("email_template_type")))
				.setCreateTime(Time.getDateTime())
				.setUpdateTime(Time.getDateTime()).end();
		return emailModel.table("email_template").add(emailTemplete.getFields(), emailTemplete.getData());
	}
	
	/**
	 * 更新邮箱基本信息
	 * @param request
	 * @return
	 * @throws SQLException 
	 */
	public static int editEmailInfo(HttpServletRequest request) throws SQLException {
		EmailModel emailModel = new EmailModel();
		EmailInfo emailInfoData = EmailInfo.instance().setAuth(Integer.parseInt(request.getParameter("auth")))
							.setEmailInfoId(Integer.parseInt(request.getParameter("email_info_id")))
							.setFromEmail(request.getParameter("from_email"))
							.setFromEmailPassword(request.getParameter("from_email_password"))
							.setFromEmailUserName(request.getParameter("from_email_user_name"))
							.setHost(request.getParameter("host"))
							.setPort(Integer.parseInt(request.getParameter("port")))
							.setProtocol(request.getParameter("protocol"))
							.setTimeout(Integer.parseInt(request.getParameter("timeout")))
							.setUpdateTime(Time.getDateTime()).end();
		EmailInfo emailInfoCondition = EmailInfo.instance().
				setEmailInfoId(Integer.parseInt(request.getParameter("email_info_id"))).end();
		return emailModel.table("email_info").where(emailInfoCondition.getCondition()).update(emailInfoData.updateData());						
	}

	/**
	 * 得到邮件基本信息
	 * @return
	 * @throws SQLException
	 */
	public static EmailInfo getEmailInfo() throws SQLException {
		EmailModel emailModel = new EmailModel();
		ResultSet emailInfo = emailModel.table("email_info").select();
		emailInfo.next();
		return EmailInfo.instance().setEmailInfoId(emailInfo.getInt(1))
				.setFromEmail(emailInfo.getString(2))
				.setFromEmailUserName(emailInfo.getString(3))
				.setFromEmailPassword(emailInfo.getString(4))
				.setHost(emailInfo.getString(5))
				.setProtocol(emailInfo.getString(6))
				.setTimeout(emailInfo.getInt(7))
				.setAuth(emailInfo.getInt(8))
				.setUpdateTime(emailInfo.getInt(9))
				.setPort(emailInfo.getInt(10));
		
	}
}
