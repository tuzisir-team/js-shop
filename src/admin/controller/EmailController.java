package admin.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.hibernate.validator.constraints.Email;

import admin.model.EmailModel;
import admin.model.GoodsModel;
import common.controller.Controller;
import extend.email.EmailFactory;
import extend.email.SendEmail;
import extend.page.Page;

public class EmailController extends Controller{
	
	/**
	 * 根据用户id查找所有已发送邮件
	 * @throws IOException
	 * @throws SQLException
	 * @throws ServletException
	 */
	public void findEmail() throws IOException, SQLException, ServletException {
		String checkResult = checkParams("post_type,user_id");
		String strcurpage = request.getParameter("currentpage"); // 接收当前页面值
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		request.setAttribute("isPage", 0);
		// 查找所有已发送邮件
		ArrayList emailsList = EmailModel.findEmail(Integer.parseInt(request.getParameter("user_id")));
		if (emailsList.isEmpty()) {
			forward("./view/admin/email/email_list.jsp");return;
		}
		Page viewPage = Page.createPage(emailsList, strcurpage).setRoute("admin_email_list"); // 获取请求页面对象
		request.setAttribute("viewpage", viewPage);
		forward("./view/admin/email/email_list.jsp");
		
	}
	
	/**
	 * 发送邮件列表
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ServletException 
	 */
	public void emailList() throws IOException, SQLException, ServletException {
		String checkResult = checkParams("get_type");
		String strcurpage = request.getParameter("currentpage"); // 接收当前页面值
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 查找所有已发送邮件
		ArrayList emailsList = EmailModel.emailList();
		if (emailsList.isEmpty()) {
			forward("./view/admin/email/email_list.jsp");return;
		}
		Page viewPage = Page.createPage(emailsList, strcurpage).setRoute("admin_email_list"); // 获取请求页面对象
		request.setAttribute("viewpage", viewPage);		
		forward("./view/admin/email/email_list.jsp");
	}
	
	/**
	 * 删除邮件模板
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public void delEmailTemplate() throws IOException, NumberFormatException, SQLException {
		String checkResult = checkParams("post_type,email_template_id");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		switch(EmailModel.delEmailTemplate(Integer.parseInt(request.getParameter("email_template_id")))) {
			case 1:
				getOut().println(returnJson(200, "删除邮件模板成功"));
				break;
			default:
				getOut().println(returnJson(400, "删除邮件模板失败"));
		}
	}
	
	/**
	 * 改变邮件模板状态，每种类型只能有一种模板是开启的
	 * @throws IOException
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public void chagneEmailTemplateStatus() throws IOException, NumberFormatException, SQLException {
		String checkResult = checkParams("post_type,email_template_status,email_template_id,email_template_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 改变状态
		if (EmailModel.chagneEmailTemplateStatus(request) == 1){
			getOut().println(returnJson(200, "修改状态成功"));return;
		}
		getOut().println(returnJson(400, "修改状态失败"));return;
		
	}
	
	/**
	 * 修改邮件模板
	 * @throws IOException
	 * @throws SQLException
	 */
	public void editEmailTemplate() throws IOException, SQLException {
		String checkResult = checkParams("post_type,email_template_type,email_template_title,email_template_content,email_template_id");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		if (EmailModel.editEmailTemplate(request) == 1) {
			getOut().println(returnJson(200, "修改邮件模板成功,正在跳转..."));return;
		}
		getOut().println(returnJson(400, "修改邮件模板失败"));return;
	}
	
	/**
	 * 修改邮件模板信息页面
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void editEmailTemplateView() throws SQLException, ServletException, IOException {
		request.setAttribute("emailTemplateInfo", EmailModel.findEmailTemplate(request));
		forward("./view/admin/email/edit_email_template_view.jsp");
	}
	
	/**
	 * 添加邮件模板
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public void addEmailTemplate() throws IOException, SQLException {
		String checkResult = checkParams("post_type,email_template_type,email_template_title,email_template_content");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		if (EmailModel.addEmailTemplate(request) == 1) {
			getOut().println(returnJson(200, "添加邮箱模板成功"));return;				
		}
		getOut().println(returnJson(400, "添加邮箱模板失败"));return;			
	}
	
	/**
	 * 邮件模板列表
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public void emailTemplateList() throws ServletException, IOException, SQLException {
		String strcurpage = request.getParameter("currentpage"); // 接收当前页面值
		ArrayList emailTemplateList = EmailModel.emailTemplateList(request);
		if (emailTemplateList.isEmpty()) {
			forward("/view/admin/email/email_template_list.jsp");return;
		}
		Page viewPage = Page.createPage(emailTemplateList, strcurpage).setRoute("admin_email_template_list"); // 获取请求页面对象
		request.setAttribute("viewpage", viewPage);		
		forward("./view/admin/email/email_template_list.jsp");
	}
	
	/**
	 * 修改邮件基本信息
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public void editEmailInfo() throws IOException, SQLException {
		String checkResult = checkParams(""
				+ "email_info_id,"
				+ "from_email," 
				+ "from_email_user_name,"
				+ "from_email_password,"
				+ "host,"
				+ "protocol,"
				+ "port,"
				+ "timeout,"
				+ "auth"
		);
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 更新信息
		if (EmailModel.editEmailInfo(request) == 1) {
			getOut().println(returnJson(200, "更新邮箱基本信息成功"));return;			
		}
		getOut().println(returnJson(400, "更新有下行基本信息失败"));return;			
	}
	
	/**
	 * 设置email的基本信息
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws SQLException 
	 */
	public void setEmail() throws ServletException, IOException, SQLException {
		request.setAttribute("emailInfo", EmailModel.getEmailInfo());
		forward("./view/admin/email/set_email.jsp");
	}
	
	public void sendEmail() throws SQLException {
		EmailFactory.instance().send(1,EmailFactory.REGISTER);
	}
}
