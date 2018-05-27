package extend.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
  
/**
 * @author magic282
 *
 */
public class Log {
	// 日志的配置文件
    public static final String LOG_CONFIGFILE_NAME = "log.properties";
    // 日志文件名在配置文件中的标签
    public static final String LOGFILE_TAG_NAME = "logfile";
 
    // 默认的日志文件的路径和文件名称
    private final String DEFAULT_LOG_FILE_NAME = "./logtext.log";
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
 
    private Log(String fileName) {
        this.logFileName = fileName;
        this.init();
    }
 
    /**
     * 单例模式返回日志对象
     * @param logFileName
     * @return
     */
    public synchronized static Log getLogWriter(String logFileName) {
        if (logWriter == null) {
            logWriter = new Log(logFileName);
        }
        return logWriter;
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
		java.util.Map<Thread, StackTraceElement[]> ts = Thread.getAllStackTraces();
		StackTraceElement[] ste = ts.get(Thread.currentThread());  
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
        if (this.logFileName == null) {
            this.logFileName = this.getLogFileNameFromConfigFile();
            // 如果配置文件不存在或者也没有指定日志文件名，则用默认的日志文件名。
            if (this.logFileName == null) {
                this.logFileName = DEFAULT_LOG_FILE_NAME;
            }
        }
        File logFile = new File(this.logFileName);
        File fileParent = logFile.getParentFile();  
        if(!fileParent.exists()){  
            fileParent.mkdirs();
        }  
        try {
            logFile.createNewFile();
            // 其中的FileWriter()中的第二个参数的含义是:是否在文件中追加内容
            // PrintWriter()中的第二个参数的含义是：自动将数据flush到文件中
            writer = new PrintWriter(new FileWriter(logFile, true), true);
            System.out.println("日志文件的位置：" + logFile.getAbsolutePath());
        } catch (IOException ex) {
            String errmsg = "无法打开日志文件:" + logFile.getAbsolutePath();
             System.out.println(errmsg);
        }
    }
 
    /**
     * 从配置文件中取日志文件名
     *
     * @return
     */
    private String getLogFileNameFromConfigFile() {
        try {
            Properties pro = new Properties();
            // 在类的当前位置,查找属性配置文件log.properties
            InputStream fin = getClass().getResourceAsStream(
                    LOG_CONFIGFILE_NAME);
            if (fin != null) {
                pro.load(fin);// 载入配置文件
                fin.close();
                return pro.getProperty(LOGFILE_TAG_NAME);
            } else {
                System.err.println("无法打开属性配置文件: log.properties");
            }
        } catch (IOException ex) {
            System.err.println("无法打开属性配置文件: log.properties");
        }
        return null;
    }
 
    // 关闭LogWriter
    public void close() {
        logWriter = null;
        if (writer != null) {
            writer.close();
        }
    }
  
}