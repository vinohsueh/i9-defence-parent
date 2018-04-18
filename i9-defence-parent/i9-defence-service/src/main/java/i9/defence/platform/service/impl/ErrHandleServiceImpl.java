package i9.defence.platform.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.EquipmentDao;
import i9.defence.platform.dao.ErrHandleDao;
import i9.defence.platform.dao.vo.ErrHandleSearchDto;
import i9.defence.platform.model.Equipment;
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
	public void addErrHandle(ErrHandle errHandle) throws BusinessException {
		try {
			if(errHandle.getId() != null) {
				errHandleDao.updateErrHandle(errHandle);
			}else {
				Manager manager = managerService.getLoginManager();
				Equipment equipment = equipmentDao.getEquipmentById(errHandle.getEqId());
				errHandle.setHandleManagerId(manager.getId());
				errHandle.setHandleDate(new Date());
				errHandle.setHandleState(1);
				errHandle.setEqDeviceId(equipment.getDeviceId());
				errHandle.setEqAddRess(equipment.getEquipmentPosition());
				errHandleDao.addErrHandle(errHandle);
			}
		} catch (Exception e) {
			throw new BusinessException("添加设备错误处理记录失败", e.getMessage());
		}
		
	}

	@Override
	public void updateErrHandle(ErrHandle errHandle) throws BusinessException {
		try {
			errHandleDao.updateErrHandle(errHandle);
		} catch (Exception e) {
			throw new BusinessException("修改设备错误处理记录失败", e.getMessage());
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
			return errHandleDao.selectByLimitPage(errHandleSearchDto);
		} catch (Exception e) {
			throw new BusinessException("分页查询设备错误处理记录失败", e.getMessage());
		}
	}
}
