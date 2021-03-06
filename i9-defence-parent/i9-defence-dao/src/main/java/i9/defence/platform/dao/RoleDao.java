package i9.defence.platform.dao;

import i9.defence.platform.model.Role;
import i9.defence.platform.model.RoleExample;
import i9.defence.platform.utils.PageBounds;

import java.util.List;
import java.util.Set;

/** 
 * 创建时间：2018年1月4日 上午9:47:35
 * @author  lby
 * @version  
 * 
 */
public interface RoleDao {
    /**
     * 添加角色
     * @param Role
     * @throws Exception
     */
    void addRole(Role role) throws Exception;
    
    /**
     * 更新角色
     * @param Role
     * @throws Exception
     */
    void updateRole(Role role) throws Exception;
    
    /**
     * 删除角色
     * @param kid
     * @throws Exception
     */
    void deleteRole(List<Integer> ids) throws Exception;
    
    /**
     * 根据ID获取角色
     * @param kid
     * @return
     * @throws Exception
     */
    Role getRoleById(int id) throws Exception;
    
    /**
     * 获取全部的角色
     * @return
     * @throws Exception
     */
    List<Role> findAllRole() throws Exception;
    
    /**
     * 分页查询角色
     * @param RoleSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<Role> selectByLimitPage(RoleExample roleExample,
            int currectPage, int pageSize) throws Exception;
    
    /**
     * 根据角色名称查找角色
     * @param role
     * @return
     * @throws Exception
     */
    Role getRoleByName(String roleName) throws Exception;
    
    /**
     * 通过角色代码查找角色
     * @param code
     * @return
     * @throws Exception
     */
    Role selectRoleByCode(String code) throws Exception;
    
    /**
     * 查询用户角色
     * @param managerId
     * @return
     */
    Set<Role> getRoleByManagerId(Integer managerId) throws Exception;
    
    /**
     * 删除角色的权限
     * @param id
     * @throws Exception
     */
    void deletePermissionByRole(Integer roleId) throws Exception;
    
    /**
     * 添加角色权限
     * @param id
     * @param permissions
     */
    void addRolePermissions(Integer id, List<Integer> permissions)  throws Exception;
    
    /**
     * 删除多个角色的权限
     * @param id
     * @throws Exception
     */
    void deletePermissionByRoles(List<Integer> ids) throws Exception;

    /**
     * 获取除了管理员以外的角色
     * @return
     * @throws Exception
     */
    List<Role> selectPartRole() throws Exception;
}
