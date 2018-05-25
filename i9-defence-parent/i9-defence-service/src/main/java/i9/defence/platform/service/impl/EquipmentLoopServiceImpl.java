package i9.defence.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i9.defence.platform.dao.EquipmentLoopDao;
import i9.defence.platform.dao.vo.EquipmentLoopDto;
import i9.defence.platform.model.EquipmentLoop;
import i9.defence.platform.service.EquipmentLoopService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;


@Service
public class EquipmentLoopServiceImpl implements EquipmentLoopService {
	
	@Autowired
	private EquipmentLoopDao equipmentLoopDao;
	
	
	@Override
	public PageBounds<EquipmentLoop> selectByLimitPage(EquipmentLoopDto equipmentLoopDto, int currectPage, int pageSize)
			throws BusinessException {
		try {
			return equipmentLoopDao.selectByLimitPage(equipmentLoopDto, currectPage, pageSize);
		} catch (Exception e) {
			throw new BusinessException("查询设备回路失败",e.getMessage());
		}
	}

	@Override
	public void insertEquipLoop(EquipmentLoop equipmentLoop) throws BusinessException {
		try {
			equipmentLoopDao.insertEquipLoop(equipmentLoop);
		} catch (Exception e) {
			throw new BusinessException("新增设备回路失败",e.getMessage());
		}
	}

}
