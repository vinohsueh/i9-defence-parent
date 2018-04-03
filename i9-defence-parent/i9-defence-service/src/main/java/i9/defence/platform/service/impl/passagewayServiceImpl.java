package i9.defence.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i9.defence.platform.dao.PassageWayDao;
import i9.defence.platform.model.Passageway;
import i9.defence.platform.service.passagewayService;
import i9.defence.platform.utils.BusinessException;

@Service
public class passagewayServiceImpl implements passagewayService {

	@Autowired
	private PassageWayDao passageWayDao;
	
	@Override
	public List<Passageway> selectPassagewaysByEquipId(Integer Id) throws BusinessException {
		try {
			return passageWayDao.selectPassagewaysByEquipId(Id);
		} catch (Exception e) {
			throw new BusinessException("根据设备id查询通道失败",e.getMessage());
		}
	}

	@Override
	public void addPassageway(Passageway passageway) throws BusinessException {
		try {
			passageWayDao.addPassageway(passageway);
		} catch (Exception e) {
			throw new BusinessException("根据设备id查询通道失败",e.getMessage());
		}
	}

}
