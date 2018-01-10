package i9.defence.platform.dao.mapper;

import i9.defence.platform.model.Role;
import i9.defence.platform.model.RoleExample;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int countByExample(@Param("example") RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> selectByLimitPage(@Param("example") RoleExample example, @Param("offset") int offset, @Param("limit") int pageSize);

    Set<Role> getRoleByManagerId(Integer managerId);

    void deletePermissionByRole(Integer roleId);

    void addRolePermissions(@Param("roleId") Integer roleId,@Param("permissionIds") List<Integer> permissions);
}