package i9.defence.platform.dao;

import i9.defence.platform.model.Permission;
import i9.defence.platform.model.PermissionExample;
import i9.defence.platform.utils.PageBounds;

import java.util.List;

/** 
 * 创建时间：2018年1月4日 上午9:47:35
 * @author  lby
 * @version  
 * 
 */
public interface PermissionDao {
    /**
     * 添加权限
     * @param permission
     * @throws Exception
     */
    void addPermission(Permission permission) throws Exception;
    
    /**
     * 更新权限
     * @param permission
     * @throws Exception
     */
    void updatePermission(Permission permission) throws Exception;
    
    /**
     * 删除权限
     * @param kid
     * @throws Exception
     */
    void deletePermission(List<Integer> ids) throws Exception;
    
    /**
     * 根据ID获取权限
     * @param kid
     * @return
     * @throws Exception
     */
    Permission getPermissionById(int id) throws Exception;
    
    /**
     * 获取全部的权限
     * @return
     * @throws Exception
     */
    List<Permission> findAllPermission() throws Exception;
    
    /**
     * 分页查询权限
     * @param permissionExample
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<Permission> selectByLimitPage(PermissionExample permissionExample,
            int currectPage, int pageSize) throws Exception;
    
    /**
     * 通过权限名称查找权限
     * @param name
     * @return
     */
    Permission selectPermissionByName(String name) throws Exception;
    
    /**
     * 通过权限代码查找权限
     * @param code
     * @return
     */
    Permission selectPermissionByCode(String code) throws Exception;
}
