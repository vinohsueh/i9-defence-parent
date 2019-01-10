package i9.defence.platform.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import i9.defence.platform.dao.vo.DealStatusDto;
import i9.defence.platform.dao.vo.EquipmentNewestDto;
import i9.defence.platform.dao.vo.EquipmentProjectDto;
import i9.defence.platform.dao.vo.EquipmentSearchDto;
import i9.defence.platform.dao.vo.HiddenDangerChannelDto;
import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.dao.vo.HiddenDangerSearchDto;
import i9.defence.platform.dao.vo.MonthData;
import i9.defence.platform.dao.vo.MonthDataDto;
import i9.defence.platform.dao.vo.TotalEquipmentDto;
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
     * 分页查询隐患报警2
     * @param hiddenDangerSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<HiddenDangerDto> selectHiddenDangerByLimitPage2(HiddenDangerSearchDto hiddenDangerSearchDto,
            int currectPage, int pageSize) throws Exception;
    
    /**
     * 分页查询隐患报警3
     * @param hiddenDangerSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<HiddenDangerDto> selectHiddenDangerByLimitPage3(HiddenDangerSearchDto hiddenDangerSearchDto,
            int currectPage, int pageSize) throws Exception;
    
    /**
     * 查询具体隐患报警--隐患
     * @param HiddenDangerChannelDto
     * @return
     */
    List<HiddenDangerChannelDto> selectHiddenDangerChannelDtoBySid(String deviceId,int count);
    
    /**
     * 
     * 修改隐患报警
     * @param DealStatusDto
     * @return
     */
    void updateDealStatus(List<DealStatusDto> list,Integer managerId,Date nowDate)throws Exception;
    
    /**
     * 查询全部隐患报警
     */
    /*List<HiddenDangerDto> getAllHiddenDanger(HiddenDangerSearchDto hiddenDangerSearchDto)throws Exception;*/
    /**
     * 查询具体隐患报警--报警
     * @param HiddenDangerChannelDto
     * @return
     */
    List<HiddenDangerChannelDto> selectDangerChannelDtoBySid(String deviceId,int count);
    
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
	
	/**
	 * 查询报警隐患数量
	 * @param deviceId
	 * @return
	 */
	HiddenDangerDto selectHiddenDangerDtoByDeviceId(String deviceId);
	
	/**
	 * 与统计报警
	 * @param monthDataDto
	 * @return
	 */
	List<MonthData> selectMonthWarningData(MonthDataDto monthDataDto);
	
	/**
	 * 与统计隐患
	 * @param monthDataDto
	 * @return
	 */
	List<MonthData> selectHiddenMonthData(MonthDataDto monthDataDto);
	
	/**
	 * 网站用户查询全部
	 * @param hiddenDangerSearchDto
	 * @return
	 */
	List<HiddenDangerDto> selectAllHiddenDangerEdit(HiddenDangerSearchDto hiddenDangerSearchDto);
	//查询设备创建时间和负责人，安全负责人手机号
	EquipmentProjectDto selectDataAndManager(int id);
	
	/**
	 * 查询设备总数
	 * @param monthDataDto
	 * @return
	 */
	TotalEquipmentDto selectTotalEquipmentDto(MonthDataDto monthDataDto);
	 /**
     * 根据用户名搜索
     * @param equipmentName
     * @return
     * @throws Exception
     */
	Equipment getEquipmentByName(String equipmentName);
	

	/**
	 * 更新设备 状态
	 * @param deviceId
	 * @param status
	 */
	void updateEquipmentStatusByDeviceId(String deviceId, int status);
	
	
	/**
	 * 根据ids查询视图数据
	* @Title: selectHiddenDangerByIds 
	* @Description: TODO
	* @param ids
	* @return
	 */
	List<HiddenDangerDto> selectHiddenDangerByIds(List<Integer> ids);
	//查询设备地址
	Equipment findEquipmentDeviceId(String deviceId);
	
	/**
	 * 更新设备数据状态
	 * @param deviceId
	 * @param datastatus
	 */
	void updateEquipmentDataStatus(String deviceId, int datastatus,int alertStatus,String newsEventTime);
	
	/**
	 * 更改设备状态
	* @Title: updateEquipStatusByIds 
	* @Description: TODO
	* @param ids
	* @throws Exception
	 */
	void updateEquipStatusByIds(List<Integer> ids)throws Exception ;
	
	/**
	 * 更改设备旧状态(第一次事件时间)
	* @Title: updateEquipRemainAlertByDeviceIds 
	* @Description: TODO
	* @param ids
	* @throws Exception
	 */
	void updateEquipRemainAlertByDeviceIds(List<String> deviceIds)throws Exception ;
	
	/**
     * 更改设备旧状态(实时时间)
    * @Title: updateEquipRemainAlertByDeviceIds 
    * @Description: TODO
    * @param ids
    * @throws Exception
     */
    void updateEquipRemainAlertByDeviceIds2(List<String> deviceIds)throws Exception ;
	
	/**
	 * 更改设备状态
	* @Title: updateAllEquipmentStatus 
	* @Description: TODO
	* @throws Exception
	 */
    void updateAllEquipmentStatus()throws Exception;
    /**
     * 更新设备最新事件时间
    * @Title: updateEquipmentNewestTime 
    * @Description: TODO
    * @param eqDeviceId
    * @param dateTime
     */
    void updateEquipmentNewestTime(String eqDeviceId, String dateTime)throws Exception;
    
    List<EquipmentNewestDto> selectAllEquipmentNewest()throws Exception;
    
    void updateSomeStatusByDevicedIds(ArrayList<String> eqDeviceIdList);
    
    List<HiddenDangerDto> selectAllHiddenDangerEdit2(HiddenDangerSearchDto hiddenDangerSearchDto)throws Exception;
    
    List<MonthData> selectCodeMonthData(MonthDataDto monthDataDto)throws Exception;

    List<String> selectCodename()throws Exception;
    
    List<MonthData> selectConnectLogMonthData(MonthDataDto monthDataDto)throws Exception;
    
    void updateEquipmentAlertAndDataStatus(String deviceId, String newsEventTime);
}
