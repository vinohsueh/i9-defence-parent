package i9.defence.platform.dao.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 姜哲 on 2018/1/24--14:55
 *            经销商 分配参数类
 */
public class AgencyParamDto implements Serializable{

    private List<Integer> managerIdS;

    private Integer parentId;

    private Integer managerId;

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
}
