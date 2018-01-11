package i9.defence.platform.api.controller;

import java.util.Arrays;
import java.util.HashMap;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.dao.vo.ProjectSearchDto;
import i9.defence.platform.model.Project;
import i9.defence.platform.service.ProjectService;
import i9.defence.platform.utils.PageBounds;

/** 
 * 创建时间：2018年1月9日
 * @author  姜哲
 * @version  
 */
@RestController
@RequestMapping("project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	/**
     * 分页查询项目列表
     * @param projectSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
	@RequestMapping("/pageProject")
	public HashMap<String, Object> pageProject(@RequestBody ProjectSearchDto projectSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        PageBounds<Project> pageBounds = projectService.selectByLimitPage(projectSearchDto);
        result.put("data",pageBounds);
        return result;
    }
	/**
	    * 添加项目
	    * @param manager
	    * @return
	    */
	    @RequestMapping("/addProject")
	    public HashMap<String, Object> pageProject(@Valid @RequestBody Project project,BindingResult bindingResult) {
	        HashMap<String, Object> result = new HashMap<String, Object>();
	        projectService.addProject(project);
	        return result;
	    }
	    
	    /**
	     * id查找项目
	     * @param managerId
	     * @return
	     */
	    @RequestMapping("/getProject")
	    public HashMap<String, Object> getProject(@Valid @NotNull(message="项目ID不能为空") @RequestBody Integer projectId) {
	        HashMap<String, Object> result = new HashMap<String, Object>();
	        Project project = projectService.getProjectById(projectId);
	        result.put("data",project);
	        return result;
	    }
	    
	   /**
	    * 删除项目
	    * @param ids
	    * @return
	    */
	    @RequestMapping("/delProject")
	    public HashMap<String, Object> delProject(@Valid @NotEmpty(message="项目IDS不能为空") @RequestBody Integer[] ids) {
	        HashMap<String, Object> result = new HashMap<String, Object>();
	        projectService.deleteProject(Arrays.asList(ids));
	        return result;
	    }
}
