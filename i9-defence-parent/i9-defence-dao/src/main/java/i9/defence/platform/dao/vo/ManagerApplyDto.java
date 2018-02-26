package i9.defence.platform.dao.vo;


/** 
 * 创建时间：2018年1月24日 下午4:28:04
 * @author  lby
 * @version  
 * 
 */
public class ManagerApplyDto extends PageListDto{
    
    /**
     * 账户类型
     */
    private Byte type;
    
    /**
     * 经销商
     */
    private Integer distributorId;
    
    private String orderByClause;
    
    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Integer distributorId) {
        this.distributorId = distributorId;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
    
}
