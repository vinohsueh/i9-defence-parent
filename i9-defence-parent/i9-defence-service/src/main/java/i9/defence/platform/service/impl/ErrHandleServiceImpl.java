package i9.defence.platform.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.EquipmentDao;
import i9.defence.platform.dao.ErrHandleDao;
import i9.defence.platform.dao.vo.ErrHandleSearchDto;
import i9.defence.platform.dao.vo.ErrHandleUnifiedDto;
import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.model.ErrHandle;
import i9.defence.platform.model.Manager;
import i9.defence.platform.service.ErrHandleService;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

@Service
@Transactional
public class ErrHandleServiceImpl implements ErrHandleService{

	@Autowired
	private ErrHandleDao errHandleDao;
	
	@Autowired
	private EquipmentDao equipmentDao;
	
	@Autowired
	private ManagerService managerService;
	
	@Override
	public void handlingErrors(ErrHandleUnifiedDto errHandleUnifiedDto) throws BusinessException {
		try {
			//当前登录人
			Manager manager = managerService.getLoginManager();
			//封装所有的历史记录
			List<ErrHandle> errHandles= new ArrayList<ErrHandle>();
			//封装所有的报警deviceId
			List<String> deviceIdsWarning=new ArrayList<String>(); 
			//封装所有的隐患deviceId
			List<String> deviceIdsHidden=new ArrayList<String>(); 
			//封装所有的离线deviceId
			List<Integer> EquipIdsOffLine=new ArrayList<Integer>(); 
			//根据ids获取到所有
			List<HiddenDangerDto> equipProblems = equipmentDao.selectHiddenDangerByIds(errHandleUnifiedDto.getEqIds());
			for(HiddenDangerDto hiddenDangerDto:equipProblems) {
				ErrHandle errHandle = new ErrHandle();
				errHandle.setHandleManagerId(manager.getId());
				errHandle.setHandleDate(new Date());
				errHandle.setHandleState(1);
				errHandle.setEqDeviceId(hiddenDangerDto.getDeviceId());
				errHandle.setEqAddRess(hiddenDangerDto.getEquipmentPosition());
				errHandle.setHandleCon(errHandleUnifiedDto.getHandleCon());
				//离线
				if(0 ==hiddenDangerDto.getStatus()) {
					errHandle.setType(1); 
					EquipIdsOffLine.add(errHandle.getId());
				}else{
					//报警
					if(1 ==hiddenDangerDto.getRemainAlert()) {
						errHandle.setType(2); 
						deviceIdsWarning.add(hiddenDangerDto.getDeviceId());
				    //隐患
					}else if(2 ==hiddenDangerDto.getRemainAlert()){
						errHandle.setType(3); 
						deviceIdsHidden.add(hiddenDangerDto.getDeviceId());
					}
				}
				errHandles.add(errHandle);
			}
			errHandleDao.addErrHandle(errHandles);
			if(0 !=EquipIdsOffLine.size()) {
				equipmentDao.updateEquipStatusByIds(EquipIdsOffLine);
			}
			if(0!= deviceIdsWarning.size()) {
				errHandleDao.updateHandleFault(deviceIdsWarning);
			}
			if(0!= deviceIdsHidden.size()) {
				errHandleDao.updateHandleHidden(deviceIdsHidden);
			}
			/*//设备
			Equipment equipment = equipmentDao.getEquipmentById(errHandleUnifiedDto.getEqId());
			//设备故障类型（1 故障）（2 报警）（3 隐患）
			Integer eqType = errHandleUnifiedDto.getEqType();
			if(eqType == 1) {
				errHandleDao.updateHandleFault(equipment.getDeviceId());
			}else if(eqType == 2) {
				errHandleDao.updateHandlePolice(equipment.getDeviceId());
			}else{
				errHandleDao.updateHandleHidden(equipment.getDeviceId());
			}
			ErrHandle errHandle = new ErrHandle();
			errHandle.setHandleManagerId(manager.getId());
			errHandle.setHandleDate(new Date());
			errHandle.setHandleState(1);
			errHandle.setEqDeviceId(equipment.getDeviceId());
			errHandle.setEqAddRess(equipment.getEquipmentPosition());
			errHandle.setType(eqType);
			errHandle.setHandleCon(errHandleUnifiedDto.getHandleCon());
			errHandleDao.addErrHandle(errHandle);*/
		} catch (Exception e) {
			throw new BusinessException("批量处理设备错误失败", e.getMessage());
		}		
	}

	@Override
	public void deleteErrHandle(List<Integer> ids) throws BusinessException {
		try {
			errHandleDao.deleteErrHandle(ids);
		} catch (Exception e) {
			throw new BusinessException("批量删除设备错误处理记录失败", e.getMessage());
		}
		
	}

	@Override
	public ErrHandle getErrHandleById(int id) throws BusinessException {
		try {
			return errHandleDao.getErrHandleById(id);
		} catch (Exception e) {
			throw new BusinessException("根据ID查询设备错误处理记录失败", e.getMessage());
		}
	}

	@Override
	public PageBounds<ErrHandle> selectByLimitPage(ErrHandleSearchDto errHandleSearchDto) throws BusinessException {
		try {
			//当前登录人
			Manager manager = managerService.getLoginManager();
			errHandleSearchDto.setManagerId(manager.getId());
			return errHandleDao.selectByLimitPage(errHandleSearchDto);
		} catch (Exception e) {
			throw new BusinessException("分页查询设备错误处理记录失败", e.getMessage());
		}
	}

	@Override
	public void handleErrorDevice(List<String> list) throws BusinessException {
		try {
			//处理故障
			errHandleDao.updateBatchHandleFault(list);
			//处理报警
			errHandleDao.updateBatchHandlePolice(list);
			//处理隐患
			errHandleDao.updateBatchHandleHidden(list);
			//添加记录
			errHandleDao.addBatchHandle(list);
		} catch (Exception e) {
			throw new BusinessException("自动处理失败", e.getMessage());
		}
		
	}

	@Override
	public void errHandleEdit(ErrHandleUnifiedDto errHandleUnifiedDto) throws BusinessException {
		try {
				//当前登录人
				Manager manager = managerService.getLoginManager();
				//封装所有的历史记录
				List<ErrHandle> errHandles= new ArrayList<ErrHandle>();
				//封装所有的报警deviceId
				List<String> deviceIdsWarning=new ArrayList<String>(); 
				//封装所有的隐患deviceId
				List<String> deviceIdsHidden=new ArrayList<String>(); 
				//根据ids获取到所有
				List<HiddenDangerDto> equipProblems = equipmentDao.selectHiddenDangerByIds(errHandleUnifiedDto.getEqIds());
				for(HiddenDangerDto hiddenDangerDto:equipProblems) {
					ErrHandle errHandle = new ErrHandle();
					errHandle.setHandleManagerId(manager.getId());
					errHandle.setHandleDate(new Date());
					errHandle.setHandleState(1);
					errHandle.setEqDeviceId(hiddenDangerDto.getDeviceId());
					errHandle.setHandleCon(errHandleUnifiedDto.getHandleCon());
					if(hiddenDangerDto.getWarningCount()>0) {
						deviceIdsWarning.add(hiddenDangerDto.getDeviceId());
					}else if(hiddenDangerDto.getHiddeCount()>0) {
						deviceIdsHidden.add(hiddenDangerDto.getDeviceId());
					}
					errHandles.add(errHandle);
				}
				errHandleDao.updateErrHandles(errHandles);
				if(0!= deviceIdsWarning.size()) {
					errHandleDao.updateHandleFault(deviceIdsWarning);
				}
				if(0!= deviceIdsHidden.size()) {
					errHandleDao.updateHandleHidden(deviceIdsHidden);
				}
		} catch (Exception e) {
			throw new BusinessException("处理失败", e.getMessage());
		}
		
	}



}
