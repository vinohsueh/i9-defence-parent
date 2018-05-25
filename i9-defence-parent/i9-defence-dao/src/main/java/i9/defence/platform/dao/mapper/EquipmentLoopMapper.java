package i9.defence.platform.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import i9.defence.platform.dao.vo.EquipmentLoopDto;
import i9.defence.platform.model.EquipmentLoop;
import i9.defence.platform.model.EquipmentLoopExample;

public interface EquipmentLoopMapper {
    long countByExample(EquipmentLoopDto equipmentLoopDto); 

    int deleteByExample(EquipmentLoopExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EquipmentLoop record);

    int insertSelective(EquipmentLoop record);

    List<EquipmentLoop> selectByExample(EquipmentLoopExample example);

    EquipmentLoop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EquipmentLoop record, @Param("example") EquipmentLoopExample example);

    int updateByExample(@Param("record") EquipmentLoop record, @Param("example") EquipmentLoopExample example);

    int updateByPrimaryKeySelective(EquipmentLoop record);

    int updateByPrimaryKey(EquipmentLoop record);
    
    /**
     * 分页查询
    * @Title: selectByLimitPage 
    * @Description: TODO
    * @param equipmentLoopDto
    * @param offset
    * @param pageSize
    * @return
     */
    List<EquipmentLoop> selectByLimitPage(@Param("example") EquipmentLoopDto equipmentLoopDto, @Param("offset") int offset, @Param("limit") int pageSize);
}