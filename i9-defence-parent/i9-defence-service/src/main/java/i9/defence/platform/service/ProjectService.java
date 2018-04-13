package i9.defence.platform.service;

import java.util.List;

import i9.defence.platform.dao.vo.ProjectSearchDto;
import i9.defence.platform.dao.vo.ProjectSelectDto;
import i9.defence.platform.model.Project;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/**
 * 项目Service
 * @author 姜哲
 * @create 2018年1月8日
 */
public interface ProjectService {

	/**
     * 添加项目
     * @param project
     * @throws Exception
     */
    void addProject(Project project) throws BusinessException;
    
    /**
     * 更新项目开关状态
     * @param project
     * @throws Exception
     */
    void updateProject(Project project) throws BusinessException;
    
    /**
     * 更新项目的责任人和安全责任人等等
     * @param project
     * @throws Exception
     */
    void updateProject2(Project project) throws BusinessException;
    
    /**
     * 删除项目
     * @param kid
     * @throws Exception
     */
    void deleteProject(List<Integer> ids) throws BusinessException;
    
    /**
     * 根据ID获取项目
     * @param id
     * @return
     * @throws Exception
     */
    Project getProjectById(int id) throws BusinessException;
    
    /**
     * 获取全部的项目
     * @return
     * @throws Exception
     */
    List<Project> findAllProject() throws BusinessException;
    
    /**
     * 分页查询项目
     * @param projectSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<Project> selectByLimitPage(ProjectSearchDto projectSearchDto) throws BusinessException;
    
    /**
     * 条件查询项目
     * @param projectSearchDto
     * @return
     * @throws BusinessException
     */
    List<Project> selectProject(ProjectSearchDto projectSearchDto) throws BusinessException;
    /**
     * 获取全部的项目的主键ID和项目名称
     * @return
     * @throws BusinessException
     */
    List<ProjectSelectDto> selectAllProjectName(ProjectSearchDto projectSearchDto) throws BusinessException;

    /**
     * 向项目-设备表 增加  设备ID
     * @param kid
     * @throws Exception
     */
    void saveProjectEquipment(Integer projectId,List<Integer> equipmentIds) throws BusinessException;

    /**
     * 获取当前项目下的全部设备ID
     * @return
     * @throws Exception
     */
    List<Integer> selectAllEquipmentIds(Integer projectId) throws BusinessException;
    
    /**
     * 申请删除项目集合
    * @Title: applyDelProject 
    * @Description: TODO
    * @param ids
     * @return 
    * @throws BusinessException 
     */
    public String  applyDelProject(List<Integer> ids) throws BusinessException;
	
}
