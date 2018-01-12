package i9.defence.platform.service.impl;

import i9.defence.platform.dao.ManagerApplyDao;
import i9.defence.platform.dao.ManagerDao;
import i9.defence.platform.dao.RoleDao;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.ManagerApply;
import i9.defence.platform.model.ManagerApplyExample;
import i9.defence.platform.service.ManagerApplyService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;
import i9.defence.platform.utils.StringUtil;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 
 * 创建时间：2018年1月11日 下午4:52:41
 * @author  lby
 * @version  
 * 
 */
@Service
public class ManagerApplyServiceImpl implements ManagerApplyService{
    
    @Autowired
    private ManagerApplyDao managerApplyDao;
    
    @Autowired
    private ManagerDao managerDao;
    
    @Autowired
    private RoleDao roleDao;
    
    @Override
    public void addManagerApply(ManagerApply managerApply)
            throws BusinessException {
        if (!managerApply.getConfirmPwd().equals(managerApply.getPassword())){
            throw new BusinessException("前后密码不一致!");
        }
        try {
            Manager existManager = managerDao.getManagerByUsername(managerApply.getUsername());
            ManagerApply existManagerApply = managerApplyDao.getUnRefusedManagerApplyByUsername(managerApply.getUsername());
            if (existManager != null || existManagerApply != null){
                throw new BusinessException("用户名已存在!");
            }
            managerApply.setCreateTime(new Date());
            managerApply.setPassword(StringUtil.MD5(managerApply.getPassword()));
            managerApplyDao.addManagerApply(managerApply);
        } catch (BusinessException e) {
            throw new BusinessException(e.getErrorMessage());
        } catch (Exception e) {
            throw new BusinessException("添加账户申请失败",e.getMessage());
        }
    }

    @Override
    public void updateManagerApply(ManagerApply managerApply)
            throws BusinessException {
        try {
            managerApplyDao.updateManagerApply(managerApply);
        } catch (Exception e) {
            throw new BusinessException("更新账户申请失败");
        }
    }

    @Override
    public void deleteManagerApply(List<Integer> ids) throws BusinessException {
        try {
            managerApplyDao.deleteManagerApply(ids);
        } catch (Exception e) {
            throw new BusinessException("删除账户申请失败");
        }
    }

    @Override
    public ManagerApply getManagerApplyById(int id) throws BusinessException {
        try {
            return managerApplyDao.getManagerApplyById(id);
        } catch (Exception e) {
            throw new BusinessException("查询账户申请失败");
        }
    }

    @Override
    public PageBounds<ManagerApply> selectByLimitPage(
            ManagerApplyExample managerApplyExample, int currectPage,
            int pageSize) throws BusinessException {
        try {
            return managerApplyDao.selectByLimitPage(managerApplyExample, currectPage, pageSize);
        } catch (Exception e) {
            throw new BusinessException("分页查询账户申请失败");
        }
    }

}
