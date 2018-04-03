package i9.defence.platform.service;

import java.util.List;

import i9.defence.platform.model.Passageway;
import i9.defence.platform.utils.BusinessException;

/**
 * 通道Service
 * @ClassName: PassageWayDao 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年4月2日 下午5:01:32
 */
public interface passagewayService {

	/**
	 * 根据设备id查询通道
	* @Title: selectPassagewaysByEquipId 
	* @Description: TODO
	* @param Id
	* @return
	* @throws BusinessException
	 */
	List<Passageway> selectPassagewaysByEquipId(Integer Id)throws BusinessException;
	

	/**
	 * 新增通道
	* @Title: addPassageway 
	* @Description: TODO
	* @param passageway
	* @throws Exception
	 */
	void addPassageway(Passageway passageway) throws BusinessException;
}
