package i9.defence.platform.service;

import i9.defence.platform.model.Permission;
import i9.defence.platform.model.PermissionExample;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

import java.util.List;
import java.util.Set;

/** 
 * 创建时间：2018年1月4日 上午9:47:35
 * @author  lby
 * @version  
 * 
 */
public interface PermissionService {
    /**
     * 添加权限
     * @param Permission
     * @throws BusinessException
     */
    void addPermission(Permission permission) throws BusinessException;
    
    /**
     * 更新权限
     * @param Permission
     * @throws BusinessException
     */
    void updatePermission(Permission permission) throws BusinessException;
    
    /**
     * 删除权限
     * @param kid
     * @throws BusinessException
     */
    void deletePermission(List<Integer> ids) throws BusinessException;
    
    /**
     * 根据ID获取权限
     * @param kid
     * @return
     * @throws BusinessException
     */
    Permission getPermissionById(int id) throws BusinessException;
    
    /**
     * 获取全部的权限
     * @return
     * @throws BusinessException
     */
    List<Permission> findAllPermission() throws BusinessException;
    
    /**
     * 分页查询权限
     * @param PermissionSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     * @throws BusinessException
     */
    PageBounds<Permission> selectByLimitPage(PermissionExample permissionExample,
            int currectPage, int pageSize) throws BusinessException;
    
    /**
     * 通过用户id查找权限
     * @param managerId
     * @return
     * @throws BusinessException
     */
    Set<Permission> getPermissionByManagerId(Integer managerId) throws BusinessException;
    
    /**
     * 通过用户id查找没有的权限
     * @param managerId
     * @return
     * @throws BusinessException
     */
	Set<Permission> getNotHavPermissionByManagerId(Integer managerId) throws BusinessException;
}
