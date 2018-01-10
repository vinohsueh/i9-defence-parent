package i9.defence.platform.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i9.defence.platform.dao.RoleDao;
import i9.defence.platform.model.Role;
import i9.defence.platform.model.RoleExample;
import i9.defence.platform.service.RoleService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/** 
 * 创建时间：2018年1月8日 下午2:55:30
 * @author  lby
 * @version  
 * 
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;
    
    @Override
    public void addRole(Role role) throws BusinessException {
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
        } catch (BusinessException e) {
            throw new BusinessException(e.getErrorMessage());
        } catch (Exception e) {
            throw new BusinessException("添加角色失败",e.getMessage());
        }
    }

    @Override
    public void updateRole(Role role) throws BusinessException {
        try {
            roleDao.updateRole(role);
        } catch (Exception e) {
            throw new BusinessException("更新角色失败",e.getMessage());
        }
    }

    @Override
    public void deleteRole(List<Integer> ids) throws BusinessException {
        try {
            roleDao.deleteRole(ids);
        } catch (Exception e) {
            throw new BusinessException("删除角色失败",e.getMessage());
        }
    }

    @Override
    public Role getRoleById(int id) throws BusinessException {
        try {
            return roleDao.getRoleById(id);
        } catch (Exception e) {
            throw new BusinessException("查询角色失败",e.getMessage());
        }
    }

    @Override
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
    public Set<Role> getRoleByManagerId(Integer managerId)
            throws BusinessException {
        try {
            return roleDao.getRoleByManagerId(managerId);
        } catch (Exception e) {
            throw new BusinessException("查询用户角色失败",e.getMessage());
        }
    }

}
