package i9.defence.platform.service;

import java.util.List;

import i9.defence.platform.dao.vo.EquipmentSearchDto;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/**
 * 项目Service
 * @author gbq
 * @create 2018年1月9日
 */
public interface EquipmentService {
	  /**
     * 分页查询项目
     * @param equipmentSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<Equipment> selectByLimitPage(EquipmentSearchDto equipmentSearchDto) throws BusinessException;
    
    /**
     * 添加设备
     * @param equipment
     * @throws Exception
     */
    void addEquipment(Equipment equipment) throws BusinessException;
    
    /**
     * 更新项目
     * @param equipment
     * @throws Exception
     */
    void updateEquipment(Equipment equipment) throws BusinessException;
    
    /**
     * 根据ID获取项目
     * @param id
     * @return
     * @throws Exception
     */
    Equipment getEquipmentById(int id) throws BusinessException;
    
    /**
     * 删除项目
     * @param id
     * @throws Exception
     */
    void deleteEquipment(List<Integer> ids) throws BusinessException;
}