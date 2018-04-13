package i9.defence.platform.api.controller;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.api.components.ProjcetMonitorComponent;
import i9.defence.platform.dao.vo.ProjectGetDto;
import i9.defence.platform.dao.vo.ProjectSearchDto;
import i9.defence.platform.dao.vo.ProjectSelectDto;
import i9.defence.platform.model.Client;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.Project;
import i9.defence.platform.model.Role;
import i9.defence.platform.service.ClientService;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.service.ProjectService;
import i9.defence.platform.utils.Constants;
import i9.defence.platform.utils.PageBounds;
import i9.defence.platform.utils.ShareCodeUtil;

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
    @Autowired
    private ClientService clientService;

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
     * 项目下拉框
     * @param projectSearchDto
     * @return
     */
    @RequestMapping("/selectProject")
    public HashMap<String, Object> selectProject(
            @RequestBody ProjectSearchDto projectSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Manager manager = managerService.getLoginManager();
        Role role = manager.getRole();
        if(Arrays.asList(Constants.S_AGENCY).contains(role.getName())){
            projectSearchDto.setDistributorId(manager.getId());
        }else if(Arrays.asList(Constants.S_PROJ_MANAGER).contains(role.getName())){
            projectSearchDto.setProjectManagerId(manager.getId());
        }
        List<Project> projectList = projectService.selectProject(projectSearchDto);
        JSONArray projectArray = new JSONArray();
        for(Project project:projectList) {
        	JSONObject projectObject = new ProjcetMonitorComponent().setProject(project).build2();
        	projectArray.add(projectObject);
        }
        result.put("data", projectArray);
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
        if(project.getDistributorId() == null) {
        	Manager manager = managerService.getLoginManager();
            project.setDistributorId(manager.getId());
        }
        projectService.addProject(project);
        return result;
    }
    
    /**
     * 修改项目
     * @return
     */
    @RequiresPermissions("proj_add")
    @RequestMapping("/updateProject")
    public HashMap<String, Object> updateProject(@RequestBody Project project) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        projectService.updateProject(project);
        return result;
    }
    
    @RequestMapping("/getClientByDistributorId")
    public HashMap<String, Object> getClientByDistributorId() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Manager manager = managerService.getLoginManager();
        //负责人  一对多  查询此经销商下 建立的全部client
        List<Client> clientList = clientService.selectByCreateId(manager.getId());
        result.put("clientList", clientList);
        return result;
    }

    /**
     * id查找项目
     * @return
     */
    @RequiresPermissions("proj_list")
    @RequestMapping("/getProject")
    public HashMap<String, Object> getProject(@RequestBody ProjectGetDto projectGetDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Project project = projectService.getProjectById(projectGetDto.getProjectId());
        //负责人  一对多  查询此经销商下 建立的全部client
        List<Client> clientList = clientService.selectByCreateId(projectGetDto.getDistributorId());
        //安全责任人  一对多 查询全部和此工程相关的人（当初输入项目邀请码的哪些注册用户们）
        List<Manager> safeList = managerService.selectSafeZeroByProjectId(projectGetDto.getProjectId());
        result.put("project", project);
        result.put("clientList", clientList);
        result.put("safeList", safeList);
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
        String msg = projectService.applyDelProject(ids);
        result.put("msg", msg); 
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
