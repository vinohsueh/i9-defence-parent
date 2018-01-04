package i9.defence.platform.dao.vo;
/** 
 * 创建时间：2017年12月26日 上午10:41:18
 * @author  lby
 * @version  
 * 
 */
public class PageListDto {
    private int pageSize;
    
    private int currentPage;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    
    
}
