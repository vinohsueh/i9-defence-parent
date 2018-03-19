package i9.defence.platform.dao.mapper;

import i9.defence.platform.dao.vo.StreamOriginSearchDto;
import i9.defence.platform.model.StreamOrigin;
import i9.defence.platform.model.StreamOriginExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StreamOriginMapper {
	int countByExample(@Param("example")StreamOriginSearchDto streamOriginSearchDto);

    int deleteByExample(StreamOriginExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StreamOrigin record);

    int insertSelective(StreamOrigin record);

    List<StreamOrigin> selectByExample(StreamOriginExample example);

    StreamOrigin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StreamOrigin record, @Param("example") StreamOriginExample example);

    int updateByExample(@Param("record") StreamOrigin record, @Param("example") StreamOriginExample example);

    int updateByPrimaryKeySelective(StreamOrigin record);

    int updateByPrimaryKey(StreamOrigin record);
    
    List<StreamOrigin> selectByLimitPage(@Param("example") StreamOriginSearchDto streamOriginSearchDto, @Param("offset") int offset, @Param("limit") int pageSize);

}