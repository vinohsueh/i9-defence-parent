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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
    @RequestMapping("/addProject")
    public HashMap<String, Object> addProject(
            @Valid @RequestBody Project project, BindingResult bindingResult) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        projectService.addProject(project);
        return result;
    }

    /**
     * id查找项目
     * @return
     */
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
    @RequestMapping("/delProject")
    public HashMap<String, Object> delProject(
            @Valid @NotEmpty(message = "项目IDS不能为空") @RequestBody Integer[] ids) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        projectService.deleteProject(Arrays.asList(ids));
        return result;
    }
}
