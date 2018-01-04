package i9.defence.platform.dao.vo;
/** 
 * 创建时间：2018年1月4日 上午10:14:06
 * @author  lby
 * @version  
 * 
 */
public class ManagerSearchDto {
    
    private String startTimeString;
    
    private String endTimeString;
    
    private String orderByClause;

    public String getStartTimeString() {
        return startTimeString;
    }

    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

    public String getEndTimeString() {
        return endTimeString;
    }

    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
    
    
}
