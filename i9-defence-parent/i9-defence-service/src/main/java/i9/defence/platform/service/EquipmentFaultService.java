package i9.defence.platform.service;

import java.util.List;

import i9.defence.platform.dao.vo.EquipmentFaultSearchDto;
import i9.defence.platform.model.EquipmentFault;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/** 
* @author user: jiace
* @version creatTime：2018年3月29日 上午11:32:46 
* 
*/
public interface EquipmentFaultService {
	/*
	 * 分页查询
	 */
	 PageBounds<EquipmentFault> selectByLimitPage(EquipmentFaultSearchDto equipmentFaultSearchDto,
	            int currectPage, int pageSize) throws BusinessException;
	 /*
	  * 添加
	  */
	 void add(EquipmentFault equipmentFault)throws BusinessException;
	 /*
	  * 更新
	  */
	 void update(EquipmentFault equipmentFault)throws BusinessException;
	 /*
	  * 批量删除
	  */
	 void deleteBatch(List<Integer> ids) throws BusinessException;
	 /*
	  * 根据id查询
	  */
	 EquipmentFault getById(Integer id)throws BusinessException;
}
 