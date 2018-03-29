package i9.defence.platform.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import i9.defence.platform.dao.vo.EquipmentFaultSearchDto;
import i9.defence.platform.model.EquipmentFault;
import i9.defence.platform.model.EquipmentFaultExample;

public interface EquipmentFaultMapper {
    long countByExample(@Param("example")EquipmentFaultSearchDto equipmentFaultSearchDto);

    int deleteByExample(EquipmentFaultExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(EquipmentFault record);

    int insertSelective(EquipmentFault record);

    List<EquipmentFault> selectByExample(EquipmentFaultExample example);

    EquipmentFault selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EquipmentFault record, @Param("example") EquipmentFaultExample example);

    int updateByExample(@Param("record") EquipmentFault record, @Param("example") EquipmentFaultExample example);

    int updateByPrimaryKeySelective(EquipmentFault record);

    int updateByPrimaryKey(EquipmentFault record);
    //分页查询
    List<EquipmentFault> selectByLimitPage(@Param("example") EquipmentFaultSearchDto equipmentFaultSearchDto, @Param("offset") int offset, @Param("limit") int pageSize);
}