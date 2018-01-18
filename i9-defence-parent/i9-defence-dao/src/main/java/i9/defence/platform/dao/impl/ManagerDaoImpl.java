package i9.defence.platform.dao.impl;

import i9.defence.platform.dao.ManagerDao;
import i9.defence.platform.dao.mapper.ManagerMapper;
import i9.defence.platform.dao.vo.ManagerSearchDto;
import i9.defence.platform.dao.vo.ManagerSelectDto;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.ManagerExample;
import i9.defence.platform.utils.PageBounds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/** 
 * 创建时间：2018年1月4日 上午10:27:17
 * @author  lby
 * @version  
 * 
 */
@Repository
public class ManagerDaoImpl implements ManagerDao{
    
    @Autowired
    private ManagerMapper managerMapper;
    
    @Override
    public void addManager(Manager manager) throws Exception {
        managerMapper.insertSelective(manager);
    }

    @Override
    public void updateManager(Manager manager) throws Exception {
        managerMapper.updateByPrimaryKeySelective(manager);
    }

    @Override
    public void deleteManager(List<Integer> ids) throws Exception {
        managerMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public Manager getManagerById(int id) throws Exception {
        return managerMapper.selectByPrimaryKey(id);
    }

    @Override
    public Manager getManagerByUsername(String username) throws Exception {
        ManagerExample example = new ManagerExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Manager> list = managerMapper.selectByExample(example);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Manager> findAllManager() throws Exception {
        ManagerExample example = new ManagerExample();
        List<Manager> list = managerMapper.selectByExample(example);
        return list;
    }

    @Override
    public PageBounds<Manager> selectByLimitPage(
            ManagerSearchDto managerSearchDto, int currectPage, int pageSize)
            throws Exception {
        final int totalSize = managerMapper.countByExample(managerSearchDto);
        PageBounds<Manager> pageBounds = new PageBounds<Manager>(currectPage, totalSize, pageSize);
        List<Manager> list = managerMapper.selectByLimitPage(managerSearchDto, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

    @Override
    public void delManagerRole(Integer managerId) {
        managerMapper.delManagerRole(managerId);
    }

    @Override
    public void addManagerRole(Integer id, Integer roleId) throws Exception {
        managerMapper.addManagerRole(id,roleId);
    }
    
    @Override
    public List<ManagerSelectDto> selectConditionMan(ManagerSearchDto managerSearchDto) throws Exception {
        return managerMapper.selectConditionMan(managerSearchDto);
    }

    @Override
    public List<Manager> selectAllAgency() throws Exception {
        return managerMapper.selectAllAgency();
    }

    @Override
    public List<Manager> selectPartAgency() throws Exception {
        return managerMapper.selectPartAgency();
    }

}
