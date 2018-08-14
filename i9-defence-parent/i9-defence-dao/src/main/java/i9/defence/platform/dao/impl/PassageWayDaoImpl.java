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
	public List<Passageway> selectPassagewaysBySystemId(String systemId) throws Exception {
		return passagewayMapper.selectPassagewaysBySystemId(systemId); 
	}
 
	@Override 
	public void addPassageway(List<Passageway> passageways) throws Exception {
		passagewayMapper.addPassageWay(passageways);
	}

	@Override
	public void delPassagewayBySystemId(String systemId) throws Exception {
		passagewayMapper.delPassagewayBySystemId(systemId);
	}

	@Override
	public int selectCountByCatIdAndChannelId(Passageway passageway) throws Exception {
		return passagewayMapper.selectCountByCatIdAndChannelId(passageway);
	}

	@Override
	public void delPassageway(Passageway passageway) throws Exception {
		passagewayMapper.delPassageway(passageway);
	}

	@Override
	public List<Integer> selectChannelsBySystemId(String systemId) {
		return passagewayMapper.selectChannelsBySystemId(systemId);
	}

	@Override
	public List<Integer> selectChannelByDeviceId(String deviceId) throws Exception {
		return passagewayMapper.selectChannelByDeviceId(deviceId);
	}

}
