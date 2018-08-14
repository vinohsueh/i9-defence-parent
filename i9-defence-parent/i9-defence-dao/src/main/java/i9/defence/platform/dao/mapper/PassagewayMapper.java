package i9.defence.platform.dao.mapper;

import java.util.List;

import i9.defence.platform.model.Passageway;

public interface PassagewayMapper {
	
	//根据设备类型id查询通道
	List<Passageway> selectPassagewaysBySystemId(String systemId); 
	
	//新增通道
	void addPassageWay(List<Passageway> passageways);
	
	//查询
	int selectCountByCatIdAndChannelId(Passageway passageway);
	
	//删除旧的通道对应关系
	void delPassagewayBySystemId(String systemId);
	
	//删除通告
	void delPassageway(Passageway passageway);
	
	/**
	 * 通过设备编号查询设备关注的通道号
	 * @param systemId
	 * @return
	 */
	List<Integer> selectChannelsBySystemId(String systemId);
	
	List<Integer> selectChannelByDeviceId(String deviceId);
}
