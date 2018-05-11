package i9.defence.platform.service;

import java.util.List;

import i9.defence.platform.dao.vo.EqSystemCategorySearchDto;
import i9.defence.platform.model.EquipmentSystemtype;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/**
 * Service
 * @author gbq
 * @create 
 */
public interface EqSystemCategoryService {
	//查询全部
	List<EquipmentSystemtype> findEquipmentSystemCategory()throws BusinessException;

	PageBounds<EquipmentSystemtype> selectByLimitPage(EqSystemCategorySearchDto eqSystemCategorySearchDto)throws BusinessException;
	//添加
	void addEqSystemtype(EquipmentSystemtype equipmentSystemtype)throws BusinessException;
	//根据id查询
	EquipmentSystemtype getEqSystemtypeById(int id) throws BusinessException;
	//删除
	void deleteEqSystemtype(List<Integer> ids) throws BusinessException;

}
