package i9.defence.platform.dao;

import java.util.List;

import i9.defence.platform.dao.vo.EqCategorySearchDto;
import i9.defence.platform.model.EquipmentCategory;
import i9.defence.platform.utils.PageBounds;

/**
 *  Dao
 * @author gbq
 * @create 2018年1月8日
 */
public interface EquipmentCategoryDao {
	 /**
     * 分页查询
     * @param eqCategorySearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<EquipmentCategory> selectByLimitPage(EqCategorySearchDto eqCategorySearchDto,
            int currectPage, int pageSize) throws Exception;
    
    /**
     * 添加
     * @param eqCategory
     * @throws Exception
     */
    void addEqCategory(EquipmentCategory eqCategory) throws Exception;
    
    /**
     * 更新
     * @param eqCategory
     * @throws Exception
     */
    void updateEqCategory(EquipmentCategory eqCategory) throws Exception;
    
    /**
     * 根据ID获取
     * @param id
     * @return
     * @throws Exception
     */
    EquipmentCategory getEqCategoryById(int id) throws Exception;
    
    /**
     * 删除项目
     * @param id
     * @throws Exception
     */
    void deleteEqCategory(List<Integer> ids) throws Exception;
    
    /**
     * 查询全部
     * @param 
     * @throws Exception
     */
    List<EquipmentCategory> serchEqCategory()throws Exception;
    
    /**
     * 查询数量
     * @param 
     * @throws Exception
     */
    List<EquipmentCategory> selectAllEqCategoryAndNum(EquipmentCategory equipmentCategory)throws Exception;
    
    //查询总数
	int selectSumEqNum(EquipmentCategory equipmentCategory)throws Exception;
	//查询二级类别
	List<EquipmentCategory> findEquipmentSystemCategory2(int id)throws Exception;
	//通过id查询全部
	List<EquipmentCategory> selectEqCategory(Integer id)throws Exception;
	//查询eqCategoryId是否重复
	EquipmentCategory getEqCategoryId(String eqCategoryId);
    
    /**
     * 查询数量
     * @param 
     * @throws Exception
     */
//    List<EquipmentCategory> selectAllEqCategoryAndNum1(EquipmentCategory equipmentCategory)throws Exception;
    
}
