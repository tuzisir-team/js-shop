package extend.time;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Time {
	
	/**
	 * 获取当前时间戳10位
	 * @return
	 */
	public static int getDateTime() {
		return (int) (new Date().getTime()/1000);
	}
	
	/* 
	* 将10 or 13 位时间戳转为时间字符串 
	* convert the number 1407449951 1407499055617 to date/time format timestamp 
	*/
	public static String timestampToDate(String str_num,String format ) {  
	    SimpleDateFormat sdf = new SimpleDateFormat(format);  
	    if (str_num.length() == 13) {  
	        String date = sdf.format(new Date(Long.parseLong(str_num)));  
	        return date;  
	    } else {  
	        String date = sdf.format(new Date(Integer.parseInt(str_num) * 1000L));  
	        return date;  
	    }  
	}
}
