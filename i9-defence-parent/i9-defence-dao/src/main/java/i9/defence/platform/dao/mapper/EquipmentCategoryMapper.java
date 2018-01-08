package i9.defence.platform.dao.mapper;

import i9.defence.platform.model.EquipmentCategory;
import i9.defence.platform.model.EquipmentCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentCategoryMapper {
    int countByExample(EquipmentCategoryExample example);

    int deleteByExample(EquipmentCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EquipmentCategory record);

    int insertSelective(EquipmentCategory record);

    List<EquipmentCategory> selectByExample(EquipmentCategoryExample example);

    EquipmentCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EquipmentCategory record, @Param("example") EquipmentCategoryExample example);

    int updateByExample(@Param("record") EquipmentCategory record, @Param("example") EquipmentCategoryExample example);

    int updateByPrimaryKeySelective(EquipmentCategory record);

    int updateByPrimaryKey(EquipmentCategory record);
}