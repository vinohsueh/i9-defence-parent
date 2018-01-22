package i9.defence.platform.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.ManagerApplyDao;
import i9.defence.platform.dao.mapper.ManagerApplyMapper;
import i9.defence.platform.dao.vo.ApplyRefuseDto;
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
    
    /**
     * 用户申请被拒绝的标记
     */
    private static final byte S_REFUSE_FLAG = (byte)-1;
    
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

    @Override
    public ManagerApply getUnRefusedManagerApplyByUsername(String username)
            throws Exception {
        ManagerApplyExample example = new ManagerApplyExample();
        example.createCriteria().andUsernameEqualTo(username).andAgreeFlagNotEqualTo(S_REFUSE_FLAG);
        List<ManagerApply> list = managerApplyMapper.selectByExample(example);
        if (list.size() > 0 ){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<ManagerApply> selectApplysByIds(List<Integer> ids)
            throws Exception {
        return managerApplyMapper.selectApplysByIds(ids);
    }

    @Override
    public void updateBatchManagerApplys(List<ManagerApply> managerApplys,Integer managerId)
            throws Exception {
        managerApplyMapper.updateBatchManagerApplys(managerApplys,managerId);
    }

    @Override
    public void refuseManagerApply(ApplyRefuseDto applyRefuseDto,
            Integer managerId) {
        managerApplyMapper.refuseManagerApply(applyRefuseDto,managerId);
    }

}
