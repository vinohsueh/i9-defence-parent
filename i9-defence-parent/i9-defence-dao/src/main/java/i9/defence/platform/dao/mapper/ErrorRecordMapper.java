package i9.defence.platform.dao.mapper;

import i9.defence.platform.model.ErrorRecord;
import i9.defence.platform.model.ErrorRecordExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErrorRecordMapper {
    long countByExample(ErrorRecordExample example);

    int deleteByExample(ErrorRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ErrorRecord record);

    int insertSelective(ErrorRecord record);

    List<ErrorRecord> selectByExample(ErrorRecordExample example);

    ErrorRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ErrorRecord record, @Param("example") ErrorRecordExample example);

    int updateByExample(@Param("record") ErrorRecord record, @Param("example") ErrorRecordExample example);

    int updateByPrimaryKeySelective(ErrorRecord record);

    int updateByPrimaryKey(ErrorRecord record);

    void insertErrorRecordCodeName(@Param("codeName")String codeName, @Param("deviceId")String deviceId ,@Param("createTime")Date date );
}