package i9.defence.platform.dao.mapper;

import i9.defence.platform.model.HiddenDangerInfo;
import i9.defence.platform.model.HiddenDangerInfoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HiddenDangerInfoMapper {
    int countByExample(@Param("example")HiddenDangerInfoExample hiddenDangerInfoExample);   

    int deleteByExample(HiddenDangerInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HiddenDangerInfo record);

    int insertSelective(HiddenDangerInfo record);

    List<HiddenDangerInfo> selectByExample(HiddenDangerInfoExample example);

    HiddenDangerInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HiddenDangerInfo record, @Param("example") HiddenDangerInfoExample example);

    int updateByExample(@Param("record") HiddenDangerInfo record, @Param("example") HiddenDangerInfoExample example);

    int updateByPrimaryKeySelective(HiddenDangerInfo record);

    int updateByPrimaryKey(HiddenDangerInfo record);
    
    /**
     * 分页查询 
     */  
    List<HiddenDangerInfo> selectByLimitPage(@Param("example") HiddenDangerInfoExample example, @Param("offset") int offset, @Param("limit") int pageSize);
}