package extend.time;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
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
	
	//获得本周一0点时间 
	public static int getWeekStartTime(){ 
		Calendar cal = Calendar.getInstance(); 
		cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0); 
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
		return (int) ((cal.getTime().getTime() - (7 * 24 * 60 * 60 * 1000))/1000); 
	} 
	
	/**
	 * 获取周的某一天开始的时间戳
	 * @param day
	 * @return
	 */
	public static int getWeekDayStartTime(int day) {
		return getWeekStartTime() + (day-1)*86400;
	}
	
	/**
	 * 获取周的某一天结束的时间戳
	 * @param day
	 * @return
	 */
	public static int getWeekDayEndTime(int day) {
		return getWeekStartTime() + day*86400;
	}
	
	//获得本周日24点时间 
	public static int getWeekEndTime(){ 
		Calendar cal = Calendar.getInstance(); 
		cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0); 
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
		return (int) (cal.getTimeInMillis()/1000);
	} 
	
	/* 
     * 将时间转换为时间戳
     */    
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    
    //获得当天0点时间 
    public static int getTimesmorning(){ 
    Calendar cal = Calendar.getInstance(); 
    cal.set(Calendar.HOUR_OF_DAY, 0); 
    cal.set(Calendar.SECOND, 0); 
    cal.set(Calendar.MINUTE, 0); 
    cal.set(Calendar.MILLISECOND, 0); 
    return (int) (cal.getTimeInMillis()/1000); 
    } 
    
    //获得当天24点时间 
    public static int getTimesnight(){ 
	    Calendar cal = Calendar.getInstance(); 
	    cal.set(Calendar.HOUR_OF_DAY, 24); 
	    cal.set(Calendar.SECOND, 0); 
	    cal.set(Calendar.MINUTE, 0); 
	    cal.set(Calendar.MILLISECOND, 0); 
	    return (int) (cal.getTimeInMillis()/1000); 
    } 
    
    //获得本月第一天0点时间 
    public static int getTimesMonthmorning(){ 
	    Calendar cal = Calendar.getInstance(); 
	    cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0); 
	    cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH)); 
	    return (int) (cal.getTimeInMillis()/1000); 
    } 
    
    //获得本月最后一天24点时间 
    public static int getTimesMonthnight(){ 
	    Calendar cal = Calendar.getInstance(); 
	    cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0); 
	    cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
	    cal.set(Calendar.HOUR_OF_DAY, 24); 
	    return (int) (cal.getTimeInMillis()/1000); 
    }
}
