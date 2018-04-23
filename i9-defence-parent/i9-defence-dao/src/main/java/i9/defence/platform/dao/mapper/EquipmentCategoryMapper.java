package i9.defence.platform.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import i9.defence.platform.dao.vo.EqCategorySearchDto;
import i9.defence.platform.model.EquipmentCategory;
import i9.defence.platform.model.EquipmentCategoryExample;

public interface EquipmentCategoryMapper {
	int countByExample(@Param("example") EqCategorySearchDto eqCategorySearchDto);

    int deleteByExample(EquipmentCategoryExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(EquipmentCategory record);

    int insertSelective(EquipmentCategory record);

    List<EquipmentCategory> selectByExample(EquipmentCategoryExample example);

    EquipmentCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EquipmentCategory record, @Param("example") EquipmentCategoryExample example);

    int updateByExample(@Param("record") EquipmentCategory record, @Param("example") EquipmentCategoryExample example);

    int updateByPrimaryKeySelective(EquipmentCategory record);

    int updateByPrimaryKey(EquipmentCategory record);
    
    List<EquipmentCategory> selectByLimitPage(@Param("example") EqCategorySearchDto eqCategorySearchDto, @Param("offset") int offset, @Param("limit") int pageSize);

    EquipmentCategory selectByeqEqCategoryId(String eqCategoryId);
    
    List<EquipmentCategory> selectAllEqCategoryAndNum(@Param("example") EquipmentCategory equipmentCategory);
    
//    List<EquipmentCategory> selectAllEqCategoryAndNum1(@Param("example") EquipmentCategory equipmentCategory);
}