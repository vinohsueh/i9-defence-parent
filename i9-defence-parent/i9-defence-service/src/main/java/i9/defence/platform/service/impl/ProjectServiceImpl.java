package i9.defence.platform.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.ApplyDao;
import i9.defence.platform.dao.ManagerDao;
import i9.defence.platform.dao.ProjectDao;
import i9.defence.platform.dao.vo.MonthDataDto;
import i9.defence.platform.dao.vo.ProjectSearchDto;
import i9.defence.platform.dao.vo.ProjectSelectDto;
import i9.defence.platform.model.Apply;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.Project;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.service.ProjectService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.Constants;
import i9.defence.platform.utils.PageBounds;

/**
 * 项目ServiceImpl
 * 
 * @author 姜哲
 * @create 2018年1月8日
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private ManagerDao managerDao;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private ApplyDao applyDao;

	@Override
	public void addProject(Project project) throws BusinessException {
		try {
			if (project.getId() != null) {
				this.updateProject2(project);
			} else {
				project.setProjectDate(new Date());
				projectDao.addProject(project);
				if(project.getClientIds2() != null  && project.getClientIds2().size() > 0){
					//再增加 已有的 责任人
					projectDao.insertIntoClientByProjectId(project.getId(), project.getClientIds2());
				}
			}
		} catch (Exception e) {
			throw new BusinessException("添加项目失败", e.getMessage());
		}

	}
	
	@Override
	public void updateProject2(Project project) throws BusinessException {
		try {
			//先删除 已有的 责任人
			projectDao.deleteClientByProjectId(project.getId());
			//先修改 安全责任人的 safe = 0
			projectDao.updateSafeZeroByProjectId(project.getId());
			if(project.getClientIds2() != null  && project.getClientIds2().size() > 0){
				//再增加 已有的 责任人
				projectDao.insertIntoClientByProjectId(project.getId(), project.getClientIds2());
			}
			if(project.getSafeIds2() != null && project.getSafeIds2().size() > 0) {
				//再修改 需要安全责任人的 safe = 1
				projectDao.updateSafeOneByProjectId(project.getId(), project.getSafeIds2());
			}
			projectDao.updateProject(project);
		} catch (Exception e) {
			throw new BusinessException("更新项目责任人、安全责任人等失败", e.getMessage());
		}
	}

	@Override
	public void updateProject(Project project) throws BusinessException {
		try {
			Integer status = project.getProjectState();
			Integer sendStatus = project.getSendStatus();
			if(null !=status) {
				if (status == 0) {
					project.setProjectState(1);
				} else{
					project.setProjectState(0);
				}
			}
			if(null !=sendStatus) {
				if(sendStatus ==0) {
					project.setSendStatus(1);
				}else {
					project.setSendStatus(0); 
				}
			}
			projectDao.updateProject(project);
		} catch (Exception e) {
			throw new BusinessException("更新项目失败开关状态", e.getMessage());
		}

	}

	@Override
	public void deleteProject(List<Integer> ids) throws BusinessException {
		try {
			projectDao.deleteProject(ids);
		} catch (Exception e) {
			throw new BusinessException("删除项目失败", e.getMessage());
		}

	}

	@Override
	public Project getProjectById(int id) throws BusinessException {
		try {
			return projectDao.getProjectById(id);
		} catch (Exception e) {
			throw new BusinessException("查询项目失败", e.getMessage());
		}
	}

	@Override
	public List<Project> findAllProject() throws BusinessException {
		try {
			return projectDao.findAllProject();
		} catch (Exception e) {
			throw new BusinessException("查询全部项目失败", e.getMessage());
		}
	}

	@Override
	public PageBounds<Project> selectByLimitPage(ProjectSearchDto projectSearchDto) throws BusinessException {
		try {
			return projectDao.selectByLimitPage(projectSearchDto, projectSearchDto.getCurrentPage(),
					projectSearchDto.getPageSize());
		} catch (Exception e) {
			throw new BusinessException("分页查询项目失败", e.getMessage());
		}
	}
	
	@Override
	public List<Project> selectProject(ProjectSearchDto projectSearchDto) throws BusinessException {
		try {
			return projectDao.selectProject(projectSearchDto);
		} catch (Exception e) {
			throw new BusinessException("分页查询项目失败", e.getMessage());
		}
	}
	
	@Override
	public List<ProjectSelectDto> selectAllProjectName(ProjectSearchDto projectSearchDto) throws BusinessException {
		try {
			return projectDao.selectAllProjectName(projectSearchDto);
		} catch (Exception e) {
			throw new BusinessException("查询全部项目ID和名称失败", e.getMessage());
		}
	}

	@Override
	public void saveProjectEquipment(Integer projectId, List<Integer> equipmentIds) throws BusinessException {
		try {
			projectDao.saveProjectEquipment(projectId, equipmentIds);
		} catch (Exception e) {
			throw new BusinessException("增加项目和设备关系ID失败", e.getMessage());
		}

	}

	@Override
	public List<Integer> selectAllEquipmentIds(Integer projectId) throws BusinessException {
		try {
			return projectDao.selectAllEquipmentIds(projectId);
		} catch (Exception e) {
			throw new BusinessException("获取当前项目下的全部设备ID", e.getMessage());
		}
	}

	@Override
	public String applyDelProject(List<Integer> ids) throws BusinessException {
		try {
			// 1.获得当前登录用户
			Manager manager = managerService.getLoginManager();
			// 1.1接受前台所要删除的List<Integer> ids
			List<Project> listProjects = projectDao.getProjectByIds(ids);
			// 1.2创建一个List包装所有申请
			List<Apply> listApplys = new ArrayList<>();
			// 1.3查询此经销商是否有父级
			Integer parentById = managerDao.selectParentById(manager.getId());
			// 1.4判断是否被申请过
			if (!Arrays.asList(Constants.S_NET_MANAGER).contains(manager.getType())) {
				// 查询此人提交的删除项目申请在数据库中是否存在
				Integer number = applyDao.selectProjectCount(ids);
				if (number >0) {
					// 有人提交 过了
					throw new BusinessException("您选中的数据已经被申请过了");
				} else {
					// 2. 判断当前用户的身份（type：0 网站用户；type：1 经销商；type：2.项目管理员）
					if (manager != null) {
						// 2.2如果为经销商
						 if (Arrays.asList(Constants.S_AGENCY_TYPE).contains(manager.getType())) {
							// 2.2.1若为顶级经销商，处理人为null，交给管理员处理。
							if (null == parentById) {
								for (Project project : listProjects) {
									Apply apply = new Apply();
									apply.setType(0);
									apply.setState(0);
									apply.setApplyId(manager.getId());
									apply.setApplyDate(new Date());
									apply.setConductorId(null);
									apply.setProjectId(project.getId());
									listApplys.add(apply);
								}
								// 3.插入到apply表中。
								applyDao.insertProjectApplys(listApplys);
								return "您的申请已提交，请等待您的上级处理";
								// 2.2.2此经销商有父级的话交给上级经销商处理;
							} else {
								for (Project project : listProjects) {
									Apply apply = new Apply();
									apply.setType(0);
									apply.setState(0);
									apply.setApplyId(manager.getId());
									apply.setApplyDate(new Date());
									apply.setConductorId(parentById);
									apply.setProjectId(project.getId());
									listApplys.add(apply);
								}
								// 3.插入到apply表中。
								applyDao.insertProjectApplys(listApplys);
								return "您的申请已提交，请等待您的上级处理";
							}
						}
						// 2.3如果项目管理员,交给上级经销商处理。
						else if (Arrays.asList(Constants.S__Project_Type).contains(manager.getType())) {
							// 2.3.1添加到apply记录。
							for (Project project : listProjects) {
								Apply apply = new Apply();
								apply.setType(0);
								apply.setState(0);
								apply.setApplyId(manager.getId());
								apply.setApplyDate(new Date());
								apply.setConductorId(project.getDistributorId());
								apply.setProjectId(project.getId());
								listApplys.add(apply);
							}
							// 3.插入到apply表中。
							applyDao.insertProjectApplys(listApplys);
							return "您的申请已提交，请等待您的上级处理";
						}
					}
				} 
			}// 2.1 如果为网站用户，直接删除。
			else if (Arrays.asList(Constants.S_NET_MANAGER).contains(manager.getType())) {
				// 真删
				this.deleteProject(ids);
				return "删除成功";
				// 假删
				/*
				 * for(Project project:listProjects){ Apply apply = new Apply();
				 * apply.setType(0); apply.setState(0); apply.setApplyId(manager.getId());
				 * apply.setApplyDate(new Date()); apply.setConductorId(null);
				 * apply.setProjectId(project.getId()); listApplys.add(apply); }
				 */
			}
		} catch (BusinessException e) {
			throw new BusinessException(e.getErrorMessage());
		} catch (Exception e) {
			throw new BusinessException("删除项目类别失败", e.getMessage());
		}
		return null;
	}

	@Override
	public List<Project> findAllProjectIndex(ProjectSearchDto projectSearchDto) throws BusinessException {
		try {
			return projectDao.findAllProjectIndex(projectSearchDto);
		} catch (Exception e) {
			throw new BusinessException("后台首页查询全部项目失败", e.getMessage());
		}
	}

	@Override
	public List<Integer> selectIdsByMonthDataDto(MonthDataDto monthDataDto) throws BusinessException {
		try {
			return projectDao.selectIdsByMonthDataDto(monthDataDto);
		} catch (Exception e) {
			throw new BusinessException("根据 项目的 省市区  查询项目ID失败", e.getMessage());
		}
	}

	@Override
	public List<Project> findProjectName()throws BusinessException{
		try {
		  //获取登录人
            Manager loginManager = managerService.getLoginManager();
          //如果为网站用户显示全部（type=0）
            if(Arrays.asList(Constants.S_NET_MANAGER).contains(loginManager.getType())) {
                return projectDao.findProjectName();
            }
            //如果为经销商和管理员
            else if (Arrays.asList(Constants.S_AGENCY_TYPE).contains(loginManager.getType())) {
                return projectDao.findProjectName2(loginManager.getId());
            }else if (Arrays.asList(Constants.S__Project_Type).contains(loginManager.getType())){
                //如果是项目管理员
                return projectDao.findProjectName3(loginManager.getId());
            }
		} catch (Exception e) {
			throw new BusinessException("查询失败", e.getMessage());
		}
		return null;
	}

	@Override
	public List<Integer> selectWarningProjectIds(ProjectSearchDto projectSearchDto) {
		try {
			return projectDao.selectWarningProjectIds(projectSearchDto);
		} catch (Exception e) {
			throw new BusinessException("查询失败", e.getMessage());
		}
	}

}
