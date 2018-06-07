package extend.page;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.GetConfig;
//每一页的模型
public class Page {
    //已知数据
    private int currentPage;//当前页（通过前台获取）
    private int pageSize;//每页显示的数据条数(自己设定)
    private int totalRecord;//数据库查询结果条数
    private List list = new ArrayList();//存放当前要展示的数据的容器大小为pageSize
    
    //计算得出
    private int totalPage;//总页数totalRecord/pageSize;
    private int startIndex;//每页第一条数据的索引
    private int endIndex;//每页最后一条数据的索引
    private int start;//可点击页的开始页
    private int end;//可点击页的最后一个
    private String route;
    
 // 构建Page模型
    public static Page createPage(ArrayList userList, String strcurpage) throws SQLException{
    	int currentPage;//通常第一次请求currentpage值为空这时设置默认值为1
        if(strcurpage!=null) {
            currentPage = Integer.parseInt(strcurpage);
        }else{ 
                currentPage = 1;
        }
        int pageSize = GetConfig.instance(GetConfig.COMMON).getIntConfig("pageSize"); //页面大小
        // 构建page模型
        int totalRecord = userList.size();
        Page pg = new Page(currentPage,pageSize,totalRecord); // 构建page对象
        ArrayList currentList = new ArrayList(); // 每页显现的那九条数据的容器
        int startIndex = pg.getStartIndex(); // 每一页的起始索引
        int endIndex = pg.getEndIndex(); // 每一页的终止索引
        // 根据每页的起始数据和最后一条数据的索引选出那九条数据并复制到currentlist
        currentList.addAll(userList.subList(startIndex, endIndex+1));
        pg.setList(currentList);// 将currentlist设置到page对象的容器
        return pg;
    }
    
    //在构造函数中计算出属性值
    public Page(int currentPage, int pageSize, int totalRecord){
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        
        //求总页数
        if(totalRecord%pageSize == 0){//可以整除情况
            this.totalPage = totalRecord/pageSize;
        }else{//不能整除有余数
            this.totalPage = totalRecord/pageSize + 1;
        }
        
        //计算每页的开始数据和结束数据的索引（需要根据当前页来确定）
        this.startIndex = (currentPage-1) * pageSize;//每页的第一条数据
        if(currentPage == totalPage){//如果当前页是最后一页
            this.endIndex = totalRecord - 1;
        }else{//当前页不是最后一页
            this.endIndex = startIndex + pageSize - 1;
        }
        //选项卡的起始页和最后一页默认值为1-5
        this.start = 1;
        this.end = 5;
        if(totalPage <= 5){//总页数小于等于5
            this.end = totalPage;
        }else{//如果总页数大于5根据当前页数来计算start和end
            this.start = currentPage - 2;
            this.end = currentPage + 2;
            if(this.start <= 0){
                this.start = 1;
                this.end  = 5;
            }
            if(this.end > totalPage){
                this.end = totalPage;
                this.start = end - 4;
            }
        }
    }
    //下面是geter和seter方法
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
    
    public Page setRoute(String route) {
    	this.route = route;
    	return this;
    }
    
    public String getRoute() {
    	return this.route;
    }
    
}
