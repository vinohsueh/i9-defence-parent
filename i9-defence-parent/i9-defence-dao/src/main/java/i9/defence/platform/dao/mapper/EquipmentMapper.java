package i9.defence.platform.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import i9.defence.platform.dao.vo.EquipmentProjectDto;
import i9.defence.platform.dao.vo.EquipmentSearchDto;
import i9.defence.platform.dao.vo.HiddenDangerChannelDto;
import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.dao.vo.HiddenDangerSearchDto;
import i9.defence.platform.dao.vo.MonthData;
import i9.defence.platform.dao.vo.MonthDataDto;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.EquipmentExample;
import i9.defence.platform.model.Passageway;

public interface EquipmentMapper {
	int countByExample(@Param("example") EquipmentSearchDto equipmentSearchDto);
	
	int countHiddenDangerByExample(@Param("example")HiddenDangerSearchDto hiddenDangerSearchDto);
	
	int countHiddenDangerByExample2(@Param("example")HiddenDangerSearchDto hiddenDangerSearchDto);
  
	int deleteByExample(EquipmentExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    List<Equipment> selectByExample(EquipmentExample example);

    Equipment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Equipment record, @Param("example") EquipmentExample example);

    int updateByExample(@Param("record") Equipment record, @Param("example") EquipmentExample example);

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);
    
    List<Equipment> selectByLimitPage(@Param("example") EquipmentSearchDto equipmentSearchDto, @Param("offset") int offset, @Param("limit") int pageSize);

    List<Equipment> selectByLimitPage2(@Param("example") EquipmentSearchDto equipmentSearchDto, @Param("offset") int offset, @Param("limit") int pageSize);
    
    List<Equipment> selectByLimitPage3(@Param("example") EquipmentSearchDto equipmentSearchDto,@Param("offset") int offset,@Param("limit") int pageSize);
    
    int countByExample3(@Param("example") EquipmentSearchDto equipmentSearchDto);
    
    int countByExample2(@Param("example") EquipmentSearchDto equipmentSearchDto);
    
    List<Passageway> selectPassagewayByEid(String systemId);
    
    void insertPassageWay(@Param("Passageway") Passageway passageway);
    
    void updateEquipments(@Param("applies") List<Equipment> applies) ;
    
    void updateEquipStatusByIds(@Param("items") List<Integer> ids) ;
    
    //隐患报警分页查询
    List<HiddenDangerDto> selectHiddenDangerByLimitPage(@Param("example") HiddenDangerSearchDto hiddenDangerSearchDto, @Param("offset") int offset, @Param("limit") int pageSize);

    List<HiddenDangerDto> selectHiddenDangerByLimitPage2(@Param("example") HiddenDangerSearchDto hiddenDangerSearchDto, @Param("offset") int offset, @Param("limit") int pageSize);
    
    //查询具体隐患报警--隐患
    List<HiddenDangerChannelDto> selectHiddenDangerChannelDtoBySid(@Param("deviceId")String deviceId,@Param("count") int count);
    //查询具体隐患报警--报警
    List<HiddenDangerChannelDto> selectDangerChannelDtoBySid(@Param("deviceId") String systemId,@Param("count") int count);
    //隐患报警 全部查询
    /*List<HiddenDangerDto> selectAllHiddenDanger(@Param("example") HiddenDangerSearchDto hiddenDangerSearchDto);*/
    /**
     * 批量添加设备
     * @param records
     */
    void insertEquipments(List<Equipment> equipments);
    
    /**
	 * 批量修改设备
	 */
	void updateEquipmentByIds(List<Equipment> list);

    
    //查询故障设备
    int countErrorByExample(@Param("example") EquipmentSearchDto equipmentSearchDto);
    
    List<Equipment> selectErrorEquipment(@Param("example") EquipmentSearchDto equipmentSearchDto, @Param("offset") int offset, @Param("limit") int pageSize);
    
    /**
	 * 查询报警隐患数量
	 * @param deviceId
	 * @return
	 */
	HiddenDangerDto selectHiddenDangerDtoByDeviceId(String deviceId);
	
	/**
	 * 查询月统计
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<MonthData> selectWarningMonthData(MonthDataDto monthDataDto);
		
	/**
	 * 查询月隐患
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<MonthData> selectHiddenMonthData(MonthDataDto monthDataDto);
	
	/**
	 * 查询全部
	 * @param hiddenDangerSearchDto
	 * @return
	 */
	List<HiddenDangerDto> selectAllHiddenDangerEdit(@Param("example") HiddenDangerSearchDto hiddenDangerSearchDto);
	//查询设备创建时间和负责人，安全负责人手机号
	EquipmentProjectDto selectDataAndManager(int id);
	
	/**
	 * 查询用户登进来的设备总数
	 * @param monthDataDto
	 * @return
	 */
	int selectTotalEquipmentDto(@Param("example") MonthDataDto monthDataDto);
	
	/**
	 * 查询用户登进来的设备报警总数
	 * @param monthDataDto
	 * @return
	 */
	int selectTotalAlertEquipmentDto(@Param("example") MonthDataDto monthDataDto);
	
	/**
	 * 查询离线设备
	 * @param monthDataDto
	 * @return
	 */
	int selectOfflineEquipment(@Param("example") MonthDataDto monthDataDto);
	
	/**
	 * 查询隐患设备数量
	 * @param monthDataDto
	 * @return
	 */
	int selectHiddenEquipment(@Param("example") MonthDataDto monthDataDto);
	/**
	 * 更新设备状态
	 * @param deviceId
	 * @param status
	 */
	void updateEquipmentStatusByDeviceId(@Param("deviceId") String deviceId,@Param("status") int status);
	
	/**
	 * 根据ids查询视图数据
	* @Title: selectHiddenDangerByIds 
	* @Description: TODO
	* @param ids
	* @return
	 */
	List<HiddenDangerDto> selectHiddenDangerByIds(@Param("Items") List<Integer> ids);
	
	/**
	 * 根据deviceId查询视图
	* @Title: selectHiddenDangerByIds 
	* @Description: TODO
	* @param ids
	* @return
	 */
	List<HiddenDangerDto> selectHiddenDangerByDevicedIds(@Param("Items") List<Integer> ids);
	
	//查询设备地址
	Equipment findEquipmentDeviceId(String deviceId);
	
	
	/**
	 * 更新设备数据状态
	 * @param deviceId
	 * @param datastatus
	 */
	void updateEquipmentDataStatus(@Param("deviceId") String deviceId,@Param("datastatus") int datastatus,@Param("alertStatus") int alertStatus);

	/**
	 * 更改设备旧状态
	* @Title: updateEquipRemainAlertByDeviceIds 
	* @Description: TODO
	* @param ids
	 */
	void updateEquipRemainAlertByDeviceIds(@Param("items") List<String> deviceIds);

}