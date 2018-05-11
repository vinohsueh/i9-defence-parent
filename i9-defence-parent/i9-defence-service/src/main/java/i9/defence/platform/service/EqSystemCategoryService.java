package i9.defence.platform.service;

import java.util.List;

import i9.defence.platform.model.EquipmentSystemtype;
import i9.defence.platform.utils.BusinessException;

/**
 * Service
 * @author gbq
 * @create 
 */
public interface EqSystemCategoryService {
	//查询全部
	List<EquipmentSystemtype> findEquipmentSystemCategory()throws BusinessException;

}
