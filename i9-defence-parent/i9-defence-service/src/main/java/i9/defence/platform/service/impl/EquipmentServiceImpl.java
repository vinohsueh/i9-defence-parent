package i9.defence.platform.service.impl;

import i9.defence.platform.dao.ApplyDao;
import i9.defence.platform.dao.EquipmentDao;
import i9.defence.platform.dao.vo.EquipmentSearchDto;
import i9.defence.platform.model.Apply;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.Passageway;
import i9.defence.platform.model.Project;
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
import org.springframework.transaction.annotation.Transactional;
/**
 * 设备ServiceImpl
 * @author gbq
 * @create 2018年
 */
@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
	
	@Autowired
	private EquipmentDao equipmentDao;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private ApplyDao applyDao;
	@Autowired
	private ProjectService projectService;
	
	@Override
	public PageBounds<Equipment> selectByLimitPage(EquipmentSearchDto equipmentSearchDto)
			throws BusinessException {
		try {
			return equipmentDao.selectByLimitPage(equipmentSearchDto, equipmentSearchDto.getCurrentPage(), equipmentSearchDto.getPageSize());
		} catch (Exception e) {
			throw new BusinessException("分页设备类别查询失败",e.getMessage());
		}
	}

	@Override
	public void addEquipment(Equipment equipment) throws BusinessException {
		try {
			if(equipment.getId()!=null) {
				equipmentDao.updateEquipment(equipment);
			}else {
				equipment.setEquipmentDate(new Date());
				equipmentDao.addEquipment(equipment);
			}
		} catch (Exception e) {
			throw new BusinessException("添加设备类别失败",e.getMessage());
		}
	}

	@Override
	public void updateEquipment(Equipment equipment) throws BusinessException {
		// TODO Auto-generated method stub
		try {
			equipmentDao.updateEquipment(equipment);
		} catch (Exception e) {
			throw new BusinessException("更新设备失败",e.getMessage());
		}
	}

	@Override
	public Equipment getEquipmentById(int id) throws BusinessException {
		try {
			return equipmentDao.getEquipmentById(id);
		} catch (Exception e) {
			throw new BusinessException("查询设备失败",e.getMessage());
		}
	}

	@Override
	public void  applyDelEquipment(List<Integer> ids) throws BusinessException {
		try {
			//获得当前登录用户
			Manager manager = managerService.getLoginManager();
			//获得当前用户所关联项目
			Project project = projectService.getProjectById(manager.getProjectId());
			//接受前台所要删除的List<Integer> ids
			List<Equipment> listEquipment = equipmentDao.getEquipmentByIds(ids);
			//创建一个List包装所有申请
			List<Apply> listApply = new ArrayList<>(); 
			for(Equipment equipment:listEquipment){
				Apply apply = new Apply();
				apply.setType(1);
				apply.setState(0);
				apply.setApplyId(manager.getId()); 
				apply.setApplyDate(new Date());
				apply.setConductorId(project.getDistributorId());
				apply.setEquipmentId(equipment.getId());
				listApply.add(apply);
			}
			applyDao.insertEquipmentApplys(listApply); 
		} catch (Exception e) {
			   throw new BusinessException("申请删除设备类别失败",e.getMessage());
		}
	}

	@Override
	public void deleteEquipment(List<Integer> ids) throws BusinessException {
		try {
			  equipmentDao.deleteEquipment(ids);  
		} catch (Exception e) {
			 throw new BusinessException("删除设备类别失败",e.getMessage());
		}
	}

	@Override
	public List<Passageway> selectPassagewayByEid(Integer Id) throws BusinessException {
		try {
			return equipmentDao.selectPassagewayByEid(Id);
		} catch (Exception e) {
			throw new BusinessException("根据设备id查询通道失败",e.getMessage());
		}
	}

	@Override
	public void InsertPassageWay(Passageway passageway) throws BusinessException {
		try {
			equipmentDao.InsertPassageWay(passageway);
		} catch (Exception e) { 
				throw new BusinessException("新增通道失败",e.getMessage());
		}
		
	}

}

