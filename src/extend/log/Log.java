package extend.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import config.GetConfig;
  
/**
 * @author tuzisir
 *
 */
public class Log {
	
    // 该类的唯一的实例
    private static Log logWriter;
    // 文件输出流
    private PrintWriter writer;
    // 日志文件名
    private String logFileName;
 
    /**
     * 默认构造函数
     */
    private Log() {
        this.init();
    }
 
    /**
     * 存放位置
     * @param fileName
     */
    private Log(String... fileName) {
    	if (fileName.length == 1) {
    		this.logFileName = fileName[0]; // 自定义日志存放目录
    	} else if (GetConfig.instance(GetConfig.CONFIG).getStringConfig("logPath") != null){
    		this.logFileName = GetConfig.instance(GetConfig.CONFIG).getStringConfig("logPath"); // 使用配置文件中的日志目录
    	} else {
    		this.logFileName = this.getClass().getClassLoader().getResource("").getPath() + 
    				"../../runtime/log/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString() + ".log"; // 默认存放目录
    	}
        this.init();
    }
 
    /**
     * 单例模式返回日志对象
     * @param logFileName
     * @return
     */
    public synchronized static Log instance(String... logFileName) {
        if (logWriter == null) {
            logWriter = new Log(logFileName);
        }
        return logWriter;
    }
    
    public synchronized void notice(String logMsg) {
        this.logInfo("NOTICE", logMsg);
	}
 
    /**
     * 往日志文件中写一条日志信息 为了防止多线程同时操作(写)日志文件，造成文件”死锁”。使用synchronized关键字
     * @param logMsg
     * 日志消息
     */
    public synchronized void error(String logMsg) {
          this.logInfo("ERROR", logMsg);
	}
    
    /**
     * 调试模式
     * @param logMsg
     */
    public synchronized void debug(String logMsg) {
        this.logInfo("DEBUG", logMsg);
    }
    
    /**
     * 日志信息
     * @param logType
     * @param logMsg
     */
    protected void logInfo(String logType, String logMsg) {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	this.writer.println("---------------------------------------tuzisir日志服务(" + df.format(new Date()) + ")---------------------------------------------\n" + 
        		"日志类型: " + logType + "\n" + 
        		"日志数据: " + logMsg + "\n" +
        		"调用过程: " + this.programProcess(5)
        		);
    	this.close();
    }
    
    /**
     * 返回程序的执行过程
     * @return
     */
    protected String programProcess(int depth) {
    	String programProcess = "";
    	depth += 5;
    	// 该方法返回一个映射，从线程到StackTraceElement数组，表示相应的线程的堆栈跟踪。
		java.util.Map<Thread, StackTraceElement[]> ts = Thread.getAllStackTraces(); 
		StackTraceElement[] ste = ts.get(Thread.currentThread()); // Thread.currentThread()可以获取当前线程的引用
		for (int i=5; i<depth; i++) {
			programProcess += "[ " + (i-4) + " ]" + ste[i].toString() + "\n"; 
		}
		return programProcess;
    }
 
    /**
     * 初始化LogWriter
     *
     * @throws LogException
     */
    private void init() {
        // 如果用户没有在参数中指定日志文件名，则从配置文件中获取。
        File logFile = new File(this.logFileName);
        File fileParent = logFile.getParentFile();  
        if(!fileParent.exists()){  
            fileParent.mkdirs();
        }  
        try {
            logFile.createNewFile();
            // 其中的FileWriter()中的第二个参数的含义是:是否在文件中追加内容
            // PrintWriter()中的第二个参数的含义是：自动将数据flush到文件中
            writer = new PrintWriter(new OutputStreamWriter(  
                    new FileOutputStream(logFile, true),  
                    "UTF-8"));
            System.out.println("日志文件的位置：" + logFile.getAbsolutePath());
        } catch (IOException ex) {
            String errmsg = "无法打开日志文件:" + logFile.getAbsolutePath();
             System.out.println(errmsg);
        }
    }
 
    // 关闭LogWriter
    public void close() {
        logWriter = null;
        if (writer != null) {
            writer.close();
        }
    }
  
}