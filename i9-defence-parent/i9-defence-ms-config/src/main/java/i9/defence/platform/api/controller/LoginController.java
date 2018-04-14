package i9.defence.platform.api.controller;

import i9.defence.platform.dao.vo.ManagerLoginDto;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.ManagerApply;
import i9.defence.platform.model.Permission;
import i9.defence.platform.model.Role;
import i9.defence.platform.service.ManagerApplyService;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.service.PermissionService;
import i9.defence.platform.service.RoleService;
import i9.defence.platform.utils.BusinessException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/** 
 * 创建时间：2018年1月4日 下午8:20:44
 * @author  lby
 * @version  
 * 
 */
@Controller
@RequestMapping("")
public class LoginController {
    
    private static final Logger S_LOGGER = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private ManagerService managerService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ManagerApplyService managerApplyService;
    @Autowired
    private PermissionService permissionService;
    /**
     * 首页
     * @return
     */
    @RequestMapping("/index.html")
    public String index() {
        return "index";
    }
    
    /**
     * 注册
     * @return
     */
    @RequestMapping("/regist.html")
    public ModelAndView regist(@ModelAttribute ManagerApply managerApply) {
        ModelAndView modelAndView = new ModelAndView("regist");
        managerApply.setSecuritier((byte)0);
        List<Role> roles = roleService.selectPartRole();
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }
    
    
    /**
     * 用户注册
     * @param managerApply
     * @return
     */
     @ResponseBody
     @RequestMapping("/regist")
     public ModelAndView addManagerApply(@ModelAttribute @Valid ManagerApply managerApply,BindingResult bindingResult) {
         try {
             managerApplyService.addManagerApply(managerApply);
             List<Role> roles = roleService.selectPartRole();
             return new ModelAndView("regist").addObject("success", "注册成功,请等待审批").addObject("roles", roles);
         } catch (BusinessException exception) {
             S_LOGGER.error("error, message: {}, errorMessage: {}, exception: {}",exception.getMessage(),exception.getErrorMessage(),exception.getExceptionMessage());
             List<Role> roles = roleService.selectPartRole();
             return new ModelAndView("regist").addObject("exception", exception).addObject("roles", roles);
         }
     }
    
    /**
     * 默认跳转
     * @return
     */
    @RequestMapping("/")
    public String defaultPage() {
        return "redirect:/index.html";
    }
    
    /**
     * 登录页面
     * @param managerLoginDto
     * @return
     */
    @RequestMapping("/login.html")
    public String toLogin(@ModelAttribute ManagerLoginDto managerLoginDto) {
        return "login";
    }
    
    /**
     * 登录
     * @param manager
     * @param bindingResult
     * @return
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute @Valid ManagerLoginDto manager,BindingResult bindingResult){
        try {
            managerService.login(manager);
            return new ModelAndView("redirect:index.html");
        } catch (BusinessException e) {
            return new ModelAndView("login").addObject("exception", e);
        }
        
    }
    
    /**
     * 当前登录用户
     * @return
     */
    @RequestMapping("/currentUser")
    @ResponseBody
    public HashMap<String, Object> current() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Manager manager = managerService.getLoginManager();
        result.put("data", manager);
        // 查找全部权限字
        List<Permission> permissions = permissionService.findAllPermission();

        // 过滤出当前登录用户没有的权限字
        boolean flag = false;
        // 用于保存用户没有的权限字
        ArrayList<String> noHaveCodes = new ArrayList<String>();
        //Shiro的subject实质上是当前执行用户的特定视图。
        Subject currenUser = SecurityUtils.getSubject();
        // 遍历所有权限字，将用户没有的权限字保存到list
        for (Permission permission : permissions) {
            flag = currenUser.isPermitted(permission.getCode());
            if (!flag) {
                noHaveCodes.add(permission.getCode());
            }
        }
        result.put("noHaveCodes", noHaveCodes);
        return result;
    }

}

