package i9.defence.platform.dao;

import i9.defence.platform.dao.vo.ManagerSearchDto;
import i9.defence.platform.dao.vo.ManagerSelectDto;
import i9.defence.platform.model.Manager;
import i9.defence.platform.utils.BusinessException;
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
    
    /**
     * 获取全部的管理员之新建项目时选择---责任人、经销商、安全责任人
     * @return
     * @throws Exception
     */
    List<ManagerSelectDto> selectConditionMan(ManagerSearchDto managerSearchDto) throws Exception;

    /**
     * 查询经销商列表(此查询是  查询的已经有下级  和为一级的  经销商)
     */
    List<Manager> selectAllAgency(Integer partentId) throws Exception;

    /**
     * 查询经销商列表(此查询是 无等级关系的  等待分配的经销商  操作模态框的左侧)
     */
    List<Manager> selectPartAgency(Integer agencyId) throws Exception;

    /**
     * 往经销商关系表中增加关系分配二级三级经销商
     */
    void insertManagerGrade(List<Integer> managerIdS,Integer parentId) throws Exception;

    /**
     * 查询已经建立关系的一级经销商们
     */
    List<Manager> selectAagency() throws Exception;
    
    /**
     * 查询已经建立关系的二级经销商们
     */
    List<Manager> selectBagency() throws Exception;

    /**
     * 查询已经建立关系的指定一级经销商下的二级经销商们
     */
    List<Manager> selectBagency(Integer agencyId) throws Exception;

    /**
     * 把此二级经销商的parentId 修改为新一级经销商ID
     * */
    void updateBagency(Integer agencyId,Integer newParentId) throws Exception;

    /**
     * 把此二级经销商下的全部下属三级经销商的parentId修改为新的二级经销商ID
     * */
    void updateCagency(List<Integer> managerIds,Integer newParentId) throws Exception;
    
    /**
     * 根据二级ID查询全部的三级的ID们
     * */
    List<Integer> selectCIdsByBid(Integer bId) throws Exception;

    /**
     * 撤销（删除）一级下的二级或者二级下的三级     右侧---->左侧(一个一个地撤销  因为会对二级判断)
     * */
    void deleteAgencyById(Integer managerId, Integer parentId) throws Exception;

    /**
     * 批量添加账户
     * @param managers
     * @throws Exception
     */
    void addBatchManagers(List<Manager> managers) throws Exception;

    /**
     * 批量添加用户角色
     * @param mrs
     */
    void addBatchManagerRole(List<Manager> managers) throws Exception;
    
    /**
     * 查询有无父级经销商
     * @param Id
     * @return
     * @throws Exception
     */
    Integer selectParentById(Integer Id) throws Exception;
    
    /**
     * 将安全责任人和项目关系插入表中
     * @param projSafeList
     * @throws Exception
     */
	void addProjSafeManager(List<Manager> projSafeList) throws Exception;
	
	//查询 此项目下不是安全责任人  safe = 0
	List<Manager> selectSafeZeroByProjectId(Integer projectId) throws Exception;
}
