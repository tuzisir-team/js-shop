<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
  if(session.getAttribute("admin_name") == null)
  {  
      out.println("<script>window.location.href='../login/login.jsp'</script>");
      return;
  }  
%>
