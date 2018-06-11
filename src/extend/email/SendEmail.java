package extend.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import common.db.model.EmailInfo;

public class SendEmail extends Thread{

    // 发件邮箱
    private String from;
    // 发件邮箱的用户名
    private String username;
    // 发件邮箱的密码
    private String password;
    // 发送邮件的服务器
    private String host;
    // 设置协议
    private String protocol;
    // 是否认证
    private String auth;
    // 设置端口
    private String port;
    // 设置超时时间
    private String timeout;
    
    // 设置发送的邮件标题
    private String emailTitle;
    
    // 设置发送的邮件内容
    private String emailContent;
    
    // 要发送给谁的
    private String sendEmail;
    
    // 设置要发送的人的邮箱
    public SendEmail setSendEmail(String sendEmail) {
    	this.sendEmail = sendEmail;
    	return this;
    }
    
    public SendEmail setEmailTitle(String emailTitle) {
    	this.emailTitle = emailTitle;
    	return this;
    }
    
    public SendEmail setEmailContent(String emailContent) {
    	this.emailContent = emailContent;
    	return this;
    } 
    
    // 初始化
    public SendEmail(EmailInfo emailInfo){
    	this.from = emailInfo.getFromEmail();
    	this.username = emailInfo.getFromEmailUserName();
    	this.password = emailInfo.getFromEmailPassword();
    	this.host = emailInfo.getHost();
    	this.protocol = emailInfo.getProtocol();
    	if (emailInfo.getAuth() == 1) {
        	this.auth = "true";
    	} else {
    		this.auth = "false";
    	}
    	this.port = Integer.toString(emailInfo.getPort());
    	this.timeout = Integer.toString(emailInfo.getTimeout());
    }
    /* 重写run方法的实现，在run方法中发送邮件给指定的用户
     * @see java.lang.Thread#run()
     */
    // @Override
    public void run() {
        try{
            // 1.创建Session
            // 参数
            Properties prop = new Properties();
            // 设置 服务器
            prop.setProperty("mail.host", host);
            // 设置 协议
            prop.setProperty("mail.transport.protocol", protocol);
            // 设置 是否认证
            prop.setProperty("mail.smtp.auth", auth);
            // 设置 端口
            prop.setProperty("mail.smtp.port", port);
            // 设置 超时时间
            prop.setProperty("mail.smtp.timeout",timeout);
            // 设置 开启tls
            prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.setProperty("mail.smtp.socketFactory.port", port);
  
            Session session = Session.getInstance(prop);
            session.setDebug(true);
            // 2.创建邮件
            Message message = createEmail(session);
            // 3.连接邮箱服务器并登录邮箱 ，发送邮件，关闭连接
            Transport ts = session.getTransport();
            ts.connect(host, username, password);
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
    * @Method: createEmail
    * @Description: 创建要发送的邮件
    * 
    *
    * @param session
    * @param user
    * @return
    * @throws Exception
    */ 
    public Message createEmail(Session session) throws Exception{
        // 创建邮件
        MimeMessage message = new MimeMessage(session);
        // 设置 发件邮箱
        message.setFrom(new InternetAddress(from));
        // 设置 收件邮箱
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(this.sendEmail));
        // 设置 邮件主题
        message.setSubject(this.emailTitle);
        // 设置 邮件内容
        String info = this.emailContent;
        message.setContent(info, "text/html;charset=UTF-8");
        // 保存
        message.saveChanges();
        /*
        带图片

                // 文本
                MimeBodyPart text = new MimeBodyPart();
                text.setContent("这是一封邮件正文带图片<img src=''>的邮件", "text/html;charset=UTF-8");
                // 图片
                MimeBodyPart image = new MimeBodyPart();
                DataHandler dh = new DataHandler(new FileDataSource("图片路径"));
                image.setDataHandler(dh);
                image.setContentID("xxx.jpg");
                // 描述数据关系
                MimeMultipart mm = new MimeMultipart();
                mm.addBodyPart(text);
                mm.addBodyPart(image);
                mm.setSubType("related");

                message.setContent(mm);
                message.saveChanges();
                // 保存邮件到本地
                message.writeTo(new FileOutputStream("保存路径"));
        */

        /*
        带附件

               MimeBodyPart text = new MimeBodyPart();
                text.setContent("使用JavaMail创建的带附件的邮件", "text/html;charset=UTF-8");
                
                // 创建邮件附件
                MimeBodyPart attach = new MimeBodyPart();
                DataHandler dh = new DataHandler(new FileDataSource("src\\2.jpg"));
                attach.setDataHandler(dh);
                attach.setFileName(dh.getName());  //
                
                // 创建容器描述数据关系
                MimeMultipart mp = new MimeMultipart();
                mp.addBodyPart(text);
                mp.addBodyPart(attach);
                mp.setSubType("mixed");
                
                message.setContent(mp);
                message.saveChanges();
        */

        /*
        图片+附件

                //正文
                MimeBodyPart text = new MimeBodyPart();
                text.setContent("xxx这是女的xxxx<br/><img src='cid:aaa.jpg'>","text/html;charset=UTF-8");
                
                //图片
                MimeBodyPart image = new MimeBodyPart();
                image.setDataHandler(new DataHandler(new FileDataSource("src\\3.jpg")));
                image.setContentID("aaa.jpg");
                
                //附件1
                MimeBodyPart attach = new MimeBodyPart();
                DataHandler dh = new DataHandler(new FileDataSource("src\\4.zip"));
                attach.setDataHandler(dh);
                attach.setFileName(dh.getName());
                
                //附件2
                MimeBodyPart attach2 = new MimeBodyPart();
                DataHandler dh2 = new DataHandler(new FileDataSource("src\\波子.zip"));
                attach2.setDataHandler(dh2);
                attach2.setFileName(MimeUtility.encodeText(dh2.getName()));
                
                //描述关系:正文和图片
                MimeMultipart mp1 = new MimeMultipart();
                mp1.addBodyPart(text);
                mp1.addBodyPart(image);
                mp1.setSubType("related");
                
                //描述关系:正文和附件
                MimeMultipart mp2 = new MimeMultipart();
                mp2.addBodyPart(attach);
                mp2.addBodyPart(attach2);
                
                //代表正文的bodypart
                MimeBodyPart content = new MimeBodyPart();
                content.setContent(mp1);
                mp2.addBodyPart(content);
                mp2.setSubType("mixed");
                
                message.setContent(mp2);
                message.saveChanges();
        */
        return message;
    }
}
