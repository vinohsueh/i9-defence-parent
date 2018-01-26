package i9.defence.platform.service.impl;

import i9.defence.platform.dao.RoleDao;
import i9.defence.platform.model.Role;
import i9.defence.platform.model.RoleExample;
import i9.defence.platform.service.RoleService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/** 
 * 创建时间：2018年1月8日 下午2:55:30
 * @author  lby
 * @version  
 * 
 */
@Service
public class RoleServiceImpl implements RoleService{
    /**
     * 缓存的key
     */
    public static final String ALL_KEY = "\"role_all\"";
    /**
     * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml
     */
    public static final String ROLE_CACHE = "role";
    /**
     * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml
     */
    public static final String PERMISSION_CACHE = "permission";
    @Autowired
    private RoleDao roleDao;
    
    @Override
    @Caching(put=@CachePut(value=ROLE_CACHE,key="#role.id"), evict = { @CacheEvict(value = ROLE_CACHE, key = ALL_KEY),
        @CacheEvict(value=PERMISSION_CACHE, allEntries = true) })
    public Role addRole(Role role) throws BusinessException {
        try {
            Role roleByName = roleDao.getRoleByName(role.getName());
            Role roleByCode = roleDao.selectRoleByCode(role.getCode());
            if (role.getId() != null) {
                if (roleByName != null && roleByName.getId() != role.getId()) {
                    throw new BusinessException("角色名称重复!");
                }
                if (roleByCode != null && roleByCode.getId() != role.getId()) {
                    throw new BusinessException("角色代码重复!");
                }
                roleDao.updateRole(role);
                //删除角色已有权限
                roleDao.deletePermissionByRole(role.getId());
            }else{
                if (roleByName != null || roleByCode != null) {
                    throw new BusinessException("角色名称或代码重复!");
                }
                roleDao.addRole(role);
            }
            //添加角色权限
            if (role.getPermissionIds().size() > 0) {
                roleDao.addRolePermissions(role.getId(),role.getPermissionIds());
            }
            return role;
        } catch (BusinessException e) {
            throw new BusinessException(e.getErrorMessage());
        } catch (Exception e) {
            throw new BusinessException("添加角色失败",e.getMessage());
        }
    }

    @Override
    @CachePut(value=ROLE_CACHE,key="#role.id")
    @CacheEvict(value=ROLE_CACHE,key=ALL_KEY)
    public Role updateRole(Role role) throws BusinessException {
        try {
            roleDao.updateRole(role);
            return role;
        } catch (Exception e) {
            throw new BusinessException("更新角色失败",e.getMessage());
        }
    }
    
    @Override
    @CacheEvict(value=ROLE_CACHE,key=ALL_KEY)
    public void deleteRole(List<Integer> ids) throws BusinessException {
        try {
            roleDao.deleteRole(ids);
            //删除角色的权限
            roleDao.deletePermissionByRoles(ids);
        } catch (Exception e) {
            throw new BusinessException("删除角色失败",e.getMessage());
        }
    }
    
    @Override
    @Cacheable(value = ROLE_CACHE, key = "#id")
    public Role getRoleById(int id) throws BusinessException {
        try {
            return roleDao.getRoleById(id);
        } catch (Exception e) {
            throw new BusinessException("查询角色失败",e.getMessage());
        }
    }

    @Override
    @Cacheable(value = ROLE_CACHE, key = ALL_KEY)
    public List<Role> findAllRole() throws BusinessException {
        try {
            return roleDao.findAllRole();
        } catch (Exception e) {
            throw new BusinessException("查询角色失败",e.getMessage());
        }
    }

    @Override
    public PageBounds<Role> selectByLimitPage(RoleExample roleExample,
            int currectPage, int pageSize) throws BusinessException {
        try {
            return roleDao.selectByLimitPage(roleExample, currectPage, pageSize);
        } catch (Exception e) {
            throw new BusinessException("分页查询角色失败",e.getMessage());
        }
    }

    @Override
    @Cacheable(value = ROLE_CACHE, key = "'manager'+#managerId")
    public Set<Role> getRoleByManagerId(Integer managerId)
            throws BusinessException {
        try {
            return roleDao.getRoleByManagerId(managerId);
        } catch (Exception e) {
            throw new BusinessException("查询用户角色失败",e.getMessage());
        }
    }

	@Override
	public List<Role> selectPartRole() throws BusinessException {
		try {
			return roleDao.selectPartRole();
		} catch (Exception e) {
			throw new BusinessException("查询部分用户角色失败",e.getMessage());
		}
	}

}
