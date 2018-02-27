package i9.defence.platform.service.impl;

import i9.defence.platform.dao.ApplyDao;
import i9.defence.platform.model.Apply;
import i9.defence.platform.model.ApplyExample;
import i9.defence.platform.model.ApplyExample.Criteria;
import i9.defence.platform.model.Manager;
import i9.defence.platform.service.ApplyService;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.service.ProjectService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 申请表ServiceImpl
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
	public PageBounds<Apply> selectByLimitPage(ApplyExample applyExample,
			int currectPage, int pageSize,Integer state) throws BusinessException {
		try { 
			//获得当前登录用户
			Manager manager = managerService.getLoginManager();
			Criteria criteria = applyExample.createCriteria();
			criteria.andConductorIdEqualTo(manager.getId());
			if(null != state){
				criteria.andStateEqualTo(state);
			}
			//创建一个当前登录人管理的列表集合
			//List<Apply> listLogin = new ArrayList<Apply>(); 
			//获得当前所有记录List集合
			PageBounds<Apply> pageBounds = applyDao.selectByLimitPage(applyExample, currectPage, pageSize);
			List<Apply> listAll =pageBounds.getPageList();
			//把当前登录人所管理的列表全部放到listLogin集合中
			/*for(Apply apply:listAll){
				if(null==state){
					if(apply.getConductorId()!=null){	
						if(manager.getId()==apply.getConductorId()){
							listLogin.add(apply);
						}
					}
				}else{
					if(apply.getState()==state) {
						if(apply.getConductorId()!=null){	
							if(manager.getId()==apply.getConductorId()){
								listLogin.add(apply);
							}
						}
					}
				}
			}*/
			pageBounds.setPageList(listAll); 
			return pageBounds;
		} catch (Exception e) {
	    	 throw new BusinessException("分页查询删除设备，项目申请表失败",e.getMessage());
		}
	}
		
	
	/**
	 * 删除申请
	 */
	@Override
	public void delApply(List<Integer> ids) throws BusinessException {
		try {
			//1.建一个listApply集合
			ArrayList<Apply> listApply = new ArrayList<Apply>();
			List<Integer> listEquipmentId = new ArrayList<Integer>();
			List<Integer> listProjectId = new ArrayList<Integer>();
			for(Integer id:ids){
				//包装Apply的处理时间,处理状态
				Apply apply = this.getApplyById(id);
				apply.setConductDate(new Date()); 
				apply.setState(1); 
				listApply.add(apply);
				//判断删除设备还是项目
				if(apply.getEquipmentId()==null){
					listProjectId.add(apply.getProjectId());
				}else{
					listEquipmentId.add(apply.getEquipmentId()); 
				}
			}
			//执行Apply更新操作
			applyDao.updateApplys(listApply);
			//2.删除所关联的设备/项目
			if(listEquipmentId!=null&&listEquipmentId.size()!=0){
				equipmentService.deleteEquipment(listEquipmentId);
			}else{
				projectService.deleteProject(listProjectId); 
			}
			//3.删除申请表中的记录
//			applyDao.delApply(ids);
		} catch (Exception e) {
			throw new BusinessException("通过删除申请设备，项目申请失败",e.getMessage());
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
			throw new BusinessException("通过id查询，项目申请失败",e.getMessage());
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
			throw new BusinessException("查询部分项目申请失败",e.getMessage());
		}
	}

}
