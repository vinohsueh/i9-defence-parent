package i9.defence.platform.dao.mapper;

import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.model.HiddenDanger;
import i9.defence.platform.model.HiddenDangerExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HiddenDangerMapper {
    int countByExample(@Param("example")HiddenDangerDto hiddenDangerDto); 

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
    
    /**
     * 分页查询 
     */
    List<HiddenDanger> selectByLimitPage(@Param("example") HiddenDangerDto hiddenDangerDto, @Param("offset") int offset, @Param("limit") int pageSize);
}