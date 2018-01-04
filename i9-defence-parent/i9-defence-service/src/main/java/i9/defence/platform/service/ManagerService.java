package i9.defence.platform.service;

import i9.defence.platform.dao.vo.ManagerLoginDto;
import i9.defence.platform.dao.vo.ManagerSearchDto;
import i9.defence.platform.model.Manager;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

import java.util.List;

/** 
 * 创建时间：2018年1月4日 上午10:47:27
 * @author  lby
 * @version  
 * 
 */
public interface ManagerService {
    /**
     * 添加管理员
     * @param manager
     * @throws BusinessException
     */
    void addManager(Manager manager) throws BusinessException;
    
    /**
     * 更新管理员
     * @param manager
     * @throws BusinessException
     */
    void updateManager(Manager manager) throws BusinessException;
    
    /**
     * 删除管理员
     * @param kid
     * @throws BusinessException
     */
    void deleteManager(List<Integer> ids) throws BusinessException;
    
    /**
     * 根据ID获取管理员
     * @param kid
     * @return
     * @throws BusinessException
     */
    Manager getManagerById(int id) throws BusinessException;
    
    /**
     * 根据用户名搜索
     * @param username
     * @return
     * @throws BusinessException
     */
    Manager getManagerByUsername(String username) throws BusinessException;
    
    /**
     * 获取全部的管理员
     * @return
     * @throws BusinessException
     */
    List<Manager> findAllManager() throws BusinessException;
    
    /**
     * 分页查询管理员
     * @param managerSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<Manager> selectByLimitPage(ManagerSearchDto managerSearchDto) throws BusinessException;
    
    /**
     * 管理员登录
     * @param manager
     */
    void login(ManagerLoginDto manager) throws BusinessException;
    
    /**
     * 获取当前登录对象
     * @return
     * @throws BusinessException
     */
    Manager getLoginManager() throws BusinessException;
}
