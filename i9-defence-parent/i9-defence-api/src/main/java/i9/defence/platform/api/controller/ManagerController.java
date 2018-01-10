package i9.defence.platform.api.controller;

import i9.defence.platform.dao.vo.ManagerSearchDto;
import i9.defence.platform.model.Manager;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.utils.PageBounds;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * 网站管理员
 * 创建时间：2018年1月4日 下午3:08:08
 * @author  lby
 * @version  
 * 
 */
@RestController
@RequestMapping("manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    
    
    /**
     * 分页查询网站管理员
     * @param managerSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/pageManager")
    public HashMap<String, Object> pageManager(@RequestBody ManagerSearchDto managerSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        managerSearchDto.setType((byte)0);
        PageBounds<Manager> pageBounds = managerService.selectByLimitPage(managerSearchDto);
        result.put("data",pageBounds);
        return result;
    }
    
   /**
    * 添加网站管理员
    * @param manager
    * @return
    */
    @RequestMapping("/addNetManager")
    public HashMap<String, Object> addNetManager(@Valid @RequestBody Manager manager,BindingResult bindingResult) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        manager.setType((byte)0);
        managerService.addNetManager(manager);
        return result;
    }
    
    /**
     * id查找
     * @param managerId
     * @return
     */
    @RequestMapping("/getManager")
    public HashMap<String, Object> pageManager(@RequestBody Integer managerId) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Manager manager = managerService.getManagerById(managerId);
        result.put("data",manager);
        return result;
    }
    
   /**
    * 删除
    * @param ids
    * @return
    */
    @RequestMapping("/delManager")
    public HashMap<String, Object> delManager(@RequestBody List<Integer> ids) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        managerService.deleteManager(ids);
        return result;
    }
}
