package i9.defence.platform.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.ManagerApplyDao;
import i9.defence.platform.dao.mapper.ManagerApplyMapper;
import i9.defence.platform.model.ManagerApply;
import i9.defence.platform.model.ManagerApplyExample;
import i9.defence.platform.utils.PageBounds;

/** 
 * 创建时间：2018年1月11日 下午4:48:59
 * @author  lby
 * @version  
 * 
 */
@Repository
public class ManagerApplyDaoImpl implements ManagerApplyDao{

    @Autowired
    private ManagerApplyMapper managerApplyMapper;
    
    @Override
    public void addManagerApply(ManagerApply managerApply) throws Exception {
        managerApplyMapper.insertSelective(managerApply);
    }

    @Override
    public void updateManagerApply(ManagerApply managerApply) throws Exception {
        managerApplyMapper.updateByPrimaryKeySelective(managerApply);
    }

    @Override
    public void deleteManagerApply(List<Integer> ids) throws Exception {
        managerApplyMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public ManagerApply getManagerApplyById(int id) throws Exception {
        return managerApplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBounds<ManagerApply> selectByLimitPage(
            ManagerApplyExample managerApplyExample, int currectPage,
            int pageSize) throws Exception {
        final int totalSize = managerApplyMapper.countByExample(managerApplyExample);
        PageBounds<ManagerApply> pageBounds = new PageBounds<ManagerApply>(currectPage, totalSize, pageSize);
        List<ManagerApply> list = managerApplyMapper.selectByLimitPage(managerApplyExample, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

}
