package i9.defence.platform.dao.mapper;

import i9.defence.platform.model.MessageLog;
import i9.defence.platform.model.MessageLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageLogMapper {
    long countByExample(MessageLogExample example);

    int deleteByExample(MessageLogExample example);

    int insert(MessageLog record);

    int insertSelective(MessageLog record);

    List<MessageLog> selectByExample(MessageLogExample example);

    int updateByExampleSelective(@Param("record") MessageLog record, @Param("example") MessageLogExample example);

    int updateByExample(@Param("record") MessageLog record, @Param("example") MessageLogExample example);
}