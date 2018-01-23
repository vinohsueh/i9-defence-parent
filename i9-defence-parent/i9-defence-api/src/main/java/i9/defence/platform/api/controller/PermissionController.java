package i9.defence.platform.api.controller;

import i9.defence.platform.dao.vo.PageListDto;
import i9.defence.platform.model.Permission;
import i9.defence.platform.model.PermissionExample;
import i9.defence.platform.service.PermissionService;
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
 * 权限
 * 创建时间：2018年1月8日 下午3:00:43
 * @author  lby
 * @version  
 * 
 */
@RestController
@RequiresPermissions("permissionPage")
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    
    
    /**
     * 分页查询权限
     * @param pageListDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/pagePermission")
    public HashMap<String, Object> pagepermission(@RequestBody PageListDto pageListDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        PermissionExample example = new PermissionExample();
        PageBounds<Permission> pageBounds = permissionService.selectByLimitPage(example, pageListDto.getCurrentPage(), pageListDto.getPageSize());
        result.put("data",pageBounds);
        return result;
    }
    
   /**
    * 添加权限
    * @param permission
    * @return
    */
    @RequestMapping("/addPermission")
    public HashMap<String, Object> pagepermission(@Valid @RequestBody Permission permission) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        permissionService.addPermission(permission);
        return result;
    }
    
    /**
     * id查找
     * @param permissionId
     * @return
     */
    @RequestMapping("/getPermission")
    public HashMap<String, Object> pagepermission(@RequestBody Integer permissionId) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Permission permission = permissionService.getPermissionById(permissionId);
        result.put("data",permission);
        return result;
    }
    
   /**
    * 删除
    * @param ids
    * @return
    */
    @RequestMapping("/delPermission")
    public HashMap<String, Object> delPermission(@Valid @NotEmpty(message = "至少选择一个") @RequestBody List<Integer> ids) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        permissionService.deletePermission(ids);
        return result;
    }
    
    /**
     * 获取全部权限
     * @return
     */
    @RequestMapping("/getAll")
    public HashMap<String, Object> getAll() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<Permission> list = permissionService.findAllPermission();
        result.put("data",list);
        return result;
    }
}
