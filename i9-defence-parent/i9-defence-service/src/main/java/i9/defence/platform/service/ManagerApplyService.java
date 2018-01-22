package i9.defence.platform.service;

import i9.defence.platform.dao.vo.ApplyRefuseDto;
import i9.defence.platform.model.ManagerApply;
import i9.defence.platform.model.ManagerApplyExample;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

import java.util.List;

/** 
 * 创建时间：2018年1月4日 上午9:47:35
 * @author  lby
 * @version  
 * 
 */
public interface ManagerApplyService {
    /**
     * 添加账户
     * @param ManagerApply
     * @throws BusinessException
     */
    void addManagerApply(ManagerApply managerApply) throws BusinessException;
    
    /**
     * 更新账户
     * @param ManagerApply
     * @throws BusinessException
     */
    void updateManagerApply(ManagerApply managerApply) throws BusinessException;
    
    /**
     * 删除账户
     * @param kid
     * @throws BusinessException
     */
    void deleteManagerApply(List<Integer> ids) throws BusinessException;
    
    /**
     * 根据ID获取账户
     * @param kid
     * @return
     * @throws BusinessException
     */
    ManagerApply getManagerApplyById(int id) throws BusinessException;
    
    /**
     * 分页查询账户
     * @param managerApplyExample
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<ManagerApply> selectByLimitPage(ManagerApplyExample managerApplyExample,
            int currectPage, int pageSize) throws BusinessException;
    
    /**
     * 同意申请
     * @param ids
     */
    void agreeManagerApply(List<Integer> ids,Integer managerId) throws BusinessException;
    
    /**
     * 拒绝申请
     * @param ids
     * @throws BusinessException
     */
    void refuseManagerApply(ApplyRefuseDto applyRefuseDto,Integer managerId) throws BusinessException;
    
}
