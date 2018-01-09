package i9.defence.platform.dao;

import java.util.List;

import i9.defence.platform.model.EquipmentFailure;

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
     * @param manager
     * @throws Exception
     */
	 void addEquipmentFailure(EquipmentFailure equipmentFailure)throws Exception;
	 
	 /**
	  * 删除故障设备
      * @param manager
      * @throws Exception
	  */
	  void deleteEquipmentFailure(List<Integer> ids ) throws Exception;
	  
	 /**
	  * 更新故障设备
      * @param manager
      * @throws Exception
	  */
	  void updateEquipmentFailure(EquipmentFailure equipmentFailure)throws Exception;
}
