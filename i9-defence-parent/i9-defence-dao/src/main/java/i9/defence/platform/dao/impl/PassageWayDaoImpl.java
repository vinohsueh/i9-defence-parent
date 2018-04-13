package i9.defence.platform.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.PassageWayDao;
import i9.defence.platform.dao.mapper.PassagewayMapper;
import i9.defence.platform.model.Passageway;

@Repository
public class PassageWayDaoImpl implements PassageWayDao {

	@Autowired
	private PassagewayMapper passagewayMapper;
	@Override
	public List<Passageway> selectPassagewaysByEquipId(String id) throws Exception {
		return passagewayMapper.selectByEquipmentId(id);
	}

	@Override 
	public void addPassageway(List<Passageway> passageways) throws Exception {
		passagewayMapper.addPassageWay(passageways);
	}

	@Override
	public void delPassagewayBySystemId(String systemId) throws Exception {
		passagewayMapper.delPassagewayBySystemId(systemId);
	}

}
