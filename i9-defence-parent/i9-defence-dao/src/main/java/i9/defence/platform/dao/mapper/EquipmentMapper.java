package i9.defence.platform.dao.mapper;

import i9.defence.platform.dao.vo.EquipmentSearchDto;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.EquipmentExample;
import i9.defence.platform.model.Passageway;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentMapper {
	int countByExample(@Param("example") EquipmentSearchDto equipmentSearchDto);

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

    List<Passageway> selectPassagewayByEid(String systemId);
    
    void insertPassageWay(@Param("Passageway") Passageway passageway);
    
    void updateEquipments(@Param("applies") List<Equipment> applies) ;
}