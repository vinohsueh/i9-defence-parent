package i9.defence.platform.dao.mapper;

import i9.defence.platform.model.Permission;
import i9.defence.platform.model.PermissionExample;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface PermissionMapper {
    int countByExample(@Param("example") PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    List<Permission> selectByLimitPage(@Param("example") PermissionExample example, @Param("offset") int offset, @Param("limit") int pageSize);

    Set<Permission> getPermissionByManagerId(Integer managerId);
}