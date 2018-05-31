package admin.controller;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;

import admin.model.LoginModel;
import common.controller.Controller;
import common.db.model.Admin;
import extend.log.Log;
import extend.myjson.MyJson;
import extend.vertifcode.VertifCode;

public class LoginController extends Controller{
	
	/**
	 * 登录
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public void login() throws IOException, SQLException {
		String checkResult = checkParams("admin_name,admin_password,captcha,post_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 验证码验证
		if (!this.checkCode()) {
			getOut().println(returnJson(445, "验证码错误"));return;
		}
		// 登录逻辑处理
		if (!LoginModel.instance(Admin.instantce().setAdminName(request.getParameter("admin_name"))
				.setAdminPassword(request.getParameter("admin_password"))).login()) {
			getOut().println(returnJson(400, "账号或密码错误"));return;
		} else {
			// 存储session
	        request.getSession().setAttribute("admin_name", request.getParameter("admin_name"));
			getOut().println(returnJson(200, "登录成功"));
		}
		return;
	}
	
	/**
	 * 退出登录
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void adminUnlogin() throws IOException, ServletException {
		String checkResult = checkParams("get_type");
		// 验证参数
		if (checkResult != null) {
			getOut().println(returnJson(444, "缺少必要参数"+checkResult));return;
		}
		// 销毁session 
		request.getSession().invalidate();
		// 跳转到登录页面
		forward("/view/admin/login/login.jsp");
	}
	
	/**
	 * 验证码验证
	 * @return
	 */
	private Boolean checkCode() {
		String clientCheckcode = request.getParameter("captcha");//接收客户端浏览器提交上来的验证码
        String serverCheckcode = (String) request.getSession().getAttribute("checkcode");//从服务器端的session中取出验证码
        if (clientCheckcode == null || serverCheckcode == null) {
        	Log.instance().error("验证码为空");
        	return false;
        }
        if (clientCheckcode.toUpperCase().equals(serverCheckcode)) {//将客户端验证码和服务器端验证比较，如果相等，则表示验证通过
        	return true;
        }
    	Log.instance().error("没有通过验证码");
    	return false;
	}
	
	/**
	 * 获取验证码
	 * @throws IOException
	 */
	public void getVertifCode() throws IOException {
        String createTypeFlag = request.getParameter("createTypeFlag");//接收客户端传递的createTypeFlag标识
        VertifCode vertifCode = new VertifCode(createTypeFlag);
        //7.将随机数存在session中
        request.getSession().setAttribute("checkcode", vertifCode.getRandom());
        //8.设置响应头通知浏览器以图片的形式打开
        response.setContentType("image/jpeg");//等同于response.setHeader("Content-Type", "image/jpeg");
        //9.设置响应头控制浏览器不要缓存
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        //10.将图片写给浏览器
        ImageIO.write(vertifCode.getImg(), "jpg", response.getOutputStream());
	}
	
}
