package i9.defence.platform.dao.mapper;

import java.util.List;

import i9.defence.platform.model.Passageway;

public interface PassagewayMapper {
	
	//根据设备id查询通道
	List<Passageway> selectByEquipmentId(Integer Id);
	
	//新增通道
	void addPassageWay(Passageway passageway);
}
