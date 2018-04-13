package i9.defence.platform.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.EquipmentDao;
import i9.defence.platform.dao.PassageWayDao;
import i9.defence.platform.dao.vo.PassagewayDto;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.Passageway;
import i9.defence.platform.service.passagewayService;
import i9.defence.platform.utils.BusinessException;

@Service
@Transactional
public class passagewayServiceImpl implements passagewayService {

	@Autowired
	private PassageWayDao passageWayDao;
	@Autowired
	private EquipmentDao equipmentDao;
	
	@Override
	public List<Passageway> selectPassagewaysByEquipId(Integer id) throws BusinessException {
		try {
			Equipment equipment = equipmentDao.getEquipmentById(id);
			return passageWayDao.selectPassagewaysByEquipId(equipment.getDeviceId());
		} catch (Exception e) {
			throw new BusinessException("根据设备id查询通道失败",e.getMessage());
		}
	}

	@Override
	public void addPassageway(PassagewayDto passagewayDto) throws BusinessException {
		try {
			Equipment equipment = equipmentDao.getEquipmentById(passagewayDto.getEquipmentId());
			//删除旧的通道对应关系
			passageWayDao.delPassagewayBySystemId(equipment.getDeviceId());
			List<Passageway> passageways = Arrays.asList(passagewayDto.getPassageways());
			for (Passageway passageway : passageways) {
				passageway.setDeviceId(equipment.getDeviceId());
			}
			passageWayDao.addPassageway(passageways);
		} catch (Exception e) {
			throw new BusinessException("根据设备id查询通道失败",e.getMessage());
		}
	}

}
