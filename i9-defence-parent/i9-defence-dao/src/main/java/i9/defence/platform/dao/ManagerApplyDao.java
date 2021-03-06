package i9.defence.platform.dao;

import i9.defence.platform.dao.vo.ApplyRefuseDto;
import i9.defence.platform.dao.vo.ManagerApplyDto;
import i9.defence.platform.model.ManagerApply;
import i9.defence.platform.utils.PageBounds;

import java.util.List;

/** 
 * 创建时间：2018年1月4日 上午9:47:35
 * @author  lby
 * @version  
 * 
 */
public interface ManagerApplyDao {
    /**
     * 添加账户
     * @param ManagerApply
     * @throws Exception
     */
    void addManagerApply(ManagerApply managerApply) throws Exception;
    
    /**
     * 更新账户
     * @param ManagerApply
     * @throws Exception
     */
    void updateManagerApply(ManagerApply managerApply) throws Exception;
    
    /**
     * 删除账户
     * @param kid
     * @throws Exception
     */
    void deleteManagerApply(List<Integer> ids) throws Exception;
    
    /**
     * 根据ID获取账户
     * @param kid
     * @return
     * @throws Exception
     */
    ManagerApply getManagerApplyById(int id) throws Exception;
    
    /**
     * 分页查询账户
     * @param managerApplyExample
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<ManagerApply> selectByLimitPage(ManagerApplyDto managerApplyDto,
            int currectPage, int pageSize) throws Exception;
    
    /**
     * 通过用户名查找未被拒绝的用户申请
     * @param username
     * @return
     */
    ManagerApply getUnRefusedManagerApplyByUsername(String username) throws Exception;
    
    /**
     * 通过id查询申请
     * @param ids
     * @return
     * @throws Exception
     */
    List<ManagerApply> selectApplysByIds(List<Integer> ids) throws Exception;
    
    /**
     * 同意账户申请后批量更新申请
     * @param managerApplys
     */
    void updateBatchManagerApplys(List<ManagerApply> managerApplys,Integer managerId) throws Exception;
    
    /**
     * 拒绝申请
     * @param applyRefuseDto
     * @param managerId
     */
    void refuseManagerApply(ApplyRefuseDto applyRefuseDto, Integer managerId);
}
