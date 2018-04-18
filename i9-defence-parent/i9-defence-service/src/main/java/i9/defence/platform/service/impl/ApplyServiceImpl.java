package i9.defence.platform.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i9.defence.platform.dao.ApplyDao;
import i9.defence.platform.dao.vo.ApplyDto;
import i9.defence.platform.model.Apply;
import i9.defence.platform.model.ApplyExample;
import i9.defence.platform.model.ApplyExample.Criteria;
import i9.defence.platform.model.Manager;
import i9.defence.platform.service.ApplyService;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.service.ProjectService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.Constants;
import i9.defence.platform.utils.PageBounds;

/**
 * 申请表ServiceImpl
 * 
 * @ClassName: ApplyServiceImpl
 * @Description: TODO
 * @author: luobo
 * @date: 2018年1月25日 上午10:36:16
 */
@Service
public class ApplyServiceImpl implements ApplyService {

	@Autowired
	private ApplyDao applyDao;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private EquipmentService equipmentService;

	@Override
	public PageBounds<Apply> selectByLimitPage(ApplyExample applyExample, int currectPage, int pageSize,Integer destriId)
			throws BusinessException { 
		try {
			// 1.获得当前登录用户
			Manager manager = managerService.getLoginManager();
			Criteria criteria = applyExample.createCriteria();
			// 1.1若为网站用户 (type=0),则全部显示
			if (Arrays.asList(Constants.S_NET_MANAGER).contains(manager.getType())) {
				if(destriId!=null && destriId==0) {
					PageBounds<Apply> pageBounds = applyDao.selectByLimitPage(applyExample, currectPage, pageSize);
					return pageBounds; 
				}else {
					ApplyDto applyDto = new ApplyDto(); 
					applyDto.setDestriId(destriId);
					PageBounds<Apply> pageBounds = applyDao.selectByLimitPage2(applyDto, currectPage, pageSize);
					return pageBounds;
				}
			} 
			// 1.2若为经销商和管理员则根据条件显示
			else if(Arrays.asList(Constants.S_ACCOUNT).contains(manager.getType())){
				criteria.andConductorIdEqualTo(manager.getId());
				PageBounds<Apply> pageBounds = applyDao.selectByLimitPage(applyExample, currectPage, pageSize);
				return pageBounds;
			}
		} catch (Exception e) {
			throw new BusinessException("分页查询删除设备，项目申请表失败", e.getMessage());
		}
		return null;  
	}

	/**
	 * 删除申请
	 */
	@Override
	public void delApply(List<Integer> ids) throws BusinessException {
		try {
			// 1.1建一个listApply集合
			//ArrayList<Apply> listApply = new ArrayList<Apply>();
			// 1.2新建一个listEquipmentId集合
			List<Integer> listEquipmentId = new ArrayList<Integer>();
			// 1.3新建一个listProjectId集合
			List<Integer> listProjectId = new ArrayList<Integer>();
			// 1.4获得当前登录用户
			Manager loginManager = managerService.getLoginManager();
			// 1.5根据ids获得apply集合
			List<Apply> listApply = applyDao.selectApplyByids(ids);
			// 如果当前登录用户为网站用户
			if(Arrays.asList(Constants.S_NET_MANAGER).contains(loginManager.getType())) {
				for (Apply apply : listApply) {
					// 包装Apply的处理时间,处理状态
					/*apply.setConductDate(new Date());
					apply.setConductorId(loginManager.getId()); 
					apply.setState(1);
					listApply.add(apply);*/
					// 判断删除设备还是项目
					if (apply.getEquipmentId() == null) {
						listProjectId.add(apply.getProjectId());
					} else {
						listEquipmentId.add(apply.getEquipmentId());
					}
				} 
			}//如果当前登录用户为经销商和网站管理员
			else if (Arrays.asList(Constants.S_ACCOUNT).contains(loginManager.getType())) {
				for (Apply apply : listApply) {
					// 包装Apply的处理时间,处理状态
					/*apply.setConductDate(new Date());
					apply.setState(1);
					listApply.add(apply);*/
					// 判断删除设备还是项目
					if (apply.getEquipmentId() == null) {
						listProjectId.add(apply.getProjectId());
					} else {
						listEquipmentId.add(apply.getEquipmentId());
					}
				} 
			}
			// 执行Apply更新操作
			//applyDao.updateApplys(listApply);
			// 2.删除所关联的设备/项目
			if (listEquipmentId != null && listEquipmentId.size() != 0) {
				equipmentService.deleteEquipment(listEquipmentId);
			} else if(listProjectId !=null && listProjectId.size() !=0) {
				projectService.deleteProject(listProjectId);
			}
			// 3.删除申请表中的记录
			 applyDao.delApply(ids);
		} catch (Exception e) {
			throw new BusinessException("通过删除申请设备，项目申请失败", e.getMessage());
		}
	}

	/**
	 * 根据id查询
	 */
	@Override
	public Apply getApplyById(int id) throws BusinessException {
		try {
			return applyDao.getApplyById(id);
		} catch (Exception e) {
			throw new BusinessException("通过id查询，项目申请失败", e.getMessage());
		}
	}

	/**
	 * 查询部分申请
	 */
	@Override
	public List<Apply> selectPartState(Integer state) throws BusinessException {
		try {
			return applyDao.selectPartState(state);
		} catch (Exception e) {
			throw new BusinessException("查询部分项目申请失败", e.getMessage());
		}
	}

}
