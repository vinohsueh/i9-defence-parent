package i9.defence.platform.dao.mapper;

import i9.defence.platform.dao.vo.EqSystemCategorySearchDto;
import i9.defence.platform.model.EquipmentSystemtype;
import i9.defence.platform.model.EquipmentSystemtypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentSystemtypeMapper {
    int countByExample(@Param("example")EqSystemCategorySearchDto eqSystemCategorySearchDto);

    int deleteByExample(EquipmentSystemtypeExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(EquipmentSystemtype record);

    int insertSelective(EquipmentSystemtype record);

    List<EquipmentSystemtype> selectByExample(EquipmentSystemtypeExample example);

    EquipmentSystemtype selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EquipmentSystemtype record, @Param("example") EquipmentSystemtypeExample example);

    int updateByExample(@Param("record") EquipmentSystemtype record, @Param("example") EquipmentSystemtypeExample example);

    int updateByPrimaryKeySelective(EquipmentSystemtype record);

    int updateByPrimaryKey(EquipmentSystemtype record);

	 List<EquipmentSystemtype> selectByLimitPage(@Param("example") EqSystemCategorySearchDto eqSystemCategorySearchDto, @Param("offset") int offset, @Param("limit") int pageSize);

}