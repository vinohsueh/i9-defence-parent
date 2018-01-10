package i9.defence.platform.dao;

import i9.defence.platform.dao.vo.ManagerSearchDto;
import i9.defence.platform.model.Manager;
import i9.defence.platform.utils.PageBounds;

import java.util.List;

/** 
 * 创建时间：2018年1月4日 上午9:47:35
 * @author  lby
 * @version  
 * 
 */
public interface ManagerDao {
    /**
     * 添加管理员
     * @param manager
     * @throws Exception
     */
    void addManager(Manager manager) throws Exception;
    
    /**
     * 更新管理员
     * @param manager
     * @throws Exception
     */
    void updateManager(Manager manager) throws Exception;
    
    /**
     * 删除管理员
     * @param kid
     * @throws Exception
     */
    void deleteManager(List<Integer> ids) throws Exception;
    
    /**
     * 根据ID获取管理员
     * @param kid
     * @return
     * @throws Exception
     */
    Manager getManagerById(int id) throws Exception;
    
    /**
     * 根据用户名搜索
     * @param username
     * @return
     * @throws Exception
     */
    Manager getManagerByUsername(String username) throws Exception;
    
    /**
     * 获取全部的管理员
     * @return
     * @throws Exception
     */
    List<Manager> findAllManager() throws Exception;
    
    /**
     * 分页查询管理员
     * @param managerSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<Manager> selectByLimitPage(ManagerSearchDto managerSearchDto,
            int currectPage, int pageSize) throws Exception;
    
    /**
     * 根据账户id删除角色信息
     * @param id
     */
    void delManagerRole(Integer managerId) throws Exception;
    
    /**
     * 添加用户角色信息
     * @param id
     * @param roleId
     */
    void addManagerRole(Integer id, Integer roleId) throws Exception;
}
