package i9.defence.platform.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.ApplyDao;
import i9.defence.platform.dao.EquipmentDao;
import i9.defence.platform.dao.ManagerDao;
import i9.defence.platform.dao.vo.EquipmentSearchDto;
import i9.defence.platform.model.Apply;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.Passageway;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.Constants;
import i9.defence.platform.utils.PageBounds;
/**
 * 项目类别ServiceImpl
 * @author gbq
 * @create 2018年
 */
@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
	
	@Autowired
	private EquipmentDao equipmentDao;
	@Autowired
	private ManagerDao managerDao;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private ApplyDao applyDao; 
	
	@Override
	public PageBounds<Equipment> selectByLimitPage(EquipmentSearchDto equipmentSearchDto)
			throws BusinessException {
		try {
			return equipmentDao.selectByLimitPage(equipmentSearchDto, equipmentSearchDto.getCurrentPage(), equipmentSearchDto.getPageSize());
		} catch (Exception e) {
			throw new BusinessException("分页项目类别类别查询失败",e.getMessage());
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
			throw new BusinessException("添加项目类别类别失败",e.getMessage());
		}
	}

	@Override
	public void updateEquipment(Equipment equipment) throws BusinessException {
		// TODO Auto-generated method stub
		try {
			equipmentDao.updateEquipment(equipment);
		} catch (Exception e) {
			throw new BusinessException("更新项目类别失败",e.getMessage());
		}
	}

	@Override
	public Equipment getEquipmentById(int id) throws BusinessException {
		try {
			return equipmentDao.getEquipmentById(id);
		} catch (Exception e) {
			throw new BusinessException("查询项目类别失败",e.getMessage());
		}
	}

	@Override
	public String  applyDelEquipment(List<Integer> ids) throws BusinessException {
		try { 
			//1.获得当前登录用户 
			Manager manager = managerService.getLoginManager();
			//1.1接受前台所要删除的设备List<Integer> ids
			List<Equipment> listEquipment = equipmentDao.getEquipmentByIds(ids);
			//1.2储存更新后的设备
			ArrayList<Equipment> listNewEquip = new ArrayList<>(); 
			//1.3创建一个List包装所有apply申请
			List<Apply> listApply = new ArrayList<>();
			//1.4查询此经销商是否有父级
			Integer parentById = managerDao.selectParentById(manager.getId()); 
			//1.5判断是否被申请过
			if(!Arrays.asList(Constants.S_NET_MANAGER).contains(manager.getType())) {
				//查询此人提交的删除项目申请在数据库中是否存在
				Integer number = applyDao.selectEquipmentCount(ids);
				if (number > 0) {
					//有人提交 过了
					throw new BusinessException("您选中的数据已经被申请过了");
				}else {
					//2. 判断当前用户的身份（type：0 网站用户；type：1 经销商；type：2.项目管理员）
					if(manager!=null) {
						//2.1 如果为网站用户，直接删除。
						if(Arrays.asList(Constants.S_NET_MANAGER).contains(manager.getType())) {
							//真删
							this.deleteEquipment(ids);
							return "成功";
							//假删
							/*for(Equipment equipment:listEquipment){
						Apply apply = new Apply();
						apply.setType(1);
						apply.setState(0);
						apply.setApplyId(manager.getId()); 
						apply.setApplyDate(new Date());
						apply.setConductorId(null);
						apply.setEquipmentId(equipment.getId());
						listApply.add(apply);
					}*/
						}					
						//2.2如果为经销商
						else if(Arrays.asList(Constants.S_AGENCY_TYPE).contains(manager.getType())) {
							//2.2.1若为顶级经销商，处理人为null，交给管理员处理。
							if(null==parentById){
								for(Equipment equipment:listEquipment){
									Apply apply = new Apply();
									apply.setType(1);
									apply.setState(0);
									apply.setApplyId(manager.getId()); 
									apply.setApplyDate(new Date());
									apply.setConductorId(null);
									apply.setEquipmentId(equipment.getId());
									equipment.setDelStatus(0);
									listApply.add(apply);
									listNewEquip.add(equipment);
								}
								//3.插入到apply表中。
								applyDao.insertEquipmentApplys(listApply); 	
								equipmentDao.updateEquipments(listNewEquip);
								return "您的申请已提交，请等待您的上级处理";
								//2.2.2此经销商有父级的话交给上级经销商处理;
							}else {
								for(Equipment equipment:listEquipment){
									Apply apply = new Apply();
									apply.setType(1);
									apply.setState(0);
									apply.setApplyId(manager.getId()); 
									apply.setApplyDate(new Date());
									apply.setConductorId(parentById);
									apply.setEquipmentId(equipment.getId());
									equipment.setDelStatus(0);
									listNewEquip.add(equipment);
									listApply.add(apply);
								} 
								//3.插入到apply表中。
								applyDao.insertEquipmentApplys(listApply); 	
								equipmentDao.updateEquipments(listNewEquip);
								return "您的申请已提交，请等待您的上级处理";
							}
						}
						//2.3如果项目管理员,交给上级经销商处理。
						else if(Arrays.asList(Constants.S__Project_Type).contains(manager.getType())) {
							//2.3.1添加到apply记录。
							for(Equipment equipment:listEquipment){
								Apply apply = new Apply();
								apply.setType(1);
								apply.setState(0);
								apply.setApplyId(manager.getId()); 
								apply.setApplyDate(new Date());
								apply.setConductorId(equipment.getProject().getDistributorId());
								apply.setEquipmentId(equipment.getId());
								equipment.setDelStatus(0);
								listNewEquip.add(equipment);
								listApply.add(apply);
							}
							//3.插入到apply表中。
							applyDao.insertEquipmentApplys(listApply); 	
							equipmentDao.updateEquipments(listNewEquip);
							return "您的申请已提交，请等待您的上级处理";
						}
					}
				}
			}	
				}
		catch (BusinessException e) {
				throw new BusinessException(e.getErrorMessage());
		} catch (Exception e) {
			   throw new BusinessException("申请删除设备类别失败",e.getMessage());
		}
		return null; 
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
	public List<Passageway> selectPassagewayByEid(String systemId) throws BusinessException {
		try {
			return equipmentDao.selectPassagewayByEid(systemId);
		} catch (Exception e) {
			throw new BusinessException("根据设备id查询通道失败",e.getMessage());
		}
	}

	@Override
	public void insertPassageWay(Passageway passageway) throws BusinessException {
		try {
			equipmentDao.insertPassageWay(passageway);
		} catch (Exception e) { 
				throw new BusinessException("新增通道失败",e.getMessage());
		}
		
	}

}

