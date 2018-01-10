package i9.defence.platform.dao.impl;

import i9.defence.platform.dao.RoleDao;
import i9.defence.platform.dao.mapper.RoleMapper;
import i9.defence.platform.model.Role;
import i9.defence.platform.model.RoleExample;
import i9.defence.platform.utils.PageBounds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/** 
 * 创建时间：2018年1月8日 下午2:44:18
 * @author  lby
 * @version  
 * 
 */
@Repository
public class RoleDaoImpl implements RoleDao{

    @Autowired
    private RoleMapper roleMapper;
    
    @Override
    public void addRole(Role role) throws Exception {
        roleMapper.insertSelective(role);
    }

    @Override
    public void updateRole(Role role) throws Exception {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void deleteRole(List<Integer> ids) throws Exception {
        roleMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public Role getRoleById(int id) throws Exception {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> findAllRole() throws Exception {
        RoleExample example = new RoleExample();
        return roleMapper.selectByExample(example);
    }

    @Override
    public PageBounds<Role> selectByLimitPage(RoleExample roleExample,
            int currectPage, int pageSize) throws Exception {
        final int totalSize = roleMapper.countByExample(roleExample);
        PageBounds<Role> pageBounds = new PageBounds<Role>(currectPage, totalSize, pageSize);
        List<Role> list = roleMapper.selectByLimitPage(roleExample, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

    @Override
    public Role getRoleByName(String roleName) throws Exception {
        RoleExample example = new RoleExample();
        example.createCriteria().andNameEqualTo(roleName);
        List<Role> list = roleMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Role selectRoleByCode(String code) throws Exception {
        RoleExample example = new RoleExample();
        example.createCriteria().andCodeEqualTo(code);
        List<Role> list = roleMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
