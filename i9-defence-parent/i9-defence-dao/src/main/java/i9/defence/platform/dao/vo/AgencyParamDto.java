package i9.defence.platform.dao.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 姜哲 on 2018/1/24--14:55
 *            经销商 分配参数类
 */
public class AgencyParamDto implements Serializable{

    /**
     * <code>serialVersionUID</code> - 
     */
    private static final long serialVersionUID = 6284929290549655304L;

    private List<Integer> managerIdS;

    private Integer parentId;

    private Integer managerId;
    
    //备用ID
    private Integer spareId;

    public List<Integer> getManagerIdS() {
        return managerIdS;
    }

    public void setManagerIdS(List<Integer> managerIdS) {
        this.managerIdS = managerIdS;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

	public Integer getSpareId() {
		return spareId;
	}

	public void setSpareId(Integer spareId) {
		this.spareId = spareId;
	}
    
    
}
