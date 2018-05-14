package i9.defence.platform.dao.mapper;

import i9.defence.platform.model.ConnectLog;
import i9.defence.platform.model.ConnectLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConnectLogMapper {
    long countByExample(ConnectLogExample example);

    int deleteByExample(ConnectLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConnectLog record);

    int insertSelective(ConnectLog record);

    List<ConnectLog> selectByExample(ConnectLogExample example);

    ConnectLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConnectLog record, @Param("example") ConnectLogExample example);

    int updateByExample(@Param("record") ConnectLog record, @Param("example") ConnectLogExample example);

    int updateByPrimaryKeySelective(ConnectLog record);

    int updateByPrimaryKey(ConnectLog record);
}