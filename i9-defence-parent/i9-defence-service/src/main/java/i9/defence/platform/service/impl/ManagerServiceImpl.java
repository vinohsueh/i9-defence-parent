package i9.defence.platform.service.impl;

import i9.defence.platform.dao.ManagerDao;
import i9.defence.platform.dao.vo.ManagerLoginDto;
import i9.defence.platform.dao.vo.ManagerSearchDto;
import i9.defence.platform.model.Manager;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;
import i9.defence.platform.utils.StringUtil;

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
 * @author  lby
 * @version  
 * 
 */
@Service
@Transactional
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    private ManagerDao managerDao;
    
    @Override
    public void addManager(Manager manager) throws BusinessException {
        try {
            if(manager.getId() != null) {
                managerDao.updateManager(manager);
            }else{
                manager.setCreateTime(new Date());
                managerDao.addManager(manager);
            }
        } catch (Exception e) {
            throw new BusinessException("添加管理员失败",e.getMessage());
        }
    }

    @Override
    public void updateManager(Manager manager) throws BusinessException {
        try {
            managerDao.updateManager(manager);
        } catch (Exception e) {
            throw new BusinessException("更新管理员失败",e.getMessage());
        }
    }

    @Override
    public void deleteManager(List<Integer> ids) throws BusinessException {
        try {
            managerDao.deleteManager(ids);
        } catch (Exception e) {
            throw new BusinessException("删除管理员失败",e.getMessage());
        }
    }

    @Override
    public Manager getManagerById(int id) throws BusinessException {
        try {
            return managerDao.getManagerById(id);
        } catch (Exception e) {
            throw new BusinessException("查询管理员失败",e.getMessage());
        }
    }

    @Override
    public Manager getManagerByUsername(String username)
            throws BusinessException {
        try {
            return managerDao.getManagerByUsername(username);
        } catch (Exception e) {
            throw new BusinessException("查询管理员失败",e.getMessage());
        }
    }

    @Override
    public List<Manager> findAllManager() throws BusinessException {
        try {
            return managerDao.findAllManager();
        } catch (Exception e) {
            throw new BusinessException("查询管理员失败",e.getMessage());
        }
    }

    @Override
    public PageBounds<Manager> selectByLimitPage(ManagerSearchDto managerSearchDto)
            throws BusinessException {
        try {
            return managerDao.selectByLimitPage(managerSearchDto,managerSearchDto.getCurrentPage(),managerSearchDto.getPageSize());
        } catch (Exception e) {
            throw new BusinessException("分页查询管理员失败",e.getMessage());
        }
    }

    @Override
    public void login(ManagerLoginDto manager) throws BusinessException {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(manager.getUsername(),StringUtil.MD5(manager.getPassword()));
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

}
