package i9.defence.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i9.defence.platform.dao.PermissionDao;
import i9.defence.platform.model.Permission;
import i9.defence.platform.model.PermissionExample;
import i9.defence.platform.service.PermissionService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/** 
 * 创建时间：2018年1月8日 下午3:18:46
 * @author  lby
 * @version  
 * 
 */
@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionDao permissionDao;
    
    @Override
    public void addPermission(Permission permission) throws BusinessException {
        try {
            if (permission.getId() != null) {
                permissionDao.updatePermission(permission);
            }else{
                permissionDao.addPermission(permission);
            }
        } catch (Exception e) {
            throw new BusinessException("添加权限失败",e.getMessage());
        }
    }

    @Override
    public void updatePermission(Permission permission)
            throws BusinessException {
        try {
            permissionDao.updatePermission(permission);
        } catch (Exception e) {
            throw new BusinessException("更新权限失败",e.getMessage());
        }
    }

    @Override
    public void deletePermission(List<Integer> ids) throws BusinessException {
        try {
            permissionDao.deletePermission(ids);
        } catch (Exception e) {
            throw new BusinessException("删除权限失败",e.getMessage());
        }
    }

    @Override
    public Permission getPermissionById(int id) throws BusinessException {
        try {
            return permissionDao.getPermissionById(id);
        } catch (Exception e) {
            throw new BusinessException("查询权限失败",e.getMessage());
        }
    }

    @Override
    public List<Permission> findAllPermission() throws BusinessException {
        try {
            return permissionDao.findAllPermission();
        } catch (Exception e) {
            throw new BusinessException("查询权限失败",e.getMessage());
        }
    }

    @Override
    public PageBounds<Permission> selectByLimitPage(
            PermissionExample permissionExample, int currectPage, int pageSize)
            throws BusinessException {
        try {
            return permissionDao.selectByLimitPage(permissionExample, currectPage, pageSize);
        } catch (Exception e) {
            throw new BusinessException("分页查询权限失败",e.getMessage());
        }
    }

}
