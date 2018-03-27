package i9.defence.platform.api.controller;

import i9.defence.platform.dao.vo.ProjectSearchDto;
import i9.defence.platform.dao.vo.ProjectSelectDto;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.Project;
import i9.defence.platform.model.Role;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.service.ProjectService;
import i9.defence.platform.utils.Constants;
import i9.defence.platform.utils.PageBounds;
import i9.defence.platform.utils.ShareCodeUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建时间：2018年1月9日
 * 
 * @author 姜哲
 * @version
 */
@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ManagerService managerService;

    /**
     * 分页查询项目列表
     * 
     * @param projectSearchDto
     * @return
     */
    @RequiresPermissions("proj_list")
    @RequestMapping("/pageProject")
    public HashMap<String, Object> pageProject(
            @RequestBody ProjectSearchDto projectSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Manager manager = managerService.getLoginManager();
        Role role = manager.getRole();
        if(Arrays.asList(Constants.S_AGENCY).contains(role.getName())){
            projectSearchDto.setDistributorId(manager.getId());
        }else if(Arrays.asList(Constants.S_PROJ_MANAGER).contains(role.getName())){
            projectSearchDto.setProjectManagerId(manager.getId());
        }
        PageBounds<Project> pageBounds = projectService
                .selectByLimitPage(projectSearchDto);
        result.put("data", pageBounds);
        return result;
    }
    /**
     * 添加项目
     * @return
     */
    @RequiresPermissions("proj_add")
    @RequestMapping("/addProject")
    public HashMap<String, Object> addProject(
            @Valid @RequestBody Project project, BindingResult bindingResult) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Manager manager = managerService.getLoginManager();
        project.setDistributorId(manager.getId());
        projectService.addProject(project);
        return result;
    }

    /**
     * id查找项目
     * @return
     */
    @RequiresPermissions("proj_list")
    @RequestMapping("/getProject")
    public HashMap<String, Object> getProject(
            @Valid @NotNull(message = "项目ID不能为空") @RequestBody Integer projectId) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Project project = projectService.getProjectById(projectId);
        result.put("data", project);
        return result;
    }

    /**
     * 查找当前登录人的全部项目
     * @return
     */
    @RequiresPermissions("proj_list")
    @RequestMapping("/findAllProjectById")
    public HashMap<String, Object> findAllProjectById() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Manager manager = managerService.getLoginManager();
        ProjectSearchDto projectSearchDto = new ProjectSearchDto();
        projectSearchDto.setDistributorId(manager.getId());
        List<ProjectSelectDto> projects = projectService.selectAllProjectName(projectSearchDto);
        result.put("data", projects);
        return result;
    }

    /**
     * 删除项目
     * 
     * @param ids
     * @return
     */
    @RequiresPermissions("del_proj")
    @RequestMapping("/delProject")
    public HashMap<String, Object> delProject(
            @Valid @NotEmpty(message = "项目IDS不能为空") @RequestBody Integer[] ids) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        projectService.deleteProject(Arrays.asList(ids));
        return result;
    }
    
    @RequestMapping("/applyDelProject")
    public HashMap<String, Object> applyDelProject(@RequestBody List<Integer> ids){
        HashMap<String, Object> result = new HashMap<String, Object>();
        projectService.applyDelProject(ids);
        return result;
    }
    /**
     * 生成邀请码
     * @param id
     * @return
     */
    @RequiresPermissions("invite_code")
    @RequestMapping("/getCode")
    public HashMap<String, Object> getCode(@Valid @NotNull(message = "请至少选择一个!")@RequestBody Integer id) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        String code = ShareCodeUtil.toSerialCode(id);
        result.put("data", code);
        return result;
    }
    
    /**
     * 获取所有项目
     */
    @RequestMapping("/getAllProject") 
    public HashMap<String, Object> getAllProject(){
    	HashMap<String, Object> result = new HashMap<String, Object>();
    	List<Project> list = projectService.findAllProject();
    	result.put("data", list);
    	return result; 
    }
 
}
