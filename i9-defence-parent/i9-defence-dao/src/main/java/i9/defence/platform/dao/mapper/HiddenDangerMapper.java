package i9.defence.platform.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import i9.defence.platform.model.HiddenDanger;
import i9.defence.platform.model.HiddenDangerExample;

public interface HiddenDangerMapper {
    long countByExample(HiddenDangerExample example); 

    int deleteByExample(HiddenDangerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HiddenDanger record);

    int insertSelective(HiddenDanger record);

    List<HiddenDanger> selectByExample(HiddenDangerExample example);

    HiddenDanger selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HiddenDanger record, @Param("example") HiddenDangerExample example);

    int updateByExample(@Param("record") HiddenDanger record, @Param("example") HiddenDangerExample example);

    int updateByPrimaryKeySelective(HiddenDanger record);

    int updateByPrimaryKey(HiddenDanger record);
    
    List<HiddenDanger> selectByLimitPage(@Param("example") HiddenDangerExample hiddenDangerExample, @Param("offset") int offset, @Param("limit") int pageSize);
}