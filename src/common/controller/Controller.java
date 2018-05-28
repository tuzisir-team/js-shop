package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller {
	public static HttpServletRequest request;
	public static HttpServletResponse response;
	public static String test;
	
	public static void setRequest(HttpServletRequest request) {
		Controller.request = request;
	}
	
	public static void setResponse(HttpServletResponse response) {
		Controller.response = response;
	}
	
	
}
