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
    
    private int state;
    
    private Integer equipCategoryId;
    

	public Integer getEquipCategoryId() {
		return equipCategoryId;
	}

	public void setEquipCategoryId(Integer equipCategoryId) {
		this.equipCategoryId = equipCategoryId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

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
