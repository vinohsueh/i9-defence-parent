package i9.defence.platform.api.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.comparator.PageUrlComparator;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.PageUrl;
import i9.defence.platform.model.Permission;
import i9.defence.platform.model.Role;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.service.PageUrlService;
import i9.defence.platform.service.PermissionService;
import i9.defence.platform.service.RoleService;

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
    @Autowired
    private PageUrlService pageUrlService;
    @Autowired
    private ManagerService ManagerService;
    @Autowired
    private RoleService roleService;
    
    @RequestMapping(value = "/noAllowedAuth")
    public HashMap<String, Object> noAllowedAuth() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Manager manager = ManagerService.getLoginManager();
        // 查找全部权限字
        Set<Permission> permissions = permissionService.getNotHavPermissionByManagerId(manager.getId());

        // 用于保存用户没有的权限字
        ArrayList<String> noHaveCodes = new ArrayList<String>();
        // 遍历所有权限字，将用户没有的权限字保存到list
        for (Permission permission : permissions) {
            noHaveCodes.add(permission.getCode());
        }
        result.put("data", noHaveCodes);
        
        //查询页签
        Set<Role> roles = roleService.getRoleByManagerId(manager.getId());
        List<Role> roleList = new ArrayList<Role>(roles);
        Integer roleId = roleList.get(0).getId();
        //查询所有的页签
        List<PageUrl> urls = pageUrlService.getPageByRoleId(roleId);
        //找出一级页签
        HashMap<Integer, PageUrl> map = new HashMap<Integer, PageUrl>();
        for (PageUrl pageUrl : urls) {
            if (pageUrl.getParentId() == null) {
                map.put(pageUrl.getId(), pageUrl);
            }
        }
        
        for (PageUrl pageUrl : urls) {
            if (map.containsKey(pageUrl.getParentId())) {
                if (map.get(pageUrl.getParentId()).getItems() != null) {
                    map.get(pageUrl.getParentId()).getItems().add(pageUrl);
                }else{
                    map.get(pageUrl.getParentId()).setItems(new ArrayList<PageUrl>());
                    map.get(pageUrl.getParentId()).getItems().add(pageUrl);
                }
                
            }
        }
        //查询显示的页签
        List<PageUrl> pages = new ArrayList<PageUrl>();
        for (Map.Entry<Integer, PageUrl> pageUrl : map.entrySet()) {
            pages.add(pageUrl.getValue());
        }
        Collections.sort(pages, new PageUrlComparator());
        result.put("urls", pages);
        result.put("user", manager);
        return result;
    }
}
