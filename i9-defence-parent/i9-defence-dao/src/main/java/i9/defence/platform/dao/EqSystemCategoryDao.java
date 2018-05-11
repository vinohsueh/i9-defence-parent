package i9.defence.platform.dao;

import java.util.List;

import i9.defence.platform.dao.vo.EqSystemCategorySearchDto;
import i9.defence.platform.model.EquipmentSystemtype;
import i9.defence.platform.utils.PageBounds;

/**
 *  Dao
 * @author gbq
 * @create 2018年5月9日
 */
public interface EqSystemCategoryDao {
	/**
     * 查询全部
     * @param 
     * @throws Exception
     */
	List<EquipmentSystemtype> findEquipmentSystemCategory()throws Exception;
	/**
     * 分页查询
     * @param 
     * @throws Exception
     */
	PageBounds<EquipmentSystemtype> selectByLimitPage(EqSystemCategorySearchDto eqSystemCategorySearchDto,
			int currentPage, int pageSize)throws Exception;
	/**
     * 添加
     * @param 
     * @throws Exception
     */
	void addEqSystemtype(EquipmentSystemtype equipmentSystemtype)throws Exception;
	/**
     * 修改
     * @param 
     * @throws Exception
     */
	void updateEqSystemtype(EquipmentSystemtype equipmentSystemtype)throws Exception;
	/**
     * 通过id查询
     * @param 
     * @throws Exception
     */
	EquipmentSystemtype getEqSystemtypeById(int id)throws Exception;
	/**
     * 删除
     * @param 
     * @throws Exception
     */
	void deleteEqSystemtype(List<Integer> ids)throws Exception;


}
