package i9.defence.platform.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import i9.defence.platform.dao.vo.UpStreamDecodeSearchDto;
import i9.defence.platform.model.UpStreamDecode;
import i9.defence.platform.model.UpStreamDecodeExample;

public interface UpStreamDecodeMapper {
    long countByExample(@Param("example")UpStreamDecodeSearchDto upStreamDecodeSearchDto);

    int deleteByExample(UpStreamDecodeExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(UpStreamDecode record);

    int insertSelective(UpStreamDecode record);

    List<UpStreamDecode> selectByExample(UpStreamDecodeExample example);

    UpStreamDecode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UpStreamDecode record, @Param("example") UpStreamDecodeExample example);

    int updateByExample(@Param("record") UpStreamDecode record, @Param("example") UpStreamDecodeExample example);

    int updateByPrimaryKeySelective(UpStreamDecode record);

    int updateByPrimaryKey(UpStreamDecode record);
    //分页查询
    List<UpStreamDecode> selectByLimitPage(@Param("example") UpStreamDecodeSearchDto upStreamDecodeSearchDto, @Param("offset") int offset, @Param("limit") int pageSize);
}