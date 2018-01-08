package i9.defence.platform.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.PermissionDao;
import i9.defence.platform.dao.mapper.PermissionMapper;
import i9.defence.platform.model.Permission;
import i9.defence.platform.model.PermissionExample;
import i9.defence.platform.utils.PageBounds;

/** 
 * 创建时间：2018年1月8日 下午3:14:15
 * @author  lby
 * @version  
 * 
 */
@Repository
public class PermissionDaoImpl implements PermissionDao{

    @Autowired
    private PermissionMapper permissionMapper;
    
    @Override
    public void addPermission(Permission permission) throws Exception {
        permissionMapper.insertSelective(permission);
    }

    @Override
    public void updatePermission(Permission permission) throws Exception {
        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public void deletePermission(List<Integer> ids) throws Exception {
        permissionMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public Permission getPermissionById(int id) throws Exception {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Permission> findAllPermission() throws Exception {
        PermissionExample example = new PermissionExample();
        return permissionMapper.selectByExample(example);
    }

    @Override
    public PageBounds<Permission> selectByLimitPage(
            PermissionExample permissionExample, int currectPage, int pageSize)
            throws Exception {
        final int totalSize = permissionMapper.countByExample(permissionExample);
        PageBounds<Permission> pageBounds = new PageBounds<Permission>(currectPage, totalSize, pageSize);
        List<Permission> list = permissionMapper.selectByLimitPage(permissionExample, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

}
