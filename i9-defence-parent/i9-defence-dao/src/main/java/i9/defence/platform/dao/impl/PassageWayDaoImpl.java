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
	public List<Passageway> selectPassagewaysByEquipId(Integer Id) throws Exception {
		return passagewayMapper.selectByEquipmentId(Id);
	}

	@Override 
	public void addPassageway(Passageway passageway) throws Exception {
		passagewayMapper.addPassageWay(passageway);
	}

}
