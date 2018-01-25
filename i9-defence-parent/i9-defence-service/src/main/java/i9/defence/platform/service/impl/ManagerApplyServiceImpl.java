package i9.defence.platform.service.impl;

import i9.defence.platform.dao.ManagerApplyDao;
import i9.defence.platform.dao.ManagerDao;
import i9.defence.platform.dao.ProjectDao;
import i9.defence.platform.dao.vo.ApplyRefuseDto;
import i9.defence.platform.dao.vo.ManagerApplyDto;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.ManagerApply;
import i9.defence.platform.model.Project;
import i9.defence.platform.service.ManagerApplyService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.Constants;
import i9.defence.platform.utils.PageBounds;
import i9.defence.platform.utils.ShareCodeUtil;
import i9.defence.platform.utils.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 
 * 创建时间：2018年1月11日 下午4:52:41
 * @author  lby
 * @version  
 * 
 */
@Service
@Transactional
public class ManagerApplyServiceImpl implements ManagerApplyService{
    
    /**
     * manager默认状态
     */
    private static final Byte  S_STATUS =(byte)1;
    
    /**
     * 经销商byte
     */
    private static final Byte  S_AGENCY =(byte)1;
    
    /**
     * 项目管理员byte
     */
    private static final Byte  S_PROJ_MANAGER =(byte)2;
    
    @Autowired
    private ManagerApplyDao managerApplyDao;
    
    @Autowired
    private ManagerDao managerDao;
    
    @Autowired
    private ProjectDao projectDao;
    
    @Override
    public void addManagerApply(ManagerApply managerApply)
            throws BusinessException {
        if (!managerApply.getConfirmPwd().equals(managerApply.getPassword())){
            throw new BusinessException("前后密码不一致!");
        }
        //判断用户注册申请的角色
        if (Arrays.asList(Constants.S_AGENCY).contains(managerApply.getRoleName())){
            managerApply.setType(S_AGENCY);
        } else if(Arrays.asList(Constants.S_PROJ_MANAGER).contains(managerApply.getRoleName())){
            managerApply.setType(S_PROJ_MANAGER);
        } else {
            throw new BusinessException("用户权限错误,请选择正确的权限");
        }
        if (2 == managerApply.getType() && StringUtils.isBlank(managerApply.getShareCode())){
            throw new BusinessException("请输入邀请码");
        }
        try {
            Manager existManager = managerDao.getManagerByUsername(managerApply.getUsername());
            ManagerApply existManagerApply = managerApplyDao.getUnRefusedManagerApplyByUsername(managerApply.getUsername());
            if (existManager != null || existManagerApply != null){
                throw new BusinessException("用户名已存在!");
            }
            managerApply.setCreateTime(new Date());
            managerApply.setPassword(StringUtil.MD5(managerApply.getPassword()));
            //如果用户申请的是项目管理人员等 需要输入邀请码
            if (2 == managerApply.getType()){
                Long projectId = ShareCodeUtil.codeToId(managerApply.getShareCode());
                Project project = projectDao.getProjectById(projectId.intValue());
                if (project == null) {
                    throw new BusinessException("邀请码错误!");
                }
                managerApply.setProjectId(projectId.intValue());
            }
            managerApplyDao.addManagerApply(managerApply);
        } catch (BusinessException e) {
            throw new BusinessException(e.getErrorMessage());
        } catch (Exception e1) {
            throw new BusinessException("添加账户申请失败",e1.getMessage());
        }
    }

    @Override
    public void updateManagerApply(ManagerApply managerApply)
            throws BusinessException {
        try {
            managerApplyDao.updateManagerApply(managerApply);
        } catch (Exception e) {
            throw new BusinessException("更新账户申请失败",e.getMessage());
        }
    }

    @Override
    public void deleteManagerApply(List<Integer> ids) throws BusinessException {
        try {
            managerApplyDao.deleteManagerApply(ids);
        } catch (Exception e) {
            throw new BusinessException("删除账户申请失败",e.getMessage());
        }
    }

    @Override
    public ManagerApply getManagerApplyById(int id) throws BusinessException {
        try {
            return managerApplyDao.getManagerApplyById(id);
        } catch (Exception e) {
            throw new BusinessException("查询账户申请失败",e.getMessage());
        }
    }

    @Override
    public PageBounds<ManagerApply> selectByLimitPage(
            ManagerApplyDto managerApplyDto, int currectPage,
            int pageSize) throws BusinessException {
        try {
            return managerApplyDao.selectByLimitPage(managerApplyDto, currectPage, pageSize);
        } catch (Exception e) {
            throw new BusinessException("分页查询账户申请失败",e.getMessage());
        }
    }

    @Override
    public void agreeManagerApply(List<Integer> ids,Integer managerId) throws BusinessException {
        try {
            //申请账户
            List<ManagerApply> managerApplys = managerApplyDao.selectApplysByIds(ids);
            //要同意的账户列表
            List<Manager> managers = new ArrayList<Manager>();
            //要同意的账户列表对应的角色
            for (ManagerApply managerApply : managerApplys) {
                if (Arrays.asList(Constants.S_ADMIN).contains(managerApply.getRoleName())){
                    throw new BusinessException("不允许添加该角色!");
                }
                if (managerApply.getAgreeFlag() != 0){
                    throw new BusinessException("只能同意未操作的!");
                }
                managerApply.setCreateTime(new Date());
                Manager manager = managerApply.getManager();
                manager.setStatus(S_STATUS);
                managers.add(manager);
            }
            //更新申请的状态
            managerApplyDao.updateBatchManagerApplys(managerApplys,managerId);
            //将申请的角色添加到用户表里
            managerDao.addBatchManagers(managers);
            //添加用户的角色
            managerDao.addBatchManagerRole(managers);
        } catch (BusinessException e) {
            throw new BusinessException(e.getErrorMessage());
        } catch (Exception e) {
            throw new BusinessException("同意账户申请失败",e.getMessage());
        }
        
    }

    @Override
    public void refuseManagerApply(ApplyRefuseDto applyRefuseDto,Integer managerId) throws BusinessException {
         //申请账户
        try {
            List<ManagerApply> managerApplys = managerApplyDao.selectApplysByIds(applyRefuseDto.getIds());
            for (ManagerApply managerApply : managerApplys) {
                if (managerApply.getAgreeFlag() != 0){
                    throw new BusinessException("只能拒绝未操作的!");
                }
            }
            managerApplyDao.refuseManagerApply(applyRefuseDto,managerId);
        } catch (BusinessException e) {
            throw new BusinessException(e.getErrorMessage());
        } catch (Exception e) {
            throw new BusinessException("拒绝账户申请失败",e.getMessage());
        }
    }

}
