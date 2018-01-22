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
     * @param id
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
     * 批量添加用户
     * @param managers
     */
    void addBatchManagers(List<Manager> managers);
    
    /**
     * 批量添加用户角色
     * @param mrs
     */
    void addBatchManagerRole(List<Manager> managers);
}