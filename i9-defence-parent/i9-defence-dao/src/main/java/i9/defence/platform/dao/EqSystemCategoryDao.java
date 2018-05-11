package i9.defence.platform.dao;

import java.util.List;

import i9.defence.platform.model.EquipmentSystemtype;

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

}
