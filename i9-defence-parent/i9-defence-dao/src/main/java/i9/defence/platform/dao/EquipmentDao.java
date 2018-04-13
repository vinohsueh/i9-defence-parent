package i9.defence.platform.dao;

import java.util.List;

import i9.defence.platform.dao.vo.DealStatusDto;
import i9.defence.platform.dao.vo.EquipmentSearchDto;
import i9.defence.platform.dao.vo.HiddenDangerChannelDto;
import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.dao.vo.HiddenDangerSearchDto;
import i9.defence.platform.model.ChannelData;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.Passageway;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/**
 * 设备Dao
 * @author gbq
 * @create 2018年1月8日
 */
public interface EquipmentDao {
	  /**
     * 分页查询设备
     * @param equipmentSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<Equipment> selectByLimitPage(EquipmentSearchDto equipmentSearchDto,
            int currectPage, int pageSize) throws Exception;
    /**
     * 如果为经销商，分页查询设备
     * @param equipmentSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<Equipment> selectByLimitPage2(EquipmentSearchDto equipmentSearchDto, int currentPage, int pageSize,int distributorId) throws Exception;
    
    /**
     * 如果为项目管理员，分页查询设备
     * @param equipmentSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<Equipment> selectByLimitPage3(EquipmentSearchDto equipmentSearchDto, int currentPage, int pageSize,
			Integer id);
    /**
     * 添加设备
     * @param equipment
     * @throws Exception
     */
    void addEquipment(Equipment equipment) throws Exception;
    
    /**
     * 批量添加设备
     * @param equipment
     * @throws Exception
     */
    void addEquipments(List<Equipment> equipments) throws Exception;
    
    /**
     * 更新设备
     * @param equipment
     * @throws Exception
     */
    void updateEquipment(Equipment equipment) throws Exception;
    
    /**
	 * 批量修改设备
	 * @param applies
	 * @throws Exception
	 */
	void updateEquipmentByIds(List<Equipment> equipments) throws Exception;
    
    /**
     * 根据ID获取设备
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
    
    /**
     * 根据设备Id查找通道
     * @return
     * @throws BusinessException
     */
    List<Passageway> selectPassagewayByEid(String systemId)throws Exception;
    
    /**
     * 新增通道
     * @param passageway
     * @throws BusinessException
     */
    void insertPassageWay(Passageway passageway) throws Exception;
    
	/**
	 * 批量更新设备
	 * @param applies
	 * @throws Exception
	 */
	void updateEquipments(List<Equipment> applies) throws Exception;

	 /**
     * 分页查询隐患报警
     * @param hiddenDangerSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<HiddenDangerDto> selectHiddenDangerByLimitPage(HiddenDangerSearchDto hiddenDangerSearchDto,
            int currectPage, int pageSize) throws Exception;
    
    /**
     * 查询具体隐患报警--隐患
     * @param HiddenDangerChannelDto
     * @return
     */
    List<HiddenDangerChannelDto> selectHiddenDangerChannelDtoBySid(String systemId);
    
    /**
     * 
     * 修改隐患报警
     * @param DealStatusDto
     * @return
     */
    void updateDealStatus(List<DealStatusDto> list)throws Exception;
    
    /**
     * 查询全部隐患报警
     */
    List<HiddenDangerDto> getAllHiddenDanger(HiddenDangerSearchDto hiddenDangerSearchDto)throws Exception;
    /**
     * 查询具体隐患报警--报警
     * @param HiddenDangerChannelDto
     * @return
     */
    List<HiddenDangerChannelDto> selectDangerChannelDtoBySid(String systemId);
    
    /**
     * 分页查询故障设备
     * @param equipmentSearchDto
     * @return
     */
	PageBounds<Equipment> selectErrorEquipment(EquipmentSearchDto equipmentSearchDto);
		
	/**
	 * 
	 * @param equipmentSearchDto
	 * @return
	 */
	List<ChannelData> selectErrorRecord(EquipmentSearchDto equipmentSearchDto);
	
	/**
	 * 根据唯一编号查找
	 * @param deviceId
	 * @return
	 */
	Equipment getEquipmentByIdentifier(String deviceId);
}
