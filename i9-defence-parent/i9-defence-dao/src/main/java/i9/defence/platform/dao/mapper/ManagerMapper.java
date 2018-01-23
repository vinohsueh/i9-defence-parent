package i9.defence.platform.dao.mapper;

import i9.defence.platform.dao.vo.ManagerSearchDto;
import i9.defence.platform.dao.vo.ManagerSelectDto;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.ManagerExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ManagerMapper {
    int countByExample(@Param("example") ManagerSearchDto managerSearchDto);

    int deleteByExample(ManagerExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(Manager record);

    int insertSelective(Manager record);

    List<Manager> selectByExample(ManagerExample example);

    Manager selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Manager record, @Param("example") ManagerExample example);

    int updateByExample(@Param("record") Manager record, @Param("example") ManagerExample example);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
    
    List<Manager> selectByLimitPage(@Param("example") ManagerSearchDto managerSearchDto, @Param("offset") int offset, @Param("limit") int pageSize);
    
    List<ManagerSelectDto> selectConditionMan(@Param("example") ManagerSearchDto managerSearchDto);
    
    /**
     * 删除用户的角色信息
     * @param managerId
     */
    void delManagerRole(Integer managerId);
    
    /**
     * 添加用户角色信息
     * @param managerId
     * @param roleId
     */
    void addManagerRole(@Param("managerId") Integer managerId,@Param("roleId") Integer roleId);

    /**
     * 查询经销商列表(此查询是  查询的已经有下级  和为一级的  经销商)
     */
    List<Manager> selectAllAgency();

    /**
     * 查询经销商列表(此查询是 无等级关系的  等待分配的经销商  操作模态框的左侧)
     */
    List<Manager> selectPartAgency();

    /**
     * 往经销商关系表中增加关系分配二级三级经销商
     */
    void insertManagerGrade(@Param("managerIdS") List<Integer> managerIdS,@Param("parentId") Integer parentId);

    /**
     * 查询已经建立关系的一级经销商们
     */
    List<Manager> selectAagency();

    /**
     * 查询已经建立关系的二级经销商们
     */
    List<Manager> selectBagency(@Param("agencyId") Integer agencyId);

    /**
     * 把此二级经销商的parentId 修改为新一级经销商ID
     * */
    void updateBagency(@Param("agencyId") Integer agencyId,@Param("newParentId") Integer newParentId);

    /**
     * 把此二级经销商下的全部下属三级经销商的parentId修改为新的二级经销商ID
     * */
    void updateCagency(@Param("managerIds") List<Integer> managerIds,@Param("newParentId") Integer newParentId);

    /**
     * 撤销（删除）一级下的二级或者二级下的三级     右侧---->左侧(一个一个地撤销  因为会对二级判断)
     * */
    void deleteAgencyById(@Param("managerId") Integer managerId,@Param("parentId") Integer parentId);
}