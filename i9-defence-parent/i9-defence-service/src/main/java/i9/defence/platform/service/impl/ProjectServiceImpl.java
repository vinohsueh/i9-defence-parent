package i9.defence.platform.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.ProjectDao;
import i9.defence.platform.dao.vo.ProjectSearchDto;
import i9.defence.platform.dao.vo.ProjectSelectDto;
import i9.defence.platform.model.Project;
import i9.defence.platform.service.ProjectService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/**
 * 项目ServiceImpl
 * @author 姜哲
 * @create 2018年1月8日
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjectDao projectDao;
	
	@Override
	public void addProject(Project project) throws BusinessException {
		try {
			if(project.getId()!=null) {
				projectDao.updateProject(project);
			}else {
				project.setProjectDate(new Date());
				projectDao.addProject(project);
			}
		} catch (Exception e) {
			throw new BusinessException("添加项目失败",e.getMessage());
		}
		
	}

	@Override
	public void updateProject(Project project) throws BusinessException {
		try {
			projectDao.updateProject(project);
		} catch (Exception e) {
			throw new BusinessException("更新项目失败",e.getMessage());
		}
		
	}

	@Override
	public void deleteProject(List<Integer> ids) throws BusinessException {
		try {
			projectDao.deleteProject(ids);
		} catch (Exception e) {
			throw new BusinessException("删除项目失败",e.getMessage());
		}
		
	}

	@Override
	public Project getProjectById(int id) throws BusinessException {
		try {
			return projectDao.getProjectById(id);
		} catch (Exception e) {
			throw new BusinessException("查询项目失败",e.getMessage());
		}
	}

	@Override
	public List<Project> findAllProject() throws BusinessException {
		try {
			return projectDao.findAllProject();
		} catch (Exception e) {
			throw new BusinessException("查询全部项目失败",e.getMessage());
		}
	}

	@Override
	public PageBounds<Project> selectByLimitPage(ProjectSearchDto projectSearchDto)
			throws BusinessException {
		try {
			return projectDao.selectByLimitPage(projectSearchDto, projectSearchDto.getCurrentPage(), projectSearchDto.getPageSize());
		} catch (Exception e) {
			throw new BusinessException("分页查询项目失败",e.getMessage());
		}
	}

	@Override
	public List<ProjectSelectDto> selectAllProjectName(ProjectSearchDto projectSearchDto) throws BusinessException {
		try {
			return projectDao.selectAllProjectName(projectSearchDto);
		} catch (Exception e) {
			throw new BusinessException("查询全部项目ID和名称失败",e.getMessage());
		}
	}

	@Override
	public void saveProjectEquipment(Integer projectId, List<Integer> equipmentIds) throws BusinessException {
		try {
			projectDao.saveProjectEquipment(projectId, equipmentIds);
		} catch (Exception e) {
			throw new BusinessException("增加项目和设备关系ID失败",e.getMessage());
		}
		
	}

	@Override
	public List<Integer> selectAllEquipmentIds(Integer projectId) throws BusinessException {
		try {
			return projectDao.selectAllEquipmentIds(projectId);
		} catch (Exception e) {
			throw new BusinessException("获取当前项目下的全部设备ID",e.getMessage());
		}
	}

}
