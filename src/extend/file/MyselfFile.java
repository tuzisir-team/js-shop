package extend.file;

import java.io.File;
import java.io.Writer;

import org.apache.commons.fileupload.FileItem;

import extend.log.Log;

public class MyselfFile {

	private FileItem fileItem;

	private Boolean isWriter = true; // 是否创建文件

	private String filePath; // 文件路径

	private String fileName; // 文件名称

	/**
	 * 无参构造方法
	 * 
	 * @param fileItem
	 */
	public MyselfFile(FileItem fileItem) {
		this.fileItem = fileItem;
	}

	/**
	 * 为了写代码好看
	 * 
	 * @param fileItem
	 *            文件对象
	 * @return
	 */
	public static MyselfFile instance(FileItem fileItem) {
		return new MyselfFile(fileItem);
	}

	/**
	 * 设置文件路径
	 * 
	 * @param filePath
	 * @return
	 */
	public MyselfFile setFilePath(String filePath) {
		this.filePath = filePath;
		return this;
	}

	/**
	 * 设置文件名称
	 * 
	 * @param fileName
	 * @return
	 */
	public MyselfFile setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	/**
	 * 验证文件是否为图片
	 * 
	 * @param fileItem
	 * @return
	 */
	public MyselfFile checkImage() {
		String contentType = this.fileItem.getContentType();
		if (!contentType.startsWith("image/")) {
			this.isWriter = false;
			Log.instance().error("不是图片类型");
		}
		return this;
	}

	/**
	 * 创建文件
	 * 
	 * @return
	 * @throws Exception 
	 */
	public Boolean createFile() throws Exception {
		if (!this.isWriter) return false;
		File file = new File(this.filePath, this.fileName);
		this.fileItem.write(file);
		return true;
	}
	
	/**
	 * 获取此文件是否可以创建
	 * @return
	 */
	public Boolean getIsWriter() {
		return this.isWriter;
	}
	
	/**
	 * 删除文件
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static Boolean delFile(String path, String fileName) {
		Log.instance().debug(fileName);
		File file=new File(path+"/"+fileName);
        if(file.exists()&&file.isFile()) {
            file.delete();return true;
        }
        return false;
	}
	

}
