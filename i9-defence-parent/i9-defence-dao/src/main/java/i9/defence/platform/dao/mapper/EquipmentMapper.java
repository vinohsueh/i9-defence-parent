package i9.defence.platform.dao.mapper;

import i9.defence.platform.dao.vo.DealStatusDto;
import i9.defence.platform.dao.vo.EquipmentSearchDto;
import i9.defence.platform.dao.vo.HiddenDangerChannelDto;
import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.dao.vo.HiddenDangerSearchDto;
import i9.defence.platform.model.ChannelData;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.EquipmentExample;
import i9.defence.platform.model.Passageway;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentMapper {
	int countByExample(@Param("example") EquipmentSearchDto equipmentSearchDto);
	
	int countHiddenDangerByExample(@Param("example")HiddenDangerSearchDto hiddenDangerSearchDto);

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

    List<Equipment> selectByLimitPage2(@Param("example") EquipmentSearchDto equipmentSearchDto, @Param("offset") int offset, @Param("limit") int pageSize,@Param("distributorId")Integer distributorId);
    
    List<Equipment> selectByLimitPage3(@Param("example") EquipmentSearchDto equipmentSearchDto,@Param("offset") int offset,@Param("limit") int pageSize,
    		@Param("distributorId")Integer managerId);
    
    List<Passageway> selectPassagewayByEid(String systemId);
    
    void insertPassageWay(@Param("Passageway") Passageway passageway);
    
    void updateEquipments(@Param("applies") List<Equipment> applies) ;
    //隐患报警分页查询
    List<HiddenDangerDto> selectHiddenDangerByLimitPage(@Param("example") HiddenDangerSearchDto hiddenDangerSearchDto, @Param("offset") int offset, @Param("limit") int pageSize);

    //查询具体隐患报警--隐患
    List<HiddenDangerChannelDto> selectHiddenDangerChannelDtoBySid(String systemId);
    //查询具体隐患报警--报警
    List<HiddenDangerChannelDto> selectDangerChannelDtoBySid(String systemId);
    //隐患报警 全部查询
    List<HiddenDangerDto> selectAllHiddenDanger(@Param("example") HiddenDangerSearchDto hiddenDangerSearchDto);
    /**
     * 批量添加设备
     * @param records
     */
    void insertEquipments(@Param("equipments") List<Equipment> equipments);
    
    /**
	 * 批量修改设备
	 */
	void updateEquipmentByIds(List<Equipment> list);

}