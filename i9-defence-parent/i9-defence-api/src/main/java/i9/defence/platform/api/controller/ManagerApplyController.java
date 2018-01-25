package i9.defence.platform.api.controller;

import i9.defence.platform.dao.vo.ApplyRefuseDto;
import i9.defence.platform.dao.vo.ManagerApplyDto;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.ManagerApply;
import i9.defence.platform.service.ManagerApplyService;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.service.ProjectService;
import i9.defence.platform.utils.Constants;
import i9.defence.platform.utils.PageBounds;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * 用户申请controller
 * 创建时间：2018年1月12日 上午9:11:59
 * @author  lby
 * @version  
 * 
 */
@RestController
@RequiresPermissions("managerApply")
@RequestMapping("managerApply")
public class ManagerApplyController {
    /**
     * 排序方式
     */
    private static final String S_ORDER_BY_CLAUSE = "createTime desc";
     
    /**
     * 项目管理员的类型
     */
    private static final Byte S_PROJ_MANAGER_TYPE = (byte)2;
    
    @Autowired
    private ManagerApplyService managerApplyService;
    
    @Autowired
    private ManagerService managerService;
    
    @Autowired
    private ProjectService projectService;
    
    /**
     * 分页查询用户申请
     * @param pageListDto
     * @return
     */
    @RequestMapping("/pageManagerApply")
    public HashMap<String, Object> pageManagerApply(@RequestBody ManagerApplyDto managerApplyDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Manager manager = managerService.getLoginManager();
        //经销商只能看到他自己的项目管理人员的申请
        if (Arrays.asList(Constants.S_AGENCY).contains(manager.getRole().getName())){
            managerApplyDto.setType(S_PROJ_MANAGER_TYPE);
            managerApplyDto.setDistributorId(manager.getId());
        }
        managerApplyDto.setOrderByClause(S_ORDER_BY_CLAUSE);
        PageBounds<ManagerApply> pageBounds = managerApplyService.selectByLimitPage(managerApplyDto, managerApplyDto.getCurrentPage(), managerApplyDto.getPageSize());
        result.put("data",pageBounds);
        return result;
    }
    
    /**
     * 获取申请
     * @param id
     * @return
     */
    @RequestMapping("/getManagerApply")
    public HashMap<String, Object> pageManagerApply(@RequestBody Integer id) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        ManagerApply managerApply = managerApplyService.getManagerApplyById(id);
        result.put("data",managerApply);
        return result;
    }
    
    /**
     * 同意
     * @param ids
     * @return
     */
     @RequestMapping("/agreeManagerApply")
     public HashMap<String, Object> agreeManagerApply(@Valid @NotEmpty(message = "至少选择一个")@RequestBody List<Integer> ids) {
         HashMap<String, Object> result = new HashMap<String, Object>();
         managerApplyService.agreeManagerApply(ids,managerService.getLoginManager().getId());
         return result;
     }
     
     /**
      * 拒绝
      * @param ids
      * @return
      */
      @RequestMapping("/refuseManagerApply")
      public HashMap<String, Object> refuseManagerApply(@Valid @RequestBody ApplyRefuseDto applyRefuseDto,BindingResult bindingResult) {
          HashMap<String, Object> result = new HashMap<String, Object>();
          managerApplyService.refuseManagerApply(applyRefuseDto,managerService.getLoginManager().getId());
          return result;
      }
    
    /**
     * 删除
     * @param ids
     * @return
     */
     @RequestMapping("/delManagerApply")
     public HashMap<String, Object> delRole(@Valid @NotEmpty(message = "至少选择一个")@RequestBody List<Integer> ids) {
         HashMap<String, Object> result = new HashMap<String, Object>();
         managerApplyService.deleteManagerApply(ids);
         return result;
     }
     
}   
