package i9.defence.platform.dao;

import i9.defence.platform.model.EquipmentFailure;
import i9.defence.platform.model.EquipmentFailureExample;
import i9.defence.platform.utils.PageBounds;

import java.util.List;

/**
 * 设备故障Dao
* @ClassName: EquipmentFailureDao 
* @Description: TODO
* @author luobo
* @date 2018年1月8日 下午5:18:33 
*
 */
public interface EquipmentFailureDao {
	 /**
     * 添加故障设备
     * @param EquipmentFailure
     * @throws Exception
     */
	 void addEquipmentFailure(EquipmentFailure equipmentFailure)throws Exception;
	 
	 /**
	  * 删除故障设备
      * @param ids
      * @throws Exception
	  */
	  void deleteEquipmentFailure(List<Integer> ids ) throws Exception;
	  
	 /**
	  * 更新故障设备
      * @param EquipmentFailure
      * @throws Exception
	  */
	  void updateEquipmentFailure(EquipmentFailure equipmentFailure)throws Exception;
	  
	  /**
		  * 根据Id获取故障设备
	      * @param id
	      * @throws Exception
		  */
	  EquipmentFailure getEquipmentFailureById(int id)throws Exception;
	  
	  /**
		  * 分页查询故障设备
	      * @param manager
	      * @throws Exception
		  */
	 PageBounds<EquipmentFailure> selectByLimitPage(EquipmentFailureExample equipmentFailureExample,
					int currectPage, int pageSize)throws Exception;
}
