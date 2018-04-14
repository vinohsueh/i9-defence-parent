package i9.defence.platform.dao.mapper;

import java.util.List;

import i9.defence.platform.model.Passageway;

public interface PassagewayMapper {
	
	//根据设备id查询通道
	List<Passageway> selectByEquipmentId(String deviceId);
	
	//新增通道
	void addPassageWay(List<Passageway> passageways);
	
	//删除旧的通道对应关系
	void delPassagewayBySystemId(String deviceId);
}
