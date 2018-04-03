package i9.defence.platform.dao;

import java.util.List;

import i9.defence.platform.model.Passageway;

/**
 * 通道dao
 * @ClassName: PassageWayDao 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年4月2日 下午5:01:32
 */
public interface PassageWayDao {
	
	/**
	 * 根据设备id查询通道
	* @Title: selectPassagewaysByEquipId 
	* @Description: TODO
	* @param Id
	* @return
	* @throws Exception
	 */
	List<Passageway> selectPassagewaysByEquipId(Integer Id)throws Exception;
	
	
	/**
	 * 新增通道
	* @Title: addPassageway 
	* @Description: TODO
	* @param passageway
	* @throws Exception
	 */
	void addPassageway(Passageway passageway) throws Exception;
}
