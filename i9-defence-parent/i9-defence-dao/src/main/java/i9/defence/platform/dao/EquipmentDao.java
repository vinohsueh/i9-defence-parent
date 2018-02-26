package i9.defence.platform.dao;

import java.util.List;

import i9.defence.platform.dao.vo.EquipmentSearchDto;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.utils.PageBounds;

/**
 * 项目Dao
 * @author gbq
 * @create 2018年1月8日
 */
public interface EquipmentDao {
	  /**
     * 分页查询项目
     * @param equipmentSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<Equipment> selectByLimitPage(EquipmentSearchDto equipmentSearchDto,
            int currectPage, int pageSize) throws Exception;
    
    /**
     * 添加设备
     * @param equipment
     * @throws Exception
     */
    void addEquipment(Equipment equipment) throws Exception;
    
    /**
     * 更新项目
     * @param equipment
     * @throws Exception
     */
    void updateEquipment(Equipment equipment) throws Exception;
    
    /**
     * 根据ID获取项目
     * @param id
     * @return
     * @throws Exception
     */
    Equipment getEquipmentById(int id) throws Exception;
    
    /**
     * 根据IDS获取设备
     * @param id
     * @return
     * @throws Exception
     */
    List<Equipment> getEquipmentByIds(List<Integer> ids) throws Exception;
    
    /**
     * 删除设备
     * @param id
     * @throws Exception
     */
    void deleteEquipment(List<Integer> ids) throws Exception;
}