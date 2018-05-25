package i9.defence.platform.service;

import i9.defence.platform.dao.vo.EquipmentLoopDto;
import i9.defence.platform.model.EquipmentLoop;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/**
 * 设备回路表
 * @ClassName: EquipmentLoopService 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年5月25日 下午4:31:16
 */
public interface EquipmentLoopService {
	
	/**
     * 分页查询设备
     * @param equipmentSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<EquipmentLoop> selectByLimitPage(EquipmentLoopDto equipmentLoopDto,
            int currectPage, int pageSize) throws BusinessException;
    
    
    /**
     * 新增设备回路
    * @Title: insertEquipLoop 
    * @Description: TODO
    * @param equipmentLoop
    * @throws Exception
     */
    void  insertEquipLoop(EquipmentLoop equipmentLoop) throws BusinessException;
	
}
