package i9.defence.platform.api.controller;

import i9.defence.platform.comparator.PageUrlComparator;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.PageUrl;
import i9.defence.platform.model.Role;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.service.PageUrlService;
import i9.defence.platform.service.RoleService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * 页签管理
 * 创建时间：2018年2月3日 下午3:09:12
 * @author  lby
 * @version  
 * 
 */
@RestController
@RequestMapping("page")
public class PageUrlController {
    @Autowired
    private PageUrlService pageUrlService;
    @Autowired
    private ManagerService ManagerService;
    @Autowired
    private RoleService roleService;
    
    /**
     * 查询该角色的所有页签
     * @return
     */
    @RequestMapping("/getPages")
    public HashMap<String, Object> getPages() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Manager Manager = ManagerService.getLoginManager();
        Set<Role> roles = roleService.getRoleByManagerId(Manager.getId());
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
        return result;
    }
    
    /**
     * 查询全部页签
     * @return
     */
    @RequiresPermissions("page_crud")
    @RequestMapping("/getAllPages")
    public HashMap<String, Object> getAllPages() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<PageUrl> urls = pageUrlService.selectAllPages();
        result.put("urls", urls);
        return result;
    }
    
    /**
     * 查询所有一级页签
     * @return
     */
    @RequiresPermissions("page_crud")
    @RequestMapping("/getAllFirstPages")
    public HashMap<String, Object> getAllFirstPages() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<PageUrl> urls = pageUrlService.selectAllFirstPages();
        result.put("urls", urls);
        return result;
    }
    
    /**
     * 查询页签
     * @param id
     * @return
     */
    @RequiresPermissions("page_crud")
    @RequestMapping("/getPage")
    public HashMap<String, Object> getPage(@RequestBody Integer id) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        PageUrl url = pageUrlService.getPageById(id);
        result.put("data", url);
        return result;
    }
    
    /**
     * 保存页签
     * @param pageUrl
     * @return
     */
    @RequiresPermissions("page_crud")
    @RequestMapping("/savePage")
    public HashMap<String, Object> savePage(@RequestBody PageUrl pageUrl) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        pageUrlService.savePage(pageUrl);
        return result;
    }
}
