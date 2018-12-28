package i9.defence.platform.service.impl;

import i9.defence.platform.dao.ManagerApplyDao;
import i9.defence.platform.dao.ManagerDao;
import i9.defence.platform.dao.RoleDao;
import i9.defence.platform.dao.vo.ManagerLoginDto;
import i9.defence.platform.dao.vo.ManagerSearchDto;
import i9.defence.platform.dao.vo.ManagerSelectDto;
import i9.defence.platform.dao.vo.ManagerUpdatePwdDto;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.ManagerApply;
import i9.defence.platform.model.Role;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.Constants;
import i9.defence.platform.utils.PageBounds;
import i9.defence.platform.utils.StringUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 创建时间：2018年1月4日 上午10:48:12
 * 
 * @author lby
 * @version
 * 
 */
@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
    /**
     * 项目管理员byte
     */
    private static final Byte S_PROJ_MANAGER = (byte) 2;
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ManagerApplyDao managerApplyDao;

    @Override
    public void addNetManager(Manager manager) throws BusinessException {
        if (!manager.getConfirmPwd().equals(manager.getPassword())) {
            throw new BusinessException("前后密码不一致!");
        }
        try {
            Manager existManager = managerDao.getManagerByUsername(manager.getUsername());
            if (manager.getId() != null) {
                if (existManager.getId() != manager.getId()) {
                    throw new BusinessException("用户名已存在!");
                }
                // 更新密码
                Manager eManager = managerDao.getManagerById(manager.getId());
                if (!eManager.getPassword().endsWith(manager.getPassword())) {
                    manager.setPassword(StringUtil.MD5(manager.getPassword()));
                }
                managerDao.updateManager(manager);
                // 删除用户角色
                managerDao.delManagerRole(manager.getId());
            } else {
                if (existManager != null) {
                    throw new BusinessException("用户名已存在!");
                }
                manager.setCreateTime(new Date());
                manager.setPassword(StringUtil.MD5(manager.getPassword()));
                managerDao.addManager(manager);
            }
            Role role = roleDao.getRoleByName(manager.getRole().getName());
            if (role != null) {
                // 添加用户角色
                managerDao.addManagerRole(manager.getId(), role.getId());
            }
        } catch (BusinessException e) {
            throw new BusinessException(e.getErrorMessage());
        } catch (Exception e) {
            throw new BusinessException("添加管理员失败", e.getMessage());
        }
    }

    @Override
    public void updateManager(Manager manager) throws BusinessException {
        try {
            managerDao.updateManager(manager);
        } catch (Exception e) {
            throw new BusinessException("更新管理员失败", e.getMessage());
        }
    }

    @Override
    public void deleteManager(List<Integer> ids) throws BusinessException {
        try {
            managerDao.deleteManager(ids);
        } catch (Exception e) {
            throw new BusinessException("删除管理员失败", e.getMessage());
        }
    }

    @Override
    public Manager getManagerById(int id) throws BusinessException {
        try {
            return managerDao.getManagerById(id);
        } catch (Exception e) {
            throw new BusinessException("查询管理员失败", e.getMessage());
        }
    }

    @Override
    public Manager getManagerByUsername(String username) throws BusinessException {
        try {
            return managerDao.getManagerByUsername(username);
        } catch (Exception e) {
            throw new BusinessException("查询管理员失败", e.getMessage());
        }
    }

    @Override
    public List<Manager> findAllManager() throws BusinessException {
        try {
            return managerDao.findAllManager();
        } catch (Exception e) {
            throw new BusinessException("查询管理员失败", e.getMessage());
        }
    }

    @Override
    public PageBounds<Manager> selectByLimitPage(ManagerSearchDto managerSearchDto) throws BusinessException {
        try {
            return managerDao.selectByLimitPage(managerSearchDto, managerSearchDto.getCurrentPage(),
                    managerSearchDto.getPageSize());
        } catch (Exception e) {
            throw new BusinessException("分页查询管理员失败", e.getMessage());
        }
    }

    @Override
    public void login(ManagerLoginDto manager) throws BusinessException {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(manager.getUsername(),
                StringUtil.MD5(manager.getPassword()));
        try {
            subject.login(token);
            Manager cManager = managerDao.getManagerByUsername(manager.getUsername());
            Session shiroSession = subject.getSession();
            shiroSession.setAttribute("loginUser", cManager);
        } catch (IncorrectCredentialsException ice) {
            // 捕获密码错误异常
            throw new BusinessException("密码错误");
        } catch (UnknownAccountException uae) {
            // 捕获未知用户名异常
            throw new BusinessException("帐号不存在");
        } catch (LockedAccountException eae) {
            // 帐号被锁定
            throw new BusinessException("帐号被锁定");
        } catch (Exception e) {
            throw new BusinessException("查询失败");
        }
    }

    @Override
    public Manager getLoginManager() throws BusinessException {
        Subject subject = SecurityUtils.getSubject();
        Session shiroSession = subject.getSession();
        Manager loginUser = (Manager) shiroSession.getAttribute("loginUser");
        return loginUser;
    }

    @Override
    public List<ManagerSelectDto> selectConditionMan(ManagerSearchDto managerSearchDto) throws BusinessException {
        try {
            return managerDao.selectConditionMan(managerSearchDto);
        } catch (Exception e) {
            throw new BusinessException("获取全部的管理员之新建项目时选择---责任人、经销商、安全责任人失败", e.getMessage());
        }

    }

    @Override
    public void updateStatus(Manager manager) throws BusinessException {
        Byte status = manager.getStatus();
        try {
            if (status == 0) {
                manager.setStatus((byte) 1);
            } else {
                manager.setStatus((byte) 0);
            }
            managerDao.updateManager(manager);
        } catch (Exception e) {
            throw new BusinessException("修改账户开启状态Status失败", e.getMessage());
        }
    }

    @Override
    public List<Manager> selectAllAgency(Integer partentId) throws BusinessException {
        try {
            return managerDao.selectAllAgency(partentId);
        } catch (Exception e) {
            throw new BusinessException("查询无建立默认或者已经建立关系并且自己为一级经销商列表失败", e.getMessage());
        }
    }

    @Override
    public List<Manager> selectPartAgency(Integer agencyId) throws BusinessException {
        try {
            return managerDao.selectPartAgency(agencyId);
        } catch (Exception e) {
            throw new BusinessException("查询无分配无建立关系的等待被分配的经销商列表失败", e.getMessage());
        }
    }

    @Override
    public void insertManagerGrade(List<Integer> managerIdS, Integer parentId) throws BusinessException {
        try {
            managerDao.insertManagerGrade(managerIdS, parentId);
        } catch (Exception e) {
            throw new BusinessException("往经销商关系表中增加关系分配二级三级经销商失败", e.getMessage());
        }
    }

    @Override
    public List<Manager> selectAagency() throws BusinessException {
        try {
            return managerDao.selectAagency();
        } catch (Exception e) {
            throw new BusinessException("查询已经建立关系的一级经销商们失败", e.getMessage());
        }
    }

    @Override
    public List<Manager> selectBagency(Integer agencyId) throws BusinessException {
        try {
            return managerDao.selectBagency(agencyId);
        } catch (Exception e) {
            throw new BusinessException("查询已经建立关系的二级经销商们失败", e.getMessage());
        }
    }

    @Override
    public void updateBagency(Integer agencyId, Integer newParentId) throws BusinessException {
        try {
            managerDao.updateBagency(agencyId, newParentId);
        } catch (Exception e) {
            throw new BusinessException("把此二级经销商的parentId 修改为新一级经销商ID失败", e.getMessage());
        }
    }

    @Override
    public void updateCagency(List<Integer> managerIds, Integer newParentId) throws BusinessException {
        try {
            managerDao.updateCagency(managerIds, newParentId);
        } catch (Exception e) {
            throw new BusinessException("把此二级经销商下的全部下属三级经销商的parentId修改为新的二级经销商ID失败", e.getMessage());
        }
    }

    @Override
    public void deleteAgencyById(Integer managerId, Integer parentId) throws BusinessException {
        try {
            managerDao.deleteAgencyById(managerId, parentId);
        } catch (Exception e) {
            throw new BusinessException("撤销（删除）一级下的二级或者二级下的三级,右侧---->左侧失败", e.getMessage());
        }
    }

    @Override
    public void addProjectManager(Manager manager) throws BusinessException {
        if (!manager.getConfirmPwd().equals(manager.getPassword())) {
            throw new BusinessException("前后密码不一致!");
        }
        if (manager.getProjectId() == null) {
            throw new BusinessException("请选择项目!");
        }
        // 判断用户注册申请的角色
        if (Arrays.asList(Constants.S_PROJ_MANAGER).contains(manager.getRole().getName())) {
            manager.setType(S_PROJ_MANAGER);
        } else {
            throw new BusinessException("用户权限错误,请选择正确的权限");
        }
        try {
            Manager existManager = managerDao.getManagerByUsername(manager.getUsername());
            ManagerApply existManagerApply = managerApplyDao.getUnRefusedManagerApplyByUsername(manager.getUsername());
            if (existManager != null || existManagerApply != null) {
                throw new BusinessException("用户名已存在!");
            }
            manager.setCreateTime(new Date());
            manager.setPassword(StringUtil.MD5(manager.getPassword()));
            managerDao.addManager(manager);
            // 添加角色
            managerDao.addBatchManagerRole(Arrays.asList(manager));
        } catch (BusinessException e) {
            throw new BusinessException(e.getErrorMessage());
        } catch (Exception e1) {
            throw new BusinessException("添加账户申请失败", e1.getMessage());
        }
    }

    @Override
    public List<Manager> selectSafeZeroByProjectId(Integer projectId) throws BusinessException {
        try {
            return managerDao.selectSafeZeroByProjectId(projectId);
        } catch (Exception e) {
            throw new BusinessException("查询 此项目下不是安全责任人  safe = 0失败", e.getMessage());
        }
    }

    @Override
    public List<Manager> selectBagency() throws BusinessException {
        try {
            return managerDao.selectBagency();
        } catch (Exception e) {
            throw new BusinessException("查询已经建立关系的二级经销商们失败", e.getMessage());
        }
    }

    @Override
    public List<Integer> selectCIdsByBid(Integer bId) throws BusinessException {
        try {
            return managerDao.selectCIdsByBid(bId);
        } catch (Exception e) {
            throw new BusinessException("根据二级ID查询全部的三级的ID们失败", e.getMessage());
        }
    }

    @Override
    public int selectAgencyGrade(Integer managerId) throws BusinessException {
        return managerDao.selectAgencyGrade(managerId);
    }

    @Override
    public void updateManagerPwd(ManagerUpdatePwdDto managerUpdatePwdDto) throws BusinessException {
        Manager loginManager = getLoginManager();
        // 验证旧密码
        if (!StringUtil.MD5(managerUpdatePwdDto.getOldPassword()).equals(loginManager.getPassword())) {
            throw new BusinessException("旧密码错误");
        }
        // 验证新密码
        if (!managerUpdatePwdDto.getPassword().equals(managerUpdatePwdDto.getConfirmPwd())) {
            throw new BusinessException("新密码输入不一致");
        }
        try {
            loginManager.setPassword(StringUtil.MD5(managerUpdatePwdDto.getPassword()));
            managerDao.updateManager(loginManager);
        } catch (Exception e) {
            throw new BusinessException("更改密码失败", e.getMessage());
        }
    }

}
