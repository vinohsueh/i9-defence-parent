package i9.defence.platform.api.controller;

import i9.defence.platform.dao.vo.PageListDto;
import i9.defence.platform.model.PageUrl;
import i9.defence.platform.model.Role;
import i9.defence.platform.model.RoleExample;
import i9.defence.platform.service.PageUrlService;
import i9.defence.platform.service.RoleService;
import i9.defence.platform.utils.PageBounds;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * 角色
 * 创建时间：2018年1月8日 下午3:00:43
 * @author  lby
 * @version  
 * 
 */
@RestController
@RequiresPermissions("role_crud")
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PageUrlService pageUrlService;
    
    /**
     * 分页查询角色
     * @param pageListDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/pageRole")
    public HashMap<String, Object> pageRole(@RequestBody PageListDto pageListDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        RoleExample example = new RoleExample();
        PageBounds<Role> pageBounds = roleService.selectByLimitPage(example, pageListDto.getCurrentPage(), pageListDto.getPageSize());
        result.put("data",pageBounds);
        return result;
    }
    
   /**
    * 添加角色
    * @param Role
    * @return
    */
    @RequestMapping("/addRole")
    public HashMap<String, Object> addRole(@RequestBody Role role) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        roleService.addRole(role);
        return result;
    }
    
    /**
     * id查找
     * @param RoleId
     * @return
     */
    @RequestMapping("/getRole")
    public HashMap<String, Object> getRole(@RequestBody Integer roleId) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Role role = roleService.getRoleById(roleId);
        List<PageUrl> pagesList = pageUrlService.getPageByRoleId(role.getId());
        result.put("pages", pagesList);
        result.put("data",role);
        return result;
    }
    
   /**
    * 删除
    * @param ids
    * @return
    */
    @RequestMapping("/delRole")
    public HashMap<String, Object> delRole(@Valid @NotEmpty(message = "至少选择一个")@RequestBody List<Integer> ids) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        roleService.deleteRole(ids);
        return result;
    }
}
