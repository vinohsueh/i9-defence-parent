package i9.defence.platform.service.impl;

import i9.defence.platform.dao.PermissionDao;
import i9.defence.platform.model.Permission;
import i9.defence.platform.model.PermissionExample;
import i9.defence.platform.service.PermissionService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/** 
 * 创建时间：2018年1月8日 下午3:18:46
 * @author  lby
 * @version  
 * 
 */
@Service
public class PermissionServiceImpl implements PermissionService{
    /**
     * 缓存的key
     */
    public static final String ALL_KEY = "\"permission_all\"";
    /**
     * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml
     */
    public static final String PERMISSION_CACHE = "permission";
    @Autowired
    private PermissionDao permissionDao;
    
    @Override
    @CacheEvict(value=PERMISSION_CACHE,key=ALL_KEY)
    public void addPermission(Permission permission) throws BusinessException {
        try {
            Permission permissionByName = permissionDao.selectPermissionByName(permission.getName());
            Permission permissionByCode = permissionDao.selectPermissionByCode(permission.getCode());
            if (permission.getId() != null) {
                if (permissionByName != null && permissionByName.getId() != permission.getId()) {
                    throw new BusinessException("权限名称重复!");
                }
                if (permissionByCode != null && permissionByCode.getId() != permission.getId()) {
                    throw new BusinessException("权限代码重复!");
                }
                permissionDao.updatePermission(permission);
            }else{
                if (permissionByName != null || permissionByCode != null) {
                    throw new BusinessException("权限名称或代码重复!");
                }
                permissionDao.addPermission(permission);
            }
        } catch (BusinessException e) {
            throw new BusinessException(e.getErrorMessage());
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
    @CacheEvict(value=PERMISSION_CACHE,key=ALL_KEY)
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
    @Cacheable(value = PERMISSION_CACHE, key = ALL_KEY)
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

    @Override
    @Cacheable(value = PERMISSION_CACHE, key = "'manager'+#managerId")
    public Set<Permission> getPermissionByManagerId(Integer managerId)
            throws BusinessException {
        try {
            return permissionDao.getPermissionByManagerId(managerId);
        } catch (Exception e) {
            throw new BusinessException("查询用户权限失败",e.getMessage());
        }
    }

}
