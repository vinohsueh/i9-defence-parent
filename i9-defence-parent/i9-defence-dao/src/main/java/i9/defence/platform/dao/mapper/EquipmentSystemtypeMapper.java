package i9.defence.platform.dao.mapper;

import i9.defence.platform.model.EquipmentSystemtype;
import i9.defence.platform.model.EquipmentSystemtypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentSystemtypeMapper {
    long countByExample(EquipmentSystemtypeExample example);

    int deleteByExample(EquipmentSystemtypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EquipmentSystemtype record);

    int insertSelective(EquipmentSystemtype record);

    List<EquipmentSystemtype> selectByExample(EquipmentSystemtypeExample example);

    EquipmentSystemtype selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EquipmentSystemtype record, @Param("example") EquipmentSystemtypeExample example);

    int updateByExample(@Param("record") EquipmentSystemtype record, @Param("example") EquipmentSystemtypeExample example);

    int updateByPrimaryKeySelective(EquipmentSystemtype record);

    int updateByPrimaryKey(EquipmentSystemtype record);

}