package i9.defence.platform.api.controller;

import i9.defence.platform.model.Permission;
import i9.defence.platform.service.PermissionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取用户没有的权限字
 * 创建时间：2018年1月10日 下午2:43:45
 * 
 * @author lby
 * @version
 * 
 */
@RestController
@RequestMapping("security")
public class SecurityController {
    
    @Autowired
    private PermissionService permissionService;
    
    @RequestMapping(value = "/noAllowedAuth")
    public HashMap<String, Object> noAllowedAuth() {
        HashMap<String, Object> result = new HashMap<String, Object>();

        // 查找全部权限字
        List<Permission> permissions = permissionService.findAllPermission();

        // 过滤出当前登录用户没有的权限字
        boolean flag = false;
        // 用于保存用户没有的权限字
        ArrayList<String> noHaveCodes = new ArrayList<String>();
        Subject currenUser = SecurityUtils.getSubject();
        // 遍历所有权限字，将用户没有的权限字保存到list
        for (Permission permission : permissions) {
            flag = currenUser.isPermitted(permission.getCode());
            if (!flag) {
                noHaveCodes.add(permission.getCode());
            }
        }
        result.put("data", noHaveCodes);
        return result;
    }
}
