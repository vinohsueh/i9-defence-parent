package i9.defence.platform.dao.mapper;

import i9.defence.platform.model.Apply;
import i9.defence.platform.model.ApplyExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ApplyMapper {
    int countByExample(ApplyExample example);

    int deleteByExample(ApplyExample example);

    int deleteByPrimaryKey(List<Integer> ids);
    
    int updateByprimaryKeys(List<Integer> ids);

    int insert(Apply record);

    int insertSelective(Apply record);

    List<Apply> selectByExample(ApplyExample example);

    Apply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Apply record, @Param("example") ApplyExample example);

    int updateByExample(@Param("record") Apply record, @Param("example") ApplyExample example);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKey(Apply record);
    
    List<Apply> selectByLimitPage(@Param("example") ApplyExample example, @Param("offset") int offset, @Param("limit") int pageSize);

    void insertEquipmentApplys(@Param("applies") List<Apply> applies);
    
    void insertProjectApplys(@Param("applies") List<Apply> applies);    
    
    int  updateApplys(@Param("applies") List<Apply> applies);
}