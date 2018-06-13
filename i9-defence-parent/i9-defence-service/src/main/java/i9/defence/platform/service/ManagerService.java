package i9.defence.platform.service;

import i9.defence.platform.dao.vo.ManagerLoginDto;
import i9.defence.platform.dao.vo.ManagerSearchDto;
import i9.defence.platform.dao.vo.ManagerSelectDto;
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
     * 添加网站管理员
     * @param manager
     * @throws BusinessException
     */
    void addNetManager(Manager manager) throws BusinessException;
    
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
    
    /**
     *获取全部的管理员之新建项目时选择---责任人、经销商、安全责任人
     * @return
     * @throws Exception
     */
    List<ManagerSelectDto> selectConditionMan(ManagerSearchDto managerSearchDto) throws BusinessException;

    /**
     * 修改账户开启状态Status
     * @return
     * @throws Exception
     */
    void updateStatus(Manager manager) throws BusinessException;

    /**
     * 查询经销商列表(此查询是 查询默认的经销商即还没有建立关系的等待被分配的经销商
     * 或者  查询已经有下级  自己为老大一级的 无parentId（父ID）的 经销商)
     */
    List<Manager> selectAllAgency(Integer partentId) throws BusinessException;

    /**
     * 查询经销商列表(此查询是 无等级关系的  等待被分配的默认经销商  操作模态框的左侧)
     */
    List<Manager> selectPartAgency(Integer agencyId) throws BusinessException;

    /**
     * 往经销商关系表中增加关系分配二级三级经销商
     */
    void insertManagerGrade(List<Integer> managerIdS,Integer parentId) throws BusinessException;

    /**
     * 查询已经建立关系的一级经销商们
     */
    List<Manager> selectAagency() throws BusinessException;
    
    /**
     * 查询已经建立关系的二级经销商们
     */
    List<Manager> selectBagency() throws BusinessException;

    /**
     * 查询已经建立关系的指定一级经销商下的二级经销商们
     */
    List<Manager> selectBagency(Integer agencyId) throws BusinessException;

    /**
     * 把此二级经销商的parentId 修改为新一级经销商ID
     * */
    void updateBagency(Integer agencyId,Integer newParentId) throws BusinessException;

    /**
     * 把此二级经销商下的全部下属三级经销商的parentId修改为新的二级经销商ID
     * */
    void updateCagency(List<Integer> managerIds,Integer newParentId) throws BusinessException;
    
    /**
     * 根据二级ID查询全部的三级的ID们
     * */
    List<Integer> selectCIdsByBid(Integer bId) throws BusinessException;

    /**
     * 撤销（删除）一级下的二级或者二级下的三级     右侧---->左侧(一个一个地撤销  因为会对二级判断)
     * */
    void deleteAgencyById(Integer managerId, Integer parentId) throws BusinessException;
    
    /**
     * 经销商添加项目管理员
     * @param manager
     */
    void addProjectManager(Manager manager) throws BusinessException;
    
    //查询 此项目下不是安全责任人  safe = 0
  	List<Manager> selectSafeZeroByProjectId(Integer projectId) throws BusinessException;
  	
  	/**
  	 * 查询经销商等级
  	 * @param managerId
  	 * @return
  	 * @throws BusinessException
  	 */
	int selectAgencyGrade(Integer managerId) throws BusinessException;
}
