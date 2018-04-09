package i9.defence.platform.service;

import i9.defence.platform.dao.vo.EquipmentSearchDto;
import i9.defence.platform.dao.vo.HiddenDangerChannelDto;
import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.dao.vo.HiddenDangerSearchDto;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.Passageway;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

import java.util.List;

/**
 * 设备Service
 * @author gbq
 * @create 2018年1月9日
 */
public interface EquipmentService {
	  /**
     * 分页查询设备
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
     * 更新设备
     * @param equipment
     * @throws Exception
     */
    void updateEquipment(Equipment equipment) throws BusinessException;
    
    /**
     * 根据ID获取设备
     * @param id
     * @return
     * @throws Exception
     */
    Equipment getEquipmentById(int id) throws BusinessException;
    
    /**
     * 删除设备
     * @param id
     * @throws Exception
     */
    void deleteEquipment(List<Integer> ids) throws BusinessException;
    
    /**
     * 申请删除设备集合
    * @Title: applyEquipment 
    * @Description: TODO
    * @param ids
    * @throws BusinessException
     */
    public String  applyDelEquipment(List<Integer> ids) throws BusinessException;
    
    /**
     * 根据设备Id查找通道
     * @return
     * @throws BusinessException
     */
    List<Passageway> selectPassagewayByEid(String systemId)throws BusinessException;
    /**
     * 分页查询隐患报警
     * @param equipmentSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<HiddenDangerDto> selectHiddenDangerByLimitPage(HiddenDangerSearchDto hiddenDangerSearchDto) throws BusinessException;
    /**
     * 新增通道
     * @param passageway
     * @throws BusinessException
     */
    void insertPassageWay(Passageway passageway) throws BusinessException;
    /**
     * 查询具体隐患报警
     * @param selectHiddenDangerChannelDtoBySid
     * @return
     */
    List<HiddenDangerChannelDto> selectHiddenDangerChannelDtoBySid(String systemId);
 
}
